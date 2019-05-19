/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itch.isc.infocoming.manejadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMExamen_CENNI;

/**
 *
 * @author diann
 */
public class ManejadorExamenCENNI implements ActionListener, KeyListener, WindowListener, ListSelectionListener{
    private InterfazBD intBD;
    private DMExamen_CENNI dm;

    
    public ManejadorExamenCENNI(InterfazBD intBD, DMExamen_CENNI d) throws SQLException{
        this.intBD = intBD;
        this.dm = d;
        
        dm.examenes.addActionListener(this);
        dm.btn.addActionListener(this);
        dm.tabla.getSelectionModel().addListSelectionListener(this);
        
        this.consultaCandidatos();
        dm.setVisible(true);
    }
    
    private void consultaCandidatos() throws SQLException {
        Object[][] datos = intBD.consultar("");
        dm.tabla.setModel(new DefaultTableModel(datos,new Object[]{"Matricula","Nombre", "Curso"}));
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
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
    public void valueChanged(ListSelectionEvent lse) {
    }

    
    
    
    
}
