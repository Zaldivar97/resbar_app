/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import java.math.BigDecimal;
import java.util.List;
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
public class ManejadorProductosTest {

    public ManejadorProductosTest() {
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
     * Test of obtenerxCategoria method, of class ManejadorProductos.
     */
   @Ignore
    @Test
    public void TestMax() {
        int result = ManejadorProductos.obtenerID();
        assertEquals(17, result);
    }

    /**
     * Test of getEM method, of class ManejadorProductos.
     */
   

    /**
     * Test of insertar method, of class ManejadorProductos.
     */
    @Test
    public void testInsertar() {
        System.out.println("insertar");
        Producto producto = new Producto(ManejadorProductos.obtenerID(), "Insertado en una prueba!!!!", BigDecimal.ONE, 'B');
        Categoria categoria = ManejadorCategorias.obtener(2);
        producto.setIdCategoria(categoria);
        ManejadorProductos.insertar(producto);
        Producto expected = ManejadorProductos.obtener(producto.getIdProducto());
        assertNotNull(expected);
        assertEquals(producto.getIdProducto(), expected.getIdProducto());
    }

    /**
     * Test of eliminar method, of class ManejadorProductos.
     */
    @Test
    public void testEliminar() {
        System.out.println("eliminar");
        int id = ManejadorProductos.obtenerID()-1;
        Producto entity = ManejadorProductos.obtener(id);
        ManejadorProductos.eliminar(entity);
        assertNull(ManejadorProductos.obtener(id));
    }

    /**
     * Test of actualizar method, of class ManejadorProductos.
     */
    @Test
    public void testActualizar() {
        System.out.println("actualizar");
        Producto actualizado = ManejadorProductos.obtener(4);
        actualizado.setPrecio(BigDecimal.TEN);
        actualizado.setArea('B');
        actualizado.setNombre("Esto fue actualizado mediante un test");
        ManejadorProductos.actualizar(actualizado);
        Producto obtenido = ManejadorProductos.obtener(4);
        assertEquals("Esto fue actualizado mediante un test", obtenido.getNombre());
    }

    /**
     * Test of obtener method, of class ManejadorProductos.
     */
    @Ignore
    @Test
    public void testObtener() {
        System.out.println("obtener");
        Integer id = 5;
        Producto result = ManejadorProductos.obtener(id);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of obtenerID method, of class ManejadorProductos.
     */
    @Ignore
    @Test
    public void testObtenerID() {
        System.out.println("obtenerID");
        int expResult = 0;
        int result = ManejadorProductos.obtenerID();
        assertEquals(expResult, result);

    }

    /**
     * Test of obtenerxCategoria method, of class ManejadorProductos.
     */
    @Test
    public void testObtenerxCategoria() {
        System.out.println("obtenerxCategoria");
        int id = 3;
        List<Producto> result = ManejadorProductos.obtenerxCategoria(id);

        assertEquals(new Integer(7), result.get(0).getIdProducto());
    }

    /**
     * Test of buscar method, of class ManejadorProductos.
     */
    @Test
    public void testBuscar() {
        System.out.println("buscar");
        
        String charSequence = "libre";
        List<Producto> result = ManejadorProductos.buscar(charSequence);
        assertEquals(new Integer(3), result.get(0).getIdProducto());

    }

}
