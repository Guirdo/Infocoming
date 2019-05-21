package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import net.miginfocom.swing.MigLayout;

public class DMConfirmarBaja extends PantallaModal{
    
    public JPasswordField tfContra;
    public JButton btnConfirmar,btnCancelar;

    public DMConfirmarBaja() {
        super("Confirmar baja",new MigLayout("wrap 2","[]15[]","[]10[]15[]"));
        
        tfContra = new JPasswordField(20);
        btnCancelar=new JButton("Cancelar");
        btnConfirmar=new JButton("Confirmar");
        
        btnConfirmar.setBackground(Color.decode("#7c98b3"));
        btnCancelar.setBackground(Color.decode("#cee5f2"));
        btnCancelar.setIcon(new ImageIcon(this.getClass().getResource("/mx/edu/itch/isc/infocoming/iconos/cancelar24.png")));
        btnConfirmar.setIcon(new ImageIcon(this.getClass().getResource("/mx/edu/itch/isc/infocoming/iconos/confirm24.png")));
        
        this.add(new JLabel("Ingrese su contraseña para continuar: "),"span 2");
        this.add(tfContra,"span 2");
        this.add(btnCancelar,"pushx,center");
        this.add(btnConfirmar,"pushx,center");
        
        this.pack();
        this.setLocationRelativeTo(null);
    }

    
    
}
