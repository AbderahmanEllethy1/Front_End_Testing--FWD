/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FWD.SIG_Controller;

import com.FWD.SIG_Model.InvHdrTbModel;
import com.FWD.SIG_Model.Invoice_Header;
import com.FWD.SIG_Model.Model_Of_Invoice_Item;
import com.FWD.SIG_Model.Invoice_Line;
import com.FWD.SIG_View.Invocie_Frame_SIG;
import com.FWD.SIG_View.SIG_Invoice_HeaderDialog;
import com.FWD.SIG_View.SIG_InvLineDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Abdu Sayed
 */
public class Sig_ActionListener implements ActionListener{

    private Invocie_Frame_SIG invoic_Frame ;
    private SIG_Invoice_HeaderDialog invoic_HeaderD ;
    private SIG_InvLineDialog invoic_LineD;
            
            
    public Sig_ActionListener(Invocie_Frame_SIG invoic_Frame) {
        this.invoic_Frame = invoic_Frame;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch (e.getActionCommand())
        {
            case  "Save":
                save();
            break;
            
            case  "Delete Invoice":
                deleteInvoice();
            break;
            
            case  "Create New Invoice":
                create_NewInv();
            break;
            
            case  "Save File":
                saveFile();
                printDataInConsol();
            break;
            
            case  "Load File":         
                load_File();   
            break;
            
            case  "Cancel":
                cancel(); 
            break;
            
            case "InvNew_Cancel":
                inv_NewDialog_Cancel();
            break;
            
            case "InvNew_Ok":
                inv_NewDialog_Ok();
            break;
            
            case "itemNew_OK" :
                item_NewDialog_Ok();
            break;
            
            case "itemNew_Cancel":
                itemNewDialog_Cancel();
            break;
            
        }
        
    }

    private void save() {
        invoic_LineD = new SIG_InvLineDialog(invoic_Frame);
        invoic_LineD.setVisible(true);
    }

    private void deleteInvoice() {
        int scInv = invoic_Frame.getInvTable_T().getSelectedRow();
        if(scInv != -1)
        {
            invoic_Frame.getInvHdr().remove(scInv);
            invoic_Frame.getInvhdrmodel().fireTableDataChanged();
            invoic_Frame.getInvItems_T().setModel(new Model_Of_Invoice_Item(new ArrayList<Invoice_Line>()));
            
            invoic_Frame.setInvLine(null);
            
            invoic_Frame.getInvNumber_L().setText("");
            invoic_Frame.getInvDate_L().setText("");  
            invoic_Frame.getCustomerName_L().setText("");
            invoic_Frame.getInvTotal_L().setText("");
        }
        
    }

    private void create_NewInv() {
        
        invoic_HeaderD = new SIG_Invoice_HeaderDialog(invoic_Frame);
        invoic_HeaderD.setVisible(true);
        
    }

