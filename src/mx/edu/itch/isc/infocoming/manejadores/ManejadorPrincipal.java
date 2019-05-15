package mx.edu.itch.isc.infocoming.manejadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBDEquipo;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMInscribirAlumno;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalAdministrador;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalCoordinadorAcademico;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalDirector;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalEquipo;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalRecepcionista;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VReinscribirAlumno;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VValidarUsuario;

public class ManejadorPrincipal implements ActionListener {

    private InterfazBD intBD;

    private PanelPrincipalEquipo ppe = null;
    private PanelPrincipalAdministrador ppa = null;
    private PanelPrincipalCoordinadorAcademico ppc = null;
    private PanelPrincipalDirector ppd = null;
    private PanelPrincipalRecepcionista ppr = null;

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

    public ManejadorPrincipal(InterfazBD inter, PanelPrincipalAdministrador p) {
        this.ppa = p;
        this.intBD = inter;

        //Aqui van a ir los addActionListener de los botnes
        ppa.etiqueta3.addActionListener(this);
        ppa.etiqueta2.addActionListener(this);//vusualizar alumno

        ppa.setVisible(true);
    }

    public ManejadorPrincipal(InterfazBD inter, PanelPrincipalCoordinadorAcademico p) {
        this.ppc = p;
        this.intBD = inter;

        //Aqui van a ir los addActionListener de los botnes
        ppc.visualizarAlumno.addActionListener(this);//Visualizar alumno
        ppc.setVisible(true);
    }

    public ManejadorPrincipal(InterfazBD inter, PanelPrincipalDirector p) {
        this.ppd = p;
        this.intBD = inter;

        //Aqui van a ir los addActionListener de los botnes
        ppd.visualizar.addActionListener(this);//Visualizar alumno
        ppa.setVisible(true);
    }

    public ManejadorPrincipal(InterfazBD inter, PanelPrincipalRecepcionista p) {
        this.ppr = p;
        this.intBD = inter;
        
        
        ppr.etiqueta1.addActionListener(this);//Visualizar alumno
        ppr.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (ppe != null) {
            if (e.getSource() == ppe.btnConsultarAlu) {
                try {
                    this.manejaEventobtnConsultarAlumno();
                } catch (SQLException ex) {
                    System.out.println("Error de consulta");
                }
            } else if (e.getSource() == ppe.btnConsultarPersonal) {
                this.manejaEventobtnConsultarPersonal();
            }
        } else if (ppa != null) {
            if (e.getSource() == ppa.etiqueta3) {
                this.manejaEventoReinscribirAlumno();
            }
        }else if(ppd != null){//PanelDirector
      
        }else if(ppc != null){//Panel Coordinador
            
        }else if(ppr != null){//Panel Recepcionista
            
        }

    }

    private void manejaEventobtnConsultarAlumno() throws SQLException {
        Object[][] datos = new InterfazBDEquipo(intBD).consultarAlumnos();

        ppe.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre", "Apellido pat", "apeMat", "Domicilio", "Tel"}));

    }

    private void manejaEventobtnConsultarPersonal() {
        System.out.println("Estas consultado al personal");
    }

    private void manejaEventoReinscribirAlumno() {
        try {
            new ManejadorReinscribirAlumno(intBD, new VReinscribirAlumno());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
