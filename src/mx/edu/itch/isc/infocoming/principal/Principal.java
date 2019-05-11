package mx.edu.itch.isc.infocoming.principal;

import mx.edu.itch.isc.infocoming.interfacesgraficas.DMRegistrarPago;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VBajaAlumno;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VGestionPagos;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VValidarUsuario;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VVisualizarAlumnos;


public class Principal {
    
    public static void main(String[] args) {
        //new VValidarUsuario().setVisible(true);
        //new VGestionPagos().setVisible(true);
        new DMRegistrarPago().setVisible(true);
        
        System.exit(0);
    }

}
