package mx.edu.itch.isc.infocoming.manejadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalAdministrador;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalCoordinadorAcademico;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalDirector;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalRecepcionista;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VValidarUsuario;

public class ManejadorVisualizarAlumnos implements ActionListener{
    private PanelPrincipalAdministrador ppa;
    private PanelPrincipalDirector ppd;
    private PanelPrincipalRecepcionista ppr;
    private PanelPrincipalCoordinadorAcademico ppc;
    
    public ManejadorVisualizarAlumnos(PanelPrincipalAdministrador pp_a) {
        this.ppa = pp_a;

        //Agregar actionListener a los botones de la vista
        ppa.etiqueta2.addActionListener(this);

        ppa.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
