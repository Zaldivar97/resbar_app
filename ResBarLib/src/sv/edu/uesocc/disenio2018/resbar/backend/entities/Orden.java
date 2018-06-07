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
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.ErrorAplicacion;

/**
 * Clase: Orden. La clase “Orden” representa un objeto que describe las órdenes
 * que se están manejando con detalle generales de las órdenes. Propiedades a
 * comentar: Para que un objeto orden sea válido, debe poseer valor en al menos
 * uno de sus campos: mesero, mesa o cliente.
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

    , @NamedQuery(name = "Orden.calcularTotal", query = "SELECT SUM(p.precio*do.cantidad) FROM DetalleOrden do INNER JOIN do.producto p WHERE do.orden.idOrden = :idOrden ")
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orden", fetch = FetchType.LAZY, orphanRemoval = true)
    public List<DetalleOrden> detalle;

    //Metodos de Orden
    protected EntityManager getEM() {
        return Persistence.createEntityManagerFactory("ResbarBackendPU").createEntityManager();
    }

    /**
     * Método: CalcularTotal() Almacena el total de consumo de la orden, para
     * ello recorre toda su colección DETALLE multiplicando el precio unitario
     * por la cantidad y luego sumándolo para al final actualizar la propiedad
     * total de la orden con el valor correcto.
     */
    public void CalcularTotal() {
        EntityManager eml = getEM();
        try {
            Query q = eml.createNamedQuery("Orden.calcularTotal");
            q.setParameter("idOrden", this.idOrden);
            this.total = (BigDecimal) q.getSingleResult();

        } catch (Exception ex) {
            throw new ErrorAplicacion("Orden.CalcularTotal()$Error al calcular el total de la orden");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    /**
     * Método: AgregarProducto(:producto,cant:double) Permite agregar más
     * productos a la orden, toma el objeto producto y la cantidad para
     * construir un objeto DetalleOrden, y luego ver si ese producto ya está
     * agregado a la orden, si ya está agregado a la orden, entonces solo se
     * suma la cantidad, sino se agrega a la colección DETALLE de la orden y se
     * invoca calcular total.
     */
    public void AgregarProducto(Producto producto, double cant) {

        if (cant <= 0) {
            throw new ErrorAplicacion("Orden.AgregarProducto()$La cantidad debe ser mayor a cero");
        }

        EntityManager eml = getEM();

        DetalleOrden detalleOrden = new DetalleOrden();
        detalleOrden.cantidad = new BigDecimal(cant);

        DetalleOrdenPK detalleOrdenPK = new DetalleOrdenPK();
        detalleOrdenPK.idOrden = this.idOrden;
        detalleOrdenPK.idProducto = producto.idProducto;

        detalleOrden.detalleOrdenPK = detalleOrdenPK;

        boolean encontrado = false;

        for (DetalleOrden d : this.detalle) {
            if (Objects.equals(d.producto.idProducto, detalleOrden.producto.idProducto)) {
                encontrado = true;
                d.cantidad.add(new BigDecimal(cant));
            }
        }

        if (!encontrado) {
            this.detalle.add(detalleOrden);
        }

        EntityTransaction et = eml.getTransaction();
        try {
            if (!et.isActive()) {
                et.begin();
            }
            eml.merge(this);
            et.commit();
            this.CalcularTotal();
        } catch (Exception ex) {
            if (et.isActive()) {
                et.rollback();
            }
            throw new ErrorAplicacion("Orden.AgregarProducto()$Algo fallo intentando agregar un nuevo producto");
        } finally {
            if (eml.isOpen()) {
                eml.close();
                this.CalcularTotal();
            }
        }
    }

    /**
     * Método: EliminarProducto(:producto,cant:double) Permite eliminar
     * productos de una orden y actualiza el total de la orden.
     */
    public void EliminarProducto(Producto producto, double cant) {
        if (cant < 0) {
            throw new ErrorAplicacion("Orden.EliminarProducto()$La cantidad debe ser mayor a cero");
        }
        
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
            throw new ErrorAplicacion("Orden.EliminarProducto()$Error al eliminar productos de la orden");
        } finally {
            if (eml.isOpen()) {
                eml.close();
                this.CalcularTotal();
            }
        }
    }

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public String getMesero() {
        return mesero;
    }

    public void setMesero(String mesero) {
        this.mesero = mesero;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<DetalleOrden> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleOrden> detalle) {
        this.detalle = detalle;
    }
    
    
}
