/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import sv.edu.uesocc.disenio2018.resbar.backend.entities.Producto;

/**
 *
 * @author zaldivar
 */
public class ManejadorProducto extends Controller {

    public static List<Producto> obtenerxCategoria(int id) {
        EntityManager eml = getEM();//entitymanagerlocal
        try {

            Query query = eml.createNamedQuery("Producto.findByCategoria");
            query.setParameter("categoria", id);
            return query.getResultList();
        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    public static List<Producto> buscar(String charSequence) {
        EntityManager eml = getEM();
        try {
            Query query = eml.createNamedQuery("Producto.findByCharsequence");
            query.setParameter("nombre", charSequence);
            return query.getResultList();
        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        } finally {
            if (eml.isOpen()) {
                eml.close();
            }
        }
    }

    public static Object Obtener(Integer id){
        init(Producto.class);
        return obtener(id);
    }

    
    public static int obtenerID() {
        init(Producto.class);
        return getCount() + 1;
    }

}
