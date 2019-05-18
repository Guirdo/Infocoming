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
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesgraficas.Pantalla;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VGestionPagos;

/**
 *
 * @author diann
 */
public class ManejadorVisualizarHistorialPago implements ActionListener, KeyListener, WindowListener, ListSelectionListener {
    private VGestionPagos vgp;
    private InterfazBD intBD;
    private int alumnoSeleccionado;
    private Pantalla vistaAnterior;
    
    public ManejadorVisualizarHistorialPago(InterfazBD inter, VGestionPagos vg, Pantalla ant) throws SQLException {
        this.vgp = vg;
        this.intBD=inter;
        this.vistaAnterior = ant;
        vgp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        vgp.tfBuscar.addKeyListener(this);
        vgp.tabla.getSelectionModel().addListSelectionListener(this);//datos de abajo
        vgp.addWindowListener(this);
        this.consultarPagos();
        vgp.setVisible(true);
    }
    
    private void consultarPagos() throws SQLException{
        Object[][] datos = intBD.consultar("select idAlumno, nombreAlumno, fecha, conceptopago from Pago,Alumno, Concepto where idAlumno=idalum and idconcepto=conceptoid");
        vgp.tabla.setModel(new DefaultTableModel(datos,new Object[]{"Matrícula","Nombre","Fecha pago","Concepto pago"}));        
    }
    private void buscarAlumnoPorMatricula() throws SQLException{
        Object[][] datos = intBD.consultar("select idAlumno, nombreAlumno, fecha, conceptopago from Pago,Alumno, Concepto where idAlumno="+vgp.tfBuscar.getText()+" and idconcepto=conceptoid");
        vgp.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre", "Apellido Paterno", "Apellido Materno"}));
    }
    private void buscarAlumnoPorApellido() throws SQLException {
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno, fecha, conceptopago from Pago,Alumno,Concepto where apellidoPaternoAlumno= '" + vgp.tfBuscar.getText() + "' and idconcepto=conceptoid");
        vgp.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre","Apellido Paterno", "Apellido Materno"}));
    }
    
    private void consultarAlumnoVentana(int matri) throws SQLException {
        Object[][] datos = intBD.consultar("select idAlumno, nombreAlumno, apellidoPaternoAlumno, apellidoMaternoAlumno, domicilioAlumno, telefonoAlumno,  horario, tipocurso from Alumno, Grupo, Curso where idAlumno="+matri+" and idgrupo=idgrupo");
        
        vv.lblMatricula.setText((int) datos[0][0]+"");
        vv.lblNombre.setText((String) datos[0][1]);
        vv.lblApellidoP.setText((String) datos[0][2]);
        vv.lblApellidoM.setText((String) datos[0][3]);
        vv.lblDomicilio.setText((String) datos[0][4]);
        vv.lblTelefono.setText((String) datos[0][5]);
        vv.lblCurso.setText((String) datos[0][6]);
        vv.lblHorario.setText((String) datos[0][7]);
        
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowOpened(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == vgp.tabla.getSelectionModel()) {
            //se obtiene matricula
            alumnoSeleccionado = (int) vgp.tabla.getValueAt(vgp.tabla.getSelectedRow(), 0);//<-- Este ultimo numero corresponde a la col de la tabla
            try {
                this.consultarAlumnoVentana(alumnoSeleccionado);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
