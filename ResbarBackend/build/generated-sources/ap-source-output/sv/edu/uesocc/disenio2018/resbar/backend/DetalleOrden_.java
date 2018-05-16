package sv.edu.uesocc.disenio2018.resbar.backend;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.uesocc.disenio2018.resbar.backend.DetalleOrdenPK;
import sv.edu.uesocc.disenio2018.resbar.backend.Orden;
import sv.edu.uesocc.disenio2018.resbar.backend.Producto;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2018-05-15T19:27:50")
@StaticMetamodel(DetalleOrden.class)
public class DetalleOrden_ { 

    public static volatile SingularAttribute<DetalleOrden, BigDecimal> cantidad;
    public static volatile SingularAttribute<DetalleOrden, Orden> orden;
    public static volatile SingularAttribute<DetalleOrden, Producto> producto;
    public static volatile SingularAttribute<DetalleOrden, DetalleOrdenPK> detalleOrdenPK;

}