/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.utilidades.TextPrompt;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author diann
 */
public class DMGenerarDocumentos extends PantallaModal{
    
    public JLabel titulo1, titulo2, titulo3, matricula, nombre, curso, semanas, tipoDoc;
    public JTextField buscar;
    public JPanel panel;
    public JCheckBox constancia ,certificado, diploma;
    public JButton btn1, btn2;
    public JTable tabla;
    
    
    public DMGenerarDocumentos() {
        super("Generar documentos institucionales",  new MigLayout("debug, wrap 3", "[][150][]","10[]15[]10[150]10[]10[]10[]10[]10[]10[]10"));
        
        titulo1 = new JLabel("Alumnos inscritos");
        titulo1.setFont(new Font("Arial",1,16));
        titulo1.setForeground(Color.decode("#37718e"));
        titulo2 = new JLabel("Datos del alumno");
        titulo2.setFont(new Font("Arial",1,15));
        titulo2.setForeground(Color.decode("#7c98b3"));
        titulo3 = new JLabel("Generar documentación");
        titulo3.setFont(new Font("Arial",1,15));
        titulo3.setForeground(Color.decode("#7c98b3"));
        matricula= new JLabel("Matrícula: ");
        matricula.setFont(new Font("Arial",2,12));
        nombre = new JLabel("Nombre: ");
        nombre.setFont(new Font("Arial",2,12));
        curso = new JLabel("Curso: ");
        curso.setFont(new Font("Arial",2,12));
        semanas = new JLabel("Semanas pagadas: ");
        semanas.setFont(new Font("Arial",2,12));
        buscar= new JTextField(15);
        buscar.setFont(new Font("Arial",2,12));
        tabla = new JTable(new DefaultTableModel(new Object[]{"Matrícula", "Nombre"},2));
        //Panel
        tipoDoc = new JLabel("Tipo de documento:");
        constancia = new JCheckBox("Constancia");
        certificado = new JCheckBox("Certificado");
        diploma= new JCheckBox("Diploma");
        btn1= new JButton("Seleccionar archivo");
        btn2= new JButton("Generar documento");
        btn2.setBackground(Color.decode("#7c98b3"));
        panel = new JPanel(new MigLayout("debug, wrap 2", "[][]", "[]10[][][][][]"));
        panel.setBackground(Color.decode("#accbe1"));
        //Buscar
        TextPrompt b = new TextPrompt("Nombre alumno", buscar);
        b.changeAlpha(0.75f);
        b.changeStyle(Font.ITALIC);
        
        panel.add(btn1,"cell 0 0");
        panel.add(constancia, "cell 0 1");
        panel.add(certificado, "cell 0 2");
        panel.add(diploma, "cell 0 3");
        panel.add(btn2, "cell 1 4");
        
        this.add(titulo1, "span 3 1");
        this.add(buscar, "span 3 1, right");
        this.add(new JScrollPane(tabla),"span 3 1");
        this.add(titulo2, "cell 0 4");
        this.add(matricula, "cell 0 5");
        this.add(new JLabel(""));
        this.add(nombre, "cell 0 6");
        this.add(new JLabel(""));
        this.add(curso, "cell 0 7");
        this.add(new JLabel(""));
        this.add(semanas, "cell 0 8");
        this.add(new JLabel(""));
        this.add(titulo3, "cell 2 4");
        this.add(panel, "span 1 4");
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
}
