/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Customer;
import DBconnection.getDate;
import DBconnection.Customer;
import DBconnection.LoyaltyCard;
import DBconnection.Order;
import java.awt.print.PrinterException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fazecast.jSerialComm.*;
import javax.swing.SwingUtilities;
/**
 *
 * @author Gimhan
 */


public class ConfirmOrderPage extends javax.swing.JFrame implements Runnable{
String cid="0";String o_type="";int points=0;String[] foodID;int[] Qyt;StringBuffer sb1;int total=0;int discount=0;
    int lines=0;int tp;

    //Thread testing - Card read. but can't link to process
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
                        btn_next.setEnabled(true);
            btn_back.setEnabled(true);
                        exit=false;
                        break;
                    }else if(numRead==10){System.out.println(" TEST 1 ");System.out.println(S.equals(q));
                        //check the card
                        if("2456322B".equals(S)){//correct card
                            lbl_waiting.setVisible(false);System.out.println(" TEST 2 ");
                            lbl_notOk.setVisible(false);
                            lbl_ok.setVisible(true);
                        }else{//wrong card
                            lbl_waiting.setVisible(false);System.out.println(" TEST 3 ");
                            lbl_notOk.setVisible(true);
                            lbl_ok.setVisible(false);
                        }
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

    public ConfirmOrderPage(StringBuffer sb, int tot,String[] foodID,int[] Qyt,int lines,String cid,String o_type,int points,int tp) {
        initComponents();
        jPanel_loyaltycard.setVisible(false);
        lbl_ok.setVisible(false);
        lbl_notOk.setVisible(false);
        this.cid=cid;
        this.o_type=o_type;
        this.points=points;
        this.sb1=sb;
        this.lines=lines;
        this.foodID=new String[lines];
        this.Qyt=new int[lines];
        this.tp=tp;
        for(int i=0;i<foodID.length;i++){
            this.foodID[i]=foodID[i];
        }
        for(int i=0;i<Qyt.length;i++){
            this.Qyt[i]=Qyt[i];
        }
        total=tot;
        txt_tp.setText(Integer.toString(tp));
        if(!"0".equals(cid)){
            try {
                Customer obj1=new Customer();
                txt_fname.setText(obj1.getFname(cid));
                txt_lname.setText(obj1.getLname(cid));
                txt_email.setText(obj1.getEmail(cid));
                txt_tp.setText(obj1.getTp(cid));
            } catch (Exception ex) {
                Logger.getLogger(ConfirmOrderPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                    
        }
        lbl_emailerror.setVisible(false);
        lbl_fnameerror.setVisible(false);
        lbl_lnameerror.setVisible(false);
        setbill(sb,tot);
    }
    
    public ConfirmOrderPage() {
        initComponents();
        lbl_ok.setVisible(false);
        lbl_notOk.setVisible(false);
        jPanel_loyaltycard.setVisible(false);
        lbl_emailerror.setVisible(false);
        lbl_fnameerror.setVisible(false);
        lbl_lnameerror.setVisible(false);
    }
    public void setbill(StringBuffer sb,int tot){
        getDate obj = new getDate();
        String date =obj.toString();
        jTextArea_bill.append("========================\n\nCOFFEE CAFE\n");
        jTextArea_bill.append(obj.dateAndTime()+"\n");
        jTextArea_bill.append("Order Type: "+o_type+"\n");
        jTextArea_bill.append("========================\n");
        jTextArea_bill.append("Items\t   Qty\tPrice\n\n");
        jTextArea_bill.append(sb.toString());
        jTextArea_bill.append("\n\n\n========================\nTotal\t\t"+tot+"\n========================\n\n\tThank You");
    }
    public void setbill(StringBuffer sb,int tot,int discount){
        getDate obj = new getDate();
        String date =obj.toString();
        jTextArea_bill.setText("");
        jTextArea_bill.append("========================\n\nCOFFEE CAFE\n");
        jTextArea_bill.append(obj.dateAndTime()+"\n");
        jTextArea_bill.append("Order Type: "+o_type+"\n");
        jTextArea_bill.append("========================\n");
        jTextArea_bill.append("Items\t   Qty\tPrice\n\n");
        jTextArea_bill.append(sb.toString());
        jTextArea_bill.append("\n\nDiscount\t\t"+discount+"\n");
        jTextArea_bill.append("========================\nTotal\t\t"+(tot-discount)+"\n========================\n\n\tThank You");
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
        btn_next = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea_bill = new javax.swing.JTextArea();
        jPanel_customerInfo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_payment = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_fname = new javax.swing.JTextField();
        txt_lname = new javax.swing.JTextField();
        txt_tp = new javax.swing.JTextField();
        txt_loyaltycard = new javax.swing.JTextField();
        btn_scan = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        rdo_cash = new javax.swing.JRadioButton();
        rdo_Card = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        lbl_lnameerror = new javax.swing.JLabel();
        lbl_emailerror = new javax.swing.JLabel();
        lbl_fnameerror = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel_loyaltycard = new javax.swing.JPanel();
        btn_scanmanage = new javax.swing.JButton();
        lbl_notOk = new javax.swing.JLabel();
        lbl_waiting = new javax.swing.JLabel();
        lbl_ok = new javax.swing.JLabel();
        lbl_scaninfo = new javax.swing.JLabel();
        lbl_background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_next.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btn_next.setText("Place Order");
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });
        getContentPane().add(btn_next, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 620, 230, 70));

        btn_back.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btn_back.setText("Cancel");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 620, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane5.setHorizontalScrollBar(null);

        jTextArea_bill.setEditable(false);
        jTextArea_bill.setColumns(20);
        jTextArea_bill.setFont(new java.awt.Font("Segoe UI Emoji", 0, 20)); // NOI18N
        jTextArea_bill.setRows(5);
        jScrollPane5.setViewportView(jTextArea_bill);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 370, 510));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 70, 390, 530));

        jPanel_customerInfo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("First Name*");
        jPanel_customerInfo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Mobile Number");
        jPanel_customerInfo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        lbl_payment.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_payment.setText("Payment method");
        jPanel_customerInfo.add(lbl_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("Last Name*");
        jPanel_customerInfo.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));

        txt_fname.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txt_fname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_fnameMouseClicked(evt);
            }
        });
        jPanel_customerInfo.add(txt_fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 200, -1));

        txt_lname.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txt_lname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_lnameMouseClicked(evt);
            }
        });
        jPanel_customerInfo.add(txt_lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 200, -1));

        txt_tp.setEditable(false);
        txt_tp.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jPanel_customerInfo.add(txt_tp, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 200, -1));

        txt_loyaltycard.setEditable(false);
        txt_loyaltycard.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jPanel_customerInfo.add(txt_loyaltycard, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 190, -1));

        btn_scan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_scan.setText("Scan");
        btn_scan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_scan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_scanMouseClicked(evt);
            }
        });
        jPanel_customerInfo.add(btn_scan, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 360, -1, -1));

        txt_email.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txt_email.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_emailMouseClicked(evt);
            }
        });
        jPanel_customerInfo.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 360, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setText("Email");
        jPanel_customerInfo.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));

        buttonGroup1.add(rdo_cash);
        rdo_cash.setSelected(true);
        rdo_cash.setText("Cash");
        jPanel_customerInfo.add(rdo_cash, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 430, 90, -1));

        buttonGroup1.add(rdo_Card);
        rdo_Card.setText("Card");
        jPanel_customerInfo.add(rdo_Card, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 430, 80, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Add Loyalty Card");
        jPanel_customerInfo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, -1, -1));

        lbl_lnameerror.setForeground(new java.awt.Color(255, 0, 0));
        lbl_lnameerror.setText("Please enter your last name");
        jPanel_customerInfo.add(lbl_lnameerror, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, -1, -1));

        lbl_emailerror.setForeground(new java.awt.Color(255, 0, 0));
        lbl_emailerror.setText("Please enter a valid email");
        jPanel_customerInfo.add(lbl_emailerror, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, -1, -1));

        lbl_fnameerror.setForeground(new java.awt.Color(255, 0, 0));
        lbl_fnameerror.setText("Please enter your first name");
        jPanel_customerInfo.add(lbl_fnameerror, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("Last Name*");
        jPanel_customerInfo.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("First Name*");
        jPanel_customerInfo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        getContentPane().add(jPanel_customerInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 730, 520));

        jPanel_loyaltycard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_scanmanage.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_scanmanage.setText("Cancel");
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

        lbl_notOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/not ok.jpg"))); // NOI18N
        jPanel_loyaltycard.add(lbl_notOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, -1, -1));

        lbl_waiting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tapping image.png"))); // NOI18N
        jPanel_loyaltycard.add(lbl_waiting, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 330, 320));

        lbl_ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ok.jpg"))); // NOI18N
        jPanel_loyaltycard.add(lbl_ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 230, 240));

        lbl_scaninfo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_scaninfo.setText("Scanning...");
        jPanel_loyaltycard.add(lbl_scaninfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        getContentPane().add(jPanel_loyaltycard, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 750, 570));
        getContentPane().add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 730));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        
        if(txt_fname.getText().length()==0){
            lbl_fnameerror.setText("Please enter your first name");
            lbl_fnameerror.setVisible(true);
        }
        else if(!txt_fname.getText().matches( "[A-Z]*[a-z]*" )){
            lbl_fnameerror.setText("Please a enter correct name");
            lbl_fnameerror.setVisible(true);
        }
        else if(txt_fname.getText().length()<=2 || txt_fname.getText().length()>=15){
            lbl_fnameerror.setText("Name length should between 2 to 15");
            lbl_fnameerror.setVisible(true);
        }
        if(txt_lname.getText().length()==0){
            lbl_fnameerror.setText("Please enter your first name");
            lbl_lnameerror.setVisible(true);
        }
        else if(!txt_lname.getText().matches( "[A-Z]*[a-z]*" )){
            lbl_lnameerror.setText("Please a enter correct name");
            lbl_lnameerror.setVisible(true);
        }
        else if(txt_lname.getText().length()<=2 || txt_lname.getText().length()>=15){
            lbl_lnameerror.setText("Name length should between 2 to 15");
            lbl_lnameerror.setVisible(true);
        }
        if(txt_email.getText().length()==0){
        }
        else if(false){//Need to complete
            lbl_emailerror.setText("Please a enter correct email");
            lbl_emailerror.setVisible(true);
        }
        else if(txt_email.getText().length()<=2 || txt_email.getText().length()>=40){
            lbl_emailerror.setText("Please a enter correct email");
            lbl_emailerror.setVisible(true);
        }
        else{
            try {
                String paymetn_method="";
                if(rdo_Card.isSelected())
                    paymetn_method="Card";
                else if(rdo_cash.isSelected())
                    paymetn_method="Cash";
                System.out.println("Validated");
                Customer obj1=new Customer();
                cid=obj1.saveCustomerDetails(cid, txt_fname.getText(), txt_lname.getText(),txt_email.getText(), txt_tp.getText(), points);
                System.out.println("Customer details saved");
                Order obj2 = new Order();
                obj2.placeOrder(o_type, cid, foodID, Qyt, paymetn_method, total, discount, "Pending");
                //foodID,qty,paymentstatus
                System.out.println("Done");
                try {
                    jTextArea_bill.print();
                } catch (PrinterException ex) {
                    Logger.getLogger(ConfirmOrderPage.class.getName()).log(Level.SEVERE, null, ex);
                }
                ThankyouPage obj =new ThankyouPage();
                obj.show();
                dispose();
            } catch (Exception ex) {
                Logger.getLogger(ConfirmOrderPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_scanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_scanMouseClicked
        try {
            pin=""; 
            jPanel_loyaltycard.setVisible(true);
            jPanel_customerInfo.setVisible(false);
            btn_next.setEnabled(false);
            btn_back.setEnabled(false);Thread.sleep(2000);
            //Start scannig
            Conn t1=new Conn();
            t1.start();
            while(t1.isAlive()) {}
            System.out.println("THE PIN IS::"+t1.getPin());
        //Thread.sleep(5000);

            
            //reading();
            //ConfirmOrderPage d=new ConfirmOrderPage();
            //Thread t1=new Thread(d);
            //t1.start();
          
            //setbill(sb1,total,200);
        } catch (Exception ex) {
            Logger.getLogger(ConfirmOrderPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_btn_scanMouseClicked

    private void txt_fnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_fnameMouseClicked
        // TODO add your handling code here:
        lbl_fnameerror.setVisible(false);
    }//GEN-LAST:event_txt_fnameMouseClicked

    private void txt_lnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_lnameMouseClicked

        lbl_lnameerror.setVisible(false);
    }//GEN-LAST:event_txt_lnameMouseClicked

    private void txt_emailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_emailMouseClicked
        // TODO add your handling code here:
        lbl_emailerror.setVisible(false);
    }//GEN-LAST:event_txt_emailMouseClicked

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        // TODO add your handling code here:
        MainPage obj =new MainPage(cid,o_type,tp);
            obj.show();
            dispose();
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_scanmanageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_scanmanageMouseClicked
        if(btn_scanmanage.getText().equals("Cancel")){
            this.exit=true;
            lbl_waiting.setVisible(true);
            lbl_notOk.setVisible(false);
            lbl_ok.setVisible(false);
            jPanel_loyaltycard.setVisible(false);
            jPanel_customerInfo.setVisible(true);
        }System.out.println(btn_scanmanage.getText());
        if(btn_scanmanage.getText().equals("Scan again")){
            lbl_notOk.setVisible(false);
            lbl_ok.setVisible(false);
            lbl_scaninfo.setVisible(true);
            btn_scanmanage.setText("Cancel");
        }
    }//GEN-LAST:event_btn_scanmanageMouseClicked

    private void btn_scanmanageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_scanmanageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_scanmanageActionPerformed

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
            java.util.logging.Logger.getLogger(ConfirmOrderPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfirmOrderPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfirmOrderPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfirmOrderPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfirmOrderPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_next;
    private javax.swing.JLabel btn_scan;
    private javax.swing.JButton btn_scanmanage;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_customerInfo;
    private javax.swing.JPanel jPanel_loyaltycard;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea_bill;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_emailerror;
    private javax.swing.JLabel lbl_fnameerror;
    private javax.swing.JLabel lbl_lnameerror;
    private javax.swing.JLabel lbl_notOk;
    private javax.swing.JLabel lbl_ok;
    private javax.swing.JLabel lbl_payment;
    private javax.swing.JLabel lbl_scaninfo;
    private javax.swing.JLabel lbl_waiting;
    private javax.swing.JRadioButton rdo_Card;
    private javax.swing.JRadioButton rdo_cash;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_fname;
    private javax.swing.JTextField txt_lname;
    private javax.swing.JTextField txt_loyaltycard;
    private javax.swing.JTextField txt_tp;
    // End of variables declaration//GEN-END:variables
    


}
