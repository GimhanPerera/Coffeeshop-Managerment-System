/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Customer;

/**
 *
 * @author Gimhan
 */
public class WelcomePage extends javax.swing.JFrame {

    public WelcomePage() {
        initComponents();
        btn_next.setText("");
        btn_next.setOpaque(false);
        btn_next.setContentAreaFilled(false);
        btn_next.setBorderPainted(false);
        btn_login.setText("");
        btn_login.setOpaque(false);
        btn_login.setContentAreaFilled(false);
        btn_login.setBorderPainted(false);
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
        btn_next = new javax.swing.JButton();
        lbl_background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_login.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btn_login.setText("S");
        btn_login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_loginMouseClicked(evt);
            }
        });
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        getContentPane().add(btn_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 60, 50));

        btn_next.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        btn_next.setText("Next");
        btn_next.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_nextMouseClicked(evt);
            }
        });
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });
        getContentPane().add(btn_next, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 570, 450, 100));

        lbl_background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Welcome pg.png"))); // NOI18N
        getContentPane().add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 740));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_nextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nextMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_nextMouseClicked

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        GetTpPage obj =new GetTpPage();
        obj.show();
        dispose();
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_loginMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_loginMouseClicked

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        LoginPage obj=new LoginPage();
        obj.show();
        dispose();
    }//GEN-LAST:event_btn_loginActionPerformed

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
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WelcomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_login;
    private javax.swing.JButton btn_next;
    private javax.swing.JLabel lbl_background;
    // End of variables declaration//GEN-END:variables
}
