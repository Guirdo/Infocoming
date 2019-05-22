package mx.edu.itch.isc.infocoming.interfacesgraficas;


import java.awt.Font;

import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import net.miginfocom.swing.MigLayout;

public class DMRegistrarPago extends PantallaModal {
    
    public JRadioButton btnComun,btnNuevo;
    public JComboBox<String> cbConceptos;

    public JTextField tfConceptoNuevo;
    
    public JButton btnRegistrar;
    public JTextField busca;
    public JLabel nombre,curso,horario;

    

    public JSpinner spnPago;

    public DMRegistrarPago() {
        super("Registrar pago", new MigLayout("wrap 2", "[]15[]", "[]10[]10[]10[]10[]10[]10[]"));

        nombre=new JLabel();
        nombre.setFont(new Font("Arial",2,12));        
        curso = new JLabel ();
        curso.setFont(new Font("Arial",2,12));
        horario=new JLabel();
        horario.setFont(new Font("Arial",1,12));
        btnComun = new JRadioButton("Común");
        btnNuevo = new JRadioButton("Nuevo");
        cbConceptos = new JComboBox<>();
        tfConceptoNuevo = new JTextField(12);
       
        btnRegistrar = new JButton("Registrar pago");

        spnPago = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1000000, 0.5));        
        busca = new JTextField (12);
        busca.setFont(new Font("Arial",2,12));
        spnPago = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1000000, 0.5));
        cbConceptos.setEnabled(false);
        tfConceptoNuevo.setEnabled(false);
        ButtonGroup bg = new ButtonGroup();
        bg.add(btnNuevo);
        bg.add(btnComun);
        
        
        btnRegistrar.setBackground(Color.decode("#7c98b3"));
        
        btnRegistrar.setIcon(new ImageIcon(this.getClass().getResource("/mx/edu/itch/isc/infocoming/iconos/entrar24.png")));
        
        btnComun.addActionListener((ActionEvent)->{
            cbConceptos.setEnabled(true);
            tfConceptoNuevo.setEnabled(false);
        });
        
        btnNuevo.addActionListener((ActionEvent)->{
            cbConceptos.setEnabled(false);
            tfConceptoNuevo.setEnabled(true);
        });
        
        this.add(new JLabel("Matrícula: "));
        this.add(busca,"wrap");
        this.add(new JLabel("Nombre alumno: "));
        this.add(nombre,"wrap");
        this.add(new JLabel("curso"));
        this.add(curso,"wrap");
        this.add(new JLabel("Horario"));
        this.add(horario,"wrap");
        this.add(new JLabel("Concepto: "),"span 2");
        this.add(btnComun);
        this.add(cbConceptos);
        this.add(btnNuevo);
        this.add(tfConceptoNuevo);
        this.add(new JLabel("Pago: "));
        this.add(spnPago,"growx");     
        this.add(btnRegistrar);

        this.pack();
        this.setLocationRelativeTo(null);
    }

}
