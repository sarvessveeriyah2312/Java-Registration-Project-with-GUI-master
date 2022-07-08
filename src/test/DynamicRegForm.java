
import javax.swing.*;
import javax.swing.table.DefaultTableModel; 

import java.awt.event.*;
import java.sql.DriverManager;

import java.sql.ResultSet;



@SuppressWarnings("serial")

public class DynamicRegForm extends RegistrationFormGUI{

      String string = "";

      ResultSet rst,rstLast;

      Object[][] data;

      

      String SHOW = "Show";

      RegistrationFormGUI formGUIObject;



      // Defining Constructor

      DynamicRegForm(){

            nameField.addKeyListener(new KeyAdapter() {

                  public void keyTyped(KeyEvent e) {

                  if(nameField.getText().length()>=15)

                        e.consume();

                  }

           

            });

            subject.addKeyListener(new KeyAdapter() {

                  public void keyPressed(KeyEvent e) {

                    subject.setSelectedIndex(0);

                       

                  }

            });
            
            session.addKeyListener(new KeyAdapter() {

                  public void keyPressed(KeyEvent e) {

                    session.setSelectedIndex(0);

                       

                  }

            });
            
            examination.addKeyListener(new KeyAdapter() {

                  @Override
                  public void keyPressed(KeyEvent e) {

                    examination.setSelectedItem(0);

                       

                  }

            });

            contactField.addKeyListener(new KeyAdapter() {

                  public void keyTyped(KeyEvent e) {

                        char c = e.getKeyChar();

                        if (!((c >= '0') && (c <= '9') ||

                             (c == KeyEvent.VK_BACK_SPACE) ||

                             (c == KeyEvent.VK_DELETE))) {

                              // getToolkit().beep();

                              e.consume();

                        }

                        if(contactField.getText().length()>9) 

                              e.consume();

                  }

            });

            exitButton.addActionListener(new ActionListener(){

                  public void actionPerformed(ActionEvent arg0) {

                        try{

                              con.close();

                              System.exit(0);

                        }catch(Exception ex){

                               System.out.println(ex.getMessage());

                        }

                  }

            });

            registerButton.addActionListener(new AbstractAction(SHOW){

                   public void actionPerformed(ActionEvent ae){

                         try{

                            if (ae.getSource() == registerButton) {

                                if (nameField.getText().equals(""))

                                   JOptionPane.showMessageDialog(idField, 

                                               "Please provide Name_Field");

                                 else if(session.getSelectedItem().equals(""))

                                   JOptionPane.showMessageDialog(idField, 

                                               "Please provide Session_Field");
                                 
                                  else if(examination.getSelectedItem().equals(""))
                                   JOptionPane.showMessageDialog(idField, "Please select Examination");
               
                                  else if(subject.getSelectedItem().equals(""))
                                   JOptionPane.showMessageDialog(idField, "Please select Subject");
                          
                                else if(contactField.getText().equals(""))
                                   JOptionPane.showMessageDialog(idField, "Please provide Contact_Field");

                                else if(ageField.getText().equals(""))

                                   JOptionPane.showMessageDialog(idField, "Please provide Age");

                                else {

                                 //Fetching column values from Database
                                 preStatement.setString(1,idField.getText()); 
                                 
                                 preStatement.setString(2,nameField.getText());

                                 preStatement.setString(3,ageField.getText());
                                 
                                 preStatement.setString(4, (String) subject.getSelectedItem());

                                 preStatement.setString(5, (String) session.getSelectedItem());

                                 preStatement.setString(6, (String) examination.getSelectedItem());

                                 preStatement.setString(7,contactField.getText());

                               //Executing MySQL Update Query

                                 int i = preStatement.executeUpdate();

                                 if(i==1){

                                  JOptionPane.showMessageDialog(panel, 

                                               "Successfully Registered");

                                 }

 

                                 // showing last row 

                                rstLast = stmt.executeQuery("SELECT * FROM data");

                                rstLast.last();

                                String string = String.valueOf(rstLast.getString(1))+","+rstLast.getString(2)+","+rstLast.getString(3)+","+rstLast.getString(4)+","+rstLast.getString(5)+","+rstLast.getString(6)+","+rstLast.getString(7);

                                 Object[] row = null;

                                 row = string.split(",");

                                 model.addRow(row);

                                 panel.revalidate();

 

                         

                               }

                              }

                       }catch(Exception ex){

                              System.out.println(ex.getMessage()); 

                       }

                   }

            });

 

            updateButton.addActionListener(new AbstractAction(SHOW){

              public void actionPerformed(ActionEvent ae){

                  if (nameField.getText().equals(""))

                        JOptionPane.showMessageDialog(idField,

                                    "Please provide Name_Field");

                  else if(subject.getSelectedItem().equals(""))

                        JOptionPane.showMessageDialog(idField,

                                    "Please provide Address_Field");

                  else if(contactField.getText().equals(""))

                        JOptionPane.showMessageDialog(idField,

                                    "Please provide Contact_Field");              

                  else if(ageField.getText().equals(""))

                        JOptionPane.showMessageDialog(idField,

                                    "Please select Age");                  

                  else {

                              

                              try{
                              int r = table.getSelectedRow();
                              
                              if(r>=0){

                                 
                                    String id = (String)table.getModel().getValueAt(r,0);
                                   
                            
                                    
                                  updatePreStmt = con.prepareStatement("UPDATE `data` SET Name=?,Age=?,Subject=?,Session=?,Examination=?,Number=? WHERE Id=?");
        
                                  updatePreStmt.setString(7,idField.getText());
                                  
                                  updatePreStmt.setString(1,nameField.getText());

                                  updatePreStmt.setString(2,ageField.getText());

                                  updatePreStmt.setString(3, (String) subject.getSelectedItem());

                                  updatePreStmt.setString(4, (String) session.getSelectedItem());
                                  
                                  updatePreStmt.setString(5, (String) examination.getSelectedItem());
                                  
                                  updatePreStmt.setString(6,contactField.getText());
                                  
                            
                                  int i = updatePreStmt.executeUpdate();
                                  if(i==1){
                                      
                                       JOptionPane.showMessageDialog(panel, 

                                               "Successfully Updated");
                                     
                                     table.setValueAt(idField.getText(),r,0);
                                     
                                     table.setValueAt(nameField.getText(),r,1);

                                     table.setValueAt(ageField.getText(), r, 2);

                                     table.setValueAt(subject.getSelectedItem(),r,3);

                                     table.setValueAt(session.getSelectedItem(), r, 4);
                                     
                                     table.setValueAt(examination.getSelectedItem(), r, 5);
                                     
                                     table.setValueAt(contactField.getText(), r, 6);

                                  }

                                  

                              }

                              }catch(Exception ex){

                                      System.out.println("Update section: "+ex.getMessage()); 

                              }

                        }

              }

          });

 

            //Registering Anonymous Listener Class

            deleteButton.addActionListener(new AbstractAction(SHOW){ 

              public void actionPerformed(ActionEvent e){

                  try{ 

                  //Getting Selected Row No

                  int r = table.getSelectedRow(); 

                  if(r>=0){ 

                        if (JOptionPane.showConfirmDialog(panel,

                            "Are you sure want to Delete this 'RECORD' ? ","WARNING",

                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){

                              String id = (String)table.getModel().getValueAt(r,0);

 

                              // Executing MySQL Update Command

                              int i = stmt.executeUpdate("DELETE  FROM data WHERE Id="+id);

                              if(i==1){

                                    model.removeRow(r);

 

                                    // fields are blank

                                    blankFields();

                                    registerButton.setEnabled(true);

                                    deleteButton.setEnabled(false);

                                    updateButton.setEnabled(false);

                              }

                        }

                  }

                  }catch(Exception ex){

                         System.out.println(ex.getMessage());

                  }

              }

          });

 

           //Registering Anonymous Listener Class

            resetButton.addActionListener(new ActionListener(){

                  public void actionPerformed(ActionEvent arg0) {

                        // calling method resetFields()

                        resetFields();

                        registerButton.setEnabled(true);

                        updateButton.setEnabled(false);

                        deleteButton.setEnabled(false);

                        resetButton.setEnabled(true);

                  }

            });



            // Registering Anonymous Listener Class

            refresh.addActionListener(new ActionListener() {

                  public void actionPerformed(ActionEvent arg0) {

                        //calling  refresh() method

                        refreshTable();

                  }

            });



            //Registering Anonymous Listener Class

            table.addMouseListener(new MouseListener(){

                  public void mouseClicked(MouseEvent arg0){ 

                        //Getting Selected Row No

                  int r = table.getSelectedRow();

                  if(r>=0){ 

                        deleteButton.setEnabled(true);

                        updateButton.setEnabled(true);

                        resetButton.setEnabled(true);

                        registerButton.setEnabled(false); 


                        //Fetching records from Table on Fields

                        idField.setText(""+table.getModel().getValueAt(r,0));

                        nameField.setText(""+table.getModel().getValueAt(r,1));
                        
                        ageField.setText(""+table.getModel().getValueAt(r,2));

                        subject.setSelectedItem(""+table.getModel().getValueAt(r,3));
                        
                        session.setSelectedItem(""+table.getModel().getValueAt(r,4));
                        
                        examination.setSelectedItem(""+table.getModel().getValueAt(r,5));

                        contactField.setText(""+table.getModel().getValueAt(r,6));

                  }

                  }


               //@Override

                  @Override
                  public void mouseReleased(MouseEvent arg0) {}

               //@Override

                  @Override
                   public void mousePressed(MouseEvent arg0) {}

                // @Override 

                  @Override
                  public void mouseExited(MouseEvent arg0) {}

               //@Override 

                  @Override
                  public void mouseEntered(MouseEvent arg0) {}

            });



            // Displaying rows into the Frame table

            addRows();

      }



      // addRows method

      private void addRows(){

            try{

            Object[] row = null;

        

            

            rst = stmt.executeQuery("SELECT * FROM data");

            while(rst.next()){ 

                  String string = String.valueOf(rst.getString(1))+","+rst.getString(2)+","+rst.getString(3)+","+rst.getString(4)+","+rst.getString(5)+","+rst.getString(6)+","+rst.getString(7);

                  row = string.split(",");



                  model.addRow(row);

            }

            panel.revalidate();

            }catch(Exception ex){ System.out.println(ex.getMessage()); }

      }



      private void resetFields(){ 

 

            //calling method blankfields() 

            blankFields();

      }

 

      // refresh method

      private void refreshTable(){

 

            // removing all the rows of the table

            DefaultTableModel dm = (DefaultTableModel)table.getModel();

            dm.getDataVector().removeAllElements();

            System.out.println("Refresh Table");



            //calling method addRows

            addRows();

      }



      private void blankFields(){

            // fields will be blank

            idField.setText("");

            nameField.setText("");
            
            ageField.setText("");
            
            subject.setSelectedItem("PLEASE SELECT");
            
            session.setSelectedItem("PLEASE SELECT");
            
            examination.setSelectedItem("PLEASE SELECT");
            
            contactField.setText("");

      }



      // main() method

      public static void main(String[] args) {

            new DynamicRegForm();

      }     
      }