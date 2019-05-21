
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
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesgraficas.Pantalla;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VGestionUsuario;

public class ManejadorModificarContrasena implements ActionListener,ListSelectionListener,KeyListener,WindowListener{

     private VGestionUsuario v;
    private InterfazBD intBD;
    private Pantalla vistaAnterior;
       
    
    
    
    ManejadorModificarContrasena(InterfazBD intBD, VGestionUsuario v, Pantalla ant) {
         this.v = v;
        this.intBD = intBD;
        this.vistaAnterior = ant;
      
        v.btnModificar.addActionListener(this);
        v.addWindowListener(this);
        v.setVisible(true);
        //this.consulta();
        
        v.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
   
    if(e.getSource() == v.btnModificar){
        this.manejaEventoModificaContrasena();
        }
       
    }
    private void manejaEventoRegistrarES() throws SQLException {// TODO ARREGLAR ESTE METODO
       
       // String claveEmpleado = v.tfClaveEmpleado.getText();
        //String tipo =(String) v.cbTipo.getSelectedItem();
        //iBD.procedimientoInsertar("{call insertarAsistencia(?,?)}", claveEmpleado,tipo);
        
       // this.consultaES();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
            
    
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void manejaEventoModificaContrasena() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
