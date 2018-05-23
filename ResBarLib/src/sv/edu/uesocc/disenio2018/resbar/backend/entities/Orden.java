package sv.edu.uesocc.disenio2018.resbar.backend.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.ErrorApplication;

/**
 * @author zaldivar
 */
@Entity
@Table(name = "Orden", catalog = "resbar", schema = "")
@NamedQueries({
    @NamedQuery(name = "Orden.findAll", query = "SELECT o FROM Orden o")
    , @NamedQuery(name = "Orden.findByIdOrden", query = "SELECT o FROM Orden o WHERE o.idOrden = :idOrden")
    , @NamedQuery(name = "Orden.findByMesero", query = "SELECT o FROM Orden o WHERE o.mesero = :mesero")
    , @NamedQuery(name = "Orden.findByMesa", query = "SELECT o FROM Orden o WHERE o.mesa = :mesa")
    , @NamedQuery(name = "Orden.findByCliente", query = "SELECT o FROM Orden o WHERE o.cliente = :cliente")
    , @NamedQuery(name = "Orden.findByFecha", query = "SELECT o FROM Orden o WHERE o.fecha = :fecha")
    , @NamedQuery(name = "Orden.findByComentario", query = "SELECT o FROM Orden o WHERE o.comentario = :comentario")
    , @NamedQuery(name = "Orden.findByTotal", query = "SELECT o FROM Orden o WHERE o.total = :total")

    , @NamedQuery(name = "Orden.maxId", query = "SELECT MAX(o.idOrden) FROM Orden o")
    , @NamedQuery(name = "Orden.obtenerVentas", query = "SELECT o FROM Orden o WHERE (o.fecha BETWEEN :inicio AND :fin) AND o.estado = false")
    , @NamedQuery(name = "Orden.findByParametro", query = "SELECT DISTINCT o FROM Orden o WHERE (UPPER(o.mesero) LIKE CONCAT('%',UPPER(:parametro),'%') OR UPPER(o.mesa) LIKE CONCAT('%',UPPER(:parametro),'%') OR UPPER(o.cliente) LIKE CONCAT('%',UPPER(:parametro),'%') OR UPPER(o.comentario) LIKE CONCAT('%',UPPER(:parametro),'%')) AND o.estado = true")

    , @NamedQuery(name = "Orden.calcularTotal", query = "SELECT p.precio*do.cantidad FROM DetalleOrden do INNER JOIN do.producto p WHERE do.orden.idOrden = :idOrden ")
    , @NamedQuery(name = "Orden.updateDetalleOrden", query = "UPDATE DetalleOrden do SET do.cantidad = :cantidad WHERE do.orden.idOrden = :idOrden AND do.producto.idProducto = :idProducto")
    , @NamedQuery(name = "Orden.deleteDetalleOrden", query = "DELETE FROM DetalleOrden do WHERE do.orden.idOrden = :idOrden AND do.producto.idProducto = :idProducto")
    , @NamedQuery(name = "Orden.findByEstado", query = "SELECT o FROM Orden o WHERE o.estado = :estado")})
public class Orden implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "idOrden", nullable = false)
    public Integer idOrden;

    @Basic(optional = false)
    @Column(name = "mesero", nullable = false, length = 100)
    public String mesero;

    @Basic(optional = false)
    @Column(name = "mesa", nullable = false, length = 100)
    public String mesa;

    @Basic(optional = false)
    @Column(name = "cliente", nullable = false, length = 100)
    public String cliente;

    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date fecha;

    @Column(name = "comentario", length = 350)
    public String comentario;

    @Basic(optional = false)
    @Column(name = "total", nullable = false, precision = 10, scale = 4)
    public BigDecimal total;

    @Basic(optional = false)
    @Column(name = "estado", nullable = false)
    public boolean estado;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orden", fetch = FetchType.LAZY)
    public List<DetalleOrden> detalle;

    //Metodos de Orden
    protected EntityManager getEM() {
        return Persistence.createEntityManagerFactory("ResbarBackendPU").createEntityManager();
    }

    public void calcularTotal() {
        EntityManager eml = getEM();
        try {
            Query q = eml.createNamedQuery("Orden.calcularTotal");
            q.setParameter("idOrden", this.idOrden);
            this.total = (BigDecimal)q.getSingleResult();

        } catch (Exception ex) {
            throw new ErrorApplication("Error al calcular el total de la orden --> $Orden.calcularTotal()"+ex.getMessage());
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    public void agregarProducto(Producto producto, double cant) {
        
        if(cant<0){
            throw new ErrorApplication("La cantidad debe ser mayor a cero --> $Orden.agregarProducto()");
        }
        
        EntityManager eml = getEM();

        DetalleOrden detalleOrden = new DetalleOrden();
        detalleOrden.cantidad = new BigDecimal(cant);

        DetalleOrdenPK detalleOrdenPK = new DetalleOrdenPK();
        detalleOrdenPK.idOrden = this.idOrden;
        detalleOrdenPK.idProducto = producto.idProducto;

        detalleOrden.detalleOrdenPK = detalleOrdenPK;

        boolean encontrado = false;
        
        for(DetalleOrden d : this.detalle){
            if(Objects.equals(d.producto.idProducto, detalleOrden.producto.idProducto)){
                encontrado = true;
                d.cantidad.add(new BigDecimal(cant));
            }
        }
        
        if(!encontrado){
            this.detalle.add(detalleOrden);
        }

        EntityTransaction et = eml.getTransaction();
        try {
            if (!et.isActive()) {
                et.begin();
            }
            eml.merge(this);
            et.commit();
            this.calcularTotal();
        } catch (Exception ex) {
            if (et.isActive()) {
                et.rollback();
            }
            throw new ErrorApplication("Algo fallo intentando insertar un nuevo producto --> $Orden.AgregarProducto() ---> " + ex.getMessage());
        } finally {
            if (eml.isOpen()) {
                eml.close();
                this.calcularTotal();
            }
        }
    }

    public void eliminarProducto(Producto producto, double cant) {
        EntityManager eml = getEM();
        try {
            if (cant > 0) {
                Query q = eml.createNamedQuery("Orden.updateDetalleOrden");
                q.setParameter("idOrden", this.idOrden);
                q.setParameter("idProducto", producto.idProducto);
                q.setParameter("cantidad", cant);
            } else if (cant == 0) {
                Query q = eml.createNamedQuery("Orden.deleteDetalleOrden");
                q.setParameter("idOrden", this.idOrden);
                q.setParameter("idProducto", producto.idProducto);
            }
        } catch (Exception ex) {
            throw new ErrorApplication("Error al eliminar productos de la orden --> $Orden.eliminarProductos()"+ex.getMessage());
        } finally {
            if (eml.isOpen()) {
                eml.close();
                this.calcularTotal();
            }
        }
    }
}
