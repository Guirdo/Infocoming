/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itch.isc.infocoming.manejadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VBajaAlumno;

/**
 *
 * @author pacop
 */
public class ManejadorBajaAlumno implements ActionListener{
    private VBajaAlumno v;
    private InterfazBD intBD;

    public ManejadorBajaAlumno(InterfazBD inte,VBajaAlumno vv) throws SQLException {
        this.v = vv;
        this.intBD = inte;
        v.btn.addActionListener(this);
        this.consultarAlumnos();
        v.setVisible(true);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == v.btn){
            try {
                this.manejaEventoBajaAlumno();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    private void consultarAlumnos() throws SQLException{
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno,horario from Alumno,Grupo where grupID = idGrupo");
        
        v.tabla.setModel(new DefaultTableModel(datos,new Object[]{"Matricula","Nombre","Horario"}));
    }

    private void manejaEventoBajaAlumno() throws SQLException{
        
    }
    
}
