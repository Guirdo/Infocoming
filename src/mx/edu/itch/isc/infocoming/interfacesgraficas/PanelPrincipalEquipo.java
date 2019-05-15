package mx.edu.itch.isc.infocoming.interfacesgraficas;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.miginfocom.swing.MigLayout;

public class PanelPrincipalEquipo extends Pantalla{
    
    public JButton btnConsultarAlu,btnConsultarPersonal;
    public JTable tabla;

    public PanelPrincipalEquipo() {
        super("Panel Principal Equipo", new MigLayout("wrap 2","[][]","[][]"));
        
        btnConsultarAlu=new JButton("Consultar alumnos");
        btnConsultarPersonal = new JButton("Consultar personal");
        tabla = new JTable();
        
        this.add(btnConsultarAlu);
        this.add(btnConsultarPersonal);
        this.add(new JScrollPane(tabla),"span 2");
        
        this.pack();
        this.setLocationRelativeTo(null);
    }

}
