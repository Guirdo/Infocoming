/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.itch.isc.infocoming.manejadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mx.edu.itch.isc.infocoming.interfacesgraficas.DMRegistrarPago;

/**
 *
 * @author pacop
 */
public class ManejadorRegistrarPago implements ActionListener {
    private DMRegistrarPago dM;

    public ManejadorRegistrarPago(DMRegistrarPago dmrg){
       this.dM=dmrg;
       dM.btnRegistrar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
