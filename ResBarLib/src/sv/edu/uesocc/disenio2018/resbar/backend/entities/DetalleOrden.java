package sv.edu.uesocc.disenio2018.resbar.backend.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Clase: DetalleOrden. La clase “DetalleOrden” representa un objeto detalle de
 * orden con todas sus propiedades establecidas. Propiedades a comentar: La
 * propiedad producto tiene un objeto de la clase producto.
 */
@Entity
@Table(name = "DetalleOrden", catalog = "resbar", schema = "")
@NamedQueries({
    @NamedQuery(name = "DetalleOrden.findAll", query = "SELECT d FROM DetalleOrden d")
    , @NamedQuery(name = "DetalleOrden.findByIdOrden", query = "SELECT d FROM DetalleOrden d WHERE d.detalleOrdenPK.idOrden = :idOrden")
    , @NamedQuery(name = "DetalleOrden.findByIdProducto", query = "SELECT d FROM DetalleOrden d WHERE d.detalleOrdenPK.idProducto = :idProducto")
    , @NamedQuery(name = "DetalleOrden.findByCantidad", query = "SELECT d FROM DetalleOrden d WHERE d.cantidad = :cantidad")})
public class DetalleOrden implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    public DetalleOrdenPK detalleOrdenPK;

    @Basic(optional = false)
    @Column(name = "cantidad", nullable = false, precision = 8, scale = 2)
    public BigDecimal cantidad;

    @JoinColumn(name = "idOrden", referencedColumnName = "idOrden", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Orden orden;

    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Producto producto;

    public DetalleOrdenPK getDetalleOrdenPK() {
        return detalleOrdenPK;
    }

    public void setDetalleOrdenPK(DetalleOrdenPK detalleOrdenPK) {
        this.detalleOrdenPK = detalleOrdenPK;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
