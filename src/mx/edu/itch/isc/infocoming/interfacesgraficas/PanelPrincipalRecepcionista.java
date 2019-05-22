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
public class PanelPrincipalRecepcionista extends Pantalla {

    public JButton etiqueta1, etiqueta2, etiqueta3, etiqueta4,titulo4;
    private JLabel titulo1, titulo2,titulo3;

    public PanelPrincipalRecepcionista() {
        super("Panel principal", new MigLayout("wrap 2", "100[150,center]50[150,center]100", "20[150]20[100]20[100]20"));
        titulo1 = new JLabel("Gestión del alumnado");
        titulo2 = new JLabel("Gestión del personal");
        etiqueta1 = new JButton("Visualizar Alumno");
        etiqueta2 = new JButton("Registrar entrada y salida del personal");
        etiqueta3 = new JButton("Registrar Pago");
        etiqueta4 = new JButton("Visualizar historial de pagos");
        titulo3 = new JLabel("Gestión de Pagos");
        titulo4 = new JButton("Gestión de Grupos");
        titulo1.setFont(new Font("Arial", 1, 16));
        titulo2.setFont(new Font("Arial", 1, 16));
        titulo3.setFont(new Font("Arial", 1, 16));
        titulo4.setFont(new Font("Arial", 1, 16));
        etiqueta1.setFont(new Font("Arial", 2, 12));
        etiqueta2.setFont(new Font("Arial", 2, 12));
        etiqueta3.setFont(new Font("Arial", 2, 12));
        etiqueta4.setFont(new Font("Arial", 2, 12));
        titulo1.setForeground(Color.decode("#37718e"));
        titulo2.setForeground(Color.decode("#37718e"));
        titulo3.setForeground(Color.decode("#37718e"));
        titulo4.setForeground(Color.decode("#37718e"));
        etiqueta1.setForeground(Color.decode("#7c98b3"));
        etiqueta2.setForeground(Color.decode("#7c98b3"));
        etiqueta3.setForeground(Color.decode("#7c98b3"));
        etiqueta4.setForeground(Color.decode("#7c98b3"));
        JLabel iconoA = new JLabel();
        Image img = new ImageIcon(this.getClass().getResource("/mx/edu/itch/isc/infocoming/iconos/gestionAlumno64.png")).getImage();
        iconoA.setIcon(new ImageIcon(img));        
        JLabel iconoP = new JLabel();
        Image imgn = new ImageIcon(this.getClass().getResource("/mx/edu/itch/isc/infocoming/iconos/gestionPersonal64.png")).getImage();
        iconoP.setIcon(new ImageIcon(imgn));        
        JLabel iconog = new JLabel();
        Image ig = new ImageIcon(this.getClass().getResource("/mx/edu/itch/isc/infocoming/iconos/gestionGrupo64.png")).getImage();
        iconog.setIcon(new ImageIcon(ig));        
        JLabel iconoPa = new JLabel();
        Image ip = new ImageIcon(this.getClass().getResource("/mx/edu/itch/isc/infocoming/iconos/gestionPago64.png")).getImage();
        iconoPa.setIcon(new ImageIcon(ip));        
        JPanel panel1 = new JPanel(new MigLayout("wrap", "", ""));
        panel1.add(iconoA,"center");
        panel1.add(titulo1);
        panel1.add(etiqueta1);
        panel1.setBackground(Color.decode("#f5f6fa"));
        this.add(panel1, "cell 0 0");
        JPanel panel2 = new JPanel(new MigLayout("wrap", "", ""));
        panel2.add(iconoP,"center");
        panel2.add(titulo2);
        panel2.add(etiqueta2);
        panel2.setBackground(Color.decode("#f5f6fa"));
        this.add(panel2, "cell 1 0");
        JPanel panel3 = new JPanel(new MigLayout("wrap", "", ""));
        panel3.add(iconoPa,"center");
        panel3.add(titulo3);
        panel3.add(etiqueta3);
        panel3.add(etiqueta4);
        panel3.setBackground(Color.decode("#f5f6fa"));
        this.add(panel3, "cell 0 1");
        JPanel panel4 = new JPanel(new MigLayout("wrap", "", ""));
        panel4.add(iconog,"center");
        panel4.add(titulo4);
        panel4.setBackground(Color.decode("#f5f6fa"));
        this.add(panel4, "cell 1 1");
        this.pack();
        this.setLocationRelativeTo(null);
    }

}
