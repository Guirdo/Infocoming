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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMCrearGrupo;
import mx.edu.itch.isc.infocoming.interfacesgraficas.Pantalla;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VGestionGrupo;

public class ManejadorGenerarLista implements ActionListener, KeyListener, ListSelectionListener,WindowListener {

    private InterfazBD intBD;
    private VGestionGrupo v;
    private Pantalla vistaAnterior;

    private int maestroAsignado;
    private int grupoSeleccionado;

    public ManejadorGenerarLista(InterfazBD inter, VGestionGrupo vv, Pantalla vistAnt) throws SQLException {
        this.intBD = inter;
        this.v = vv;
        this.vistaAnterior = vistAnt;
        
        v.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //Agregar actionlistener
        v.btn1.addActionListener(this);
        v.btn2.addActionListener(this);
        v.buscar.addKeyListener(this);
        v.tabla.getSelectionModel().addListSelectionListener(this);
        v.addWindowListener(this);

        this.consultarGrupos();

        v.setVisible(true);
    }

    private void consultarGrupos() throws SQLException {
        Object[][] datos = intBD.consultar("select idGrupo,horario,tipoCurso,Personalid from Grupo,Curso "
                + "where curso = idCurso");

        v.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Num", "Horario", "Curso", "ClaveMaestro"}));
    }

    private void buscarGrupoPorMaestro() throws SQLException {
        Object[][] datos = intBD.consultar("select idGrupo,horario,tipoCurso,Personalid from Grupo,Curso "
                + "where curso = idCurso and Personalid = " + v.buscar.getText());

        v.tabla.setModel(new DefaultTableModel(datos, new Object[]{"Num", "Horario", "Curso", "ClaveMaestro"}));
    }

    private void consultarGrupo() throws SQLException {
        Object[][] datos = intBD.consultar("select p.nombrepersonal,apellidoPaternopersonal,"
                + "apellidoMaternopersonal,g.horario,c.tipocurso"
                + "    from Personal p join(Grupo g join Curso c on g.curso = c.idCurso)"
                + "    on p.idPersonal = g.Personalid"
                + "    where idGrupo = " + grupoSeleccionado);

        String maestro = ((String) datos[0][0]) + " " + ((String) datos[0][1]) + " " + ((String) datos[0][2]);

        v.horario.setText((String) datos[0][3]);
        v.curso.setText((String) datos[0][4]);
        v.maestro.setText(maestro);
    }

    private void manejaEventoImprimirLista() throws FileNotFoundException, SQLException, IOException {
        Object[][] datos = intBD.consultar("select p.nombrepersonal,apellidoPaternopersonal,"
                + "apellidoMaternopersonal,g.idGrupo,horario,c.tipocurso"
                + "    from Personal p join(Grupo g join Curso c on g.curso = c.idCurso)"
                + "    on p.idPersonal = g.Personalid"
                + "    where idGrupo = " + grupoSeleccionado);

        String maestro = ((String) datos[0][0]) + " " + ((String) datos[0][1]) + " " + ((String) datos[0][2]);

        Object[][] listaGrupo = intBD.consultar("select idAlumno,nombreAlumno,apellidoPaternoAlumno,apellidoMaternoAlumno "
                + "from Alumno where grupid = " + grupoSeleccionado);

        //Lineas fundamentales para generar un PDF
        PdfDocument pdf = new PdfDocument(new PdfWriter("src/mx/edu/itch/isc/infocoming/archivos/listaAsistencia.pdf"));
        Document document = new Document(pdf, PageSize.A4);
        document.setMargins(40, 30, 35, 30);

        Table pdfTable = new Table(new float[]{15, 15,
            15, 15, 15, 15, 15, 15, 15});
        pdfTable.setWidthPercent(100);

        pdfTable.addHeaderCell(new Cell().add("Matricula"));
        pdfTable.addHeaderCell(new Cell().add("Nombre"));

        for (int i = 0; i < 7; i++) {
            pdfTable.addHeaderCell(new Cell().add("Dia " + (i + 1)));
        }

        for (Object[] fila : listaGrupo) {
            pdfTable.addCell((int) fila[0] + "");
            pdfTable.addCell((String) fila[1] + " " + (String) fila[2] + " " + (String) fila[3]);
            for (int i = 0; i < 7; i++) {
                pdfTable.addCell("");
            }
        }

        document.add(new Paragraph("Grupo: " + (int) datos[0][3]));
        document.add(new Paragraph("Horario: " + (String) datos[0][4]));
        document.add(new Paragraph("Curso: " + (String) datos[0][5]));
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("Maestro asignado: " + maestro));
        document.add(new Paragraph("\n"));
        document.add(pdfTable);

        document.close();

        Desktop.getDesktop().open(new File("src/mx/edu/itch/isc/infocoming/archivos/listaAsistencia.pdf"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == v.btn1) {
            try {
                new ManejadorCrearGrupo(intBD, new DMCrearGrupo());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == v.btn2) {
            try {
                this.manejaEventoImprimirLista();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!v.buscar.getText().isEmpty()) {
                try {
                    this.buscarGrupoPorMaestro();
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
        try {
            grupoSeleccionado = (int) v.tabla.getValueAt(v.tabla.getSelectedRow(), 0);

            this.consultarGrupo();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        v.dispose();
        vistaAnterior.setVisible(true);
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

}
