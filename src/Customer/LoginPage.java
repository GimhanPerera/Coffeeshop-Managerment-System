/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Customer;

import java.awt.Color;
import DBconnection.Customer;
import DBconnection.Emp;
import Cashier.CashierMain;
import Chef.chefMain;
import java.util.logging.Level;
import java.util.logging.Logger;
import Manager.TestJFrame1;
import java.awt.Component;
import java.sql.SQLException;
import javax.swing.JOptionPane;
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

        btn_login = new javax.swing.JButton();
        lbl_welcomepage = new javax.swing.JLabel();
        lbl_forgotpwd = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_pwd = new javax.swing.JPasswordField();
        lbl_emperror = new javax.swing.JLabel();
        lbl_background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_login.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn_login.setText("Log in");
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        getContentPane().add(btn_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 370, 230, 50));

        lbl_welcomepage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_welcomepage.setText("Back to welcome page");
        lbl_welcomepage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_welcomepageMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_welcomepage, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 620, 140, -1));

        lbl_forgotpwd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_forgotpwd.setText("Forget Passward");
        lbl_forgotpwd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_forgotpwdMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_forgotpwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 450, 110, -1));

        txt_email.setFont(new java.awt.Font("Segoe UI", 0, 19)); // NOI18N
        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });
        getContentPane().add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 240, 310, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Password");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 304, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setText("Email");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, -1, -1));

        txt_pwd.setFont(new java.awt.Font("Segoe UI", 0, 19)); // NOI18N
        txt_pwd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_pwdMouseClicked(evt);
            }
        });
        getContentPane().add(txt_pwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, 220, 40));

        lbl_emperror.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbl_emperror.setForeground(new java.awt.Color(255, 0, 0));
        lbl_emperror.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_emperror.setText("Error massage");
        getContentPane().add(lbl_emperror, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 490, 1070, -1));
        getContentPane().add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 740));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        if(txt_email.getText().isEmpty()){
            lbl_emperror.setText("Please enter your email");
            lbl_emperror.setVisible(true);
        }else if(!txt_email.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")){
            lbl_emperror.setText("Invalid email format");
            lbl_emperror.setVisible(true);
        }else if(txt_email.getText().length()<=2 || txt_email.getText().length() > 50){
            lbl_emperror.setText("Invalid email length Please enter a valid email address.");
            lbl_emperror.setVisible(true);
        }else if(txt_pwd.getText().isEmpty()){
            lbl_emperror.setText("Please enter your password");
            lbl_emperror.setVisible(true);
        }else if(txt_pwd.getText().length()<6 || txt_pwd.getText().length() > 15){
                lbl_emperror.setText("Wrong password");
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
                        chefMain obj3 = new chefMain();
                        obj3.show();
                        dispose();
                    }else{//cashier
                        System.out.println(ch+" cashier");
                        CashierMain obj3 = new CashierMain();
                        obj3.show();
                        dispose();
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

    private void lbl_forgotpwdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_forgotpwdMouseClicked
        JOptionPane.showConfirmDialog((Component) null, "Please meet the manager, To change your password",
        "alert", JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_lbl_forgotpwdMouseClicked

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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_emperror;
    private javax.swing.JLabel lbl_forgotpwd;
    private javax.swing.JLabel lbl_welcomepage;
    private javax.swing.JTextField txt_email;
    private javax.swing.JPasswordField txt_pwd;
    // End of variables declaration//GEN-END:variables
}
