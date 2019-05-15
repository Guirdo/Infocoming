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

    public ManejadorReinscribirAlumno(InterfazBD inter, VReinscribirAlumno vv, Pantalla ant) throws SQLException {
        this.v = vv;
        this.intBD = inter;
        this.vistaAnterior = ant;

        //ActionListerner de los botones del dm
        v.btnReinscribir.addActionListener(this);
        v.tfBuscar.addKeyListener(this);
        v.tabla.getSelectionModel().addListSelectionListener(this);
        v.addWindowListener(this);

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
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno,horario from Alumno,Grupo where grupID = idGrupo");

        v.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre", "Horario"}));
    }

    private void buscarAlumno() throws SQLException {
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno,horario from Alumno,Grupo "
                + "where grupID = idGrupo and nombreAlumno = '" + v.tfBuscar.getText() + "'");

        v.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre", "Horario"}));
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
        intBD.actualizar("update Alumno set telefonoalumno = '"+dm.telefono.getText()+"', "
                + "grupid = "+grupoSeleccionado+" where idAlumno =  "+alumnoSeleccionado);
        
        
        //  this.consultarAlumnos();
        
        dm.dispose();
    }

    private void consultarHorarioCurso() throws SQLException {
        Object[][] datos = intBD.consultar("select idGrupo,horario,tipocurso from Grupo,Curso "
                + "where curso=idCurso");

        dm.tabla.setModel(new DefaultTableModel(datos, new Object[]{"NumGrupo","Horario", "Curso"}));
    }

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
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!v.tfBuscar.getText().isEmpty()) {
                try {
                    this.buscarAlumno();
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
        v.dispose();
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
        if (e.getSource() == v.tabla.getSelectionModel()) {
            alumnoSeleccionado = (int) v.tabla.getValueAt(v.tabla.getSelectedRow(), 0);
            try {
                this.consultarAlumnoVentana(alumnoSeleccionado);
                v.btnReinscribir.setEnabled(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(e.getSource() == dm.tabla.getSelectionModel()){
            grupoSeleccionado = (int) dm.tabla.getValueAt(dm.tabla.getSelectedRow(), 0);
            System.out.println(grupoSeleccionado);
        }
    }

}
