package mx.edu.itch.isc.infocoming.principal;

<<<<<<< HEAD
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMConfirmarBaja;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMCrearGrupo;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelDirector;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VBajaAlumno;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VVisualizarAlumnos;
=======
import mx.edu.itch.isc.infocoming.interfacesgraficas.*;
import mx.edu.itch.isc.infocoming.manejadores.ManejadorValidarUsuario;
>>>>>>> 8d6dc27e30e93c3f7b9a44073b69ad6712945535


public class Principal {
    
    public static void main(String[] args) {
<<<<<<< HEAD
        //new VValidarUsuario().setVisible(true);
        //new VGestionPagos().setVisible(true);
        //new DMRegistrarPago().setVisible(true);
        //new DMRegistroES().setVisible(true);
        //new VGestionUsuario().setVisible(true);
        //new VVisualizarAlumnos().setVisible(true);
        //new DMCrearGrupo().setVisible(true);
//        new VBajaAlumno().setVisible(true);
        new PanelDirector().setVisible(true);
        
//        new DMConfirmarBaja().setVisible(true);
        
//        System.exit(0);
=======
        
        ManejadorValidarUsuario mvu = new ManejadorValidarUsuario(new VValidarUsuario());
        
>>>>>>> 8d6dc27e30e93c3f7b9a44073b69ad6712945535
    }

}
