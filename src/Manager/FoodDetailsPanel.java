/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Manager;
import DBconnection.Connect;
import DBconnection.Food;
import DBconnection.LoyaltyCard;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Gimhan
 */
public class FoodDetailsPanel extends javax.swing.JPanel {

    /**
     * Creates new form FoodDetailsPanel
     */
    public FoodDetailsPanel() {
        initComponents();
        jTable_food.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 15));
        lbl_Error.setVisible(false);
        jPanel_foodAddEdit.setVisible(false);
        jPanel_main.setVisible(true);
        lbl_empty.setVisible(false);
        lbl_Error1.setVisible(false);
    }
    
    private void setEditRemovebtn(){
        if(jTable_food.getRowCount()!=0){
            btn_delete.setEnabled(true);
            btn_edit.setEnabled(true);
        }
        else{
            btn_edit.setEnabled(false);
            btn_delete.setEnabled(false);
        }
    }
    
    public void getFood() throws Exception{
        Connect obj = new Connect();
        Connection c = obj.getConnection();  //getConnection();//Establish the connection
        lbl_empty.setVisible(false);
        try{ //int q=1;System.out.println(q++); <- tester
            
                Statement stmt = c.createStatement();//Prepare statement
                ResultSet rs = stmt.executeQuery("select * from FOOD"); //SQL stetment
                while(rs.next()){
                    String foodID=rs.getString("FOOD_ID");
                    String foodName=rs.getString("FOOD_NAME");
                    String category=rs.getString("CATEGORY");
                    String qty=rs.getString("QUANTITY_TYPE");
                    String price=rs.getString("UNIT_PRICE");
                    String dailyqty=rs.getString("DAILY_QUANTITY");
                    String tbData[]={foodID,foodName,category,qty,price,dailyqty};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable_food.getModel(); 
                    tblModel.addRow(tbData);
                }
                if(jTable_food.getRowCount()!=0)
                    jTable_food.setRowSelectionInterval(0, 0);
                else
                    lbl_empty.setVisible(true);
                setEditRemovebtn();
        }
        catch(SQLException ex)//Is database has a problem, this catch stetment catch it
        {
            System.out.println(ex);
        }
        finally{
            c.close(); 
        }   
    }
    
    public void getFoodFromCatogorywise(int i) throws Exception{
        Connect obj = new Connect();
        Connection c = obj.getConnection();  //getConnection();//Establish the connection
        clearTable();
        lbl_empty.setVisible(false);
        try{ //int q=1;System.out.println(q++); <- tester
            
                Statement stmt = c.createStatement();//Prepare statement
                ResultSet rs;
            switch (i) {
                case 1:
                    rs = stmt.executeQuery("select * from FOOD WHERE CATEGORY='COFFEE'"); //SQL stetment
                    while(rs.next()){
                        String foodID=rs.getString("FOOD_ID");
                    String foodName=rs.getString("FOOD_NAME");
                    String category=rs.getString("CATEGORY");
                    String qty=rs.getString("QUANTITY_TYPE");
                    String price=rs.getString("UNIT_PRICE");
                    String dailyqty=rs.getString("DAILY_QUANTITY");
                    String tbData[]={foodID,foodName,category,qty,price,dailyqty};
                        DefaultTableModel tblModel =(DefaultTableModel)jTable_food.getModel();
                        tblModel.addRow(tbData);
                    }   break;
                case 2:
                    rs = stmt.executeQuery("select * from FOOD WHERE CATEGORY='CAKE'"); //SQL stetment
                    while(rs.next()){
                        String foodID=rs.getString("FOOD_ID");
                    String foodName=rs.getString("FOOD_NAME");
                    String category=rs.getString("CATEGORY");
                    String qty=rs.getString("QUANTITY_TYPE");
                    String price=rs.getString("UNIT_PRICE");
                    String dailyqty=rs.getString("DAILY_QUANTITY");
                    String tbData[]={foodID,foodName,category,qty,price,dailyqty};
                        DefaultTableModel tblModel =(DefaultTableModel)jTable_food.getModel();
                        tblModel.addRow(tbData);
                    }   break;
                case 3:
                    rs = stmt.executeQuery("select * from FOOD WHERE CATEGORY='BUN'"); //SQL stetment
                    while(rs.next()){
                        String foodID=rs.getString("FOOD_ID");
                    String foodName=rs.getString("FOOD_NAME");
                    String category=rs.getString("CATEGORY");
                    String qty=rs.getString("QUANTITY_TYPE");
                    String price=rs.getString("UNIT_PRICE");
                    String dailyqty=rs.getString("DAILY_QUANTITY");
                    String tbData[]={foodID,foodName,category,qty,price,dailyqty};
                        DefaultTableModel tblModel =(DefaultTableModel)jTable_food.getModel();
                        tblModel.addRow(tbData);
                    }   break;
                default:
                    break;
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
    
    public void getFoodFromSearch(int i,String txt) throws Exception{
        Connect obj = new Connect();
        Connection c = obj.getConnection();  //getConnection();//Establish the connection
        lbl_empty.setVisible(false);
        try{ //int q=1;System.out.println(q++); <- tester
                Statement stmt = c.createStatement();//Prepare statement
                ResultSet rs;
            switch (i) {
                case 0:
                    rs = stmt.executeQuery("select * from FOOD WHERE FOOD_ID='"+txt+"'"); //SQL stetment
                    while(rs.next()){
                        String foodID=rs.getString("FOOD_ID");
                    String foodName=rs.getString("FOOD_NAME");
                    String category=rs.getString("CATEGORY");
                    String qty=rs.getString("QUANTITY_TYPE");
                    String price=rs.getString("UNIT_PRICE");
                    String dailyqty=rs.getString("DAILY_QUANTITY");
                    String tbData[]={foodID,foodName,category,qty,price,dailyqty};
                        DefaultTableModel tblModel =(DefaultTableModel)jTable_food.getModel();
                        tblModel.addRow(tbData);
                    }   break;
                case 1:
                    rs = stmt.executeQuery("select * from FOOD WHERE FOOD_NAME='"+txt+"'"); //SQL stetment
                    while(rs.next()){
                        String foodID=rs.getString("FOOD_ID");
                    String foodName=rs.getString("FOOD_NAME");
                    String category=rs.getString("CATEGORY");
                    String qty=rs.getString("QUANTITY_TYPE");
                    String price=rs.getString("UNIT_PRICE");
                    String dailyqty=rs.getString("DAILY_QUANTITY");
                    String tbData[]={foodID,foodName,category,qty,price,dailyqty};
                        DefaultTableModel tblModel =(DefaultTableModel)jTable_food.getModel();
                        tblModel.addRow(tbData);
                    }   break;
                default:
                    break;
            }
            if(jTable_food.getRowCount()!=0)
                    jTable_food.setRowSelectionInterval(0, 0);
                else
                    lbl_empty.setVisible(true);
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
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel_main = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        lbl_foodtype = new javax.swing.JLabel();
        jComboBox_foodtype = new javax.swing.JComboBox<>();
        btn_searchByType = new javax.swing.JButton();
        jComboBox_searchby = new javax.swing.JComboBox<>();
        lbl_searchby = new javax.swing.JLabel();
        lbl_Error = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        lbl_empty = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_food = new javax.swing.JTable();
        btn_viewall = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        jPanel_foodAddEdit = new javax.swing.JPanel();
        lbl_title = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_foodID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_foodname = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox_ftype = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txt_qtyType = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_price = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_maxQty = new javax.swing.JTextField();
        btn_cancel = new javax.swing.JButton();
        btn_add1 = new javax.swing.JButton();
        lbl_Error1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(173, 85, 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 740, 1170, 30));

        jPanel1.setBackground(new java.awt.Color(173, 85, 2));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Food Details");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, -1, -1));

        jPanel_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Filter Food");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel_main.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, -1, -1));

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jRadioButton2.setText("Search Food");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel_main.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, -1));

        lbl_foodtype.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_foodtype.setText("Food Type");
        jPanel_main.add(lbl_foodtype, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        jComboBox_foodtype.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboBox_foodtype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--", "COFFEE", "CAKE", "BUN" }));
        jComboBox_foodtype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_foodtypeActionPerformed(evt);
            }
        });
        jPanel_main.add(jComboBox_foodtype, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 240, -1));

        btn_searchByType.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_searchByType.setText("Search");
        btn_searchByType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchByTypeActionPerformed(evt);
            }
        });
        jPanel_main.add(btn_searchByType, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 20, 100, -1));

        jComboBox_searchby.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboBox_searchby.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Food ID", "Food Name" }));
        jComboBox_searchby.setEnabled(false);
        jComboBox_searchby.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_searchbyActionPerformed(evt);
            }
        });
        jPanel_main.add(jComboBox_searchby, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 150, -1));

        lbl_searchby.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_searchby.setText("Search by");
        lbl_searchby.setEnabled(false);
        jPanel_main.add(lbl_searchby, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, -1, -1));

        lbl_Error.setForeground(new java.awt.Color(255, 0, 0));
        lbl_Error.setText("Error msg");
        jPanel_main.add(lbl_Error, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 120, 330, -1));

        txt_search.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_search.setEnabled(false);
        jPanel_main.add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 220, -1));

        btn_search.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_search.setText("Search");
        btn_search.setEnabled(false);
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });
        jPanel_main.add(btn_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 80, 100, -1));

        lbl_empty.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_empty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_empty.setText("No results");
        jPanel_main.add(lbl_empty, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 350, 310, 30));

        jTable_food.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTable_food.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Food ID", "Name", "category", "Unit Type", "Price", "Daily Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_food.setRowHeight(30);
        jScrollPane1.setViewportView(jTable_food);

        jPanel_main.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 970, 420));

        btn_viewall.setText("View All");
        btn_viewall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_viewallActionPerformed(evt);
            }
        });
        jPanel_main.add(btn_viewall, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 210, 110, 40));

        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });
        jPanel_main.add(btn_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1016, 270, 100, -1));

        btn_add.setText("Add");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });
        jPanel_main.add(btn_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(1014, 350, 100, 40));

        btn_edit.setText("Edit");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });
        jPanel_main.add(btn_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1014, 410, 100, 40));

        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        jPanel_main.add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1014, 470, 100, -1));

        add(jPanel_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 1130, 600));

        jPanel_foodAddEdit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_title.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbl_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_title.setText("New Item");
        jPanel_foodAddEdit.add(lbl_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 440, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Food ID");
        jPanel_foodAddEdit.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, -1, -1));

        txt_foodID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_foodID.setEnabled(false);
        jPanel_foodAddEdit.add(txt_foodID, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, 170, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Food name");
        jPanel_foodAddEdit.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, -1, -1));

        txt_foodname.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel_foodAddEdit.add(txt_foodname, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 190, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Food Category");
        jPanel_foodAddEdit.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, -1, -1));

        jComboBox_ftype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "COFFEE", "CAKE", "BUN" }));
        jComboBox_ftype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_ftypeActionPerformed(evt);
            }
        });
        jPanel_foodAddEdit.add(jComboBox_ftype, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 260, 170, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Quantity Type");
        jPanel_foodAddEdit.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, -1, -1));

        txt_qtyType.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel_foodAddEdit.add(txt_qtyType, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 310, 170, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Price (Rs.)");
        jPanel_foodAddEdit.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, -1, -1));

        txt_price.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel_foodAddEdit.add(txt_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 360, 170, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Daily maximum Quantity");
        jPanel_foodAddEdit.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, -1, -1));

        txt_maxQty.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel_foodAddEdit.add(txt_maxQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, 170, -1));

        btn_cancel.setText("Cancel");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });
        jPanel_foodAddEdit.add(btn_cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 490, 130, 50));

        btn_add1.setText("Add");
        btn_add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add1ActionPerformed(evt);
            }
        });
        jPanel_foodAddEdit.add(btn_add1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 490, 130, 50));

        lbl_Error1.setForeground(new java.awt.Color(255, 0, 0));
        lbl_Error1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Error1.setText("Error msg");
        jPanel_foodAddEdit.add(lbl_Error1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 101, 660, 20));

        add(jPanel_foodAddEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 740, 610));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        lbl_Error.setVisible(false);
        txt_search.setText("");
        if(jTable_food.getRowCount()==0){//validations
            JOptionPane.showMessageDialog(new JFrame(), "Please select a item",
               "Imformation", JOptionPane.ERROR_MESSAGE);
        }else{
            int row=jTable_food.getSelectedRow();
            String a=(String) jTable_food.getValueAt(row, 0);
            AddEdit(a);
            jPanel_foodAddEdit.setVisible(true);
            jPanel_main.setVisible(false);
            jLabel1.setVisible(false);
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        txt_search.setText("");
        lbl_Error.setVisible(false);
        AddEdit();
        jPanel_foodAddEdit.setVisible(true);
        jPanel_main.setVisible(false);
        jLabel1.setVisible(false);
        txt_foodname.requestFocus();
        jComboBox_ftype.setEnabled(true);
    }//GEN-LAST:event_btn_addActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        txt_search.setText("");
        lbl_Error.setVisible(false);
        jComboBox_foodtype.setEnabled(true);
        lbl_foodtype.setEnabled(true);
        lbl_searchby.setEnabled(false);
        jComboBox_searchby.setEnabled(false);
        txt_search.setEnabled(false);
        btn_search.setEnabled(false);
        btn_searchByType.setEnabled(true);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        jComboBox_foodtype.setEnabled(false);
        lbl_foodtype.setEnabled(false);
        lbl_searchby.setEnabled(true);
        jComboBox_searchby.setEnabled(true);
        txt_search.setEnabled(true);
        btn_search.setEnabled(true);
        btn_searchByType.setEnabled(false);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void btn_viewallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_viewallActionPerformed
        try {
            txt_search.setText("");
            lbl_Error.setVisible(false);
            lbl_empty.setVisible(false);
            clearTable();
            getFood();
            setEditRemovebtn();
            
        } catch (Exception ex) {
            Logger.getLogger(FoodDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_viewallActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        txt_search.setText("");
        lbl_Error.setVisible(false);
        clearTable();
        lbl_empty.setVisible(true);
        jComboBox_foodtype.setSelectedIndex(0);
        setEditRemovebtn();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        lbl_Error.setVisible(false);
        try {
            if(txt_search.getText().isEmpty()){//empty
                lbl_Error.setVisible(true);
                lbl_Error.setText("Please enter "+jComboBox_searchby.getSelectedItem());
            }
            //search by food ID
            else if(jComboBox_searchby.getSelectedIndex()==0 && txt_search.getText().length()!=5){//food ID width need to 5
                lbl_Error.setVisible(true);
                lbl_Error.setText("Wrong food id");
            }
            else if(jComboBox_searchby.getSelectedIndex()==0 && !txt_search.getText().substring(2).matches("^[0-9]*$")){
                //last 3 characters of food id need to be numbers
                lbl_Error.setVisible(true);
                lbl_Error.setText("Wrong food id format");
            }
            else if(jComboBox_searchby.getSelectedIndex()==0 &&  !txt_search.getText().substring(0, 2).matches("^[a-zA-Z]*$")){//
                lbl_Error.setVisible(true);
                lbl_Error.setText("Wrong food id format");
            }
            //search by food name
            else if(jComboBox_searchby.getSelectedIndex()==1 && txt_search.getText().matches("^[0-9]*$")){//numbers in name
                lbl_Error.setVisible(true);
                lbl_Error.setText("Wrong food name");
            }
            else if (jComboBox_searchby.getSelectedIndex()==1 && !txt_search.getText().matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$")) {//alphabetical letters and single spaces only
                lbl_Error.setVisible(true);
                lbl_Error.setText("Wrong food name format");
            }
            else if(jComboBox_searchby.getSelectedIndex()==1 && txt_search.getText().length()<20){//numbers in name
                lbl_Error.setVisible(true);
                lbl_Error.setText("Wrong food name");
            }
            else{
                clearTable();
                getFoodFromSearch(jComboBox_searchby.getSelectedIndex(),txt_search.getText());
                if(jTable_food.getRowCount()!=0)
                    jTable_food.setRowSelectionInterval(0, 0);
                else
                    lbl_empty.setVisible(true);
                setEditRemovebtn();
            }
        } catch (Exception ex) {
            Logger.getLogger(FoodDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
            lbl_Error.setVisible(true);
            lbl_Error.setText("No result. Please check entered "+jComboBox_searchby.getSelectedItem());
            setEditRemovebtn();
        }
    }//GEN-LAST:event_btn_searchActionPerformed

    private void jComboBox_foodtypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_foodtypeActionPerformed

    }//GEN-LAST:event_jComboBox_foodtypeActionPerformed

    private void btn_searchByTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchByTypeActionPerformed
        try {
            if(jComboBox_foodtype.getSelectedIndex()!=0){
                getFoodFromCatogorywise(jComboBox_foodtype.getSelectedIndex());
                if(jTable_food.getRowCount()!=0)
                    jTable_food.setRowSelectionInterval(0, 0);
                else
                    lbl_empty.setVisible(true);
                setEditRemovebtn();
            }          
        } catch (Exception ex) {
            Logger.getLogger(FoodDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_searchByTypeActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        txt_search.setText("");
        lbl_Error.setVisible(false);
        lbl_empty.setVisible(false);
        txt_search.setText("");
        if(jTable_food.getRowCount()==0){//validations
            JOptionPane.showMessageDialog(new JFrame(), "Please select a item",
               "Imformation", JOptionPane.ERROR_MESSAGE);
        }
        else{
            try {
                int row=jTable_food.getSelectedRow();
                String a=(String) jTable_food.getValueAt(row, 0);
                Food obj=new Food();
                int result = JOptionPane.showConfirmDialog((Component) null, "Are you sure?",
                                                                    "alert", JOptionPane.YES_NO_OPTION);
                System.out.println(result);
                if(result==0){
                    try {
                        if(obj.deleteFood(a)){//delete the food
                            if(jComboBox_foodtype.getSelectedIndex()==0){
                            clearTable();
                            getFood();
                            setEditRemovebtn();
                        }else{
                            getFoodFromCatogorywise(jComboBox_foodtype.getSelectedIndex());
                        }
                        if(jTable_food.getRowCount()!=0)
                            jTable_food.setRowSelectionInterval(0, 0);
                        else
                            lbl_empty.setVisible(true);
                        setEditRemovebtn();
                        }
                        else{//delete unsuccessful
                            JOptionPane.showMessageDialog(new JFrame(), "Can't delete the food item. Because the item has in a pending order",
                                "Imformation", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                                Logger.getLogger(LoyaltyCardPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } 
            } 
            catch (Exception ex) {
                JOptionPane.showMessageDialog(new JFrame(), "Can't delete the food item. Because the item has in a pending order",
                    "Imformation", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void jComboBox_searchbyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_searchbyActionPerformed

    }//GEN-LAST:event_jComboBox_searchbyActionPerformed

    private void jComboBox_ftypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_ftypeActionPerformed
        if(btn_add.getText().equals("Add")){//edit existing food item
            newid();
        }

    }//GEN-LAST:event_jComboBox_ftypeActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        jPanel_foodAddEdit.setVisible(false);
        jPanel_main.setVisible(true);
        lbl_Error1.setVisible(false);
        jLabel1.setVisible(true);
        setFoodFormEmpty();
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add1ActionPerformed
        Food obj=new Food();
        lbl_Error1.setVisible(false);
        if(txt_foodname.getText().isEmpty()){//check is it empty
            lbl_Error1.setText("Food name cannot be empty");
            lbl_Error1.setVisible(true);
            txt_foodname.requestFocus();
        }
        else if(!txt_foodname.getText().matches( "^[a-zA-Z]+(\\s[a-zA-Z]+)*$")){//check is any alphabetic characters and space
            lbl_Error1.setText("Invalid food name format");
            lbl_Error1.setVisible(true);
            txt_foodname.requestFocus();
        }
        else if(txt_foodname.getText().length()<=2 || txt_foodname.getText().length()>=20){//not allow 2>=length>=20
            lbl_Error1.setText("Food name length should between 2 to 20");
            lbl_Error1.setVisible(true);
            txt_foodname.requestFocus();
        }
        else if(!txt_qtyType.getText().isEmpty() && !txt_qtyType.getText().matches( "^[a-zA-Z]+(\\s[a-zA-Z]+)*$")){//check is any alphabetic character and space
            lbl_Error1.setText("Invalid Qty type format");
            lbl_Error1.setVisible(true);
            txt_qtyType.requestFocus();
        }
        else if(!txt_qtyType.getText().isEmpty() && (txt_qtyType.getText().length()<=2 || txt_qtyType.getText().length()>=15)){//not allow 2>=length>=15
            lbl_Error1.setText("Qty type length should between 2 to 15");
            lbl_Error1.setVisible(true);
            txt_qtyType.requestFocus();
        }
        else if(txt_price.getText().isEmpty()){//check is it empty
            lbl_Error1.setText("Price cannot be empty");
            lbl_Error1.setVisible(true);
            txt_price.requestFocus();
        }
        else if(!txt_price.getText().matches("^[0-9]*$")){
            lbl_Error1.setText("Invalid Price");
            lbl_Error1.setVisible(true);
            txt_price.requestFocus();
        }
        else if(Integer.parseInt(txt_price.getText())>10000){
            lbl_Error1.setText("Invalid Price");
            lbl_Error1.setVisible(true);
            txt_price.requestFocus();
        }
        else if(txt_maxQty.getText().isEmpty()){//check is it empty
            lbl_Error1.setText("Daily maximum Quentity cannot be empty");
            lbl_Error1.setVisible(true);
            txt_maxQty.requestFocus();
        }
        else if(!txt_maxQty.getText().matches("^[0-9]*$")){
            lbl_Error1.setText("Invalid Daily maximum Quentity");
            lbl_Error1.setVisible(true);
            txt_maxQty.requestFocus();
        }
        else if(Integer.parseInt(txt_maxQty.getText())>10000){
            txt_maxQty.setText("Invalid Price");
            txt_maxQty.setVisible(true);
            txt_maxQty.requestFocus();
        }
        else if(btn_add1.getText().equals("Save")){//edit existing food item
            try {
                String temp="";
                switch(jComboBox_ftype.getSelectedIndex()){
                    case 0:
                        temp="COFFEE";
                        break;
                    case 1:
                        temp="CAKE";
                        break;
                    case 2:
                        temp="BUN";
                        break;
                }
                lbl_Error1.setVisible(false);
                
                obj.updateFood(txt_foodID.getText(),txt_foodname.getText(),temp,txt_price.getText(),txt_maxQty.getText(),txt_qtyType.getText());
                JOptionPane.showMessageDialog(new JFrame(), "Food updated",
               "Imformation", JOptionPane.INFORMATION_MESSAGE);
                setFoodFormEmpty();
                System.out.println(txt_foodID.getText()+" Food updated");
                lbl_Error.setVisible(false);
                clearTable();
                getFood();
                jPanel_foodAddEdit.setVisible(false);
                jPanel_main.setVisible(true);
                jLabel1.setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(FoodDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{//add new food item
            try {
                if(obj.isFoodAlreadyExist(txt_foodname.getText())){//check food is already exist
                    lbl_Error1.setText("Food is already exist");
                    lbl_Error1.setVisible(true);
                    txt_foodname.requestFocus();
                }
                else{
                    obj.addNewFood(txt_foodID.getText(),txt_foodname.getText(),String.valueOf(jComboBox_ftype.getSelectedItem()),txt_price.getText(),txt_maxQty.getText(),txt_qtyType.getText());
                    System.out.println(txt_foodID.getText()+" Food added");
                    lbl_Error1.setVisible(false);
                    lbl_Error.setVisible(false);
                    JOptionPane.showMessageDialog(new JFrame(), "New food added",
                    "Imformation", JOptionPane.INFORMATION_MESSAGE);
                    setFoodFormEmpty();
                    clearTable();
                    getFood();
                    jPanel_foodAddEdit.setVisible(false);
                    jPanel_main.setVisible(true);
                    jLabel1.setVisible(true);
                }
            } catch (Exception ex) {
                Logger.getLogger(FoodDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
    }//GEN-LAST:event_btn_add1ActionPerformed
    private void clearTable(){
        DefaultTableModel tblModel =(DefaultTableModel)jTable_food.getModel(); 
        int rowCount = tblModel.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
    }
    
    private void AddEdit() {
        newid();
    }
    
    private void AddEdit(String id) {
        try {
            btn_add1.setText("Save");
        lbl_title.setText("Edit item");
            Food obj1=new Food(id);
            txt_foodID.setText(obj1.getfoodID());
            txt_foodname.setText(obj1.getfoodName());
            switch(obj1.getCategory()){
                case "COFFEE":
                    jComboBox_ftype.setSelectedIndex(0);
                    break;
                case "CAKE":
                    jComboBox_ftype.setSelectedIndex(1);
                    break;
                case "BUN":
                    jComboBox_ftype.setSelectedIndex(2);
                    break;
            }
            txt_maxQty.setText(obj1.getDailyQty());
            txt_price.setText(obj1.getPrice());
            txt_qtyType.setText(obj1.getQtyType());
            jComboBox_ftype.setEnabled(false);
        } catch (Exception ex) {
            Logger.getLogger(FoodDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void newid(){
        try {
            Food obj =new Food();
                switch (jComboBox_ftype.getSelectedIndex()) {
                    case 0:
                        txt_foodID.setText(obj.newFID("CF"));
                        break;
                    case 1:
                        txt_foodID.setText(obj.newFID("CK"));
                        break;
                    case 2:
                        txt_foodID.setText(obj.newFID("BN"));
                        break;
                    default:
                        break;
                }
        } catch (Exception ex) {
            Logger.getLogger(FoodDetailsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setFoodFormEmpty(){
        txt_foodname.setText("");
        txt_qtyType.setText("");
        txt_price.setText("");
        txt_maxQty.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_add1;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_searchByType;
    private javax.swing.JButton btn_viewall;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> jComboBox_foodtype;
    private javax.swing.JComboBox<String> jComboBox_ftype;
    private javax.swing.JComboBox<String> jComboBox_searchby;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_foodAddEdit;
    private javax.swing.JPanel jPanel_main;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_food;
    private javax.swing.JLabel lbl_Error;
    private javax.swing.JLabel lbl_Error1;
    private javax.swing.JLabel lbl_empty;
    private javax.swing.JLabel lbl_foodtype;
    private javax.swing.JLabel lbl_searchby;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JTextField txt_foodID;
    private javax.swing.JTextField txt_foodname;
    private javax.swing.JTextField txt_maxQty;
    private javax.swing.JTextField txt_price;
    private javax.swing.JTextField txt_qtyType;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
