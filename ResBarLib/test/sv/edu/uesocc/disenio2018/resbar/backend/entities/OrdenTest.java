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
     * Test of calcularTotal method, of class Orden.
     */
    @Test
    public void testCalcularTotal() {
        System.out.println("calcularTotal");
        Orden orden = ManejadorOrdenes.Obtener(1);
        orden.calcularTotal();
        double total = 2 * 2.5;
        assertTrue(total == orden.total);
    }

    /**
     * Test of agregarProducto method, of class Orden.
     */
//    @Test
//    public void xtestAgregarProducto() {
//        System.out.println("agregarProducto");
//        Producto producto = ManejadorProductos.Obtener(2);
//        double cantidad = 2;
//        
//        Orden orden = ManejadorOrdenes.Obtener(2);
//        orden.agregarProducto(producto, cantidad);
//        
//        assertEquals(2, orden.getDetalleOrdenList().size());
//        
//    }
    /**
     * Test of eliminarProducto method, of class Orden.
     */
//    @Test
//    public void ytestEliminarProducto() {
//        System.out.println("eliminarProducto");
//        Producto producto = ManejadorProductos.Obtener(2);
//        double cantidad = 2;
//        
//        Orden orden = ManejadorOrdenes.Obtener(2);
//        orden.eliminarProducto(producto, cantidad);
//        
//        assertEquals(1, ManejadorOrdenes.Obtener(2).getDetalleOrdenList().size());
//        
//        
//    }
//    
}
