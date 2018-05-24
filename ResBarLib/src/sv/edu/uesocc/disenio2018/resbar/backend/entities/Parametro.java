package sv.edu.uesocc.disenio2018.resbar.backend.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Clase: Parametro. La clase “Parametro” representa un objeto parámetro con
 * todas sus propiedades establecidas.
 */
@Entity
@Table(name = "Parametro", catalog = "resbar", schema = "")
@NamedQueries({
    @NamedQuery(name = "Parametro.findAll", query = "SELECT p FROM Parametro p")
    , @NamedQuery(name = "Parametro.findByIdParametro", query = "SELECT p FROM Parametro p WHERE p.idParametro = :idParametro")
    , @NamedQuery(name = "Parametro.findByNombre", query = "SELECT p FROM Parametro p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Parametro.findByValor", query = "SELECT p FROM Parametro p WHERE p.valor = :valor")})
public class Parametro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "idParametro", nullable = false)
    public Integer idParametro;

    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 100)
    public String nombre;

    @Basic(optional = false)
    @Column(name = "valor", nullable = false, length = 400)
    public String valor;

}
