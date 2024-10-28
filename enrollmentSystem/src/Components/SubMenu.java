package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static Components.LoginFrame.MainFrame;


public class SubMenu {
    JPanel SPanel = new JPanel();
    JLabel Header, CodeTxt, DescTxt, SchedTxt, TeachTxt, UnitsTxt;
    JTextField CodeField, DescField, SchedField, TeachField, UnitsField;
    JButton SearchBTN, SaveBTN, CancelBTN, BackBTN, DeleteBTN;
    Connection con;
    Statement stmt;
    static int Units;

    public SubMenu(){
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

        CodeTxt = new JLabel("SUBJECT CODE: ");
        CodeTxt.setBounds(40,70,150,20);
        CodeTxt.setFont(new Font("Arial", 1, 15));
        CodeTxt.setForeground(new Color(252, 252, 252));
        SPanel.add(CodeTxt);

        DescTxt = new JLabel("DESCRIPTION: ");
        DescTxt.setBounds(40,120,150,20);
        DescTxt.setFont(new Font("Arial", 1, 15));
        DescTxt.setForeground(new Color(252, 252, 252));
        SPanel.add(DescTxt);

        SchedTxt = new JLabel("SCHEDULE: ");
        SchedTxt.setBounds(40,170,150,20);
        SchedTxt.setFont(new Font("Arial", 1, 15));
        SchedTxt.setForeground(new Color(252, 252, 252));
        SPanel.add(SchedTxt);

        TeachTxt = new JLabel("TEACHER: ");
        TeachTxt.setBounds(40,220,150,20);
        TeachTxt.setFont(new Font("Arial", 1, 15));
        TeachTxt.setForeground(new Color(252, 252, 252));
        SPanel.add(TeachTxt);

        UnitsTxt = new JLabel("UNITS: ");
        UnitsTxt.setBounds(40,270,150,20);
        UnitsTxt.setFont(new Font("Arial", 1, 15));
        UnitsTxt.setForeground(new Color(252, 252, 252));
        SPanel.add(UnitsTxt);

        CodeField = new JTextField();
        CodeField.setBounds(185, 65, 200, 30);
        SPanel.add(CodeField);

        DescField = new JTextField();
        DescField.setBounds(185, 115, 200, 30);
        SPanel.add(DescField);

        SchedField = new JTextField();
        SchedField.setBounds(185, 165, 200, 30);
        SPanel.add(SchedField);

        TeachField = new JTextField();
        TeachField.setBounds(185, 215, 200, 30);
        SPanel.add(TeachField);

        UnitsField = new JTextField();
        UnitsField.setBounds(185, 265, 200, 30);
        SPanel.add(UnitsField);

        SearchBTN = new JButton("SEARCH");
        SearchBTN.setFont(new Font("Inter", 1, 10));
        SearchBTN.setBounds(395, 65, 90, 25);
        SPanel.add(SearchBTN);
        SearchBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int Code = Integer.parseInt(CodeField.getText());

                try{
                    stmt = con.createStatement();
                    String sData = "SELECT * FROM subject WHERE CODE =" + Code;
                    ResultSet rs = stmt.executeQuery(sData);
                    if(rs.next()){
                        DescField.setText(rs.getString("DESCRIPTION"));
                        SchedField.setText(rs.getString("SCHEDULE"));
                        TeachField.setText(rs.getString("TEACHER"));
                        UnitsField.setText(rs.getString("UNITS"));
                        DeleteBTN.setEnabled(true);
                    }else{
                        JOptionPane.showMessageDialog(null, "No such CODE exist into the database.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        CodeField.setText("");
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
                int Code = Integer.parseInt(CodeField.getText());
                String Desc = DescField.getText();
                String Sched = SchedField.getText();
                String Teach = TeachField.getText();
                Units = Integer.parseInt(UnitsField.getText());

                try{
                    stmt = con.createStatement();
                    if(e.getSource() == SaveBTN){
                        String addDATA =  "INSERT INTO subject (CODE, DESCRIPTION, SCHEDULE, TEACHER, UNITS) VALUES (" +
                                Code + ", '" +
                                Desc + "', '" +
                                Sched + "', '" +
                                Teach + "', '" +
                                Units + "')";
                        stmt.executeUpdate(addDATA);
                        JOptionPane.showMessageDialog(null, "Record added successfully.");
                        CodeField.setText("");
                        DescField.setText("");
                        SchedField.setText("");
                        TeachField.setText("");
                        UnitsField.setText("");
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
                CodeField.setText("");
                DescField.setText("");
                SchedField.setText("");
                TeachField.setText("");
                UnitsField.setText("");
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
                int Code = Integer.parseInt(CodeField.getText());
                int output = JOptionPane.showConfirmDialog(null
                        , "Do you want to delete this subject?"
                        ,""
                        ,JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                try{
                    if(output == JOptionPane.YES_OPTION){


                        String dlt = "DELETE FROM subject WHERE CODE =" + Code;
                        PreparedStatement pstmt = con.prepareStatement(dlt);
                        int rowsAffected = pstmt.executeUpdate();

                        if(rowsAffected > 0){
                            JOptionPane.showMessageDialog(null, "Data deleted successfully.");
                            CodeField.setText("");
                            DescField.setText("");
                            SchedField.setText("");
                            TeachField.setText("");
                            UnitsField.setText("");
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
