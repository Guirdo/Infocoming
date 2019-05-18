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
    
    public JTextField tfUsuario;
    public JPasswordField tfContra;
    public JButton btnCancelar,btnIngresar;

    public VValidarUsuario() {
        super("Inicio de sesión",new MigLayout("wrap 2","","[]15[]15[]"));
        
        JLabel imagenInstitucional = new JLabel();
        Image img = new ImageIcon(this.getClass().getResource("/mx/edu/itch/isc/infocoming/iconos/marcaInstitucional.png")).getImage();
        imagenInstitucional.setIcon(new ImageIcon(img));
        JLabel imagenUsuario = new JLabel();
        img = new ImageIcon(this.getClass().getResource("/mx/edu/itch/isc/infocoming/iconos/usuario128.png")).getImage();
        imagenUsuario.setIcon(new ImageIcon(img));
        
        tfUsuario = new JTextField(12);
        tfContra = new JPasswordField(12);
        btnIngresar = new JButton("Ingresar");
        btnCancelar = new JButton("Cancelar");
        
        btnCancelar.setBackground(Color.decode("#cee5f2"));
        btnIngresar.setBackground(Color.decode("#7c98b3"));
        
        this.add(imagenInstitucional,"span 2");
        this.add(imagenUsuario,"w 150, h 150,span 2, center");
        this.add(this.darPnlFormulario(),"center,span 2");
        
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    private JPanel darPnlFormulario(){
        JPanel pnl = new JPanel(new MigLayout("wrap 2","[120]25[120]","[]10[]20[]"));
        pnl.setBackground(this.getContentPane().getBackground());
        
        pnl.add(new JLabel("Nombre usuario: "),"");
        pnl.add(tfUsuario);
        pnl.add(new JLabel("Contraseña: "),"");
        pnl.add(tfContra);
        pnl.add(btnIngresar,"growx");
        pnl.add(btnCancelar,"growx");
        
        return pnl;
    }
}
