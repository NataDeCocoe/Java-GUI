package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static Components.LoginFrame.MainFrame;


public class StudMenu {
    JPanel SPanel = new JPanel();
    JLabel Header, IDTxt, NameTxt, ADDTxt, CTxt, YearTxt;
    JTextField IDField, NameField, ADDField, CField, YearField;
    JButton SearchBTN, SaveBTN, CancelBTN, BackBTN, DeleteBTN;
    Connection con;
    Statement stmt;

    public StudMenu(){
        String url = "jdbc:mysql://localhost:3306/Schooldb";
        String user = "root";
        String password = "";

        try{
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("FAILED TO CONNECT TO DATABASE.");;
        }



        SPanel.setSize(550,550);
        SPanel.setSize(550,550);
        SPanel.setLayout(null);
        SPanel.setBackground(new Color(96, 32, 32));
        MainFrame.add(SPanel);

        Header = new JLabel("ENROLLMENT INFORMATION SYSTEM");
        Header.setBounds(130,25, 350, 20);
        Header.setFont(new Font("Arial", 1, 15));
        Header.setForeground(new Color(252, 252, 252));
        SPanel.add(Header);

        IDTxt = new JLabel("STUDENT ID: ");
        IDTxt.setBounds(40,70,150,20);
        IDTxt.setFont(new Font("Arial", 1, 15));
        IDTxt.setForeground(new Color(252, 252, 252));
        SPanel.add(IDTxt);

        NameTxt = new JLabel("STUDENT NAME: ");
        NameTxt.setBounds(40,120,150,20);
        NameTxt.setFont(new Font("Arial", 1, 15));
        NameTxt.setForeground(new Color(252, 252, 252));
        SPanel.add(NameTxt);

        ADDTxt = new JLabel("ADDRESS: ");
        ADDTxt.setBounds(40,170,150,20);
        ADDTxt.setFont(new Font("Arial", 1, 15));
        ADDTxt.setForeground(new Color(252, 252, 252));
        SPanel.add(ADDTxt);

        CTxt = new JLabel("COURSE: ");
        CTxt.setBounds(40,220,150,20);
        CTxt.setFont(new Font("Arial", 1, 15));
        CTxt.setForeground(new Color(252, 252, 252));
        SPanel.add(CTxt);

        YearTxt = new JLabel("YEAR: ");
        YearTxt.setBounds(40,270,150,20);
        YearTxt.setFont(new Font("Arial", 1, 15));
        YearTxt.setForeground(new Color(252, 252, 252));
        SPanel.add(YearTxt);

        IDField = new JTextField();
        IDField.setBounds(185, 65, 200, 30);
        SPanel.add(IDField);

        NameField = new JTextField();
        NameField.setBounds(185, 115, 200, 30);
        SPanel.add(NameField);

        ADDField = new JTextField();
        ADDField.setBounds(185, 165, 200, 30);
        SPanel.add(ADDField);

        CField = new JTextField();
        CField.setBounds(185, 215, 200, 30);
        SPanel.add(CField);

        YearField = new JTextField();
        YearField.setBounds(185, 265, 200, 30);
        SPanel.add(YearField);

        SearchBTN = new JButton("SEARCH");
        SearchBTN.setFont(new Font("Inter", 1, 10));
        SearchBTN.setBounds(395, 65, 90, 25);
        SPanel.add(SearchBTN);
        SearchBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ID = Integer.parseInt(IDField.getText());

                try{
                    stmt = con.createStatement();
                    String sData = "SELECT * FROM student WHERE ID =" + ID;
                    ResultSet rs = stmt.executeQuery(sData);
                    if(rs.next()){
                        NameField.setText(rs.getString("STNAME"));
                        ADDField.setText(rs.getString("ADDRESS"));
                        CField.setText(rs.getString("COURSE"));
                        YearField.setText(rs.getString("YEAR"));
                        DeleteBTN.setEnabled(true);
                    }else{
                        JOptionPane.showMessageDialog(null, "No such ID exist into the database.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                                IDField.setText("");
                    }
                }catch (SQLException s){
                    s.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Cannot search data into the database.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        SaveBTN = new JButton("SAVE");
        SaveBTN.setFont(new Font("Inter", 1, 10));
        SaveBTN.setBounds(185, 320, 90, 25);
        SPanel.add(SaveBTN);
        SaveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    int ID = Integer.parseInt(IDField.getText());
                    String Name = NameField.getText();
                    String ADD = ADDField.getText();
                    String Course = CField.getText();
                    int Year = Integer.parseInt(YearField.getText());

                    try{
                            stmt = con.createStatement();
                            if(e.getSource() == SaveBTN){
                                String addDATA =  "INSERT INTO student (ID, STNAME, ADDRESS, COURSE, YEAR) VALUES (" +
                                        ID + ", '" +
                                        Name + "', '" +
                                        ADD + "', '" +
                                        Course + "', '" +
                                        Year + "')";
                                                  stmt.executeUpdate(addDATA);
                                                  JOptionPane.showMessageDialog(null, "Record added successfully.");
                                                  IDField.setText("");
                                                  NameField.setText("");
                                                  ADDField.setText("");
                                                  CField.setText("");
                                                  YearField.setText("");
                            }
                    }catch (SQLException a){
                            a.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Failed to add the data into the database.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
            }
        });



        CancelBTN = new JButton("CANCEL");
        CancelBTN.setFont(new Font("Inter", 1, 10));
        CancelBTN.setBounds(290, 320, 90, 25);
        SPanel.add(CancelBTN);
        CancelBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IDField.setText("");
                NameField.setText("");
                ADDField.setText("");
                CField.setText("");
                YearField.setText("");
            }
        });

        DeleteBTN = new JButton("DELETE");
        DeleteBTN.setFont(new Font("Inter", 1, 10));
        DeleteBTN.setBounds(395, 265,90, 25);
        DeleteBTN.setEnabled(false);
        SPanel.add(DeleteBTN);
        DeleteBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ID = Integer.parseInt(IDField.getText());
                try{
                    if(e.getSource() == DeleteBTN){
                        String dlt = "DELETE FROM student WHERE ID =" + ID;
                        PreparedStatement pstmt = con.prepareStatement(dlt);
                        int rowsAffected = pstmt.executeUpdate();

                        if(rowsAffected > 0){
                            JOptionPane.showMessageDialog(null, "Data deleted successfully.");
                            IDField.setText("");
                            NameField.setText("");
                            ADDField.setText("");
                            CField.setText("");
                            YearField.setText("");
                        }else{
                            JOptionPane.showMessageDialog(null, "Cannot find record in the database", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }catch (SQLException b){
                    b.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to delete the data into the database.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        BackBTN = new JButton("BACK");
        BackBTN.setFont(new Font("Inter", 1, 10));
        BackBTN.setBounds(430, 460, 90, 25);
        SPanel.add(BackBTN);
        BackBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuMain();
                SPanel.setVisible(false);
            }
        });
    }
}
