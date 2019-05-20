package mx.edu.itch.isc.infocoming.manejadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import mx.edu.itch.isc.infocoming.excepciones.RegistroGrupoInvalidoException;
import mx.edu.itch.isc.infocoming.interfacesbd.InterfazBD;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMCrearGrupo;

public class ManejadorCrearGrupo implements ActionListener,ListSelectionListener {

    private InterfazBD intBD;
    private DMCrearGrupo dm;

    private int contDias;
    private final String[] DIAS_SEMANA = {"Lu","Ma","Mier","Jue","Vie","Sab","Dom"};
    private int maestroSeleccionado;

    public ManejadorCrearGrupo(InterfazBD intBD, DMCrearGrupo d) throws SQLException {
        this.intBD = intBD;
        this.dm = d;

        contDias = 0;
        
        dm.btnCrear.addActionListener(this);
        dm.tabla.getSelectionModel().addListSelectionListener(this);
        for (int i = 0; i < dm.cbDias.length; i++) dm.cbDias[i].addActionListener(this);
        
        this.consultarGrupos();
        this.consultarMaestros();
        
        dm.setVisible(true);
    }
    
    private void consultarGrupos() throws SQLException{
        Object[][] datos = intBD.consultar("select tipocurso curso from Curso");
        
        for(Object[] fila : datos){
            dm.cbCursos.addItem((String) fila[0]);
        }
    }
    
    private void consultarMaestros() throws SQLException{
        Object[][] datos = intBD.consultar("select idPersonal,nombrepersonal from Personal");
        
        dm.tabla.setModel(new DefaultTableModel(datos,new Object[]{"id","Nombre"}));
    }
    
    private void manejaEventoCrearGrupo() throws RegistroGrupoInvalidoException, SQLException {
        String dias = "";
        
        for(int i=0;i<dm.cbDias.length;i++){
            if(dm.cbDias[i].isSelected()){
                if(!dias.isEmpty()){
                    dias+="-"+DIAS_SEMANA[i];
                }else{
                    dias=DIAS_SEMANA[i];
                }
            }
        }
        
        String horaInicio = (String) dm.cbHoraInicio.getSelectedItem();
        String horaFinal = (String) dm.cbHoraFinal.getSelectedItem();
        
        String[] hoI = horaInicio.split(":");
        String[] hoF = horaFinal.split(":");
        
        String horarioSeleccionado;
        
        if(Integer.parseInt(hoF[0])>Integer.parseInt(hoI[0])){
            horarioSeleccionado = horaInicio+"-"+horaFinal;
        }else{
            throw new RegistroGrupoInvalidoException("Registro Grupo Invalido: Hora final es mayor o igual a Hora inicial.");
        }
        
        String horario = dias +" "+horarioSeleccionado;
        Date fecha = dm.fechaSelector.getDate();
        String fechaInicio = ""+fecha.getDay()+"-"+fecha.getMonth()+"-"+fecha.getYear();
        int cursoSeleccionado = dm.cbCursos.getSelectedIndex()+1;
        
        intBD.procedimientoInsertar("{call insertarGrupo(?,?,?,?)}",
                                    horario,cursoSeleccionado,fechaInicio,maestroSeleccionado );
        
        dm.dispose();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dm.btnCrear) {
            try {
                this.manejaEventoCrearGrupo();
            } catch (RegistroGrupoInvalidoException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Mensaje de error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else {
            for (int i = 0; i < dm.cbDias.length; i++) {
                if (e.getSource() == dm.cbDias[i]) {
                    if (dm.cbDias[i].isSelected()) {
                        contDias++;
                    } else {
                        if (contDias == 4) {
                            for (JCheckBox cb : dm.cbDias) {
                                cb.setEnabled(true);
                            }
                        }
                        contDias--;
                    }

                    if (contDias == 4) {
                        for (JCheckBox cb : dm.cbDias) {
                            if(!cb.isSelected()){
                                cb.setEnabled(false);
                            }
                        }
                    }
                }
            }
        }

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        maestroSeleccionado = (int) dm.tabla.getValueAt(dm.tabla.getSelectedRow(),0);
        
        System.out.println("Maestro: "+maestroSeleccionado);
    }

}
