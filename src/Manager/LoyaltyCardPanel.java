/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Manager;

import Customer.Conn;
import DBconnection.Connect;
import DBconnection.LoyaltyCard;
import com.fazecast.jSerialComm.SerialPort;
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
public class LoyaltyCardPanel extends javax.swing.JPanel  implements Runnable{

    /**
     * Creates new form LoyaltyCardPanel
     */
    public LoyaltyCardPanel() {
        try {
            initComponents();
            lbl_Error.setVisible(false);
            clearTable();
            getAllCards();
            jTable.setRowSelectionInterval(0, 0);
            lbl_waiting.setVisible(true);
            lbl_notOk.setVisible(false);
            lbl_ok.setVisible(false);
            jPanel_loyaltycard.setVisible(false);
        } catch (Exception ex) {
            Logger.getLogger(LoyaltyCardPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Thread testing - Card read.
    private static boolean exit=false;private static String pin=""; 
    @Override
    public void run() {
        reading();
    }
    
    public void reading(){
        String S="";
char[] c = new char[8];
        
        SerialPort [] AvailablePorts = SerialPort.getCommPorts();   
        //Open the first Available port
        SerialPort MySerialPort = AvailablePorts[0];//problem happen here if not connected the Aduino correctly
        int BaudRate = 9600;
        int DataBits = 8;
        int StopBits = SerialPort.ONE_STOP_BIT;
        int Parity   = SerialPort.NO_PARITY;
        //Sets all serial port parameters at one time
        MySerialPort.setComPortParameters(BaudRate,DataBits,StopBits,Parity);

        //Set Read Time outs
        MySerialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 0);
        MySerialPort.openPort(); //open the port
                                 //Arduino May get reset 
        //Thread.sleep(2000);
        if (MySerialPort.isOpen())//Check whether port open/not
              System.out.println("is Open ");
        else{
           System.out.println(" Port not open ");
           exit=true;
        }
        try {
                int numRead=0;
                int time=0;String q="9B4D7422";
                while (time<=5){//set time here
                    
                    byte[] readBuffer = new byte[100];

                    numRead = MySerialPort.readBytes(readBuffer, readBuffer.length);
                    System.out.print("Time: "+time);
                    System.out.print(" ,Read " + numRead + " bytes -");
      
                    //Convert bytes to String
                    S = new String(readBuffer, "UTF-8"); 
                    System.out.println("Received -> "+ S);
                    time++;  
                    Thread.sleep(800);
                    if(exit){
                        exit=false;
                        break;
                    }else if(numRead==10){System.out.println(" TEST 1 ");System.out.println(S.equals(q));
                        //check the card
                        
                        exit=false;
                        break;
                    }
                }    
        }        
        catch (Exception e){
            e.printStackTrace(); 
        }finally{
            MySerialPort.closePort(); //Close the port
        }      
        pin = S;
        System.out.println("PIN : "+pin);
    }
    //Thread testing
    
    public void getAllCards() throws Exception{//NOT CORRECT
        Connect obj = new Connect();
        Connection c = obj.getConnection();  //getConnection();//Establish the connection
        
        try{ //int q=1;System.out.println(q++); <- tester
                String cardID=""; 
                String tp="";
                String status=""; 
                String issuedBy="";
                Statement stmt = c.createStatement();//Prepare statement
                ResultSet rs = stmt.executeQuery("select L.CARD_ID ,C.MOBILE_NUMBER ,L.STATUS ,CONCAT(E.F_NAME,' ',E.L_NAME) as issued_by\n" +
"from LOYALTY_CARD L LEFT JOIN EMPLOYEE E ON L.EMP_ID=E.EMP_ID LEFT JOIN CUSTOMER C ON L.CUSTOMER_ID=C.CUSTOMER_ID; "); //SQL stetment
                while(rs.next()){
                    cardID=rs.getString("CARD_ID");
                    status=rs.getString("STATUS");
                    tp=rs.getString("MOBILE_NUMBER");
                    issuedBy=rs.getString("issued_by");                    

                    String tbData[]={cardID,tp,status,issuedBy};
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
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btn_search = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        btn_remove = new javax.swing.JButton();
        btn_viewAll = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        jComboBox_search = new javax.swing.JComboBox<>();
        txt_search = new javax.swing.JTextField();
        jComboBox_filtertype = new javax.swing.JComboBox<>();
        btn_filter = new javax.swing.JButton();
        jRadioButton_search = new javax.swing.JRadioButton();
        jRadioButton_filter = new javax.swing.JRadioButton();
        btn_clear = new javax.swing.JButton();
        btn_block = new javax.swing.JButton();
        lbl_Error = new javax.swing.JLabel();
        jPanel_loyaltycard = new javax.swing.JPanel();
        btn_scanmanage = new javax.swing.JButton();
        lbl_notOk = new javax.swing.JLabel();
        lbl_waiting = new javax.swing.JLabel();
        lbl_ok = new javax.swing.JLabel();
        lbl_loyaltycardTitle = new javax.swing.JLabel();
        lbl_background = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(173, 85, 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 740, 1170, 30));

        jPanel1.setBackground(new java.awt.Color(173, 85, 2));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 30));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_search.setText("Search");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });
        jPanel3.add(btn_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, -1, -1));

        jTable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Card ID", "Customer Mobile Number", "Status", "Issued by"
            }
        ));
        jTable.setRowHeight(28);
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 930, 400));

        btn_remove.setText("Remove");
        btn_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_removeActionPerformed(evt);
            }
        });
        jPanel3.add(btn_remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 510, -1, -1));

        btn_viewAll.setText("View All");
        btn_viewAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_viewAllActionPerformed(evt);
            }
        });
        jPanel3.add(btn_viewAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 200, 100, 30));

        btn_add.setText("Add");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });
        jPanel3.add(btn_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 390, 90, 40));

        jComboBox_search.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Customer Telephone Number", "Loyalty Card ID" }));
        jComboBox_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_searchActionPerformed(evt);
            }
        });
        jPanel3.add(jComboBox_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 240, -1));

        txt_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_searchMouseClicked(evt);
            }
        });
        jPanel3.add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 160, -1));

        jComboBox_filtertype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Blocked", "Free" }));
        jComboBox_filtertype.setEnabled(false);
        jPanel3.add(jComboBox_filtertype, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 120, -1));

        btn_filter.setText("Filter");
        btn_filter.setEnabled(false);
        btn_filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_filterActionPerformed(evt);
            }
        });
        jPanel3.add(btn_filter, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 100, -1));

        buttonGroup1.add(jRadioButton_search);
        jRadioButton_search.setSelected(true);
        jRadioButton_search.setText("Search by");
        jRadioButton_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton_searchMouseClicked(evt);
            }
        });
        jPanel3.add(jRadioButton_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, -1));

        buttonGroup1.add(jRadioButton_filter);
        jRadioButton_filter.setText("Filter by");
        jRadioButton_filter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton_filterMouseClicked(evt);
            }
        });
        jPanel3.add(jRadioButton_filter, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, -1, -1));

        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });
        jPanel3.add(btn_clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 250, 100, -1));

        btn_block.setText("Block/Unblock");
        btn_block.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_blockActionPerformed(evt);
            }
        });
        jPanel3.add(btn_block, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 450, 140, 40));

        lbl_Error.setForeground(new java.awt.Color(255, 0, 0));
        lbl_Error.setText("Error msg");
        jPanel3.add(lbl_Error, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 90, 330, -1));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 1170, 600));

        jPanel_loyaltycard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_scanmanage.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_scanmanage.setText("Scan");
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

        lbl_notOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Not_final.png"))); // NOI18N
        jPanel_loyaltycard.add(lbl_notOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, -1));

        lbl_waiting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tapping image.png"))); // NOI18N
        jPanel_loyaltycard.add(lbl_waiting, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 330, 320));

        lbl_ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ok_final.png"))); // NOI18N
        jPanel_loyaltycard.add(lbl_ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 340, 330));

        add(jPanel_loyaltycard, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 750, 570));

        lbl_loyaltycardTitle.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbl_loyaltycardTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_loyaltycardTitle.setText("Loyalty Card Details");
        add(lbl_loyaltycardTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 460, -1));
        add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 770));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        lbl_Error.setVisible(false);
        lbl_loyaltycardTitle.setText("Scan Loyalty Card");
        jPanel3.setVisible(false);jPanel_loyaltycard.setVisible(true);
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_removeActionPerformed
        lbl_Error.setVisible(false);
    }//GEN-LAST:event_btn_removeActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        lbl_Error.setVisible(false);
        clearTable();
        //jComboBox_foodtype.setSelectedIndex(0);
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_viewAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_viewAllActionPerformed
        try {
            lbl_Error.setVisible(false);
            clearTable();
            getAllCards();
            jTable.setRowSelectionInterval(0, 0);
        } catch (Exception ex) {
            Logger.getLogger(LoyaltyCardPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_viewAllActionPerformed

    private void jRadioButton_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton_searchMouseClicked
        jComboBox_search.setEnabled(true);
        txt_search.setEnabled(true);
        btn_search.setEnabled(true);
        btn_filter.setEnabled(false);
        jComboBox_filtertype.setEnabled(false);
    }//GEN-LAST:event_jRadioButton_searchMouseClicked

    private void jRadioButton_filterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton_filterMouseClicked
        jComboBox_search.setEnabled(false);
        txt_search.setEnabled(false);
        btn_search.setEnabled(false);
        btn_filter.setEnabled(true);
        jComboBox_filtertype.setEnabled(true);
    }//GEN-LAST:event_jRadioButton_filterMouseClicked

    private void btn_blockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_blockActionPerformed
        lbl_Error.setVisible(false);
        if(jTable.getRowCount()==0){//validations
            JOptionPane.showMessageDialog(new JFrame(), "Please select a card",
               "Imformation", JOptionPane.ERROR_MESSAGE);
        }else{
            try {
                int row=jTable.getSelectedRow();
                String a=(String) jTable.getValueAt(row, 0);
                String status=(String) jTable.getValueAt(row, 2);
                LoyaltyCard obj=new LoyaltyCard();
                if("Blocked".equals(status))
                    obj.blockUnblockCard(a, "Active");
                else if("Active".equals(status))
                    obj.blockUnblockCard(a, "Blocked");
                clearTable();
                getAllCards();
                jTable.setRowSelectionInterval(row, row);//set selection
            } catch (Exception ex) {
                Logger.getLogger(LoyaltyCardPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_blockActionPerformed

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        if(jTable.getRowCount()!=0){//validations
            try {
                int row=jTable.getSelectedRow();
                String a=(String) jTable.getValueAt(row, 2);
                switch (a) {
                    case "Blocked":
                        btn_block.setEnabled(true);
                        btn_block.setText("Unblock");
                        break;
                    case "FREE":
                        btn_block.setEnabled(false);
                        btn_block.setText("Block");
                        break;
                    default:
                        btn_block.setEnabled(true);
                        btn_block.setText("Block");
                        break;
                }
            } catch (Exception ex) {
                Logger.getLogger(LoyaltyCardPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTableMouseClicked

    private void btn_filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_filterActionPerformed
        try {   
            searchByFilter();
        } catch (Exception ex) {
            Logger.getLogger(LoyaltyCardPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_filterActionPerformed

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed
        if("".equals(txt_search.getText())){
            lbl_Error.setText("Please enter "+jComboBox_search.getSelectedItem());
            lbl_Error.setVisible(true);
        }
        else{
            try {
                searchBy();
            } catch (Exception ex) {
                Logger.getLogger(LoyaltyCardPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_searchActionPerformed

    private void txt_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_searchMouseClicked
        lbl_Error.setVisible(false);
    }//GEN-LAST:event_txt_searchMouseClicked

    private void jComboBox_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_searchActionPerformed

    private void btn_scanmanageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_scanmanageMouseClicked
        if(btn_scanmanage.getText().equals("Scan")){
            try {
                pin="";
                //Start scannig
                while(jPanel3.isVisible()==true){}
                Conn t1=new Conn();
                t1.start();
                while(t1.isAlive()) {}
                System.out.println("THE PIN IS::"+t1.getPin());
                if("9B4D7422".equals(t1.getPin())){//correct card
                    lbl_waiting.setVisible(false);System.out.println(" TEST 2 ");
                    lbl_notOk.setVisible(false);
                    lbl_ok.setVisible(true);
                    btn_scanmanage.setText("Done");
                }else{//wrong card
                    lbl_waiting.setVisible(false);System.out.println(" TEST 3 ");
                    lbl_notOk.setVisible(true);
                    lbl_ok.setVisible(false);
                    btn_scanmanage.setText("Cancel");
                }
                //Thread.sleep(2000);
                //lbl_waiting.setVisible(true);
                //lbl_ok.setVisible(false);
                //lbl_notOk.setVisible(false);
                //jPanel_loyaltycard.setVisible(false);
                //jPanel_customerInfo.setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(LoyaltyCardPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(btn_scanmanage.getText().equals("Done")){
            //this.exit=true;
            lbl_waiting.setVisible(true);
            lbl_notOk.setVisible(false);
            lbl_ok.setVisible(false);
            jPanel_loyaltycard.setVisible(false);
            btn_scanmanage.setText("Scan");
            lbl_loyaltycardTitle.setText("Loyalty Card Details");
            jPanel3.setVisible(true);
            try {
            clearTable();
            getAllCards();
            jTable.setRowSelectionInterval(0, 0);
            } catch (Exception ex) {
                Logger.getLogger(LoyaltyCardPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(btn_scanmanage.getText().equals("Cancel")){
            this.exit=false;
            lbl_waiting.setVisible(true);
            lbl_notOk.setVisible(false);
            lbl_ok.setVisible(false);
            jPanel_loyaltycard.setVisible(false);
            btn_scanmanage.setText("Scan");
            lbl_loyaltycardTitle.setText("Loyalty Card Details");
            jPanel3.setVisible(true);
            try {
            clearTable();
            getAllCards();
            jTable.setRowSelectionInterval(0, 0);
            } catch (Exception ex) {
                Logger.getLogger(LoyaltyCardPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(btn_scanmanage.getText().equals("Scan again")){//NOT USEFUL
            lbl_notOk.setVisible(false);
            lbl_ok.setVisible(false);
            btn_scanmanage.setText("Cancel");
            lbl_loyaltycardTitle.setText("Loyalty Card Details");
            jPanel3.setVisible(true);
        }
    }//GEN-LAST:event_btn_scanmanageMouseClicked

    private void btn_scanmanageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_scanmanageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_scanmanageActionPerformed
    
    public void searchBy() throws Exception{
        clearTable();
        Connect obj = new Connect();
        Connection c = obj.getConnection();  //getConnection();//Establish the connection
        
        try{ //int q=1;System.out.println(q++); <- tester
                String cardID=""; 
                String tp="";
                String status=""; 
                String issuedBy="";
                Statement stmt = c.createStatement();//Prepare statement
                ResultSet rs;
                if(jComboBox_search.getSelectedIndex()==0)
                    rs = stmt.executeQuery("select L.CARD_ID ,C.MOBILE_NUMBER ,L.STATUS ,CONCAT(E.F_NAME,' ',E.L_NAME) as issued_by\n" +
"from LOYALTY_CARD L LEFT JOIN EMPLOYEE E ON L.EMP_ID=E.EMP_ID LEFT JOIN CUSTOMER C ON L.CUSTOMER_ID=C.CUSTOMER_ID WHERE C.MOBILE_NUMBER='"+txt_search.getText()+"'; "); //SQL stetment
                else
                    rs = stmt.executeQuery("select L.CARD_ID ,C.MOBILE_NUMBER ,L.STATUS ,CONCAT(E.F_NAME,' ',E.L_NAME) as issued_by\n" +
"from LOYALTY_CARD L LEFT JOIN EMPLOYEE E ON L.EMP_ID=E.EMP_ID LEFT JOIN CUSTOMER C ON L.CUSTOMER_ID=C.CUSTOMER_ID WHERE L.CARD_ID='"+txt_search.getText()+"'; "); //SQL stetment
                
                while(rs.next()){
                    cardID=rs.getString("CARD_ID");
                    status=rs.getString("STATUS");
                    tp=rs.getString("MOBILE_NUMBER");
                    issuedBy=rs.getString("issued_by");                    

                    String tbData[]={cardID,tp,status,issuedBy};
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
    
    public void searchByFilter() throws Exception{
        clearTable();
        Connect obj = new Connect();
        Connection c = obj.getConnection();  //getConnection();//Establish the connection
        
        try{ //int q=1;System.out.println(q++); <- tester
                String cardID=""; 
                String tp="";
                String status=""; 
                String issuedBy="";
                Statement stmt = c.createStatement();//Prepare statement
                ResultSet rs = stmt.executeQuery("select L.CARD_ID ,C.MOBILE_NUMBER ,L.STATUS ,CONCAT(E.F_NAME,' ',E.L_NAME) as issued_by\n" +
"from LOYALTY_CARD L LEFT JOIN EMPLOYEE E ON L.EMP_ID=E.EMP_ID LEFT JOIN CUSTOMER C ON L.CUSTOMER_ID=C.CUSTOMER_ID WHERE STATUS='"+jComboBox_filtertype.getSelectedItem()+"'; "); //SQL stetment
                while(rs.next()){
                    cardID=rs.getString("CARD_ID");
                    status=rs.getString("STATUS");
                    tp=rs.getString("MOBILE_NUMBER");
                    issuedBy=rs.getString("issued_by");                    

                    String tbData[]={cardID,tp,status,issuedBy};
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
    
    public void clearTable(){
        DefaultTableModel tblModel =(DefaultTableModel)jTable.getModel(); 
        int rowCount = tblModel.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            tblModel.removeRow(i);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_block;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_filter;
    private javax.swing.JButton btn_remove;
    private javax.swing.JButton btn_scanmanage;
    private javax.swing.JButton btn_search;
    private javax.swing.JButton btn_viewAll;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> jComboBox_filtertype;
    private javax.swing.JComboBox<String> jComboBox_search;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel_loyaltycard;
    private javax.swing.JRadioButton jRadioButton_filter;
    private javax.swing.JRadioButton jRadioButton_search;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JLabel lbl_Error;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_loyaltycardTitle;
    private javax.swing.JLabel lbl_notOk;
    private javax.swing.JLabel lbl_ok;
    private javax.swing.JLabel lbl_waiting;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
