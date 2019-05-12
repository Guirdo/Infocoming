/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author pacop
 */
public class VGestionPersonal extends Pantalla {
     private JLabel titulo1, titulo2, claveEmpleado, nombre, domicilio,telefono;
      private JButton btn;
    private JTable tabla;

    public VGestionPersonal() {
        super("Gestion Personal", new MigLayout("debug,wrap 3","[][][]","10[]15[100]10[]15[][][][][][]"));
        titulo1= new JLabel("Persona que labora en la empresa");
        titulo2= new JLabel("Datos del empleado");
        claveEmpleado= new JLabel("Clave empleado:");
        nombre= new JLabel("Nombre:");
        domicilio= new JLabel("Domicilio:");
        telefono= new JLabel("Telefono:");
        tabla=new JTable();
        btn=new JButton("Dar de baja");
        titulo1.setFont(new Font("Arial",1,20));
        titulo2.setFont(new Font("Arial",1,20));
        claveEmpleado.setFont(new Font("Arial",2,12));
        nombre.setFont(new Font("Arial",2,12));
        domicilio.setFont(new Font("Arial",2,12));
        telefono.setFont(new Font("Arial",2,12));
        this.add(titulo1,"span 3 1");
        this.add(new JScrollPane(tabla),"span 3 1");
        this.add(titulo2,"span 3 1");
        this.add(claveEmpleado,"span 3 1");
        this.add(nombre,"span 3 1");
        this.add(domicilio,"span 3 1");
        this.add(telefono,"span 3 1");
        this.add(btn,"span 3 1,right");
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
