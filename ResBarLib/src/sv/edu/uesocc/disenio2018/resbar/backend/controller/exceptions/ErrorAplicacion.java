/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.disenio2018.resbar.backend.controller.exceptions;

/**
 * Clase: ErrorAplicacion. Es una clase para el lanzamiento de excepciones
 * personalizadas hacia la capa superior de interfaz. Hereda de la clase
 * Exception. Solo posee un constructor el cual recibe el mensaje de error, el
 * mensaje de error usara el separador Dólar, para indicar primero el objeto y
 * método que desencadena la excepción, y luego el propio mensaje.
 */
public class ErrorAplicacion extends RuntimeException {

    public ErrorAplicacion(String mensaje) {
        super(mensaje);
    }
}
