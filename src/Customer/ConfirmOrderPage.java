/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Customer;
import AduinoConnection.Conn;
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
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
/**
 *
 * @author Gimhan
 */


public class ConfirmOrderPage extends javax.swing.JFrame{
String cid="0";String o_type="";int points=0;String[] foodID;int[] Qyt;StringBuffer sb1;int total=0;int discount=0;
    int lines=0;int tp;

    public ConfirmOrderPage(StringBuffer sb, int tot,String[] foodID,int[] Qyt,int lines,String cid,String o_type,int points,int tp,int discount) {
        initComponents();
        jPanel_payment.setVisible(false);
        lbl_discount.setVisible(false);
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
        this.discount=discount;
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
        setbill(sb,tot,discount);
    }
    
    public ConfirmOrderPage() {
        initComponents();
        jPanel_payment.setVisible(false);
        lbl_discount.setVisible(false);
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
        if(discount!=0)
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
        lbl_ok = new javax.swing.JLabel();
        lbl_waiting = new javax.swing.JLabel();
        lbl_notOk = new javax.swing.JLabel();
        lbl_discount = new javax.swing.JLabel();
        jPanel_payment = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btn_back1 = new javax.swing.JLabel();
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
        getContentPane().add(btn_next, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 620, 230, 70));

        btn_back.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btn_back.setText("Cancel");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 630, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane5.setHorizontalScrollBar(null);

