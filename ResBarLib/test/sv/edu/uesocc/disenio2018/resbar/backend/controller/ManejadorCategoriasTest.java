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

    /**
     * Test of actualizar method, of class ManejadorCategorias.
     */
    @Test
    public void testActualizar() {
        System.out.println("actualizar");
        Categoria categoria = new Categoria(3, "Postres nuevos");
        ManejadorCategorias.actualizar(categoria);
//        Categoria expected = ;
//        assertEquals(expected, categoria.getNombre());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerId method, of class ManejadorCategorias.
     */
    @Test
    public void testObtenerID() {
        System.out.println("obtenerID");
        int expResult = 4;
        int result = ManejadorCategorias.obtenerID();
        assertEquals(expResult, result);
         //TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of insertar method, of class ManejadorCategorias.
     */
    @Test
    public void testInsertar() {
        System.out.println("insertar");
        Categoria categoria = new Categoria(4, "Categoria 2");
        ManejadorCategorias.insertar(categoria);
//        assertEquals(categoria.getIdCategoria(),);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of eliminar method, of class ManejadorCategorias.
     */
    @Test
    public void testEliminar() {
        System.out.println("eliminar");
        Categoria categoria = new Categoria(4, "Categoria 2");
        ManejadorCategorias.eliminar(categoria);
        //assertEquals(categoria, this);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of obtener method, of class ManejadorCategorias.
     */
    @Test
    public void testObtener() {
        System.out.println("obtener");
        boolean withDetails = true;
        List<Categoria> expResult = null;
        List<Categoria> result = ManejadorCategorias.obtener(withDetails);
        System.out.println(result);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

//    /**
//     * Test of getEM method, of class ManejadorCategorias.
//     */
//    @Test
//    public void testGetEM() {
//        System.out.println("getEM");
//        EntityManager expResult = null;
//        EntityManager result = ManejadorCategorias.getEM();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
    
}
