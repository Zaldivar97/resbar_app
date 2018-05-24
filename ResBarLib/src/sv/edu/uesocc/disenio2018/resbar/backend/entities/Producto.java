package sv.edu.uesocc.disenio2018.resbar.backend.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase: Producto. La clase “Producto” representa un objeto producto con todas
 * sus propiedades establecidas. Propiedades a comentar: area tendrá un valor C
 * si el producto debe prepararse en cocina y B si el producto debe prepararse
 * en el área de bebidas. Categoría: esta propiedad tendrá un objeto categoría,
 * pero dicho objeto categoría no tendrá cargados los productos.
 */
@Entity
@Table(name = "Producto", catalog = "resbar", schema = "")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto")
    , @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Producto.findByPrecio", query = "SELECT p FROM Producto p WHERE p.precio = :precio")
    , @NamedQuery(name = "Producto.findByArea", query = "SELECT p FROM Producto p WHERE p.area = :area")
    , @NamedQuery(name = "Producto.findByCategoria", query = "SELECT p FROM Producto p WHERE p.categoria.idCategoria = :idCategoria")
    , @NamedQuery(name = "Producto.findByCharsequence", query = "SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE CONCAT('%',LOWER(:nombre),'%')")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "idProducto", nullable = false)
    public Integer idProducto;

    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 200)
    public String nombre;

    @Basic(optional = false)
    @Column(name = "precio", nullable = false, precision = 10, scale = 4)
    public BigDecimal precio;

    @Basic(optional = false)
    @Column(name = "area", nullable = false)
    public Character area;

    @JoinColumn(name = "idCategoria", referencedColumnName = "idCategoria", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Categoria categoria;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto", fetch = FetchType.LAZY)
    public List<DetalleOrden> detalleOrdenList;

    
}
