/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Cashier;
import Customer.MainPage;
import DBconnection.Connect;
import DBconnection.mgrDashboard;
import DBconnection.Order;
import DBconnection.cashier;
import DBconnection.getDate;
import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Gimhan
 */
public class cashierDashboard extends javax.swing.JPanel {

    /**
     * Creates new form Dashboard
     */
    String cashierID="EM002";
    public cashierDashboard() {   
        
        initComponents();
        refresh();       
    }
    public cashierDashboard(String cashierID) {   
        this.cashierID=cashierID;
        initComponents();
        refresh();       
    }
    
    private void refresh(){
       try { 
           jScrollPane1.setEnabled(true);
           mgrDashboard obj1=new mgrDashboard();
           cashier obj2=new cashier();
           lbl_income.setText(Integer.toString(obj1.monthlyIncome())+".00");
           lbl_pendingOdr.setText(Integer.toString(obj2.getPendingOrderCount()));
           getData();
           if(jTable_dBoard.getRowCount()!=0){
            btn_hold.setEnabled(false);
            int row=jTable_dBoard.getSelectedRow();
            String a=(String) jTable_dBoard.getValueAt(row, 3);
            if("Hold".equals(a)){
                btn_hold.setText("Unhold");
                btn_hold.setEnabled(true);
            }
            else if("Pending".equals(a)){
                btn_hold.setText("Hold");
                btn_hold.setEnabled(true);
            }       
        }else{
            btn_hold.setText("Hold");
            btn_hold.setEnabled(false);
        }
        cancelVieewCompleteBtn();
        } catch (Exception ex) {
            Logger.getLogger(cashierDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getData(){        
        try{
            clearTable();
            Connect obj = new Connect();
            Connection c = obj.getConnection();//Establish the connection
            cashier obj2=new cashier();
            try{ //int q=1;System.out.println(q++); <- tester
                getDate obj1 =new getDate();
                String date=obj1.dateOnly();
                Statement stmt = c.createStatement();//Prepare statement
                ResultSet rs; //"+date+"
                //pending orders
                rs = stmt.executeQuery("select o.ORDER_NUMBER, o.ORDER_TYPE, o.ORDER_DATETIME,o.STATUS as o_sts,i.STATUS as i_sts from CUSTOMER c INNER JOIN ORDER_T o ON o.CUSTOMER_ID=c.CUSTOMER_ID " +
                    "INNER JOIN INVOICE i ON i.ORDERID=o.ORDER_NUMBER WHERE (ORDER_DATETIME > '2023-02-01 00:00:00' AND ORDER_DATETIME <'"+date+" 23:59:59') AND o.STATUS='Pending'"); //Tables need to join
                while(rs.next()){
                    String oID=rs.getString("ORDER_NUMBER");
                    String otype=rs.getString("ORDER_TYPE");
                    String time=rs.getString("ORDER_DATETIME");
                    String o_sts=rs.getString("o_sts");
                    String i_sts=rs.getString("i_sts");
                    
                    String tbData[]={oID,otype,time,o_sts,i_sts,Integer.toString(obj2.getorderTotal(oID))};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable_dBoard.getModel(); 
                    tblModel.addRow(tbData);
                }
                //hold orders
                rs = stmt.executeQuery("select o.ORDER_NUMBER, o.ORDER_TYPE, o.ORDER_DATETIME,o.STATUS as o_sts,i.STATUS as i_sts from CUSTOMER c INNER JOIN ORDER_T o ON o.CUSTOMER_ID=c.CUSTOMER_ID " +
                    "INNER JOIN INVOICE i ON i.ORDERID=o.ORDER_NUMBER WHERE (ORDER_DATETIME > '2023-02-01 00:00:00' AND ORDER_DATETIME <'"+date+" 23:59:59') AND o.STATUS='Hold'"); //Tables need to join
                while(rs.next()){
                    String oID=rs.getString("ORDER_NUMBER");
                    String otype=rs.getString("ORDER_TYPE");
                    String time=rs.getString("ORDER_DATETIME");
                    String o_sts=rs.getString("o_sts");
                    String i_sts=rs.getString("i_sts");
                    
                    String tbData[]={oID,otype,time,o_sts,i_sts,Integer.toString(obj2.getorderTotal(oID))};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable_dBoard.getModel(); 
                    tblModel.addRow(tbData);
                }
                //Finish orders
                rs = stmt.executeQuery("select o.ORDER_NUMBER, o.ORDER_TYPE, o.ORDER_DATETIME,o.STATUS as o_sts,i.STATUS as i_sts from CUSTOMER c INNER JOIN ORDER_T o ON o.CUSTOMER_ID=c.CUSTOMER_ID " +
                    "INNER JOIN INVOICE i ON i.ORDERID=o.ORDER_NUMBER WHERE (ORDER_DATETIME > '2023-02-01 00:00:00' AND ORDER_DATETIME <'"+date+" 23:59:59') AND o.STATUS='Finish'"); //Tables need to join
                while(rs.next()){
                    String oID=rs.getString("ORDER_NUMBER");
                    String otype=rs.getString("ORDER_TYPE");
                    String time=rs.getString("ORDER_DATETIME");
                    String o_sts=rs.getString("o_sts");
                    String i_sts=rs.getString("i_sts");
                    
                    String tbData[]={oID,otype,time,o_sts,i_sts,Integer.toString(obj2.getorderTotal(oID))};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable_dBoard.getModel(); 
                    tblModel.addRow(tbData);
                }
                //Completed orders
                rs = stmt.executeQuery("select o.ORDER_NUMBER, o.ORDER_TYPE, o.ORDER_DATETIME,o.STATUS as o_sts,i.STATUS as i_sts from CUSTOMER c INNER JOIN ORDER_T o ON o.CUSTOMER_ID=c.CUSTOMER_ID " +
                    "INNER JOIN INVOICE i ON i.ORDERID=o.ORDER_NUMBER WHERE (ORDER_DATETIME > '2023-02-01 00:00:00' AND ORDER_DATETIME <'"+date+" 23:59:59') AND o.STATUS='Completed'"); //Tables need to join
                while(rs.next()){
                    String oID=rs.getString("ORDER_NUMBER");
                    String otype=rs.getString("ORDER_TYPE");
                    String time=rs.getString("ORDER_DATETIME");
                    String o_sts=rs.getString("o_sts");
                    String i_sts=rs.getString("i_sts");
                    
                    String tbData[]={oID,otype,time,o_sts,i_sts,Integer.toString(obj2.getorderTotal(oID))};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable_dBoard.getModel(); 
                    tblModel.addRow(tbData);
                }
                btn_hold.setEnabled(false);
                if(jTable_dBoard.getRowCount()!=0){
                    jTable_dBoard.setRowSelectionInterval(0, 0);
                    int row=jTable_dBoard.getSelectedRow();
                    String a=(String) jTable_dBoard.getValueAt(row, 3);
                    if("Hold".equals(a)){
                        btn_hold.setText("Pending");
                        btn_hold.setEnabled(true);
                    }
                    else if("Pending".equals(a)){
                        btn_hold.setText("Hold");
                        btn_hold.setEnabled(true);
                    }       
                }
                cancelVieewCompleteBtn();
            }
            catch(SQLException ex)//Is database has a problem, this catch stetment catch it
            {
                System.out.println(ex);
            }
            finally{
                c.close();
            }
        }
        catch(Exception ex)//Is database has a problem, this catch stetment catch it
        {
            Logger.getLogger(cashierDashboard.class.getName()).log(Level.SEVERE, null,ex); 
        }
    }
    
    public void clearTable(){
        DefaultTableModel tblModel =(DefaultTableModel)jTable_dBoard.getModel(); 
        int rowCount = tblModel.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
    }
    
    private void cancelVieewCompleteBtn(){
        int row=jTable_dBoard.getSelectedRow();
        String a=(String) jTable_dBoard.getValueAt(row, 3);
        if("Hold".equals(a)){
            btn_viewtheOdr.setEnabled(true);
            btn_cancel.setEnabled(true);
            btn_completeOdr.setEnabled(false);
        }
        else{
            btn_completeOdr.setEnabled(false);
            if("Finish".equals(a))
                btn_completeOdr.setEnabled(true);
            btn_viewtheOdr.setEnabled(false);
            btn_cancel.setEnabled(false);
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

        jLabel1 = new javax.swing.JLabel();
        lbl_income = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_delivered = new javax.swing.JLabel();
        lbl_pendingOdr = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_refresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_dBoard = new javax.swing.JTable();
        btn_viewtheOdr = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        btn_completeOdr = new javax.swing.JButton();
        btn_hold = new javax.swing.JButton();
        lbl_background = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jLabel1.setText("Today Imcome");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        lbl_income.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lbl_income.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_income.setText("0.00");
        add(lbl_income, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jLabel2.setText("Pending Order count");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, -1, -1));

        lbl_delivered.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lbl_delivered.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_delivered.setText("0");
        add(lbl_delivered, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 70, 30, -1));

        lbl_pendingOdr.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lbl_pendingOdr.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_pendingOdr.setText("0");
        add(lbl_pendingOdr, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, 30, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jLabel5.setText("Delivered orders");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, -1, -1));

        btn_refresh.setText("Refresh");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });
        add(btn_refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 60, -1, -1));

        jTable_dBoard.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTable_dBoard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Order type", "Ordered Time", "Status", "Payment Status", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_dBoard.setRowHeight(28);
        jTable_dBoard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_dBoardMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_dBoard);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 1050, -1));

        btn_viewtheOdr.setText("Edit Order");
        btn_viewtheOdr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_viewtheOdrActionPerformed(evt);
            }
        });
        add(btn_viewtheOdr, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 660, 140, 40));

        btn_cancel.setText("Cancel the order");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });
        add(btn_cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 660, 160, 40));

        btn_completeOdr.setText("Complete the order");
        btn_completeOdr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_completeOdrActionPerformed(evt);
            }
        });
        add(btn_completeOdr, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 650, 180, 50));

        btn_hold.setText("Hold");
        btn_hold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_holdActionPerformed(evt);
            }
        });
        add(btn_hold, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 660, 110, 40));
        add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 740));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        int row=jTable_dBoard.getSelectedRow();System.out.println("AAAAAAA "+row);       
        if(row>=0){//If there are records in the table
            String a1=(String) jTable_dBoard.getValueAt(row, 0);
            refresh();
            if(jTable_dBoard.isEnabled()==false)
            {           
                DefaultTableModel tblModel =(DefaultTableModel)jTable_dBoard.getModel(); 
                int rowCount = tblModel.getRowCount();
                //check rows
                String a2="";
                for (int i = 0; i < rowCount; i++) {
                    a2=(String) jTable_dBoard.getValueAt(i, 0);
                    if(a1.equals(a2)){
                        jTable_dBoard.setRowSelectionInterval(i, i);
                        break;
                    }
                }
                cancelVieewCompleteBtn();
                jTable_dBoard.setEnabled(true);
            }
        }
        else{
            refresh();
            if(jTable_dBoard.isEnabled()==false)
            {           
                DefaultTableModel tblModel =(DefaultTableModel)jTable_dBoard.getModel(); 
                int rowCount = tblModel.getRowCount();
                if(rowCount!=0){
                    jTable_dBoard.setRowSelectionInterval(0, 0);
                cancelVieewCompleteBtn();
                jTable_dBoard.setEnabled(true);
                }               
            }
        }
            
        
        
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void btn_holdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_holdActionPerformed
        try {
            int row=jTable_dBoard.getSelectedRow();
            String a1=(String) jTable_dBoard.getValueAt(row, 0);
            String a2=(String) jTable_dBoard.getValueAt(row, 3);
            
            cashier obj=new cashier();
            if("Hold".equals(a2)){
                obj.setHoldUnhold(a1,"Pending");
            }  
            else if("Pending".equals(a2)){
                obj.setHoldUnhold(a1,"Hold");
            }
            getData();
            DefaultTableModel tblModel =(DefaultTableModel)jTable_dBoard.getModel(); 
            int rowCount = tblModel.getRowCount();
            //check rows
            for (int i = 0; i < rowCount; i++) {
                a2=(String) jTable_dBoard.getValueAt(i, 0);
                if(a1.equals(a2)){
                    jTable_dBoard.setRowSelectionInterval(i, i);
                    break;
                }
            }
            cancelVieewCompleteBtn();
        } catch (Exception ex) {
            Logger.getLogger(cashierDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_holdActionPerformed

    private void jTable_dBoardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_dBoardMouseClicked
        if(jTable_dBoard.isEnabled()==true){
            if(jTable_dBoard.getRowCount()!=0){
            btn_hold.setEnabled(false);
            int row=jTable_dBoard.getSelectedRow();
            String a=(String) jTable_dBoard.getValueAt(row, 3);
            if("Hold".equals(a)){
                btn_hold.setText("Unhold");
                btn_hold.setEnabled(true);
            }
            else if("Pending".equals(a)){
                btn_hold.setText("Hold");
                btn_hold.setEnabled(true);
            }       
        }else{
            btn_hold.setText("Hold");
            btn_hold.setEnabled(false);
        }
        cancelVieewCompleteBtn();
        }
        
        
    }//GEN-LAST:event_jTable_dBoardMouseClicked

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        int row=jTable_dBoard.getSelectedRow();
        String a=(String) jTable_dBoard.getValueAt(row, 4);
        if("Paid".equals(a)){
            JOptionPane.showConfirmDialog((Component) null, "Sorry! Paid orders can't cancel",
        "alert", JOptionPane.ERROR_MESSAGE);
        }else{ 
            int result = JOptionPane.showConfirmDialog((Component) null, "Are you sure?",
        "alert", JOptionPane.YES_NO_OPTION);
        System.out.println("Result : "+result);
        if(result==0){
            try {
                String OID=(String) jTable_dBoard.getValueAt(row, 0);
                Order obj=new Order();
                obj.cancelOrder(OID); 
                refresh();                
            } catch (Exception ex) {
                Logger.getLogger(cashierDashboard.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showConfirmDialog((Component) null, "Database Error",
        "alert", JOptionPane.ERROR_MESSAGE);
            }
        }
        }
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_completeOdrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_completeOdrActionPerformed
        // Need to code
        int result = JOptionPane.showConfirmDialog((Component) null, "Are you sure?",
        "alert", JOptionPane.YES_NO_OPTION);
        System.out.println("Result : "+result);
        if(result==0){
            try {
                int row=jTable_dBoard.getSelectedRow();
                String OID=(String) jTable_dBoard.getValueAt(row, 0);
                Order obj=new Order();
                obj.completeOdr(OID);
                refresh();
            } catch (Exception ex) {
                Logger.getLogger(cashierDashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_completeOdrActionPerformed
    
    private void btn_viewtheOdrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_viewtheOdrActionPerformed
        int row=jTable_dBoard.getSelectedRow();
        String a=(String) jTable_dBoard.getValueAt(row, 4);
        if("Paid".equals(a)){
            JOptionPane.showMessageDialog(new JFrame(), "Sorry! Paid orders can't cancel",
                    "Imformation", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            try {
                //All btns and jSTable set unenabled
                jTable_dBoard.setEnabled(false);
                btn_viewtheOdr.setEnabled(false);
                btn_cancel.setEnabled(false);
                btn_completeOdr.setEnabled(false);
                btn_hold.setEnabled(false);
                
                String OID=(String) jTable_dBoard.getValueAt(row, 0);
                String Otype=(String) jTable_dBoard.getValueAt(row, 1);
                cashier obj2=new cashier();
                int tp=obj2.getCustomerTp(OID);
            
                MainPage obj =new MainPage(true,cashierID,OID,Otype,tp,obj2.getorderTotal(OID),obj2.getfooditemsOfOrder(OID),obj2.getQtysOfOrder(OID));
                obj.show();
                clearTable();
                //Can't close cashier fframe
                //CashierMain obj8 =new CashierMain();
            
            } catch (Exception ex) {
                Logger.getLogger(cashierDashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_viewtheOdrActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_completeOdr;
    private javax.swing.JButton btn_hold;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_viewtheOdr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_dBoard;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_delivered;
    private javax.swing.JLabel lbl_income;
    private javax.swing.JLabel lbl_pendingOdr;
    // End of variables declaration//GEN-END:variables
}
