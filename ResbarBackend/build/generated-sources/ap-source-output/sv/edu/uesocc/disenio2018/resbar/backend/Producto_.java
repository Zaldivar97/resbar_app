package sv.edu.uesocc.disenio2018.resbar.backend;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.edu.uesocc.disenio2018.resbar.backend.Categoria;
import sv.edu.uesocc.disenio2018.resbar.backend.DetalleOrden;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2018-05-15T19:27:50")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, Character> area;
    public static volatile SingularAttribute<Producto, BigDecimal> precio;
    public static volatile ListAttribute<Producto, DetalleOrden> detalleOrdenList;
    public static volatile SingularAttribute<Producto, Integer> idProducto;
    public static volatile SingularAttribute<Producto, Categoria> idCategoria;
    public static volatile SingularAttribute<Producto, String> nombre;

}