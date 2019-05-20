
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
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMRegistrarPersonal;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMRegistroES;
import mx.edu.itch.isc.infocoming.interfacesgraficas.Pantalla;

public class ManejadorES  implements ActionListener, KeyListener, WindowListener,ListSelectionListener{
     private DMRegistroES dmES;
     private InterfazBD iBD;
     private Pantalla VistaAnterior;
     
     private int empleadoSeleccionado;
     
     public ManejadorES(InterfazBD ibd, DMRegistroES dmES, Pantalla ant) throws SQLException { 
       this.VistaAnterior = ant;
        this.dmES = dmES;
        this.iBD = ibd;  
        
        //ActionListerner de los botones del dm
        this.dmES.btnRegistrar.addActionListener(this);
        dmES.tfClaveEmpleado.addActionListener(this);
        //Esta linea sirve para mostrar los datos abajo
        dmES.tabla.getSelectionModel().addListSelectionListener(this);
        this.dmES.addWindowListener(this);
        dmES.setVisible(true);
     }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dmES.btnRegistrar) {
            try {
                this.manejaEventoRegistrarES();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } 
    }
    private void manejaEventoRegistrarES() throws SQLException {// TODO ARREGLAR ESTE METODO
        //","Nombre","HoraEntrada","HoraSalida"
        String claveEmpleado = dmES.tfClaveEmpleado.getText();
        iBD.procedimientoInsertar("{call insertarAsistencia(?)}", claveEmpleado);
      
    }
    private void consultaES() throws SQLException {
        //Object[][] datos = iBD.consultar("select claveEmpleado,nombreEmpleado,"
              //  + "horaEntrada,horaSalida" + empleadoSeleccionado + "");

    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
    //Si el usuario presiona la tecla enter en el Textfield buscar, entonces...
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
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
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    //Cierro la ventana actua
        dmES.dispose();
        //Se vuelve a mostrar la ventana anterior
        this.VistaAnterior.setVisible(true);
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
    }
    
}
