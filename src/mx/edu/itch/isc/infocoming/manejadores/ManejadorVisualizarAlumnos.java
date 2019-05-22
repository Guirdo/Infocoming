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
import mx.edu.itch.isc.infocoming.interfacesgraficas.VVisualizarAlumnos;

public class ManejadorVisualizarAlumnos implements ActionListener, KeyListener, WindowListener, ListSelectionListener{
    private VVisualizarAlumnos vv;
    private InterfazBD intBD;
    private int alumnoSeleccionado;
    private Pantalla vistaAnterior;
    
    public ManejadorVisualizarAlumnos(InterfazBD inter, VVisualizarAlumnos v, Pantalla ant) throws SQLException {
        this.vv = v;
        this.intBD=inter;
        this.vistaAnterior = ant;
        vv.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //Agregar actionListener a los botones de la vista
        //Esta linea sirve para que al dar enter busque
        vv.tfBuscar.addKeyListener(this);
        vv.tabla.getSelectionModel().addListSelectionListener(this);//datos de abajo
        vv.addWindowListener(this);
        this.consultarAlumnos();
        vv.setVisible(true);
    }

    private void consultarAlumnos() throws SQLException{
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno, apellidoPaternoAlumno, apellidoMaternoAlumno from Alumno");
        
        vv.tabla.setModel(new DefaultTableModel(datos,new Object[]{"Matricula","Nombre", "Apellido Paterno", "Apellido Materno"}));
    }
    
    private void buscarAlumnoPorMatricula() throws SQLException{
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno, apellidoPaternoAlumno, apellidoMaternoAlumno from Alumno where idAlumno=" + vv.tfBuscar.getText());
        vv.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre", "Apellido Paterno", "Apellido Materno"}));
    }
    private void buscarAlumnoPorApellido() throws SQLException {
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno, apellidoPaternoAlumno, apellidoMaternoAlumno from Alumno where apellidoPaternoAlumno= '" + vv.tfBuscar.getText() + "'");
        vv.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre","Apellido Paterno", "Apellido Materno"}));
    }

    
    @Override
    public void actionPerformed(ActionEvent ae) {
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == vv.tabla.getSelectionModel()) {
            //De esta forma obtengo la matricula del alumno seleccionado
            alumnoSeleccionado = (int) vv.tabla.getValueAt(vv.tabla.getSelectedRow(), 0);//<-- Este ultimo numero corresponde a la col de la tabla
            try {
                this.consultarAlumnoVentana(alumnoSeleccionado);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    private void consultarAlumnoVentana(int matri) throws SQLException {
        Object[][] datos = intBD.consultar(" select idAlumno, nombreAlumno, apellidoPaternoAlumno, apellidoMaternoAlumno, domicilioAlumno, telefonoAlumno,  horario, tipocurso from Alumno, Grupo, Curso where idAlumno="+matri+" and idgrupo=grupid and idcurso=curso;");
        
        String nombre = ((String) datos[0][1]) + " " + ((String) datos[0][2]) + " " + ((String) datos[0][3]);
        vv.lblMatricula.setText((int) datos[0][0]+"");
        vv.lblNombre.setText(nombre);
        vv.lblDomicilio.setText((String) datos[0][4]);
        vv.lblTelefono.setText((String) datos[0][5]);
        vv.lblCurso.setText((String) datos[0][6]);
        vv.lblHorario.setText((String) datos[0][7]);
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //El enter :(
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            //Si el textfield no esta vacio entonces..
            if (!vv.tfBuscar.getText().isEmpty()) {
                String busqueda = vv.tfBuscar.getText();

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
        vv.dispose();
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
}
