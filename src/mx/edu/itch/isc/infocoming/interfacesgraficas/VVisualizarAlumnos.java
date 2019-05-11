package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class VVisualizarAlumnos extends Pantalla{

    public VVisualizarAlumnos() {
        super("Alumnos inscritos",new MigLayout("wrap 3","[][][]","[]15[]10[150]20[]15[]10[]10[]10[]"));
        
        JLabel titulo = new JLabel("Alumnos inscritos");
        titulo.setFont(new Font("Arial",1,16));
        JTextField tfBuscar = new JTextField("Nombre del alumno");
        JTable tabla = new JTable(new DefaultTableModel(new Object[]{"Matrícula","Nombre"},2 ));
        JLabel titulo1 = new JLabel("Datos del alumno");
        titulo1.setFont(new Font("Arial",1,16));
        
        JLabel lblMatricula = new JLabel();
        JLabel lblNombre = new JLabel();
        JLabel lblCurso = new JLabel();
        JLabel lblHorario = new JLabel();
        
        this.add(titulo,"span 3");
        this.add(tfBuscar,"span 3,right");
        this.add(new JScrollPane(tabla),"span 3");
        this.add(titulo1,"span 3");
        this.add(new JLabel("Matrícula"));
        this.add(lblMatricula,"wrap");
        this.add(new JLabel("Nombre"));
        this.add(lblNombre,"wrap");
        this.add(new JLabel("Curso"));
        this.add(lblCurso,"wrap");
        this.add(new JLabel("Horario"));
        this.add(lblHorario,"wrap");
        
        this.pack();
        this.setLocationRelativeTo(null);
    }

}
