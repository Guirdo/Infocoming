package mx.edu.itch.isc.infocoming.manejadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMInscribirAlumno;

public class ManejadorReinscribirAlumno implements ActionListener{
    
    
    private InterfazBD intBD;

    public ManejadorReinscribirAlumno(InterfazBD inter, DMInscribirAlumno dmIA) {
        //this.dmIA = dmIA;
        this.intBD=inter;
        
        //ActionListerner de los botones del dm
        
        dmIA.setVisible(true);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

}
