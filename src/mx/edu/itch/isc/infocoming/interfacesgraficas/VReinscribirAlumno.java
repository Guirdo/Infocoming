package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import mx.edu.itch.isc.infocoming.utilidades.TextPrompt;
import net.miginfocom.swing.MigLayout;

public class VReinscribirAlumno extends Pantalla{
    
    public JTextField tfBuscar;
    public JTable tabla;
    public JLabel lblNombre,lblCurso,lblApePat,lblApeMat,lblHorario;
    public JButton btnReinscribir;
    

    public VReinscribirAlumno() {
        super("Reinscribir alumno",new MigLayout("wrap 3","[]15[200]15[]","[]15[]10[150]20[]15[]10[]10[]10[]10[]"));
        
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        JLabel lblTitulo1 = new JLabel("Alumnos inscritos");
        JLabel lblTitulo2 = new JLabel("Datos del alumno");
        
        lblTitulo1.setFont(new Font("Arial",1,16));
        lblTitulo2.setFont(new Font("Arial",1,16));
        
        tfBuscar=new JTextField(12);
        tabla = new JTable();
        lblNombre = new JLabel();
        lblCurso= new JLabel();
        lblApePat = new JLabel();
        lblApeMat=new JLabel();
        lblHorario = new JLabel();
        btnReinscribir= new JButton("Reinscribir alumno");
        
        btnReinscribir.setBackground(Color.decode("#c3c3c3"));
        btnReinscribir.setIcon(new ImageIcon(this.getClass().getResource("/mx/edu/itch/isc/infocoming/iconos/modificar24.png")));
        TextPrompt ph = new TextPrompt("Apellido o matricula...", tfBuscar);
        ph.changeAlpha(0.75f);
        ph.changeStyle(Font.ITALIC);
        
        this.add(lblTitulo1,"span 3");
        this.add(tfBuscar,"span 3,right");
        this.add(new JScrollPane(tabla),"span 3,growx");
        this.add(lblTitulo2,"span 3");
        this.add(new JLabel("Nombre: "));
        this.add(lblNombre,"wrap");
        this.add(new JLabel("Apellido Paterno: "));
        this.add(lblApePat,"wrap");
        this.add(new JLabel("Apellido Materno: "));
        this.add(lblApeMat,"wrap");
        this.add(new JLabel("Curso: "));
        this.add(lblCurso,"wrap");
        this.add(new JLabel("Horario: "));
        this.add(lblHorario);
        this.add(btnReinscribir);
        
        this.pack();
        this.setLocationRelativeTo(null);
    }

}
