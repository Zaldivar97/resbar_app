/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.disenio2018.resbar.backend.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
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
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.ErrorApplication;

/**
 *
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
  , @NamedQuery(name = "Orden.findByParametro", query = "SELECT DISTINCT o FROM Orden o WHERE (UPPER(o.mesero) LIKE UPPER(:parametro) OR UPPER(o.mesa) LIKE UPPER(:parametro) OR UPPER(o.cliente) LIKE UPPER(:parametro) OR UPPER(o.comentario) LIKE UPPER(:parametro)) AND o.estado = true")

    , @NamedQuery(name = "Orden.calcularTotal", query = "SELECT p.precio*do.cantidad FROM DetalleOrden do INNER JOIN do.producto p WHERE do.orden.idOrden = :idOrden ")
    , @NamedQuery(name = "Orden.updateDetalleOrden", query = "UPDATE DetalleOrden do SET do.cantidad = :cantidad WHERE do.orden.idOrden = :idOrden AND do.producto.idProducto = :idProducto")
    , @NamedQuery(name = "Orden.deleteDetalleOrden", query = "DELETE FROM DetalleOrden do WHERE do.orden.idOrden = :idOrden AND do.producto.idProducto = :idProducto")
    , @NamedQuery(name = "Orden.findByEstado", query = "SELECT o FROM Orden o WHERE o.estado = :estado")})
public class Orden implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idOrden", nullable = false)
    private Integer idOrden;
    @Basic(optional = false)
    @Column(name = "mesero", nullable = false, length = 100)
    private String mesero;
    @Basic(optional = false)
    @Column(name = "mesa", nullable = false, length = 100)
    private String mesa;
    @Basic(optional = false)
    @Column(name = "cliente", nullable = false, length = 100)
    private String cliente;
    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "comentario", length = 350)
    private String comentario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "total", nullable = false, precision = 10, scale = 4)
    private BigDecimal total;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false)
    private boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orden", fetch = FetchType.EAGER)
    private List<DetalleOrden> detalleOrdenList;

    public Orden() {
    }

    public Orden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public Orden(Integer idOrden, String mesero, String mesa, String cliente, Date fecha, BigDecimal total, boolean estado) {
        this.idOrden = idOrden;
        this.mesero = mesero;
        this.mesa = mesa;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
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

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<DetalleOrden> getDetalleOrdenList() {
        return detalleOrdenList;
    }

    public void setDetalleOrdenList(List<DetalleOrden> detalleOrdenList) {
        this.detalleOrdenList = detalleOrdenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrden != null ? idOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orden)) {
            return false;
        }
        Orden other = (Orden) object;
        if ((this.idOrden == null && other.idOrden != null) || (this.idOrden != null && !this.idOrden.equals(other.idOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.edu.uesocc.disenio2018.resbar.backend.entities.Orden[ idOrden=" + idOrden + " ]";
    }

    //Metodos 
    protected EntityManager getEM() {
        return Persistence.createEntityManagerFactory("ResbarBackendPU").createEntityManager();
    }

    public void calcularTotal() {
        EntityManager eml = getEM();
        try {
            Query q = eml.createNamedQuery("Orden.calcularTotal");
            q.setParameter("idOrden", this.getIdOrden());
            this.setTotal((BigDecimal) (q.getSingleResult()));

        } catch (Exception ex) {
            throw new ErrorApplication("Error al calcular el total de la orden --> $Orden.calcularTotal()");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    /////////////////////REVISAR ESTE METODO//////////////////////////////////////////////////
    public void agregarProducto(Producto producto, double cantidad) {
        EntityManager eml = getEM();
        try {
            DetalleOrden detalleOrden = new DetalleOrden();
            detalleOrden.setOrden(this);
            detalleOrden.setProducto(producto);
            detalleOrden.setCantidad((BigDecimal.valueOf(cantidad))); //Revisar esto

//            Query q = eml.createNamedQuery("Orden.");
//            q.setParameter("idOrden", this.idOrden);
        } catch (Exception ex) {
            throw new ErrorApplication("Error al agregar productos a la orden --> $Orden.agregarProductos()");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }

    }

    public void eliminarProducto(Producto producto, double cantidad) {
        EntityManager eml = getEM();
        try {

            if (cantidad > 0) {
                Query q = eml.createNamedQuery("Orden.updateDetalleOrden");
                q.setParameter("idOrden", this.getIdOrden());
                q.setParameter("idProducto", producto.getIdProducto());
                q.setParameter("cantidad", cantidad);

            } else if (cantidad == 0) {
                Query q = eml.createNamedQuery("Orden.deleteDetalleOrden");
                q.setParameter("idOrden", this.getIdOrden());
                q.setParameter("idProducto", producto.getIdProducto());

            }

        } catch (Exception ex) {
            throw new ErrorApplication("Error al eliminar productos de la orden --> $Orden.eliminarProductos()");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }

    }

}
