package sv.edu.uesocc.disenio2018.resbar.backend.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase: Categoria. La clase “Categoria” representa un objeto categoría con
 * todas sus propiedades establecidas, cada categoría contiene una colección de
 * productos. Propiedades a comentar: La propiedad productos tendrá una
 * colección de objetos de la clase producto.
 */
@Entity
@Table(name = "Categoria", catalog = "resbar", schema = "")
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")
    , @NamedQuery(name = "Categoria.findAllWithoutDetails", query = "SELECT c.idCategoria,c.nombre FROM Categoria c")
    , @NamedQuery(name = "Categoria.findByIdCategoria", query = "SELECT c FROM Categoria c WHERE c.idCategoria = :idCategoria")
    , @NamedQuery(name = "Categoria.findByNombre", query = "SELECT c FROM Categoria c WHERE c.nombre = :nombre")})
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "idCategoria", nullable = false)
    public Integer idCategoria;

    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 200)
    public String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria", fetch = FetchType.LAZY)
    public List<Producto> productos;

}
