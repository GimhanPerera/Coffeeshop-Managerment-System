/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Customer;

import java.awt.Color;
import DBconnection.Customer;
import DBconnection.Emp;
import java.util.logging.Level;
import java.util.logging.Logger;
import Manager.TestJFrame1;
import java.sql.SQLException;
/**
 *
 * @author Gimhan
 */
public class LoginPage extends javax.swing.JFrame {

    /**
     * Creates new form LoginPage
     */
    public LoginPage() {
        initComponents();
        lbl_error.setVisible(false);
        lbl_emperror.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel_stuff = new javax.swing.JPanel();
        btn_login = new javax.swing.JButton();
        lbl_welcomepage = new javax.swing.JLabel();
        lbl_forgotpwd = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_pwd = new javax.swing.JPasswordField();
        lbl_emperror = new javax.swing.JLabel();
        jPanel_customer = new javax.swing.JPanel();
        txt_tp = new javax.swing.JTextField();
        btn_next = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lbl_error = new javax.swing.JLabel();
        lbl_changetp = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel_customer = new javax.swing.JLabel();
        jLabel_stuff = new javax.swing.JLabel();
        lbl_background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel_stuff.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_login.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn_login.setText("Log in");
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        jPanel_stuff.add(btn_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 370, 230, 50));

