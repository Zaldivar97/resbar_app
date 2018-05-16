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
import sv.edu.uesocc.disenio2018.resbar.backend.Categoria;

/**
 *
 * @author zaldivar
 */
public abstract class Controller implements Serializable {

    private static Class<?> ENTITY;
    
    private static EntityManager em;
    
    public Controller(Class<?> entity,EntityManager em) {
        Controller.em=em;
        Controller.ENTITY=entity;
    }

    public static void insertar(Object entity) {

        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();

            em.persist(entity);

            em.getTransaction().commit();
        } catch (Exception ex) {

        } finally {
            if (em != null) {
                em.close();
            }
            if (et != null) {
                et.rollback();
            }
        }
    }

    public static Object eliminar(Object entity) {
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {

            em.remove(em.merge(entity));

            trans.commit();
            return entity;
        } catch (Exception e) {
            return null;
        } finally {
            trans.rollback();

            em.close();
        }
    }

    public static boolean actualizar(Object entityObject) {
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.merge(entityObject);
            et.commit();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
            if (et != null) {
                et.rollback();
            }
        }
    }

    public static List<Object> findEntities() {
        return findEntities(true, -1, -1);
    }

    public static List<Object> findEntities(int maxResults, int firstResult) {
        return findEntities(false, maxResults, firstResult);
    }

    private static List<Object> findEntities(boolean all, int maxResults, int firstResult) {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ENTITY));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Object obtener(Integer id) {
        try {
            return em.find(ENTITY, id);
        } finally {
            em.close();
        }
    }

    public static int getCount() {
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categoria> rt = cq.from(ENTITY);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
