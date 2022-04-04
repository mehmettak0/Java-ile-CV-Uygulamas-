/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cvapp;

import java.awt.Color;
import java.nio.file.*;
import javax.activation.*;
import javax.mail.*;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hasan
 */
public class CvCreationScreen extends javax.swing.JFrame {

    /**
     * Creates new form CvCreationScreen
     */
    String userEmail = "";
    DefaultTableModel tableModel;// I created DefaultTableModel to connect User database.

    public CvCreationScreen(String incomingEmail) {
        initComponents();
        setTitle("Cv Creation Screen");
        updateComboboxExperiences();
        updateComboboxLanguages();
        updateComboboxHobbies();

        userEmail = incomingEmail;
        txtField_Email.setText(userEmail);

        //I pulled the data and projected it into the table.
        tableModel = GetUser();
        table_UserRecords.setModel(tableModel);

    }
//I made this here to pull information from database to table
    public DefaultTableModel GetUser() {
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{"Name", "Surname", "Email", "Phone number", "City", "Gender", "Birthday", "Experiences", "Languages", "Hobbies", "Age"});
        tableModel.setRowCount(0);//we reset the number of rows so that it does not add on top of the previous information
        Connection conn = null;
        try {
            // connect to the database
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CvAppDB", "sa", "as");
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM CV_INFO WHERE EMAIL = '"+userEmail+"'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                //taking data from the database
                String username = rs.getString("FIRST_NAME");
                String surname = rs.getString("SURNAME");
                String email = rs.getString("EMAIL");
                String phone_number = rs.getString("PHONE_NUMBER");
                String city = rs.getString("CITY_TOWN");
                String gender = rs.getString("GENDER");
                String birhday = rs.getString("DATE_OF_BIRTH");
                String experiences = rs.getString("EXPERIENCES");
                String languages = rs.getString("USER_LANGUAGES");
                String hobbies = rs.getString("USER_HOBBIES");
                int age = rs.getInt("USER_AGE");
                tableModel.addRow(new Object[]{username, surname, email, phone_number, city, gender, birthday, experiences, languages, hobbies, age});
            }

