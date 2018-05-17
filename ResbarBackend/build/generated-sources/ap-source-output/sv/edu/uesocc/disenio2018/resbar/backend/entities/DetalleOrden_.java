package sv.edu.uesocc.disenio2018.resbar.backend.entities;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.DetalleOrdenPK;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Orden;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Producto;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2018-05-17T01:09:36")
@StaticMetamodel(DetalleOrden.class)
public class DetalleOrden_ { 

    public static volatile SingularAttribute<DetalleOrden, BigDecimal> cantidad;
    public static volatile SingularAttribute<DetalleOrden, Orden> orden;
    public static volatile SingularAttribute<DetalleOrden, Producto> producto;
    public static volatile SingularAttribute<DetalleOrden, DetalleOrdenPK> detalleOrdenPK;

}