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
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMReinscribirAlumno;
import mx.edu.itch.isc.infocoming.interfacesgraficas.Pantalla;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VReinscribirAlumno;

public class ManejadorReinscribirAlumno implements ActionListener, KeyListener, WindowListener, ListSelectionListener {

    private VReinscribirAlumno v;
    private DMReinscribirAlumno dm = null;
    private InterfazBD intBD;
    private Pantalla vistaAnterior;

    private int alumnoSeleccionado;
    private int grupoSeleccionado;

    /**
     * Constructor principal
     *
     * @param inter Objeto de la conexion a la base de datos
     * @param vv ventana a mostrar
     * @param ant ventana de donde viene el evento, la ventana anterior
     * @throws SQLException
     */
    public ManejadorReinscribirAlumno(InterfazBD inter, VReinscribirAlumno vv, Pantalla ant) throws SQLException {
        this.v = vv;
        this.intBD = inter;
        this.vistaAnterior = ant;

        //ActionListerner de los botones del dm
        v.btnReinscribir.addActionListener(this);

        //Esta linea sirve para que al dar enter busque
        v.tfBuscar.addKeyListener(this);
        //Esta linea sirve para mostrar los datos abajo
        v.tabla.getSelectionModel().addListSelectionListener(this);
        //Esta linea sirve para poder regresar al panel anterior
        v.addWindowListener(this);

        //Esta linea sirve para consultar antes de entrar
        this.consultarAlumnos();

        v.btnReinscribir.setEnabled(false);
        v.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == v.btnReinscribir) {
            try {
                this.manejaEventoReinscribirAlumno();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (dm != null) {
            if (e.getSource() == dm.btnModificar) {
                try {
                    this.manejaEventoModificarAlumno();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else if (e.getSource() == dm.btnCancelar) {
                dm.dispose();
            }
        }
    }

    private void consultarAlumnos() throws SQLException {
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno,apellidoPaternoAlumno,apellidoMaternoAlumno,horario "
                + "from Alumno,Grupo where grupID = idGrupo");

        v.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre","ApePaterno","ApeMaterno", "Horario"}));
    }

    private void buscarAlumnoPorApellido() throws SQLException {
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno,apellidoPaternoAlumno,apellidoMaternoAlumno,horario from Alumno,Grupo "
                + "where grupID = idGrupo and apellidoPaternoAlumno = '" + v.tfBuscar.getText() + "'");

        v.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre","ApePaterno","ApeMaterno", "Horario"}));
    }
    
    private void buscarAlumnoPorMatricula() throws SQLException{
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno,apellidoPaternoAlumno,apellidoMaternoAlumno,horario from Alumno,Grupo "
                + "where grupID = idGrupo and idAlumno = " + v.tfBuscar.getText());

        v.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre","ApePaterno","ApeMaterno", "Horario"}));
    }

    private void manejaEventoReinscribirAlumno() throws SQLException {
        dm = new DMReinscribirAlumno();

        dm.btnModificar.addActionListener(this);
        dm.btnCancelar.addActionListener(this);
        dm.tabla.getSelectionModel().addListSelectionListener(this);

        this.consultarHorarioCurso();
        this.consultarAlumnoDM();

        dm.setVisible(true);
    }

    private void consultarAlumnoDM() throws SQLException {
        Object[][] datos = intBD.consultar("select nombreAlumno,apellidoPaternoAlumno,"
                + "apellidoMaternoAlumno,domicilioalumno,telefonoalumno from Alumno,Curso,Grupo "
                + "where idAlumno = " + alumnoSeleccionado + " and grupid = idGrupo "
                + "and curso = idCurso");

        dm.lblNombre.setText((String) datos[0][0]);
        dm.lblApellidoPaterno.setText((String) datos[0][1]);
        dm.lblApellidoMaterno.setText((String) datos[0][2]);
        dm.lblDomicilio.setText((String) datos[0][3]);
        dm.telefono.setText((String) datos[0][4]);

    }

    private void manejaEventoModificarAlumno() throws SQLException {
        intBD.actualizar("update Alumno set telefonoalumno = '" + dm.telefono.getText() + "', "
                + "grupid = " + grupoSeleccionado + " where idAlumno =  " + alumnoSeleccionado);

        //  this.consultarAlumnos();
        dm.dispose();
    }

    private void consultarHorarioCurso() throws SQLException {
        Object[][] datos = intBD.consultar("select idGrupo,horario,tipocurso from Grupo,Curso "
                + "where curso=idCurso");

        dm.tabla.setModel(new DefaultTableModel(datos, new Object[]{"NumGrupo", "Horario", "Curso"}));
    }

    /**
     * Metodo que llena los label que estan debajo de la tabla
     *
     * @param matri La matricula del alumno seleccionado
     * @throws SQLException
     */
    private void consultarAlumnoVentana(int matri) throws SQLException {
        Object[][] datos = intBD.consultar("select nombreAlumno,apellidoPaternoAlumno,"
                + "apellidoMaternoAlumno,tipocurso,horario from Alumno,Curso,Grupo "
                + "where idAlumno = " + matri + " and grupid = idGrupo "
                + "and curso = idCurso");

        v.lblNombre.setText((String) datos[0][0]);
        v.lblApePat.setText((String) datos[0][1]);
        v.lblApeMat.setText((String) datos[0][2]);
        v.lblCurso.setText((String) datos[0][3]);
        v.lblHorario.setText((String) datos[0][4]);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Si el usuario presiona la tecla enter en el Textfield buscar, entonces...
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            //Si el textfield no esta vacio entonces..
            if (!v.tfBuscar.getText().isEmpty()) {
                String busqueda = v.tfBuscar.getText();

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

    @Override
    public void valueChanged(ListSelectionEvent e) {
        //Este es para la tabla de mi ventana
        if (e.getSource() == v.tabla.getSelectionModel()) {
            //De esta forma obtengo la matricula del alumno seleccionado
            alumnoSeleccionado = (int) v.tabla.getValueAt(v.tabla.getSelectedRow(), 0);//<-- Este ultimo numero corresponde a la col de la tabla
            try {
                this.consultarAlumnoVentana(alumnoSeleccionado);
                v.btnReinscribir.setEnabled(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }//Este es para la tabla de mi dm
        else if (e.getSource() == dm.tabla.getSelectionModel()) {
            //De esta forma obtengo el id del grupo seleccionado
            grupoSeleccionado = (int) dm.tabla.getValueAt(dm.tabla.getSelectedRow(), 0);
            System.out.println(grupoSeleccionado);
        }
    }

}
