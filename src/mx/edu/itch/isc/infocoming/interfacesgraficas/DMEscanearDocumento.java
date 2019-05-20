/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.utilidades.TextPrompt;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author diann
 */
public class DMEscanearDocumento extends PantallaModal{
    public JLabel titulo1, titulo2, titulo3, matricula, nombre, curso, semanas, tipoDoc,lblRuta;
    public JTextField buscar,tfMatricula;
    public JPanel panel;
    public JButton btn1, btn2;
    public JTable tabla;
    public JRadioButton acta, curp, ide;
    public JFileChooser fcSelector;
    
    
    public DMEscanearDocumento() {
        super("Escanear documentos del alumno: ",  new MigLayout("wrap 3", "[][150][]","10[]15[]10[150]10[]10[]10[]10[]10[]10[]10"));
        
        titulo1 = new JLabel("Alumnos inscritos");
        titulo1.setFont(new Font("Arial",1,16));
        titulo1.setForeground(Color.decode("#37718e"));
        titulo2 = new JLabel("Datos del alumno");
        titulo2.setFont(new Font("Arial",1,15));
        titulo2.setForeground(Color.decode("#7c98b3"));
        titulo3 = new JLabel("Agregar documentación");
        titulo3.setFont(new Font("Arial",1,15));
        titulo3.setForeground(Color.decode("#7c98b3"));
        matricula= new JLabel();
        //matricula.setFont(new Font("Arial",1,12));
        nombre = new JLabel();
        //nombre.setFont(new Font("Arial",2,12));
        curso = new JLabel();
        //curso.setFont(new Font("Arial",2,12));
        semanas = new JLabel();
        //semanas.setFont(new Font("Arial",2,12));
        buscar= new JTextField(15);
        buscar.setFont(new Font("Arial",2,12));
        tabla = new JTable(new DefaultTableModel(new Object[]{"Tipo Documento","Ruta", "Matrícula", "Nombre"},4));
        lblRuta = new JLabel();
        tfMatricula = new JTextField(12);
        fcSelector = new JFileChooser();
        
        fcSelector.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo PDF", "pdf");
        fcSelector.setFileFilter(filtro);
        
        //Buscar
        TextPrompt b = new TextPrompt("Nombre alumno", buscar);
        b.changeAlpha(0.75f);
        b.changeStyle(Font.ITALIC);
        
        //Panel
        tipoDoc= new JLabel("Tipo de documento: ");
        acta= new JRadioButton("Acta de nacimiento");
        acta.setSelected(true);
        curp= new JRadioButton("CURP");
        ide= new JRadioButton("Credencial estudiantil");
        btn1= new JButton("Seleccionar archivo");
        btn2= new JButton("Escanear documento");
        btn2.setBackground(Color.decode("#7c98b3"));
        panel = new JPanel(new MigLayout("wrap 2", "[][::170]", "[]10[][][][][]"));
        panel.setBackground(Color.decode("#accbe1"));
        
        ButtonGroup group = new ButtonGroup();
        group.add(acta);
        group.add(curp);
        group.add(ide);
        
        panel.add(new JLabel("Matricula del Alumno: "));
        panel.add(tfMatricula);
        panel.add(btn1,"cell 0 1");
        panel.add(lblRuta,"cell 1 1");
        panel.add(acta, "cell 0 2");
        panel.add(curp, "cell 0 3");
        panel.add(ide, "cell 0 4");
        panel.add(btn2, "cell 1 5");
        
        this.add(titulo1, "span 3 1");
        this.add(buscar, "span 3 1, right");
        this.add(new JScrollPane(tabla),"span 3,growx");
        this.add(titulo2, "cell 0 4");
        this.add(new JLabel("Matrícula: "), "cell 0 5");
        this.add(matricula);
        this.add(new JLabel("Nombre: "), "cell 0 6");
        this.add(nombre);
        this.add(new JLabel("Curso: "), "cell 0 7");
        this.add(curso);
        this.add(new JLabel("Horario: "), "cell 0 8");
        this.add(semanas);
        this.add(titulo3, "cell 2 4");
        this.add(panel, "span 1 4");
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
}
