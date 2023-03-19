/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Customer;
import DBconnection.Customer;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Gimhan
 */
public class GetTpPage extends javax.swing.JFrame {

    /**
     * Creates new form GetTpPage
     */
    public GetTpPage() {
        initComponents();
        lbl_error.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_customer = new javax.swing.JPanel();
        txt_tp = new javax.swing.JTextField();
        btn_next = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lbl_error = new javax.swing.JLabel();
        lbl_changetp = new javax.swing.JLabel();
        lbl_welcomepage = new javax.swing.JLabel();
        lbl_background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        lbl_welcomepage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_welcomepage.setText("Back to welcome page");
        lbl_welcomepage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_welcomepage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_welcomepageMouseClicked(evt);
            }
        });
        jPanel_customer.add(lbl_welcomepage, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 140, -1));

        getContentPane().add(jPanel_customer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 740));
        getContentPane().add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 740));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_tpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_tpMouseClicked
        // TODO add your handling code here:
        txt_tp.setText("");
        lbl_error.setVisible(false);
    }//GEN-LAST:event_txt_tpMouseClicked

    private void txt_tpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tpActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_tpActionPerformed

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

    private void lbl_errorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_errorMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_lbl_errorMouseClicked

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
            java.util.logging.Logger.getLogger(GetTpPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GetTpPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GetTpPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GetTpPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GetTpPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_next;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel_customer;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_changetp;
    private javax.swing.JLabel lbl_error;
    private javax.swing.JLabel lbl_welcomepage;
    private javax.swing.JTextField txt_tp;
    // End of variables declaration//GEN-END:variables
}
