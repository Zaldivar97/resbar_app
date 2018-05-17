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
   
    
    public static EntityManagerFactory getEmFactory(String PUName){
        return Persistence.createEntityManagerFactory(PUName);
    }
    
}
