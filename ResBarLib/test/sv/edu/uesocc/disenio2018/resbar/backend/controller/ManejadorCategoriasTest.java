package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Categoria;

/**
 *
 * @author irvin
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    public void atestObtenerId() {
        System.out.println("ObtenerId");
        int expected = 4;
        int result = ManejadorCategorias.ObtenerId();
        assertEquals(expected, result);
    }

    /**
     * Test of Obtener method, of class ManejadorCategorias.
     */
    @Test
    public void btestObtener() {
        System.out.println("Obtener");
        boolean withDetails = true;
        int expected = 3;
        List<Categoria> result = ManejadorCategorias.Obtener(withDetails);
        assertEquals(expected, result.size());
    }

    /**
     * Test of Actualizar method, of class ManejadorCategorias.
     */
    @Test
    public void ctestActualizar() {
        System.out.println("Actualizar");
        Categoria categoria = new Categoria();
        categoria.idCategoria = 3;
        categoria.nombre = "Postres helados";
        ManejadorCategorias.Actualizar(categoria);

        List<Categoria> expected = ManejadorCategorias.Obtener(true);
        assertEquals(expected.get(2).nombre, categoria.nombre);
    }

    /**
     * Test of Insertar method, of class ManejadorCategorias.
     */
    @Test
    public void dtestInsertar() {
        System.out.println("Insertar");
        Categoria categoria = new Categoria();
        categoria.idCategoria = 4;
        categoria.nombre = "Platillos";
        ManejadorCategorias.Insertar(categoria);

        List<Categoria> expected = ManejadorCategorias.Obtener(true);
        assertEquals(expected.get(3).idCategoria, categoria.idCategoria);
        assertEquals(expected.get(3).nombre, categoria.nombre);
    }

    /**
     * Test of Eliminar method, of class ManejadorCategorias.
     */
    @Test
    public void etestEliminar() {
        System.out.println("Eliminar");
        int expected = 3;
        Categoria categoria = new Categoria();
        categoria.idCategoria = 4;
        categoria.nombre = "Platillos";
        ManejadorCategorias.Eliminar(categoria);

        List<Categoria> result = ManejadorCategorias.Obtener(true);
        assertEquals(expected, result.size());
    }

}
