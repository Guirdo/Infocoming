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
public class PanelPrincipalAdministrador extends Pantalla {
  public JButton etiqueta1,etiqueta2,etiqueta3,etiqueta4,etiqueta5,etiqueta6,etiqueta7,etiqueta8,etiqueta9,titulo5,etiqueta10,etiqueta11;  
  private JLabel titulo1,titulo2,titulo3,titulo4;
  public PanelPrincipalAdministrador(){
      super("Panel Principal",new MigLayout("debug,wrap 2","100[150]50[150]100","20[150]20[150]20[150]20"));
      titulo1= new JLabel("Gestión del alumnado");
      titulo2= new JLabel("Gestión del personal");
      titulo3= new JLabel("Gestión del usuarios");
      etiqueta1=new JButton("Incribir Alumno");
      etiqueta2=new JButton("Visualizar Alumno");
      etiqueta3=new JButton("Reinscribir Alumno");
      etiqueta4=new JButton("Generar Documentos");
      etiqueta5=new JButton("Escanear Documentos");
      etiqueta6=new JButton("Agendar Examen CENNI");
      etiqueta7=new JButton("Dar baja Alumno");
      etiqueta8=new JButton("Registrar Empleado");
      etiqueta9=new JButton("Modificar Contraseña");
      etiqueta10=new JButton("Registrar Pago");
      etiqueta11=new JButton("Visualizar pagos");
      titulo4=new JLabel("Gestión de Pagos");
      titulo5=new JButton("Gestión de Grupos");
      titulo1.setFont(new Font("Arial",1,16));
      titulo2.setFont(new Font("Arial",1,16));
      titulo3.setFont(new Font("Arial",1,16));
      titulo4.setFont(new Font("Arial",1,16));
      titulo5.setFont(new Font("Arial",1,16));
      etiqueta1.setFont(new Font("Arial",2,12));
      etiqueta2.setFont(new Font("Arial",2,12));
      etiqueta3.setFont(new Font("Arial",2,12));
      etiqueta4.setFont(new Font("Arial",2,12));
      etiqueta5.setFont(new Font("Arial",2,12));
      etiqueta6.setFont(new Font("Arial",2,12));
      etiqueta7.setFont(new Font("Arial",2,12));
      etiqueta8.setFont(new Font("Arial",2,12));
      etiqueta9.setFont(new Font("Arial",2,12));
      etiqueta10.setFont(new Font("Arial",2,12));
      etiqueta11.setFont(new Font("Arial",2,12));
      JPanel menu1=new JPanel(new MigLayout("wrap",""));
      menu1.add(titulo1);
      menu1.add(etiqueta1);
      menu1.add(etiqueta2);
      menu1.add(etiqueta3);
      menu1.add(etiqueta4);
      menu1.add(etiqueta5);
      menu1.add(etiqueta6);
      menu1.add(etiqueta7);
      this.add(menu1,"cell 0 0");
      JPanel menu2=new JPanel(new MigLayout("wrap",""));
      menu2.add(titulo2);
      menu2.add(etiqueta8);
      this.add(menu2,"cell 1 0");
      JPanel menu3=new JPanel(new MigLayout("wrap",""));
      menu3.add(titulo3);
      menu3.add(etiqueta9);
      this.add(menu3,"cell 0 1");
      JPanel menu4=new JPanel(new MigLayout("wrap",""));
      menu4.add(titulo4);
      menu4.add(etiqueta10);
      menu4.add(etiqueta11);
      this.add(menu4,"cell 1 1");
      JPanel menu5=new JPanel(new MigLayout("wrap",""));
      menu5.add(titulo5);
      this.add(menu5,"cell 0 2");
        this.pack();
        this.setLocationRelativeTo(null);


       
  }
}
