package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.ErrorApplication;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Categoria;

/**
 * @author irvin
 */
public class ManejadorCategorias {

    private static EntityManager getEM() {
        return Persistence.createEntityManagerFactory("ResbarBackendPU").createEntityManager();
    }
   
    public static List<Categoria> Obtener(boolean withDetails) {
        EntityManager eml = getEM();
        if (withDetails) {
            try {
                Query query = eml.createNamedQuery("Categoria.findAll");
                return query.getResultList();
            } catch (Exception ex) {
                throw new ErrorApplication("Fallo obtener lista de categorias --> $ManejadorCategorias.Obtener()");
            } finally {
                if (eml.isOpen()) {
                    eml.close();
                }
            }
        } else {
            try {
                Query query = eml.createNamedQuery("Categoria.findAllWithoutDetails");
                return query.getResultList();
            } catch (Exception ex) {
                throw new ErrorApplication("Fallo obtener lista de categorias sin detalles --> $ManejadorCategorias.Obtener()");
            } finally {
                if (eml.isOpen()) {
                    eml.close();
                }
            }
        }
    }

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
                throw new ErrorApplication("Fallo actualizar la categoria --> $ManejadorCategorias.Actualizar()");
            } finally {
                if (eml.isOpen()) {
                    eml.close();
                }

            }
        } else {
            throw new ErrorApplication("La categoria debe tener un nombre --> $ManejadorCategorias.Actualizar()");
        }
    }

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
                throw new ErrorApplication("Fallo al crear la categoria --> $ManejadorCategorias.Insertar()");
            } finally {
                if (eml.isOpen()) {
                    eml.close();
                }

            }
        } else {
            throw new ErrorApplication("La categoria debe tener un nombre --> $ManejadorCategorias.Insertar()");
        }

    }

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
            throw new ErrorApplication("Fallo eliminar la categoria --> $ManejadorCategorias.eliminar()");
        } finally {
            eml.close();
        }
    }

    public static int ObtenerId() {
        EntityManager eml = getEM();
        try {
            CriteriaQuery cq = eml.getCriteriaBuilder().createQuery(Integer.class);
            Root categoria = cq.from(Categoria.class);
            cq.select(eml.getCriteriaBuilder().max(categoria.get("idCategoria")));
            Query q = eml.createQuery(cq);
            return ((int) q.getSingleResult()) + 1;
        } catch (Exception ex) {
            throw new ErrorApplication("Fallo obtener ID para la nueva categoria --> $ManejadorCategorias.obtenerID()");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

}
