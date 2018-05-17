/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Categoria;

/**
 *
 * @author irvin
 */
public class ManejadorCategoriasTest {
    
    public ManejadorCategoriasTest() {
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

//    /**
//     * Test of actualizar method, of class ManejadorCategorias.
//     */
//    @Test
//    public void testActualizar() {
//        System.out.println("actualizar");
//        Categoria categoria = null;
//        ManejadorCategorias instance = new ManejadorCategorias();
//        instance.actualizar(categoria);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
    /**
     * Test of obtenerId method, of class ManejadorCategorias.
     */
    @Test
    public void testObtenerID() {
        System.out.println("obtenerID");
        int expResult = 2;
        int result = ManejadorCategorias.obtenerID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
//
//    /**
//     * Test of eliminar method, of class ManejadorCategorias.
//     */
//    @Test
//    public void testEliminar() {
//        System.out.println("eliminar");
//        Categoria categoria = null;
//        ManejadorCategorias instance = new ManejadorCategorias();
//        instance.eliminar(categoria);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of insertar method, of class ManejadorCategorias.
//     */
//    @Test
//    public void testInsertar() {
//        System.out.println("insertar");
//        Categoria categoria = new Categoria(2, "Categoria 2");
//        ManejadorCategorias.insertar(categoria);
////        assertEquals(categoria.getIdCategoria(),);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of obtener method, of class ManejadorCategorias.
//     */
//    @Test
//    public void testObtener() {
//        System.out.println("obtener");
//        boolean withDetails = false;
//        ManejadorCategorias instance = new ManejadorCategorias();
//        List<Categoria> expResult = null;
//        List<Categoria> result = instance.obtener(true);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//    
}