        lbl_welcomepage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_welcomepage.setText("Back to welcome page");
        lbl_welcomepage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_welcomepageMouseClicked(evt);
            }
        });
        jPanel_stuff.add(lbl_welcomepage, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 620, 140, -1));

        lbl_forgotpwd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_forgotpwd.setText("Forget Passward");
        jPanel_stuff.add(lbl_forgotpwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 450, 110, -1));

        txt_email.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });
        jPanel_stuff.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 240, 310, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Password");
        jPanel_stuff.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 300, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setText("Email");
        jPanel_stuff.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, -1, -1));

        txt_pwd.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txt_pwd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_pwdMouseClicked(evt);
            }
        });
        jPanel_stuff.add(txt_pwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, 220, -1));

        lbl_emperror.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbl_emperror.setForeground(new java.awt.Color(255, 0, 0));
        lbl_emperror.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_emperror.setText("Error massage");
        jPanel_stuff.add(lbl_emperror, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 490, 520, -1));

        jTabbedPane1.addTab("tab2", jPanel_stuff);

        jPanel_customer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_tp.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        txt_tp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_tp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_tpMouseClicked(evt);
            }
        });
        txt_tp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tpActionPerformed(evt);
            }
        });
        jPanel_customer.add(txt_tp, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, 430, -1));

        btn_next.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn_next.setText("Next");
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });
        jPanel_customer.add(btn_next, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 370, 220, 70));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setText("Enter Mobile Number");
        jPanel_customer.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 220, -1, -1));

        lbl_error.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_error.setForeground(new java.awt.Color(255, 0, 0));
        lbl_error.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_error.setText("Please enter your moblie number");
        lbl_error.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_errorMouseClicked(evt);
            }
        });
        jPanel_customer.add(lbl_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 490, 710, -1));

        lbl_changetp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_changetp.setText("Change your mobile number");
        lbl_changetp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel_customer.add(lbl_changetp, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 460, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("|");
        jPanel_customer.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 680, 10, -1));

        jLabel_customer.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_customer.setText("Customer");
        jLabel_customer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_customerMouseClicked(evt);
            }
        });
        jPanel_customer.add(jLabel_customer, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 680, -1, -1));

        jLabel_stuff.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_stuff.setText("Stuff");
        jLabel_stuff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_stuffMouseClicked(evt);
            }
        });
        jPanel_customer.add(jLabel_stuff, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 680, -1, -1));

        jTabbedPane1.addTab("tab1", jPanel_customer);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -50, 1350, 710));
        getContentPane().add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 740));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_errorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_errorMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_lbl_errorMouseClicked

    private void jLabel_stuffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_stuffMouseClicked
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel_stuffMouseClicked

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        if(txt_tp.getText().length()==0)
        {
            lbl_error.setText("Please enter your moblie number");
            lbl_error.setVisible(true);
        }
        else if(txt_tp.getText().matches("[0-9]+")==false)
        {
            lbl_error.setText("Please enter a correct moblie number");
            lbl_error.setVisible(true);
        }
        else if(txt_tp.getText().length()!=10)
        {
            lbl_error.setText("Please enter a correct moblie number");
            lbl_error.setVisible(true);
        }
        else{
            try {
                Customer obj1 = new Customer();
                System.out.println("CID: "+obj1.getCID(txt_tp.getText()));
                System.out.println("tp: "+Integer.parseInt(txt_tp.getText()));
                LandingPage obj =new LandingPage(obj1.getCID(txt_tp.getText()),Integer.parseInt(txt_tp.getText()));
                obj.show();
                dispose();
            } catch (SQLException ex) {
                System.out.println("Can't connect with database");
                Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (Exception ex) {
                lbl_error.setText("Please enter a correct moblie number");
                lbl_error.setVisible(true);
                System.out.println("Can't Open Landing page");
                Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_btn_nextActionPerformed

    private void jLabel_customerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_customerMouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel_customerMouseClicked

    private void txt_tpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tpActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_tpActionPerformed

    private void txt_tpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tpMouseClicked
        // TODO add your handling code here:
        txt_tp.setText("");
        lbl_error.setVisible(false);        
    }//GEN-LAST:event_txt_tpMouseClicked

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        // TODO add your handling code here:
        if("".equals(txt_email.getText())){
            lbl_emperror.setText("Please enter your email");
            lbl_emperror.setVisible(true);
        }else if("".equals(txt_pwd.getText())){
            lbl_emperror.setText("Please enter your password");
            lbl_emperror.setVisible(true);
        }else{
            try {
                Emp obj = new Emp();
                String ch=obj.checkStuff(txt_email.getText(), txt_pwd.getText());
                if(ch.equals("1")){
                    lbl_emperror.setText("Email is incorrect!! Please check again");
                    lbl_emperror.setVisible(true);
                }
                else if(ch.equals("2")){
                    lbl_emperror.setText("Password is incorrect!! Please check again");
                    lbl_emperror.setVisible(true);
                }
                else{
                    if(ch.equals("MANAGER")){
                        System.out.println("MANAGER");
                        TestJFrame1 obj3 = new TestJFrame1();
                        obj3.show();
                        dispose();
                    }else if(ch.equals("CHEF")){
                        System.out.println("CHEF");
                    }else{
                        System.out.println(ch);
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_loginActionPerformed

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed
        lbl_emperror.setVisible(false);
    }//GEN-LAST:event_txt_emailActionPerformed

    private void txt_pwdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_pwdMouseClicked
        lbl_emperror.setVisible(false);
    }//GEN-LAST:event_txt_pwdMouseClicked

    private void lbl_welcomepageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_welcomepageMouseClicked
        WelcomePage obj=new WelcomePage();
        obj.show();
        dispose();
    }//GEN-LAST:event_lbl_welcomepageMouseClicked

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
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_login;
    private javax.swing.JButton btn_next;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_customer;
    private javax.swing.JLabel jLabel_stuff;
    private javax.swing.JPanel jPanel_customer;
    private javax.swing.JPanel jPanel_stuff;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_changetp;
    private javax.swing.JLabel lbl_emperror;
    private javax.swing.JLabel lbl_error;
    private javax.swing.JLabel lbl_forgotpwd;
    private javax.swing.JLabel lbl_welcomepage;
    private javax.swing.JTextField txt_email;
    private javax.swing.JPasswordField txt_pwd;
    private javax.swing.JTextField txt_tp;
    // End of variables declaration//GEN-END:variables
}
