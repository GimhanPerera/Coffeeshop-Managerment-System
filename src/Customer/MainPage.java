/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;
import DBconnection.Food;
import DBconnection.LoyaltyCard;
import DBconnection.Connect;
/**
 *
 * @author Gimhan
 */


public class MainPage extends javax.swing.JFrame {

    /**
     * Creates new form MainPage
     */
    String cid="0";String o_type="Dinein";int points=0;int tp;
    public MainPage() {
        initComponents();
        firstDataGet();       
    }
    public MainPage(String cid,String o_type,int tp) {
        initComponents();
        this.cid=cid;
        this.o_type=o_type;
        this.tp=tp;
        firstDataGet();
        LoyaltyCard obj1=new LoyaltyCard();
        try {
            int req = obj1.checkRequseted(cid);
            if(req==1){
                btn_request.setVisible(false);
                lbl_request.setVisible(false);
            }
            int points=obj1.getLoyaltyPoints(cid);
            this.points=points;
            lbl_loyaltyPoints.setText(Integer.toString(points));
        } catch (Exception ex) {
            //Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Database error: Cant get loyalty point");
        }
    }
    
    public void firstDataGet(){
        jTabbedPane1.setSelectedIndex(3);
        try {
            getFood();
            } catch (Exception ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            LandingPage obj =new LandingPage();
            obj.show();
            dispose();
        }
        jTable_menu0.setRowSelectionInterval(0, 0);//set selection
        jTable_menu1.setRowSelectionInterval(0, 0);//set selection
        jTable_menu2.setRowSelectionInterval(0, 0);//set selection
        jTable_menu3.setRowSelectionInterval(0, 0);//set selection
    }
    
    public void getFood() throws Exception{
        Connect obj = new Connect();
        Connection c = obj.getConnection();  //getConnection();//Establish the connection
        
        try{ //int q=1;System.out.println(q++); <- tester
            
                Statement stmt = c.createStatement();//Prepare statement
                ResultSet rs = stmt.executeQuery("select FOOD_NAME,QUANTITY_TYPE,UNIT_PRICE from FOOD WHERE CATEGORY='COFFEE'"); //SQL stetment
                while(rs.next()){
                    String foodName=rs.getString("FOOD_NAME");
                    String qty=rs.getString("QUANTITY_TYPE");//get the value to variable "fname"
                    String price=rs.getString("UNIT_PRICE");
                    String tbData[]={foodName,qty,"0",price};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable_menu0.getModel(); 
                    tblModel.addRow(tbData);
                }
                rs = stmt.executeQuery("select FOOD_NAME,QUANTITY_TYPE,UNIT_PRICE from FOOD WHERE CATEGORY='COFFEE'"); //SQL stetment
                while(rs.next()){
                    String foodName=rs.getString("FOOD_NAME");
                    String qty=rs.getString("QUANTITY_TYPE");//get the value to variable "fname"
                    String price=rs.getString("UNIT_PRICE");
                    String tbData[]={foodName,qty,"0",price};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable_menu3.getModel();
                    tblModel.addRow(tbData);
                }
            //2nd table
                rs = stmt.executeQuery("select FOOD_NAME,QUANTITY_TYPE,UNIT_PRICE from FOOD WHERE CATEGORY='CAKE'"); //SQL stetment
                while(rs.next()){
                    String foodName=rs.getString("FOOD_NAME");
                    String qty=rs.getString("QUANTITY_TYPE");//get the value to variable "fname"
                    String price=rs.getString("UNIT_PRICE");
                    String tbData[]={foodName,qty,"0",price};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable_menu1.getModel(); 
                    tblModel.addRow(tbData);
                }
            //3rd table
                rs = stmt.executeQuery("select FOOD_NAME,QUANTITY_TYPE,UNIT_PRICE from FOOD WHERE CATEGORY='BUN'"); //SQL stetment
                while(rs.next()){
                    String foodName=rs.getString("FOOD_NAME");
                    String qty=rs.getString("QUANTITY_TYPE");//get the value to variable "fname"
                    String price=rs.getString("UNIT_PRICE");
                    String tbData[]={foodName,qty,"0",price};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable_menu2.getModel();
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

        jPanel5 = new javax.swing.JPanel();
        jComboBox_category = new javax.swing.JComboBox<>();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_menu0 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_menu1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_menu2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_menu3 = new javax.swing.JTable();
        title = new javax.swing.JPanel();
        lbl_title = new javax.swing.JLabel();
        lbl_loyaltyPoints = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        btn_request = new javax.swing.JButton();
        lbl_request = new javax.swing.JLabel();
        bill = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea_bill = new javax.swing.JTextArea();
        lbl_total = new javax.swing.JLabel();
        lbl_00 = new javax.swing.JLabel();
        lbl_tottxt = new javax.swing.JLabel();
        lbl_BILL = new javax.swing.JLabel();
        lbl_BILL1 = new javax.swing.JLabel();
        lbl_BILL2 = new javax.swing.JLabel();
        lbl_BILL3 = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_back1 = new javax.swing.JButton();
        btn_plus = new javax.swing.JButton();
        lbl_background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox_category.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jComboBox_category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Coffees", "Cakes", "Buns" }));
        jComboBox_category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_categoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addComponent(jComboBox_category, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(385, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jComboBox_category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 900, 40));

        jPanel1.setName(""); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_menu0.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_menu0.setPreferredSize(new java.awt.Dimension(300, 140));
        jTable_menu0.setRowHeight(35);
        jScrollPane1.setViewportView(jTable_menu0);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 880, 475));

        jTabbedPane1.addTab("tab1", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_menu1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Qty type", "Qty", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_menu1.setRowHeight(35);
        jScrollPane2.setViewportView(jTable_menu1);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 880, 475));

