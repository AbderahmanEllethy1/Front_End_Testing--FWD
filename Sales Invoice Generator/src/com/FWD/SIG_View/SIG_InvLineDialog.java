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
public class SIG_InvLineDialog extends JDialog{
   private JTextField areaOfItemName;
    private JTextField areaOfItemCount;
    private JTextField areaOfItemPrice;
    private JLabel lblOfItemName;
    private JLabel lblOfItemCount;
    private JLabel lblOfItemPrice;
    private JButton B_Ok;
    private JButton B_Cancel;
    
    public SIG_InvLineDialog(Invocie_Frame_SIG InvFr) {
        areaOfItemName = new JTextField(20);
        lblOfItemName = new JLabel("Item Name");
        
        areaOfItemCount = new JTextField(20);
        lblOfItemCount = new JLabel("Item Count");
        
        areaOfItemPrice = new JTextField(20);
        lblOfItemPrice = new JLabel("Item Price");
        
        B_Ok = new JButton("OK");
        B_Cancel = new JButton("Cancel");
        
        B_Ok.setActionCommand("itemNew_OK");
        B_Cancel.setActionCommand("itemNew_Cancel");
        
        B_Ok.addActionListener(InvFr.getAction_B());
        B_Cancel.addActionListener(InvFr.getAction_B());
        setLayout(new GridLayout(4, 2));
        
        add(lblOfItemName);
        add(areaOfItemName);
        add(lblOfItemCount);
        add(areaOfItemCount);
        add(lblOfItemPrice);
        add(areaOfItemPrice);
        add(B_Ok);
        add(B_Cancel);
        
        pack();
    }

    public JTextField get_Area_Of_ItemName() {
        return areaOfItemName;
    }

    public JTextField get_Area_Of_ItemCount() {
        return areaOfItemCount;
    }

    public JTextField get_Area_Of_ItemPrice() {
        return areaOfItemPrice;
    } 
}
