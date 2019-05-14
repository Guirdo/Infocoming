package mx.edu.itch.isc.infocoming.manejadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBDEquipo;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalEquipo;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VValidarUsuario;

public class ManejadorPrincipal implements ActionListener {

    private InterfazBD intBD;

    private PanelPrincipalEquipo ppe;

    public ManejadorPrincipal() {
        new ManejadorValidarUsuario(new VValidarUsuario());
    }

    public ManejadorPrincipal(InterfazBD inter, PanelPrincipalEquipo p) {
        this.ppe = p;
        this.intBD = inter;

        ppe.btnConsultarAlu.addActionListener(this);
        ppe.btnConsultarPersonal.addActionListener(this);

        ppe.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ppe.btnConsultarAlu){
            try {
                this.manejaEventobtnConsultarAlumno();
            } catch (SQLException ex) {
                System.out.println("Error de consulta");
            }
        }else if(e.getSource() == ppe.btnConsultarPersonal){
            this.manejaEventobtnConsultarPersonal();
        }

    }

    private void manejaEventobtnConsultarAlumno() throws SQLException {
        Object[][] datos = new InterfazBDEquipo(intBD).consultarAlumnos();
        
        ppe.tabla.setModel(new DefaultTableModel(datos, new Object[]{"idAlumno","nombre","apePat","apeMat","Domicilio","Tel"}));
        
    }

    private void manejaEventobtnConsultarPersonal() {
        System.out.println("Estas consultado al personal");
    }

}
