package mx.edu.itch.isc.infocoming.interfacesgraficas;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import net.miginfocom.swing.MigLayout;

public class DMRegistrarPago extends PantallaModal {
    
    private JRadioButton btnComun,btnNuevo;
    private JComboBox<String> cbConceptos;

    private JTextField tfConceptoNuevo;
    public JButton btnCancelar;
    public JButton btnRegistrar;

    private JTextField tfAlumno;

    private JSpinner spnPago;

    public DMRegistrarPago() {
        super("Registrar pago", new MigLayout("wrap 2", "[]15[]", "[]10[]10[]10[]10[]10[]"));

        tfAlumno = new JTextField(12);
        btnComun = new JRadioButton("Común");
        btnNuevo = new JRadioButton("Nuevo");
        cbConceptos = new JComboBox<>();
        tfConceptoNuevo = new JTextField(12);
        btnCancelar = new JButton("Cancelar");
        btnRegistrar = new JButton("Registrar pago");
        spnPago = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1000000, 0.5));

        cbConceptos.addItem("Pago semanal");
        cbConceptos.addItem("Inscripción");
        cbConceptos.addItem("ExC-2019-1");
        cbConceptos.setEnabled(false);
        tfConceptoNuevo.setEnabled(false);
        ButtonGroup bg = new ButtonGroup();
        bg.add(btnNuevo);
        bg.add(btnComun);
        
        btnComun.addActionListener((ActionEvent)->{
            cbConceptos.setEnabled(true);
            tfConceptoNuevo.setEnabled(false);
        });
        
        btnNuevo.addActionListener((ActionEvent)->{
            cbConceptos.setEnabled(false);
            tfConceptoNuevo.setEnabled(true);
        });
        
        this.add(new JLabel("Matrícula: "));
        this.add(tfAlumno);
        this.add(new JLabel("Concepto: "),"span 2");
        this.add(btnComun);
        this.add(cbConceptos);
        this.add(btnNuevo);
        this.add(tfConceptoNuevo);
        this.add(new JLabel("Pago: "));
        this.add(spnPago,"growx");
        this.add(btnCancelar);
        this.add(btnRegistrar);

        this.pack();
        this.setLocationRelativeTo(null);
    }

}
