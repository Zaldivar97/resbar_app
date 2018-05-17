/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import sv.edu.uesocc.disenio2018.resbar.backend.Parametro;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.NonexistentEntityException;

/**
 *
 * @author zaldivar
 */
public class ParametroJpaController implements Serializable {

    public ParametroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Parametro> Obtener() {
        Query q = getEntityManager().createNamedQuery("Parametro.findAll");
        q.setParameter("estado", true);
        return q.getResultList();
    }

    public List<Parametro> ObtenerPorId(int parametro) {
        Query q = getEntityManager().createNamedQuery("Parametro.findByIdParametro");
        q.setParameter("parametro", parametro);
        return q.getResultList();
    }
     public void actualizar(Parametro parametro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            parametro = em.merge(parametro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = parametro.getIdParametro();
                if (ObtenerPorId(id) == null) {
                    throw new NonexistentEntityException("The parametro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
}
