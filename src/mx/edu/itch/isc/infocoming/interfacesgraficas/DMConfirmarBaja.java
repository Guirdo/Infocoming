package mx.edu.itch.isc.infocoming.interfacesgraficas;

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
        
        this.add(new JLabel("Ingrese su contraseña para continuar: "),"span 2");
        this.add(tfContra,"span 2");
        this.add(btnCancelar,"pushx,center");
        this.add(btnConfirmar,"pushx,center");
        
        this.pack();
        this.setLocationRelativeTo(null);
    }

    
    
}
