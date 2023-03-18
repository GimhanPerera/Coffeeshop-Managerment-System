/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Cashier;

import DBconnection.Connect;
import com.fazecast.jSerialComm.SerialPort;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gimhan
 */
public class IssueLoyaltyCard extends javax.swing.JPanel  implements Runnable{

    /**
     * Creates new form IssueLoyaltyCard
     */
    //Thread testing - Card read.
    private static boolean exit=false;private static String pin=""; 
    @Override
    public void run() {
        reading();
    }
    
    public void reading(){
        String S="";
char[] c = new char[8];
        
        SerialPort [] AvailablePorts = SerialPort.getCommPorts();   
        //Open the first Available port
        SerialPort MySerialPort = AvailablePorts[0];//problem happen here if not connected the Aduino correctly
        int BaudRate = 9600;
        int DataBits = 8;
        int StopBits = SerialPort.ONE_STOP_BIT;
        int Parity   = SerialPort.NO_PARITY;
        //Sets all serial port parameters at one time
        MySerialPort.setComPortParameters(BaudRate,DataBits,StopBits,Parity);

        //Set Read Time outs
        MySerialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 0);
        MySerialPort.openPort(); //open the port
                                 //Arduino May get reset 
        //Thread.sleep(2000);
        if (MySerialPort.isOpen())//Check whether port open/not
              System.out.println("is Open ");
        else{
           System.out.println(" Port not open ");
           exit=true;
        }
        try {
                int numRead=0;
                int time=0;String q="9B4D7422";
                while (time<=5){//set time here
                    
                    byte[] readBuffer = new byte[100];

                    numRead = MySerialPort.readBytes(readBuffer, readBuffer.length);
                    System.out.print("Time: "+time);
                    System.out.print(" ,Read " + numRead + " bytes -");
      
                    //Convert bytes to String
                    S = new String(readBuffer, "UTF-8"); 
                    System.out.println("Received -> "+ S);
                    time++;  
                    Thread.sleep(800);
                    if(exit){
                        exit=false;
                        break;
                    }else if(numRead==10){System.out.println(" TEST 1 ");System.out.println(S.equals(q));
                        //check the card
                        
                        exit=false;
                        break;
                    }
                }    
        }        
        catch (Exception e){
            e.printStackTrace(); 
        }finally{
            MySerialPort.closePort(); //Close the port
        }      
        pin = S;
        System.out.println("PIN : "+pin);
    }
    //Thread testing
    
    public IssueLoyaltyCard() {
        try {
            initComponents();        
            getAllRequest();
            lbl_ok.setVisible(false);
            lbl_notOk.setVisible(false);
            lbl_waiting.setVisible(false);
        } catch (Exception ex) {
            Logger.getLogger(IssueLoyaltyCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getAllRequest() throws Exception{
        Connect obj = new Connect();
        Connection c = obj.getConnection();  //getConnection();//Establish the connection
        
        try{ //int q=1;System.out.println(q++); <- tester
            
                Statement stmt = c.createStatement();//Prepare statement
                ResultSet rs = stmt.executeQuery("select MOBILE_NUMBER,POINTS from CUSTOMER WHERE REQUEST='1'"); //SQL stetment
                while(rs.next()){
                    String tp=rs.getString("MOBILE_NUMBER");
                    String points=rs.getString("POINTS");
                    String tbData[]={tp,points};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable.getModel(); 
                    tblModel.addRow(tbData);
                }
        }
        catch(SQLException ex)//Is database has a problem, this catch stetment catch it
        {
            System.out.println(ex);
        }
        finally{
            c.close(); 
        }   
    }
    
    public void getAllIssuedCardOwners() throws Exception{
        Connect obj = new Connect();
        Connection c = obj.getConnection();  //getConnection();//Establish the connection
        
        try{ //int q=1;System.out.println(q++); <- tester
            
                Statement stmt = c.createStatement();//Prepare statement
                ResultSet rs = stmt.executeQuery("select MOBILE_NUMBER,POINTS from CUSTOMER WHERE REQUEST='2'"); //SQL stetment
                while(rs.next()){
                    String tp=rs.getString("MOBILE_NUMBER");
                    String points=rs.getString("POINTS");
                    String tbData[]={tp,points};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable.getModel(); 
                    tblModel.addRow(tbData);
                }
        }
        catch(SQLException ex)//Is database has a problem, this catch stetment catch it
        {
            System.out.println(ex);
        }
        finally{
            c.close(); 
        }   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        btn_issueCard = new javax.swing.JButton();
        btn_reject = new javax.swing.JButton();
        rdo_requests = new javax.swing.JRadioButton();
        rdo_issued = new javax.swing.JRadioButton();
        lbl_waiting = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_scanning = new javax.swing.JLabel();
        lbl_ok = new javax.swing.JLabel();
        lbl_notOk = new javax.swing.JLabel();
        lbl_background = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer Telephone Number", "Loyalty Points"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable.setRowHeight(29);
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 630, 490));

        btn_issueCard.setText("Issue Card");
        btn_issueCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_issueCardActionPerformed(evt);
            }
        });
        add(btn_issueCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 580, 110, 40));

        btn_reject.setText("Reject requset");
        btn_reject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rejectActionPerformed(evt);
            }
        });
        add(btn_reject, new org.netbeans.lib.awtextra.AbsoluteConstraints(832, 640, 140, 30));

        buttonGroup1.add(rdo_requests);
        rdo_requests.setSelected(true);
        rdo_requests.setText("Loyalty card requests");
        rdo_requests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_requestsActionPerformed(evt);
            }
        });
        add(rdo_requests, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 230, -1, -1));

        buttonGroup1.add(rdo_issued);
        rdo_issued.setText("Issued customers");
        rdo_issued.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_issuedActionPerformed(evt);
            }
        });
        add(rdo_issued, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 190, -1, -1));

        lbl_waiting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tapping image resized.png"))); // NOI18N
        add(lbl_waiting, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 280, 240, 240));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Issue Loyalty card");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, -1, -1));

        lbl_scanning.setText("Scan the card");
        add(lbl_scanning, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 540, -1, -1));

        lbl_ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ok.jpg"))); // NOI18N
        add(lbl_ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 290, 230, 250));

        lbl_notOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/not ok.jpg"))); // NOI18N
        add(lbl_notOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 290, 240, 240));
        add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 740));
    }// </editor-fold>//GEN-END:initComponents

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked


    }//GEN-LAST:event_jTableMouseClicked

    private void btn_issueCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_issueCardActionPerformed
        if("Issue Card".equals(btn_issueCard.getText())){
            if(false){//validation
            
            }
            else{
                btn_issueCard.setText("Scan");
            lbl_waiting.setVisible(true);
            btn_reject.setEnabled(false);
            rdo_issued.setEnabled(false);
            rdo_requests.setEnabled(false);
            }   
        }        
        else if("Scan".equals(btn_issueCard.getText())){

            try {
            pin=""; 
            //Start scannig
            while(jPanel_customerInfo.isVisible()==true){}
            Conn t1=new Conn();
            t1.start();
            while(t1.isAlive()) {}
            System.out.println("THE PIN IS::"+t1.getPin());
            if("9B4D7422".equals(t1.getPin())){//correct card
                lbl_waiting.setVisible(false);System.out.println(" TEST 2 ");
                lbl_notOk.setVisible(false);
                lbl_ok.setVisible(true);
                btn_issueCard.setText("Done");
                txt_loyaltycard.setText(t1.getPin());
            }else{//wrong card
                lbl_waiting.setVisible(false);System.out.println(" TEST 3 ");
                lbl_notOk.setVisible(true);
                lbl_ok.setVisible(false);
                btn_issueCard.setText("Cancel");
                txt_loyaltycard.setText(t1.getPin());
            }
            //Thread.sleep(2000);
            //lbl_waiting.setVisible(true);
            //lbl_ok.setVisible(false);
            //lbl_notOk.setVisible(false);
            //jPanel_loyaltycard.setVisible(false);
            //jPanel_customerInfo.setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(IssueLoyaltyCard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        else if(btn_issueCard.getText().equals("Done")){
            //this.exit=true;
            lbl_notOk.setVisible(false);
            lbl_ok.setVisible(false);
            lbl_waiting.setVisible(false);
            btn_reject.setEnabled(true);
            rdo_issued.setEnabled(true);
            rdo_requests.setEnabled(true);
            btn_issueCard.setText("Scan");
        }
        else if(btn_issueCard.getText().equals("Cancel")){
            this.exit=false;
            lbl_waiting.setVisible(true);
            lbl_notOk.setVisible(false);
            lbl_ok.setVisible(false);
            jPanel_loyaltycard.setVisible(false);
            jPanel_customerInfo.setVisible(true);
            btn_next.setEnabled(true);
            btn_back.setEnabled(true);
            btn_issueCard.setText("Scan");
        }
        else if(btn_issueCard.getText().equals("Scan again")){//NOT USEFUL and need to correct
            lbl_notOk.setVisible(false);
            lbl_ok.setVisible(false);
            btn_issueCard.setText("Cancel");
        }
    }//GEN-LAST:event_btn_issueCardActionPerformed

    private void btn_rejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rejectActionPerformed

    }//GEN-LAST:event_btn_rejectActionPerformed

    private void rdo_requestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_requestsActionPerformed
        try {
            clearTable();
            getAllRequest();
        } catch (Exception ex) {
            Logger.getLogger(IssueLoyaltyCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rdo_requestsActionPerformed

    private void rdo_issuedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_issuedActionPerformed
        try {
            clearTable();
            getAllIssuedCardOwners();
        } catch (Exception ex) {
            Logger.getLogger(IssueLoyaltyCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rdo_issuedActionPerformed
    private void clearTable(){
        DefaultTableModel tblModel =(DefaultTableModel)jTable.getModel(); 
        int rowCount = tblModel.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_issueCard;
    private javax.swing.JButton btn_reject;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_notOk;
    private javax.swing.JLabel lbl_ok;
    private javax.swing.JLabel lbl_scanning;
    private javax.swing.JLabel lbl_waiting;
    private javax.swing.JRadioButton rdo_issued;
    private javax.swing.JRadioButton rdo_requests;
    // End of variables declaration//GEN-END:variables
}
