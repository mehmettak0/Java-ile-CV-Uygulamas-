/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cvapp;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import static java.lang.Compiler.command;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author hasan
 */
public class NewRecordScreenFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewRecordScreenFrame
     */
    public NewRecordScreenFrame() {
        initComponents();
         setTitle("New Register Screen");
    }

    //I made the part below to make a background with transition between 2 colors.
    class JPanelGradient extends JPanel {

        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            Color color_1 = new Color(232, 232, 232);
            Color color_2 = new Color(130, 130, 130);

            GradientPaint gp = new GradientPaint(0, 0, color_1, 180, height, color_2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);

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

        jPanel1 = new JPanelGradient();
        lbl_userIon = new javax.swing.JLabel();
        lbl_EmailIcon = new javax.swing.JLabel();
        lbl_PasswordIcon = new javax.swing.JLabel();
        lbl_UserName = new javax.swing.JLabel();
        lbl_Email = new javax.swing.JLabel();
        lbl_password = new javax.swing.JLabel();
        lbl_Repassword = new javax.swing.JLabel();
        txtField_UserName = new javax.swing.JTextField();
        txtField_Email = new javax.swing.JTextField();
        txtField_Password = new javax.swing.JPasswordField();
        txtField_RePassword = new javax.swing.JPasswordField();
        btn_cancel = new javax.swing.JButton();
        btn_SignUp = new javax.swing.JButton();
        chckBox_ShowPassword = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        lbl_userIon.setIcon(new javax.swing.ImageIcon("C:\\Users\\hasan\\Desktop\\FSMV??\\CV UYGULAMASI(F??NAL)\\profile-user.png")); // NOI18N

        lbl_EmailIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\hasan\\Desktop\\FSMV??\\CV UYGULAMASI(F??NAL)\\email.png")); // NOI18N

        lbl_PasswordIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\hasan\\Desktop\\FSMV??\\CV UYGULAMASI(F??NAL)\\padlock.png")); // NOI18N

        lbl_UserName.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lbl_UserName.setText("Username");

        lbl_Email.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lbl_Email.setText("Email");

        lbl_password.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lbl_password.setText("Password");

        lbl_Repassword.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lbl_Repassword.setText("Repassword");

        txtField_UserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtField_UserNameActionPerformed(evt);
            }
        });

        txtField_Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtField_EmailActionPerformed(evt);
            }
        });

        txtField_Password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtField_PasswordActionPerformed(evt);
            }
        });

        btn_cancel.setText("Cancel");
        btn_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelActionPerformed(evt);
            }
        });

        btn_SignUp.setText("Sign Up");
        btn_SignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SignUpActionPerformed(evt);
            }
        });

        chckBox_ShowPassword.setText("Show password");
        chckBox_ShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chckBox_ShowPasswordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(btn_SignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(chckBox_ShowPassword)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbl_userIon)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbl_UserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lbl_PasswordIcon)
                                            .addComponent(lbl_EmailIcon))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lbl_password, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbl_Repassword, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                            .addComponent(lbl_Email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtField_UserName, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                    .addComponent(txtField_Email)
                                    .addComponent(txtField_Password)
                                    .addComponent(txtField_RePassword))))))
                .addGap(60, 60, 60))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lbl_UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_userIon))
                    .addComponent(txtField_UserName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtField_Email)
                    .addComponent(lbl_Email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_EmailIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(lbl_password, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(txtField_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_Repassword, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(txtField_RePassword)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lbl_PasswordIcon)))
                .addGap(18, 18, 18)
                .addComponent(chckBox_ShowPassword)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cancel)
                    .addComponent(btn_SignUp))
                .addGap(40, 40, 40))
        );

        getContentPane().add(jPanel1);

        setSize(new java.awt.Dimension(499, 446));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void chckBox_ShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chckBox_ShowPasswordActionPerformed
        // TODO add your handling code here:

