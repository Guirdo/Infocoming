package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class VGestionPagos extends Pantalla{

    public VGestionPagos() {
        super("Gestión de pagos", new MigLayout("debug,wrap 3","[]10[200]10[]"
                                  ,"[]15[]10[150]25[]15[]10[]10[]10[]"));
        
        JLabel lblTitulo = new JLabel("Historial de pago por alumno");
        JLabel lblTitulo1 = new JLabel("Datos del alumno");
        lblTitulo.setFont(new Font("Arial",1,16));
        lblTitulo1.setFont(new Font("Arial",1,16));
        
        JTextField tfBuscar = new JTextField(12);
        
        JTable tabla = new JTable();
        JButton btnRegistrarPago = new JButton("Registrar pago");
        JLabel lblMatricula = new JLabel();
        JLabel lblNombre = new JLabel("____");
        JLabel lblCurso = new JLabel("_____");
        JLabel lblHorario = new JLabel(); 
        
        tabla.setModel(new DefaultTableModel(new Object[]{"Matrícula","Nombre","Fecha pago","ConceptoPago"}, 0));
        
        this.add(lblTitulo,"span 3");
        this.add(tfBuscar,"span 3,right");
        this.add(new JScrollPane(tabla),"span 3");
        this.add(lblTitulo1,"span 3");
        this.add(new JLabel("Matrícula: "));
        this.add(lblMatricula,"growx,wrap");
        this.add(new JLabel("Nombre: "));
        this.add(lblNombre,"growx,pushx");
        this.add(btnRegistrarPago,"span 1 2,right");
        this.add(new JLabel("Curso: "));
        this.add(lblCurso,"growx");
        this.add(new JLabel("Horario: "));
        this.add(lblHorario,"growx");
        
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
