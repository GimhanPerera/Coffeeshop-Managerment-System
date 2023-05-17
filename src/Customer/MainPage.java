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
import Cashier.CashierMain;
import DBconnection.Food;
import DBconnection.LoyaltyCard;
import DBconnection.Connect;
import DBconnection.Customer;
import DBconnection.Order;
import java.awt.Font;
/**
 *
 * @author Gimhan
 */


public class MainPage extends javax.swing.JFrame {

    /**
     * Creates new form MainPage
     */
    String cid="0";String o_type="Dinein";int points=0;int tp;int discount=0;
    String empmode="0";
    String orderID="";//orderID use only updating a order
    String tables[]={};
    boolean editmode=false;
    
    //for testing
    public MainPage() {
        initComponents();
        firstDataGet();
        btn_request.setVisible(false);
        
    }
    
    //normal customer order
    public MainPage(String empmode,String cid,String o_type,int tp,String tables[]) {
        initComponents();
        setBackground();
        this.empmode=empmode;
        this.cid=cid;
        this.o_type=o_type;
        this.tp=tp;
        //table array
        this.tables=new String[tables.length];
        for(int i=0;i<tables.length;i++){
            this.tables[i]=tables[i];
        } 
        //
        firstDataGet();
        LoyaltyCard obj1=new LoyaltyCard();
        try {
            int req = obj1.checkRequseted(cid);//check customer has loyalty card? requested? not requested?
            if(req==1){//requested
                btn_request.setVisible(false);
                lbl_request.setVisible(true);
            }
            else if(req==2){//customer has a card
                btn_request.setVisible(false);
                lbl_request.setVisible(false);
            }
            points=obj1.getLoyaltyPoints(cid);//get loyalty points
            lbl_loyaltyPoints.setText(Integer.toString(points));//display loyalty points
        } catch (Exception ex) {
            System.out.println("Database error: Cant get loyalty point");
            btn_request.setVisible(false);
        }
    }
    
    //If come from ConfirmOrderPage
    public MainPage(String empmode,String orderID,String o_type,int tp,int tot,String[] foodID,int[] Qyt,String tables[],String cid) {
        initComponents();
        setBackground();
        this.empmode=empmode;
        this.o_type=o_type;
        this.tp=tp;
        this.orderID=orderID;
        this.cid=cid;
        //table array
        this.tables=new String[tables.length];
        for(int i=0;i<tables.length;i++){
            this.tables[i]=tables[i];
        } 
        //
        LoyaltyCard obj1=new LoyaltyCard();
        try {
            int req = obj1.checkRequseted(cid);
            if(req==1){
                btn_request.setVisible(false);
                lbl_request.setVisible(true);
            }
            else if(req==2){
                btn_request.setVisible(false);
                lbl_request.setVisible(false);
            }
            int points=obj1.getLoyaltyPoints(cid);//get loyalty points
            this.points=points;
            lbl_loyaltyPoints.setText(Integer.toString(points));
            getFood(foodID,Qyt);//set food tables
            lbl_total.setText(Integer.toString(tot));//set total
            if(!empmode.equals("0") && "EM".equals(empmode.substring(0,2)))//check this is a cashier
                btn_next.setText("Update Order");
        } catch (Exception ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Database error: Cant get loyalty point");
            btn_request.setVisible(false);
            lbl_request.setVisible(false);
        }
    }
    
