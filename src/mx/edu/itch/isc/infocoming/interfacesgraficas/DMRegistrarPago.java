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

    public DMRegistrarPago() {
        super("Registrar pago", new MigLayout("wrap 2", "[]15[]", "[][][][][][]"));

        JLabel lblNombre = new JLabel();
        JLabel lblCurso = new JLabel();
        JLabel lblHorario = new JLabel();
        JRadioButton btnComun = new JRadioButton("Común");
        JRadioButton btnNuevo = new JRadioButton("Nuevo");
        JComboBox<String> cbConceptos = new JComboBox<>();
        JTextField tfConceptoNuevo = new JTextField(12);
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnRegistrar = new JButton("Registrar pago");
        JSpinner spnPago = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1000000, 0.5));

        cbConceptos.addItem("Pago semanal");
        cbConceptos.addItem("Inscripción");
        cbConceptos.addItem("ExC-2019-1");
        ButtonGroup bg = new ButtonGroup();
        bg.add(btnNuevo);
        bg.add(btnComun);
        
        this.add(new JLabel("Nombre: "));
        this.add(lblNombre);
        this.add(new JLabel("Curso: "));
        this.add(lblCurso);
        this.add(new JLabel("Horario: "));
        this.add(lblHorario);
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
