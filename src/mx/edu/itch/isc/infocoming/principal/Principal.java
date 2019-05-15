package mx.edu.itch.isc.infocoming.principal;

import mx.edu.itch.isc.infocoming.interfacesgraficas.DMConfirmarBaja;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMCrearGrupo;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMRegistrarPago;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMRegistrarPersonal;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMRegistroES;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMReinscribirAlumno;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalAdministrador;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalCoordinadorAcademico;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalRecepcionista;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VGestionPersonal;
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
//        new VGestionPersonal().setVisible(true);
        new PanelPrincipalAdministrador().setVisible(true);
        new PanelPrincipalCoordinadorAcademico().setVisible(true);
        new PanelPrincipalRecepcionista().setVisible(true);
//        new DMReinscribirAlumno().setVisible(true);
        
        
    }

}
