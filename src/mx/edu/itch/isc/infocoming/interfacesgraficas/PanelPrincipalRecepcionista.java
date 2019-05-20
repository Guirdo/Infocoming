/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author pacop
 */
public class PanelPrincipalRecepcionista extends Pantalla{
  
    public JButton etiqueta1;
    public JButton etiqueta2;  
      private JButton titulo3,titulo4;  
  private JLabel titulo1,titulo2;
    public PanelPrincipalRecepcionista(){
        super("Panel principal",new MigLayout("debug,wrap 2","100[150]50[150]100","20[150]20[100]20[100]20"));
        titulo1= new JLabel("Gestión del alumnado");
        titulo2= new JLabel("Gestión del personal");     
        etiqueta1=new JButton("Visualizar Alumno");
        etiqueta2=new JButton("Registrar entarda y salida del personal");
        titulo3=new JButton("Gestión de Pagos");
        titulo4=new JButton("Gestión de Grupos");
        titulo1.setFont(new Font("Arial",1,16));
        titulo2.setFont(new Font("Arial",1,16));
        titulo3.setFont(new Font("Arial",1,16));
        titulo4.setFont(new Font("Arial",1,16));
        etiqueta1.setFont(new Font("Arial",2,12));
        etiqueta2.setFont(new Font("Arial",2,12));
        JPanel panel1=new JPanel(new MigLayout("wrap","",""));
        panel1.add(titulo1);
        panel1.add(etiqueta1);
        this.add(panel1,"cell 0 0");
        JPanel panel2=new JPanel(new MigLayout("wrap","",""));
        panel2.add(titulo2);
        panel2.add(etiqueta2);
        this.add(panel2,"cell 1 0");
         JPanel panel3=new JPanel(new MigLayout("wrap","",""));
        panel3.add(titulo3);
        this.add(panel3,"cell 0 1");
        JPanel panel4=new JPanel(new MigLayout("wrap","",""));
        panel4.add(titulo4);
        this.add(panel4,"cell 1 1");
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
}
