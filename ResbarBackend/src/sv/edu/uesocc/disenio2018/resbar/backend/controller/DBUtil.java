/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.disenio2018.resbar.backend.controller;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author zaldivar
 */
public class DBUtil {
    private static final EntityManagerFactory ENF = Persistence.createEntityManagerFactory("ResbarBackendPU");
    
    public static EntityManagerFactory getEmFactory(){
        return ENF;
    }
    
}
