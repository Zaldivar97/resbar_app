/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.disenio2018.resbar.backend.manejadores;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sv.edu.uesocc.disenio2018.resbar.backend.DetalleOrden;
import sv.edu.uesocc.disenio2018.resbar.backend.DetalleOrdenPK;
import sv.edu.uesocc.disenio2018.resbar.backend.Orden;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.ErrorApplication;

/**
 *
 * @author danm
 */
public class ManejadorOrdenTest {
    
    public ManejadorOrdenTest() {
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
     * Test of obtenerActivas method, of class ManejadorOrden.
     */
//    @Test
//    public void testObtenerActivas() {
//        System.out.println("obtenerActivas");
//        List<Orden> expResult = null;
//        List<Orden> result = ManejadorOrden.obtenerActivas();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of actualizar method, of class ManejadorOrden.
//     */
//    @Test
//    public void testActualizar() {
//        System.out.println("actualizar");
//        Orden orden = null;
//        ManejadorOrden.actualizar(orden);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of buscarActivas method, of class ManejadorOrden.
//     */
//    @Test
//    public void testBuscarActivas() {
//        System.out.println("buscarActivas");
//        String parametro = "";
//        List<Orden> expResult = null;
//        List<Orden> result = ManejadorOrden.buscarActivas(parametro);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of insertar method, of class ManejadorOrden.
     */
    @Test
    public void testInsertarGood() {
        System.out.println("**** insertar");
        Orden orden = new Orden(1, "mesero1", "mesa1", "cliente1", new Date(), new BigDecimal("12.5"), true);
        ArrayList<DetalleOrden> detalle = new ArrayList<>();
        detalle.add(new DetalleOrden(new DetalleOrdenPK(1,1), new BigDecimal(1)));
        orden.setDetalleOrdenList(detalle);
        ManejadorOrden mo = new ManejadorOrden();
                mo.insertar(orden);
        assertEquals(new Integer(1), mo.obtenerActivas().get(0).getIdOrden() );
    }
    
//    @Test
//    public void testInsertarDuplicado(){
//        System.out.println("**** insertar duplicado");
//        Orden orden = new Orden(1, "mesero1", "mesa1", "cliente1", new Date(), new BigDecimal("12.5"), true);
//        ArrayList<DetalleOrden> detalle = new ArrayList<>();
//        detalle.add(new DetalleOrden(new DetalleOrdenPK(1,1), new BigDecimal(1)));
//        orden.setDetalleOrdenList(detalle);
//        try{
//            ManejadorOrden.insertar(orden);
//        }catch(ErrorApplication e){
//            assertEquals("Orden ya existente", e.getMessage());
//        }
//    }
//
//    /**
//     * Test of eliminar method, of class ManejadorOrden.
//     */
//    @Test
//    public void testEliminar() {
//        System.out.println("eliminar");
//        Orden orden = null;
//        ManejadorOrden.eliminar(orden);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of obtenerId method, of class ManejadorOrden.
//     */
//    @Test
//    public void testObtenerId() {
//        System.out.println("obtenerId");
//        int expResult = 0;
//        int result = ManejadorOrden.obtenerId();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of ObtenerVentas method, of class ManejadorOrden.
//     */
//    @Test
//    public void testObtenerVentas() {
//        System.out.println("ObtenerVentas");
//        Date inicio = null;
//        Date fin = null;
//        List<Orden> expResult = null;
//        List<Orden> result = ManejadorOrden.ObtenerVentas(inicio, fin);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
