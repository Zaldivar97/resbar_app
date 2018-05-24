package sv.edu.uesocc.disenio2018.resbar.backend.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.ManejadorOrdenes;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.ManejadorProductos;

/**
 * @author danm
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrdenTest {

    public OrdenTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of CalcularTotal method, of class Orden.
     */
    @Test
    public void testCalcularTotal() {
        System.out.println("calcularTotal");
        Orden orden = ManejadorOrdenes.Obtener(1);
        orden.CalcularTotal();
        BigDecimal total = new BigDecimal(2 * 2.5);
        assertTrue(total.compareTo(orden.total)==0);
    }

    /**
     * Test of agregarProducto method, of class Orden.
     */
//    @Test
//    public void xtestAgregarProducto() {
//        System.out.println("AgregarProducto");
//        Producto producto = ManejadorProductos.Obtener(2);
//        double cantidad = 2;
//        
//        Orden orden = ManejadorOrdenes.Obtener(2);
//        orden.AgregarProducto(producto, cantidad);
//        
//        assertEquals(2, orden.getDetalleOrdenList().size());
//        
//    }
    /**
     * Test of eliminarProducto method, of class Orden.
     */
//    @Test
//    public void ytestEliminarProducto() {
//        System.out.println("EliminarProducto");
//        Producto producto = ManejadorProductos.Obtener(2);
//        double cantidad = 2;
//        
//        Orden orden = ManejadorOrdenes.Obtener(2);
//        orden.EliminarProducto(producto, cantidad);
//        
//        assertEquals(1, ManejadorOrdenes.Obtener(2).getDetalleOrdenList().size());
//        
//        
//    }
//    
}
