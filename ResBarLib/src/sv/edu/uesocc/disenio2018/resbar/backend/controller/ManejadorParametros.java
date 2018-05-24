package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions.ErrorAplicacion;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Parametro;

/**
 * ManejadorParametros. Clase Controladora que brinda servicios para los
 * diferentes acciones a realizar con los parámetros, los métodos de esta clase
 * son STATIC.
 */
public class ManejadorParametros {

    private static EntityManager getEM() {
        return Persistence.createEntityManagerFactory("ResbarBackendPU").createEntityManager();
    }

    /**
     * Método: Actualizar(:parametro) Actualiza el valor del parámetro en la
     * base de datos, no se puede actualizar ni el ID, ni el nombre, solo se
     * puede modificar el campo valor.
     */
    public static void Actualizar(Parametro entityObject) {
        if (entityObject.nombre.isEmpty() || entityObject.valor.isEmpty()) {
            throw new ErrorAplicacion("ManejadorParametros.Actualizar(:parametro)$El nombre y el valor del parametro no pueden estar vacíos");
        }

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
            throw new ErrorAplicacion("ManejadorParametros.Actualizar(:parametro)$Fallo actualizar el parámetro");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }

        }
    }

    /**
     * Método: Obtener(idParametro:int):parametro Toma el ID parametro y busca
     * en la base de datos una tupla que coincida con dicho ID, luego devuelve
     * un objeto parametro construido acorde a la tupla.
     */
    public static Parametro Obtener(Integer id) {
        if (id <= 0) {
            throw new ErrorAplicacion("ManejadorParametros.Obtener(:int)$El ID debe ser mayor a cero");
        }

        EntityManager eml = getEM();
        try {
            return eml.find(Parametro.class, id);
        } catch (Exception ex) {
            throw new ErrorAplicacion("ManejadorParametros.Obtener(:int)$Fallo el obtener el parámetro");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    /**
     * Método: Obtener(): Parametro[] Va a la base de datos y obtiene todos los
     * parametros que están en dicha tabla, devolviendo una colección de objetos
     * parametros.
     */
    public static List<Parametro> Obtener() {
        EntityManager eml = getEM();
        try {
            Query query = eml.createNamedQuery("Parametro.findAll");
            return query.getResultList();
        } catch (Exception ex) {
            throw new ErrorAplicacion("ManejadorParametros.Obtener()$Fallo obtener la lista de parámetros");
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

}
