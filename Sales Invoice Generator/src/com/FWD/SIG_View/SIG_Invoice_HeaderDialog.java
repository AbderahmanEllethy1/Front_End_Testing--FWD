/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FWD.SIG_View;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Abdu Sayed
 */
public class SIG_Invoice_HeaderDialog extends JDialog {
   private JTextField aeraOfCustomer ;
   private JLabel lblOfCustomer;
   private JTextField aeraOfDate;
   private JLabel lblOfDate;
   private JButton B_Cancel;
   private JButton B_Ok;

    public SIG_Invoice_HeaderDialog(Invocie_Frame_SIG InvFr) {
        lblOfCustomer = new JLabel("Customer Name:");
        aeraOfCustomer = new JTextField(20);
        lblOfDate = new JLabel("Invoice Date:");
        aeraOfDate = new JTextField(20);
        B_Cancel = new JButton("Cancel");
        B_Ok = new JButton("OK");
        
        B_Ok.setActionCommand("InvNew_Ok");
        B_Cancel.setActionCommand("InvNew_Cancel");
        
        B_Ok.addActionListener(InvFr.getAction_B());
        B_Cancel.addActionListener(InvFr.getAction_B());
        setLayout(new GridLayout(3, 2));
        
        add(lblOfDate);
        add(aeraOfDate);
        add(lblOfCustomer);
        add(aeraOfCustomer);
        add(B_Ok);
        add(B_Cancel);
        
        pack();
        
    }

    public JTextField getAeraOfCustomer() { /////////
        return aeraOfCustomer;
    }

    public JTextField getAeraOfDate() { ////////////
        return aeraOfDate;
    }
    
}
