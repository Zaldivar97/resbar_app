/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author irvin
 */
public class ManejadorCategorias {

    private static EntityManager getEM() {
        return Persistence.createEntityManagerFactory("ResbarBackendPU").createEntityManager();
    }

    public static void insertar(Categoria categoria) {

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
            throw new ErrorApplication("Fallo al crear la categoria --> $ManejadorCategorias.insertar()");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }

        }
    }

    public static void eliminar(Categoria categoria) {
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

    public static void actualizar(Categoria categoria) {
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
            throw new ErrorApplication("Fallo actualizar la categoria --> $ManejadorCategorias.actualizar()");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }

        }
    }

    public static int obtenerID() {
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
    
    public static Categoria obtener(int id){
        EntityManager eml = getEM();
        try {
            return eml.find(Categoria.class, id);
            
        } catch (Exception e) {
            throw new ErrorApplication("Fallo obtener categoría con id: "+id+" --> $ManejadorCategorias.obtenerID()");
        }
    }

    public static List<Categoria> obtener(boolean withDetails) {
        EntityManager eml = getEM();
        if (withDetails) {
            try {
                Query query = eml.createNamedQuery("Categoria.findAll");
                return query.getResultList();
            } catch (Exception ex) {
                throw new ErrorApplication("Fallo obtener lista de categorias --> $ManejadorCategorias.obtener()");
            } finally {
                if (eml.isOpen()) {
                    eml.close();
                }
            }
        } else{
            try {
                Query query = eml.createNamedQuery("Categoria.findAllWithoutDetails");
                return query.getResultList();
            } catch (Exception ex) {
                throw new ErrorApplication("Fallo obtener lista de categorias sin detalles --> $ManejadorCategorias.obtener()");
            } finally {
                if (eml.isOpen()) {
                    eml.close();
                }
            }
        } 
    }

}
