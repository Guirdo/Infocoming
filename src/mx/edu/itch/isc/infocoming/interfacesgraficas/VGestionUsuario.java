package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class VGestionUsuario extends Pantalla{
    
    public JComboBox<String> cbUsuario;
    public JLabel lblUsuario;
    public JPasswordField tfNuevoContra,tfConfirmarContra;
    public JButton btnModificar;

    public VGestionUsuario() {
        super("Gestión de usuarios",new MigLayout("wrap 3","[][][]"
                ,"10[]10[]10[]"));
        
        this.getContentPane().setBackground(Color.decode("#f5f6fa"));
       
        
        cbUsuario = new JComboBox();
        lblUsuario = new JLabel();
        tfNuevoContra = new JPasswordField(15);
        tfConfirmarContra=new JPasswordField(15);
        btnModificar = new JButton("Modificar");
       cbUsuario.addItem("Administrador");
       cbUsuario.addItem("Director");
       cbUsuario.addItem("Recepcionista");
       cbUsuario.addItem("CoordinadorAca");
        btnModificar.setBackground(Color.decode("#cee5f2"));
      
        
        this.add(new JLabel("Usuario: "));
        this.add(cbUsuario,"wrap");
        this.add(new JLabel("Nueva contraseña: "));
        this.add(tfNuevoContra,"growx");
        this.add(btnModificar,"span 1 2,right");
        this.add(new JLabel("Confirmar: "));
        this.add(tfConfirmarContra);
        
        
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    

}
