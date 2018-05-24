package sv.edu.uesocc.disenio2018.resbar.backend.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author zaldivar
 */
@Embeddable
public class DetalleOrdenPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idOrden", nullable = false)
    public int idOrden;

    @Basic(optional = false)
    @Column(name = "idProducto", nullable = false)
    public int idProducto;
    
}
