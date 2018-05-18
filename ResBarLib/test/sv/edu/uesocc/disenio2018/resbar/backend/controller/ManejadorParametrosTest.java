/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import java.util.List;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Parametro;

/**
 *
 * @author irvin
 */
public class ManejadorParametrosTest {
    
    public ManejadorParametrosTest() {
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
//     * Test of getEM method, of class ManejadorParametros.
//     */
//    @Test
//    public void testGetEM() {
//        System.out.println("getEM");
//        EntityManager expResult = null;
//        EntityManager result = ManejadorParametros.getEM();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }


    /**
     * Test of obtener method, of class ManejadorParametros.
     */
    @Test
    public void testObtener_Integer() {
        System.out.println("obtener");
        Integer id = 1;
        Parametro expResult = new Parametro(1, "empresa", "ABC");
        Parametro result = ManejadorParametros.obtener(id);
        assertEquals(expResult.getIdParametro(), result.getIdParametro());
        assertEquals(expResult.getNombre(), result.getNombre());
        assertEquals(expResult.getValor(), result.getValor());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of obtener method, of class ManejadorParametros.
     */
    @Test
    public void testObtener_0args() {
        System.out.println("obtener");
        List<Parametro> expResult = null;
        List<Parametro> result = ManejadorParametros.obtener();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of actualizar method, of class ManejadorParametros.
     */
    @Test
    public void testActualizar() {
        System.out.println("actualizar");
        Parametro entityObject = new Parametro(1, "empresa", "ABC");
        ManejadorParametros.actualizar(entityObject);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