        jTextArea_bill.setEditable(false);
        jTextArea_bill.setColumns(20);
        jTextArea_bill.setFont(new java.awt.Font("Segoe UI Emoji", 0, 20)); // NOI18N
        jTextArea_bill.setRows(5);
        jScrollPane5.setViewportView(jTextArea_bill);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 370, 510));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 70, 390, 530));

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
        rdo_cash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_cashActionPerformed(evt);
            }
        });
        jPanel_customerInfo.add(rdo_cash, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 430, 90, -1));

        buttonGroup1.add(rdo_Card);
        rdo_Card.setText("Card");
        rdo_Card.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_CardActionPerformed(evt);
            }
        });
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

        lbl_ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ok_final.png"))); // NOI18N
        jPanel_loyaltycard.add(lbl_ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 340, 330));

        lbl_waiting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tapping image.png"))); // NOI18N
        jPanel_loyaltycard.add(lbl_waiting, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 330, 320));

        lbl_notOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Not_final.png"))); // NOI18N
        jPanel_loyaltycard.add(lbl_notOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, -1, -1));

        lbl_discount.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lbl_discount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_discount.setText("You got Rs 100/= discount");
        jPanel_loyaltycard.add(lbl_discount, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 730, -1));

        getContentPane().add(jPanel_loyaltycard, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 750, 570));

        jPanel_payment.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.setOpaque(false);
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });
        jPanel_payment.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 530, 450, 60));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/payment final 2.png"))); // NOI18N
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_payment.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 90, 480, 520));

        btn_back1.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        btn_back1.setText("Back");
        btn_back1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_back1MouseClicked(evt);
            }
        });
        jPanel_payment.add(btn_back1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 630, -1, -1));

        getContentPane().add(jPanel_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 730));
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
            if("Pay".equals(btn_next.getText())){
                jPanel_payment.setVisible(true);
                jPanel_customerInfo.setVisible(false);
                jPanel1.setVisible(false);
                jPanel_loyaltycard.setVisible(false);
                btn_back.setVisible(false);
                btn_next.setVisible(false);
            }
            else
            {
            try {
                String paymetn_method="";
                if(rdo_Card.isSelected())
                    paymetn_method="Card";
                else if(rdo_cash.isSelected())
                    paymetn_method="Cash";
                System.out.println("Validated");
                Customer obj1=new Customer();
                if(discount==0)
                    points=(int) (points+total*0.01);//WORNING: we got total as int. not double
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
                JOptionPane.showConfirmDialog((Component) null, "Database error",
        "alert", JOptionPane.PLAIN_MESSAGE);
                jPanel_loyaltycard.setVisible(false);
            }
        }
        }
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_scanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_scanMouseClicked
            if("Scan".equals(btn_scan.getText())){
                jPanel_loyaltycard.setVisible(true);
                jPanel_customerInfo.setVisible(false);
                btn_next.setEnabled(false);
                btn_back.setEnabled(false);
            }
            else{
                btn_scan.setText("Scan");
                this.discount=0;
                setbill(this.sb1,this.total);
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
        int result = JOptionPane.showConfirmDialog((Component) null, "Your discount will remove",
        "alert", JOptionPane.OK_CANCEL_OPTION);
        System.out.println(result);
        if(result==0){
            MainPage obj =new MainPage(cid,"",o_type,tp,total,foodID,Qyt);
            obj.show();
            dispose();
        }//String cid,String o_type,int tp,//int tot,String[] foodID,int[] Qyt,int lines
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_scanmanageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_scanmanageMouseClicked
        if(btn_scanmanage.getText().equals("Scan")){
            Customer obj =new Customer();
            try { 
            //Start scannig
            while(jPanel_customerInfo.isVisible()==true){}
            Conn t1=new Conn();
            t1.start();
            t1.setReadsuccess();
            while(t1.isAlive()) {}
            System.out.println("THE PIN IS::"+t1.getPin());
            if(obj.getCardID(cid).equals(t1.getPin())){//correct card
                lbl_waiting.setVisible(false);
                lbl_notOk.setVisible(false);
                lbl_ok.setVisible(true);
                btn_scanmanage.setText("Done");
                txt_loyaltycard.setText(t1.getPin());
                //calculate and set the discount
                this.discount=obj.getMaxDiscount(cid);
                if(this.discount>this.total)
                {
                    this.discount=this.total;
                }
                setbill(this.sb1,this.total,this.discount);
                lbl_discount.setVisible(true);
                if(this.discount!=0)
                    lbl_discount.setText("You got Rs "+this.discount+"/= discount");
                else
                    lbl_discount.setText("Sorry, your loyalty points are not enough to get a discount");
            }else{//wrong card
                lbl_waiting.setVisible(false);System.out.println("Carrect ID "+obj.getCardID(cid));
                lbl_notOk.setVisible(true);
                lbl_ok.setVisible(false);
                btn_scanmanage.setText("Cancel");
                txt_loyaltycard.setText("");
                lbl_discount.setVisible(true);
                lbl_discount.setText("Wrong card or not scaned your card correctly");
            }
            } catch (Exception ex) {
                Logger.getLogger(ConfirmOrderPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(btn_scanmanage.getText().equals("Done")){
            //this.exit=true;
            lbl_waiting.setVisible(true);
            lbl_notOk.setVisible(false);
            lbl_ok.setVisible(false);
            jPanel_loyaltycard.setVisible(false);
            jPanel_customerInfo.setVisible(true);
            btn_next.setEnabled(true);
            btn_back.setEnabled(true);
            btn_scanmanage.setText("Scan");
            lbl_discount.setVisible(false);
        }
        else if(btn_scanmanage.getText().equals("Cancel")){
            lbl_waiting.setVisible(true);
            lbl_notOk.setVisible(false);
            lbl_ok.setVisible(false);
            jPanel_loyaltycard.setVisible(false);
            jPanel_customerInfo.setVisible(true);
            btn_next.setEnabled(true);
            btn_back.setEnabled(true);
            btn_scanmanage.setText("Scan");
            lbl_discount.setVisible(false);
        }
        if(btn_scanmanage.getText().equals("Scan again")){//NOT USEFUL
            lbl_notOk.setVisible(false);
            lbl_ok.setVisible(false);
            btn_scanmanage.setText("Cancel");
        }
    }//GEN-LAST:event_btn_scanmanageMouseClicked
    
    private void btn_scanmanageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_scanmanageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_scanmanageActionPerformed

    private void rdo_cashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_cashActionPerformed
        if("Takeaway".equals(o_type)){
            btn_next.setText("Place Order");
        }
    }//GEN-LAST:event_rdo_cashActionPerformed

    private void rdo_CardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_CardActionPerformed
        if("Takeaway".equals(o_type)){
            btn_next.setText("Pay");
        }
    }//GEN-LAST:event_rdo_CardActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        try {
                String paymetn_method="";
                if(rdo_Card.isSelected())
                    paymetn_method="Card";
                else if(rdo_cash.isSelected())
                    paymetn_method="Cash";
                System.out.println("Validated");
                Customer obj1=new Customer();
                if(discount==0)
                    points=(int) (points+total*0.01);//WORNING: we got total as int. not double
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
                JOptionPane.showConfirmDialog((Component) null, "Database error",
        "alert", JOptionPane.PLAIN_MESSAGE);
                jPanel_loyaltycard.setVisible(false);
            }
        
    }//GEN-LAST:event_jPanel2MouseClicked

    private void btn_back1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_back1MouseClicked
        jPanel_payment.setVisible(false);
        jPanel_customerInfo.setVisible(true);
        jPanel1.setVisible(true);
        jPanel_loyaltycard.setVisible(true);
        btn_back.setVisible(true);
        btn_next.setVisible(true);
    }//GEN-LAST:event_btn_back1MouseClicked

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
    private javax.swing.JLabel btn_back1;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_customerInfo;
    private javax.swing.JPanel jPanel_loyaltycard;
    private javax.swing.JPanel jPanel_payment;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea_bill;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_discount;
    private javax.swing.JLabel lbl_emailerror;
    private javax.swing.JLabel lbl_fnameerror;
    private javax.swing.JLabel lbl_lnameerror;
    private javax.swing.JLabel lbl_notOk;
    private javax.swing.JLabel lbl_ok;
    private javax.swing.JLabel lbl_payment;
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
