package mx.edu.itch.isc.infocoming.manejadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBDEquipo;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalAdministrador;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VVisualizarAlumnos;

public class ManejadorVisualizarAlumnos implements ActionListener, KeyListener,ListSelectionListener{
    private VVisualizarAlumnos vv;
    private InterfazBD intBD;
    private int alumnoSeleccionado;
    
    public ManejadorVisualizarAlumnos(InterfazBD inter, VVisualizarAlumnos v) throws SQLException {
        this.vv = v;
        this.intBD=inter;
        

        //Agregar actionListener a los botones de la vista
//        this.consultarAlumnos();
        //Esta linea sirve para que al dar enter busque
        vv.tfBuscar.addKeyListener(this);
        vv.tabla.getSelectionModel().addListSelectionListener(this);//datos de abajo
        this.consultarAlumnos();
        vv.setVisible(true);
    }

    private void consultarAlumnos() throws SQLException{
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno from Alumno");
        
        vv.tabla.setModel(new DefaultTableModel(datos,new Object[]{"Matricula","Nombre"}));
    }
    
    private void buscarAlumno() throws SQLException {
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno from Alumno "
                + "where nombreAlumno = '" + vv.tfBuscar.getText() + "'");

        vv.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre"}));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        Object[][] datos = intBD.consultar("select idAlumno, nombreAlumno, idgrupo from Alumno, Grupo where idAlumno="+matri+" and idgrupo=idGrupo");
        
        vv.lblMatricula.setText((int) datos[0][0]+"");
        vv.lblNombre.setText((String) datos[0][1]);
        vv.lblCurso.setText((String) datos[0][2]);
//        vv.lblHorario.setText((String) datos[0][3]);
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //El enter
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            //Si el textfield no esta vacio entonces..
            if (!vv.tfBuscar.getText().isEmpty()) {
                try {
                    this.buscarAlumno();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
}
