package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.utilidades.TextPrompt;
import net.miginfocom.swing.MigLayout;

public class VGestionPagos extends Pantalla{
    
    public JLabel lblNombre,lblApellidoP,lblApellidoM, lblFolio,lblCantidad, lblConcepto, lblHorario;
    public JTextField tfBuscar;
    public JTable tabla;

    public VGestionPagos() {
        super("Gestión de pagos", new MigLayout("wrap 3","[]10[200]10[]"
                                  ,"10[]15[]10[150]25[]15[]10[]10[]10[]10[]10[]10[]10"));
        
        JLabel lblTitulo = new JLabel("Historial de pago por alumno");
        JLabel lblTitulo1 = new JLabel("Datos del alumno");
        lblTitulo.setFont(new Font("Arial",1,16));
        lblTitulo1.setFont(new Font("Arial",1,16));
        
        tfBuscar = new JTextField(12);
        tabla = new JTable();
        lblNombre = new JLabel();
        lblApellidoP = new JLabel();
        lblApellidoM = new JLabel();
        lblFolio =new JLabel();
        lblCantidad = new JLabel();
        lblConcepto= new JLabel();
        lblHorario = new JLabel(); 
        
        tabla.setModel(new DefaultTableModel(new Object[]{"Matrícula","Nombre","Fecha pago","Concepto pago"}, 4));
        TextPrompt ph = new TextPrompt("Nombre del alumno...", tfBuscar);
        ph.changeAlpha(0.75f);
        ph.changeStyle(Font.ITALIC);
        
        this.add(lblTitulo,"span 3");
        this.add(tfBuscar,"span 3,right");
        this.add(new JScrollPane(tabla),"span 3");
        this.add(lblTitulo1,"span 3");
        this.add(new JLabel("Nombre: "));
        this.add(lblNombre,"wrap");
        this.add(new JLabel("Apellido Paterno: "));
        this.add(lblApellidoP, "wrap");
        this.add(new JLabel("Apellido Materno: "));
        this.add(lblApellidoM,"wrap");
        this.add(new JLabel("Folio: "));
        this.add(lblFolio,"wrap");
        this.add(new JLabel("Cantidad: "));
        this.add(lblCantidad,"wrap");
        this.add(new JLabel("Concepto: "));
        this.add(lblConcepto,"wrap");
        this.add(new JLabel("Horario: "));
        this.add(lblHorario,"wrap");
        
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
