/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.runners.MethodSorters;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.ErrorApplication;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.DetalleOrden;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.DetalleOrdenPK;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Orden;

/**
 *
 * @author irvin
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ManejadorOrdenesTest {
    
    public ManejadorOrdenesTest() {
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
     * Test of obtenerActivas method, of class ManejadorOrdenes.
     */
    @Test
    public void testObtenerActivas() {
        System.out.println("obtenerActivas");
        List<Orden> result = ManejadorOrdenes.obtenerActivas();
        assertEquals(4, result.size());
        assertEquals(true, result.get(0).getEstado());
    }


    /**
     * Test of actualizar method, of class ManejadorOrdenes.
     */
    @Test
    public void testActualizar() {
        System.out.println("actualizar");
        Orden orden = ManejadorOrdenes.obtener(1);
        orden.setCliente("ClienteActualizado");
        ManejadorOrdenes.actualizar(orden);
        Orden expected = ManejadorOrdenes.obtener(1);
        assertEquals(expected.getCliente(), "ClienteActualizado");
    }

    /**
     * Test of obtenerID method, of class ManejadorOrdenes.
     */
    @Test
    public void testObtenerID() {
        System.out.println("obtenerID");
        int expResult = 9;
        int result = ManejadorOrdenes.obtenerID();
        assertEquals(expResult, result);
    }

    /**
     * Test of buscarActivas method, of class ManejadorOrdenes.
     */
    @Test
    public void testBuscarActivas() {
        System.out.println("buscarActivas");
        String parametro = "an";
        List<Orden> result = ManejadorOrdenes.buscarActivas(parametro);
        assertEquals(2, result.size());
    }

    /**
     * Test of obtenerVentas method, of class ManejadorOrdenes.
     */
    @Test
    public void testObtenerVentas_Date_Date() {
        try {
            System.out.println("obtenerVentas");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            Date inicio = sdf.parse("2018-05-10") ;
            Date fin = sdf.parse("2018-05-23") ;
            
            List<Orden> result = ManejadorOrdenes.obtenerVentas(inicio, fin);
            assertEquals(3, result.size());
        } catch (ParseException ex) {
            Logger.getLogger(ManejadorOrdenesTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("error al convertir fecha");
        }
    }

    /**
     * Test of obtenerVentas method, of class ManejadorOrdenes.
     */
    @Test
    public void testObtenerVentas_Date() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date inicio = sdf.parse("2018-05-10") ;
            
            List<Orden> result = ManejadorOrdenes.obtenerVentas(inicio);
            assertEquals(1, result.size());
            assertEquals(new Integer(5), result.get(0).getIdOrden());
            System.out.println("FECHA:"+result.get(0).getFecha());
            
        } catch (ParseException ex) {
            Logger.getLogger(ManejadorOrdenesTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("error al convertir fecha");
        }
        
    }
    
    @Test(expected = ErrorApplication.class)
    public void testInsertarSinDetalle(){
        System.out.println("**** insertar Sin Detalle");
         
        Orden orden = new Orden(1, "mesero1", "mesa1", "cliente1", new Date(), new BigDecimal("12.5"), true);
        ArrayList<DetalleOrden> detalle = new ArrayList<>();
        orden.setDetalleOrdenList(detalle);
        ManejadorOrdenes.insertar(orden);
        
    }
    
    
    @Test
    public void ytestInsertarGood() {
        System.out.println("**** insertar Good");
        Orden orden = new Orden(9, "mesero1", "mesa1", "cliente1", new Date(), new BigDecimal("12.5"), true);
        ArrayList<DetalleOrden> detalle = new ArrayList<>();
        detalle.add(new DetalleOrden(new DetalleOrdenPK(1,1), new BigDecimal(1)));
        orden.setDetalleOrdenList(detalle);
        ManejadorOrdenes.insertar(orden);
        
        Orden ingresada = ManejadorOrdenes.obtener(9);
        assertEquals(new Integer(9), ingresada.getIdOrden());
        assertEquals("mesero1", ingresada.getMesero());
    }
    
    /**
     * Test of eliminar method, of class ManejadorOrdenes.
     */
    @Test(expected = ErrorApplication.class)
    public void ztestEliminar() {
        System.out.println("eliminar");
        Orden orden = ManejadorOrdenes.obtener(1);
        ManejadorOrdenes.eliminar(orden);
        
        ManejadorOrdenes.obtener(1);
    }
    
    
}
