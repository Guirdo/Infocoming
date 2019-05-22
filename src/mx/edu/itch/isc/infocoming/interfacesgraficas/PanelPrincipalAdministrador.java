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
public class PanelPrincipalAdministrador extends Pantalla {

    public JButton etiqueta1, etiqueta2, etiqueta3, etiqueta4, etiqueta5, etiqueta6, etiqueta7, etiqueta8, etiqueta9, titulo5, etiqueta10, etiqueta11;
    private JLabel titulo1, titulo2, titulo3, titulo4;

    public PanelPrincipalAdministrador() {
        super("Panel Principal", new MigLayout("wrap 2", "20[150,center]50[150,center]20", "20[100]20[100]20[100]20"));
        titulo1 = new JLabel("Gestión del alumnado");
        titulo2 = new JLabel("Gestión del personal");
        titulo3 = new JLabel("Gestión del usuarios");
        etiqueta1 = new JButton("Incribir Alumno");
        etiqueta2 = new JButton("Visualizar Alumno");
        etiqueta3 = new JButton("Reinscribir Alumno");
        etiqueta4 = new JButton("Generar Documentos");
        etiqueta5 = new JButton("Escanear Documentos");
        etiqueta6 = new JButton("Agendar Examen CENNI");
        etiqueta7 = new JButton("Dar baja Alumno");
        etiqueta8 = new JButton("Registrar Empleado");
        etiqueta9 = new JButton("Modificar Contraseña");
        etiqueta10 = new JButton("Registrar Pago");
        etiqueta11 = new JButton("Visualizar pagos");
        titulo4 = new JLabel("Gestión de Pagos");
        titulo5 = new JButton("Gestión de Grupos");
        titulo1.setFont(new Font("Arial", 1, 16));
        titulo1.setForeground(Color.decode("#37718e"));
        titulo2.setFont(new Font("Arial", 1, 16));
        titulo2.setForeground(Color.decode("#37718e"));
        titulo3.setFont(new Font("Arial", 1, 16));
        titulo3.setForeground(Color.decode("#37718e"));
        titulo4.setFont(new Font("Arial", 1, 16));
        titulo4.setForeground(Color.decode("#37718e"));
        titulo5.setFont(new Font("Arial", 1, 16));
        titulo5.setForeground(Color.decode("#37718e"));
        etiqueta1.setFont(new Font("Arial", 2, 12));
        etiqueta1.setForeground(Color.decode("#7c98b3"));
        etiqueta2.setFont(new Font("Arial", 2, 12));
        etiqueta2.setForeground(Color.decode("#7c98b3"));
        etiqueta3.setFont(new Font("Arial", 2, 12));
        etiqueta3.setForeground(Color.decode("#7c98b3"));
        etiqueta4.setFont(new Font("Arial", 2, 12));
        etiqueta4.setForeground(Color.decode("#7c98b3"));
        etiqueta5.setFont(new Font("Arial", 2, 12));
        etiqueta5.setForeground(Color.decode("#7c98b3"));
        etiqueta6.setFont(new Font("Arial", 2, 12));
        etiqueta6.setForeground(Color.decode("#7c98b3"));
        etiqueta7.setFont(new Font("Arial", 2, 12));
        etiqueta7.setForeground(Color.decode("#7c98b3"));
        etiqueta8.setFont(new Font("Arial", 2, 12));
        etiqueta8.setForeground(Color.decode("#7c98b3"));
        etiqueta9.setFont(new Font("Arial", 2, 12));
        etiqueta9.setForeground(Color.decode("#7c98b3"));
        etiqueta10.setFont(new Font("Arial", 2, 12));
        etiqueta10.setForeground(Color.decode("#7c98b3"));
        etiqueta11.setFont(new Font("Arial", 2, 12));
        etiqueta11.setForeground(Color.decode("#7c98b3"));        
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
        JLabel iconou = new JLabel();
        Image iu = new ImageIcon(this.getClass().getResource("/mx/edu/itch/isc/infocoming/iconos/gestionUsuario64.png")).getImage();
        iconou.setIcon(new ImageIcon(iu));
        JPanel menu1 = new JPanel(new MigLayout("wrap", ""));
        menu1.add(iconoA, "center");
        menu1.add(titulo1);
        menu1.add(etiqueta1);
        menu1.add(etiqueta2);
        menu1.add(etiqueta3);
        menu1.add(etiqueta4);
        menu1.add(etiqueta5);
        menu1.add(etiqueta6);
        menu1.add(etiqueta7);
        menu1.setBackground(Color.decode("#f5f6fa"));
        this.add(menu1, "cell 0 0");
        JPanel menu2 = new JPanel(new MigLayout("wrap", ""));
        menu2.add(iconoP, "center");
        menu2.add(titulo2);
        menu2.add(etiqueta8);
        menu2.setBackground(Color.decode("#f5f6fa"));
        this.add(menu2, "cell 1 0");
        JPanel menu3 = new JPanel(new MigLayout("wrap", ""));
        menu3.add(iconou, "center");
        menu3.add(titulo3);
        menu3.add(etiqueta9);
        menu3.setBackground(Color.decode("#f5f6fa"));
        this.add(menu3, "cell 0 1");
        JPanel menu4 = new JPanel(new MigLayout("wrap", ""));
        menu4.add(iconoPa, "center");
        menu4.add(titulo4);
        menu4.add(etiqueta10);
        menu4.add(etiqueta11);
        menu4.setBackground(Color.decode("#f5f6fa"));
        this.add(menu4, "cell 1 1");
        JPanel menu5 = new JPanel(new MigLayout("wrap", ""));
        menu5.add(iconog, "center");
        menu5.add(titulo5);  
        menu5.setBackground(Color.decode("#f5f6fa"));
        this.add(menu5, "cell 0 2");
        this.pack();
        this.setLocationRelativeTo(null);

    }
}
