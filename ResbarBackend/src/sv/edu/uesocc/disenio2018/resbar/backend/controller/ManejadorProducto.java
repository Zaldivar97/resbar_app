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
import sv.edu.uesocc.disenio2018.resbar.backend.Producto;

/**
 *
 * @author zaldivar
 */
public class ManejadorProducto extends Controller{

    private final EntityManager em;
    
    public ManejadorProducto(EntityManager em) {
        super(Producto.class, em);
        this.em=em;
    }

    public List<Producto> obtenerxCategoria(int id){
        try {
        Query query = em.createNamedQuery("Producto.findByCategoria");
        query.setParameter("categoria", id);
        return query.getResultList();
        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        }finally{
            em.close();
        }
    }
    
    public List<Producto> buscar(String charSequence){
        try {
            Query query = em.createNamedQuery("Producto.findByCharsequence");
            query.setParameter("nombre", charSequence);
            return query.getResultList();
        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        }
        finally{
            em.close();
        }
    }
    
    public int obtenerID(){
        return getCount() + 1;
    }
    
}
