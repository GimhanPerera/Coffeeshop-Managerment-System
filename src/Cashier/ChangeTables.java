/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Cashier;

import java.awt.Font;
import DBconnection.Connect;
import DBconnection.Tables;
import DBconnection.cashier;
import DBconnection.getDate;
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
public class ChangeTables extends javax.swing.JPanel {

    /**
     * Creates new form ChangeTables
     */
    public ChangeTables() {
        initComponents();
        table_reserverd.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 15));
        refresh();
    }
    private void refresh(){
        clear_orderIdTable();
        clear_reservedTablesTable();
        clear_availableTable();
        getData_table_orderID();
    }
    private void getData_table_orderID(){
        try{
            clear_orderIdTable();
            clear_reservedTablesTable();
            clear_availableTable();
            Connect obj = new Connect();
            Connection c = obj.getConnection();//Establish the connection
            try{ //int q=1;System.out.println(q++); <- tester
                getDate obj1 =new getDate();
                String date=obj1.dateOnly();
                Statement stmt = c.createStatement();//Prepare statement
                ResultSet rs; //"+date+"
                //pending orders
                //Statting order date eka wenas karanna ona ada dawasata
                rs = stmt.executeQuery("select ORDER_NUMBER from ORDER_T WHERE status not in ('Completed','Finish') AND ORDER_TYPE='Dinein'"); //Tables need to join
                while(rs.next()){
                    String oID=rs.getString("ORDER_NUMBER");
                    String tbData[]={oID};
                    DefaultTableModel tblModel =(DefaultTableModel)table_orderID.getModel(); 
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
    
    private void getData_table_reserved(){
        try{
            clear_reservedTablesTable();
            int row= table_orderID.getSelectedRow();
            String a=(String) table_orderID.getValueAt(row, 0);
            Connect obj = new Connect();
            Connection c = obj.getConnection();//Establish the connection
            try{ 
                Statement stmt = c.createStatement();//Prepare statement
                ResultSet rs = stmt.executeQuery("select TABLE_T.TABLE_NO,TABLE_T.CHAIRS from ORDER_TABLE INNER JOIN TABLE_T ON ORDER_TABLE.TABLE_NO=TABLE_T.TABLE_NO WHERE ORDER_TABLE.ORDER_NUMBER='"+a+"'"); //SQL stetment
                while(rs.next()){
                    String tables=rs.getString("TABLE_NO");
                    String chairs=rs.getString("CHAIRS");
                    DefaultTableModel tblModel =(DefaultTableModel)table_reserverd.getModel(); 
                    String tbData[]={tables,chairs};
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
    
    private void getData_table_available(){
        try{
            clear_reservedTablesTable();
            int row= table_orderID.getSelectedRow();
            String a=(String) table_orderID.getValueAt(row, 0);
            Connect obj = new Connect();
            Connection c = obj.getConnection();//Establish the connection
            getDate obj2 = new getDate();
            String date=obj2.dateOnly();
            try{ 
                Statement stmt = c.createStatement();//Prepare statement
                ResultSet rs = stmt.executeQuery("select table_no,chairs from TABLE_T WHERE table_no NOT IN(select DISTINCT ot.table_no from  ORDER_TABLE ot " +
                        "where ot.ORDER_NUMBER IN (select ORDER_NUMBER from ORDER_T " +
                            "where ORDER_DATETIME > '"+date+" 00:00:00' AND ORDER_DATETIME <'"+date+" 23:59:59' AND status != 'Completed'))"); //SQL stetment
            System.out.println("Available Tables");
            while(rs.next()){
                String table=rs.getString("TABLE_NO");
                //String chairs=rs.getByte("chairs");
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
    
    
    private void clear_orderIdTable(){
        DefaultTableModel tblModel =(DefaultTableModel)table_orderID.getModel(); 
        int rowCount = tblModel.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
    }
    private void clear_reservedTablesTable(){
        DefaultTableModel tblModel =(DefaultTableModel)table_reserverd.getModel(); 
        int rowCount = tblModel.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
    }
    private void clear_availableTable(){
        DefaultTableModel tblModel =(DefaultTableModel)table_availableTables.getModel(); 
        int rowCount = tblModel.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            tblModel.removeRow(i);
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

        lbl_title = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_reserverd = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        table_availableTables = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        table_orderID = new javax.swing.JTable();
        btn_refresh = new javax.swing.JButton();
        lbl_background = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_title.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_title.setText("Change Tables");
        add(lbl_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, -1, -1));

        table_reserverd.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        table_reserverd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Reserved Tables", "Seats"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_reserverd.setRowHeight(30);
        jScrollPane5.setViewportView(table_reserverd);
        if (table_reserverd.getColumnModel().getColumnCount() > 0) {
            table_reserverd.getColumnModel().getColumn(0).setResizable(false);
        }

        add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 260, 250, 290));

        jButton1.setText("Swap");
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 380, 80, 50));

        table_availableTables.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        table_availableTables.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Available Tables", "Seats"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_availableTables.setRowHeight(30);
        jScrollPane6.setViewportView(table_availableTables);
        if (table_availableTables.getColumnModel().getColumnCount() > 0) {
            table_availableTables.getColumnModel().getColumn(0).setResizable(false);
            table_availableTables.getColumnModel().getColumn(1).setResizable(false);
        }

        add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 260, 250, 290));

        table_orderID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        table_orderID.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_orderID.setRowHeight(30);
        table_orderID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_orderIDMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(table_orderID);
        if (table_orderID.getColumnModel().getColumnCount() > 0) {
            table_orderID.getColumnModel().getColumn(0).setResizable(false);
        }

        add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 150, 290));

        btn_refresh.setText("Refresh");
        btn_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_refreshMouseClicked(evt);
            }
        });
        add(btn_refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 70, -1, -1));
        add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 740));
    }// </editor-fold>//GEN-END:initComponents

    private void table_orderIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_orderIDMouseClicked
        getData_table_reserved();
        //getData_table_available();
    }//GEN-LAST:event_table_orderIDMouseClicked

    private void btn_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_refreshMouseClicked
        refresh();
    }//GEN-LAST:event_btn_refreshMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JTable table_availableTables;
    private javax.swing.JTable table_orderID;
    private javax.swing.JTable table_reserverd;
    // End of variables declaration//GEN-END:variables
}
