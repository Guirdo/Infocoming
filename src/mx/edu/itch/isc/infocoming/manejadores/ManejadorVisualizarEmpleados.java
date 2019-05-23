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
import mx.edu.itch.isc.infocoming.interfacesgraficas.VGestionPersonal;

public class ManejadorVisualizarEmpleados implements ActionListener, ListSelectionListener, KeyListener, WindowListener {

    private VGestionPersonal v;
    private InterfazBD intBD;
    private int personalSeleccion;
    private Pantalla vistaAnterior;
    private int claveEmpleado;

    public ManejadorVisualizarEmpleados(InterfazBD inte, VGestionPersonal vv, Pantalla ant) throws SQLException {
        this.v = vv;
        this.intBD = inte;
        this.vistaAnterior = ant;
        v.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        v.tabla.getSelectionModel().addListSelectionListener(this);
        // v.buscar.addKeyListener(this);
        v.addWindowListener(this);
        this.consultarPersonal();
//        v.btnBajaEmpleado.setEnabled(false);
        v.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    private void consultarPersonal() throws SQLException {
        Object[][] datos = intBD.consultar("Select idPersonal, nombrepersonal, apellidoPaternopersonal, apellidoMaternopersonal "
                + "from Personal");

        v.tabla.setModel(new DefaultTableModel(datos, new Object[]{"clave Empleado", "Nombre", "ApePat", "ApeMat"}));
    }

    private void consultarPersonalVentana(int matri) throws SQLException {
        Object[][] datos = intBD.consultar("select nombrePersonal,apellidoPaternopersonal,"
                + "apellidoMaternopersonal,idPersonal,telefonopersonal, domiciliopersonal from Personal "
                + "where idPersonal = " + personalSeleccion);

        v.lblNombre.setText((String) datos[0][0] + " " + (String) datos[0][1] + " " + (String) datos[0][2]);
        v.lblClave.setText((int) datos[0][3] + " ");
        v.lblTelefono.setText((String) datos[0][4]);
        v.lblDomicilio.setText((String) datos[0][5]);

//        v.setVisible(true);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        //Este es para la tabla de mi ventana
        if (e.getSource() == v.tabla.getSelectionModel()) {
            //De esta forma obtengo la matricula del alumno seleccionado
            personalSeleccion = (int) v.tabla.getValueAt(v.tabla.getSelectedRow(), 0);//<-- Este ultimo numero corresponde a la col de la tabla
            try {
                this.consultarPersonalVentana(personalSeleccion);
//                v.btnBajaEmpleado.setEnabled(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //Cierro la ventana actua
        v.dispose();
        //Se vuelve a mostrar la ventana anterior
        this.vistaAnterior.setVisible(true);

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

}
