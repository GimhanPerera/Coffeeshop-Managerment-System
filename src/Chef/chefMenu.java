/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Chef;

import DBconnection.Connect;
import DBconnection.Food;
import java.awt.Font;
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
public class chefMenu extends javax.swing.JPanel {

    /**
     * Creates new form chefMenu
     */
    public chefMenu() {
        initComponents();
        jTable1.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 15));
        jTable2.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 15));
        jTable3.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 15));
        try {
            getFood();
            bestThreeFoodsLastMonth();
        } catch (Exception ex) {
            Logger.getLogger(chefMenu.class.getName()).log(Level.SEVERE, null, ex);
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

        lbl_topfoods = new javax.swing.JLabel();
        lbl_topfoods1 = new javax.swing.JLabel();
        lbl_topfoods2 = new javax.swing.JLabel();
        lbl_topfoods3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable0 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        lbl_foodType = new javax.swing.JLabel();
        jComboBox_category = new javax.swing.JComboBox<>();
        btn_refresh = new javax.swing.JButton();
        lbl_background = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_topfoods.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbl_topfoods.setText("Trending Items of this month");
        add(lbl_topfoods, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        lbl_topfoods1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lbl_topfoods1.setText("1. --");
        add(lbl_topfoods1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 190, -1));

        lbl_topfoods2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lbl_topfoods2.setText("2. --");
        add(lbl_topfoods2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 190, -1));

        lbl_topfoods3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lbl_topfoods3.setText("3. --");
        add(lbl_topfoods3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 190, -1));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, 720, 70));

        jPanel1.setName(""); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable0.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Qty type", "Qty", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable0.setPreferredSize(new java.awt.Dimension(300, 140));
        jTable0.setRowHeight(35);
        jScrollPane1.setViewportView(jTable0);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 710, 410));

        jTabbedPane1.addTab("tab1", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Today Available Qty", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(35);
        jScrollPane2.setViewportView(jTable1);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 710, 475));

        jTabbedPane1.addTab("cake", jPanel2);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Today Available Qty", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setRowHeight(35);
        jScrollPane3.setViewportView(jTable2);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 710, 475));

        jTabbedPane1.addTab("bun", jPanel3);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Today Available Qty", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setRowHeight(35);
        jScrollPane4.setViewportView(jTable3);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 710, 475));

        jTabbedPane1.addTab("coffee", jPanel4);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 710, 530));

        lbl_foodType.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lbl_foodType.setText("Food type");
        add(lbl_foodType, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, -1, -1));

        jComboBox_category.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jComboBox_category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Coffees", "Cakes", "Buns" }));
        jComboBox_category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_categoryActionPerformed(evt);
            }
        });
        add(jComboBox_category, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, 140, -1));

        btn_refresh.setText("Refresh");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });
        add(btn_refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 70, -1, -1));
        add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 740));
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_categoryActionPerformed
        switch(jComboBox_category.getSelectedIndex()){
            case 0:
                jTabbedPane1.setSelectedIndex(3);
                break;
            case 1:
                jTabbedPane1.setSelectedIndex(1);
                break;
            case 2:
                jTabbedPane1.setSelectedIndex(2);
                break;    
        }
    }//GEN-LAST:event_jComboBox_categoryActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        try {
            clearTable();
            getFood();
            bestThreeFoodsLastMonth();
            switch(jComboBox_category.getSelectedIndex()){
                case 0:
                jTabbedPane1.setSelectedIndex(3);
                break;
                case 1:
                jTabbedPane1.setSelectedIndex(1);
                break;
                case 2:
                jTabbedPane1.setSelectedIndex(2);
                break;
            }
        } catch (Exception ex) {
            Logger.getLogger(chefMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_refreshActionPerformed
    private void clearTable(){
        DefaultTableModel tblModel =(DefaultTableModel)jTable1.getModel(); 
        int rowCount = tblModel.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
        tblModel =(DefaultTableModel)jTable2.getModel(); 
        rowCount = tblModel.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
        tblModel =(DefaultTableModel)jTable3.getModel(); 
        rowCount = tblModel.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
    }
    
    private void getFood() throws Exception{
        try{
            Connect obj = new Connect();
        Food obj2=new Food();
        String qty="";
        Connection c = obj.getConnection();  //getConnection();//Establish the connection
        jTabbedPane1.setSelectedIndex(3);
         //int q=1;System.out.println(q++); <- tester
                Statement stmt = c.createStatement();//Prepare statement
                ResultSet rs = stmt.executeQuery("select FOOD_NAME,UNIT_PRICE from FOOD WHERE CATEGORY='COFFEE'"); //SQL stetment
                while(rs.next()){
                    String foodName=rs.getString("FOOD_NAME");
                    String price=rs.getString("UNIT_PRICE");
                    qty=Integer.toString(obj2.FoodAvailableCount(foodName));
                    String tbData[]={foodName,qty,price};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable3.getModel();
                    tblModel.addRow(tbData);
                }
            //2nd table
                rs = stmt.executeQuery("select FOOD_NAME,UNIT_PRICE from FOOD WHERE CATEGORY='CAKE'"); //SQL stetment
                while(rs.next()){
                    String foodName=rs.getString("FOOD_NAME");
                    String price=rs.getString("UNIT_PRICE");
                    qty=Integer.toString(obj2.FoodAvailableCount(foodName));
                    String tbData[]={foodName,qty,price};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable1.getModel(); 
                    tblModel.addRow(tbData);
                }
            //3rd table
                rs = stmt.executeQuery("select FOOD_NAME,UNIT_PRICE from FOOD WHERE CATEGORY='BUN'"); //SQL stetment
                while(rs.next()){
                    String foodName=rs.getString("FOOD_NAME");
                    String price=rs.getString("UNIT_PRICE");
                    qty=Integer.toString(obj2.FoodAvailableCount(foodName));
                    String tbData[]={foodName,qty,price};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable2.getModel();
                    tblModel.addRow(tbData);
                }
        }catch (Exception ex) {
                    Logger.getLogger(chefMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    public void bestThreeFoodsLastMonth() throws Exception{ 
        Connect obj = new Connect();
        Connection c = obj.getConnection();//get the connection using inheritance
        String food="";
        byte z=0;
        try{ 
            Statement stmt = c.createStatement();//Prepare statement
            ResultSet rs = stmt.executeQuery("select o.FOOD_ID,f.food_name, sum(quantity) from ORDER_FOOD o INNER JOIN food f ON o.food_id=f.food_id where ORDER_NUMBER IN (SELECT ORDER_NUMBER FROM ORDER_T WHERE DATE(ORDER_DATETIME)>DATE(NOW() - INTERVAL 30 DAY)) group by o.food_id Order by sum(quantity) DESC LIMIT 3"); //SQL stetment
            while(rs.next()){
                food=rs.getString("FOOD_NAME"); 
                if(z==0)
                    lbl_topfoods1.setText("1. "+food);
                if(z==1)
                    lbl_topfoods2.setText("2. "+food);
                if(z==2)
                    lbl_topfoods3.setText("3. "+food);
                z++;
            }
        }
        finally{
            c.close(); 
        }   
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_refresh;
    private javax.swing.JComboBox<String> jComboBox_category;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable0;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_foodType;
    private javax.swing.JLabel lbl_topfoods;
    private javax.swing.JLabel lbl_topfoods1;
    private javax.swing.JLabel lbl_topfoods2;
    private javax.swing.JLabel lbl_topfoods3;
    // End of variables declaration//GEN-END:variables
}
