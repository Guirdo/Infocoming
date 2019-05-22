package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
    public JComboBox<String> cbTipo;

    public DMRegistroES() {
        super("Registro entrada/salida", new MigLayout("wrap","[]","[]15[150]25[]"));
        
        Calendar fecha = new GregorianCalendar();
        
        JLabel lblTitulo = new JLabel("Lista de asistencia de empleados");
        lblTitulo.setFont(new Font("Arial",1,16));
        lblTitulo.setForeground(Color.decode("#37718e"));
        
        lblFecha = new JLabel();
        tabla = new JTable();
        btnRegistrar = new JButton("Registrar entrada/salida");
        tfClaveEmpleado = new JTextField(12);
        cbTipo = new JComboBox<>();
        
        tabla.setModel(new DefaultTableModel(new Object[]{"ClaveEmpleado","Nombre","HoraEntrada","HoraSalida"},0));
        lblFecha.setText(fecha.get(Calendar.DATE)+"/"+fecha.get(Calendar.MONTH)+"/"+fecha.get(Calendar.YEAR));
        btnRegistrar.setBackground(Color.decode("#7c98b3"));
        btnRegistrar.setIcon(new ImageIcon(this.getClass().getResource("/mx/edu/itch/isc/infocoming/iconos/entrar24.png")));
        
        this.add(lblTitulo);
        this.add(new JScrollPane(tabla));
        this.add(this.darPanel(),"center");
        
        this.pack();
        this.setLocationRelativeTo(null);
        
    }
    
    private JPanel darPanel(){
        JPanel panel = new JPanel(new MigLayout("wrap 2","[]15[]","[]10[]10[]15[]"));
        panel.setBackground(Color.decode("#accbe1"));
        cbTipo.addItem("ENTRADA");
        cbTipo.addItem("SALIDA");
        
        panel.add(new JLabel("Fecha: "));
        panel.add(lblFecha);
        panel.add(new JLabel("Clave del empleado: "));
        panel.add(tfClaveEmpleado);
        panel.add(cbTipo,"span 2,center");
        panel.add(btnRegistrar,"span 2,center");
        
        return panel;
    }

}