        jTabbedPane1.addTab("tab2", jPanel2);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_menu2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Qty type", "Qty", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_menu2.setRowHeight(35);
        jScrollPane3.setViewportView(jTable_menu2);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 880, 475));

        jTabbedPane1.addTab("tab3", jPanel3);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_menu3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Qty type", "Qty", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_menu3.setRowHeight(35);
        jScrollPane4.setViewportView(jTable_menu3);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 880, 475));

        jTabbedPane1.addTab("tab3", jPanel4);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 880, 530));

        title.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        title.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_title.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_title.setText("Title");
        title.add(lbl_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        lbl_loyaltyPoints.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_loyaltyPoints.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_loyaltyPoints.setText("320");
        title.add(lbl_loyaltyPoints, new org.netbeans.lib.awtextra.AbsoluteConstraints(1209, 20, 90, -1));

        jButton3.setText("jButton1");
        title.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 560, -1, -1));

        btn_request.setText("Request Loyalty Card");
        btn_request.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_requestActionPerformed(evt);
            }
        });
        title.add(btn_request, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 20, -1, -1));

        lbl_request.setText("Request send");
        title.add(lbl_request, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 30, -1, -1));

        getContentPane().add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 80));

        bill.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane5.setHorizontalScrollBar(null);

        jTextArea_bill.setEditable(false);
        jTextArea_bill.setColumns(20);
        jTextArea_bill.setFont(new java.awt.Font("Segoe UI Emoji", 0, 20)); // NOI18N
        jTextArea_bill.setRows(5);
        jScrollPane5.setViewportView(jTextArea_bill);

        lbl_total.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        lbl_total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_total.setText("0");

        lbl_00.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        lbl_00.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_00.setText(".00");

        lbl_tottxt.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        lbl_tottxt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_tottxt.setText("Total");

        lbl_BILL.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbl_BILL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_BILL.setText("BILL");

        lbl_BILL1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_BILL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_BILL1.setText("Price");

        lbl_BILL2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_BILL2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_BILL2.setText("Qty");

        lbl_BILL3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_BILL3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_BILL3.setText("Irems");

        javax.swing.GroupLayout billLayout = new javax.swing.GroupLayout(bill);
        bill.setLayout(billLayout);
        billLayout.setHorizontalGroup(
            billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billLayout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(lbl_BILL, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(billLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, billLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lbl_tottxt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addComponent(lbl_total, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_00, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(billLayout.createSequentialGroup()
                        .addComponent(lbl_BILL3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(lbl_BILL2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_BILL1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)))
                .addContainerGap())
        );
        billLayout.setVerticalGroup(
            billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(billLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_BILL, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_BILL3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_BILL1)
                        .addComponent(lbl_BILL2)))
                .addGap(0, 0, 0)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_00, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(lbl_tottxt, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        getContentPane().add(bill, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 100, 370, 520));

        btn_back.setText("Back");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 640, 180, 60));

        btn_next.setText("Next");
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });
        getContentPane().add(btn_next, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 640, 180, 60));

        btn_back1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn_back1.setText("-");
        btn_back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_back1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 640, 80, 60));

        btn_plus.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn_plus.setText("+");
        btn_plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_plusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_plus, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 640, 80, 60));
        getContentPane().add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 730));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        // TODO add your handling code here:
        LandingPage obj =new LandingPage(cid,tp);
        obj.show();
        dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        // TODO add your handling code here:
        int lines= (jTextArea_bill.getLineCount())-1;
        if(lines>0){
            String foodid[]=new String[lines];
            int qty[]=new int[lines];
        int count=0;
        for(int j=0;j<jTable_menu3.getRowCount();j++){//check coffee table
            if(Integer.parseInt((String) jTable_menu3.getValueAt(j, 2))!=0){
                foodid[count]=(String) jTable_menu3.getValueAt(j, 0);
                qty[count]=(Integer.parseInt((String) jTable_menu3.getValueAt(j, 2)));
                count++;
            }  
        }
        for(int j=0;j<jTable_menu1.getRowCount();j++){//check coffee table
            if(Integer.parseInt((String) jTable_menu1.getValueAt(j, 2))!=0){
                foodid[count]=(String) jTable_menu1.getValueAt(j, 0);
                qty[count]=(Integer.parseInt((String) jTable_menu1.getValueAt(j, 2)));
                count++;
            }  
        }
        for(int j=0;j<jTable_menu2.getRowCount();j++){//check coffee table
            if(Integer.parseInt((String) jTable_menu2.getValueAt(j, 2))!=0){
                foodid[count]=(String) jTable_menu2.getValueAt(j, 0);
                qty[count]=(Integer.parseInt((String) jTable_menu2.getValueAt(j, 2)));
                count++;
            }  
        }
        //copy the bill
        StringBuffer sb = new StringBuffer(jTextArea_bill.getText());
        for(int q=0;q<lines;q++){//convert food names to food ids
            Food obj1=new Food();
            try {
                foodid[q]=obj1.getFoodID(foodid[q]);
            } catch (Exception ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for(int q=0;q<lines;q++)//to test
        {
            System.out.println(foodid[q]+" "+qty[q]);
        }
        ConfirmOrderPage obj =new ConfirmOrderPage(sb,Integer.parseInt(lbl_total.getText()),foodid, qty,lines,cid,o_type,Integer.parseInt(lbl_loyaltyPoints.getText()),tp);
        obj.show();
        dispose();
        }
        else{
            JOptionPane.showMessageDialog(new JFrame(), "Please select your foods",
               "Imformation", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_back1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_back1ActionPerformed
        btn_plusMin(2);
    }//GEN-LAST:event_btn_back1ActionPerformed

    private void btn_plusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_plusActionPerformed
        btn_plusMin(1);
        
    }//GEN-LAST:event_btn_plusActionPerformed
    public void btn_plusMin(int x){
        int total=Integer.parseInt(lbl_total.getText());
        int i=jTabbedPane1.getSelectedIndex();
        if(i==3){
            int row=jTable_menu3.getSelectedRow();
            String a=(String) jTable_menu3.getValueAt(row, 2);
            int b=Integer.parseInt(a);
            if(x==1){
                b++;
                total+=Integer.parseInt((String) jTable_menu3.getValueAt(row, 3));
            }    
            else if(x==2&&b>0){
                b--;
                total-=Integer.parseInt((String) jTable_menu3.getValueAt(row, 3));
            }
            a= Integer. toString(b);
            jTable_menu3.setValueAt(a, row, 2);
        }
        if(i==1){
            int row=jTable_menu1.getSelectedRow();
            String a=(String) jTable_menu1.getValueAt(row, 2);
            int b=Integer.parseInt(a);
            if(x==1){
                b++;
                total+=Integer.parseInt((String) jTable_menu1.getValueAt(row, 3));
            }
            else if(x==2&&b>0){
                b--;
                total-=Integer.parseInt((String) jTable_menu1.getValueAt(row, 3));
            }
            a= Integer. toString(b);
            jTable_menu1.setValueAt(a, row, 2);
        }
        if(i==2){
            int row=jTable_menu2.getSelectedRow();
            String a=(String) jTable_menu2.getValueAt(row, 2);
            int b=Integer.parseInt(a);
            if(x==1){
                b++;
                total+=Integer.parseInt((String) jTable_menu2.getValueAt(row, 3));
            }
            else if(x==2&&b>0){
                b--;
                total-=Integer.parseInt((String) jTable_menu2.getValueAt(row, 3));
            }
            a= Integer. toString(b);
            jTable_menu2.setValueAt(a, row, 2);
        }
        lbl_total.setText(Integer.toString(total));
        billUpdate();
    }
    public void billUpdate(){
        jTextArea_bill.setText("");
        
        String a;
        String count;
        String price;       
            for(int j=0;j<jTable_menu3.getRowCount();j++){//check coffee table
                if(Integer.parseInt((String) jTable_menu3.getValueAt(j, 2))!=0){
                    a=(String) jTable_menu3.getValueAt(j, 0);
                    count=(String) jTable_menu3.getValueAt(j, 2);
                    price=(String) jTable_menu3.getValueAt(j, 3);
                    jTextArea_bill.append(a+"\t   "+count+"\t"+(Integer.parseInt(price))*(Integer.parseInt(count))+"\n");
                }  
            }      
            for(int j=0;j<jTable_menu1.getRowCount();j++){//check cake table
                if(Integer.parseInt((String) jTable_menu1.getValueAt(j, 2))!=0){
                    a=(String) jTable_menu1.getValueAt(j, 0);
                    count=(String) jTable_menu1.getValueAt(j, 2);
                    price=(String) jTable_menu1.getValueAt(j, 3);
                    jTextArea_bill.append(a+"\t   "+count+"\t"+(Integer.parseInt(price))*(Integer.parseInt(count))+"\n");
                }  
            }
            for(int j=0;j<jTable_menu2.getRowCount();j++){//check bun table
                if(Integer.parseInt((String) jTable_menu2.getValueAt(j, 2))!=0){
                    a=(String) jTable_menu2.getValueAt(j, 0);
                    count=(String) jTable_menu2.getValueAt(j, 2);
                    price=(String) jTable_menu2.getValueAt(j, 3);
                    jTextArea_bill.append(a+"\t   "+count+"\t"+(Integer.parseInt(price))*(Integer.parseInt(count))+"\n");
                }  
            }
            
        
    }
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

    private void btn_requestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_requestActionPerformed
        if(points>=1000){
            try {
                LoyaltyCard obj=new LoyaltyCard();
                obj.sendRequest(cid);
                btn_request.setText("Requset send");
                btn_request.setVisible(false);
            } catch (Exception ex) {
                Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Request Failed");
            }
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "You need more than 1000 loyalty points to request loyalty card",
               "Imformation", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_requestActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bill;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_back1;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_plus;
    private javax.swing.JButton btn_request;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable_menu0;
    private javax.swing.JTable jTable_menu1;
    private javax.swing.JTable jTable_menu2;
    private javax.swing.JTable jTable_menu3;
    private javax.swing.JTextArea jTextArea_bill;
    private javax.swing.JLabel lbl_00;
    private javax.swing.JLabel lbl_BILL;
    private javax.swing.JLabel lbl_BILL1;
    private javax.swing.JLabel lbl_BILL2;
    private javax.swing.JLabel lbl_BILL3;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_loyaltyPoints;
    private javax.swing.JLabel lbl_request;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JLabel lbl_total;
    private javax.swing.JLabel lbl_tottxt;
    private javax.swing.JPanel title;
    // End of variables declaration//GEN-END:variables
}