            conn.close();
        } catch (Exception e) {
            String ex = e.getMessage();
        }
        return tableModel;
    }

    //I made the part below to make a background with transition between 2 colors.
    class JPanelGradient extends JPanel {
    //I made the part below to make a background with transition between 2 colors.
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            Color color_1 = new Color(255, 178, 102);
            Color color_2 = new Color(255, 128, 0);

            GradientPaint gp = new GradientPaint(0, 0, color_1, 180, height, color_2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);

        }

    }

    //update combobox values from database...
    private void updateComboboxExperiences() {
        Connection conn = null;
        Statement stmt_select = null;
        String sql = "select * from EXPERIENCES";
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CvAppDB", "sa", "as");
            stmt_select = conn.createStatement();

            ResultSet rs_ex = stmt_select.executeQuery(sql);
            while (rs_ex.next()) {
                comboBox_experiences.addItem(rs_ex.getString(1));
            }

        } catch (Exception e) {
        }
    }

    private void updateComboboxLanguages() {
        Connection conn = null;
        Statement stmt_select = null;
        String sql = "select * from LANGUAGES";
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CvAppDB", "sa", "as");
            stmt_select = conn.createStatement();

            ResultSet rs_ex = stmt_select.executeQuery(sql);
            while (rs_ex.next()) {
                comboBox_languages.addItem(rs_ex.getString(1));
            }

        } catch (Exception e) {
        }
    }

    private void updateComboboxHobbies() {
        Connection conn = null;
        Statement stmt_select = null;
        String sql = "select * from INTEREST_AND_HOBBIES";
        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CvAppDB", "sa", "as");
            stmt_select = conn.createStatement();

            ResultSet rs_ex = stmt_select.executeQuery(sql);
            while (rs_ex.next()) {
                comboBoc_Hobbies.addItem(rs_ex.getString(1));
            }

        } catch (Exception e) {
        }
    }

    //I made these methods to get the information held in jLists as a single string value.
    public String user_languages() {
        String str = "";
        if (List_Languages != null) {

            for (int i = 0; i < List_Languages.getModel().getSize(); i++) {

                str += List_Languages.getModel().getElementAt(i) + ", ";

            }

        }

        return str;

    }

    public String user_hobbies() {
        String str = "";
        if (List_InterestAndHobbies != null) {
            for (int i = 0; i < List_InterestAndHobbies.getModel().getSize(); i++) {

                str += List_InterestAndHobbies.getModel().getElementAt(i) + ", ";

            }

        }

        return str;
    }

    public String user_experiences() {
        String str = "";
        if (List_Experiences != null) {
            for (int i = 0; i < List_Experiences.getModel().getSize(); i++) {

                str += List_Experiences.getModel().getElementAt(i) + ", ";

            }

        }

        return str;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroup_Gender = new javax.swing.ButtonGroup();
        tabbedPane_CreationCv = new javax.swing.JTabbedPane();
        pnl_PersonalInformation = new JPanelGradient();
        lbl_firstname = new javax.swing.JLabel();
        lbl_Surname = new javax.swing.JLabel();
        lbl_Email = new javax.swing.JLabel();
        txtField_Email = new javax.swing.JTextField();
        txtField_firstName = new javax.swing.JTextField();
        txtField_Surname = new javax.swing.JTextField();
        lbl_phoneNumber = new javax.swing.JLabel();
        txtField_phoneNumber = new javax.swing.JTextField();
        lbl_city = new javax.swing.JLabel();
        cmboBox_Cities = new javax.swing.JComboBox<>();
        lbl_DateOfBirth = new javax.swing.JLabel();
        cmbBox_Day = new javax.swing.JComboBox<>();
        cmbBox_Months = new javax.swing.JComboBox<>();
        lbl_Gender = new javax.swing.JLabel();
        rbtn_male = new javax.swing.JRadioButton();
        rbtn_female = new javax.swing.JRadioButton();
        cmbBox_Year = new javax.swing.JComboBox<>();
        btn_NextStepExperiences = new javax.swing.JButton();
        jlabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbl_age = new javax.swing.JLabel();
        lbl_ageIcon = new javax.swing.JLabel();
        slider_age = new javax.swing.JSlider();
        spinner_age = new javax.swing.JSpinner();
        btn_BacktoTheLoginFrame = new javax.swing.JButton();
        jPanel2 = new JPanelGradient();
        lbl_experiencesIcon = new javax.swing.JLabel();
        btn_add = new javax.swing.JButton();
        comboBox_experiences = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        List_Experiences = new javax.swing.JList<>();
        btn_delete = new javax.swing.JButton();
        lbl_languagesIcon = new javax.swing.JLabel();
        comboBox_languages = new javax.swing.JComboBox<>();
        btn_addLanguages = new javax.swing.JButton();
        btn_deleteLanguages = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        List_Languages = new javax.swing.JList<>();
        btn_NextStepHobbies = new javax.swing.JButton();
        btn_BackStepPersonal = new javax.swing.JButton();
        jPanel3 = new JPanelGradient();
        lbl_HobbiesIcon = new javax.swing.JLabel();
        lbl_FinishIcon = new javax.swing.JLabel();
        btn_FinishCv = new javax.swing.JButton();
        lbl_IconHobbies = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        List_InterestAndHobbies = new javax.swing.JList<>();
        comboBoc_Hobbies = new javax.swing.JComboBox<>();
        btn_addHobbies = new javax.swing.JButton();
        btn_deleteHobbies = new javax.swing.JButton();
        jPanel1 = new JPanelGradient();
        jScrollPane4 = new javax.swing.JScrollPane();
        table_UserRecords = new javax.swing.JTable();
        btn_deleteUserRecords = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        lbl_firstname.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_firstname.setText("First name");

        lbl_Surname.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_Surname.setText("Surname");

        lbl_Email.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_Email.setText("Email");

        txtField_Email.setEditable(false);

        lbl_phoneNumber.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_phoneNumber.setText("Phone number");

        lbl_city.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_city.setText("City/Town");

        cmboBox_Cities.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmboBox_Cities.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Adana", "Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin", "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta", "İçel (Mersin)", "İstanbul", "İzmir", "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "K.maraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak", "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak", "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce" }));

        lbl_DateOfBirth.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_DateOfBirth.setText("Date of birth");

        cmbBox_Day.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbBox_Day.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));

        cmbBox_Months.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbBox_Months.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January\t", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));

        lbl_Gender.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_Gender.setText("Gender");

        btnGroup_Gender.add(rbtn_male);
        rbtn_male.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rbtn_male.setText("Male");

        btnGroup_Gender.add(rbtn_female);
        rbtn_female.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rbtn_female.setText("Female");

        cmbBox_Year.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbBox_Year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1980" }));

        btn_NextStepExperiences.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_NextStepExperiences.setText("Next step >");
        btn_NextStepExperiences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NextStepExperiencesActionPerformed(evt);
            }
        });

        jlabel7.setIcon(new javax.swing.ImageIcon("C:\\Users\\hasan\\Desktop\\FSMVÜ\\CV UYGULAMASI(FİNAL)\\name.png")); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\hasan\\Desktop\\FSMVÜ\\CV UYGULAMASI(FİNAL)\\aaaaemail.png")); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\hasan\\Desktop\\FSMVÜ\\CV UYGULAMASI(FİNAL)\\buildings.png")); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\hasan\\Desktop\\FSMVÜ\\CV UYGULAMASI(FİNAL)\\birthday.png")); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\hasan\\Desktop\\FSMVÜ\\CV UYGULAMASI(FİNAL)\\name.png")); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\hasan\\Desktop\\FSMVÜ\\CV UYGULAMASI(FİNAL)\\telephone.png")); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\hasan\\Desktop\\FSMVÜ\\CV UYGULAMASI(FİNAL)\\lavatory.png")); // NOI18N

        lbl_age.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbl_age.setText("Age");

        lbl_ageIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\hasan\\Desktop\\FSMVÜ\\CV UYGULAMASI(FİNAL)\\adult.png")); // NOI18N

        slider_age.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        slider_age.setMajorTickSpacing(5);
        slider_age.setMaximum(50);
        slider_age.setMinimum(18);
        slider_age.setValue(34);
        slider_age.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                slider_ageStateChanged(evt);
            }
        });

        spinner_age.setModel(new javax.swing.SpinnerNumberModel(24, 18, 50, 1));
        spinner_age.setEnabled(false);

        btn_BacktoTheLoginFrame.setText("<<< Return to login screen");
        btn_BacktoTheLoginFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BacktoTheLoginFrameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl_PersonalInformationLayout = new javax.swing.GroupLayout(pnl_PersonalInformation);
        pnl_PersonalInformation.setLayout(pnl_PersonalInformationLayout);
        pnl_PersonalInformationLayout.setHorizontalGroup(
            pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_PersonalInformationLayout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_PersonalInformationLayout.createSequentialGroup()
                        .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_ageIcon, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_PersonalInformationLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rbtn_male)
                                .addGap(47, 47, 47)
                                .addComponent(rbtn_female)
                                .addGap(29, 29, 29))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_PersonalInformationLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtField_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pnl_PersonalInformationLayout.createSequentialGroup()
                                            .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lbl_DateOfBirth)
                                                .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(pnl_PersonalInformationLayout.createSequentialGroup()
                                                        .addComponent(lbl_Email)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtField_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_PersonalInformationLayout.createSequentialGroup()
                                                        .addComponent(lbl_city)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(cmboBox_Cities, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addGap(7, 7, 7)))
                                    .addGroup(pnl_PersonalInformationLayout.createSequentialGroup()
                                        .addComponent(lbl_age)
                                        .addGap(47, 47, 47)
                                        .addComponent(slider_age, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(52, 52, 52)
                                        .addComponent(spinner_age, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                                .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnl_PersonalInformationLayout.createSequentialGroup()
                                        .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6))
                                        .addGap(35, 35, 35)
                                        .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl_Surname)
                                            .addComponent(lbl_phoneNumber)
                                            .addComponent(lbl_Gender))
                                        .addGap(30, 30, 30)
                                        .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtField_Surname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtField_phoneNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnl_PersonalInformationLayout.createSequentialGroup()
                                        .addComponent(btn_BacktoTheLoginFrame)
                                        .addGap(30, 30, 30)
                                        .addComponent(btn_NextStepExperiences, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(pnl_PersonalInformationLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_PersonalInformationLayout.createSequentialGroup()
                                .addGap(159, 159, 159)
                                .addComponent(cmbBox_Day, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbBox_Months, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(cmbBox_Year, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl_PersonalInformationLayout.createSequentialGroup()
                                .addComponent(lbl_firstname)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(50, 50, 50))
        );
        pnl_PersonalInformationLayout.setVerticalGroup(
            pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_PersonalInformationLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtField_Surname, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Surname)
                    .addComponent(txtField_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_firstname)
                    .addComponent(jlabel7)
                    .addComponent(jLabel4))
                .addGap(50, 50, 50)
                .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtField_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_Email)
                        .addComponent(jLabel1))
                    .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_phoneNumber)
                        .addComponent(txtField_phoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_PersonalInformationLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(pnl_PersonalInformationLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rbtn_male)
                                .addComponent(rbtn_female)
                                .addComponent(lbl_Gender))
                            .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmboBox_Cities, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_city)))))
                .addGap(50, 50, 50)
                .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbBox_Year, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbBox_Months, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbBox_Day, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_DateOfBirth))
                    .addComponent(jLabel3))
                .addGap(41, 41, 41)
                .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnl_PersonalInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_NextStepExperiences, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_BacktoTheLoginFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lbl_age)
                        .addComponent(slider_age, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spinner_age, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_PersonalInformationLayout.createSequentialGroup()
                        .addComponent(lbl_ageIcon)
                        .addGap(19, 19, 19)))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        tabbedPane_CreationCv.addTab("Personal", pnl_PersonalInformation);

        lbl_experiencesIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\hasan\\Desktop\\FSMVÜ\\CV UYGULAMASI(FİNAL)\\increase12.png")); // NOI18N

        btn_add.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_add.setText("ADD");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        List_Experiences.setBorder(javax.swing.BorderFactory.createTitledBorder("Programming Languages"));
        List_Experiences.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(List_Experiences);

        btn_delete.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_delete.setText("DELETE");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        lbl_languagesIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\hasan\\Desktop\\FSMVÜ\\CV UYGULAMASI(FİNAL)\\translation.png")); // NOI18N

        btn_addLanguages.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_addLanguages.setText("ADD");
        btn_addLanguages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addLanguagesActionPerformed(evt);
            }
        });

        btn_deleteLanguages.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_deleteLanguages.setText("DELETE");
        btn_deleteLanguages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteLanguagesActionPerformed(evt);
            }
        });

        List_Languages.setBorder(javax.swing.BorderFactory.createTitledBorder("Languages"));
        List_Languages.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jScrollPane2.setViewportView(List_Languages);

        btn_NextStepHobbies.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_NextStepHobbies.setText("Next Step >");
        btn_NextStepHobbies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NextStepHobbiesActionPerformed(evt);
            }
        });

        btn_BackStepPersonal.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_BackStepPersonal.setText("< Previous Step");
        btn_BackStepPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BackStepPersonalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_BackStepPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btn_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_delete, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                .addComponent(comboBox_experiences, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(lbl_experiencesIcon)))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(lbl_languagesIcon))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_deleteLanguages, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_addLanguages, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBox_languages, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_NextStepHobbies, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 29, Short.MAX_VALUE)
                                .addComponent(lbl_languagesIcon)
                                .addGap(27, 27, 27)
                                .addComponent(comboBox_languages, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(btn_addLanguages, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(btn_deleteLanguages, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lbl_experiencesIcon)
                        .addGap(27, 27, 27)
                        .addComponent(comboBox_experiences, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_NextStepHobbies, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_BackStepPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        tabbedPane_CreationCv.addTab("Experiences", jPanel2);

        lbl_HobbiesIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\hasan\\Desktop\\FSMVÜ\\CV UYGULAMASI(FİNAL)\\mental-health.png")); // NOI18N

        lbl_FinishIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\hasan\\Desktop\\FSMVÜ\\CV UYGULAMASI(FİNAL)\\finish.png")); // NOI18N

        btn_FinishCv.setText("FINISH CV");
        btn_FinishCv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FinishCvActionPerformed(evt);
            }
        });

        lbl_IconHobbies.setIcon(new javax.swing.ImageIcon("C:\\Users\\hasan\\Desktop\\FSMVÜ\\CV UYGULAMASI(FİNAL)\\lifestyle.png")); // NOI18N

        List_InterestAndHobbies.setBorder(javax.swing.BorderFactory.createTitledBorder("Interest And Hobbies"));
        List_InterestAndHobbies.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jScrollPane3.setViewportView(List_InterestAndHobbies);

        comboBoc_Hobbies.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btn_addHobbies.setText("ADD");
        btn_addHobbies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addHobbiesActionPerformed(evt);
            }
        });

        btn_deleteHobbies.setText("DELETE");
        btn_deleteHobbies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteHobbiesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbl_HobbiesIcon)
                        .addGap(60, 60, 60)
                        .addComponent(lbl_IconHobbies))
                    .addComponent(btn_addHobbies, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboBoc_Hobbies, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_deleteHobbies, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(56, 56, 56)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_FinishIcon)
                    .addComponent(btn_FinishCv, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(lbl_FinishIcon)
                        .addGap(69, 69, 69)
                        .addComponent(btn_FinishCv, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_IconHobbies)
                            .addComponent(lbl_HobbiesIcon))
                        .addGap(50, 50, 50)
                        .addComponent(comboBoc_Hobbies, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(btn_addHobbies, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_deleteHobbies, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tabbedPane_CreationCv.addTab("Interest and Hobbies ", jPanel3);

        table_UserRecords.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "First Name", "Surname", "Email", "Phone number", "City", "Gender", "Birthday", "Experiences", "Languages", "Hobbies", "Age"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(table_UserRecords);

        btn_deleteUserRecords.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_deleteUserRecords.setText("Delete All Records");
        btn_deleteUserRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteUserRecordsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(334, 334, 334)
                .addComponent(btn_deleteUserRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(371, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(btn_deleteUserRecords)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        tabbedPane_CreationCv.addTab("Created Cv", jPanel1);

        getContentPane().add(tabbedPane_CreationCv);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    String birthday;
    String city;
    int age;
    char gender;

    private void btn_NextStepExperiencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NextStepExperiencesActionPerformed
        // TODO add your handling code here:

        Matcher matcher;

        //I checked that the fields are filled in as desired
        //this field cannot be empty...
        if (txtField_firstName.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Please fill in the First name area.", "Error", JOptionPane.ERROR_MESSAGE);
            return;

        } else {
            matcher = Pattern.compile("^[a-zA-z]{3,15}$").matcher(txtField_firstName.getText());
            if (!matcher.find()) {
                JOptionPane.showMessageDialog(null, "First name must be leat 3 maximum 15 character and no number \n Your first name must be like mehmet or Mehmet", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {

                if (txtField_Surname.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Please fill in the  surname area.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;

                } else {
                    matcher = Pattern.compile("^[a-zA-z]{2,20}$").matcher(txtField_firstName.getText());
                    if (!matcher.find()) {
                        JOptionPane.showMessageDialog(null, "Surname must be leat 2 maximum 20 character and no number \n Your Surname must be like ak or Ak", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        if (txtField_phoneNumber.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please fill in the  phone number area.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        } else {
                            matcher = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$").matcher(txtField_phoneNumber.getText());
                            if (!matcher.find()) {
                                JOptionPane.showMessageDialog(null, "you must first enter the country code and then 10 digits \n Your phone number must be like \n+123 (202) 555-0125\n+12 (202) 555-0125\n+1 (202) 555-0125", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            } else {
                                birthday = "" + cmbBox_Day.getSelectedItem() + "/" + cmbBox_Months.getSelectedItem() + "/" + cmbBox_Year.getSelectedItem();
                                city = "" + cmboBox_Cities.getSelectedItem();
                                age = slider_age.getValue();

                                if (rbtn_female.isSelected() || rbtn_male.isSelected()) {
                                    if (rbtn_female.isSelected()) {
                                        gender = 'F';
                                    } else {
                                        gender = 'M';
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(null, "please select a gender", "Error", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }

                                //to switch to the next tabbedpane screen
                                tabbedPane_CreationCv.setSelectedIndex(1);
                            }
                        }
                    }
                }
            }
        }


    }//GEN-LAST:event_btn_NextStepExperiencesActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        // I want to ask to users when the winwod closing...
        int SelectedOption = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to close the app?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (SelectedOption == JOptionPane.YES_OPTION) {
            this.dispose();
        }
        if (SelectedOption == JOptionPane.NO_OPTION) {
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        //When the screen opened, user email is showing automaticly.

    }//GEN-LAST:event_formWindowOpened

    DefaultListModel listModel = new DefaultListModel();
    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        // TODO add your handling code here

        for (int i = 0; i < List_Experiences.getModel().getSize(); i++) {

            if (List_Experiences.getModel().getElementAt(i) == comboBox_experiences.getSelectedItem()) {

                JOptionPane.showMessageDialog(null, "The experience you want to add is available in the list", "Error", JOptionPane.ERROR_MESSAGE);
                listModel.remove(i);
                return;
            }

        }
        listModel.addElement(comboBox_experiences.getSelectedItem());
        List_Experiences.setModel(listModel);
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        listModel.removeElementAt(List_Experiences.getSelectedIndex());
    }//GEN-LAST:event_btn_deleteActionPerformed

    DefaultListModel listModelLanguages = new DefaultListModel();
    private void btn_addLanguagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addLanguagesActionPerformed

        for (int i = 0; i < List_Languages.getModel().getSize(); i++) {

            if (List_Languages.getModel().getElementAt(i) == comboBox_languages.getSelectedItem()) {

                JOptionPane.showMessageDialog(null, "The languages you want to add is available in the list", "Error", JOptionPane.ERROR_MESSAGE);
                listModelLanguages.remove(i);
                return;
            }

        }
        listModelLanguages.addElement(comboBox_languages.getSelectedItem());
        List_Languages.setModel(listModelLanguages);

    }//GEN-LAST:event_btn_addLanguagesActionPerformed

    private void btn_deleteLanguagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteLanguagesActionPerformed

        listModelLanguages.removeElementAt(List_Languages.getSelectedIndex());

    }//GEN-LAST:event_btn_deleteLanguagesActionPerformed

    private void btn_NextStepHobbiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NextStepHobbiesActionPerformed
        // TODO add your handling code here:
        //to switch to the next tabbedpane screen
        tabbedPane_CreationCv.setSelectedIndex(2);
    }//GEN-LAST:event_btn_NextStepHobbiesActionPerformed

    private void btn_BackStepPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BackStepPersonalActionPerformed
        // TODO add your handling code here:
        //to switch to the next tabbedpane screen
        tabbedPane_CreationCv.setSelectedIndex(0);
    }//GEN-LAST:event_btn_BackStepPersonalActionPerformed

    private void slider_ageStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_slider_ageStateChanged
        // TODO add your handling code here:
        spinner_age.setValue(slider_age.getValue());
    }//GEN-LAST:event_slider_ageStateChanged

    DefaultListModel listModelHobbies = new DefaultListModel();
    private void btn_addHobbiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addHobbiesActionPerformed
        // TODO add your handling code here:
        for (int i = 0; i < List_InterestAndHobbies.getModel().getSize(); i++) {

            if (List_InterestAndHobbies.getModel().getElementAt(i) == comboBoc_Hobbies.getSelectedItem()) {

                JOptionPane.showMessageDialog(null, "The hobbies you want to add is available in the list", "Error", JOptionPane.ERROR_MESSAGE);
                listModelHobbies.remove(i);
                return;
            }

        }
        listModelHobbies.addElement(comboBoc_Hobbies.getSelectedItem());
        List_InterestAndHobbies.setModel(listModelHobbies);
    }//GEN-LAST:event_btn_addHobbiesActionPerformed

    private void btn_deleteHobbiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteHobbiesActionPerformed
        // TODO add your handling code here:
        listModelHobbies.removeElementAt(List_InterestAndHobbies.getSelectedIndex());
    }//GEN-LAST:event_btn_deleteHobbiesActionPerformed

    private void btn_BacktoTheLoginFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BacktoTheLoginFrameActionPerformed
        // TODO add your handling code here:
        //// When Return to login screen is clicked, to close this frame and return to the main login screen.
        LoginFrame LoginFrame = new LoginFrame();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null);
        LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_btn_BacktoTheLoginFrameActionPerformed


    private void btn_FinishCvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FinishCvActionPerformed
        // TODO add your handling code here:

        if (txtField_firstName.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Please fill in the First name area.", "Error", JOptionPane.ERROR_MESSAGE);
            return;

        } else {

            if (txtField_Surname.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Please fill in the  surname area.", "Error", JOptionPane.ERROR_MESSAGE);
                return;

            } else {

                if (txtField_phoneNumber.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in the  phone number area.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {

                    // everything is correct and save database, give information...
                    Connection conn = null;

                    try {

                        String usr_lang = user_languages();
                        String usr_hob = user_hobbies();
                        String usr_exp = user_experiences();

                        //I did the insert operations.
                        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CvAppDB", "sa", "as");
                        PreparedStatement stmt = conn.prepareStatement("INSERT INTO CV_INFO (FIRST_NAME, SURNAME, EMAIL,PHONE_NUMBER,CITY_TOWN,GENDER,DATE_OF_BIRTH,EXPERIENCES,USER_LANGUAGES,USER_HOBBIES,USER_AGE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                        stmt.setString(1, txtField_firstName.getText());
                        stmt.setString(2, txtField_Surname.getText());
                        stmt.setString(3, txtField_Email.getText());
                        stmt.setString(4, txtField_phoneNumber.getText());
                        stmt.setString(5, cmboBox_Cities.getSelectedItem().toString());
                        stmt.setString(6, String.valueOf(gender));//internetteb buldum hatalı olabilir.
                        stmt.setString(7, birthday);
                        stmt.setString(8, usr_exp);
                        stmt.setString(9, usr_lang);
                        stmt.setString(10, usr_hob);
                        stmt.setInt(11, age);
                        stmt.executeUpdate();

                        //............
                        try {
                            Path path = Paths.get("D:\\CV_APP\\CV_APP.txt");//creates Path instance  
                            Path p = Files.createFile(path);     //creates file at specified location  
                            File f = new File("D:\\CV_APP\\CV_APP.txt");
                            if (f.exists()) {
                                FileWriter fw = new FileWriter("D:\\CV_APP\\CV_APP.txt");
                                fw.write("                                           PERSONAL INFORMATION" + " \n");
                                fw.write("name  : " + txtField_firstName.getText() + " \n");
                                fw.write("surname  : " + txtField_Surname.getText() + " \n");
                                fw.write("email  : " + txtField_Email.getText() + " \n");
                                fw.write("phone number  : " + txtField_phoneNumber.getText() + " \n");
                                fw.write("city/town  : " + cmboBox_Cities.getSelectedItem().toString() + " \n");
                                fw.write("gender  : " + String.valueOf(gender) + " \n");
                                fw.write("birthday  : " + birthday + " \n");
                                fw.write("age  : " + age + " \n");
                                fw.write("                                           EXPERIENCES INFORMATION" + " \n");
                                fw.write(usr_exp + " \n");
                                fw.write("                                           INTEREST AND HOBBIES" + " \n");
                                fw.write(usr_hob + " \n");
                                fw.write("                                           LANGUAGES" + " \n");
                                fw.write(usr_lang + " \n");

                                fw.close();

                                // Recipient's email ID needs to be mentioned.
                                String to = userEmail;

                                // Sender's email ID needs to be mentioned
                                String from = "cv.application.code@gmail.com";

                                // Assuming you are sending email from through gmails smtp
                                String host = "smtp.gmail.com";

                                // Get system properties
                                Properties properties = System.getProperties();

                                // Setup mail server
                                properties.put("mail.smtp.host", host);
                                properties.put("mail.smtp.port", "465");
                                properties.put("mail.smtp.ssl.enable", "true");
                                properties.put("mail.smtp.auth", "true");

                                // Get the Session object.// and pass username and password
                                Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

                                    protected PasswordAuthentication getPasswordAuthentication() {

                                        return new PasswordAuthentication(from, "29558016578Ma.");

                                    }

                                });

                                // Create a default MimeMessage object.
                                MimeMessage message = new MimeMessage(session);

                                // Set From: header field of the header.
                                message.setFrom(new InternetAddress(from));

                                // Set To: header field of the header.
                                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                                // Set Subject: header field
                                message.setSubject("GENERATED CV");

                                // Now set the actual message
                                MimeBodyPart messageBodyPart = new MimeBodyPart();

                                Multipart multipart = new MimeMultipart();

                                String file = "D:\\CV_APP\\CV_APP.txt";
                                String fileName = "CV_APP.txt";
                                DataSource source = new FileDataSource(file);
                                messageBodyPart.setDataHandler(new DataHandler(source));
                                messageBodyPart.setFileName(fileName);
                                multipart.addBodyPart(messageBodyPart);

                                message.setContent(multipart);
//                        System.out.println("sending...");
                                // Send message
                                Transport.send(message);
                                tableModel = GetUser();
                                table_UserRecords.setModel(tableModel);
//                        System.out.println("Sent message successfully....");
                                JOptionPane.showMessageDialog(null, "The generated cv has been successfully sent to your e-mail address.", "Information", JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                        } catch (Exception ex) {
                            String hata = ex.getMessage();
                        } finally {
                            File f = new File("D:\\CV_APP\\CV_APP.txt");
                            if (f.exists()) {
                                f.delete();
                            }
                        }

                    } catch (Exception ex) {

                    }

                }

            }

        }


    }//GEN-LAST:event_btn_FinishCvActionPerformed

    private void btn_deleteUserRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteUserRecordsActionPerformed
        // TODO add your handling code here:

        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CvAppDB", "sa", "as");

            PreparedStatement stmt = conn.prepareStatement("DELETE from cv_info WHERE EMAIL = ? ");
            stmt.setString(1, userEmail);
            stmt.executeUpdate();
            tableModel = GetUser();
            table_UserRecords.setModel(tableModel);
            JOptionPane.showMessageDialog(null, userEmail + " all information about the e-mail has been successfully deleted from the system.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

    }//GEN-LAST:event_btn_deleteUserRecordsActionPerformed

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
            java.util.logging.Logger.getLogger(CvCreationScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CvCreationScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CvCreationScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CvCreationScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CvCreationScreen("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> List_Experiences;
    private javax.swing.JList<String> List_InterestAndHobbies;
    private javax.swing.JList<String> List_Languages;
    private javax.swing.ButtonGroup btnGroup_Gender;
    private javax.swing.JButton btn_BackStepPersonal;
    private javax.swing.JButton btn_BacktoTheLoginFrame;
    private javax.swing.JButton btn_FinishCv;
    private javax.swing.JButton btn_NextStepExperiences;
    private javax.swing.JButton btn_NextStepHobbies;
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_addHobbies;
    private javax.swing.JButton btn_addLanguages;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_deleteHobbies;
    private javax.swing.JButton btn_deleteLanguages;
    private javax.swing.JButton btn_deleteUserRecords;
    private javax.swing.JComboBox<String> cmbBox_Day;
    private javax.swing.JComboBox<String> cmbBox_Months;
    private javax.swing.JComboBox<String> cmbBox_Year;
    private javax.swing.JComboBox<String> cmboBox_Cities;
    private javax.swing.JComboBox<String> comboBoc_Hobbies;
    private javax.swing.JComboBox<String> comboBox_experiences;
    private javax.swing.JComboBox<String> comboBox_languages;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel jlabel7;
    private javax.swing.JLabel lbl_DateOfBirth;
    private javax.swing.JLabel lbl_Email;
    private javax.swing.JLabel lbl_FinishIcon;
    private javax.swing.JLabel lbl_Gender;
    private javax.swing.JLabel lbl_HobbiesIcon;
    private javax.swing.JLabel lbl_IconHobbies;
    private javax.swing.JLabel lbl_Surname;
    private javax.swing.JLabel lbl_age;
    private javax.swing.JLabel lbl_ageIcon;
    private javax.swing.JLabel lbl_city;
    private javax.swing.JLabel lbl_experiencesIcon;
    private javax.swing.JLabel lbl_firstname;
    private javax.swing.JLabel lbl_languagesIcon;
    private javax.swing.JLabel lbl_phoneNumber;
    private javax.swing.JPanel pnl_PersonalInformation;
    private javax.swing.JRadioButton rbtn_female;
    private javax.swing.JRadioButton rbtn_male;
    private javax.swing.JSlider slider_age;
    private javax.swing.JSpinner spinner_age;
    private javax.swing.JTabbedPane tabbedPane_CreationCv;
    private javax.swing.JTable table_UserRecords;
    private javax.swing.JTextField txtField_Email;
    private javax.swing.JTextField txtField_Surname;
    private javax.swing.JTextField txtField_firstName;
    private javax.swing.JTextField txtField_phoneNumber;
    // End of variables declaration//GEN-END:variables
}
