package mx.edu.itch.isc.infocoming.principal;

import mx.edu.itch.isc.infocoming.interfacesgraficas.*;
import mx.edu.itch.isc.infocoming.manejadores.ManejadorValidarUsuario;


public class Principal {
    
    public static void main(String[] args) {
        
        ManejadorValidarUsuario mvu = new ManejadorValidarUsuario(new VValidarUsuario());
        
    }

}
