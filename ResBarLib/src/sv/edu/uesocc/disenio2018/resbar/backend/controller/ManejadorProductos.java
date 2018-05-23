package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.ErrorApplication;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Producto;

/**
 * @author zaldivar
 */
public class ManejadorProductos {

    private static EntityManager getEM() {
        return Persistence.createEntityManagerFactory("ResbarBackendPU").createEntityManager();
    }

    public static void Insertar(Producto entity) {
        if (entity.idProducto <= 0 || entity.precio <= 0) {
            throw new ErrorApplication("El ID y el precio deben ser mayor a cero --> $ManejadorProducto.insertar()");
        }
        if (entity.nombre.isEmpty()) {
            throw new ErrorApplication("El nombre del producto no puede estar vacío --> $ManejadorProducto.insertar()");
        }
        if (entity.area != 'B' && entity.area != 'C') {
            throw new ErrorApplication("El area del producto solamente puede ser del tipo C o B --> $ManejadorProducto.insertar()");
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
            throw new ErrorApplication("Algo fallo intentando insertar un nuevo producto --> $ManejadorProducto.insertar()");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    public static void Eliminar(Producto entity) {
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
            throw new ErrorApplication("Algo fallo intentando eliminar un producto --> $ManejadorProducto.eliminar()");

        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    public static void Actualizar(Producto entity) {

        if (entity.idProducto <= 0 || entity.precio <= 0) {
            throw new ErrorApplication("El ID y el precio deben ser mayor a cero --> $ManejadorProducto.insertar()");
        }
        if (entity.nombre.isEmpty()) {
            throw new ErrorApplication("El nombre del producto no puede estar vacío --> $ManejadorProducto.insertar()");
        }
        if (entity.area != 'B' && entity.area != 'C') {
            throw new ErrorApplication("El area del producto solamente puede ser del tipo C o B --> $ManejadorProducto.insertar()");
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
            throw new ErrorApplication("Algo fallo intentando actualizar un producto --> $ManejadorProducto.actualizar()");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    public static Producto Obtener(Integer id) {
        EntityManager eml = getEM();
        try {
            return eml.find(Producto.class, id);
        } catch (Exception e) {
            throw new ErrorApplication("Algo fallo intentando obtener un producto con id: " + id + " --> $ManejadorProducto.obtener()");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    public static int ObtenerID() {
        EntityManager eml = getEM();
        try {
            CriteriaQuery cq = eml.getCriteriaBuilder().createQuery(Integer.class);
            Root producto = cq.from(Producto.class);
            cq.select(eml.getCriteriaBuilder().max(producto.get("idProducto")));
            Query q = eml.createQuery(cq);
            return ((int) q.getSingleResult()) + 1;
        } catch (Exception ex) {
            throw new ErrorApplication("Algo fallo intentando obtener el ID del ultimo producto creado --> $ManejadorProducto.obtenerID()");

        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    public static List<Producto> ObtenerxCategoria(int id) {
        EntityManager eml = getEM();//entitymanagerlocal
        try {
            Query query = eml.createNamedQuery("Producto.findByCategoria");
            query.setParameter("categoria", id);
            return query.getResultList();
        } catch (Exception ex) {
            throw new ErrorApplication("Algo fallo intentando obtener producto con ID categoria: " + id + " --> $ManejadorProducto.obtenerxCategoria()");

        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    public static List<Producto> buscar(String charSequence) {
        EntityManager eml = getEM();
        try {
            Query query = eml.createNamedQuery("Producto.findByCharsequence");
            query.setParameter("nombre", charSequence);
            return query.getResultList();
        } catch (Exception e) {
            throw new ErrorApplication("Algo fallo intentando obtener producto --> $ManejadorProducto.bucar()");

        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

}
