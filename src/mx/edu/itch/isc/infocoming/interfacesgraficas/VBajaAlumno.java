/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.utilidades.TextPrompt;
import net.miginfocom.swing.MigLayout;
//import net.miginfocom.swing.MigLayout;

/**
 *
 * @author diann
 */
public class VBajaAlumno extends Pantalla{
    
    public JLabel titulo1, titulo2, matricula, nombre, curso;
    public JTextField buscar;
    public JButton btn;
    public JTable tabla;
    
    public VBajaAlumno(){
        super("Baja alumno",new MigLayout("debug, wrap 3", "[][][]","10[]15[]10[150]20[]15[]10[]10[]10[]10" ));
        
        titulo1 = new JLabel ("Alumnos inscritos");
        titulo1.setFont(new Font("Arial",1,16));
        titulo1.setForeground(Color.decode("#37718e"));
        titulo2 = new JLabel ("Datos del alumno");
        titulo2.setFont(new Font("Arial",1,15));
        titulo2.setForeground(Color.decode("#7c98b3"));
        matricula = new JLabel ();
        matricula.setFont(new Font("Arial",2,12));
        nombre = new JLabel ();
        nombre.setFont(new Font("Arial",2,12));
        curso = new JLabel ();
        curso.setFont(new Font("Arial",2,12));
        buscar = new JTextField(10);
        buscar.setFont(new Font("Arial",2,12));
        tabla = new JTable();
        tabla.setModel(new DefaultTableModel(new Object[]{"Matrícula", "Nombre"},2));
        btn = new JButton("Dar de baja");
        btn.setBackground(Color.decode("#cee5f2"));
        
        TextPrompt b = new TextPrompt("Nombre alumno", buscar);
        b.changeAlpha(0.75f);
        b.changeStyle(Font.ITALIC);

        
        this.add(titulo1,"span 3");
        this.add(buscar, "span 3 1, right");
        this.add(new JScrollPane(tabla),"span 3");
        this.add(titulo2, "span 3");
        this.add(new JLabel("Matrícula: "));
        this.add(matricula,"wrap");
        this.add(new JLabel("Nombre: "));
        this.add(nombre,"wrap");
        this.add(new JLabel("Curso"));
        this.add(curso, "wrap");
        this.add(btn, "span 3 1, right");
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
