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
public class Model_Of_Invoice_Item extends AbstractTableModel{

    private ArrayList<Invoice_Line> inLine ;
    private String [] colNames = {"No." , "Item Name" , "Item Price" , "Count","Item Total"};
    
    public Model_Of_Invoice_Item(ArrayList<Invoice_Line> inLine) {
        this.inLine = inLine;
    }
    @Override
    public int getRowCount() {
        return inLine.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    

    @Override
    public Object getValueAt(int row_Index, int column_Index) {
        Invoice_Line invL = inLine.get(row_Index) ;
        switch(column_Index){
            case 0 : return invL.get_Invoice_Header().get_Invoice_Number();
            case 1 : return invL.get_Name_Of_Item();
            case 2 : return invL.get_Price_Of_Item();
            case 3 : return invL.get_Item_Count();
            case 4 : return invL.get_Total_Item();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column] ;
    }
    
    
}
