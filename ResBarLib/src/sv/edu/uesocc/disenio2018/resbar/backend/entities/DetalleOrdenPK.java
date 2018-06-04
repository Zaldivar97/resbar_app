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

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    
}
