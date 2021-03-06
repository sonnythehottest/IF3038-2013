/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ConnectionChecker;
import model.ConnectionHandler;
import model.SharedToDoListClient;
import static model.SharedToDoListClient.message;

/**
 *
 * @author FUJITSU
 */
public class LoginFrame extends javax.swing.JFrame {

     Socket socket;
     public String uName;
     public String uPass;
    
     private final String LOGIN_SUCCESS = "login_success";
     private static final String LOGIN_FAIL = "login_fail";
     
     private boolean isFirst;
     
    /**
     * Creates new form LoginFrame
     */
    public LoginFrame(Socket s) {
        initComponents();
        
        isFirst = true;
        this.setTitle("SharedToDoList | Login Frame");
//        inisialisasi atribut
        socket = s;
        uName = "";uPass = "";
        statusMsg.setText("Selamat Datang, silahkan login :");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        uNameField = new javax.swing.JTextField();
        logIn = new javax.swing.JButton();
        pswField = new javax.swing.JPasswordField();
        statusMsg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("SharedToDoList");

        logIn.setText("Log In");
        logIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInActionPerformed(evt);
            }
        });

        statusMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusMsg.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(logIn)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pswField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(uNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(91, 91, 91))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(statusMsg)
                        .addGap(27, 27, 27))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(statusMsg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(uNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pswField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logIn)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(112, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInActionPerformed
        try {                                      
             // TODO add your handling code here:
             //susun string yang mau dikirim
             String kode = "login";
             uName = uNameField.getText();
             SharedToDoListClient.mainF.setClientID(uName);
     //        String uNameEnc = uName + "x";
             String uNameEnc = getEncryptedText(uName);
             uPass = new String(pswField.getPassword());
//             String uPassEnc = uPass + "x";
             String uPassEnc = getEncryptedText(uPass);
             
             String msg = kode + "_" + uNameEnc + "_" + uPassEnc;
             //kirim string
             try {
                  ConnectionHandler.sendString(msg);
                  System.out.println("mulai listen");
                  
                  String result = "";
//                  jika percobaan pertama dan belum berhasil login
                  if (!SharedToDoListClient.isLoggedIn) {
                     result = ConnectionHandler.listen();
//                     isFirst = false;
                  }
                  System.out.println("result : " + result);
                  System.out.println("selesai listen");
                 try {
//                     mengecek validitas login
                     Thread.sleep(1000);
                 } catch (InterruptedException ex) {
                     Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
                 }
                  System.out.println("message login : " + SharedToDoListClient.message);
                  if (result.equals(LOGIN_SUCCESS) || SharedToDoListClient.message.equals(LOGIN_SUCCESS)) {
//                      mengganti variabel isLoggedIn
                      SharedToDoListClient.isLoggedIn = true;
                      
//                      menampilkan halaman utama
                      SharedToDoListClient.mainF.setVisible(true);
                      
                      //membuat thread khusus untuk selalu mengecek kondisi koneksi
                      Thread t = new Thread(new ConnectionChecker(socket, SharedToDoListClient.mainF));
                      t.start();
                      System.out.println("checker jalan");
                      this.setVisible(false);
                  } else if (result.equals(LOGIN_FAIL) || message.equals(LOGIN_FAIL)) {
                      System.out.println("masuk LOGIN SALAH");
                      JOptionPane.showMessageDialog(null, "Password Anda SALAH");
                  }
              } catch (IOException ex) {
     //             Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
                  System.out.println("Exception! koneksi terputus");
                  ConnectionHandler.isConnected = false;
              }
         } catch (UnsupportedEncodingException ex) {
             Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_logInActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new LoginFrame(soc).setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton logIn;
    private javax.swing.JPasswordField pswField;
    public javax.swing.JLabel statusMsg;
    private javax.swing.JTextField uNameField;
    // End of variables declaration//GEN-END:variables

    private String getEncryptedText(String uName) throws UnsupportedEncodingException {
//        ubah string ke dalam bentuk byte array
        byte[] byteMsg = uName.getBytes("UTF8");
        
//        modifikasi byte array
        for (int i=0; i<byteMsg.length; i++) {
            byteMsg[i] = (byte) (byteMsg[i] + i);
        }
        
        return new String(byteMsg);
    }
}
