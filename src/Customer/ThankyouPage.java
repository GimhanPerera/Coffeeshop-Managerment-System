/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Customer;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author Gimhan
 */
public class ThankyouPage extends javax.swing.JFrame {
    
    //TIMEER
    private class MyTimer implements Runnable{
        @Override
        public void run() {
            this.runTimer();
        }

        public void runTimer(){
            int i = 10;
            while (i>=0){
            System.out.println("Remaining: "+i+" seconds");
            lbl_time.setText(Integer.toString(i));
            try {
                    i--;
                    Thread.sleep(1000L);    // 1000L = 1000ms = 1 second
                }
                catch (InterruptedException e) {
               //I don't think you need to do anything for your particular problem
                }
            }
            WelcomePage obj =new WelcomePage();
            obj.show();
            dispose();
        }
    }
    private Thread thread;
    //TIMEER

    public ThankyouPage() {
        
        initComponents();
        //btn disappered
        btn_main.setOpaque(false);
        btn_main.setContentAreaFilled(false);
        btn_main.setBorderPainted(false);
        
        MyTimer obj=new MyTimer();
        //Thread thread = new Thread(obj);
        this.thread = new Thread(obj);
        thread.start();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_main = new javax.swing.JButton();
        lbl_time = new javax.swing.JLabel();
        lbl_background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_main.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btn_main.setText("      ");
        btn_main.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_main.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mainActionPerformed(evt);
            }
        });
        getContentPane().add(btn_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 560, 310, -1));

        lbl_time.setFont(new java.awt.Font("Segoe UI Historic", 1, 48)); // NOI18N
        lbl_time.setForeground(new java.awt.Color(255, 255, 255));
        lbl_time.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_time.setText("10");
        getContentPane().add(lbl_time, new org.netbeans.lib.awtextra.AbsoluteConstraints(758, 560, 100, -1));

        lbl_background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Thank you pg3.png"))); // NOI18N
        getContentPane().add(lbl_background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 730));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_mainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mainActionPerformed
       thread.stop();
       WelcomePage obj =new WelcomePage();
       obj.show();
       dispose();
    }//GEN-LAST:event_btn_mainActionPerformed

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
            java.util.logging.Logger.getLogger(ThankyouPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThankyouPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThankyouPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThankyouPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThankyouPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_main;
    private javax.swing.JLabel lbl_background;
    private javax.swing.JLabel lbl_time;
    // End of variables declaration//GEN-END:variables
}
