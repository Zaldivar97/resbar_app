/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Categoria;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Producto;

/**
 *
 * @author zaldivar
 */
public class ManejadorProductoTest {

    public ManejadorProductoTest() {
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
     * Test of obtenerxCategoria method, of class ManejadorProducto.
     */
//    @Ignore
    @Test
    public void Testcreate() {
        Producto producto = new Producto(ManejadorProducto.obtenerID(), "nombre", BigDecimal.ONE, '1');
        Categoria categoria=null;
        producto.setIdCategoria(categoria);
        ManejadorProducto.insertar(producto);
        System.out.println("el ID es: " + producto.getIdProducto());
        Producto expected = (Producto) ManejadorProducto.obtener(producto.getIdProducto());
        assertNotNull(expected);
        assertEquals(producto.getIdProducto(), expected.getIdProducto());
        fail("falloooooooooooooo");
    }

    @Test
    public void TestMax(){
        int result=ManejadorProducto.obtenerID();
        assertEquals(17, result);
    }
    
}