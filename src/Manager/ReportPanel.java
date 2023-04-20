/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Manager;
import DBconnection.Connect;
import DBconnection.getDate;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Gimhan
 */
public class ReportPanel extends javax.swing.JPanel {

    /**
     * Creates new form ReportPanel
     */
    public ReportPanel() {
        initComponents();
        jComboBox_years.setVisible(true);
        jComboBox_months.setVisible(false);
        jComboBox_date.setVisible(false);
        txt_income.setVisible(false);
        lbl_income.setVisible(false);
        jTable1.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 15));
        jTable2.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 15));
        //Set years
        jComboBox_years.removeAllItems();
        for(int i=2022;i<=Year.now().getValue();i++){
            jComboBox_years.addItem(Integer.toString(i));System.out.println(i);
        }
        setTimeFrame();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbl_timeHeader = new javax.swing.JLabel();
        lbl_time = new javax.swing.JLabel();
        lbl_report = new javax.swing.JLabel();
        combox_reportType = new javax.swing.JComboBox<>();
        combox_timeFrame = new javax.swing.JComboBox<>();
        btn_generate = new javax.swing.JButton();
        btn_print = new javax.swing.JButton();
        jScrollPane_income = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane_sale = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        lbl_time1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox_months = new javax.swing.JComboBox<>();
        jComboBox_years = new javax.swing.JComboBox<>();
        jComboBox_date = new javax.swing.JComboBox<>();
        txt_income = new javax.swing.JLabel();
        lbl_income = new javax.swing.JLabel();
        lbl_background = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(173, 85, 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 740, 1170, 30));

        jPanel1.setBackground(new java.awt.Color(173, 85, 2));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 30));

        lbl_timeHeader.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbl_timeHeader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_timeHeader.setText("Months");
        add(lbl_timeHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 357, 200, 20));

        lbl_time.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lbl_time.setText("Time frame");
        add(lbl_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, -1, 20));

        lbl_report.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lbl_report.setText("Report Type");
        add(lbl_report, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, -1, 20));

        combox_reportType.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        combox_reportType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Income Report", "Sales Report" }));
        combox_reportType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combox_reportTypeActionPerformed(evt);
            }
        });
        add(combox_reportType, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, 230, 30));

        combox_timeFrame.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        combox_timeFrame.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Annually", "Monthly", "Daily" }));
        combox_timeFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combox_timeFrameActionPerformed(evt);
            }
        });
        add(combox_timeFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 230, 30));

        btn_generate.setBackground(new java.awt.Color(173, 85, 2));
        btn_generate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_generate.setForeground(new java.awt.Color(255, 255, 255));
        btn_generate.setText("Generate");
        btn_generate.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_generate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_generate.setFocusPainted(false);
        btn_generate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generateActionPerformed(evt);
            }
        });
        add(btn_generate, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 280, 100, 40));

        btn_print.setText("Print");
        add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 300, 120, 30));

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Amount without discounts", "Discount", "Income"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setFocusable(false);
        jTable1.setRowHeight(29);
        jScrollPane_income.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        add(jScrollPane_income, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 910, 330));

        jTable2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Qty type", "Qty", "Income"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setRowHeight(29);
        jScrollPane_sale.setViewportView(jTable2);

        add(jScrollPane_sale, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 910, 330));

        lbl_time1.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        lbl_time1.setText("Time frame");
        add(lbl_time1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, -1, 20));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("Reports");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, -1, -1));

        jComboBox_months.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jComboBox_months.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        jComboBox_months.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_monthsActionPerformed(evt);
            }
        });
        add(jComboBox_months, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 230, -1, -1));

        jComboBox_years.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jComboBox_years.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2022", "2023" }));
        jComboBox_years.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_yearsActionPerformed(evt);
            }
        });
        add(jComboBox_years, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, -1, -1));

        jComboBox_date.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jComboBox_date.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27" }));
        add(jComboBox_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 230, -1, -1));

        txt_income.setFont(new java.awt.Font("Segoe UI", 1, 27)); // NOI18N
        txt_income.setText("TOTAL INCOME");
        add(txt_income, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 690, -1, 30));

        lbl_income.setFont(new java.awt.Font("Segoe UI", 1, 27)); // NOI18N
        lbl_income.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_income.setText("0.00");
        add(lbl_income, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 690, 220, 30));

        lbl_background.setBackground(new java.awt.Color(173, 85, 2));
        add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 770));
    }// </editor-fold>//GEN-END:initComponents

    private void combox_timeFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combox_timeFrameActionPerformed
        setTimeFrame();
    }//GEN-LAST:event_combox_timeFrameActionPerformed
    
    private void setTimeFrame(){//frontend handle by this
        switch(combox_timeFrame.getSelectedIndex()){
            case 0://annualy
                jComboBox_years.setEnabled(true);
                jComboBox_months.setVisible(false);
                jComboBox_date.setVisible(false);
                btn_print.setEnabled(true);
                btn_generate.setEnabled(true);
                break;
            case 1://monthly
                jComboBox_years.setEnabled(true);
                jComboBox_months.setVisible(true);
                jComboBox_date.setVisible(false);
                btn_print.setEnabled(true);
                btn_generate.setEnabled(true);
                break;
            case 2://daily
                boolean temp=true;
                if(combox_reportType.getSelectedIndex()==0)//if select income report
                    temp=false;
                jComboBox_years.setEnabled(temp);
                jComboBox_months.setVisible(temp);
                jComboBox_date.setVisible(temp);
                jComboBox_date.setSelectedIndex(0);
                btn_print.setEnabled(temp);
                btn_generate.setEnabled(temp);
                setlastdate();
                break;
        }
    }
    
    public void setIncomeTable() throws Exception{
        clearTables();
        Connect obj = new Connect();
        Connection c = obj.getConnection();  //getConnection();//Establish the connection
        String date_from="",date_to="";
        int amount=0,discounts=0,count=0,tot=0;
        switch(combox_timeFrame.getSelectedIndex()){
            case 0://annualy
                count=1;
                int month=1;
                date_from=(String)jComboBox_years.getSelectedItem()+"-01-01";
                date_to=(String)jComboBox_years.getSelectedItem()+"-12-31";
                try{ //int q=1;System.out.println(q++); <- tester
                    Statement stmt = c.createStatement();//Prepare statement
                    ResultSet rs = stmt.executeQuery("select MONTH(ORDER_DATETIME),SUM(AMOUNT),SUM(DISCOUNT) from ORDER_T Inner Join INVOICE ON INVOICE.ORDERID=ORDER_T.ORDER_NUMBER " +
                                "where ORDERID IN (select ORDER_NUMBER from ORDER_T where ORDER_DATETIME > '"+date_from+" 00:00:00' AND ORDER_DATETIME <'"+date_to+" 23:59:59') " +
                                    "AND INVOICE.STATUS='Paid' " +
                                        "GROUP BY MONTH(ORDER_DATETIME) " +
                                                "ORDER BY MONTH(ORDER_DATETIME)"); //SQL stetment
                    while(rs.next()){
                        month=rs.getInt("MONTH(ORDER_DATETIME)");
                        amount=rs.getInt("sum(AMOUNT)");
                        discounts=rs.getInt("sum(DISCOUNT)");
                        tot+=amount-discounts;
                        if(count==month){
                            String tbData[]={Integer.toString(month),Integer.toString(amount)+".00",Integer.toString(discounts)+".00",Integer.toString(amount-discounts)+".00"};
                            DefaultTableModel tblMode1 =(DefaultTableModel)jTable1.getModel();
                            tblMode1.addRow(tbData);
                            count++;
                        }
                        else{
                            while(count!=month){
                            String tbData[]={Integer.toString(count),"0.00","0.00","0.00"};
                            DefaultTableModel tblMode1 =(DefaultTableModel)jTable1.getModel();
                            tblMode1.addRow(tbData);
                            count++;
                            if(count==month){
                                String tbData1[]={Integer.toString(month),Integer.toString(amount)+".00",Integer.toString(discounts)+".00",Integer.toString(amount-discounts)+".00"};
                                DefaultTableModel tblMode2 =(DefaultTableModel)jTable1.getModel();
                                tblMode1.addRow(tbData1);
                                count++;
                            }
                            if(count==13)
                                break;
                            }
                        }
                    }            
                }
                catch(SQLException ex){//Is database has a problem, this catch stetment catch it
                    System.out.println(ex);
                }
                finally{
                    c.close(); 
                } 
                break;
            case 1://monthly
                //get last day of the month
                int year=Integer.parseInt(jComboBox_years.getSelectedItem().toString());
                byte day=1;
                month=jComboBox_months.getSelectedIndex()+1;
                getDate obj2=new getDate();
                int Ldate=obj2.lastdayofmonth(year, month);
                count=1;
                //
                date_from=(String)jComboBox_years.getSelectedItem()+"-"+month+"-01";
                date_to=(String)jComboBox_years.getSelectedItem()+"-"+month+"-"+Ldate;
                try{ //int q=1;System.out.println(q++); <- tester
                    Statement stmt = c.createStatement();//Prepare statement
                    ResultSet rs = stmt.executeQuery("select DAY(ORDER_DATETIME),SUM(AMOUNT),SUM(DISCOUNT) from ORDER_T Inner Join INVOICE ON INVOICE.ORDERID=ORDER_T.ORDER_NUMBER " +
                                "where ORDERID IN (select ORDER_NUMBER from ORDER_T where ORDER_DATETIME > '"+date_from+" 00:00:00' AND ORDER_DATETIME <'"+date_to+" 23:59:59') " +
                                    "AND INVOICE.STATUS='Paid' " +
                                        "GROUP BY DAY(ORDER_DATETIME) " +
                                                "ORDER BY DAY(ORDER_DATETIME)"); //SQL stetment
                    while(rs.next()){
                        day=rs.getByte("DAY(ORDER_DATETIME)");
                        amount=rs.getInt("sum(AMOUNT)");
                        discounts=rs.getInt("sum(DISCOUNT)");
                        tot+=amount-discounts;
                        if(count==day){
                            String tbData[]={Integer.toString(day),Integer.toString(amount)+".00",Integer.toString(discounts)+".00",Integer.toString(amount-discounts)+".00"};
                            DefaultTableModel tblMode1 =(DefaultTableModel)jTable1.getModel();
                            tblMode1.addRow(tbData);
                            count++;
                        }
                        else{
                            while(count!=day){
                            String tbData[]={Integer.toString(count),"0.00","0.00","0.00"};
                            DefaultTableModel tblMode1 =(DefaultTableModel)jTable1.getModel();
                            tblMode1.addRow(tbData);
                            count++;
                            if(count==day){
                                String tbData1[]={Integer.toString(day),Integer.toString(amount)+".00",Integer.toString(discounts)+".00",Integer.toString(amount-discounts)+".00"};
                                DefaultTableModel tblMode2 =(DefaultTableModel)jTable1.getModel();
                                tblMode1.addRow(tbData1);
                                count++;
                                break;
                            }
                            if(count==(Ldate+1))
                                break;
                            }
                        }
                    } 
                    while(count<(Ldate+1)){
                            String tbData[]={Integer.toString(count++),"0.00","0.00","0.00"};
                            DefaultTableModel tblMode1 =(DefaultTableModel)jTable1.getModel();
                            tblMode1.addRow(tbData);
                    }
                }
                catch(SQLException ex){//Is database has a problem, this catch stetment catch it
                    System.out.println(ex);
                }
                finally{
                    c.close(); 
                } 
                break;
        }
        lbl_income.setText(tot+".00");
          
    }
    
    //NOT COMPLETED
    public void setSalesTable() throws Exception{
        Connect obj = new Connect();
        Connection c = obj.getConnection();//Establish the connection
        try{ //int q=1;System.out.println(q++); <- tester
        clearTables();
        getDate obj2=new getDate();
        String date_from="",date_to="", food="",category="";
        int qty=0,tot=0,year;
        byte month,Ldate;
        Statement stmt = c.createStatement();//Prepare statement
        Statement stmt2 = c.createStatement();//Prepare statement
        ResultSet rs;
        switch(combox_timeFrame.getSelectedIndex()){
            case 0://annualy
                date_from=(String)jComboBox_years.getSelectedItem()+"-01-01";
                date_to=(String)jComboBox_years.getSelectedItem()+"-12-31";
                //int q=1;System.out.println(q++); <- tester
                    rs = stmt.executeQuery("select food_NAME,CATEGORY,SUM(QUANTITY) from ORDER_T o Inner Join ORDER_FOOD OandF ON OandF.ORDER_NUMBER=o.ORDER_NUMBER INNER JOIN " +
                                            " FOOD f ON f.FOOD_ID=OandF.FOOD_ID  " +
                                                    " where o.ORDER_NUMBER IN ( " +
                                                        " select ORDER_NUMBER from ORDER_T Inner Join INVOICE ON INVOICE.ORDERID=ORDER_T.ORDER_NUMBER where ORDER_DATETIME > '"+date_from+" 00:00:00' " +
                                    " AND ORDER_DATETIME <'"+date_to+" 23:59:59'  AND INVOICE.STATUS='Paid') " +
                                        "GROUP BY CATEGORY,food_NAME " +
                                                    "ORDER BY SUM(quantity) DESC"); //SQL stetment
                    while(rs.next()){
                        food=rs.getString("food_NAME");
                        category=rs.getString("CATEGORY");
                        qty=rs.getInt("SUM(QUANTITY)");
                        ResultSet rs1 = stmt2.executeQuery("select UNIT_PRICE from FOOD where food_NAME='"+food+"'"); //SQL stetment
                        while(rs1.next()){
                            tot=rs1.getInt("UNIT_PRICE")*qty;
                        }
                        String tbData[]={food,category,Integer.toString(qty),tot+".00"};
                        DefaultTableModel tblMode1 =(DefaultTableModel)jTable2.getModel();
                        tblMode1.addRow(tbData);
                    }  
                break;
            case 1://monthly
                //get last day of the month
                year=Integer.parseInt(jComboBox_years.getSelectedItem().toString());
                month=(byte) (jComboBox_months.getSelectedIndex()+1);
                Ldate=(byte) obj2.lastdayofmonth(year, month);
                //
                date_from=(String)jComboBox_years.getSelectedItem()+"-"+month+"-01";
                date_to=(String)jComboBox_years.getSelectedItem()+"-"+month+"-"+Ldate;
                //int q=1;System.out.println(q++); <- tester
                    rs = stmt.executeQuery("select food_NAME,CATEGORY,SUM(QUANTITY) from ORDER_T o Inner Join ORDER_FOOD OandF ON OandF.ORDER_NUMBER=o.ORDER_NUMBER INNER JOIN " +
                                            " FOOD f ON f.FOOD_ID=OandF.FOOD_ID  " +
                                                    " where o.ORDER_NUMBER IN ( " +
                                                        " select ORDER_NUMBER from ORDER_T Inner Join INVOICE ON INVOICE.ORDERID=ORDER_T.ORDER_NUMBER where ORDER_DATETIME > '"+date_from+" 00:00:00' " +
                                    " AND ORDER_DATETIME <'"+date_to+" 23:59:59'  AND INVOICE.STATUS='Paid') " +
                                        "GROUP BY CATEGORY,food_NAME " +
                                                    "ORDER BY SUM(quantity) DESC"); //SQL stetment
                    while(rs.next()){
                        food=rs.getString("food_NAME");
                        category=rs.getString("CATEGORY");
                        qty=rs.getInt("SUM(QUANTITY)");System.out.println("TTTT1");
                        ResultSet rs1 = stmt2.executeQuery("select UNIT_PRICE from FOOD where food_NAME='"+food+"'"); //SQL stetment
                        while(rs1.next()){System.out.println("TTTT2");
                            tot=rs1.getInt("UNIT_PRICE")*qty;
                        }System.out.println("TTTT3");
                        String tbData[]={food,category,Integer.toString(qty),tot+".00"};
                        DefaultTableModel tblMode1 =(DefaultTableModel)jTable2.getModel();
                        tblMode1.addRow(tbData);
                    }    
                break;
            case 2://Daily
                //get last day of the month
                year=Integer.parseInt(jComboBox_years.getSelectedItem().toString());
                month=(byte) (jComboBox_months.getSelectedIndex()+1);
                Ldate=(byte) (jComboBox_date.getSelectedIndex()+1);
                //
                date_from = year+"-"+month+"-"+Ldate;
                date_to = date_from;
                    rs = stmt.executeQuery("select food_NAME,CATEGORY,SUM(QUANTITY) from ORDER_T o Inner Join ORDER_FOOD OandF ON OandF.ORDER_NUMBER=o.ORDER_NUMBER INNER JOIN " +
                                            " FOOD f ON f.FOOD_ID=OandF.FOOD_ID  " +
                                                    " where o.ORDER_NUMBER IN ( " +
                                                        " select ORDER_NUMBER from ORDER_T Inner Join INVOICE ON INVOICE.ORDERID=ORDER_T.ORDER_NUMBER where ORDER_DATETIME > '"+date_from+" 00:00:00' " +
                                    " AND ORDER_DATETIME <'"+date_to+" 23:59:59'  AND INVOICE.STATUS='Paid') " +
                                        "GROUP BY CATEGORY,food_NAME " +
                                                    "ORDER BY SUM(quantity) DESC"); //SQL stetment
                    while(rs.next()){
                        food=rs.getString("food_NAME");
                        category=rs.getString("CATEGORY");
                        qty=rs.getInt("SUM(QUANTITY)");
                        ResultSet rs1 = stmt2.executeQuery("select UNIT_PRICE from FOOD where food_NAME='"+food+"'"); //SQL stetment
                        while(rs1.next()){
                            tot=rs1.getInt("UNIT_PRICE")*qty;
                        }
                        String tbData[]={food,category,Integer.toString(qty),tot+".00"};
                        DefaultTableModel tblMode1 =(DefaultTableModel)jTable2.getModel();
                        tblMode1.addRow(tbData);
                    } 
                    break;           
                }
                
                }
                catch(SQLException ex){//Is database has a problem, this catch stetment catch it
                    System.out.println(ex);
                }
                finally{
                    c.close(); 
                } 
    }
    
    private void clearTables(){
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
    }
    
    private void jComboBox_monthsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_monthsActionPerformed
        setlastdate();
    }//GEN-LAST:event_jComboBox_monthsActionPerformed

    private void jComboBox_yearsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_yearsActionPerformed
        setlastdate();
    }//GEN-LAST:event_jComboBox_yearsActionPerformed

    private void combox_reportTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combox_reportTypeActionPerformed
        setTimeFrame();
    }//GEN-LAST:event_combox_reportTypeActionPerformed

    private void btn_generateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generateActionPerformed
        if(combox_reportType.getSelectedIndex()==0){
            try {//if select income report
                setIncomeTable();//Create table
                txt_income.setVisible(true);
                lbl_income.setVisible(true);
                jScrollPane_income.setVisible(true);
                lbl_timeHeader.setVisible(true);
                if(combox_timeFrame.getSelectedIndex()==0)
                    lbl_timeHeader.setText("Months");
                else
                    lbl_timeHeader.setText("Dates of Month");
            } catch (Exception ex) {
                Logger.getLogger(ReportPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                setSalesTable();
                txt_income.setVisible(false);
                lbl_income.setVisible(false);
                jScrollPane_income.setVisible(false);
                lbl_timeHeader.setVisible(false);
            } catch (Exception ex) {
                Logger.getLogger(ReportPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_generateActionPerformed
    public void setlastdate(){
        if(combox_timeFrame.getSelectedIndex()==2){
            int year=Integer.parseInt(jComboBox_years.getSelectedItem().toString());
            int month=jComboBox_months.getSelectedIndex()+1;
            getDate obj=new getDate();
            int Ldate=obj.lastdayofmonth(year, month);
            jComboBox_date.removeAllItems();
            for(int i=1;i<=Ldate;i++){
                jComboBox_date.addItem(Integer.toString(i));
            }
            System.out.println("Set dates of jComboBox_date");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_generate;
    private javax.swing.JButton btn_print;
    private javax.swing.JComboBox<String> combox_reportType;
    private javax.swing.JComboBox<String> combox_timeFrame;
    private javax.swing.JComboBox<String> jComboBox_date;
    private javax.swing.JComboBox<String> jComboBox_months;
    private javax.swing.JComboBox<String> jComboBox_years;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane_income;
    private javax.swing.JScrollPane jScrollPane_sale;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_income;
    private javax.swing.JLabel lbl_report;
    private javax.swing.JLabel lbl_time;
    private javax.swing.JLabel lbl_time1;
    private javax.swing.JLabel lbl_timeHeader;
    private javax.swing.JLabel txt_income;
    // End of variables declaration//GEN-END:variables
}
