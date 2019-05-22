/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author pacop
 */
public class DMReinscribirAlumno extends PantallaModal {
    public JLabel lblNombre,lblApellidoPaterno,lblApellidoMaterno,lblDomicilio,lblTitulo;
    public JTable tabla;
    public JTextField telefono;
    public JButton btnCancelar,btnModificar;
    
    public DMReinscribirAlumno(){
        super("Reinscribir Alumno", new MigLayout("wrap 2", "10[]5[]10", "10[]15[]15[]15[]15[]15[]15[150]10"));
        lblTitulo=new JLabel("Datos del alumno a modificar");
        lblNombre = new JLabel();
        lblApellidoPaterno = new JLabel();
        lblApellidoMaterno=new JLabel();
        lblDomicilio=new JLabel();
        telefono=new JTextField(12);
        btnCancelar = new JButton("Cancelar");
        btnModificar = new JButton("Modificar alumno");
        tabla=new JTable(new DefaultTableModel(new Object []{"Horario","Curso"},10));
        lblTitulo.setFont(new Font("Arial",1,16));
        this.add(lblTitulo,"span 2");
        this.add(new JLabel("Nombre: "));
        this.add(lblNombre);
        this.add(new JLabel("Apellido paterno: "));
        this.add(lblApellidoPaterno);
        this.add(new JLabel("Apellido materno: "));
        this.add(lblApellidoMaterno);
        this.add(new JLabel("Domicilio: "));
        this.add(lblDomicilio);
        this.add(new JLabel("Telefono: "),"split 2");
        this.add(telefono,"wrap");
        this.add(new JScrollPane(tabla),"span 2 ");
        this.add(btnCancelar);
        this.add(btnModificar);
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
}
