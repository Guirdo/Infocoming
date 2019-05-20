/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itch.isc.infocoming.manejadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.beans.binding.Bindings.select;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMInscribirAlumno;

/**
 *
 * @author diann
 */
public class ManejadorInscribirAlumno implements ActionListener, WindowListener, ListSelectionListener {

    private InterfazBD intBD;
    private DMInscribirAlumno dm;
    String nombre, apellidoP, apellidoM, domicilio, tel, horario;
    String horarioselect;
    private int grupo;

    public ManejadorInscribirAlumno(InterfazBD intBD, DMInscribirAlumno d) throws SQLException {
        this.intBD = intBD;
        this.dm = d;

        dm.btn1.addActionListener(this);
        dm.btn2.addActionListener(this);
        dm.tabla.getSelectionModel().addListSelectionListener(this);

        this.consultaHorarios();
        dm.btn1.setEnabled(false);
        dm.setVisible(true);
    }

    private void consultaHorarios() throws SQLException {
        Object[][] datos = intBD.consultar("select idGrupo,horario, tipocurso from grupo, curso where curso=idcurso");
        dm.tabla.setModel(new DefaultTableModel(datos, new Object[]{"NumGrupo", "Horario", "Curso"}));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dm.btn1) {
            try {
                this.inscribir();
            } catch (SQLException ex) {
                Logger.getLogger(ManejadorInscribirAlumno.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void inscribir() throws SQLException {
        nombre = dm.nombres.getText();
        apellidoP = dm.apeP.getText();
        apellidoM = dm.apeM.getText();
        domicilio = dm.dom.getText();
        tel = dm.tel.getText();
        intBD.procedimientoInsertar("{call insertarAlumno(?, ?,?,?,?,?)}",
                nombre, apellidoP, apellidoM, domicilio, tel, grupo);
        
        dm.dispose();
    }

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {

    }

    @Override
    public void windowClosed(WindowEvent we) {

    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == dm.tabla.getSelectionModel()) {
            //De esta forma obtengo el horario seleccionado
            grupo = (int) dm.tabla.getValueAt(dm.tabla.getSelectedRow(), 0);
            horarioselect = (String) dm.tabla.getValueAt(dm.tabla.getSelectedRow(), 1);
            dm.btn1.setEnabled(true);
            try {
                this.consultarGrupo(horarioselect);
            } catch (SQLException ex) {
                Logger.getLogger(ManejadorInscribirAlumno.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void consultarGrupo(String horarioselect) throws SQLException {
        intBD.consultar("select idGrupo from grupo where horario='" + horarioselect + "'");

    }

}
