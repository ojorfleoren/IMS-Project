package inventory.management;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author OJT-21
 */
public class LogIn extends javax.swing.JFrame {
    Connection conn = null;
    /**
     * @param args the command line arguments
     */
        public static Connection connectDB(){    
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:ITEMS.db");
            System.out.println("Connected");
            
            return conn;
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Connection Failed" +e);
            return null;
        }
    }
    PreparedStatement pst = null;
    ResultSet rst = null;
    
    //
    public LogIn() {
        initComponents();
        conn = LogIn.connectDB();
        
    }
    //Clear Functionality
    public void clear(){ 
        txtUsername.setText("");
        txtPassword.setText("");
        //cbAccountType.setSelectedIndex(0);
        chkPass.setSelected(false);
    }
/*    
private void login() {
    String username = txtUsername.getText();
    String password = new String(txtPassword.getPassword());
    String accountType = (String) cbAccountType.getSelectedItem();

    if (username.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Username and Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to login?", "Confirmation", JOptionPane.YES_NO_OPTION);
    if (choice != JOptionPane.YES_OPTION) {
        return; // User chose not to login
    }
    try {
        String query = "SELECT * FROM Accounts WHERE UserName = ? AND AccountType = ?";
        pst = conn.prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, accountType);
        rst = pst.executeQuery();

        if (rst.next()) {
            String storedHashedPassword = rst.getString("PassWord");
            if (verifyPassword(password, storedHashedPassword)) {
                JOptionPane.showMessageDialog(this, "Login Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                openAppropriateFrame(accountType);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
                clear();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
            clear();
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error during login.", "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (rst != null) {
                rst.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}*/