    //If this is update of an existing order
    public MainPage(boolean editmode,String empmode,String orderID,String o_type,int tp,int tot,String[] foodID,int[] Qyt) {
        initComponents();
        setBackground();
        btn_next.setText("Place order");
        btn_next.setFont(new Font("SansSerif", Font.BOLD, 20));
        this.editmode=editmode;
        this.empmode=empmode;
        try {
            Customer obj2=new Customer(orderID);
            this.cid=obj2.getC_id();
        } catch (Exception ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.o_type=o_type;
        this.tp=tp;
        this.tp=tp;
        this.orderID=orderID;
        LoyaltyCard obj1=new LoyaltyCard();
        try {
            int req = obj1.checkRequseted(cid);
            if(req==1){
                btn_request.setVisible(false);
                lbl_request.setVisible(true);
            }
            else if(req==2){
                btn_request.setVisible(false);
                lbl_request.setVisible(false);
            }
            int points=obj1.getLoyaltyPoints(cid);
            this.points=points;
            lbl_loyaltyPoints.setText(Integer.toString(points));
            getFood(foodID,Qyt);
            lbl_total.setText(Integer.toString(tot));
            if("EM".equals(cid.substring(0,2)))//check this is a cashier
                btn_next.setText("Update Order");
        } catch (Exception ex) {
            //Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Database error: Cant get loyalty point");
            btn_request.setVisible(false);
        }
    }
    
    private void setBackground(){
        //newly added
        jTable_menu1.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));
        jTable_menu2.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));
        jTable_menu3.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));
        //Set next btn invisible
        btn_next.setOpaque(false);
        btn_next.setContentAreaFilled(false);
        btn_next.setBorderPainted(false);
        //Set back btn invisible
        btn_back.setOpaque(false);
        btn_back.setContentAreaFilled(false);
        btn_back.setBorderPainted(false);
        //Set plus btn invisible
        btn_plus.setOpaque(false);
        btn_plus.setContentAreaFilled(false);
        btn_plus.setBorderPainted(false);
        //Set mins btn invisible
        btn_back1.setOpaque(false);
        btn_back1.setContentAreaFilled(false);
        btn_back1.setBorderPainted(false);
        //
    }
    
    public void firstDataGet(){
        jTabbedPane1.setSelectedIndex(3);
        try {
            getFood();
            jTable_menu0.setRowSelectionInterval(0, 0);//set selection
            jTable_menu1.setRowSelectionInterval(0, 0);//set selection
            jTable_menu2.setRowSelectionInterval(0, 0);//set selection
            jTable_menu3.setRowSelectionInterval(0, 0);//set selection
            } catch (Exception ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
            LandingPage obj =new LandingPage();
            obj.show();
            dispose();
        }
    }
    
    //normal way of setting food tables
    public void getFood() throws Exception{
        Connect obj = new Connect();
        Connection c = obj.getConnection();  //getConnection();//Establish the connection
        
        try{
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
    
    //first set table if update order or from confirm page
    public void getFood(String[] foodID,int[] Qyt) throws Exception{
        Connect obj = new Connect();
        Food obj2=new Food();
        Connection c = obj.getConnection();//Establish the connection
        jTabbedPane1.setSelectedIndex(3);
        try{
                //1st table
                Statement stmt = c.createStatement();//Prepare statement
                ResultSet rs = stmt.executeQuery("select FOOD_NAME,QUANTITY_TYPE,UNIT_PRICE from FOOD WHERE CATEGORY='COFFEE'"); //SQL stetment
                while(rs.next()){
                    String foodName=rs.getString("FOOD_NAME");
                    String qty=rs.getString("QUANTITY_TYPE");
                    String price=rs.getString("UNIT_PRICE");
                    int x=0;
                    for(int i=0;i<foodID.length;i++){
                        if(foodName.equals(obj2.getFoodName(foodID[i])))
                            x=Qyt[i];
                    }
                    String tbData[]={foodName,qty,Integer.toString(x),price};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable_menu0.getModel(); 
                    tblModel.addRow(tbData);
                }
                rs = stmt.executeQuery("select FOOD_NAME,QUANTITY_TYPE,UNIT_PRICE from FOOD WHERE CATEGORY='COFFEE'"); //SQL stetment
                while(rs.next()){
                    String foodName=rs.getString("FOOD_NAME");
                    String qty=rs.getString("QUANTITY_TYPE");
                    String price=rs.getString("UNIT_PRICE");
                    int x=0;
                    for(int i=0;i<foodID.length;i++){
                        if(foodName.equals(obj2.getFoodName(foodID[i])))
                            x=Qyt[i];
                    }
                    String tbData[]={foodName,qty,Integer.toString(x),price};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable_menu3.getModel();
                    tblModel.addRow(tbData);
                }
            //2nd table
                rs = stmt.executeQuery("select FOOD_NAME,QUANTITY_TYPE,UNIT_PRICE from FOOD WHERE CATEGORY='CAKE'"); //SQL stetment
                while(rs.next()){
                    String foodName=rs.getString("FOOD_NAME");
                    String qty=rs.getString("QUANTITY_TYPE");
                    String price=rs.getString("UNIT_PRICE");
                    int x=0;
                    for(int i=0;i<foodID.length;i++){
                        if(foodName.equals(obj2.getFoodName(foodID[i])))
                            x=Qyt[i];
                    }
                    String tbData[]={foodName,qty,Integer.toString(x),price};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable_menu1.getModel(); 
                    tblModel.addRow(tbData);
                }
            //3rd table
                rs = stmt.executeQuery("select FOOD_NAME,QUANTITY_TYPE,UNIT_PRICE from FOOD WHERE CATEGORY='BUN'"); //SQL stetment
                while(rs.next()){
                    String foodName=rs.getString("FOOD_NAME");
                    String qty=rs.getString("QUANTITY_TYPE");
                    String price=rs.getString("UNIT_PRICE");
                    int x=0;
                    for(int i=0;i<foodID.length;i++){
                        if(foodName.equals(obj2.getFoodName(foodID[i])))
                            x=Qyt[i];
                    }
                    String tbData[]={foodName,qty,Integer.toString(x),price};
                    DefaultTableModel tblModel =(DefaultTableModel)jTable_menu2.getModel();
                    tblModel.addRow(tbData);
                }
                billUpdate();
                jTable_menu0.setRowSelectionInterval(0, 0);//set selection
                jTable_menu1.setRowSelectionInterval(0, 0);//set selection
                jTable_menu2.setRowSelectionInterval(0, 0);//set selection
                jTable_menu3.setRowSelectionInterval(0, 0);//set selection
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
        lbl_barBackground = new javax.swing.JLabel();
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
        lbl_loyaltyPoints = new javax.swing.JLabel();
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
        lbl_backgroundBill = new javax.swing.JLabel();
        btn_back = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_back1 = new javax.swing.JButton();
        btn_plus = new javax.swing.JButton();
        lbl_background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox_category.setBackground(new java.awt.Color(192, 124, 124));
        jComboBox_category.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jComboBox_category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Coffees", "Cakes", "Buns" }));
        jComboBox_category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_categoryActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox_category, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 0, 190, -1));

        lbl_barBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Menue pg bar.png"))); // NOI18N
        jPanel5.add(lbl_barBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, -1, 905, 47));

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

        lbl_loyaltyPoints.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_loyaltyPoints.setForeground(new java.awt.Color(255, 255, 255));
        lbl_loyaltyPoints.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_loyaltyPoints.setText("0");
        getContentPane().add(lbl_loyaltyPoints, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 20, 90, -1));

        btn_request.setBackground(new java.awt.Color(205, 102, 102));
        btn_request.setForeground(new java.awt.Color(255, 255, 255));
        btn_request.setText("Request Loyalty Card");
        btn_request.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_request.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_requestActionPerformed(evt);
            }
        });
        getContentPane().add(btn_request, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 20, -1, -1));

        lbl_request.setForeground(new java.awt.Color(255, 255, 255));
        lbl_request.setText("Request send");
        getContentPane().add(lbl_request, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 30, -1, -1));

        bill.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bill.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane5.setHorizontalScrollBar(null);

        jTextArea_bill.setEditable(false);
        jTextArea_bill.setColumns(20);
        jTextArea_bill.setFont(new java.awt.Font("Segoe UI Emoji", 0, 20)); // NOI18N
        jTextArea_bill.setRows(5);
        jScrollPane5.setViewportView(jTextArea_bill);

        bill.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 107, 366, 349));

        lbl_total.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        lbl_total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_total.setText("0");
        bill.add(lbl_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 464, 156, -1));

        lbl_00.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        lbl_00.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_00.setText(".00");
        bill.add(lbl_00, new org.netbeans.lib.awtextra.AbsoluteConstraints(326, 462, 39, 39));

        lbl_tottxt.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        lbl_tottxt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_tottxt.setText("Total");
        bill.add(lbl_tottxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 462, 71, 39));

        lbl_BILL.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbl_BILL.setForeground(new java.awt.Color(255, 255, 255));
        lbl_BILL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_BILL.setText("BILL");
        bill.add(lbl_BILL, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 7, 71, 45));

        lbl_BILL1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_BILL1.setForeground(new java.awt.Color(255, 255, 255));
        lbl_BILL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_BILL1.setText("Price");
        bill.add(lbl_BILL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 70, 71, -1));

        lbl_BILL2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_BILL2.setForeground(new java.awt.Color(255, 255, 255));
        lbl_BILL2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_BILL2.setText("Qty");
        bill.add(lbl_BILL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 71, -1));

        lbl_BILL3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_BILL3.setForeground(new java.awt.Color(255, 255, 255));
        lbl_BILL3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_BILL3.setText("Items");
        bill.add(lbl_BILL3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 70, 71, -1));

        lbl_backgroundBill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Menue pg bill 2.png"))); // NOI18N
        bill.add(lbl_backgroundBill, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 520));

        getContentPane().add(bill, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 100, 380, 520));

        btn_back.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn_back.setForeground(new java.awt.Color(255, 255, 255));
        btn_back.setText("Back");
        btn_back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 640, 180, 60));

        btn_next.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        btn_next.setForeground(new java.awt.Color(255, 255, 255));
        btn_next.setText("Next");
        btn_next.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });
        getContentPane().add(btn_next, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 640, 190, 60));

        btn_back1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn_back1.setForeground(new java.awt.Color(255, 255, 255));
        btn_back1.setText("-");
        btn_back1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_back1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 635, 80, 60));

        btn_plus.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        btn_plus.setForeground(new java.awt.Color(255, 255, 255));
        btn_plus.setText("+");
        btn_plus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_plusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_plus, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 635, 80, 60));

        lbl_background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Menue pg updated.png"))); // NOI18N
        getContentPane().add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 730));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        if(!"0".equals(empmode)){//if this is a update of a order
            //Because of can't close cashier window...
            //CashierMain obj4=new CashierMain(empmode);//need to pass cashier id
            //obj4.show();
            if(editmode)
                this.dispose();
            else{
                LandingPage obj =new LandingPage(empmode,cid,tp);
                obj.show();
                dispose();
            }            
        }
        else{//if this is a new order
            LandingPage obj =new LandingPage(empmode,cid,tp);
            obj.show();
            dispose();
        }
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        int lines= (jTextArea_bill.getLineCount())-1;//get selected item count
        if(lines>0){
            String foodid[]=new String[lines];//to store food ids
            int qty[]=new int[lines];//to store qty of this items
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
        if(editmode){//if this is a update of a order
            //convert food names to food ids
            for(int q=0;q<lines;q++){
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
            //update the order
            Order obj=new Order();
                try {
                    obj.updateOrder(orderID,foodid,qty,Integer.parseInt(lbl_total.getText()));
                } catch (Exception ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(new JFrame(), "Can't update the order",
               "Imformation", JOptionPane.ERROR_MESSAGE);
                }
            //Cashier page cant close when update a order
            //CashierMain obj4=new CashierMain(empmode);//need to pass cashier id
            //obj4.show();
            dispose();
        }
        else{//If this is a new order
            StringBuffer sb = new StringBuffer(jTextArea_bill.getText());//copy the bill
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
            ConfirmOrderPage obj2 =new ConfirmOrderPage(empmode,sb,Integer.parseInt(lbl_total.getText()),foodid, qty,lines,cid,o_type,Integer.parseInt(lbl_loyaltyPoints.getText()),tp,0,tables);
            obj2.show();
            dispose();
            } 
        }
        else{
            JOptionPane.showMessageDialog(new JFrame(), "Please select your foods",
               "Imformation", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_back1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_back1ActionPerformed
        btn_plusMin(false);
    }//GEN-LAST:event_btn_back1ActionPerformed

    private void btn_plusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_plusActionPerformed
        btn_plusMin(true);
        
    }//GEN-LAST:event_btn_plusActionPerformed
    public void btn_plusMin(boolean x){
        Food obj5=new Food();
        int total=Integer.parseInt(lbl_total.getText());
        int i=jTabbedPane1.getSelectedIndex();//check selected food type       
        if(i==1){
            int row=jTable_menu1.getSelectedRow();
            String food_name=(String) jTable_menu1.getValueAt(row, 0);//get Food name for check availability
            String a=(String) jTable_menu1.getValueAt(row, 2);
            int b=Integer.parseInt(a);//get selected amount
            if(x==true){
                try {
                    //CHECK FOOD AVALABILITY
                    //create a method called isAvailable(fID,Selected amount)->if available,return true.if not, return false.
                    int temp=b;System.out.println("TEST 1");
                    if(editmode){//If this is a update of placed order
                        temp=temp-obj5.foodCountOfTheOrder(food_name,orderID);
                    }
                    if(obj5.isFoodAvailability(food_name,temp)){//if food available
                        b++;
                        total+=Integer.parseInt((String) jTable_menu1.getValueAt(row, 3));
                    }
                    else{//if not available
                        JOptionPane.showMessageDialog(new JFrame(), "Out of stock",
                                "Imformation", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(x==false&&b>0){
                b--;
                total-=Integer.parseInt((String) jTable_menu1.getValueAt(row, 3));
            }
            a= Integer. toString(b);
            jTable_menu1.setValueAt(a, row, 2);
        }
        if(i==2){
            int row=jTable_menu2.getSelectedRow();
            String food_name=(String) jTable_menu2.getValueAt(row, 0);//get Food name for check availability
            String a=(String) jTable_menu2.getValueAt(row, 2);
            int b=Integer.parseInt(a);
            if(x==true){
                try {
                    //CHECK FOOD AVALABILITY
                    int temp=b;
                    if(editmode){//If this is a update of placed order
                        temp=temp-obj5.foodCountOfTheOrder(food_name,orderID);
                    }
                    if(obj5.isFoodAvailability(food_name,temp)){//if food available
                        b++;
                        total+=Integer.parseInt((String) jTable_menu2.getValueAt(row, 3));
                    }
                    else{//if not available
                        JOptionPane.showMessageDialog(new JFrame(), "Out of stock",
                                "Imformation", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(x==false&&b>0){
                b--;
                total-=Integer.parseInt((String) jTable_menu2.getValueAt(row, 3));
            }
            a= Integer. toString(b);
            jTable_menu2.setValueAt(a, row, 2);
        }
        if(i==3){
            int row=jTable_menu3.getSelectedRow();
            String food_name=(String) jTable_menu3.getValueAt(row, 0);//get Food name for check availability
            String a=(String) jTable_menu3.getValueAt(row, 2);
            int b=Integer.parseInt(a);
            if(x==true){
                try {
                    //CHECK FOOD AVALABILITY
                    int temp=b;
                    if(editmode){//If this is a update of placed order
                        temp=temp-obj5.foodCountOfTheOrder(food_name,orderID);
                    }
                    if(obj5.isFoodAvailability(food_name,temp)){//if food available
                        b++;
                        total+=Integer.parseInt((String) jTable_menu3.getValueAt(row, 3));
                    }
                    else{//if not available
                        JOptionPane.showMessageDialog(new JFrame(), "Out of stock",
                                "Imformation", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }    
            else if(x==false&&b>0){
                b--;
                total-=Integer.parseInt((String) jTable_menu3.getValueAt(row, 3));
            }
            a= Integer. toString(b);
            jTable_menu3.setValueAt(a, row, 2);
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
                    jTextArea_bill.append(" "+a+"\t   "+count+"\t"+(Integer.parseInt(price))*(Integer.parseInt(count))+".00\n");
                }  
            }      
            for(int j=0;j<jTable_menu1.getRowCount();j++){//check cake table
                if(Integer.parseInt((String) jTable_menu1.getValueAt(j, 2))!=0){
                    a=(String) jTable_menu1.getValueAt(j, 0);
                    count=(String) jTable_menu1.getValueAt(j, 2);
                    price=(String) jTable_menu1.getValueAt(j, 3);
                    jTextArea_bill.append(" "+a+"\t   "+count+"\t"+(Integer.parseInt(price))*(Integer.parseInt(count))+".00\n");
                }  
            }
            for(int j=0;j<jTable_menu2.getRowCount();j++){//check bun table
                if(Integer.parseInt((String) jTable_menu2.getValueAt(j, 2))!=0){
                    a=(String) jTable_menu2.getValueAt(j, 0);
                    count=(String) jTable_menu2.getValueAt(j, 2);
                    price=(String) jTable_menu2.getValueAt(j, 3);
                    jTextArea_bill.append(" "+a+"\t   "+count+"\t"+(Integer.parseInt(price))*(Integer.parseInt(count))+".00\n");
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
        if(points>=100){
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
            JOptionPane.showMessageDialog(new JFrame(), "You need more than 100 a loyalty points to request loyalty card",
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
    private javax.swing.JLabel lbl_backgroundBill;
    private javax.swing.JLabel lbl_barBackground;
    private javax.swing.JLabel lbl_loyaltyPoints;
    private javax.swing.JLabel lbl_request;
    private javax.swing.JLabel lbl_total;
    private javax.swing.JLabel lbl_tottxt;
    // End of variables declaration//GEN-END:variables
}
