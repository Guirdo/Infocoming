/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
public class VGestionGrupo extends Pantalla{
    
    public JLabel titulo1, titulo2, horario, curso, maestro, semanas;
    public JTextField buscar;
    public JTable tabla;
    public JButton btn1, btn2;
    
    public VGestionGrupo() {
        super("Gestión de grupos", new MigLayout("debug, wrap 3", "[][][]","10[]15[]10[150]20[]15[]10[]10[]10[]10[]10[]10" ));
        
        titulo1 = new JLabel("Grupos");
        titulo1.setFont(new Font("Arial",1,16));
        titulo1.setForeground(Color.decode("#37718e"));
        titulo2 = new JLabel("Datos del grupo");
        titulo2.setFont(new Font("Arial",1,15));
        titulo2.setForeground(Color.decode("#7c98b3"));
        horario = new JLabel();
        horario.setFont(new Font("Arial",2,12));
        curso = new JLabel();
        curso.setFont(new Font("Arial",2,12));
        maestro = new JLabel();
        maestro.setFont(new Font("Arial",2,12));
        semanas = new JLabel();
        semanas.setFont(new Font("Arial",2,12));
        buscar = new JTextField(10);
        buscar.setFont(new Font("Arial",2,12));
        tabla = new JTable();
        tabla.setModel(new DefaultTableModel(new Object[]{"Días", "Horario", "Maestro asignado", "Num. Alumnos"},4));
        btn1 = new JButton("Crear grupo");
        btn1.setBackground(Color.decode("#7c98b3"));
        btn2 = new JButton("Imprimir lista");
        btn2.setBackground(Color.decode("#c3c3c3"));
        
        
        //Buscar
        TextPrompt b = new TextPrompt("Nombre alumno", buscar);
        b.changeAlpha(0.75f);
        b.changeStyle(Font.ITALIC);
        
        
        this.add(titulo1,"span 3 1");
        this.add(buscar,"span 3 1, right");
        this.add(new JScrollPane(tabla),"span 3 1");
        this.add(btn1,"span 3 1, right");
        this.add(titulo2, "span 3 1");
        this.add(new JLabel("Horario: "));
        this.add(horario,"wrap");
        this.add(new JLabel("Curso: "));
        this.add(curso,"wrap");
        this.add(new JLabel("Maestro asignado: "));
        this.add(maestro,"wrap");
        this.add(new JLabel("Semanas: "));
        this.add(semanas,"wrap");
        this.add(btn2, "span 3 1, right");
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    
    
}
