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
 * @author pacop
 */
public class PanelPrincipalCoordinadorAcademico extends Pantalla {
    private JLabel titulo;
    public JButton visualizarAlumno,darBajaAlumno,registrarEvaluaciones;
    public PanelPrincipalCoordinadorAcademico() {
        super("Panel principal",new MigLayout("wrap 3","[100,center][:150:,center][100,center]","[100][150][100]"));
        titulo= new JLabel("Gestión del alumnado");
        visualizarAlumno=new JButton("Visualizar Alumno");
        darBajaAlumno= new JButton("Dar baja alumno");
        registrarEvaluaciones= new JButton("Registra evaluaciones");
        titulo.setFont(new Font("Arial",1,16));
        visualizarAlumno.setFont(new Font("Arial",2,12));
        darBajaAlumno.setFont(new Font("Arial",2,12));
        registrarEvaluaciones.setFont(new Font("Arial",2,12)); 
        titulo.setForeground(Color.decode("#37718e"));
        visualizarAlumno.setForeground(Color.decode("#7c98b3"));
        darBajaAlumno.setForeground(Color.decode("#7c98b3"));
        registrarEvaluaciones.setForeground(Color.decode("#7c98b3"));
        JLabel iconoA = new JLabel();
        Image img = new ImageIcon(this.getClass().getResource("/mx/edu/itch/isc/infocoming/iconos/gestionAlumno64.png")).getImage();
        iconoA.setIcon(new ImageIcon(img));        
        JPanel panel=new JPanel(new MigLayout("wrap","",""));
        panel.add(iconoA,"center");
        panel.add(titulo);        
        panel.add(visualizarAlumno);
        panel.add(darBajaAlumno);
        panel.add(registrarEvaluaciones);
        panel.setBackground(Color.decode("#f5f6fa"));
        this.add(panel,"cell 1 1");
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
