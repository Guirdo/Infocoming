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
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.manejadores.ManejadorBajaEmpleado;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author pacop
 */
public class VGestionPersonal extends Pantalla {
     public JLabel titulo1, titulo2, claveEmpleado, nombre, domicilio,telefono;
     public JButton btnBajaEmpleado;
    public JTable tabla;
    public JLabel lblNombre,lblClave,lblDomicilio,lblTelefono;

    public VGestionPersonal() {
        super("Gestion Personal", new MigLayout("debug,wrap 3","[][][]","20[]15[100]20[]15[]10[]10[]10[]10[]10[]"));
        titulo1= new JLabel("Persona que labora en la empresa");
        titulo2= new JLabel("Datos del empleado");
        claveEmpleado= new JLabel("Clave empleado:");
        nombre= new JLabel("Nombre:");
        domicilio= new JLabel("Domicilio:");
        telefono= new JLabel("Telefono:");
        tabla=new JTable(new DefaultTableModel(new Object []{"Clave Empleado","Nombre"},10));
        btnBajaEmpleado=new JButton("Dar de baja");
        titulo1.setFont(new Font("Arial",1,16));
        titulo2.setFont(new Font("Arial",1,15));
        claveEmpleado.setFont(new Font("Arial",0,12));
        nombre.setFont(new Font("Arial",0,12));
        domicilio.setFont(new Font("Arial",0,12));
        telefono.setFont(new Font("Arial",0,12));
        
        lblNombre = new JLabel();
        lblClave = new JLabel();
        lblDomicilio = new JLabel();
        lblTelefono = new JLabel();
        
        this.add(titulo1,"span 3 1");
        this.add(new JScrollPane(tabla),"span 3 1");
        this.add(titulo2,"span 3 1");
        this.add(claveEmpleado);
        this.add(lblClave,"wrap");
        this.add(nombre);
        this.add(lblNombre,"wrap");
        this.add(domicilio);
        this.add(lblDomicilio,"wrap");
        this.add(telefono);
        this.add(lblTelefono,"wrap");
        this.add(btnBajaEmpleado,"right");
        this.pack();
        this.setLocationRelativeTo(null);
    }

}
