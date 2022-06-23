/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FWD.SIG_Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Abdu Sayed
 */
public class Invoice_Header {
    private SimpleDateFormat sDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private int invoice_Num ;
    private Date inv_Date ;
    private String nameOfCustomer ;
    private ArrayList<Invoice_Line> invLine ;
    
    public Invoice_Header() {
    }

    public Invoice_Header(int invoice_Num, Date inv_Date, String name_Of_Customer) {
        this.invoice_Num = invoice_Num;
        this.inv_Date = inv_Date;
        this.nameOfCustomer = name_Of_Customer;
    }

    public String get_Name_Of_Customer() {
        return nameOfCustomer;
    }

    public void set_Name_Of_Customer(String name_Of_Customer) {
        this.nameOfCustomer = name_Of_Customer;
    }

    public int get_Invoice_Number() {
        return invoice_Num;
    }

    public void set_Invoice_Number(int invoice_Number) {
        this.invoice_Num = invoice_Number;
    }

    public Date get_Invoice_Date() {
        return inv_Date;
    }

    public void set_Invoice_Date(Date invDate) {
        this.inv_Date = invDate;
    }
    
    public ArrayList<Invoice_Line> getInvLine() {
        //null obj
        if (invLine == null)
            invLine = new ArrayList<>();  // lazy creation
        
        return invLine;
    }

    public void setInvoiceLine(ArrayList<Invoice_Line> invLine) {
        
        this.invLine = invLine;
    }
    
    public double get_Total ()
    {
        int i;
        double tAll = 0.0;
        for(i=0 ; i< getInvLine().size() ; i++)
        {
            tAll += getInvLine().get(i).get_Total_Item();
        }
        return tAll ;
    }
    
    public String upload_Format() {
            return invoice_Num + "," + sDateFormat.format(inv_Date) + "," + nameOfCustomer ;
        }

    @Override
    public String toString() {
        return "InvHeader{" + "sDateFormat=" + sDateFormat + ", invNum=" + invoice_Num + ", inv_Date=" + inv_Date + ", customerName=" + nameOfCustomer + '}' +"\n" + "invLine=" + invLine.toString() ;
    }
    
    
    
    
    
}
