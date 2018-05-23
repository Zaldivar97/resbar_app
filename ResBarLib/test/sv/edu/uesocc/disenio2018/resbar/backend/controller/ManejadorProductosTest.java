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
     * Test of ObtenerxCategoria method, of class ManejadorProductos.
     */
    @Ignore
    @Test
    public void TestMax() {
        int result = ManejadorProductos.ObtenerID();
        assertEquals(17, result);
    }

    /**
     * Test of Insertar method, of class ManejadorProductos.
     */
    @Test
    public void testInsertar() {
        System.out.println("insertar");
        Producto producto = new Producto();
        producto.idProducto = ManejadorProductos.ObtenerID();
        producto.nombre = "Insertado en una prueba!!!!";
        producto.precio = 1;
        producto.area = 'B';

        Categoria categoria = new Categoria();
        categoria.idCategoria = 1;
        categoria.nombre = "Bebidas";

        producto.categoria = categoria;
        ManejadorProductos.Insertar(producto);

        Producto expected = ManejadorProductos.Obtener(producto.idProducto);
        assertNotNull(expected);
        assertEquals(producto.idProducto, expected.idProducto);
    }

    /**
     * Test of Eliminar method, of class ManejadorProductos.
     */
    @Test
    public void testEliminar() {
        System.out.println("eliminar");
        int id = ManejadorProductos.ObtenerID() - 1;
        Producto entity = ManejadorProductos.Obtener(id);
        ManejadorProductos.Eliminar(entity);
        assertNull(ManejadorProductos.Obtener(id));
    }

    /**
     * Test of Actualizar method, of class ManejadorProductos.
     */
    @Test
    public void testActualizar() {
        System.out.println("actualizar");
        Producto actualizado = ManejadorProductos.Obtener(4);
        actualizado.precio = 10;
        actualizado.area = 'B';
        actualizado.nombre = "Esto fue actualizado mediante un test";
        ManejadorProductos.Actualizar(actualizado);
        Producto obtenido = ManejadorProductos.Obtener(4);
        assertEquals("Esto fue actualizado mediante un test", obtenido.nombre);
    }

    /**
     * Test of Obtener method, of class ManejadorProductos.
     */
    @Ignore
    @Test
    public void testObtener() {
        System.out.println("obtener");
        Integer id = 5;
        Producto result = ManejadorProductos.Obtener(id);
        assertNotNull(result);
    }

    /**
     * Test of ObtenerID method, of class ManejadorProductos.
     */
    @Ignore
    @Test
    public void testObtenerID() {
        System.out.println("obtenerID");
        int expResult = 0;
        int result = ManejadorProductos.ObtenerID();
        assertEquals(expResult, result);
    }

    /**
     * Test of ObtenerxCategoria method, of class ManejadorProductos.
     */
    @Test
    public void testObtenerxCategoria() {
        System.out.println("obtenerxCategoria");
        int id = 3;
        List<Producto> result = ManejadorProductos.ObtenerxCategoria(id);
        assertEquals(new Integer(7), result.get(0).idProducto);
    }

    /**
     * Test of Buscar method, of class ManejadorProductos.
     */
    @Test
    public void testBuscar() {
        System.out.println("buscar");
        String charSequence = "libre";
        List<Producto> result = ManejadorProductos.Buscar(charSequence);
        assertEquals(new Integer(3), result.get(0).idProducto);
    }

}
