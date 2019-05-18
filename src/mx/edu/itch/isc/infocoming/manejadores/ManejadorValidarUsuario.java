package mx.edu.itch.isc.infocoming.manejadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalAdministrador;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalCoordinadorAcademico;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalDirector;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalEquipo;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalRecepcionista;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VValidarUsuario;

public class ManejadorValidarUsuario implements ActionListener,KeyListener {

    private VValidarUsuario v;

    public ManejadorValidarUsuario(VValidarUsuario vv) {
        this.v = vv;

        //Agregar actionListener a los botones de la vista
        v.btnIngresar.addActionListener(this);
        v.btnCancelar.addActionListener(this);
        v.tfContra.addKeyListener(this);

        v.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == v.btnIngresar) {
            this.manejaEventoIngresar();
        }else if(e.getSource() == v.btnCancelar){
            //Cerrar con mensaje
            System.exit(0);
        }

    }
    
    private void manejaEventoIngresar(){
        try {
                String usuario = v.tfUsuario.getText();
                char[] contraArray = v.tfContra.getPassword();
                String contra = "";

                for (char c : contraArray) {
                    contra += c;
                }

                InterfazBD intBD = new InterfazBD(usuario, contra);

                v.dispose();
                
                //Este switch se encargara de desplegar los paneles que corresponden
                switch (usuario) {
                    case "Administrador":
                        new ManejadorPrincipal(intBD,new PanelPrincipalAdministrador());
                        break;
                    case "Recepcionista":
                        new ManejadorPrincipal(intBD,new PanelPrincipalRecepcionista());
                        break;
                    case "CoordinadorAca":
                        new ManejadorPrincipal(intBD,new PanelPrincipalCoordinadorAcademico());
                        break;
                    case "Director":
                        new ManejadorPrincipal(intBD,new PanelPrincipalDirector());
                        break;
                    case "root":
                        new ManejadorPrincipal(intBD,new PanelPrincipalEquipo());
                        break;
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Usuario o contraseña invalido", "Mensaje de error", JOptionPane.ERROR_MESSAGE);
            }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            this.manejaEventoIngresar();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

}