//        to make the password visible when the user wants to see the password
        if (chckBox_ShowPassword.isSelected()) {

            txtField_Password.setEchoChar((char) 0);
            txtField_RePassword.setEchoChar((char) 0);

        } else {
            txtField_Password.setEchoChar('*');
            txtField_RePassword.setEchoChar('*');
        }
    }//GEN-LAST:event_chckBox_ShowPasswordActionPerformed

    private void btn_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelActionPerformed
        // When cancel is clicked, to close this frame and return to the main login screen.
        LoginFrame LoginFrame = new LoginFrame();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null);
        LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_btn_cancelActionPerformed

    private void btn_SignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SignUpActionPerformed

        Matcher matcher;

        //Username checks and I checked the desired regex for register screen.
        //this field cannot be empty...
        if (txtField_UserName.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Please fill in the Username area.", "Error", JOptionPane.ERROR_MESSAGE);
            return;

        } else {

            matcher = Pattern.compile("^[a-zA-z]{5,25}$").matcher(txtField_UserName.getText());
            if (!matcher.find()) {
                JOptionPane.showMessageDialog(null, "Username must be leat 5 maximum 25 character and no number \n Your username must be like mehmetak or mehmetAK", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                //Email checks and I checked the desired regex for register screen.
                //this field cannot be empty...
                if (txtField_Email.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Please write your Email adress...", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    //In the mail part, it should start with a letter and I want the user to enter a mail address in the format of mehmetak01@gmail.com.
                    matcher = Pattern.compile("^([\\w][\\w\\d]+)@(gmail|hotmail)(.com)$").matcher(txtField_Email.getText());
                    if (!matcher.find()) {
                        JOptionPane.showMessageDialog(null, "Your Email address must be like mehmetak@hotmail.com or mehmetak01@gmail.com", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        //password and repassword checks and I checked the desired regex for register screen.
                        //this field cannot be empty...
                        if (txtField_Password.getText().isEmpty()) {

                            JOptionPane.showMessageDialog(null, "Please fill in the password area.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;

                            //password must be least one big character, least one number and password's lenght can be least 3..
                        } else {
                            matcher = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{3,}$").matcher(txtField_Password.getText());
                            if (!matcher.find()) {

                                JOptionPane.showMessageDialog(null, "Password must have least one number,\n and password's lenght not less than 3.\nYour password must like mehmetAk01 ", "Error", JOptionPane.ERROR_MESSAGE);
                                return;

                            } else {
                                //this field cannot be empty...
                                if (txtField_RePassword.getText().isEmpty()) {

                                    JOptionPane.showMessageDialog(null, "Please fill in the repassword area.", "Error", JOptionPane.ERROR_MESSAGE);
                                    return;

                                    //repassword must be least one big character, least one number and repassword's lenght can be least 3..
                                } else {
                                    matcher = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{3,}$").matcher(txtField_RePassword.getText());
                                    if (!matcher.find()) {

                                        JOptionPane.showMessageDialog(null, "RePassword must have least one number,\n and repassword's lenght not less than 3.\nYour repassword must like mehmetAk01 ", "Error", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    } else {
                                        String password = txtField_Password.getText();
                                        String repassword = txtField_RePassword.getText();
                                        int value = password.compareTo(repassword);
                                        //password and repassword must be the same...
                                        //if value = 0 the passwords are the same
                                        if (value == 0) {
                                            // everything is correct and save database, give information...
                                            Connection conn = null;
                                            Statement stmt_select = null;
                                            try {
                                                conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CvAppDB", "sa", "as");
                                                //The email must be unique so I checked whether the entered e-mail address is in the system or not.
                                                String query_email = "select count(*) as adet from user_info where user_email='" + txtField_Email.getText() + "' ";

                                                stmt_select = conn.createStatement();

                                                ResultSet rs_check_Email = stmt_select.executeQuery(query_email);
                                                int email_count = 0;
                                                while (rs_check_Email.next()) {
                                                    email_count = rs_check_Email.getInt("adet");
                                                }
                                                if (email_count == 0) {
                                                    ResultSet rs = stmt_select.executeQuery("select max(user_id)+1 as user_id from user_info");
                                                    int user_id_value = 0;
                                                    while (rs.next()) {
                                                        user_id_value = rs.getInt("user_id");
                                                    }
                                                    //System.out.println("user id value :" + user_id_value);
                                                    //I did the process of placing the information entered by the user into the database table.
                                                    PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_info (USER_ID, USER_NAME, USER_EMAIL,USER_PASSWORD,USER_SENDCODE,USER_STATUS) VALUES (?, ?, ?, ?, ?, ?)");

                                                    stmt.setInt(1, user_id_value);
                                                    stmt.setString(2, txtField_UserName.getText());
                                                    stmt.setString(3, txtField_Email.getText());
                                                    stmt.setString(4, txtField_Password.getText());
                                                    stmt.setString(5, "000000");
                                                    stmt.setString(6, "0");
                                                    stmt.executeUpdate();
                                                    JOptionPane.showMessageDialog(null, "Registration has been completed successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
                                                    LoginFrame LoginFrame = new LoginFrame();
                                                    LoginFrame.setVisible(true);
                                                    LoginFrame.pack();
                                                    LoginFrame.setLocationRelativeTo(null);
                                                    LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                                    this.dispose();
                                                    return;

                                                } else {
                                                    JOptionPane.showMessageDialog(null, "The e-mail entered is registered in the system", "Error", JOptionPane.ERROR_MESSAGE);
                                                    return;
                                                }

                                            } catch (Exception ex) {
                                                String hata = ex.getMessage();
                                            }

                                        } else {
                                            JOptionPane.showMessageDialog(null, "Please check your password and repassword.\n They must be same. ", "Error", JOptionPane.ERROR_MESSAGE);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }


    }//GEN-LAST:event_btn_SignUpActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // I want to ask to users when the winwod closing...
        int SelectedOption = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to close the app?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
         
        if (SelectedOption == JOptionPane.YES_OPTION) {

            this.dispose();

        }
        if (SelectedOption == JOptionPane.NO_OPTION) {
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }

    }//GEN-LAST:event_formWindowClosing

    private void txtField_EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtField_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtField_EmailActionPerformed

    private void txtField_PasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtField_PasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtField_PasswordActionPerformed

    private void txtField_UserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtField_UserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtField_UserNameActionPerformed

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
            java.util.logging.Logger.getLogger(NewRecordScreenFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewRecordScreenFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewRecordScreenFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewRecordScreenFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewRecordScreenFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_SignUp;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JCheckBox chckBox_ShowPassword;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_Email;
    private javax.swing.JLabel lbl_EmailIcon;
    private javax.swing.JLabel lbl_PasswordIcon;
    private javax.swing.JLabel lbl_Repassword;
    private javax.swing.JLabel lbl_UserName;
    private javax.swing.JLabel lbl_password;
    private javax.swing.JLabel lbl_userIon;
    private javax.swing.JTextField txtField_Email;
    private javax.swing.JPasswordField txtField_Password;
    private javax.swing.JPasswordField txtField_RePassword;
    private javax.swing.JTextField txtField_UserName;
    // End of variables declaration//GEN-END:variables
}
