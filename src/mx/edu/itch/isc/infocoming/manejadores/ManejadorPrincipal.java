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
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMExamen_CENNI;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMInscribirAlumno;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMRegistrarPago;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMRegistrarPersonal;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMRegistroES;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalAdministrador;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalCoordinadorAcademico;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalDirector;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalEquipo;
import mx.edu.itch.isc.infocoming.interfacesgraficas.PanelPrincipalRecepcionista;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VBajaAlumno;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VGestionPagos;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VGestionGrupo;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VGestionPersonal;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VGestionUsuario;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VReinscribirAlumno;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VValidarUsuario;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VVisualizarAlumnos;

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
        ppa.etiqueta8.addActionListener(this);
        ppa.etiqueta2.addActionListener(this);
        ppa.etiqueta5.addActionListener(this);
        ppa.etiqueta7.addActionListener(this);
        ppa.etiqueta11.addActionListener(this);//Visualizar pago
        ppa.titulo5.addActionListener(this);
        ppa.etiqueta6.addActionListener(this);
        ppa.etiqueta9.addActionListener(this);

        ppa.setVisible(true);

    }

    public ManejadorPrincipal(InterfazBD inter, PanelPrincipalCoordinadorAcademico p) {
        this.ppc = p;
        this.intBD = inter;
        //Aqui van a ir los addActionListener de los botnes
        ppc.visualizarAlumno.addActionListener(this);//Visualizar alumno
        ppc.darBajaAlumno.addActionListener(this);
        ppc.setVisible(true);
    }

    public ManejadorPrincipal(InterfazBD inter, PanelPrincipalDirector p) {
        this.ppd = p;
        this.intBD = inter;

        //Aqui van a ir los addActionListener de los botnes
        ppd.visualizar.addActionListener(this);//Visualizar alumno
        ppd.bajaA.addActionListener(this);
        ppd.registrarE.addActionListener(this);
        ppd.bajaE.addActionListener(this);

        ppd.setVisible(true);
    }

    public ManejadorPrincipal(InterfazBD inter, PanelPrincipalRecepcionista p) {

        this.ppr = p;
        this.intBD = inter;
        ppr.titulo4.addActionListener(this);
        ppr.etiqueta1.addActionListener(this);//Visualizar alumno
        ppr.etiqueta4.addActionListener(this);//Visualizar pago
        ppr.etiqueta2.addActionListener(this);
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
                    this.manejaEventoInscribirAlumno();//Metodo de prueba, borralo cuando ya no lo necesites
                } else if (e.getSource() == ppa.etiqueta3) {
                    this.manejaEventoReinscribirAlumno();
                } else if (e.getSource() == ppa.etiqueta7) {
                    this.manejaEventoBajaAlumno();
                } else if (e.getSource() == ppa.titulo5) {
                    this.manejaEventoGestionGrupo();
                } else if (e.getSource() == ppa.etiqueta5) {
                    this.manejaEventoEscanearDocumento();
                } else if (e.getSource() == ppa.etiqueta11) {
                    this.ManejaEventoVisualizarHistPago();
                } else if (e.getSource() == ppa.etiqueta6) {
                    this.ManejaEventoExamenCENNI();
                } else if (e.getSource() == ppa.etiqueta2) {
                    this.manejaEventoVisualizarAlumno();
                } else if (e.getSource() == ppa.etiqueta8) {
                    this.manejaEventoRegistrarEmpleado();
                }
                else if (e.getSource() == ppa.etiqueta9) {
                    this.manejaEventoModificarContrasena();
                }
                
                
            } else if (ppd != null) {//PanelDirector
                if (e.getSource() == ppd.bajaA) {
                    this.manejaEventoBajaAlumno();
                } else if (e.getSource() == ppd.visualizar) {
                    this.manejaEventoVisualizarAlumno();
                } else if (e.getSource() == ppd.registrarE) {
                    this.manejaEventoRegistrarEmpleado();
                }
                else if (e.getSource() == ppd.bajaE) {
                    this.manejaEventoBajaEmpleado();
                }
                
                
            } else if (ppc != null) {//Panel Coordinador
                if (e.getSource() == ppc.darBajaAlumno) {
                    this.manejaEventoBajaAlumno();
                } else if (e.getSource() == ppc.visualizarAlumno) {
                    this.manejaEventoVisualizarAlumno();
                }
            } else if (ppr != null) {//Panel Recepcionista
                if (e.getSource() == ppr.titulo4) {
                    this.manejaEventoGestionGrupo();
                } else if (e.getSource() == ppr.etiqueta1) {
                    this.manejaEventoVisualizarAlumno();
                } else if (e.getSource() == ppr.etiqueta4) {
                    this.ManejaEventoVisualizarHistPago();
                } else if (e.getSource() == ppr.etiqueta2) {
                    this.manejaEventoRegistroES();
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

    private void manejaEventoVisualizarAlumno() throws SQLException {
        if (ppa != null) {
            ppa.dispose();
            new ManejadorVisualizarAlumnos(intBD, new VVisualizarAlumnos(), ppa);
        } else if (ppd != null) {
            ppd.dispose();
            new ManejadorVisualizarAlumnos(intBD, new VVisualizarAlumnos(), ppd);
        } else if (ppc != null) {
            ppc.dispose();
            new ManejadorVisualizarAlumnos(intBD, new VVisualizarAlumnos(), ppc);
        } else if (ppr != null) {
            ppr.dispose();
            new ManejadorVisualizarAlumnos(intBD, new VVisualizarAlumnos(), ppr);
        }
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
    private void manejaEventoBajaEmpleado() throws SQLException {
        if (ppa != null) {
            ppa.dispose();
            new ManejadorBajaEmpleado(intBD, new VGestionPersonal(), ppa);
        } else if (ppc != null) {
            ppc.dispose();
            new ManejadorBajaEmpleado(intBD, new VGestionPersonal(), ppc);
        } else if (ppd != null) {
            ppd.dispose();
            new ManejadorBajaEmpleado(intBD, new VGestionPersonal(), ppd);
        }

    }

    private void manejaEventobtConsultarPersonal() {
        System.out.println("Estas consultado al personal");
    }

    private void manejaEventoRegistrarEmpleado() throws SQLException {
        new ManejadorRegistrarEmpleado(intBD, new DMRegistrarPersonal());
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

    private void ManejaEventoVisualizarHistPago() throws SQLException {
        if (ppr != null) {
            ppr.dispose();
            new ManejadorVisualizarHistorialPago(intBD, new VGestionPagos(), ppr);
        } else if (ppa != null) {
            ppa.dispose();
            new ManejadorVisualizarHistorialPago(intBD, new VGestionPagos(), ppa);
        }
    }

    private void manejaEventoRegistroES() throws SQLException {
        new ManejadorRegistroES(intBD, new DMRegistroES());
    }

    private void manejaEventobtConsultarES() {
        System.out.println("Estas consultado al personal");

    }

    private void manejaEventoInscribirAlumno() throws SQLException {
        new ManejadorInscribirAlumno(intBD, new DMInscribirAlumno());
    }

    private void ManejaEventoExamenCENNI() throws SQLException {
        new ManejadorExamenCENNI(intBD, new DMExamen_CENNI());
    }
    

    private void manejaEventoModificarContrasena() {
        ppa.dispose();
        new ManejadorModificarContrasena(intBD, new VGestionUsuario(),ppa);
    }
    
}
