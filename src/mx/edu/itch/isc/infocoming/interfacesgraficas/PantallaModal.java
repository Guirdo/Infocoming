package mx.edu.itch.isc.infocoming.interfacesgraficas;

import java.awt.Frame;
import java.awt.LayoutManager;
import javax.swing.JDialog;

public class PantallaModal extends JDialog{

    public PantallaModal(Frame owner, LayoutManager layoutManager) {
        super(owner, true);
        this.setLayout(layoutManager);
        
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }
    
    

}
