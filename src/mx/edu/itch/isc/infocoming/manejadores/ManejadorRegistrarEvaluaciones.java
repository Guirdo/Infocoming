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
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMRegistrarEvaluacion;
import mx.edu.itch.isc.infocoming.interfacesgraficas.Pantalla;

/**
 *
 * @author pacop
 */
public class ManejadorRegistrarEvaluaciones implements ActionListener, KeyListener, ListSelectionListener {

    private DMRegistrarEvaluacion dm = null;
    private InterfazBD intBD;
 
    public int alumnoSeleccionado = 0;
    public double calificacion = 0;

    public ManejadorRegistrarEvaluaciones(InterfazBD inter, DMRegistrarEvaluacion d) throws SQLException {
        this.intBD = inter;
        this.dm = d;
        dm.btn.addActionListener(this);
        dm.buscar.addKeyListener(this);
        dm.busca.addActionListener(this);
        dm.matricula.addKeyListener(this);
        dm.tabla.getSelectionModel().addListSelectionListener(this);
        dm.cal.addKeyListener(this);
        this.alumnos();
        dm.btn.setEnabled(false);
        dm.setVisible(true);

    }

    public void alumnos() throws SQLException {
        Object[][] datos = intBD.consultar("select idalumno,nombrealumno,tipocurso,calificacion from alumno,curso,grupo,evaluacion where idalumno=alumno_id and idcurso=curso and idgrupo=grupid");
        dm.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre alumno", "Curso", "Calificacion"}));
    }

    private void buscarAlumnoPorApellido() throws SQLException {
        Object[][] datos = intBD.consultar("select idalumno,nombrealumno,tipocurso,calificacion from alumno,curso,grupo,evaluacion where idalumno=alumno_id and idcurso=curso and idgrupo=grupid and apellidoPaternoAlumno = '" + dm.buscar.getText() + "'");
        dm.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre alumno", "Curso", "Calificacion"}));
    }

    private void buscarAlumnoPorMatricula() throws SQLException {
        Object[][] datos = intBD.consultar("select idalumno,nombrealumno,tipocurso,calificacion from alumno,curso,grupo,evaluacion where idalumno=alumno_id and idcurso=curso and idgrupo=grupid and idAlumno = " + dm.buscar.getText());
        dm.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre alumno", "Curso", "Calificacion"}));
    }

    private void consultarAlumnoVentana(int matri) throws SQLException {
        Object[][] datos = intBD.consultar("select nombreAlumno,tipocurso from Alumno,grupo,curso where idgrupo=grupid and idcurso=curso and idAlumno=" + matri);
        dm.matricula.setText(matri + "");
        dm.nombre.setText((String) datos[0][0]);
        dm.curso.setText((String) datos[0][1]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dm.btn) {
            try {
                this.manejaEventoRegistrarEvaluacioin();
                this.alumnos();
                dm.matricula.setText("");
                dm.cal.setText("");
                dm.nombre.setText("");
                dm.curso.setText("");
                dm.btn.setEnabled(false);
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
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            //Si el textfield no esta vacio entonces..
            if (e.getSource() == dm.buscar) {
                if (!dm.buscar.getText().isEmpty()) {
                    String busqueda = dm.buscar.getText();

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
            } else if (e.getSource() == dm.cal) {
                if (!dm.cal.getText().isEmpty()) {
                    calificacion = Double.parseDouble(dm.cal.getText());
                    dm.btn.setEnabled(true);
                }
            }else if (e.getSource() == dm.matricula) {
                if (!dm.matricula.getText().isEmpty()) {
                    try {                        
                        alumnoSeleccionado = Integer.parseInt(dm.matricula.getText());
                        this.consultarAlumnoVentana(alumnoSeleccionado);

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    private void manejaEventoRegistrarEvaluacioin() throws SQLException {
        
        intBD.actualizar("insert into evaluacion values(" + alumnoSeleccionado + "," + calificacion + ")");

    }
}
