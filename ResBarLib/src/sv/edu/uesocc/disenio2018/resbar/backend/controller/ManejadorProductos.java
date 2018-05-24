package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.ErrorAplicacion;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Producto;

/**
 * ManejadorProductos. Clase Controladora que brinda servicios para los
 * diferentes acciones a realizar con los objetos productos, los métodos de esta
 * clase son STATIC.
 */
public class ManejadorProductos {

    private static EntityManager getEM() {
        return Persistence.createEntityManagerFactory("ResbarBackendPU").createEntityManager();
    }

    /**
     * Método: Insertar(p: producto) Agrega el objeto “producto” a la base de
     * datos.
     */
    public static void Insertar(Producto entity) {
        if (entity.idProducto <= 0 || entity.precio.doubleValue() <= 0) {
            throw new ErrorAplicacion("ManejadorProductos.Insertar(:producto)$El ID y el precio deben ser mayor a cero");
        }
        if (entity.nombre.isEmpty()) {
            throw new ErrorAplicacion("ManejadorProductos.Insertar(:producto)$El nombre del producto no puede estar vacío");
        }
        if (entity.area != 'B' && entity.area != 'C') {
            throw new ErrorAplicacion("ManejadorProductos.Insertar(:producto)$El area del producto solamente puede ser del tipo C o B");
        }

        EntityManager eml = getEM();
        EntityTransaction et = eml.getTransaction();
        try {
            if (!et.isActive()) {
                et.begin();
            }
            eml.persist(entity);
            et.commit();
        } catch (Exception ex) {
            if (et.isActive()) {
                et.rollback();
            }
            throw new ErrorAplicacion("ManejadorProductos.Insertar(:producto)$Algo fallo intentando insertar un nuevo producto");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    /**
     * Método: Eliminar(p: producto) Elimina el objeto “producto” de la base de
     * datos.
     */
    public static void Eliminar(Producto entity) {
        if (entity.idProducto <= 0 || entity.precio.doubleValue() <= 0) {
            throw new ErrorAplicacion("ManejadorProductos.Eliminar(:producto)$El ID y el precio deben ser mayor a cero");
        }
        if (entity.nombre.isEmpty()) {
            throw new ErrorAplicacion("ManejadorProductos.Eliminar(:producto)$El nombre del producto no puede estar vacío");
        }
        if (entity.area != 'B' && entity.area != 'C') {
            throw new ErrorAplicacion("ManejadorProductos.Eliminar(:producto)$El area del producto solamente puede ser del tipo C o B");
        }

        EntityManager eml = getEM();
        EntityTransaction trans = eml.getTransaction();
        try {
            if (!trans.isActive()) {
                trans.begin();
            }
            eml.remove(eml.merge(entity));
            trans.commit();
        } catch (Exception ex) {
            if (trans.isActive()) {
                trans.rollback();
            }
            throw new ErrorAplicacion("ManejadorProductos.Eliminar(:producto)$Algo fallo intentando eliminar un producto");

        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    /**
     * Método: Actualizar(p: producto) Si el objeto “producto” se desea
     * modificar este actualizara en la base de datos cuando este ya este
     * modificado, no se modificara el ID del producto solo sus otros campos.
     */
    public static void Actualizar(Producto entity) {

        if (entity.idProducto <= 0 || entity.precio.doubleValue() <= 0) {
            throw new ErrorAplicacion("ManejadorProductos.Actualizar(:producto)$El ID y el precio deben ser mayor a cero");
        }
        if (entity.nombre.isEmpty()) {
            throw new ErrorAplicacion("ManejadorProductos.Actualizar(:producto)$El nombre del producto no puede estar vacío");
        }
        if (entity.area != 'B' && entity.area != 'C') {
            throw new ErrorAplicacion("ManejadorProductos.Actualizar(:producto)$El area del producto solamente puede ser del tipo C o B");
        }
        EntityManager eml = getEM();
        EntityTransaction et = eml.getTransaction();
        try {
            if (!et.isActive()) {
                et.begin();
            }
            eml.merge(entity);
            et.commit();
        } catch (Exception ex) {
            if (et.isActive()) {
                et.rollback();
            }
            throw new ErrorAplicacion("ManejadorProductos.Actualizar(:producto)$Algo fallo intentando actualizar un producto");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    /**
     * Método: Obtener(:integer): producto Realiza una petición a la base de
     * datos y devuelve un objeto producto que cuyo IDProducto coincide con el
     * valor del parámetro.
     */
    public static Producto Obtener(Integer id) {
        if (id <= 0) {
            throw new ErrorAplicacion("ManejadorProductos.Obtener(:int)$El ID debe ser mayor a cero");
        }

        EntityManager eml = getEM();
        try {
            return eml.find(Producto.class, id);
        } catch (Exception e) {
            throw new ErrorAplicacion("ManejadorProductos.Obtener(:int)$Algo fallo intentando obtener un producto con id: " + id);
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    /**
     * Método: ObtenerId(): integer Obtiene el identificador de producto, va la
     * base de datos a obtener el ultimo ID de producto y le suma uno a dicho
     * valor.
     */
    public static int ObtenerID() {
        EntityManager eml = getEM();
        try {
            CriteriaQuery cq = eml.getCriteriaBuilder().createQuery(Integer.class);
            Root producto = cq.from(Producto.class);
            cq.select(eml.getCriteriaBuilder().max(producto.get("idProducto")));
            Query q = eml.createQuery(cq);
            return ((int) q.getSingleResult()) + 1;
        } catch (Exception ex) {
            throw new ErrorAplicacion("ManejadorProductos.ObtenerID()$Algo fallo intentando obtener el ID del ultimo producto creado");

        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    /**
     * Método: ObtenerxCategoria(IdCat:integer): Producto[] Realiza una petición
     * a la base de datos y devuelve una colección de objetos productos que se
     * corresponden con el Identificador de categoría que se pasó como
     * parámetro.
     */
    public static List<Producto> ObtenerxCategoria(int id) {
        if (id <= 0) {
            throw new ErrorAplicacion("ManejadorProductos.ObtenerxCategoria(:int)$El ID debe ser mayor a cero");
        }

        EntityManager eml = getEM();
        try {
            Query query = eml.createNamedQuery("Producto.findByCategoria");
            query.setParameter("categoria", id);
            return query.getResultList();
        } catch (Exception ex) {
            throw new ErrorAplicacion("ManejadorProductos.ObtenerxCategoria(:int)$Algo fallo intentando obtener producto con ID categoria: " + id);

        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    /**
     * Método: Buscar(:String): Producto[] Toma la cadena pasada como parametro
     * como criterio de búsqueda, para ir a la base de datos y buscar todos los
     * productos cuyo Id o nombre coincida con el criterio de búsqueda, luego
     * devuelve la colección de productos, sin devolver productos duplicados.
     */
    public static List<Producto> Buscar(String charSequence) {
        EntityManager eml = getEM();
        try {
            Query query = eml.createNamedQuery("Producto.findByCharsequence");
            query.setParameter("nombre", charSequence);
            return query.getResultList();
        } catch (Exception e) {
            throw new ErrorAplicacion("ManejadorProductos.Buscar(:String)$Algo fallo intentando obtener producto");

        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

}
