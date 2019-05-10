/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author pacop
 */
public class VGestionPersonal extends JFrame {

    public VGestionPersonal() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Gestion Personal");
        this.setLayout(new MigLayout("debug,wrap 3","[][][]","10[]15[100]10[]15[][][][][][]"));
        JLabel etiqueta1= new JLabel("etiqueta1");
        JLabel etiqueta2= new JLabel("etiqueta2");
        JLabel etiqueta3= new JLabel("etiqueta3");
        JLabel etiqueta4= new JLabel("etiqueta4");
        JLabel etiqueta5= new JLabel("etiqueta5");
        JLabel etiqueta6= new JLabel("etiqueta6");
        JTable tabla=new JTable();
        JButton boton=new JButton("boton");
        etiqueta1.setFont(new Font("Arial",1,20));
        etiqueta2.setFont(new Font("Arial",1,20));
        this.add(etiqueta1,"span 3 1");
        this.add(new JScrollPane(tabla),"span 3 1");
        this.add(etiqueta2,"span 3 1");
        this.add(etiqueta3,"span 3 1");
        this.add(etiqueta4,"span 3 1");
        this.add(etiqueta5,"span 3 1");
        this.add(etiqueta6,"span 3 1");
        this.add(boton,"span 3 1,right");
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    
}
