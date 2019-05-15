package mx.edu.itch.isc.infocoming.manejadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBDEquipo;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalAdministrador;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VVisualizarAlumnos;

public class ManejadorVisualizarAlumnos implements ActionListener, ListSelectionListener{
    private VVisualizarAlumnos vv;
    private InterfazBD intBD;
    
    public ManejadorVisualizarAlumnos(InterfazBD inter, VVisualizarAlumnos v) throws SQLException {
        this.vv = v;
        this.intBD=inter;

        //Agregar actionListener a los botones de la vista
        this.consultarAlumnos();
        vv.setVisible(true);
    }

    private void consultarAlumnos() throws SQLException{
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno from Alumno");
        
        vv.tabla.setModel(new DefaultTableModel(datos,new Object[]{"Matricula","Nombre"}));
        vv.tabla.getSelectionModel().addListSelectionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
