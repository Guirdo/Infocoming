package mx.edu.itch.isc.infocoming.manejadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalEquipo;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VValidarUsuario;

public class ManejadorValidarUsuario implements ActionListener {

    private VValidarUsuario v;

    public ManejadorValidarUsuario(VValidarUsuario vv) {
        this.v = vv;

        //Agregar actionListener a los botones de la vista
        v.btnIngresar.addActionListener(this);
        v.btnCancelar.addActionListener(this);

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
                        System.out.println("Soy el administrador");
                        break;
                    case "Recepcionista":
                        System.out.println("Soy el Recepcionista");
                        break;
                    case "CoordinadorAca":
                        System.out.println("Soy el Coordinador");
                        break;
                    case "Director":
                        System.out.println("Soy el Director");
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

}
