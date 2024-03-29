/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Cashier;

import DBconnection.Connect;
import DBconnection.cashier;
import AduinoConnection.Conn;
import DBconnection.LoyaltyCard;
import Manager.LoyaltyCardPanel;
import com.fazecast.jSerialComm.SerialPort;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gimhan
 */
public class IssueLoyaltyCard extends javax.swing.JPanel{

    /**
     * Creates new form IssueLoyaltyCard
     */
    String cashierID="EM002";
    public IssueLoyaltyCard() {
        try {
            initComponents();
            jTable.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 15));
            lbl_ok.setVisible(false);
            lbl_notOk.setVisible(false);
            lbl_waiting.setVisible(false);
            lbl_scanstatus.setVisible(false);
            lbl_noReqeset.setVisible(false);
            jPanel5.setOpaque(false);
            getAllRequest();
        } 
        catch (Exception ex) {
            Logger.getLogger(IssueLoyaltyCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public IssueLoyaltyCard(String cashierID) {
        try {
            this.cashierID=cashierID;
            initComponents();
            jTable.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 15));
            lbl_ok.setVisible(false);
            lbl_notOk.setVisible(false);
            lbl_waiting.setVisible(false);
            lbl_scanstatus.setVisible(false);
            lbl_noReqeset.setVisible(false); 
            jPanel5.setOpaque(false);
            getAllRequest();
            
        } catch (Exception ex) {
            Logger.getLogger(IssueLoyaltyCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void getAllRequest() throws Exception{
        Connect obj = new Connect();
        Connection c = obj.getConnection();  //getConnection();//Establish the connection
        clearTable();
        try{
                Statement stmt = c.createStatement();//Prepare statement
                ResultSet rs = stmt.executeQuery("select MOBILE_NUMBER,POINTS from CUSTOMER WHERE REQUEST='1'"); //SQL stetment
                while(rs.next()){
                    String tp=rs.getString("MOBILE_NUMBER");
                    String points=rs.getString("POINTS");
                    String tbData[]={tp,points};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable.getModel(); 
                    tblModel.addRow(tbData);
                }
                if(jTable.getRowCount()==0){
                    btn_reject.setEnabled(false);
                    btn_issueCard.setEnabled(false);
                    lbl_noReqeset.setVisible(true);
                }
                else{
                    jTable.setRowSelectionInterval(0, 0); 
                    btn_reject.setEnabled(true);
                    btn_issueCard.setEnabled(true);
                    lbl_noReqeset.setVisible(false);
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
                ResultSet rs = stmt.executeQuery("select MOBILE_NUMBER,POINTS from CUSTOMER WHERE REQUEST='2' AND MOBILE_NUMBER!=0100000001"); //SQL stetment
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
        lbl_noReqeset = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lbl_scanstatus = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        rdo_issued = new javax.swing.JRadioButton();
        rdo_requests = new javax.swing.JRadioButton();
        btn_issueCard = new javax.swing.JButton();
        btn_reject = new javax.swing.JButton();
        jPanel_loyaltycard = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btn_scanmanage = new javax.swing.JButton();
        lbl_notOk = new javax.swing.JLabel();
        lbl_waiting = new javax.swing.JLabel();
        lbl_ok = new javax.swing.JLabel();
        lbl_background = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noReqeset.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_noReqeset.setText("No Loyalty card Requests");
        add(lbl_noReqeset, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 410, -1, -1));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 630, 490));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Issue Loyalty card");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, -1, -1));

        lbl_scanstatus.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lbl_scanstatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_scanstatus.setText("Scan New Loyalty Card");
        add(lbl_scanstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 730, -1));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroup1.add(rdo_issued);
        rdo_issued.setText("Issued customers");
        rdo_issued.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_issuedActionPerformed(evt);
            }
        });
        jPanel3.add(rdo_issued, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        buttonGroup1.add(rdo_requests);
        rdo_requests.setSelected(true);
        rdo_requests.setText("Loyalty card requests");
        rdo_requests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_requestsActionPerformed(evt);
            }
        });
        jPanel3.add(rdo_requests, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        btn_issueCard.setText("Issue Card");
        btn_issueCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_issueCardActionPerformed(evt);
            }
        });
        jPanel3.add(btn_issueCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 110, 40));

        btn_reject.setText("Reject requset");
        btn_reject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rejectActionPerformed(evt);
            }
        });
        jPanel3.add(btn_reject, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 180, 140, 30));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 400, 190, 220));

        jPanel_loyaltycard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });
        jPanel5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel5KeyPressed(evt);
            }
        });
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel_loyaltycard.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, 120, 60));

        btn_scanmanage.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_scanmanage.setText("Scan");
        btn_scanmanage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_scanmanage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_scanmanageMouseClicked(evt);
            }
        });
        btn_scanmanage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_scanmanageActionPerformed(evt);
            }
        });
        jPanel_loyaltycard.add(btn_scanmanage, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 450, 150, 60));

        lbl_notOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Not_final updated.png"))); // NOI18N
        jPanel_loyaltycard.add(lbl_notOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        lbl_waiting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tapping image updated.png"))); // NOI18N
        jPanel_loyaltycard.add(lbl_waiting, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 330, 320));

        lbl_ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ok_final updated.png"))); // NOI18N
        jPanel_loyaltycard.add(lbl_ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 340, 330));

        add(jPanel_loyaltycard, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 750, 570));
        add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 740));
    }// </editor-fold>//GEN-END:initComponents

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked


    }//GEN-LAST:event_jTableMouseClicked

    private void btn_issueCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_issueCardActionPerformed
        if(jTable.getRowCount()!=0){
            jLabel2.setVisible(false);
            lbl_scanstatus.setText("Scan Loyalty Card");
            lbl_scanstatus.setVisible(true);
            jPanel3.setVisible(false);
            jScrollPane1.setVisible(false);
            lbl_waiting.setVisible(true);
            jPanel_loyaltycard.setVisible(true);
            jPanel5.setVisible(true);
        }
    }//GEN-LAST:event_btn_issueCardActionPerformed

    private void btn_rejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rejectActionPerformed
        int result = JOptionPane.showConfirmDialog((Component) null, "Are you sure?",
        "alert", JOptionPane.YES_NO_OPTION);
        System.out.println(result);
        if(result==0){
            try {
                int row=jTable.getSelectedRow();
                String a=(String) jTable.getValueAt(row, 0);
                LoyaltyCard obj=new LoyaltyCard();
                obj.rejectRequest(a);
                getAllRequest();
                
            } catch (Exception ex) {
                Logger.getLogger(LoyaltyCardPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_rejectActionPerformed

    private void rdo_requestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_requestsActionPerformed
        try {
            btn_reject.setEnabled(true);
            btn_issueCard.setEnabled(true);
            getAllRequest();
        } 
        catch (Exception ex) {
            Logger.getLogger(IssueLoyaltyCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rdo_requestsActionPerformed

    private void rdo_issuedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_issuedActionPerformed
        try {
            
            lbl_noReqeset.setVisible(false);
            btn_reject.setEnabled(false);
            btn_issueCard.setEnabled(false);
            clearTable();
            getAllIssuedCardOwners();
        } catch (Exception ex) {
            Logger.getLogger(IssueLoyaltyCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rdo_issuedActionPerformed

    private void btn_scanmanageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_scanmanageMouseClicked
        if(btn_scanmanage.getText().equals("Scan")){
            try {
                lbl_scanstatus.setVisible(true);
                lbl_scanstatus.setText("Scanning...");
                //Start scannig
                Conn t1=new Conn();
                LoyaltyCard obj1=new LoyaltyCard();
                t1.setReadsuccess();
                t1.start();
                while(t1.isAlive()) {}
                System.out.println("THE PIN IS::"+t1.getPin());
                if(!t1.isadu_connected()){//if arduino not connected 
                    lbl_waiting.setVisible(false);
                    lbl_notOk.setVisible(true);
                    lbl_ok.setVisible(false);
                    btn_scanmanage.setText("Cancel");
                    lbl_scanstatus.setVisible(true);
                    lbl_scanstatus.setText("Scanner is not connected correctly");
                }
                else if(!t1.getReadsuccess()){//not read
                    lbl_waiting.setVisible(false);
                    lbl_notOk.setVisible(true);
                    lbl_ok.setVisible(false);
                    lbl_scanstatus.setVisible(true);
                    lbl_scanstatus.setText("Scanning unsuccessful!!");
                    btn_scanmanage.setText("Cancel");
                }
                else if(obj1.checkIssued(t1.getPin())!=1){//check is it FREE
                    lbl_waiting.setVisible(false);
                    lbl_notOk.setVisible(true);
                    lbl_ok.setVisible(false);
                    btn_scanmanage.setText("Cancel");
                    lbl_scanstatus.setVisible(true);
                    lbl_scanstatus.setText("This card already issued or not in the system");
                }else{//Issue card
                    lbl_waiting.setVisible(false);
                    lbl_notOk.setVisible(false);
                    lbl_ok.setVisible(true);
                    btn_scanmanage.setText("Done");
                    lbl_scanstatus.setVisible(true);
                    lbl_scanstatus.setText(t1.getPin()+" card issued successfully");
                    int row=jTable.getSelectedRow();
                    String a="0"+(String) jTable.getValueAt(row, 0);
                    obj1.issueCard(t1.getPin(),a,cashierID);
                }
            } catch (Exception ex) {
                Logger.getLogger(IssueLoyaltyCard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(btn_scanmanage.getText().equals("Done")){
            lbl_waiting.setVisible(true);
            lbl_notOk.setVisible(false);
            lbl_ok.setVisible(false);
            
            btn_scanmanage.setText("Scan");
            lbl_scanstatus.setVisible(false);
            jPanel3.setVisible(true);
            jScrollPane1.setVisible(true);
            jLabel2.setVisible(true);
            lbl_scanstatus.setVisible(false);
            jPanel_loyaltycard.setVisible(false);    
            try {
                getAllRequest();
            } catch (Exception ex) {
                Logger.getLogger(IssueLoyaltyCard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(btn_scanmanage.getText().equals("Cancel")){
            lbl_waiting.setVisible(true);
            lbl_notOk.setVisible(false);
            lbl_ok.setVisible(false);
            btn_scanmanage.setText("Scan");
            lbl_scanstatus.setVisible(false);
            jPanel3.setVisible(true);
            jScrollPane1.setVisible(true);
            jLabel2.setVisible(true);
            lbl_scanstatus.setVisible(false);
            jPanel_loyaltycard.setVisible(false);           
            try {
                getAllRequest();
            } catch (Exception ex) {
                Logger.getLogger(IssueLoyaltyCard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(btn_scanmanage.getText().equals("Scan again")){//NOT USEFUL
            lbl_notOk.setVisible(false);
            lbl_ok.setVisible(false);
            btn_scanmanage.setText("Cancel");
            lbl_scanstatus.setVisible(false);
            jPanel3.setVisible(true);
            jScrollPane1.setVisible(true);
        }
    }//GEN-LAST:event_btn_scanmanageMouseClicked

    private void btn_scanmanageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_scanmanageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_scanmanageActionPerformed

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        lbl_scanstatus.setVisible(true);
        lbl_scanstatus.setText("Scanning...");
        jPanel5.setVisible(false);
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel5KeyPressed

    }//GEN-LAST:event_jPanel5KeyPressed
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
    private javax.swing.JButton btn_scanmanage;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel_loyaltycard;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_noReqeset;
    private javax.swing.JLabel lbl_notOk;
    private javax.swing.JLabel lbl_ok;
    private javax.swing.JLabel lbl_scanstatus;
    private javax.swing.JLabel lbl_waiting;
    private javax.swing.JRadioButton rdo_issued;
    private javax.swing.JRadioButton rdo_requests;
    // End of variables declaration//GEN-END:variables
}
