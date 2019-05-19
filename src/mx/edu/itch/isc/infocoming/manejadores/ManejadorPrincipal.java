package mx.edu.itch.isc.infocoming.manejadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBDEquipo;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMEscanearDocumento;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMRegistrarPago;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalAdministrador;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalCoordinadorAcademico;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalDirector;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalEquipo;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalRecepcionista;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VBajaAlumno;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VGestionGrupo;
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
        ppa.etiqueta1.addActionListener(this);
        ppa.etiqueta3.addActionListener(this);
        ppa.etiqueta2.addActionListener(this);
        ppa.etiqueta5.addActionListener(this);
        ppa.etiqueta7.addActionListener(this);
        ppa.titulo5.addActionListener(this);
        ppa.setVisible(true);
    }

    public ManejadorPrincipal(InterfazBD inter, PanelPrincipalCoordinadorAcademico p) {
        this.ppc = p;
        this.intBD = inter;
        //Aqui van a ir los addActionListener de los botnes
        ppc.darBajaAlumno.addActionListener(this);
        ppc.setVisible(true);
    }

    public ManejadorPrincipal(InterfazBD inter, PanelPrincipalDirector p) {
        this.ppd = p;
        this.intBD = inter;

        //Aqui van a ir los addActionListener de los botnes
        ppd.bajaA.addActionListener(this);
        ppd.setVisible(true);
    }

    public ManejadorPrincipal(InterfazBD inter, PanelPrincipalRecepcionista p) {
        this.ppr = p;
        this.intBD = inter;

        ppr.titulo4.addActionListener(this);

        ppr.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
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
                if (e.getSource() == ppa.etiqueta1) {
                    this.insertarAlumno();//Metodo de prueba, borralo cuando ya no lo necesites
                } else if (e.getSource() == ppa.etiqueta3) {
                    this.manejaEventoReinscribirAlumno();
                } else if (e.getSource() == ppa.etiqueta7) {
                    this.manejaEventoBajaAlumno();
                } else if (e.getSource() == ppa.titulo5) {
                    this.manejaEventoGestionGrupo();
                } else if (e.getSource() == ppa.etiqueta5) {
                    this.manejaEventoEscanearDocumento();
                }
            } else if (ppd != null) {//PanelDirector
                if (e.getSource() == ppd.bajaA) {
                    this.manejaEventoBajaAlumno();
                }
            } else if (ppc != null) {//Panel Coordinador
                if (e.getSource() == ppc.darBajaAlumno) {
                    this.manejaEventoBajaAlumno();
                }
            } else if (ppr != null) {//Panel Recepcionista
                if (e.getSource() == ppr.titulo4) {
                    this.manejaEventoGestionGrupo();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void manejaEventoEscanearDocumento() throws SQLException {
        new ManejadorEscanearDocumentos(intBD, new DMEscanearDocumento());
    }

    private void manejaEventobtnConsultarAlumno() throws SQLException {
        Object[][] datos = new InterfazBDEquipo(intBD).consultarAlumnos();

        ppe.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre", "Apellido pat", "apeMat", "Domicilio", "Tel"}));

    }

    private void manejaEventobtnConsultarPersonal() {
        System.out.println("Estas consultado al personal");
    }

    private void manejaEventoReinscribirAlumno() throws SQLException {
        ppa.dispose();
        new ManejadorReinscribirAlumno(intBD, new VReinscribirAlumno(), ppa);

    }

    private void manejaEventoBajaAlumno() throws SQLException {
        if (ppa != null) {
            ppa.dispose();
            new ManejadorBajaAlumno(intBD, new VBajaAlumno(), ppa);
        } else if (ppc != null) {
            ppc.dispose();
            new ManejadorBajaAlumno(intBD, new VBajaAlumno(), ppc);
        } else if (ppd != null) {
            ppd.dispose();
            new ManejadorBajaAlumno(intBD, new VBajaAlumno(), ppd);
        }

    }

    private void manejaEventoGestionGrupo() throws SQLException {
        if (ppa != null) {
            ppa.dispose();
            new ManejadorGenerarLista(intBD, new VGestionGrupo(), ppa);
        } else if (ppr != null) {
            ppr.dispose();
            new ManejadorGenerarLista(intBD, new VGestionGrupo(), ppr);
        }
    }

    private void ManejaEventoRegistrarPago() {
        new ManejadorRegistrarPago(new DMRegistrarPago());
    }

    /**
     * Este metodo es de prueba, borralo cuando ya no lo necesites
     */
    private void insertarAlumno() {
        try {
            intBD.procedimientoInsertar("{call insertarAlumno(?,?,?,?,?,?)}", //Llamada al procedimeinto
                    "Daniel", "Ramirez", "Contreras", "Col. Ye", "3435363733", 2);//Cada ? representa cada parametro que recibe
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
