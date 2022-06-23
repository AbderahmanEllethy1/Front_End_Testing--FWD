/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FWD.SIG_Model;

import com.FWD.SIG_View.Invocie_Frame_SIG;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Abdu Sayed
 */
public class InvHdrTbModel extends AbstractTableModel{

    private ArrayList<Invoice_Header> invoiceHeader ;
    private String [] columnNames = {"No." , "Date" , "Customer" , "Total"};
    public InvHdrTbModel(ArrayList<Invoice_Header> invHdr) {
        this.invoiceHeader = invHdr;
    }

    
    
    @Override
    public int getRowCount() {
        return invoiceHeader.size(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
        return columnNames.length ;
    }

    @Override
    public Object getValueAt(int row_Index, int column_Index) {
        Invoice_Header invH = invoiceHeader.get(row_Index) ;
        switch(column_Index){
            case 0 : return invH.get_Invoice_Number();
            case 1 : return Invocie_Frame_SIG.sDateFormat.format(invH.get_Invoice_Date());
            case 2 : return invH.get_Name_Of_Customer();
            case 3 : return invH.get_Total();
        }
        return "";
    }

    @Override
    public String getColumnName(int Column) {
        return columnNames[Column] ;
    }
    
    
            
}
