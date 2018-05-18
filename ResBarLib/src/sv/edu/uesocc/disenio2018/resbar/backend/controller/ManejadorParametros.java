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
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.ErrorApplication;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Parametro;

/**
 *
 * @author zaldivar
 */
public class ManejadorParametros {

    private static EntityManager getEM() {
        return Persistence.createEntityManagerFactory("ResbarBackendPU").createEntityManager();
    }

    public static void actualizar(Parametro entityObject) {
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
            throw new ErrorApplication("Fallo actualizar el parámetro --> $ManejadorPaŕametros.actualizar()");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }

        }
    }

    //Revisar el return
    public static Parametro obtener(Integer id) {
        EntityManager eml = getEM();
        try {
            return eml.find(Parametro.class, id);
        } catch (Exception e) {
            throw new ErrorApplication("Fallo obtener el parámetro --> $ManejadorPaŕametros.obtener()");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }

        }
    }

    public static List<Parametro> obtener() {
        EntityManager eml = getEM();
        try {
            Query query = eml.createNamedQuery("Parametro.findAll");
            return query.getResultList();
        } catch (Exception ex) {
            throw new ErrorApplication("Fallo obtener la lista de parámetros --> $ManejadorParámetros.obtener()");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

}
