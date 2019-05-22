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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMGenerarDocumentos;

/**
 *
 * @author pacop
 */
public class ManejadorGenerarDocumento implements ActionListener, KeyListener, ListSelectionListener,WindowListener {
    private DMGenerarDocumentos dm;
    private InterfazBD intBD;
    private int alumnoSeleccionado;
   private String apellidop="";
     private String apellidom="";
     private String fechaac;
    public  ManejadorGenerarDocumento (InterfazBD inter,DMGenerarDocumentos d) throws SQLException{
        this.dm=d;
        this.intBD=inter;
        dm.btn1.addActionListener(this);
        dm.tabla.getSelectionModel().addListSelectionListener(this);
        dm.buscar.addKeyListener(this);
        dm.btn1.setEnabled(false);
        dm.constancia.addActionListener(this);
        this.consultarAlumnos();
        dm.setVisible(true);
        
}
    private void consultarAlumnos() throws SQLException{
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno from alumno");
        dm.tabla.setModel(new DefaultTableModel(datos,new Object[]{"Matricula","Nombre"}));
    }
    private void consultarAlumnoVentana(int matri) throws SQLException {
        Object[][] datos = intBD.consultar("select nombreAlumno,apellidopaternoalumno,apellidomaternoalumno,"
                +"tipocurso from Alumno,Curso,Grupo "
                + "where idAlumno = " + matri + " and grupid = idGrupo "
                + "and curso = idCurso");
 
        dm.matricula.setText(matri+"");
        dm.nombre.setText((String) datos[0][0]);
        apellidop=(String)datos[0][1];                
        apellidom=(String)datos[0][2];
        dm.curso.setText((String) datos[0][3]);
        
    }
    private void gConstancia() throws FileNotFoundException, IOException{
        Calendar fecha = new GregorianCalendar();
        fechaac=fecha.get(Calendar.DATE)+"/"+fecha.get(Calendar.MONTH)+"/"+fecha.get(Calendar.YEAR);
        PdfDocument pdf = new PdfDocument(new PdfWriter("src/mx/edu/itch/isc/infocoming/archivos/constancia.pdf"));
        Document document = new Document(pdf, PageSize.A4);
        document.setMargins(40, 30, 35, 30);
        
        document.add(new Paragraph("\n\n\n\n\nINFOCOMING\n" +
"\n" +
"CONSTANCIA DE ESTUDIOS\n" +
"\n"
                + "\n"
                + "\n" +
"A QUIEN CORRESPONDA:\n" +
"\n"
                + "\n"
                + "\n"
                + "\n"
                + "\n"
                + "\n"
                + "\n"
                + "\n"
                + "\n" +
"El INFOCOMING hace CONSTAR que el ciudadano " +dm.nombre.getText()+" "+apellidop+ " "+apellidom+",con matricula "+dm.matricula.getText()+" ,se encuentra estudiando el nivel "+dm.curso.getText()+"\n" +
"\n" +
"Para los fines que al interesado le convengan, se extiende la presente el día    "+fechaac+"\n" +
"\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n"
        + "\n" +
"ATentamente\n" +
"EL DIRECTOR DEL INFOCOMING"));
        
        document.close();
        Desktop.getDesktop().open(new File("src/mx/edu/itch/isc/infocoming/archivos/constancia.pdf"));
    }
//    public void gCertificado()throws FileNotFoundException, IOException, SQLException{
//        Object[][] listacal = intBD.consultar("select tipocurso,calificacion from alumno,grupo,curso,evaluacion where idalumno=alumno_id and idcurso=curso and idgrupo=grupid and idAlumno = " + dm.matricula.getText());
//        Calendar fecha = new GregorianCalendar();
//        fechaac=fecha.get(Calendar.DATE)+"/"+fecha.get(Calendar.MONTH)+"/"+fecha.get(Calendar.YEAR);
//        PdfDocument pdf = new PdfDocument(new PdfWriter("src/mx/edu/itch/isc/infocoming/archivos/certificado.pdf"));
//        Document document = new Document(pdf, PageSize.A4);
//        document.setMargins(40, 30, 35, 30);
//        Table pdfTable = new Table(new float[]{15, 15});
//        pdfTable.setWidthPercent(100);
//
//        pdfTable.addHeaderCell(new Cell().add("Curso"));
//        pdfTable.addHeaderCell(new Cell().add("calificacion"));
//        for (Object[] fila : listacal) {
//            pdfTable.addCell((int) fila[0] + "");
//            pdfTable.addCell((String) fila[1] + " " + (String) fila[2] + " " + (String) fila[3]);
//            for (int i = 0; i < 7; i++) {
//                pdfTable.addCell("");
//            }
//        }
//        document.add(new Paragraph("\n\n\n\n\nINFOCOMING\n" +
//"\n" +
//"Certificado\n" +
//"\n  Nombre: "+ dm.nombre.getText()+" "+apellidop+ " "+apellidom+
//"\n  Matricula: "+dm.matricula.getText()+
//"\n  Nivel: "+dm.curso.getText()+
//"\n  Fecha: " +fechaac+
//"\n" ));
//        document.add(pdfTable);
//        document.add(new Paragraph("\n" +
//"\n" +
//"\n" +
//"\n" +
//"\n\n\n\n\n\n" +
//"ATentamente\n" +
//"EL DIRECTOR DEL INFOCOMING"));
//
//        document.close();
//        Desktop.getDesktop().open(new File("src/mx/edu/itch/isc/infocoming/archivos/certificado.pdf"));
//
//    }
    public void gDiploma()throws SQLException,FileNotFoundException, IOException{
        Calendar fecha = new GregorianCalendar();
        fechaac=fecha.get(Calendar.DATE)+"/"+fecha.get(Calendar.MONTH)+"/"+fecha.get(Calendar.YEAR);
        PdfDocument pdf = new PdfDocument(new PdfWriter("src/mx/edu/itch/isc/infocoming/archivos/diploma.pdf"));
        Document document = new Document(pdf, PageSize.A4);
        document.setMargins(40, 30, 35, 30);
        
        document.add(new Paragraph("INFOCOMING\n" +
"\n" +
"\n" +
"DIPLOMA\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"A "+dm.nombre.getText()+" "+apellidop+" "+apellidom+"\n" +
"Por haber concluido con éxito el curso de + dm.curso.gettext()\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"\n" +
"ATentamente\n" +
"EL DIRECTOR DEL INFOCOMING"));
        document.close();
        Desktop.getDesktop().open(new File("src/mx/edu/itch/isc/infocoming/archivos/diploma.pdf"));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == dm.btn1){
            try {
                this.manejaEventoGenerar();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(ManejadorGenerarDocumento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void manejaEventoGenerar()throws SQLException, IOException{
        if (dm.constancia.isSelected()) {
           this.gConstancia();  
        }
        if(dm.diploma.isSelected()){
            this.gDiploma();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
               //Si el usuario presiona la tecla enter en el Textfield buscar, entonces...
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            //Si el textfield no esta vacio entonces..
            if (!dm.buscar.getText().isEmpty()) {
                String busqueda = dm.buscar.getText();
                try {
                    if (busqueda.matches("[A-Za-z]+")) {
                        this.buscarAlumnoPorApellido();
                    } else if (busqueda.matches("[0-9]+")) {                        
                        this.buscarAlumnoPorMatricula();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        //Este es para la tabla de mi ventana
        if (e.getSource() == dm.tabla.getSelectionModel()) {
            //De esta forma obtengo la matricula del alumno seleccionado
            alumnoSeleccionado = (int) dm.tabla.getValueAt(dm.tabla.getSelectedRow(), 0);//<-- Este ultimo numero corresponde a la col de la tabla
            try {
                this.consultarAlumnoVentana(alumnoSeleccionado);
                dm.btn1.setEnabled(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }

    
        
    private void buscarAlumnoPorApellido() throws SQLException {
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno from Alumno where apellidoPaternoAlumno = '" + dm.buscar.getText()+ "'");
        dm.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre"}));
    }
    
    private void buscarAlumnoPorMatricula() throws SQLException{
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno from Alumno where idAlumno = " + dm.buscar.getText());
        dm.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Matricula", "Nombre"}));
    }
    
}
