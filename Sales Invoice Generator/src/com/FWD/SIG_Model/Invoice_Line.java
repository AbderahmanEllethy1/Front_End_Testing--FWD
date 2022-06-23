/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FWD.SIG_Model;

/**
 *
 * @author Abdu Sayed
 */
public class Invoice_Line {
    
    private String name_Of_Item ;
    private double price_Of_Item ;
    private int item_Count;
    private Invoice_Header invoice_Header ;

    public Invoice_Line(String item_Name, double item_Price, int item_Count, Invoice_Header invoice_Header) {
        this.name_Of_Item = item_Name;
        this.price_Of_Item = item_Price;
        this.item_Count = item_Count;
        this.invoice_Header = invoice_Header;
    }
    
    public Invoice_Line() {
    }

   

    public String get_Name_Of_Item() {
        return name_Of_Item;
    }

    public void set_Name_Of_Item(String name_Of_Item) {
        this.name_Of_Item = name_Of_Item;
    }

    public double get_Price_Of_Item() {
        return price_Of_Item;
    }

    public void set_Price_Of_Item(double price_Of_Item) {
        this.price_Of_Item = price_Of_Item;
    }

    public int get_Item_Count() {
        return item_Count;
    }

    public void set_Item_Count(int item_Count) {
        this.item_Count = item_Count;
    }

    public Invoice_Header get_Invoice_Header() {
        return invoice_Header;
    }

    public void set_Invoice_Header(Invoice_Header invoic_Header) {
        this.invoice_Header = invoic_Header;
    }

    
    public double get_Total_Item (){
        return item_Count * price_Of_Item ;
    }
    
    public String uploadLineFormat() {
            return invoice_Header.get_Invoice_Number()+ "," +name_Of_Item + "," + price_Of_Item + "," + item_Count  ;

        }

    @Override
    public String toString() {
        return "InvLine{" + "itemName=" + name_Of_Item + ", itemPrice=" + price_Of_Item + ", itemCount=" + item_Count +  '}'+"\n";
    }
    
    
    
    
    
    

    
}
