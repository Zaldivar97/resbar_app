package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Parametro;

/**
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

    /**
     * Test of Actualizar method, of class ManejadorParametros.
     */
    @Test
    public void testActualizar() {
        System.out.println("actualizar");
        Parametro parametro = new Parametro();
        parametro.idParametro = 7;
        parametro.nombre = "email";
        parametro.valor = "empresaabc@gmail.com";

        ManejadorParametros.Actualizar(parametro);

        Parametro expected = ManejadorParametros.Obtener(7);
        assertEquals(expected.nombre, parametro.nombre);
        assertEquals(expected.valor, parametro.valor);
    }

    /**
     * Test of Obtener method, of class ManejadorParametros.
     */
    @Test
    public void testObtener_Integer() {
        System.out.println("obtener");
        Integer id = 1;
        Parametro expResult = new Parametro();
        expResult.idParametro = 1;
        expResult.nombre = "empresa";
        expResult.valor = "ABC";
        
        Parametro result = ManejadorParametros.Obtener(id);
        assertEquals(expResult.nombre, result.nombre);
        assertEquals(expResult.valor, result.valor);
    }

    /**
     * Test of Obtener method, of class ManejadorParametros.
     */
    @Test
    public void testObtener_0args() {
        System.out.println("obtener");
        int expected = 7;
        List<Parametro> result = ManejadorParametros.Obtener();
        assertEquals(expected, result.size());
    }
}
