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
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author diann
 */
public class DMInscribirAlumno extends PantallaModal{
    
    public JLabel titulo, nombre, apellidoP, apellidoM, domicilio, telefono, horario;
    public JTextField nombres, apeP, apeM, dom, tel;
    public JTable tabla;
    public JButton btn1, btn2;
    
    public DMInscribirAlumno() {
        super("Inscribir alumno", new MigLayout("wrap 4", "[][][][]","10[]15[]10[]10[]10[]10[]10[]10"));
        
        titulo = new JLabel("Datos del alumno de nuevo ingreso");
        titulo.setFont(new Font("Arial",1,16));
        titulo.setForeground(Color.decode("#37718e"));
        nombre= new JLabel("Nombre(s): ");
        nombre.setFont(new Font("Arial",2,12));
        apellidoP = new JLabel("Apellido paterno: ");
        apellidoP.setFont(new Font("Arial",2,12));
        apellidoM = new JLabel("Apellido materno: ");
        apellidoM.setFont(new Font("Arial",2,12));
        domicilio = new JLabel("Domicilio: ");
        domicilio.setFont(new Font("Arial",2,12));
        telefono = new JLabel("Teléfono: ");
        telefono.setFont(new Font("Arial",2,12));
        horario = new JLabel("Horario: ");
        horario.setFont(new Font("Arial",2,12));
        nombres = new JTextField(15);
        nombres.setFont(new Font("Arial",2,12));
        apeP= new JTextField(15);
        apeP.setFont(new Font("Arial",2,12));
        apeM = new JTextField(15);
        apeM.setFont(new Font("Arial",2,12));
        dom= new JTextField(25);
        dom.setFont(new Font("Arial",2,12));
        tel = new JTextField(15);
        tabla = new JTable();
        tabla.setModel(new DefaultTableModel(new Object[]{"Horario", "Curso"},2));
        btn1 = new JButton("Inscribir Alumno");
        btn1.setBackground(Color.decode("#7c98b3"));
        btn2 = new JButton("Cancelar");
        btn2.setBackground(Color.decode("#cee5f2"));
        
        this.add(titulo, "span 4 1");
        this.add(nombre, "cell 0 1");
        this.add(nombres, "cell 1 1");
        this.add(apellidoP, "cell 0 2");
        this.add(apeP, "cell 1 2");
        this.add(apellidoM,"cell 2 2");
        this.add(apeM, "cell 4 2");
        this.add(domicilio, "cell 0 3");
        this.add(dom, "cell 1 3");
        this.add(telefono, "cell 0 4");
        this.add(tel, "cell 1 4");
        this.add(horario, "cell 0 5");
        this.add(new JScrollPane(tabla),"w 200, h 100,span 4 1");
        this.add(btn1, "cell 1 7, center");
        this.add(btn2, "cell 2 7");
        this.pack();
        this.setLocationRelativeTo(null);
        
        
    }
    
    
    
}
