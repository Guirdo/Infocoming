package mx.edu.itch.isc.infocoming.principal;

import mx.edu.itch.isc.infocoming.interfacesgraficas.DMConfirmarBaja;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMCrearGrupo;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VVisualizarAlumnos;


public class Principal {
    
    public static void main(String[] args) {
        //new VValidarUsuario().setVisible(true);
        //new VGestionPagos().setVisible(true);
        //new DMRegistrarPago().setVisible(true);
        //new DMRegistroES().setVisible(true);
        //new VGestionUsuario().setVisible(true);
        //new VVisualizarAlumnos().setVisible(true);
        //new DMCrearGrupo().setVisible(true);
        
        new DMConfirmarBaja().setVisible(true);
        
        System.exit(0);
    }

}
