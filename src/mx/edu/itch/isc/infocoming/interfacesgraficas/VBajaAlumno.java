/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Font;
import java.awt.TextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;
//import net.miginfocom.swing.MigLayout;

/**
 *
 * @author diann
 */
public class VBajaAlumno extends Pantalla{
    
    private JLabel titulo1, titulo2, matricula, nombre, curso;
    private TextField buscar;
    private JButton btn;
    private JTable tabla;
    
    public VBajaAlumno(){
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setTitle("Baja Alumno.");
        super("Baja alumno",new MigLayout("debug, wrap 3", "[][][]","[]15[][200]20[]5[]5[]5[][]20" ) );
//        this.setLayout(new MigLayout("debug, wrap 3", "[][][]","[]15[][200]20[]5[]5[]5[][]20" ));
        
        titulo1 = new JLabel ("Alumnos inscritos");
        titulo1.setFont(new Font("Arial",2,16));
        titulo2 = new JLabel ("Datos del alumno");
        titulo2.setFont(new Font("Arial",2,14));
        matricula = new JLabel ("Matrícula: ");
        matricula.setFont(new Font("Arial",2,12));
        nombre = new JLabel ("Nombre: ");
        nombre.setFont(new Font("Arial",2,12));
        curso = new JLabel ("Curso: ");
        curso.setFont(new Font("Arial",2,12));
        buscar = new TextField("Nombre alumno..");
        buscar.setFont(new Font("Arial",2,12));
        tabla = new JTable();
        btn = new JButton("Dar de baja");

        
        this.add(titulo1,"span 3 1");
        this.add(buscar, "span 3 1, right");
        this.add(new JScrollPane(tabla),"span 3 1");
        this.add(titulo2, "span 3 1");
        this.add(matricula,"wrap");
        this.add(nombre,"wrap");
        this.add(curso, "wrap");
        this.add(btn, "span 3 1, right");
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
