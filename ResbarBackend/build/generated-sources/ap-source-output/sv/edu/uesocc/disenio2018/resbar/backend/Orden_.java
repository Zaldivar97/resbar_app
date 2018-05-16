package sv.edu.uesocc.disenio2018.resbar.backend;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.uesocc.disenio2018.resbar.backend.DetalleOrden;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2018-05-15T19:27:50")
@StaticMetamodel(Orden.class)
public class Orden_ { 

    public static volatile SingularAttribute<Orden, String> cliente;
    public static volatile SingularAttribute<Orden, Date> fecha;
    public static volatile SingularAttribute<Orden, String> mesero;
    public static volatile SingularAttribute<Orden, BigDecimal> total;
    public static volatile SingularAttribute<Orden, Boolean> estado;
    public static volatile SingularAttribute<Orden, String> mesa;
    public static volatile ListAttribute<Orden, DetalleOrden> detalleOrdenList;
    public static volatile SingularAttribute<Orden, String> comentario;
    public static volatile SingularAttribute<Orden, Integer> idOrden;

}