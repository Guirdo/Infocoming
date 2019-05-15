package mx.edu.itch.isc.infocoming.manejadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VReinscribirAlumno;

public class ManejadorReinscribirAlumno implements ActionListener{
    
    private VReinscribirAlumno v;
    private InterfazBD intBD;

    public ManejadorReinscribirAlumno(InterfazBD inter, VReinscribirAlumno vv) throws SQLException {
        this.v=vv;
        this.intBD=inter;
        
        //ActionListerner de los botones del dm
        v.btnReinscribir.addActionListener(this);
        
        this.consultarAlumnos();
        
        v.setVisible(true);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == v.btnReinscribir){
            try {
                this.manejaEventoReinscribirAlumno();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void consultarAlumnos() throws SQLException{
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno,horario from Alumno,Grupo where grupID = idGrupo");
        
        v.tabla.setModel(new DefaultTableModel(datos,new Object[]{"Matricula","Nombre","Horario"}));
    }

    private void manejaEventoReinscribirAlumno() throws SQLException {
        
    }
}
