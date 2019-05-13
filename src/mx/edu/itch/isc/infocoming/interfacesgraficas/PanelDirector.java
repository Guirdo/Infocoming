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
 * @author diann
 */
public class PanelDirector extends Pantalla{
    
    public PanelDirector(){
        
        super("Director",new MigLayout("debug, wrap 3", "100[:100:]100[:100:]100","100[100][:100:][100]100" ));
        
        JLabel titulo1 = new JLabel ("Gestión del alumnado");
        titulo1.setFont(new Font("Arial",2,16));
        JLabel titulo2 = new JLabel  ("Gestión de Personal");
        titulo2.setFont(new Font("Arial",2,16));
        JButton  visualizar = new JButton ("Visualizar alumnos");
        visualizar.setFont(new Font("Arial",2,15));
        JButton bajaA = new JButton ("Dar baja a alumno");
        bajaA.setFont(new Font("Arial",2,15));
        JButton  registrarE = new JButton ("Registrar empleado");
        registrarE.setFont(new Font("Arial",2,15));
        JButton bajaE = new JButton ("Dar baja a empleado");
        bajaE.setFont(new Font("Arial",2,15));
        
        JPanel panel1 = new JPanel(new MigLayout("debug, wrap", ""));
        JPanel panel2 = new JPanel(new MigLayout("debug, wrap", ""));
        
        panel1.add(titulo1);
        panel1.add(visualizar);
        panel1.add(bajaA);
        
        panel2.add(titulo2);
        panel2.add(registrarE);
        panel2.add(bajaE);
        
        this.add(panel1, "cell 0 1");
        this.add(panel2, "cell 1 1");
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
}
