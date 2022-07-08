import java.awt.Color; 

import java.awt.Dimension;

import java.awt.GridLayout;

import java.awt.Toolkit;

import java.sql.Connection;


import java.sql.DriverManager;

import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.BorderFactory;

import javax.swing.ButtonGroup;

import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JRadioButton;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;


@SuppressWarnings("serial")

public class RegistrationFormGUI extends JFrame{   


      Connection con;
      Statement stmt;

       PreparedStatement preStatement,updatePreStmt;

      JLabel title, idLabel, nameLabel, ageLabel, subjectLabel,examinationLabel ,sessionLabel, contactLabel;

      JTextField idField, nameField, ageField, subjectField, examinationField,sessionField, contactField;

      JButton registerButton, exitButton,updateButton,deleteButton,resetButton,refresh;
              
    String exm[]={"PLEASE SELECT","UPSR","PMR","SPM"};
    JComboBox examination = new JComboBox(exm);



    String sub[]={"PLEASE SELECT","BAHASA MELAYU","BAHASA INGGERIS","SEJARAH","MATHEMATICS","SCIENCE","CHEMISTRY","ADDITIONAL MATHEMATICS","BIOLOGY","EKONAMI ASAS","PERDAGANGAN"};
    JComboBox subject = new JComboBox(sub);

    String ses[]={"PLEASE SELECT","SATURDAY 8:00AM 11:00AM","SATURDAY 8:00PM 10:00PM","SUNDAY 8:00AM - 11:00AM","SUNDAY 8:00PM - 10:00PM"};
    JComboBox session = new JComboBox(ses);

      JPanel panel;
      JTable table;

      DefaultTableModel model;


      JScrollPane scrollpane;
      public RegistrationFormGUI() {
           // TODO Auto-generated constructor stub

           super(" Brain-Tree REGISTRATION FORM");
            setSize(870, 500);
            setLayout(null);

            // Calling connect method, this will connect us to database
            connect();

            // Defining Labels 

            title = new JLabel("Brain-Tree Registration Form");

            title.setBounds(60, 7, 200, 30);

            idLabel = new JLabel("ID");

            idLabel.setBounds(30, 50, 60, 30);

            nameLabel = new JLabel("Name"); 

            nameLabel.setBounds(30, 85, 60, 30);
            
            ageLabel = new JLabel("Age"); 

            ageLabel.setBounds(30, 120, 60, 30);

            subjectLabel = new JLabel("Subject"); 

            subjectLabel.setBounds(30, 155, 60, 30); 
            
            sessionLabel = new JLabel("Session"); 

            sessionLabel.setBounds(30, 190, 60, 30); 
            
            examinationLabel = new JLabel("Examination"); 

            examinationLabel.setBounds(30, 225, 60, 30); 

            contactLabel = new JLabel("Phone"); 

            contactLabel.setBounds(30, 260, 60, 30);


            // Defining ID field
            idField = new JTextField(); 

            idField.setBounds(95, 50, 130, 30);
            


            // Defining Name field
            nameField = new JTextField(); 

            nameField.setBounds(95, 85, 130, 30);         
                  
            ageField = new JTextField(); 

            ageField.setBounds(95, 120, 130, 30);

            subject = new JComboBox(sub); 

            subject.setBounds(95, 155, 130 ,30);

            session = new JComboBox(ses);

            session.setBounds(95, 190, 130, 30); 
            
            examination = new JComboBox(exm);

            examination.setBounds(95, 225, 130, 30); 

            contactField = new JTextField(); 

            contactField.setBounds(95, 260, 130, 30);

 

            // fixing all Label,TextField,RadioButton

            add(title);

            add(idLabel);

            add(nameLabel);

            add(ageLabel);

            add(subjectLabel);
            
            add(sessionLabel);
            
            add(examinationLabel);

            add(contactLabel);

            add(idField);

            add(nameField);

            add(ageField);

            add(subject);
            
            add(session);
            
            add(examination);
            
            add(contactField);



            // Defining Exit Button

            exitButton = new JButton("Exit"); 

            exitButton.setBounds(25, 320, 80, 25);            


            // Defining Register Button

            registerButton = new JButton("Register");

            registerButton.setBounds(110, 320, 100, 25);



            // Defining Update Button

            updateButton = new JButton("Update");

            updateButton.setBounds(110, 355, 100, 25);

            updateButton.setEnabled(false);



            // Defining Delete Button

            deleteButton = new JButton("Delete");

            deleteButton.setBounds(25, 355, 80, 25);

            deleteButton.setEnabled(false);



            // Defining Reset Button

            resetButton = new JButton("Reset");

            resetButton.setBounds(60, 390, 100, 25);

            resetButton.setEnabled(true); 



            // fixing all Buttons

            add(exitButton);

            add(registerButton);

            add(updateButton);

            add(deleteButton);

            add(resetButton);    



            // Defining Panel

            panel = new JPanel();

            panel.setLayout(new GridLayout());

            panel.setBounds(250, 20, 480, 330);

            panel.setBorder(BorderFactory.createDashedBorder(Color.blue));

            add(panel);


            // Defining Refresh Button

            refresh = new JButton("Refresh Table");

            refresh.setBounds(350, 350, 270, 25);

            add(refresh);



            //Defining Model for table

            model = new DefaultTableModel();

            //Adding object of DefaultTableModel into JTable

            table = new JTable(model);
            



            //Fixing Columns move

            table.getTableHeader().setReorderingAllowed(false);



            // Defining Column Names on model

          
            model.addColumn("ID");
            model.addColumn("Name");
            model.addColumn("Age");
            model.addColumn("Subject");
            model.addColumn("Session");
            model.addColumn("Exam");
            model.addColumn("Phone");

 

            // Enable Scrolling on table

           scrollpane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,

                                           JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

            panel.add(scrollpane);



            //Displaying Frame in Center of the Screen

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            this.setLocation(dim.width/2-this.getSize().width/2,

                             dim.height/2-this.getSize().height/2);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setResizable(false);

            setVisible(true);

      }



      // Connection with Database

      public void connect(){

            try{

                  Class.forName("com.mysql.jdbc.Driver");

                  con =DriverManager.getConnection(

                                  "jdbc:mysql://localhost:3306/student","root","");

                  stmt = con.createStatement();

                  preStatement = con.prepareStatement("INSERT INTO data (`Id`,`Name`,`Age`,`Subject`,`Session`,`Examination`,`Number`) values (?,?,?,?,?,?,?)");
                  

            }catch(Exception e){

                  System.out.print(e.getMessage());

            }

      }

}