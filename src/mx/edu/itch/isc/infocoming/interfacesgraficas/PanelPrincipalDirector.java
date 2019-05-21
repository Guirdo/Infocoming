/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author diann
 */
public class PanelPrincipalDirector extends Pantalla{
    
    public JLabel titulo1, titulo2;
    public JButton visualizar, bajaA, registrarE, btnVisualizarPersonal;
    public JPanel panel1, panel2;
    public PanelPrincipalDirector(){
        
        super("Director",new MigLayout("debug, wrap 3", "100[:100:]100[:100:]100","100[100][:100:][100]100" ));
        
        titulo1 = new JLabel ("Gestión del alumnado");
        titulo1.setFont(new Font("Arial",2,16));
        titulo1.setForeground(Color.decode("#37718e"));
        titulo2 = new JLabel  ("Gestión de Personal");
        titulo2.setFont(new Font("Arial",2,16));
        titulo2.setForeground(Color.decode("#37718e"));
        visualizar = new JButton ("Visualizar alumnos");
        visualizar.setFont(new Font("Arial",2,15));
        visualizar.setForeground(Color.decode("#7c98b3"));
        bajaA = new JButton ("Dar baja a alumno");
        bajaA.setFont(new Font("Arial",2,15));
        bajaA.setForeground(Color.decode("#7c98b3"));
        registrarE = new JButton ("Registrar empleado");
        registrarE.setFont(new Font("Arial",2,15));
        registrarE.setForeground(Color.decode("#7c98b3"));
        btnVisualizarPersonal = new JButton ("Visualizar personal");
        btnVisualizarPersonal.setFont(new Font("Arial",2,15));
        btnVisualizarPersonal.setForeground(Color.decode("#7c98b3"));
        
        JLabel iconoA = new JLabel();
        Image img = new ImageIcon(this.getClass().getResource("/mx/edu/itch/isc/infocoming/iconos/gestionAlumno64.png")).getImage();
        iconoA.setIcon(new ImageIcon(img));
        
        JLabel iconoP = new JLabel();
        Image imgn = new ImageIcon(this.getClass().getResource("/mx/edu/itch/isc/infocoming/iconos/gestionPersonal64.png")).getImage();
        iconoP.setIcon(new ImageIcon(imgn));
        
        
        panel1 = new JPanel(new MigLayout("wrap", "[]", "[]10[]15[]10[]"));
        panel2 = new JPanel(new MigLayout("wrap", "[]", "[]10[]15[]10[]"));
        panel1.setBackground(Color.decode("#f5f6fa"));
        panel2.setBackground(Color.decode("#f5f6fa"));
        
        panel1.add(iconoA,"w 64, h 64,span 2, center");
        panel1.add(titulo1);
        panel1.add(visualizar);
        panel1.add(bajaA);
        
        panel2.add(iconoP,"w 64, h 64,span 2, center");
        panel2.add(titulo2);
        panel2.add(registrarE);
        panel2.add(btnVisualizarPersonal);
        
        this.add(panel1, "cell 0 1");
        this.add(panel2, "cell 1 1");
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
}
