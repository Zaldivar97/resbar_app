/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
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
     * Test of getIdOrden method, of class Orden.
     */
   
    /**
     * Test of calcularTotal method, of class Orden.
     */
    @Test
    public void testCalcularTotal() {
        System.out.println("calcularTotal");
        Orden instance = ManejadorOrdenes.obtener(1);
        instance.calcularTotal();
        
        BigDecimal total = new BigDecimal( (2*2.5) );
        
        assertTrue(total.compareTo(instance.getTotal()) == 0); 
    }

    /**
     * Test of agregarProducto method, of class Orden.
     */
//    @Test
//    public void xtestAgregarProducto() {
//        System.out.println("agregarProducto");
//        Producto producto = ManejadorProductos.obtener(2);
//        double cantidad = 2;
//        
//        Orden instance = ManejadorOrdenes.obtener(2);
//        instance.agregarProducto(producto, cantidad);
//        
//        assertEquals(2, instance.getDetalleOrdenList().size());
//        
//    }

    /**
     * Test of eliminarProducto method, of class Orden.
     */
//    @Test
//    public void ytestEliminarProducto() {
//        System.out.println("eliminarProducto");
//        Producto producto = ManejadorProductos.obtener(2);
//        double cantidad = 2;
//        
//        Orden instance = ManejadorOrdenes.obtener(2);
//        instance.eliminarProducto(producto, cantidad);
//        
//        assertEquals(1, ManejadorOrdenes.obtener(2).getDetalleOrdenList().size());
//        
//        
//    }
//    
}
