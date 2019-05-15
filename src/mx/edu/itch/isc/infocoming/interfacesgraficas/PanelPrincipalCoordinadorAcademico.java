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
public class PanelPrincipalCoordinadorAcademico extends Pantalla {
    private JLabel titulo;
    public JButton visualizarAlumno,darBajaAlumno,registrarEvaluaciones;
    public PanelPrincipalCoordinadorAcademico() {
        super("Panel principal",new MigLayout("debug,wrap 3","[150][:150:][150]","[150][150][150]"));
        titulo= new JLabel("Gestión del alumnado");
        visualizarAlumno=new JButton("Visualizar Alumno");
        darBajaAlumno= new JButton("Dar baja alumno");
        registrarEvaluaciones= new JButton("Registra evaluaciones");
        titulo.setFont(new Font("Arial",1,16));
        visualizarAlumno.setFont(new Font("Arial",2,12));
        darBajaAlumno.setFont(new Font("Arial",2,12));
        registrarEvaluaciones.setFont(new Font("Arial",2,12));     
        JPanel panel=new JPanel(new MigLayout("wrap","",""));
        panel.add(titulo);
        panel.add(visualizarAlumno);
        panel.add(darBajaAlumno);
        panel.add(registrarEvaluaciones);
        this.add(panel,"cell 1 1");
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