    private void saveFile() {
        
        ArrayList<Invoice_Header> invH = invoic_Frame.getInvHdr();
        JFileChooser fileChoosed = new JFileChooser();
        try {
            int option = fileChoosed.showSaveDialog(invoic_Frame);
            if (option == JFileChooser.APPROVE_OPTION) {
                File h_File = fileChoosed.getSelectedFile();
                FileWriter newInvHdrFile = new FileWriter(h_File);
                String textHeaders = "";
                String textLines = "";
                for (Invoice_Header inV : invH) {
                    textHeaders += inV.upload_Format();
                    textHeaders += "\n";
                    for (Invoice_Line Ln : inV.getInvLine()) {
                        textLines += Ln.uploadLineFormat();
                        textLines += "\n";
                    }
                }
                textHeaders = textHeaders.substring(0, textHeaders.length()-1);
                textLines = textLines.substring(0, textLines.length()-1);
                option = fileChoosed.showSaveDialog(invoic_Frame);
                File l_File = fileChoosed.getSelectedFile();
                FileWriter newInvLineFile = new FileWriter(l_File);
                newInvHdrFile.write(textHeaders);
                newInvLineFile.write(textLines);
                
                newInvHdrFile.close();
                newInvLineFile.close();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(invoic_Frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    private void load_File()  {
        
        JFileChooser select_File = new JFileChooser();
        try{
        int option = select_File.showOpenDialog(invoic_Frame);
        if(option == JFileChooser.APPROVE_OPTION)
        {
            File selected_File = select_File.getSelectedFile();
            Path filePath = Paths.get(selected_File.getAbsolutePath());
            List<String> fileLines = Files.readAllLines(filePath);
            ArrayList<Invoice_Header> invHeaders = new ArrayList<>();
            for(String fileLine : fileLines)
            {
                String [] lineSplit = fileLine.split(",");
                String numS = lineSplit[0];
                String dateS = lineSplit[1];
                String customerS = lineSplit[2];
                
                int id = Integer.parseInt(numS);
                Date date = invoic_Frame.sDateFormat.parse(dateS);
                
                Invoice_Header invHeader = new Invoice_Header(id, date, customerS);
                invHeaders.add(invHeader);
            }
            
            invoic_Frame.setInvHdr(invHeaders);
        option = select_File.showOpenDialog(invoic_Frame);
            if(option == JFileChooser.APPROVE_OPTION)
            {
                File selectedFile2 = select_File.getSelectedFile();
                Path filePath2 = Paths.get(selectedFile2.getAbsolutePath());
                List<String> itemLines = Files.readAllLines(filePath2);
               // ArrayList<InvLine> invLines = new ArrayList<>();
                for(String itemline : itemLines)
                {
                    String [] line_Split = itemline.split(",");
                    String num_S = line_Split[0];
                    String item_Of_NameS = line_Split[1];
                    String price_S = line_Split[2];
                    String amount_S = line_Split[3];
                    
                    int id = Integer.parseInt(num_S);
                    double price = Double.parseDouble(price_S);
                    int amount = Integer.parseInt(amount_S);
 
                    Invoice_Line invline  = new Invoice_Line(item_Of_NameS , price ,amount , invoic_Frame.matchedID(id));
                    invoic_Frame.matchedID(id).getInvLine().add(invline);
                        
                }
            } 
            InvHdrTbModel hdrModel = new InvHdrTbModel(invHeaders);
            invoic_Frame.setInvhdrmodel(hdrModel);
            invoic_Frame.getInvTable_T().setModel(hdrModel);
           
        }
        
       // TODO
       }
        catch(IOException e) {
            JOptionPane.showMessageDialog(invoic_Frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch(ParseException e) {
            JOptionPane.showMessageDialog(invoic_Frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancel() {
        int scInv = invoic_Frame.getInvItems_T().getSelectedRow();
        int index = invoic_Frame.getInvTable_T().getSelectedRow();
        if(scInv != -1)
        {
            invoic_Frame.getInvLine().remove(scInv);
            Model_Of_Invoice_Item itemmodel = (Model_Of_Invoice_Item)invoic_Frame.getInvItems_T().getModel();
            itemmodel.fireTableDataChanged();
            invoic_Frame.getInvTotal_L().setText(""+invoic_Frame.getInvHdr().get(index).get_Total());
            invoic_Frame.getInvhdrmodel().fireTableDataChanged();
            invoic_Frame.getInvTable_T().setRowSelectionInterval(index, index);
        }
    }

    private void inv_NewDialog_Cancel() {
        invoic_HeaderD.setVisible(false);
        invoic_HeaderD.dispose();
        invoic_HeaderD = null;
        
    }

    private void inv_NewDialog_Ok() {
        invoic_HeaderD.setVisible(false);
        int index =0 ;
        String [] S = {"",""};
        S[0] = invoic_HeaderD.getAeraOfCustomer().getText();
        S[1] = invoic_HeaderD.getAeraOfDate().getText();
        
        Date date = new Date();
        try{
        date = Invocie_Frame_SIG.sDateFormat.parse(S[1]);
        } catch(ParseException e) {
            JOptionPane.showMessageDialog(invoic_Frame,"Error Parsing", "Wrong Date Format", JOptionPane.ERROR_MESSAGE);
        }
        
        for(Invoice_Header hdr : invoic_Frame.getInvHdr())
        {
            if(hdr.get_Invoice_Number() > index)
            {
                index = hdr.get_Invoice_Number() ;
                // max
            }
        }
        index++;
        
        Invoice_Header invH = new Invoice_Header(index, date, S[0]);
        invoic_Frame.getInvHdr().add(invH);
        invoic_Frame.getInvhdrmodel().fireTableDataChanged();
        
       
        invoic_HeaderD.dispose();
        invoic_HeaderD = null;
    }

    private void itemNewDialog_Cancel() {
         invoic_LineD.setVisible(false);
         invoic_LineD.dispose();
         invoic_LineD = null;
    }

    
    private void item_NewDialog_Ok() {
         invoic_LineD.setVisible(false);
          int scInv = invoic_Frame.getInvTable_T().getSelectedRow();
         int cnt = 1 ; 
         double prc = 1 ;
        String [] S = {"","",""};
        S[0] = invoic_LineD.get_Area_Of_ItemName().getText();
        S[1] = invoic_LineD.get_Area_Of_ItemPrice().getText();
        S[2] = invoic_LineD.get_Area_Of_ItemCount().getText();
        
        try{
            cnt = Integer.parseInt(S[2]); // or price
            }
        catch(NumberFormatException e)
            { 
                JOptionPane.showMessageDialog(invoic_Frame,"Error Converting", "Undefined Number Format", JOptionPane.ERROR_MESSAGE);
            }
        try{
            prc = Double.parseDouble(S[1]); 
            }
        catch(NumberFormatException e)
            { 
                JOptionPane.showMessageDialog(invoic_Frame,"Error Converting", "Undefined Number Format", JOptionPane.ERROR_MESSAGE);
            }
        if(scInv != -1)
        {       
            Invoice_Line invL = new Invoice_Line(S[0], prc , cnt , invoic_Frame.getInvHdr().get(scInv) );
            invoic_Frame.getInvLine().add(invL);
            
            //invFrame.getInvitemmodel().fireTableDataChanged();
            Model_Of_Invoice_Item itemmodel = (Model_Of_Invoice_Item)invoic_Frame.getInvItems_T().getModel();
            itemmodel.fireTableDataChanged();
            invoic_Frame.getInvhdrmodel().fireTableDataChanged();

        }
        invoic_Frame.getInvTable_T().setRowSelectionInterval(scInv, scInv);
         
         invoic_LineD.dispose();
         invoic_LineD = null;
         
         
    }
    
   private void printDataInConsol() {
	        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	        for (Invoice_Header hdr : invoic_Frame.getInvHdr()) {
                    System.out.println("________________________________________________________");
	            System.out.println(hdr.toString());
	        }
	        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	    } 
}
