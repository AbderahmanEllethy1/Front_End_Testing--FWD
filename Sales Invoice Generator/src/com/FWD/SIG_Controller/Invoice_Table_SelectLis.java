/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FWD.SIG_Controller;

import com.FWD.SIG_Model.Invoice_Header;
import com.FWD.SIG_Model.Model_Of_Invoice_Item;
import com.FWD.SIG_Model.Invoice_Line;
import com.FWD.SIG_View.Invocie_Frame_SIG;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Abdu Sayed
 */
public class Invoice_Table_SelectLis implements ListSelectionListener{

    private Invocie_Frame_SIG invoice_Frame ;

    public Invoice_Table_SelectLis(Invocie_Frame_SIG invoic_Frame) {
        this.invoice_Frame = invoic_Frame;
    }

   /* public Invoice_Table_SelectLis(Invocie_Frame_SIG aThis) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    
    
    
    
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int scI = invoice_Frame.getInvTable_T().getSelectedRow();
        if(scI != -1)
        {
        Invoice_Header scInv =  invoice_Frame.getInvHdr().get(scI);
        ArrayList<Invoice_Line> invLine = scInv.getInvLine();
        Model_Of_Invoice_Item invItemsTbM = new Model_Of_Invoice_Item(invLine);
        invoice_Frame.setInvLine(invLine);
        invoice_Frame.getInvItems_T().setModel(invItemsTbM);
        invoice_Frame.getInvNumber_L().setText(scInv.get_Invoice_Number()+"");
        invoice_Frame.getInvDate_L().setText(invoice_Frame.sDateFormat.format(scInv.get_Invoice_Date()));  
        invoice_Frame.getCustomerName_L().setText(scInv.get_Name_Of_Customer());
        invoice_Frame.getInvTotal_L().setText(scInv.get_Total()+"");
        }  
        
        
    }
    
}
