/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions;

/**
 *
 * @author danm
 */
public class ErrorApplication extends RuntimeException{
    public ErrorApplication(String s){
        super(s);
    }
}
