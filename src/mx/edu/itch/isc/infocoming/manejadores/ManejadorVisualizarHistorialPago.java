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
import java.util.ArrayList;
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
    private int[] alumnoS;
    
    private Pantalla vistaAnterior;
    private int matricula, folio;
    
    public ManejadorVisualizarHistorialPago(InterfazBD inter, VGestionPagos vg, Pantalla ant) throws SQLException {
        this.vgp = vg;
        this.intBD=inter;
        this.vistaAnterior = ant;
        alumnoS= new int[2];
        vgp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        vgp.tfBuscar.addKeyListener(this);
        vgp.tabla.getSelectionModel().addListSelectionListener(this);//datos de abajo
        vgp.addWindowListener(this);
        this.consultarPagos();
        vgp.setVisible(true);
    }
    
    private void consultarPagos() throws SQLException{
        Object[][] datos = intBD.consultar("select idAlumno, nombreAlumno, fecha, conceptopago, idPago from Pago,Alumno, Concepto where idAlumno=idalum and idconcepto=conceptoid");
        vgp.tabla.setModel(new DefaultTableModel(datos,new Object[]{"Matrícula","Nombre","Fecha pago","Concepto pago","Folio"}));        
    }
    private void buscarAlumnoPorMatricula() throws SQLException{
        Object[][] datos = intBD.consultar("select idAlumno, nombreAlumno, fecha, conceptopago, idpago from Pago,Alumno, Concepto where idAlumno="+vgp.tfBuscar.getText()+" and idAlumno=idalum and idconcepto=conceptoid");
        vgp.tabla.setModel(new DefaultTableModel(datos,new Object[]{"Matricula", "Nombre", "Fecha pago", "Concepto pago", "Folio"}));
    }
    private void buscarAlumnoPorApellido() throws SQLException {
        Object[][] datos = intBD.consultar("select idAlumno, nombreAlumno, fecha, conceptopago, idpago from Pago,Alumno, Concepto where apellidoPaternoAlumno= '"+vgp.tfBuscar.getText()+"' and idAlumno=idalum and idconcepto=conceptoid");
        vgp.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre","Fecha pago", "Concepto pago", "Folio"}));
    }
    
    private void consultarAlumnoVentana(int[] dat) throws SQLException {
        Object[][] datos = intBD.consultar("select nombreAlumno, apellidoPaternoAlumno, apellidoMaternoAlumno, idpago, cantidad, conceptopago, horario from pago, alumno, concepto, grupo where idAlumno=idalum and idAlumno="+dat[0]+" and idconcepto=conceptoid and idGrupo=grupid and idpago="+dat[1]);
        
        vgp.lblNombre.setText((String) datos[0][0]);
        vgp.lblApellidoP.setText((String) datos[0][1]);
        vgp.lblApellidoM.setText((String) datos[0][2]);
        vgp.lblFolio.setText((int) datos[0][3]+"");
        vgp.lblCantidad.setText((Double) datos[0][4]+"");
        vgp.lblConcepto.setText((String) datos[0][5]);
        vgp.lblHorario.setText((String) datos[0][6]);
        
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            //Si el textfield no esta vacio entonces..
            if (!vgp.tfBuscar.getText().isEmpty()) {
                String busqueda = vgp.tfBuscar.getText();

                try {
                    if (busqueda.matches("[A-Za-z]+")) {
                        this.buscarAlumnoPorApellido();
                    } else if (busqueda.matches("[0-9]+")) {
                        this.buscarAlumnoPorMatricula();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {
        vgp.dispose();
        this.vistaAnterior.setVisible(true);
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
        if (e.getSource() == vgp.tabla.getSelectionModel()) {
            matricula= ((int) vgp.tabla.getValueAt(vgp.tabla.getSelectedRow(), 0));//<-- Este ultimo numero corresponde a la col de la tabla
            folio= ((int) vgp.tabla.getValueAt(vgp.tabla.getSelectedRow(),4));
            alumnoS[0]=matricula;
            alumnoS[1]=folio;
            try {
                this.consultarAlumnoVentana(alumnoS);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
