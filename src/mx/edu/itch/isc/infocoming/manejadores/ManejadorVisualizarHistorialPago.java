/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itch.isc.infocoming.manejadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VGestionPagos;
import mx.edu.itch.isc.infocoming.interfacesgraficas.VVisualizarAlumnos;

/**
 *
 * @author diann
 */
public class ManejadorVisualizarHistorialPago implements ActionListener {
    private VGestionPagos vgp;
    private InterfazBD intBD;
    
    public ManejadorVisualizarHistorialPago(InterfazBD inter, VGestionPagos vg) throws SQLException {
        this.vgp = vg;
        this.intBD=inter;

        //Agregar actionListener a los botones de la vista
        this.consultarPagos();
        vgp.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void consultarPagos() throws SQLException{
        Object[][] datos = intBD.consultar("select idAlumno,nombreAlumno, fecha, conceptoPago, cantidad, idPago from Concepto, Alumno, Pago where idAlumno");
        vgp.tabla.setModel(new DefaultTableModel(datos,new Object[]{"Matricula","Nombre","Fecha pago","Concepto pago", "Cantidad", "Folio"}));
        
    }
}
