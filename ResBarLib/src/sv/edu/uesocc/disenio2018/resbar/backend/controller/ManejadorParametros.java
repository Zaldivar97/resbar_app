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
import static sv.edu.uesocc.disenio2018.resbar.backend.controller.ManejadorProductos.getEM;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.ErrorApplication;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Parametro;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Producto;

/**
 *
 * @author zaldivar
 */
public class ManejadorParametros {

    protected static EntityManager getEM() {
        return Persistence.createEntityManagerFactory("ResbarBackendPU").createEntityManager();
    }

    protected static void actualizar(Parametro entityObject) {
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
            throw new ErrorApplication("Fallo actualizar el parámetro --> $ManejadorPaŕametros.actualizar() --> " + ex.getMessage());
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }

        }
    }

    protected static Object obtener(Integer id) {
        EntityManager eml = getEM();
        try {
            return eml.find(Producto.class, id);
        } catch (Exception e) {
            return null;
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }

        }
    }

    protected static List<Parametro> obtener() {
        EntityManager eml = getEM();
        try {
            Query query = eml.createNamedQuery("Parametro.findAll");
            return query.getResultList();
        } catch (Exception ex) {
            throw new ErrorApplication("Fallo obtener lista de Parámetros --> $ManejadorParámetros --> " + ex.getMessage());
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

}
