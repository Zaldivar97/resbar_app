package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.ErrorAplicacion;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Categoria;

/**
 * ManejadorCategorias. Clase encargada de los servicios para las categorías,
 * los métodos de esta clase también serán STATIC. Propiedades a comentar:
 * Obtener() tendrá un valor verdadero que cargaran los subproductos, y si su
 * valor es falso no se cargaran los subproductos.
 */
public class ManejadorCategorias {

    private static EntityManager getEM() {
        return Persistence.createEntityManagerFactory("ResbarBackendPU").createEntityManager();
    }

    /**
     * Método: Obtener(:boolean): categoria[] Realiza una petición a la base de
     * datos y devuelve una colección de categorías, si el valor del parametro
     * es TRUE cargara todos los productos que están en esa categoría, si el
     * valor del parametro es FALSE la propiedad “productos” de cada categoría
     * será NULL.
     */
    public static List<Categoria> Obtener(boolean withDetails) {
        EntityManager eml = getEM();
        if (withDetails) {
            try {
                Query query = eml.createNamedQuery("Categoria.findAll");
                return query.getResultList();
            } catch (Exception ex) {
                throw new ErrorAplicacion("ManejadorCategorias.Obtener(:boolean)$Fallo obtener lista de categorias");

            } finally {
                if (eml.isOpen()) {
                    eml.close();
                }
            }
        } else {
            try {
                Query query = eml.createNamedQuery("Categoria.findAll");
                return query.getResultList();
            } catch (Exception ex) {
                throw new ErrorAplicacion("ManejadorCategorias.Obtener(:boolean)$Fallo obtener lista de categorias sin detalles");

            } finally {
                if (eml.isOpen()) {
                    eml.close();
                }
            }
        }
    }

    /**
     * Método: Actualizar(c: categoria) Si se desea modificar el objeto
     * “categoria” este actualizara en la base de datos cuando este ya este
     * modificado, no se modificara el IDCategoria.
     */
    public static void Actualizar(Categoria categoria) {
        if (!categoria.nombre.isEmpty()) {
            EntityManager eml = getEM();
            EntityTransaction et = eml.getTransaction();
            try {
                if (!et.isActive()) {
                    et.begin();
                }
                eml.merge(categoria);
                et.commit();
            } catch (Exception ex) {
                if (et.isActive()) {
                    et.rollback();
                }
                throw new ErrorAplicacion("ManejadorCategorias.Actualizar(:categoria)$Fallo actualizar la categoria");

            } finally {
                if (eml.isOpen()) {
                    eml.close();
                }

            }
        } else {
            throw new ErrorAplicacion("ManejadorCategorias.Actualizar(:categoria)$La categoria debe tener un nombre");
        }
    }

    /**
     * Método: Insertar(c: categoria) Agrega el objeto “categoria” a la base de
     * datos.
     */
    public static void Insertar(Categoria categoria) {
        if (!categoria.nombre.isEmpty()) {
            EntityManager eml = getEM();
            EntityTransaction et = eml.getTransaction();
            try {
                if (!et.isActive()) {
                    et.begin();
                }
                eml.persist(categoria);
                et.commit();
            } catch (Exception ex) {
                if (et.isActive()) {
                    et.rollback();
                }
                throw new ErrorAplicacion("ManejadorCategorias.Insertar(:categoria)$Fallo al crear la categoria");

            } finally {
                if (eml.isOpen()) {
                    eml.close();
                }

            }
        } else {
            throw new ErrorAplicacion("ManejadorCategorias.Insertar(:categoria)$La categoria debe tener un nombre");
        }

    }

    /**
     * Método: Eliminar(c: categoria) Elimina el objeto “categoria” de la base
     * de datos.
     */
    public static void Eliminar(Categoria categoria) {
        EntityManager eml = getEM();
        EntityTransaction trans = eml.getTransaction();
        try {
            if (!trans.isActive()) {
                trans.begin();
            }
            eml.remove(eml.merge(categoria));
            trans.commit();
        } catch (Exception ex) {
            if (trans.isActive()) {
                trans.rollback();
            }
            throw new ErrorAplicacion("ManejadorCategorias.Eliminar(:categoria)$Fallo eliminar la categoria");
        } finally {
            eml.close();
        }
    }

    /**
     * Método: ObtenerId(): integer Obtiene el identificador de categoria, va la
     * base de datos a obtener el ultimo ID de categoria y le suma uno a dicho
     * valor.
     */
    public static int ObtenerId() {
        EntityManager eml = getEM();
        try {
            CriteriaQuery cq = eml.getCriteriaBuilder().createQuery(Integer.class);
            Root categoria = cq.from(Categoria.class);
            cq.select(eml.getCriteriaBuilder().max(categoria.get("idCategoria")));
            Query q = eml.createQuery(cq);
            return ((int) q.getSingleResult()) + 1;
        } catch (Exception ex) {
            throw new ErrorAplicacion("ManejadorCategorias.ObtenerId()$Fallo obtener ID para la nueva categoria");

        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

}
