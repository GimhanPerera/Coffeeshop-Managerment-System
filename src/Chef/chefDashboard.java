/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Chef;

import Cashier.cashierDashboard;
import DBconnection.Connect;
import DBconnection.Order;
import DBconnection.cashier;
import DBconnection.getDate;
import DBconnection.mgrDashboard;
import java.awt.Component;
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
public class chefDashboard extends javax.swing.JPanel {

    /**
     * Creates new form chefDashboard
     */
    public chefDashboard() {
        initComponents();
        lbl_msg.setVisible(false);
        refresh(); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_msg = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea_bill = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_dBoard = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lbl_pendingOdr = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_making = new javax.swing.JLabel();
        btn_refresh = new javax.swing.JButton();
        btn_finishOdr = new javax.swing.JButton();
        btn_accept = new javax.swing.JButton();
        lbl_background = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_msg.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lbl_msg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_msg.setText("No orders");
        add(lbl_msg, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 590, -1));

        jScrollPane5.setHorizontalScrollBar(null);

        jTextArea_bill.setEditable(false);
        jTextArea_bill.setColumns(20);
        jTextArea_bill.setFont(new java.awt.Font("Segoe UI Emoji", 0, 20)); // NOI18N
        jTextArea_bill.setRows(5);
        jScrollPane5.setViewportView(jTextArea_bill);

