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
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMExamen_CENNI;

/**
 *
 * @author diann
 */
public class ManejadorExamenCENNI implements ActionListener, KeyListener {

    private InterfazBD intBD;
    private DMExamen_CENNI dm;

    public ManejadorExamenCENNI(InterfazBD intBD, DMExamen_CENNI d) throws SQLException {
        this.intBD = intBD;
        this.dm = d;

        dm.examenes.addActionListener(this);
        dm.btn.addActionListener(this);

        this.consultaCandidatos();
        this.consultarExamenes();
        dm.setVisible(true);
    }

    private void consultarExamenes() throws SQLException {
        Object[][] datos = intBD.consultar("select conceptoPago from Concepto "
                + "where idConcepto > 2");

        for (Object[] fila : datos) {
            dm.examenes.addItem((String) fila[0]);
        }
    }

    private void consultaCandidatos() throws SQLException {
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno,horario,curso "
                + "from Alumno,Grupo,Curso where grupid=idGrupo and curso=idCurso "
                + "and idAlumno = ( "
                + "select idAlum from Pago where conceptoid = 3)");
        dm.tabla.setModel(new DefaultTableModel(datos,
                new Object[]{"Matricula", "Nombre", "Horario", "Curso"}));
    }

    private void imprimirLista() throws FileNotFoundException, SQLException, IOException {

        Object[][] grupoExamen = intBD.consultar("select idAlumno,nombreAlumno,"
                + "apellidoPaternoAlumno,apellidoMaternoAlumno,horario,tipocurso "
                + "from Alumno,Grupo,Curso where grupid=idGrupo and curso=idCurso "
                + "and idAlumno = ( "
                + "select idAlum from Pago where conceptoid = 3)");

        //Lineas fundamentales para generar un PDF
        PdfDocument pdf = new PdfDocument(
                new PdfWriter("src/mx/edu/itch/isc/infocoming/archivos/listaExamenCENNI.pdf"));
        Document document = new Document(pdf, PageSize.A4);
        document.setMargins(40, 30, 35, 30);

        Table pdfTable = new Table(new float[]{15, 15,15, 15});
        pdfTable.setWidthPercent(100);

        pdfTable.addHeaderCell(new Cell().add("Matricula"));
        pdfTable.addHeaderCell(new Cell().add("Nombre"));
        pdfTable.addHeaderCell(new Cell().add("Horario"));
        pdfTable.addHeaderCell(new Cell().add("Curso"));

        for (Object[] fila : grupoExamen) {
            pdfTable.addCell((int) fila[0] + "");
            pdfTable.addCell((String) fila[1] + " " + (String) fila[2] + " " + (String) fila[3]);
            pdfTable.addCell((String) fila[4]);
            pdfTable.addCell((String) fila[5]);
        }

        document.add(new Paragraph("Lista " + (String) dm.examenes.getSelectedItem() + "\n").setBold());
        document.add(new Paragraph("Fecha: _______/_________/______"));
        document.add(new Paragraph("Usuario: ____________________"));
        document.add(new Paragraph("Contraseña: ____________________"));
        document.add(pdfTable);

        document.close();

        Desktop.getDesktop().open(new File("src/mx/edu/itch/isc/infocoming/archivos/listaExamenCENNI.pdf"));

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == dm.btn) {
                this.imprimirLista();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

}
