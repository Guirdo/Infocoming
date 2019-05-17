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
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMRegistrarPersonal;
import mx.edu.itch.isc.infocoming.interfacesgraficas.Pantalla;
import sun.awt.WindowClosingListener;

public class ManejadorRegistrarEmpleado implements ActionListener, KeyListener, WindowListener, ListSelectionListener {

    private DMRegistrarPersonal dm;// es DMregistrar empleado 
    private InterfazBD iBD;
    private Pantalla VistaAnterior;

    private int empleadoSeleccionado;

    public ManejadorRegistrarEmpleado(InterfazBD ibd, DMRegistrarPersonal dm, Pantalla ant) throws SQLException { // DMregistrar Empleado 

        this.VistaAnterior = ant;
        this.dm = dm;
        this.iBD = ibd;
        //ActionListerner de los botones del dm
        this.dm.btnRegistrarEmpleado.addActionListener(this);
        dm.btnCancelar.addActionListener(this);
        this.dm.addWindowListener(this);

        //Esta linea sirve para consultar antes de entrar
        //this.consultarEmpleado();
        dm.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dm.btnRegistrarEmpleado) {
            try {
                this.manejaEventoRegistrarEmpleado();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == dm.btnCancelar) {
            dm.dispose();
        }
    }

    //private void  consultarEmpleado () throws SQLException{
    //Object[][] datos = iBD.consultar("select idPersonal,nombrepersonal,apellidoPaterno,apellidoMaterno,domicilio, telefono from Personal");
    //dm.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre"}));
    // }
    //private void buscarEmpleado() throws SQLException {
    // Object[][] datos = iBD.consultar("select idEmpleado,nombreEmpleado  '" + dm.tfBuscar.getText() + "'");
    // dm.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre"}));
    //}
    private void manejaEventoRegistrarEmpleado() throws SQLException {// TODO ARREGLAR ESTE METODO
        String telfono = dm.telefono.getText();
        String apellPaterno= dm.apellidoPaterno.getText();
        String apellMaterno= dm. apellidoMaterno.getText();
        String domi= dm.domicilio.getText();
        
       
    }

    private void consultarEmpleadoDM() throws SQLException {
        Object[][] datos = iBD.consultar("select nombreEmpleado,apellidoPaternoEmpleado,"
                + "apellidoMaternoEmpleado,domicilioEmpleado,telefonoEmpleado" + empleadoSeleccionado + "");

        dm.nombre.setText((String) datos[0][0]);
        dm.apellidoPaterno.setText((String) datos[0][1]);
        dm.apellidoMaterno.setText((String) datos[0][2]);
        dm.domicilio.setText((String) datos[0][3]);
        dm.telefono.setText((String) datos[0][4]);

    }

    private void manejaEventoModificarEmpleado() throws SQLException {
        iBD.actualizar("update Empleado set telefonoEmpleado = '" + dm.telefono.getText() + "', "
                + " where idEmpleado =  " + empleadoSeleccionado);

        //  this.consultarEmpleado();
        dm.dispose();
    }

    private void consultarEmpleadoVentana(int matri) throws SQLException {
        Object[][] datos = iBD.consultar("select nombreEmpleado,apellidoPaternoEmpleado,"
                + "apellidoMaternoEmpleado,"
                + "where idEmpleado = " + matri);

        dm.nombre.setText((String) datos[0][0]);
        dm.apellidoMaterno.setText((String) datos[0][1]);
        dm.apellidoPaterno.setText((String) datos[0][2]);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        //Si el usuario presiona la tecla enter en el Textfield buscar, entonces...
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            //Si el textfield no esta vacio entonces..
            // if (!dm.tfBuscar.getText().isEmpty()) {
            // try {
            // this.buscarEmpleado();
            //} catch (SQLException ex) {
            // ex.printStackTrace();
        }
    }
    // }
    //}

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        //Cierro la ventana actua
        dm.dispose();
        //Se vuelve a mostrar la ventana anterior
        this.VistaAnterior.setVisible(true);

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        //Este es para la tabla de mi ventana
        /* if (e.getSource() == dm.tabla.getSelectionModel()) {
            //De esta forma obtengo la matricula del empleado seleccionado
            empleadoSeleccionado = (int) dm.tabla.getValueAt(dm.tabla.getSelectedRow(), 0);//<-- Este ultimo numero corresponde a la col de la tabla
            try {
                this.consultarEmpleadoVentana(empleadoSeleccionado);
                dm.btnRegistrarEmpleado.setEnabled(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
         */
    }//Este es para la tabla de mi dm

}
