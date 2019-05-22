/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itch.isc.infocoming.manejadores;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.font.*;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.text.Font;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMRegistrarPago;

/**
 *
 * @author pacop
 */
public class ManejadorRegistrarPago implements ActionListener, KeyListener, ListSelectionListener {

    private DMRegistrarPago dM = null;
    private InterfazBD intBD;
    private int matricula = 0;
    private double pago = 0;

    private String concep = "";

    public ManejadorRegistrarPago(InterfazBD intBD, DMRegistrarPago dmrg) throws SQLException {
        this.dM = dmrg;
        this.intBD = intBD;
        dM.btnRegistrar.addActionListener(this);
        concepto();
        dM.busca.addKeyListener(this);
        dM.btnRegistrar.setEnabled(false);
        dM.btnComun.setEnabled(false);
        dM.btnNuevo.setEnabled(false);
        dM.setVisible(true);
    }

    public void buscarAlumnoPorMatricula(int matri) throws SQLException {
        Object[][] datos = intBD.consultar("select idalumno,nombrealumno,tipocurso,horario from alumno,curso,grupo where idgrupo=grupid and idcurso=curso and idalumno=" + matri);
        dM.nombre.setText((String) datos[0][1]);
        dM.curso.setText((String) datos[0][2]);
        dM.horario.setText((String) datos[0][3]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dM.btnRegistrar) {
            try {

                pago = Double.parseDouble(dM.spnPago.getValue().toString());
                
                matricula = Integer.parseInt(dM.busca.getText());

                this.manejaeventoregistrarpago(pago, matricula);
                dM.busca.setText("");
                
                dM.nombre.setText("");
                dM.curso.setText("");
                dM.horario.setText("");
                dM.btnComun.setEnabled(false);
                dM.btnRegistrar.setEnabled(false);
                dM.btnNuevo.setEnabled(false);
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(ManejadorRegistrarPago.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void manejaeventoregistrarpago(double pag, int mat) throws SQLException, FileNotFoundException, IOException {
        String id = "";
        int idconcep = 0;
        if (dM.btnNuevo.isSelected()) {
            this.conceptoNuevo();
            Object[][] datos = intBD.consultar("select idconcepto from concepto where conceptopago='" + dM.tfConceptoNuevo.getText() + "'");
            idconcep = (int) datos[0][0];
            concep=dM.tfConceptoNuevo.getText();
        } else if (dM.btnComun.isSelected()) {
            Object[][] datos = intBD.consultar("select idconcepto from concepto where conceptopago='" + dM.cbConceptos.getSelectedItem().toString() + "'");
            idconcep = (int) datos[0][0];
            concep=dM.cbConceptos.getSelectedItem().toString();            
        }
        intBD.actualizar(" insert into pago values(null," + pag + ",now()," + mat + "," + idconcep + ")");
        String fechaac="";
        Calendar fecha = new GregorianCalendar();
        fechaac=fecha.get(Calendar.DATE)+"/"+fecha.get(Calendar.MONTH)+"/"+fecha.get(Calendar.YEAR);
        //Lineas fundamentales para generar un PDF
//        String fech;
        
        PdfDocument pdf = new PdfDocument(new PdfWriter("src/mx/edu/itch/isc/infocoming/archivos/recibopago.pdf"));
        Document document = new Document(pdf, PageSize.A7);
        document.setMargins(40, 30, 35, 30);
        document.add(new Paragraph("Infocomig\n"));
        document.add(new Paragraph("Recibo de pago")) ;
        document.add(new Paragraph("\nFecha " + fechaac)) ;
        document.add(new Paragraph("Nombre: " +dM.nombre.getText()));
        document.add(new Paragraph("concepto: " +concep));
        document.add(new Paragraph("Horario: " + dM.horario.getText()));       
        document.close();
        Desktop.getDesktop().open(new File("src/mx/edu/itch/isc/infocoming/archivos/recibopago.pdf"));
    }

    public void concepto() throws SQLException {
        Object[][] datos = intBD.consultar("select conceptopago from concepto");
        for (Object[] fila : datos) {
            dM.cbConceptos.addItem((String) fila[0]);
        }
    }

    public void conceptoNuevo() throws SQLException {
        intBD.actualizar("insert into concepto values(null,'" + dM.tfConceptoNuevo.getText() + "')");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            //Si el textfield no esta vacio entonces..
            if (e.getSource() == dM.busca) {
                if (!dM.busca.getText().isEmpty()) {
                    String busqueda = dM.busca.getText();
                    matricula = Integer.parseInt(dM.busca.getText());
                    try {
                        if (busqueda.matches("[0-9]+")) {
                            this.buscarAlumnoPorMatricula(matricula);
                            dM.btnRegistrar.setEnabled(true);
                            dM.btnComun.setEnabled(true);
                            dM.btnNuevo.setEnabled(true);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        
                    }
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

}
