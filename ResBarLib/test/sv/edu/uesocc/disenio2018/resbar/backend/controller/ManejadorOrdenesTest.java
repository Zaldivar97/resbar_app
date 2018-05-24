package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.ErrorApplication;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.DetalleOrden;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.DetalleOrdenPK;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Orden;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Producto;

/**
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
     * Test of ObtenerActivas method, of class ManejadorOrdenes.
     */
    @Test
    public void testObtenerActivas() {
        System.out.println("obtenerActivas");
        List<Orden> result = ManejadorOrdenes.ObtenerActivas();
        assertEquals(4, result.size());
        System.out.println("*********** Detalle orden "+result.get(0).detalle);
        Producto p = result.get(0).detalle.get(0).producto;
        System.out.println("*********** Producto del detalle: "+ p);
        assertEquals(true, result.get(0).estado);
        
    }

    /**
     * Test of Actualizar method, of class ManejadorOrdenes.
     */
    @Test
    public void testActualizar() {
        System.out.println("actualizar");
        Orden orden = ManejadorOrdenes.Obtener(1);
        orden.cliente = "ClienteActualizado";
        ManejadorOrdenes.Actualizar(orden);
        Orden expected = ManejadorOrdenes.Obtener(1);
        assertEquals(expected.cliente, "ClienteActualizado");
    }

    /**
     * Test of ObtenerId method, of class ManejadorOrdenes.
     */
    @Test
    public void testObtenerID() {
        System.out.println("obtenerID");
        int expResult = 9;
        int result = ManejadorOrdenes.ObtenerId();
        assertEquals(expResult, result);
    }

    /**
     * Test of BuscarActivas method, of class ManejadorOrdenes.
     */
    @Test
    public void testBuscarActivas() {
        System.out.println("buscarActivas");
        String parametro = "an";
        List<Orden> result = ManejadorOrdenes.BuscarActivas(parametro);
        assertEquals(2, result.size());
    }

    /**
     * Test of ObtenerVentas method, of class ManejadorOrdenes.
     */
    @Test
    public void testObtenerVentas_Date_Date() {
        try {
            System.out.println("obtenerVentas");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date inicio = sdf.parse("2018-05-10");
            Date fin = sdf.parse("2018-05-23");

            List<Orden> result = ManejadorOrdenes.ObtenerVentas(inicio, fin);
            assertEquals(3, result.size());
        } catch (ParseException ex) {
            Logger.getLogger(ManejadorOrdenesTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("error al convertir fecha");
        }
    }

    /**
     * Test of ObtenerVentas method, of class ManejadorOrdenes.
     */
    @Test
    public void testObtenerVentas_Date() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date inicio = sdf.parse("2018-05-10");

            List<Orden> result = ManejadorOrdenes.ObtenerVentas(inicio);
            assertEquals(1, result.size());
            assertEquals(new Integer(5), result.get(0).idOrden);
            System.out.println("FECHA:" + result.get(0).fecha);

        } catch (ParseException ex) {
            Logger.getLogger(ManejadorOrdenesTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("error al convertir fecha");
        }

    }

//    @Test(expected = ErrorApplication.class)
//    public void testInsertarSinDetalle() {
//        System.out.println("**** insertar Sin Detalle");
//
//        Orden orden = new Orden();
//        orden.idOrden = 1;
//        orden.mesero = "mesero1";
//        orden.mesa = "mesa1";
//        orden.cliente = "cliente1";
//        orden.fecha = new DateTime();
//        orden.total = 12.5;
//        orden.estado = true;
//
//        ArrayList<DetalleOrden> detalle = new ArrayList<>();
//        orden.detalle = detalle;
//        ManejadorOrdenes.Insertar(orden);
//
//    }
//
//    @Test
//    public void ytestInsertarGood() {
//        System.out.println("**** insertar Good");
//        Orden orden = new Orden();
//        orden.idOrden = 9;
//        orden.mesero = "mesero1";
//        orden.mesa = "mesa1";
//        orden.cliente = "cliente1";
//        orden.fecha = new DateTime();
//        orden.total = 12.5;
//        orden.estado = true;
//
//        ArrayList<DetalleOrden> detalle = new ArrayList<>();
//        
//        DetalleOrden detalleOrden = new DetalleOrden();
//        detalleOrden.cantidad = 1;
//        
//        DetalleOrdenPK detalleOrdenPK = new DetalleOrdenPK();
//        detalleOrdenPK.idOrden= 1;
//        detalleOrdenPK.idProducto= 1;
//        
//        detalleOrden.detalleOrdenPK = detalleOrdenPK;
//        detalle.add(detalleOrden);
//        
//        orden.detalle = detalle;
//        ManejadorOrdenes.Insertar(orden);
//
//        Orden ingresada = ManejadorOrdenes.Obtener(9);
//        assertEquals(new Integer(9), ingresada.idOrden);
//        assertEquals("mesero1", ingresada.mesero);
//    }

    /**
     * Test of Eliminar method, of class ManejadorOrdenes.
     */
    @Test(expected = ErrorApplication.class)
    public void ztestEliminar() {
        System.out.println("eliminar");
        Orden orden = ManejadorOrdenes.Obtener(9);
        ManejadorOrdenes.Eliminar(orden);

        ManejadorOrdenes.Obtener(9);
    }

}
