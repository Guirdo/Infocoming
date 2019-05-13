package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class VGestionUsuario extends Pantalla{
    
    private JTable tabla;
    private JLabel lblUsuario;
    private JPasswordField tfNuevoContra,tfConfirmarContra;
    private JButton btnModificar;

    public VGestionUsuario() {
        super("Gestión de usuarios",new MigLayout("wrap 3","[][][]","[]15[150]25[]15[]10[]10[]"));
        
        JLabel lblTitulo = new JLabel("Cuentas de usuario del sistema");
        JLabel lblTitulo1 = new JLabel("Modificar contraseña");
        lblTitulo.setFont(new Font("Arial",1,16));
        lblTitulo1.setFont(new Font("Arial",1,16));
        
        tabla = new JTable();
        lblUsuario = new JLabel();
        tfNuevoContra = new JPasswordField(15);
        tfConfirmarContra=new JPasswordField(15);
        btnModificar = new JButton("Modificar");
        
        tabla.setModel(new DefaultTableModel(new Object[]{"Usuario","Ultimo Login"},0));
        
        this.add(lblTitulo,"span 3");
        this.add(new JScrollPane(tabla),"span 3");
        this.add(lblTitulo1,"span 3");
        this.add(new JLabel("Usuario: "));
        this.add(lblUsuario,"wrap");
        this.add(new JLabel("Nueva contraseña: "));
        this.add(tfNuevoContra,"growx");
        this.add(btnModificar,"span 1 2,right");
        this.add(new JLabel("Confirmar: "));
        this.add(tfConfirmarContra);
        
        
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    

}