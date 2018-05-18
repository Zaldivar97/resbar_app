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
import sv.edu.uesocc.disenio2018.resbar.backend.controller.ManejadorOrdenes;

/**
 *
 * @author danm
 */
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
    @Test
    public void testAgregarProducto() {
        System.out.println("agregarProducto");
        Producto producto = null;
        double cantidad = 2;
        Orden instance = new Orden();
        instance.agregarProducto(producto, cantidad);
        
    }

    /**
     * Test of eliminarProducto method, of class Orden.
     */
    @Test
    public void testEliminarProducto() {
        System.out.println("eliminarProducto");
        Producto producto = null;
        double cantidad = 0.0;
        Orden instance = new Orden();
        instance.eliminarProducto(producto, cantidad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
