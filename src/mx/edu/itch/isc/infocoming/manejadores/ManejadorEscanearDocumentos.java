package mx.edu.itch.isc.infocoming.manejadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMEscanearDocumento;

public class ManejadorEscanearDocumentos implements ActionListener,KeyListener,ListSelectionListener{
    
    private InterfazBD intBD;
    private DMEscanearDocumento dm;
    
    private int alumnoSeleccionado;
    private String rutaSeleccionada="";

    public ManejadorEscanearDocumentos(InterfazBD inter, DMEscanearDocumento d) throws SQLException {
        this.intBD = inter;
        this.dm = d;
        
        dm.btn1.addActionListener(this);
        dm.btn2.addActionListener(this);
        dm.buscar.addKeyListener(this);
        dm.tabla.getSelectionModel().addListSelectionListener(this);
        
        this.consultarAlumnoDocumento();
        
        dm.btn2.setEnabled(false);
        
        dm.setVisible(true);
    }
    
    private void consultarAlumnoDocumento() throws SQLException{
        Object[][] datos = intBD.consultar("select rutaDoc,idAlumno,nombreAlumno,"
                + "apellidoPaternoAlumno,apellidoMaternoAlumno "
                + "from Documento,Alumno where idAlum = idAlumno");
        
        dm.tabla.setModel(new DefaultTableModel(datos,new Object[]{"Ruta","Matricula"
                ,"Nombre Alumno","ApePaterno","ApeMaterno"}));
    }
    
    private void buscarAlumnoPorApellido() throws SQLException{
        Object[][] datos = intBD.consultar("select rutaDoc,idAlumno,nombreAlumno,"
                + "apellidoPaternoAlumno,apellidoMaternoAlumno "
                + "from Documento,Alumno where idAlum = idAlumno "
                + "and apellidoPaternoAlumno = '"+dm.buscar.getText()+"'");
        
        dm.tabla.setModel(new DefaultTableModel(datos,new Object[]{"Ruta","Matricula"
                ,"Nombre Alumno","ApePaterno","ApeMaterno"}));
    }
    
    private void consultarAlumno() throws SQLException{
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno,apellidoPaternoAlumno," 
                + "apellidoMaternoAlumno,tipocurso,horario from Alumno,Curso,Grupo " 
                + "where idAlumno ="+alumnoSeleccionado+" and grupid = idGrupo " 
                + "and curso = idCurso");
        
        dm.matricula.setText((int)datos[0][0]+"");
        dm.nombre.setText((String)datos[0][1]+" "+(String)datos[0][2]+" "+(String)datos[0][3]);
        dm.curso.setText((String)datos[0][4]);
        dm.semanas.setText((String)datos[0][5]);
    }
    
    private void manejaEventoEscanearDocumento(){
        if(dm.tfMatricula.getText().matches("\\d+")){
            alumnoSeleccionado=Integer.parseInt(dm.tfMatricula.getText());
            String tipoDocumento="";
            if(dm.acta.isSelected()){
                tipoDocumento="ActaNacimiento";
            }else if(dm.curp.isSelected()){
                tipoDocumento="CURP";
            }else if(dm.ide.isSelected()){
                tipoDocumento="CredencialEstudiantil";
            }
            
            try {
                intBD.procedimientoInsertar("{call insertarDocumento(?,?,?)}",
                        rutaSeleccionada,tipoDocumento,alumnoSeleccionado);
                
                this.consultarAlumnoDocumento();
                
                dm.lblRuta.setText("");
                dm.tfMatricula.setText("");
                rutaSeleccionada="";
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void manejaEventoSeleccionarArchivo(){
        int seleccion = dm.fcSelector.showOpenDialog(dm);
            
            if(seleccion != JFileChooser.CANCEL_OPTION){
                File archivo = dm.fcSelector.getSelectedFile();
                
                if(archivo == null || archivo.getName().isEmpty()){
                    dm.lblRuta.setText("...");
                    rutaSeleccionada="";
                }else{
                    dm.lblRuta.setText(archivo.getName());
                    rutaSeleccionada=archivo.getPath();
                    dm.btn2.setEnabled(true);
                }
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == dm.btn1){
            this.manejaEventoSeleccionarArchivo();
        }else if(e.getSource() == dm.btn2){
            this.manejaEventoEscanearDocumento();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!dm.buscar.getText().isEmpty()){
                try {         
                    this.buscarAlumnoPorApellido();
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
            alumnoSeleccionado = (int) dm.tabla.getValueAt(dm.tabla.getSelectedRow(), 1);
            dm.btn2.setEnabled(true);
            dm.tfMatricula.setText(""+alumnoSeleccionado);
            this.consultarAlumno();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
