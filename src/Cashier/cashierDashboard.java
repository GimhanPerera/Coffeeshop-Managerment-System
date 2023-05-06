/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Cashier;
import Customer.MainPage;
import DBconnection.Connect;
import DBconnection.Customer;
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
           lbl_income.setText(Integer.toString(obj1.todayIncome())+".00");
           lbl_delivered.setText(Integer.toString(obj1.orderCount()));
           lbl_pendingOdr.setText(Integer.toString(obj2.getPendingOrderCount()));//status pending or hold
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
                //Statting order date eka wenas karanna ona ada dawasata
                rs = stmt.executeQuery("select o.ORDER_NUMBER, o.ORDER_TYPE, o.ORDER_DATETIME,o.STATUS as o_sts,i.STATUS as i_sts from CUSTOMER c INNER JOIN ORDER_T o ON o.CUSTOMER_ID=c.CUSTOMER_ID " +
                    "INNER JOIN INVOICE i ON i.ORDERID=o.ORDER_NUMBER WHERE (ORDER_DATETIME > '"+date+" 00:00:00' AND ORDER_DATETIME <'"+date+" 23:59:59') AND o.STATUS='Pending' ORDER BY o.ORDER_DATETIME DESC"); //Tables need to join
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
                    "INNER JOIN INVOICE i ON i.ORDERID=o.ORDER_NUMBER WHERE (ORDER_DATETIME > '"+date+" 00:00:00' AND ORDER_DATETIME <'"+date+" 23:59:59') AND o.STATUS='Hold' ORDER BY o.ORDER_DATETIME DESC"); //Tables need to join
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
                    "INNER JOIN INVOICE i ON i.ORDERID=o.ORDER_NUMBER WHERE (ORDER_DATETIME > '2023-04-01 00:00:00' AND ORDER_DATETIME <'"+date+" 23:59:59') AND o.STATUS='Finish' ORDER BY o.ORDER_DATETIME DESC"); //Tables need to join
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
                    "INNER JOIN INVOICE i ON i.ORDERID=o.ORDER_NUMBER WHERE (ORDER_DATETIME > '"+date+" 00:00:00' AND ORDER_DATETIME <'"+date+" 23:59:59') AND o.STATUS='Completed' ORDER BY o.ORDER_DATETIME DESC"); //Tables need to join
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
    
    private void clearTable(){
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel_dashboard = new javax.swing.JPanel();
        lbl_income = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_pendingOdr = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_delivered = new javax.swing.JLabel();
        btn_refresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_dBoard = new javax.swing.JTable();
        btn_viewOrd = new javax.swing.JButton();
        btn_viewtheOdr = new javax.swing.JButton();
        btn_hold = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        btn_completeOdr = new javax.swing.JButton();
        jPanel_order = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea_bill = new javax.swing.JTextArea();
        btn_cancel1 = new javax.swing.JButton();
        btn_completeOdr1 = new javax.swing.JButton();
        jCheckBox_paid = new javax.swing.JCheckBox();
        jRadioButton_cash = new javax.swing.JRadioButton();
        jRadioButton_card = new javax.swing.JRadioButton();
        lbl_tableheader = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbl_c_id = new javax.swing.JLabel();
        lbl_name = new javax.swing.JLabel();
        lbl_tp = new javax.swing.JLabel();
        lbl_tables = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbl_background = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel_dashboard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_income.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lbl_income.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_income.setText("0.00");
        jPanel_dashboard.add(lbl_income, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 90, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jLabel1.setText("Today Imcome");
        jPanel_dashboard.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jLabel2.setText("Pending Order count");
        jPanel_dashboard.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        lbl_pendingOdr.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lbl_pendingOdr.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_pendingOdr.setText("0");
        jPanel_dashboard.add(lbl_pendingOdr, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 30, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jLabel5.setText("Today completed order count");
        jPanel_dashboard.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, -1, -1));

        lbl_delivered.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lbl_delivered.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_delivered.setText("0");
        jPanel_dashboard.add(lbl_delivered, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, 30, -1));

        btn_refresh.setText("Refresh");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });
        jPanel_dashboard.add(btn_refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 20, -1, -1));

        jTable_dBoard.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTable_dBoard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order Number", "Order type", "Ordered Time", "Status", "Payment Status", "Total"
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

        jPanel_dashboard.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 1050, -1));

        btn_viewOrd.setText("View Order");
        btn_viewOrd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_viewOrdActionPerformed(evt);
            }
        });
        jPanel_dashboard.add(btn_viewOrd, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 610, 140, 40));

        btn_viewtheOdr.setText("Edit Order");
        btn_viewtheOdr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_viewtheOdrActionPerformed(evt);
            }
        });
        jPanel_dashboard.add(btn_viewtheOdr, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 610, 140, 40));

        btn_hold.setText("Hold");
        btn_hold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_holdActionPerformed(evt);
            }
        });
        jPanel_dashboard.add(btn_hold, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 610, 110, 40));

        btn_cancel.setText("Cancel the order");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });
        jPanel_dashboard.add(btn_cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 610, 160, 40));

        btn_completeOdr.setText("Complete the order");
        btn_completeOdr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_completeOdrActionPerformed(evt);
            }
        });
        jPanel_dashboard.add(btn_completeOdr, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 600, 180, 50));

        add(jPanel_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1070, 670));

        jPanel_order.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane5.setHorizontalScrollBar(null);

        jTextArea_bill.setEditable(false);
        jTextArea_bill.setColumns(20);
        jTextArea_bill.setFont(new java.awt.Font("Segoe UI Emoji", 0, 20)); // NOI18N
        jTextArea_bill.setRows(5);
        jScrollPane5.setViewportView(jTextArea_bill);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 370, 510));

        jPanel_order.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 390, 530));

        btn_cancel1.setText("Back");
        btn_cancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancel1ActionPerformed(evt);
            }
        });
        jPanel_order.add(btn_cancel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 550, 160, 40));

        btn_completeOdr1.setText("Complete the order");
        btn_completeOdr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_completeOdr1ActionPerformed(evt);
            }
        });
        jPanel_order.add(btn_completeOdr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 470, 180, 50));

        jCheckBox_paid.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jCheckBox_paid.setText("Paid");
        jPanel_order.add(jCheckBox_paid, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 310, -1, -1));

        buttonGroup1.add(jRadioButton_cash);
        jRadioButton_cash.setText("By cash");
        jPanel_order.add(jRadioButton_cash, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 360, -1, -1));

        buttonGroup1.add(jRadioButton_card);
        jRadioButton_card.setText("By card");
        jPanel_order.add(jRadioButton_card, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 400, -1, -1));

        lbl_tableheader.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_tableheader.setText("Table(s)");
        jPanel_order.add(lbl_tableheader, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 240, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Customer ID");
        jPanel_order.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 120, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Customer Name");
        jPanel_order.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 160, -1, -1));

        lbl_c_id.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_c_id.setText("00000");
        jPanel_order.add(lbl_c_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 120, 230, -1));

        lbl_name.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_name.setText("Name");
        jPanel_order.add(lbl_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 160, 230, -1));

        lbl_tp.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_tp.setText("08767677");
        jPanel_order.add(lbl_tp, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 200, 230, -1));

        lbl_tables.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_tables.setText("No Table");
        jPanel_order.add(lbl_tables, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 240, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Tel. number");
        jPanel_order.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 200, -1, -1));

        add(jPanel_order, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1080, 710));
        add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 740));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        int row=jTable_dBoard.getSelectedRow();System.out.println("Row "+row);       
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
                    row=jTable_dBoard.getSelectedRow();
                    a2=(String) jTable_dBoard.getValueAt(row, 3);
                    if("Hold".equals(a2)){
                        btn_hold.setText("Unhold");
                    }  
                    else if("Pending".equals(a2)){
                        btn_hold.setText("Hold");
                    }
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
            JOptionPane.showMessageDialog(new JFrame(), "Sorry! Paid orders can't cancel",
                    "Imformation", JOptionPane.INFORMATION_MESSAGE);
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
        int row=jTable_dBoard.getSelectedRow();
        String a=(String) jTable_dBoard.getValueAt(row, 0);
        jPanel_dashboard.setVisible(false);
        jPanel_order.setVisible(true);
        orderCompleteItems(true);  
        setbill(a);
        getCustomerDetails();
    }//GEN-LAST:event_btn_completeOdrActionPerformed
    
    private void btn_viewtheOdrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_viewtheOdrActionPerformed
        int row=jTable_dBoard.getSelectedRow();
        String a=(String) jTable_dBoard.getValueAt(row, 4);
        if("Paid".equals(a)){
            JOptionPane.showMessageDialog(new JFrame(), "Sorry! Paid orders can't edit",
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

    private void btn_viewOrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_viewOrdActionPerformed
        int row=jTable_dBoard.getSelectedRow();
        String a=(String) jTable_dBoard.getValueAt(row, 0);
        jPanel_dashboard.setVisible(false);
        jPanel_order.setVisible(true);
        setbill(a);
        orderCompleteItems(false);
        getCustomerDetails();
    }//GEN-LAST:event_btn_viewOrdActionPerformed
    
    private void btn_cancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancel1ActionPerformed
        jPanel_dashboard.setVisible(true);
        jPanel_order.setVisible(false);
    }//GEN-LAST:event_btn_cancel1ActionPerformed

    private void btn_completeOdr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_completeOdr1ActionPerformed
        if(!jCheckBox_paid.isSelected()){
            JOptionPane.showMessageDialog(new JFrame(), "Payment should be done",
               "Imformation", JOptionPane.ERROR_MESSAGE);
        }
        else{
            int result = JOptionPane.showConfirmDialog((Component) null, "Are you sure?",
                "alert", JOptionPane.YES_NO_OPTION);
            System.out.println("Result : "+result);
            if(result==0){
                try {
                    int row=jTable_dBoard.getSelectedRow();
                    String OID=(String) jTable_dBoard.getValueAt(row, 0);
                    boolean cashPayemt=true;
                    Order obj=new Order();
                    if(jRadioButton_card.isSelected())
                        cashPayemt=false;
                    System.out.println(cashPayemt);
                    obj.completeOdr(OID,cashPayemt);
                    refresh();
                    jPanel_dashboard.setVisible(true);
                    jPanel_order.setVisible(false);
                } catch (Exception ex) {
                    Logger.getLogger(cashierDashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
    }//GEN-LAST:event_btn_completeOdr1ActionPerformed
    
    public void setbill(String o_id){
            try{
                Connect obj = new Connect();
                Connection c = obj.getConnection();//Establish the connection
                cashier obj2=new cashier();
                try{ //int q=1;System.out.println(q++); <- tester
                    int tot=0;
                    int discount=0;
                    getDate obj1 =new getDate();
                    String date=obj1.dateOnly();
                    Statement stmt = c.createStatement();//Prepare statement
                    ResultSet rs;
                    jTextArea_bill.setText("");
                    jTextArea_bill.append("========================\n");
                    rs = stmt.executeQuery("select * FROM order_t o INNER JOIN invoice i ON o.ORDER_NUMBER=i.ORDERID WHERE i.ORDERID='"+o_id+"'");
                    while(rs.next()){
                        String oID=rs.getString("o.ORDER_NUMBER");
                        String otype=rs.getString("o.ORDER_TYPE");
                        String time=rs.getString("o.ORDER_DATETIME");
                        tot=rs.getInt("i.AMOUNT");
                        discount=rs.getInt("i.DISCOUNT");
                        //Set payment settings
                        String i_sts=rs.getString("i.STATUS");
                        String pay_method=rs.getString("i.PAYMENT_METHOD");
                        if(pay_method.equals("CASH")){
                            jRadioButton_cash.setSelected(true);
                            jRadioButton_card.setSelected(false);
                        }
                        else{
                            jRadioButton_card.setSelected(true);
                            jRadioButton_cash.setSelected(false);
                        }
                        if(i_sts.equals("Paid")){
                            jCheckBox_paid.setSelected(true);
                            jCheckBox_paid.setEnabled(false);
                            jRadioButton_card.setEnabled(false);
                            jRadioButton_cash.setEnabled(false);
                        }
                        else{
                            jCheckBox_paid.setSelected(false);
                            jCheckBox_paid.setEnabled(true);
                            jRadioButton_card.setEnabled(true);
                            jRadioButton_cash.setEnabled(true);
                        }
                        //
                        //String o_sts=rs.getString("END_TIME");
                        jTextArea_bill.append("Order ID: "+o_id+"\n");
                        jTextArea_bill.append("Placed at: "+time+"\n");
                        jTextArea_bill.append("Order Type: "+otype+"\n");
                        jTextArea_bill.append("========================\n");           
                        jTextArea_bill.append("Items\t   Qty\tPrice\n\n");
                    }
                    rs = stmt.executeQuery("select f.FOOD_NAME,o.QUANTITY,f.UNIT_PRICE FROM ORDER_FOOD o INNER JOIN FOOD f ON f.FOOD_ID=o.FOOD_ID WHERE o.ORDER_NUMBER='"+o_id+"'");
                    while(rs.next()){
                        //ITEMS
                        String food=rs.getString("f.FOOD_NAME");
                        int qty=rs.getInt("o.QUANTITY");
                        int price=rs.getInt("f.UNIT_PRICE");
                        jTextArea_bill.append(food+"\t   "+qty+"\t"+price*qty+"\n\n");
                        //
                    }
                    
                            jTextArea_bill.append("\n\nDiscount\t\t"+discount+"\n");
                        jTextArea_bill.append("========================\nTotal\t\t"+(tot-discount)+"\n========================\n\n");
                        
                    
                    
                } catch (Exception ex) {
                    Logger.getLogger(cashierDashboard.class.getName()).log(Level.SEVERE, null, ex);
                    jPanel_dashboard.setVisible(true);
                    jPanel_order.setVisible(false);
                    jPanel1.setVisible(false);
                }
                finally{
                    c.close();
                }
  
            } catch (Exception ex) {
                Logger.getLogger(cashierDashboard.class.getName()).log(Level.SEVERE, null, ex);
                jPanel_dashboard.setVisible(true);
                jPanel_order.setVisible(false);
                jPanel1.setVisible(false);
            }   
    }
    
    private void orderCompleteItems(boolean a){
        btn_completeOdr1.setVisible(a);
        jCheckBox_paid.setEnabled(a);
        jRadioButton_card.setEnabled(a);
        jRadioButton_cash.setEnabled(a);
    }
    
    private void getCustomerDetails(){
        try {
            int row=jTable_dBoard.getSelectedRow();
            String a=(String) jTable_dBoard.getValueAt(row, 0);
            Customer obj1=new Customer(a);
            Order obj2=new Order();
            lbl_c_id.setText(obj1.getC_id());
            lbl_name.setText(obj1.getName());
            lbl_tp.setText("0"+Integer.toString(obj1.getTp()));
            lbl_tables.setText(obj2.getTablelist(a));
            if(lbl_tables.getText().equals("")){
                lbl_tableheader.setVisible(false);
            }
            else{
                lbl_tableheader.setVisible(true);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(cashierDashboard.class.getName()).log(Level.SEVERE, null, ex);
            jPanel_dashboard.setVisible(true);
            jPanel_order.setVisible(false);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_cancel1;
    private javax.swing.JButton btn_completeOdr;
    private javax.swing.JButton btn_completeOdr1;
    private javax.swing.JButton btn_hold;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_viewOrd;
    private javax.swing.JButton btn_viewtheOdr;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox jCheckBox_paid;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_dashboard;
    private javax.swing.JPanel jPanel_order;
    private javax.swing.JRadioButton jRadioButton_card;
    private javax.swing.JRadioButton jRadioButton_cash;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable_dBoard;
    private javax.swing.JTextArea jTextArea_bill;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_c_id;
    private javax.swing.JLabel lbl_delivered;
    private javax.swing.JLabel lbl_income;
    private javax.swing.JLabel lbl_name;
    private javax.swing.JLabel lbl_pendingOdr;
    private javax.swing.JLabel lbl_tableheader;
    private javax.swing.JLabel lbl_tables;
    private javax.swing.JLabel lbl_tp;
    // End of variables declaration//GEN-END:variables
}
