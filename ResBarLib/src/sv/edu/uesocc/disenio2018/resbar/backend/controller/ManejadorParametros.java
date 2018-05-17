/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author zaldivar
 */
public class ManejadorParametros {

    protected static EntityManager getEM() {
        return Persistence.createEntityManagerFactory("ResbarBackendPU").createEntityManager();
    }
    
    

}