        add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 140, 310, 430));

        jTable_dBoard.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTable_dBoard.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Table Number(s)", "Order Type", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 720, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jLabel2.setText("Pending Order count");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, -1, -1));

        lbl_pendingOdr.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lbl_pendingOdr.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_pendingOdr.setText("0");
        add(lbl_pendingOdr, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 30, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jLabel5.setText("Making orders");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, -1, -1));

        lbl_making.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lbl_making.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_making.setText("0");
        add(lbl_making, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 60, 30, -1));

        btn_refresh.setText("Refresh");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });
        add(btn_refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 60, -1, -1));

        btn_finishOdr.setText("Finish order");
        btn_finishOdr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_finishOdrActionPerformed(evt);
            }
        });
        add(btn_finishOdr, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 610, 180, 50));

        btn_accept.setText("Accept");
        btn_accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_acceptActionPerformed(evt);
            }
        });
        add(btn_accept, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 620, 160, 40));
        add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 740));
    }// </editor-fold>//GEN-END:initComponents

    private void jTable_dBoardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_dBoardMouseClicked
        refresh(); 
    }//GEN-LAST:event_jTable_dBoardMouseClicked

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        refresh();   
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void btn_finishOdrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_finishOdrActionPerformed
        int result = JOptionPane.showConfirmDialog((Component) null, "Are you sure?",
            "alert", JOptionPane.YES_NO_OPTION);
        System.out.println("Result : "+result);
        if(result==0){
            try {
                int row=jTable_dBoard.getSelectedRow();
                String oid=(String) jTable_dBoard.getValueAt(row, 0);
            Order obj=new Order();
            obj.setMakingFinishStatus(oid,false);
                refresh();
            } catch (Exception ex) {
                Logger.getLogger(cashierDashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_finishOdrActionPerformed

    private void btn_acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_acceptActionPerformed
        try {
            int row=jTable_dBoard.getSelectedRow();
            String a=(String) jTable_dBoard.getValueAt(row, 0);
            Order obj=new Order();
            obj.setMakingFinishStatus(a,true);
            refresh();
        } catch (Exception ex) {
            Logger.getLogger(chefDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_acceptActionPerformed
    
    private void refresh(){
       try { 
           lbl_msg.setVisible(false);
           mgrDashboard obj1=new mgrDashboard();
           cashier obj2=new cashier();
           lbl_pendingOdr.setText(Integer.toString(obj2.getPendingOrderCount()));
           int row=0;String a="";
           if(jTable_dBoard.getRowCount()!=0){
               row=jTable_dBoard.getSelectedRow();
               a=(String) jTable_dBoard.getValueAt(row, 0);
           }
           getData();
           if(jTable_dBoard.getRowCount()!=0){
               String a2="";
               if("".equals(a)){
                   a=(String) jTable_dBoard.getValueAt(0, 0);
                   jTable_dBoard.setRowSelectionInterval(0, 0);
               }  
               else{
                    for (int i = 0; i < jTable_dBoard.getRowCount(); i++) {
                        a2=(String) jTable_dBoard.getValueAt(i, 0);
                        if(a.equals(a2)){
                            jTable_dBoard.setRowSelectionInterval(i, i);
                            break;
                        }
                        if(i==jTable_dBoard.getRowCount()-1){
                            jTable_dBoard.setRowSelectionInterval(0, 0);
                            a=(String) jTable_dBoard.getValueAt(0, 0);
                            break;
                        }
                    }   
               }
               btnRefresh();
               getItems(a);
            }
           else{
               jTextArea_bill.setText("");
               lbl_msg.setVisible(true);
           }
        } catch (Exception ex) {
            Logger.getLogger(cashierDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void btnRefresh(){
        if(jTable_dBoard.getRowCount()!=0){
            int row=jTable_dBoard.getSelectedRow();
            String a=(String) jTable_dBoard.getValueAt(row, 3);
            if(a.equals("Making")){
                btn_accept.setEnabled(false);
                btn_finishOdr.setEnabled(true);
            }
            if(a.equals("Pending")){
                btn_accept.setEnabled(true);
                btn_finishOdr.setEnabled(false);
            }
        }
        else{
            btn_accept.setEnabled(false);
            btn_finishOdr.setEnabled(false);
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
                ResultSet rs;
                //Get pending order count
                rs = stmt.executeQuery("select count(o.ORDER_NUMBER) AS value from  ORDER_T o WHERE STATUS ='Pending'"); //Tables need to join
                while(rs.next()){
                    String count=rs.getString("value");
                    lbl_pendingOdr.setText(count);
                }
                //Get Making order count
                rs = stmt.executeQuery("select count(o.ORDER_NUMBER) AS value from  ORDER_T o WHERE STATUS ='Making'"); //Tables need to join
                while(rs.next()){
                    String count=rs.getString("value");
                    lbl_making.setText(count);
                }
                
                rs = stmt.executeQuery("select o.ORDER_NUMBER, o.ORDER_TYPE ,o.STATUS as o_sts from  ORDER_T o WHERE STATUS IN ('Pending','Making') ORDER BY STATUS DESC, ORDER_NUMBER"); //Tables need to join
                while(rs.next()){
                    String oID=rs.getString("ORDER_NUMBER");
                    String otype=rs.getString("ORDER_TYPE");
                    String o_sts=rs.getString("o_sts");
                    //GET ALL TABLE NAMBERS TO SINGLE STRING
                    
                    String tbData[]={oID,"",otype,o_sts};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable_dBoard.getModel(); 
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
        catch(Exception ex)//Is database has a problem, this catch stetment catch it
        {
            Logger.getLogger(cashierDashboard.class.getName()).log(Level.SEVERE, null,ex); 
        }
    }
    
    private void getItems(String oID){
        jTextArea_bill.setText("");
        try{
            Connect obj = new Connect();
            Connection c = obj.getConnection();//Establish the connection
            cashier obj2=new cashier();
            try{ //int q=1;System.out.println(q++); <- tester
                Statement stmt = c.createStatement();//Prepare statement
                ResultSet rs;
                int y=0;
                jTextArea_bill.append("\n====================\n\n\n");
                rs = stmt.executeQuery("select FOOD_NAME,QUANTITY from ORDER_FOOD INNER JOIN FOOD ON ORDER_FOOD.FOOD_ID=FOOD.FOOD_ID \n" +
                                "WHERE ORDER_NUMBER ='"+oID+"'"); //Tables need to join
                while(rs.next()){
                    String foodName=rs.getString("FOOD_NAME");
                    String qty=rs.getString("QUANTITY");
                    //print a row
                    jTextArea_bill.append(foodName+"\t\t"+qty+"\n");
                    y++;
                }
                //for(int z=y;z<10;y++)
                //    jTextArea_bill.append("\n");
                jTextArea_bill.append("\n====================\n");
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_accept;
    private javax.swing.JButton btn_finishOdr;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable_dBoard;
    private javax.swing.JTextArea jTextArea_bill;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_making;
    private javax.swing.JLabel lbl_msg;
    private javax.swing.JLabel lbl_pendingOdr;
    // End of variables declaration//GEN-END:variables
}
