package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.ErrorApplication;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Parametro;

/**
 * @author zaldivar
 */
public class ManejadorParametros {

    private static EntityManager getEM() {
        return Persistence.createEntityManagerFactory("ResbarBackendPU").createEntityManager();
    }

    public static void Actualizar(Parametro entityObject) {
        EntityManager eml = getEM();
        EntityTransaction et = eml.getTransaction();
        try {
            if (!et.isActive()) {
                et.begin();
            }
            eml.merge(entityObject);
            et.commit();
        } catch (Exception ex) {
            if (et.isActive()) {
                et.rollback();
            }
            throw new ErrorApplication("ManejadorParametros.Actualizar(:parametro)$Fallo actualizar el parámetro");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }

        }
    }

    //Revisar el return
    public static Parametro Obtener(Integer id) {
        if (id > 0) {
            EntityManager eml = getEM();
            try {
                return eml.find(Parametro.class, id);
            } catch (Exception e) {
                throw new ErrorApplication("ManejadorParametros.Obtener(:int)$Fallo el obtener el parámetro");
            } finally {
                if (eml.isOpen()) {
                    eml.close();
                }

            }
        } else {
            throw new ErrorApplication("ManejadorParametros.Obtener(:int)$ID inválido");
        }
    }

    public static List<Parametro> Obtener() {
        EntityManager eml = getEM();
        try {
            Query query = eml.createNamedQuery("Parametro.findAll");
            return query.getResultList();
        } catch (Exception ex) {
            throw new ErrorApplication("ManejadorParametros.Obtener()$Fallo obtener la lista de parámetros");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

}
