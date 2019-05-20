package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class DMRegistroES extends PantallaModal{
    
    public JLabel lblFecha;
    public JTable tabla;
    public  JButton btnRegistrar;
    public  JTextField tfClaveEmpleado;

    public DMRegistroES() {
        super("Registro entrada/salida", new MigLayout("wrap","[]","[]15[150]25[]"));
        
        Calendar fecha = new GregorianCalendar();
        
        JLabel lblTitulo = new JLabel("Lista de asistencia de empleados");
        lblTitulo.setFont(new Font("Arial",1,16));
        
        lblFecha = new JLabel();
        tabla = new JTable();
        btnRegistrar = new JButton("Registrar entrada/salida");
        tfClaveEmpleado = new JTextField(12);
        
        tabla.setModel(new DefaultTableModel(new Object[]{"ClaveEmpleado","Nombre","HoraEntrada","HoraSalida"},0));
        lblFecha.setText(fecha.get(Calendar.DATE)+"/"+fecha.get(Calendar.MONTH)+"/"+fecha.get(Calendar.YEAR));
        
        this.add(lblTitulo);
        this.add(new JScrollPane(tabla));
        this.add(this.darPanel(),"center");
        
        this.pack();
        this.setLocationRelativeTo(null);
        
    }
    
    private JPanel darPanel(){
        JPanel panel = new JPanel(new MigLayout("wrap 2","[]15[]","[]10[]15[]"));
        
        panel.add(new JLabel("Fecha: "));
        panel.add(lblFecha);
        panel.add(new JLabel("Clave del empleado: "));
        panel.add(tfClaveEmpleado);
        panel.add(btnRegistrar,"span 2,center");
        
        return panel;
    }

}
