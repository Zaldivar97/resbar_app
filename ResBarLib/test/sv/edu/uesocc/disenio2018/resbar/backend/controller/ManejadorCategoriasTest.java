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
     * Test of obtenerId method, of class ManejadorCategorias.
     */
    @Test
    public void testObtenerID() {
        System.out.println("obtenerID");
        int expected = 4;
        int result = ManejadorCategorias.obtenerID();
        assertEquals(expected, result);
    }

    /**
     * Test of obtener method, of class ManejadorCategorias.
     */
    @Test
    public void testObtener() {
        System.out.println("obtener");
        boolean withDetails = true;
        int expected = 3;
        List<Categoria> result = ManejadorCategorias.obtener(withDetails);
        assertEquals(expected, result.size());
    }
    
    /**
     * Test of actualizar method, of class ManejadorCategorias.
     */
    @Test
    public void testActualizar() {
        System.out.println("actualizar");
        Categoria categoria = new Categoria(3, "Postres");
        ManejadorCategorias.actualizar(categoria);
        
        List<Categoria> expected = ManejadorCategorias.obtener(true);
        assertEquals(expected.get(2).getNombre(), categoria.getNombre());
    }


    /**
     * Test of insertar method, of class ManejadorCategorias.
     */
    @Test
    public void testInsertar() {
        System.out.println("insertar");
        Categoria categoria = new Categoria(4, "Categoria 2");
        ManejadorCategorias.insertar(categoria);
        
        List<Categoria> expected = ManejadorCategorias.obtener(true);
        assertEquals(expected.get(3).getIdCategoria(),categoria.getIdCategoria());
        assertEquals(expected.get(3).getNombre(),categoria.getNombre());
    }

    /**
     * Test of eliminar method, of class ManejadorCategorias.
     */
    @Test
    public void testEliminar() {
        System.out.println("eliminar");
        int expected = 3;
        Categoria categoria = new Categoria(4, "Categoria 2");
        ManejadorCategorias.eliminar(categoria);
        
        List<Categoria> result = ManejadorCategorias.obtener(true);
        assertEquals(expected, result.size());
    }
    
}
