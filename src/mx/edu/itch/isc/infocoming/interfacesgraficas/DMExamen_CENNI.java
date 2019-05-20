/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author diann
 */
public class DMExamen_CENNI extends PantallaModal{
    public JLabel titulo, select;
    public JComboBox<String> examenes;
    public JTable tabla;
    public JButton btn;
    
    
    public DMExamen_CENNI() {
        super("Examen CENNI",new MigLayout("wrap 2", "[][]","10[]15[]10[]10[150]20[]10"));
        
        titulo= new JLabel("Candidatos examen CENNI");
        titulo.setFont(new Font("Arial",1,16));
        titulo.setForeground(Color.decode("#37718e"));
        select = new JLabel("Seleccione el examen: ");
        select.setFont(new Font("Arial",2,12));
        examenes = new JComboBox();
        tabla =new JTable();
        tabla.setModel(new DefaultTableModel(new Object[]{"Matrícula", "Nombre", "Curso",},3));
        btn= new JButton("Imprimir lista");
        btn.setBackground(Color.decode("#c3c3c3"));
        
        this.add(titulo,"span 2 1");
        this.add(select, "span 2 1");
        this.add(examenes, "span 2 1");
        this.add(new JScrollPane(tabla),"span 2 1");
        this.add(btn, "span 2 1, right");
        this.pack();
        this.setLocationRelativeTo(null);
        
    }
    
}
