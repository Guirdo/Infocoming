package mx.edu.itch.isc.infocoming.interfacesgraficas;

import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.awt.PopupMenu;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class DMCrearGrupo extends PantallaModal{
    
    private JComboBox<String> cbHoraInicio,cbHoraFinal,cbCursos;
    private JCheckBox[] cbDias = new JCheckBox[7];
    private JTextField tfClaveMaestro;
    

    public DMCrearGrupo() {
        super("Crear grupo", new MigLayout("wrap 2","[]15[]","[]15[]10[]10[]10[]10[]10[]"));
        
        JLabel lblTitulo = new JLabel("Crear grupo");
        
        lblTitulo.setFont(new Font("Arial",1,16));
        
        cbHoraInicio = new JComboBox<>();
        cbHoraFinal = new JComboBox<>();
        cbCursos= new JComboBox<>();
        tfClaveMaestro = new JTextField(12);
        
        JDateChooser fechaSelector =new JDateChooser();
        
        this.cargarCBHoras();
        this.cargarChBDias();
        this.cargarCBCursos();
        
        this.add(lblTitulo,"span 2");
        this.add(new JLabel("Día(s) del curso: "));
        this.add(this.darPnlDias());
        this.add(new JLabel("Horario: "));
        this.add(this.darPnlHorario());
        this.add(new JLabel("Curso: "));
        this.add(cbCursos);
        this.add(new JLabel("Inicio de curso: "));
        this.add(new JLabel(" Aquí va un calendario "));
        this.add(new JLabel("Maestro asignado: "));
        this.add(tfClaveMaestro);
        
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    private void cargarCBHoras(){
        int hora=7;
        boolean mediaHora=false;
        String minutos;
        int i=0;
        
        while(hora<19){
            minutos = mediaHora ? "30":"00";
            
            cbHoraInicio.addItem(hora+":"+minutos);
            cbHoraFinal.addItem((hora+1)+":"+minutos);
            
            i++;
            
            if(i%2==0){
                mediaHora=false;
                hora++;
            }else{
                if (hora==18) break;
                mediaHora=true; 
            }
        }
    }

    private void cargarChBDias() {
        cbDias[0]= new JCheckBox("Lu");
        cbDias[1]= new JCheckBox("Ma");
        cbDias[2]= new JCheckBox("Mi");
        cbDias[3]= new JCheckBox("Jue");
        cbDias[4]= new JCheckBox("Vie");
        cbDias[5]= new JCheckBox("Sa");
        cbDias[6]= new JCheckBox("Do");
    }

    private void cargarCBCursos() {
        //TODO Mejorar
        
        cbCursos.addItem("Ingles - A1");
        cbCursos.addItem("Ingles - A2");
        cbCursos.addItem("Ingles - B1");
        cbCursos.addItem("Ingles - Infantil");
    }

    private JPanel darPnlDias() {
        JPanel pnl = new JPanel(new MigLayout("","",""));
        
        for(int i=0;i<cbDias.length;i++){
            pnl.add(cbDias[i]);
        }
        
        return pnl;
    }

    private JPanel darPnlHorario() {
        JPanel pnl = new JPanel(new MigLayout("","[]15[]15[]",""));
        
        pnl.add(cbHoraInicio);
        pnl.add(new JLabel(" -- "));
        pnl.add(cbHoraFinal);
        
        return pnl;
    }

}
