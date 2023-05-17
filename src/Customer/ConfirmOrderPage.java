/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Customer;
import AduinoConnection.Conn;
import Cashier.CashierMain;
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
import com.sun.glass.events.KeyEvent;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
/**
 *
 * @author Gimhan
 */


public class ConfirmOrderPage extends javax.swing.JFrame{
String cid="0";String o_type="";int points=0;String[] foodID;int[] Qyt;StringBuffer sb1;int total=0;int discount=0;
    int lines=0;int tp;
    String empmode="0";
    String tables[];
    boolean secondclick=false;

    public ConfirmOrderPage(String empmode,StringBuffer sb, int tot,String[] foodID,int[] Qyt,int lines,String cid,String o_type,int points,int tp,int discount,String tables[]) {
        initComponents();
        this.empmode=empmode;
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
        //
        this.tables=new String[tables.length];
        for(int i=0;i<tables.length;i++){
            this.tables[i]=tables[i];
        }
        for(int i=0;i<tables.length;i++){
            System.out.println("Table "+tables[i]);
        }
        //
        for(int i=0;i<foodID.length;i++){
            this.foodID[i]=foodID[i];
        }
        for(int i=0;i<Qyt.length;i++){
            this.Qyt[i]=Qyt[i];
        }
        total=tot;
        txt_tp.setText("0"+Integer.toString(tp));
        btn_scan.setVisible(false);//By default, Hide the scan btn
        if(!"0".equals(cid)){
            try {
                Customer obj1=new Customer();
                txt_fname.setText(obj1.getFname(cid));
                txt_lname.setText(obj1.getLname(cid));
                txt_email.setText(obj1.getEmail(cid));
                txt_tp.setText("0"+obj1.getTp(cid));
                //
                LoyaltyCard obj2=new LoyaltyCard();
                try {
                    int req = obj2.checkRequseted(cid);
                    if(req==1){
                        //If customer not request a card
                    }
                    else if(req==2){//customer has a card
                        btn_scan.setVisible(true);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
                }
                //
            } catch (Exception ex) {
                Logger.getLogger(ConfirmOrderPage.class.getName()).log(Level.SEVERE, null, ex);
            }         
        }
        lbl_emailerror.setVisible(false);
        lbl_fnameerror.setVisible(false);
        lbl_lnameerror.setVisible(false);
        setbill(sb,tot,discount);
        btn_back.setText("");
        btn_back.setOpaque(false);
        btn_back.setContentAreaFilled(false);
        btn_back.setBorderPainted(false);
        btn_next.setOpaque(false);
        btn_next.setContentAreaFilled(false);
        btn_next.setBorderPainted(false);
        btn_scanmanage.setOpaque(false);
        btn_scanmanage.setContentAreaFilled(false);
        btn_scanmanage.setBorderPainted(false);
        jPanel5.setOpaque(false);
    }
    
    //for testing
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
        btn_back.setText("");
        btn_back.setOpaque(false);
        btn_back.setContentAreaFilled(false);
        btn_back.setBorderPainted(false);
        btn_next.setOpaque(false);
        btn_next.setContentAreaFilled(false);
        btn_next.setBorderPainted(false);
        btn_scanmanage.setOpaque(false);
        btn_scanmanage.setContentAreaFilled(false);
        btn_scanmanage.setBorderPainted(false);
        jPanel5.setOpaque(false);
    }
    public void setbill(StringBuffer sb,int tot){
    try {
        getDate obj = new getDate();
        String date =obj.toString();
        Order obj1=new Order();
        jTextArea_bill.setText("");
        jTextArea_bill.append("========================\n                     COFFEE CAFE\n                 ");
        jTextArea_bill.append(obj.dateAndTime()+"\n");
        jTextArea_bill.append("             Order Type: "+o_type+"\n                Order ID : "+obj1.newOrderID()+"\n");
        if("Dinein".equals(o_type))
        {
            jTextArea_bill.append("Table number(s) : ");
            boolean a=false;
            int i=1;
            for(String z:tables){//For teseting
                if(a){
                    jTextArea_bill.append(",");
                }
                z=z.substring(2);
                jTextArea_bill.append(z);
                a=true;
                i++;
                if(i==6){
                    jTextArea_bill.append("\n\t");
                }
            }
            jTextArea_bill.append("\n");
        }
        jTextArea_bill.append("========================\n");
        jTextArea_bill.append("Items\t   Qty\tPrice\n\n");
        jTextArea_bill.append(sb.toString());
        jTextArea_bill.append("\n\n\n========================\nTotal\t\t"+tot+".00\n========================\n\n\tThank You");
    } catch (Exception ex) {
        Logger.getLogger(ConfirmOrderPage.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    public void setbill(StringBuffer sb,int tot,int discount){
    try {
        getDate obj = new getDate();
        Order obj1=new Order();
        String date =obj.toString();
        jTextArea_bill.setText("");
        jTextArea_bill.append("========================\n                     COFFEE CAFE\n                 ");
        jTextArea_bill.append(obj.dateAndTime()+"\n ");
        jTextArea_bill.append("             Order Type: "+o_type+"\n                Order ID : "+obj1.newOrderID()+"\n");
        if("Dinein".equals(o_type))
        {
            jTextArea_bill.append("Table number(s) : ");
            boolean a=false;
            int i=1;
            for(String z:tables){//For teseting
                if(a){
                    jTextArea_bill.append(",");
                }
                z=z.substring(2);
                jTextArea_bill.append(z);
                a=true;
                i++;
                if(i==6){
                    jTextArea_bill.append("\n\t");
                }
            }
            jTextArea_bill.append("\n");
        }
        jTextArea_bill.append("========================\n");
        jTextArea_bill.append("  Items\t   Qty\tPrice\n\n");
        jTextArea_bill.append(sb.toString());
        if(discount!=0)
            jTextArea_bill.append("\n\n\nDiscount\t\t"+discount+".00");
        jTextArea_bill.append("\n========================\nTotal\t\t"+(tot-discount)+".00\n========================\n\n\tThank You");
    } catch (Exception ex) {
        Logger.getLogger(ConfirmOrderPage.class.getName()).log(Level.SEVERE, null, ex);
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
        lbl_customerdetailsbackground = new javax.swing.JLabel();
        jPanel_loyaltycard = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btn_scanmanage = new javax.swing.JButton();
        lbl_ok = new javax.swing.JLabel();
        lbl_waiting = new javax.swing.JLabel();
        lbl_notOk = new javax.swing.JLabel();
        lbl_discount = new javax.swing.JLabel();
        lbl_backgroundLoyaltycard = new javax.swing.JLabel();
        lbl_background = new javax.swing.JLabel();
        jPanel_payment = new javax.swing.JPanel();
        txt_carddate = new javax.swing.JTextField();
        txt_cardno = new javax.swing.JTextField();
        txt_cardname = new javax.swing.JTextField();
        txt_cardcode = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btn_back1 = new javax.swing.JLabel();
        lbl_background1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_next.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btn_next.setForeground(new java.awt.Color(255, 255, 255));
        btn_next.setText("Place Order");
        btn_next.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });
        getContentPane().add(btn_next, new org.netbeans.lib.awtextra.AbsoluteConstraints(1027, 623, 230, 70));

        btn_back.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btn_back.setText("Cancel");
        btn_back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 637, 200, 60));

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
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("First Name*");
        jPanel_customerInfo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 45, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mobile Number");
        jPanel_customerInfo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        lbl_payment.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl_payment.setForeground(new java.awt.Color(255, 255, 255));
        lbl_payment.setText("Payment method");
        jPanel_customerInfo.add(lbl_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Last Name*");
        jPanel_customerInfo.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        txt_fname.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txt_fname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_fnameMouseClicked(evt);
            }
        });
        jPanel_customerInfo.add(txt_fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 200, -1));

        txt_lname.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txt_lname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_lnameMouseClicked(evt);
            }
        });
        jPanel_customerInfo.add(txt_lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 116, 200, -1));

        txt_tp.setEditable(false);
        txt_tp.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jPanel_customerInfo.add(txt_tp, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 200, -1));

        txt_loyaltycard.setEditable(false);
        txt_loyaltycard.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jPanel_customerInfo.add(txt_loyaltycard, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, 190, -1));

        btn_scan.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_scan.setForeground(new java.awt.Color(255, 255, 255));
        btn_scan.setText("Scan");
        btn_scan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_scan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_scanMouseClicked(evt);
            }
        });
        jPanel_customerInfo.add(btn_scan, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 360, -1, -1));

        txt_email.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txt_email.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_emailMouseClicked(evt);
            }
        });
        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });
        jPanel_customerInfo.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 260, 360, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Email");
        jPanel_customerInfo.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 265, -1, -1));

        buttonGroup1.add(rdo_cash);
        rdo_cash.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdo_cash.setForeground(new java.awt.Color(255, 255, 255));
        rdo_cash.setSelected(true);
        rdo_cash.setText("Cash");
        rdo_cash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_cashActionPerformed(evt);
            }
        });
        jPanel_customerInfo.add(rdo_cash, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 430, 90, -1));

        buttonGroup1.add(rdo_Card);
        rdo_Card.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        rdo_Card.setForeground(new java.awt.Color(255, 255, 255));
        rdo_Card.setText("Card");
        rdo_Card.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_CardActionPerformed(evt);
            }
        });
        jPanel_customerInfo.add(rdo_Card, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 430, 80, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Apply Loyalty Card");
        jPanel_customerInfo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 355, -1, -1));

        lbl_lnameerror.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lbl_lnameerror.setForeground(new java.awt.Color(255, 0, 0));
        lbl_lnameerror.setText("Please enter your last name");
        jPanel_customerInfo.add(lbl_lnameerror, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, -1, -1));

        lbl_emailerror.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lbl_emailerror.setForeground(new java.awt.Color(255, 0, 0));
        lbl_emailerror.setText("Please enter a valid email");
        jPanel_customerInfo.add(lbl_emailerror, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, -1, -1));

        lbl_fnameerror.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lbl_fnameerror.setForeground(new java.awt.Color(255, 0, 0));
        lbl_fnameerror.setText("Please enter your first name");
        jPanel_customerInfo.add(lbl_fnameerror, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, -1, -1));

        lbl_customerdetailsbackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Place order details panal test3.png"))); // NOI18N
        jPanel_customerInfo.add(lbl_customerdetailsbackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 520));

        getContentPane().add(jPanel_customerInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 78, 730, 520));

        jPanel_loyaltycard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });
        jPanel5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel5KeyPressed(evt);
            }
        });
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel_loyaltycard.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 460, 140, 60));

        btn_scanmanage.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_scanmanage.setForeground(new java.awt.Color(255, 255, 255));
        btn_scanmanage.setText("Scan");
        btn_scanmanage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        jPanel_loyaltycard.add(btn_scanmanage, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 460, 160, 60));

        lbl_ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ok_final updated.png"))); // NOI18N
        jPanel_loyaltycard.add(lbl_ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 340, 330));

        lbl_waiting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/tapping image updated.png"))); // NOI18N
        jPanel_loyaltycard.add(lbl_waiting, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 330, 320));

        lbl_notOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Not_final updated.png"))); // NOI18N
        jPanel_loyaltycard.add(lbl_notOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, -1, -1));

        lbl_discount.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        lbl_discount.setForeground(new java.awt.Color(255, 255, 255));
        lbl_discount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_discount.setText("You got Rs 100/= discount");
        jPanel_loyaltycard.add(lbl_discount, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 44, 720, -1));

        lbl_backgroundLoyaltycard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Place order pg loyalty card.png"))); // NOI18N
        jPanel_loyaltycard.add(lbl_backgroundLoyaltycard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 580));

        getContentPane().add(jPanel_loyaltycard, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 750, 570));

        lbl_background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/confirm page test 4.png"))); // NOI18N
        getContentPane().add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 730));

        jPanel_payment.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_carddate.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        txt_carddate.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txt_carddate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_carddateKeyPressed(evt);
            }
        });
        jPanel_payment.add(txt_carddate, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 444, 190, 40));

        txt_cardno.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        txt_cardno.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txt_cardno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cardnoKeyPressed(evt);
            }
        });
        jPanel_payment.add(txt_cardno, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 239, 430, 40));

        txt_cardname.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        txt_cardname.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txt_cardname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cardnameKeyPressed(evt);
            }
        });
        jPanel_payment.add(txt_cardname, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 341, 430, 40));

        txt_cardcode.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        txt_cardcode.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel_payment.add(txt_cardcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 443, 110, 40));

        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.setOpaque(false);
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });
        jPanel_payment.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 530, 450, 60));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/payment final 2.png"))); // NOI18N
        jLabel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel_payment.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 90, 480, 520));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel_payment.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(751, 70, 479, 60));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btn_back1.setFont(new java.awt.Font("Segoe UI", 0, 21)); // NOI18N
        btn_back1.setText("Back");
        btn_back1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_back1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_back1MouseClicked(evt);
            }
        });
        jPanel3.add(btn_back1);

        jPanel_payment.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(751, 600, 479, 60));

        lbl_background1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Payment background 1.png"))); // NOI18N
        jPanel_payment.add(lbl_background1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 730));

        getContentPane().add(jPanel_payment, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 730));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        lbl_fnameerror.setVisible(false);
        lbl_lnameerror.setVisible(false);
        lbl_emailerror.setVisible(false);
        if(txt_fname.getText().isEmpty()){//check is it empty
            lbl_fnameerror.setText("First name cannot be empty");
            lbl_fnameerror.setVisible(true);
        }
        else if(!txt_fname.getText().matches( "[A-Z]*[a-z]*" )){//check is any non-alphabetic character
            lbl_fnameerror.setText("Invalid format");
            lbl_fnameerror.setVisible(true);
        }
        else if(txt_fname.getText().length()<=2 || txt_fname.getText().length()>=15){//not allow 2>=length>=15
            lbl_fnameerror.setText("First name length should between 2 to 15");
            lbl_fnameerror.setVisible(true);
        }
        if(txt_lname.getText().isEmpty()){
            lbl_lnameerror.setText("Last name cannot be empty");
            lbl_lnameerror.setVisible(true);
        }
        else if(!txt_lname.getText().matches( "[A-Z]*[a-z]*" )){
            lbl_lnameerror.setText("Invalid format");
            lbl_lnameerror.setVisible(true);
        }
        else if(txt_lname.getText().length()<=2 || txt_lname.getText().length()>=15){
            lbl_lnameerror.setText("Last name length should between 2 to 15");
            lbl_lnameerror.setVisible(true);
        }
        if(!txt_email.getText().isEmpty()){
            if(!txt_email.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")){
                lbl_emailerror.setText("Invalid format");
                lbl_emailerror.setVisible(true);
            }
            else if(txt_email.getText().length()<=2 || txt_email.getText().length() > 50){
                lbl_emailerror.setText("Invalid length. Please enter a valid email address.");
                lbl_emailerror.setVisible(true);
            }
        }
        if(!(lbl_fnameerror.isVisible() || lbl_lnameerror.isVisible() || lbl_emailerror.isVisible())){//if validations are ok
            System.out.println("Validations are OK!!");
            if("Pay".equals(btn_next.getText())){//for card payments of takeaway customers
                jPanel_payment.setVisible(true);
                jPanel_customerInfo.setVisible(false);
                jPanel1.setVisible(false);
                jPanel_loyaltycard.setVisible(false);
                btn_back.setVisible(false);
                btn_next.setVisible(false);
                lbl_background.setVisible(false);
            }
            else
            {
            try {
                String paymetn_method="";
                if(rdo_Card.isSelected())
                    paymetn_method="CARD";
                else if(rdo_cash.isSelected())
                    paymetn_method="CASH";
                Customer obj1=new Customer();
                if(discount==0)//calculating points
                    points=(int) (points+total*0.01);
                else
                    points-=discount;
                cid=obj1.saveCustomerDetails(cid, txt_fname.getText(), txt_lname.getText(),txt_email.getText(), txt_tp.getText(), points);
                System.out.println("Customer details saved");
                Order obj2 = new Order();
                obj2.placeOrder(o_type, cid, foodID, Qyt, paymetn_method, total, discount, "Pending",tables);
                //foodID,qty,paymentstatus
                System.out.println("Order placed");
                try {
                    jTextArea_bill.print();//print the bill
                } catch (PrinterException ex) {
                    Logger.getLogger(ConfirmOrderPage.class.getName()).log(Level.SEVERE, null, ex);
                }
                if("0".equals(empmode)){//if this is customer
                    ThankyouPage obj =new ThankyouPage();
                    obj.show();
                    dispose();
                }
                else{//if this is a cashier
                    JOptionPane.showMessageDialog(new JFrame(), "Order placed",
                    "Imformation", JOptionPane.INFORMATION_MESSAGE);
                    CashierMain obj =new CashierMain(empmode);//open cashier dashboard
                    obj.show();
                    dispose();
                }
                
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
                jPanel5.setVisible(true);
                lbl_discount.setVisible(true);
                lbl_discount.setText("Press the scan button");
            }
            else if("cancel".equals(btn_scan.getText())){
                btn_scan.setText("Scan");
                txt_loyaltycard.setText("");
                this.discount=0;
                setbill(this.sb1,this.total);
            }

        
        
    }//GEN-LAST:event_btn_scanMouseClicked

    private void txt_fnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_fnameMouseClicked
        lbl_fnameerror.setVisible(false);
    }//GEN-LAST:event_txt_fnameMouseClicked

    private void txt_lnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_lnameMouseClicked
        lbl_lnameerror.setVisible(false);
    }//GEN-LAST:event_txt_lnameMouseClicked

    private void txt_emailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_emailMouseClicked
        lbl_emailerror.setVisible(false);
    }//GEN-LAST:event_txt_emailMouseClicked

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        if(txt_loyaltycard.getText().equals("")){//if not applied the loyalty card
            MainPage obj =new MainPage(empmode,"",o_type,tp,total,foodID,Qyt,tables,cid);
            obj.show();
            dispose();
        }
        else{//if applied the loyalty card
            int result = JOptionPane.showConfirmDialog((Component) null, "Your discount will cancelled",
                            "alert", JOptionPane.OK_CANCEL_OPTION);
        if(result==0){//if click ok
            MainPage obj =new MainPage(empmode,"",o_type,tp,total,foodID,Qyt,tables,cid);
            obj.show();
            dispose();
            }
        }
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_scanmanageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_scanmanageMouseClicked
        if(btn_scanmanage.getText().equals("Scan")){
            Customer obj =new Customer();
            try { 
                lbl_discount.setVisible(true);
                lbl_discount.setText("Scanning...");
                while(jPanel_customerInfo.isVisible()==true){}
                Conn t1=new Conn();
                t1.start();
                t1.setReadsuccess();
                while(t1.isAlive()) {}
                System.out.println("READING END");
                System.out.println("THE PIN IS(READ):: "+t1.getPin());
            
                if(!t1.isadu_connected()){//if arduino not connected 
                    lbl_waiting.setVisible(false);
                    System.out.println("Carrect ID "+obj.getCardID(cid));
                    lbl_notOk.setVisible(true);
                    lbl_ok.setVisible(false);
                    btn_scanmanage.setText("Cancel");
                    txt_loyaltycard.setText("");
                    lbl_discount.setVisible(true);
                    lbl_discount.setText("Scanner is not connected correctly");
                }
                else{//if arduino connected 
                    if(obj.getCardID(cid).equals(t1.getPin())){//correct card
                        System.out.println("Carrect ID "+obj.getCardID(cid));
                        lbl_waiting.setVisible(false);
                        lbl_notOk.setVisible(false);
                        lbl_ok.setVisible(true);
                        btn_scanmanage.setText("Done");
                        btn_scan.setText("cancel");
                        txt_loyaltycard.setText(t1.getPin());
                        //calculate and set the discount
                        this.discount=obj.getMaxDiscount(cid);//points count
                        if(discount>500)//if discount more than 500, Set it as 500(bcz max discount for any bill is 500/=)
                            discount=500;
                        if(this.total<1000){//if bill total is less than 1000, Max discount is half of the total
                            if(this.discount>=this.total/2)
                                discount=this.total/2;
                        }
                        //
                        setbill(this.sb1,this.total,this.discount);
                        lbl_discount.setVisible(true);
                        if(this.discount!=0)
                            lbl_discount.setText("You got Rs "+this.discount+"/= discount");
                        else
                            lbl_discount.setText("Sorry, your loyalty points are not enough to get a discount");
                    }else{//wrong card
                        lbl_waiting.setVisible(false);
                        System.out.println("Carrect ID "+obj.getCardID(cid));
                        lbl_notOk.setVisible(true);
                        lbl_ok.setVisible(false);
                        btn_scanmanage.setText("Cancel");
                        txt_loyaltycard.setText("");
                        lbl_discount.setVisible(true);
                        lbl_discount.setText("Wrong card. Please scan your loyalty card again.");
                        if(!t1.getReadsuccess()){//not read
                            lbl_discount.setText("Scan unsuccessful");
                        }
                    }
                }
            }catch (Exception ex) {
                Logger.getLogger(ConfirmOrderPage.class.getName()).log(Level.SEVERE, null, ex);
                System.out.print("ERROR 2");
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
        if(btn_scanmanage.getText().equals("Scan again")){//NOT USE
            lbl_notOk.setVisible(false);
            lbl_ok.setVisible(false);
            btn_scanmanage.setText("Cancel");
        }
    }//GEN-LAST:event_btn_scanmanageMouseClicked
    
    private class MyTimer1 implements Runnable{
        @Override
        public void run() {
            this.runTimer();
        }

        public void runTimer(){
            //int i = 10;
            //while (i>=0){
                System.out.println("LABEL CHANGING RUNNING");
            lbl_discount.setVisible(true);
            lbl_discount.setText("Scanning...");
            try {
            //        i--;
                    Thread.sleep(100L);    // 1000L = 1000ms = 1 second
                }
                catch (InterruptedException e) {
               //I don't think you need to do anything for your particular problem
                }
            //}
        }
    }
    
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
        if(txt_cardno.getText().isEmpty()){
            txt_cardno.requestFocus();
            JOptionPane.showMessageDialog(new JFrame(), "Please enter the card details",
               "Imformation", JOptionPane.ERROR_MESSAGE);
        }
        else if(txt_cardname.getText().isEmpty()){
            txt_cardname.requestFocus();
            JOptionPane.showMessageDialog(new JFrame(), "Please enter the card details",
               "Imformation", JOptionPane.ERROR_MESSAGE);
        }
        else if(txt_carddate.getText().isEmpty()){
            txt_carddate.requestFocus();
            JOptionPane.showMessageDialog(new JFrame(), "Please enter the card details",
               "Imformation", JOptionPane.ERROR_MESSAGE);
        }
        else if(txt_cardcode.getText().isEmpty()){
            txt_cardcode.requestFocus();
            JOptionPane.showMessageDialog(new JFrame(), "Please enter the card details",
               "Imformation", JOptionPane.ERROR_MESSAGE);
        }
        else if(!txt_cardno.getText().equals("1234-5678-1234-5678") || !txt_carddate.getText().equals("24/12") || !txt_cardcode.getText().equals("123")){
            JOptionPane.showMessageDialog(new JFrame(), "Wrong card details",
               "Imformation", JOptionPane.ERROR_MESSAGE);
        }
        else{
            try {
                String paymetn_method="";
                if(rdo_Card.isSelected())
                    paymetn_method="CARD";
                else if(rdo_cash.isSelected())
                    paymetn_method="CASH";
                System.out.println("Validated");
                Customer obj1=new Customer();
                if(discount==0)
                    points=(int) (points+total*0.01);
                else
                    points-=discount;
                cid=obj1.saveCustomerDetails(cid, txt_fname.getText(), txt_lname.getText(),txt_email.getText(), txt_tp.getText(), points);
                System.out.println("Customer details saved");
                Order obj2 = new Order();
                obj2.placeOrder(o_type, cid, foodID, Qyt, paymetn_method, total, discount, "Paid",tables);
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
        
    }//GEN-LAST:event_jPanel2MouseClicked

    private void btn_back1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_back1MouseClicked
        jPanel_payment.setVisible(false);
        jPanel_customerInfo.setVisible(true);
        jPanel1.setVisible(true);
        jPanel_loyaltycard.setVisible(false);
        btn_back.setVisible(true);
        btn_next.setVisible(true);
        lbl_background.setVisible(true);
    }//GEN-LAST:event_btn_back1MouseClicked

    private void txt_cardnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cardnoKeyPressed
        if((txt_cardno.getText().length()==4 ||txt_cardno.getText().length()==9 ||txt_cardno.getText().length()==14) && evt.getKeyCode() != KeyEvent.VK_BACKSPACE){
            txt_cardno.setText(txt_cardno.getText()+"-");
        }
    }//GEN-LAST:event_txt_cardnoKeyPressed

    private void txt_carddateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_carddateKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_carddateKeyPressed

    private void txt_cardnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cardnameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cardnameKeyPressed

    private void jPanel5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel5KeyPressed
        
    }//GEN-LAST:event_jPanel5KeyPressed

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        MyTimer1 obj2=new MyTimer1();//
        Thread scanningLbl = new Thread(obj2);//
        scanningLbl.start();//
        while(scanningLbl.isAlive()) {}//
        jPanel5.setVisible(false);
    }//GEN-LAST:event_jPanel5MouseClicked

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailActionPerformed

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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel_customerInfo;
    private javax.swing.JPanel jPanel_loyaltycard;
    private javax.swing.JPanel jPanel_payment;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextArea_bill;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_background1;
    private javax.swing.JLabel lbl_backgroundLoyaltycard;
    private javax.swing.JLabel lbl_customerdetailsbackground;
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
    private javax.swing.JPasswordField txt_cardcode;
    private javax.swing.JTextField txt_carddate;
    private javax.swing.JTextField txt_cardname;
    private javax.swing.JTextField txt_cardno;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_fname;
    private javax.swing.JTextField txt_lname;
    private javax.swing.JTextField txt_loyaltycard;
    private javax.swing.JTextField txt_tp;
    // End of variables declaration//GEN-END:variables
    


}
