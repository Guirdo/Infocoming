
package mx.edu.itch.isc.infocoming.manejadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import mx.edu.itch.isc.infocoming.excepciones.ContrasenaException;
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

   // ManejadorModificarContrasena(InterfazBD intBD, VGestionUsuario vGestionUsuario) {
       
    //}

    @Override
    public void actionPerformed(ActionEvent e) {
   
    if(e.getSource() == v.btnModificar){
        try {
            this.manejaEventoModificaContrasena();
        } catch (ContrasenaException ex) {
            Logger.getLogger(ManejadorModificarContrasena.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorModificarContrasena.class.getName()).log(Level.SEVERE, null, ex);
        }

	}
}
    @Override
    public void valueChanged(ListSelectionEvent e) {
            
    
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

    private void manejaEventoModificaContrasena() throws ContrasenaException, SQLException {
        char[ ] nueva,confirmacion;
        boolean iguales= false;
        nueva= v.tfNuevoContra.getPassword();
        confirmacion=v.tfConfirmarContra.getPassword();
        if(nueva.length==confirmacion.length){
          for(int i=0; i<nueva.length; i++){
              if(nueva[i]!=confirmacion[i]){
                  iguales=false;
                  break;
              }else iguales=true;
              
            
        }
          
        }
        if (iguales){
            String usuario;
            String contra= "";
            usuario= (String) v.cbUsuario.getSelectedItem();
            
            for(char c:nueva){
                contra+=c;
            }
            intBD.actualizar("alter user "+ usuario+ " identified by '"+ contra+ "'" );
            
        }else {
            throw new ContrasenaException("No coinsiden");
        }
    }
    
}