private boolean verifyPassword(String password, String storedHashedPassword) {
    try {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(password.getBytes());

        // Convert hashed password bytes to hexadecimal representation
        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : hashBytes) {
            String hex = Integer.toHexString(0xff & hashByte);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }

        // Compare hashed password from the database with the newly hashed password
        return hexString.toString().equals(storedHashedPassword);
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
        return false;
    }
}
private void openAppropriateFrame(String accountType) {
    switch (accountType) {
        case "SuperAdmin":
            SwingUtilities.invokeLater(() -> new superadmin().setVisible(true));
            break;
        case "Admin":
            SwingUtilities.invokeLater(() -> new addmin().setVisible(true));
            break;
        case "User":
            SwingUtilities.invokeLater(() -> new User().setVisible(true));
            break;
        default:
            break;
    }
}
private void login() {
    String username = txtUsername.getText();
    String password = new String(txtPassword.getPassword());

    if (username.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Username and Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to login?", "Confirmation", JOptionPane.YES_NO_OPTION);
    if (choice != JOptionPane.YES_OPTION) {
        return; // User chose not to login
    }
    try {
        String query = "SELECT * FROM Accounts WHERE UserName = ?";
        pst = conn.prepareStatement(query);
        pst.setString(1, username);
        rst = pst.executeQuery();

        if (rst.next()) {
            String storedHashedPassword = rst.getString("PassWord");
            if (verifyPassword(password, storedHashedPassword)) {
                String accountType = rst.getString("AccountType");
                JOptionPane.showMessageDialog(this, "Login Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                openAppropriateFrame(accountType);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
                clear();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
            clear();
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error during login.", "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        try {
            if (rst != null) {
                rst.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

        jPanel1 = new javax.swing.JPanel();
        lblwelcome = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblpleaselogin = new javax.swing.JLabel();
        lblusername = new javax.swing.JLabel();
        lblpassword = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        chkPass = new javax.swing.JCheckBox();
        btnLogin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0,1));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        lblwelcome.setBackground(new java.awt.Color(255, 255, 255));
        lblwelcome.setFont(new java.awt.Font("Ink Free", 1, 46)); // NOI18N
        lblwelcome.setForeground(new java.awt.Color(247, 247, 216));
        lblwelcome.setText("Welcome Back!");

        lblpleaselogin.setBackground(new java.awt.Color(255, 255, 255));
        lblpleaselogin.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblpleaselogin.setForeground(new java.awt.Color(255, 255, 255));
        lblpleaselogin.setText("Please login to your account");

        lblusername.setBackground(new java.awt.Color(255, 255, 255));
        lblusername.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblusername.setForeground(new java.awt.Color(255, 255, 255));
        lblusername.setText("Username");

        lblpassword.setBackground(new java.awt.Color(255, 255, 255));
        lblpassword.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblpassword.setForeground(new java.awt.Color(255, 255, 255));
        lblpassword.setText("Password");

        txtUsername.setBackground(new java.awt.Color(247, 247, 217));
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });

        txtPassword.setBackground(new java.awt.Color(247, 247, 216));
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        chkPass.setBackground(new java.awt.Color(0, 0, 0,1));
        chkPass.setForeground(new java.awt.Color(255, 255, 255));
        chkPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPassActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(193, 221, 110));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Show Password");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/logos_small_67RB7KTLSNXPWKW6NAG7-56a62ad1-removebg-preview (2) (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblwelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(lblpleaselogin))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(lblusername))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(lblpassword))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(chkPass)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel5))
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(421, 421, 421)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(14, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblwelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblpleaselogin)
                        .addGap(24, 24, 24)
                        .addComponent(lblusername)
                        .addGap(6, 6, 6)
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblpassword)
                        .addGap(6, 6, 6)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(chkPass)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addComponent(btnLogin)
                .addGap(53, 53, 53))
        );

        jLabel2.setBackground(new java.awt.Color(153, 153, 153));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Purple Simple Login Website Application Desktop Prototype.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void chkPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPassActionPerformed
        if(chkPass.isSelected()){
            txtPassword.setEchoChar((char)0);
        }
        else{
            txtPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_chkPassActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        login();
       /* if (txtUsername.getText().equals("") ){
            JOptionPane.showMessageDialog(null, "Please fill out username");
        }
        else if(txtPassword.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please fill out password ");
        }
        else if(txtUsername.getText().contains("user") && txtPassword.getText().contains("user")){
            JOptionPane.showMessageDialog(null,"Login Successful");
           new User().setVisible(true);
            dispose();
        }
         else if(txtUsername.getText().contains("admin") && txtPassword.getText().contains("admin")){
            JOptionPane.showMessageDialog(null,"Login Successful");
           new admin().setVisible(true);
            dispose();
        }
          else if(txtUsername.getText().contains("Sadmin") && txtPassword.getText().contains("Sadmin")){
            JOptionPane.showMessageDialog(null,"Login Successful");
           new superadmin().setVisible(true);
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(null, "Wrong username or password!!!","Message",JOptionPane.ERROR_MESSAGE);
            txtUsername.setText("");
            txtPassword.setText("");
        }
        /*
                String username = txtUsername.getText();
                String password = txtPassword.getText();
                String userType = cbAccountType.getSelectedItem().toString();

                String accountType = databaseManager.checkLogin(username, password, userType);

                if (!accountType.isEmpty()) {
                    if (accountType.equals("User")) {
                        // Redirect to User Dashboard
                        JOptionPane.showMessageDialog(null,"Login Successful");
                        new User.setVisible(true);
                    } else if (accountType.equals("Admin")) {
                        // Redirect to Admin Dashboard
                        JOptionPane.showMessageDialog(null,"Login Successful");
                        new Admin.setVisible(true);
                    } else if (accountType.equals("Superadmin")) {
                        // Redirect to Superadmin Dashboard
                        JOptionPane.showMessageDialog(null,"Login Successful");
                        new SuperAdmin.setVisible(true);
                    }
                } else {
                    // Show error message for invalid credentials
                    JOptionPane.showMessageDialog(LogIn.this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
                }
                */
    }//GEN-LAST:event_btnLoginActionPerformed

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
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JCheckBox chkPass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblpassword;
    private javax.swing.JLabel lblpleaselogin;
    private javax.swing.JLabel lblusername;
    private javax.swing.JLabel lblwelcome;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
