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
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMConfirmarBaja;
import mx.edu.itch.isc.infocoming.interfacesgraficas.Pantalla;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VBajaAlumno;

/**
 *
 * @author pacop
 */
public class ManejadorBajaAlumno implements ActionListener,ListSelectionListener,KeyListener,WindowListener{
    private VBajaAlumno v;
    private InterfazBD intBD;
    private int alumnoSeleccionado;
    private DMConfirmarBaja dm=null;
    private Pantalla vistaAnterior;

    public ManejadorBajaAlumno(InterfazBD inte,VBajaAlumno vv,Pantalla ant) throws SQLException {
        this.v = vv;
        this.intBD = inte;
        this.vistaAnterior = ant;
        v.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        v.btn.addActionListener(this);
        v.tabla.getSelectionModel().addListSelectionListener(this);
        v.buscar.addKeyListener(this);
        v.addWindowListener(this);
        this.consultarAlumnos();
        v.btn.setEnabled(false);
        v.setVisible(true);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == v.btn){
            try {
                this.manejaEventoBajaAlumno();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else if (dm != null) {
            if (e.getSource() == dm.btnConfirmar) {
                try {
                    this.eliminaAlumno();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else if (e.getSource() == dm.btnCancelar) {
                dm.dispose();
            }
        }
    }
    private void consultarAlumnos() throws SQLException{
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno from Alumno,Grupo where grupID = idGrupo");
        
        v.tabla.setModel(new DefaultTableModel(datos,new Object[]{"Matricula","Nombre"}));
    }

    private void manejaEventoBajaAlumno() throws SQLException{
        dm=new DMConfirmarBaja();
        dm.btnConfirmar.addActionListener(this);
        dm.btnCancelar.addActionListener(this);
        dm.setVisible(true);
    }
    private void eliminaAlumno()throws SQLException {
        char contra[]=dm.tfContra.getPassword();
        String gContra="";
        for (char s:contra ){
           gContra+=s; 
        }
        if( gContra.equals(intBD.contrasena)){
        intBD.eliminar("delete from alumno where idalumno= "+alumnoSeleccionado);

        v.tabla.getSelectionModel().removeListSelectionListener(this);
        this.consultarAlumnos();
        v.tabla.getSelectionModel().addListSelectionListener(this);
        dm.dispose();
        }else
            JOptionPane.showMessageDialog(null, "contraseña invalida", "Mensaje de error", JOptionPane.ERROR_MESSAGE);
    }
    private void consultarAlumnoVentana(int matri) throws SQLException {
        Object[][] datos = intBD.consultar("select nombreAlumno,apellidopaternoalumno,"
                +"apellidomaternoalumno,tipocurso from Alumno,Curso,Grupo "
                + "where idAlumno = " + matri + " and grupid = idGrupo "
                + "and curso = idCurso");

        v.matricula.setText(matri+"");
        v.nombre.setText((String) datos[0][0]);
        v.lblApePat.setText((String) datos[0][1]);
        v.lblApeMat.setText((String) datos[0][2]);
        v.curso.setText((String) datos[0][3]);
//        v.setVisible(true);
    }
     private void buscarAlumnoPorApellido() throws SQLException {
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno from Alumno,grupo where grupID = idGrupo and apellidoPaternoAlumno = '" + v.buscar.getText() + "'");
        v.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre"}));
    }
    
    private void buscarAlumnoPorMatricula() throws SQLException{
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno from Alumno,grupo where grupID = idGrupo and idAlumno = " + v.buscar.getText());
        v.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre"}));
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        //Este es para la tabla de mi ventana
        if (e.getSource() == v.tabla.getSelectionModel()) {
            //De esta forma obtengo la matricula del alumno seleccionado
            alumnoSeleccionado = (int) v.tabla.getValueAt(v.tabla.getSelectedRow(), 0);//<-- Este ultimo numero corresponde a la col de la tabla
            try {
                this.consultarAlumnoVentana(alumnoSeleccionado);
                v.btn.setEnabled(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }//Este es para la tabla de mi dm
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
               //Si el usuario presiona la tecla enter en el Textfield buscar, entonces...
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            //Si el textfield no esta vacio entonces..
            if (!v.buscar.getText().isEmpty()) {
                String busqueda = v.buscar.getText();

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
    
}
