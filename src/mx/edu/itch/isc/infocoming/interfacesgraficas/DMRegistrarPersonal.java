/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author pacop
 */
public class DMRegistrarPersonal extends PantallaModal{
    private JLabel lblTelefono,lblTitulo,inicioContrato,tipoEmp;
    private JTable tabla;
    public JTextField telefono,nombre,apellidoPaterno,apellidoMaterno,domicilio,cargo;
    public JRadioButton docente,administrativo;
    private JButton btnCancelar,btnModificar;
    
    public DMRegistrarPersonal(){
        super("Registro de empleado", new MigLayout("wrap 1", "10[]10", "10[]15[15]15[]15[]15[]15[]15[]15[]10"));
        lblTitulo=new JLabel("Datos del alumno a modificar");
        docente=new JRadioButton("Docente");
        administrativo=new JRadioButton("Administrativo");
        ButtonGroup vg=new ButtonGroup();
        vg.add(docente);
        vg.add(administrativo);
        tipoEmp=new JLabel();
        cargo=new JTextField(12);
        nombre=new JTextField(12);
        apellidoPaterno=new JTextField(12);
        apellidoMaterno=new JTextField(12);
        domicilio=new JTextField(12);
        telefono=new JTextField(12);
        inicioContrato=new JLabel();
        btnCancelar = new JButton("Cancelar");
        btnModificar = new JButton("Modificar alumno");
        lblTitulo.setFont(new Font("Arial",1,16));
        this.add(lblTitulo);
        this.add(new JLabel("Nombre: "),"split 2");
        this.add(nombre,"wrap");
        this.add(new JLabel("Apellido paterno: "),"split 2");
        this.add(apellidoPaterno,"wrap");
        this.add(new JLabel("Apellido materno: "),"split 2");
        this.add(apellidoMaterno,"wrap");
        this.add(new JLabel("Domicilio: "),"split 2");
        this.add(domicilio,"wrap");
        this.add(new JLabel("Telefono: "),"split 2");
        this.add(telefono,"wrap");
        this.add(new JLabel("Tipo de empleado: "));
        this.add(docente,"split 2");
        this.add(administrativo);
        this.add(new JLabel("Cargo: "),"split 2");
        this.add(cargo,"wrap");
        this.add(new JLabel("Inicio de contrato: "));
        this.add(inicioContrato);
        this.add(btnCancelar);
        this.add(btnModificar);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
