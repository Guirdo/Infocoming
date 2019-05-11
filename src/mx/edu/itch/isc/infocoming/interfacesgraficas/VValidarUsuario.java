package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class VValidarUsuario extends Pantalla{

    public VValidarUsuario() {
        super("Inicio de sesión",new MigLayout("debug,wrap 2","","[]15[]15[]"));
        
        JLabel imagenInstitucional = new JLabel();
        Image img = new ImageIcon(this.getClass().getResource("/mx/edu/itch/isc/infocoming/iconos/marcaInstitucional.png")).getImage();
        imagenInstitucional.setIcon(new ImageIcon(img));
        JLabel imagenUsuario = new JLabel();
        imagenUsuario.setBackground(Color.BLACK);
        
        JPanel formInicio = new JPanel(new MigLayout("wrap 2","[120]25[120]","[]10[]20[]"));
        JTextField tfUsuario = new JTextField(12);
        JPasswordField tfContra = new JPasswordField(12);
        JButton btnIngresar = new JButton("Ingresar");
        JButton btnCancelar = new JButton("Cancelar");
        
        formInicio.add(new JLabel("Nombre usuario: "),"");
        formInicio.add(tfUsuario);
        formInicio.add(new JLabel("Contraseña: "),"");
        formInicio.add(tfContra);
        formInicio.add(btnIngresar,"growx");
        formInicio.add(btnCancelar,"growx");
        
        this.add(imagenInstitucional,"span 2");
        this.add(imagenUsuario,"w 150, h 150,span 2, center");
        this.add(formInicio,"center,span 2");
        
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    

}
