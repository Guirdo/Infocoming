package mx.edu.itch.isc.infocoming.manejadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMRegistroES;

public class ManejadorRegistroES implements ActionListener {

    private DMRegistroES dm;
    private InterfazBD iBD;

    private int empleadoSeleccionado;

    public ManejadorRegistroES(InterfazBD ibd, DMRegistroES d) throws SQLException {
        this.dm = d;
        this.iBD = ibd;

        //ActionListerner de los botones del dm
        dm.btnRegistrar.addActionListener(this);
        dm.tfClaveEmpleado.addActionListener(this);

        this.consultaES();
        
        dm.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dm.btnRegistrar) {
            try {
                this.manejaEventoRegistrarES();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void manejaEventoRegistrarES() throws SQLException {// TODO ARREGLAR ESTE METODO
        //","Nombre","HoraEntrada","HoraSalida"
        String claveEmpleado = dm.tfClaveEmpleado.getText();
        String tipo =(String) dm.cbTipo.getSelectedItem();
        iBD.procedimientoInsertar("{call insertarAsistencia(?,?)}", claveEmpleado,tipo);
        
        this.consultaES();
    }

    private void consultaES() throws SQLException {
        Object[][] datos = iBD.consultar("select idPersonal,fecha, "
          + "tipo from AsistenciaP ");
        
        dm.tabla.setModel(new DefaultTableModel(datos,new Object[]{"idPersonal","Fecha y hora","Tipo"}));
    }

}
