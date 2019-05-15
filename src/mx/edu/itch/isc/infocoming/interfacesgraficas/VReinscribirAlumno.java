package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Font;
import javax.swing.JButton;
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
        super("Reinscribir alumno",new MigLayout("wrap 3","[][200][]","[]15[]10[150]20[]15[]10[]10[]10[]10[]"));
        
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
        
        TextPrompt ph = new TextPrompt("Nombre del alumno...", tfBuscar);
        ph.changeAlpha(0.75f);
        ph.changeStyle(Font.ITALIC);
        
        this.add(lblTitulo1,"span 3");
        this.add(tfBuscar,"span 3,right");
        this.add(new JScrollPane(tabla),"span 3");
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
