/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Categoria;

/**
 *
 * @author zaldivar
 */
public class Controller implements Serializable {

    private static Class<?> ENTITY;

    protected static void init(Class<?> entity) {
        Controller.ENTITY = entity;
    }

    protected static EntityManager getEM() {
        return DBUtil.getEmFactory("ResbarBackendPU").createEntityManager();
    }

    protected static void insertar(Object entity) {
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
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }

        }
    }

    protected static Object eliminar(Object entity) {
        EntityManager eml = getEM();
        EntityTransaction trans = eml.getTransaction();
        try {
            if (!trans.isActive()) {
                trans.begin();
            }
            eml.remove(eml.merge(entity));

            trans.commit();
            return entity;
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            return null;
        } finally {

            eml.close();
        }
    }

    protected static boolean actualizar(Object entityObject) {
        EntityManager eml = getEM();
        EntityTransaction et = eml.getTransaction();
        try {
            if (!et.isActive()) {
                et.begin();
            }
            eml.merge(entityObject);
            et.commit();
            return true;
        } catch (Exception ex) {
            if (et.isActive()) {
                et.rollback();
            }
            return false;
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }

        }
    }

    protected static List<Object> findEntities() {
        return findEntities(true, -1, -1);
    }

    protected static List<Object> findEntities(int maxResults, int firstResult) {
        return findEntities(false, maxResults, firstResult);
    }

    private static List<Object> findEntities(boolean all, int maxResults, int firstResult) {
        EntityManager eml = getEM();
        try {
            CriteriaQuery cq = eml.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ENTITY));
            Query q = eml.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    protected static Object obtener(Integer id) {
        EntityManager eml = getEM();
        try {
            return eml.find(ENTITY, id);
        } catch (Exception e) {
            System.err.print("El error es :"+e);
            return null;
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }

        }
    }

    protected static int getCount() {
        EntityManager eml = getEM();
        try {
            CriteriaQuery cq = eml.getCriteriaBuilder().createQuery();
            Root<Categoria> rt = cq.from(ENTITY);
            cq.select(eml.getCriteriaBuilder().count(rt));
            Query q = eml.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }
}
