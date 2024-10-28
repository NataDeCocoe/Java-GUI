package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static Components.LoginFrame.MainFrame;
import static Components.ReceiptPanel.*;

public class AssessmentMenu {
    JPanel APanel = new JPanel();
    JLabel Header;
    JLabel StudName;
    JLabel SubCode;
    JLabel List;
    JLabel Sub1;
    JLabel Sub2;
    JLabel Tuition;
    JComboBox Name, Code;
    JScrollPane pane;
    JButton AddSubBTN, CancelBTN, BackBTN, PrintBTN;
    Connection con;
    Statement stmt;
    double units;
    double Ftotal;
    double Total;
    int count;
    ResultSet rs;
    String selectedItem, selectedName, Sub, forS1, forS2, TotalAs;

    AssessmentMenu(){
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
        APanel.setSize(550,550);
        APanel.setSize(550,550);
        APanel.setLayout(null);
        APanel.setBackground(new Color(96, 32, 32));
        MainFrame.add(APanel);


        Header = new JLabel("ENROLLMENT INFORMATION SYSTEM");
        Header.setBounds(130,25, 350, 20);
        Header.setFont(new Font("Arial", 1, 15));
        Header.setForeground(new Color(252, 252, 252));
        APanel.add(Header);

        StudName = new JLabel("STUDENT NAME: ");
        StudName.setBounds(40,70,150,20);
        StudName.setFont(new Font("Arial", 1, 15));
        StudName.setForeground(new Color(252, 252, 252));
        APanel.add(StudName);

        SubCode = new JLabel("SUBJECT CODE: ");
        SubCode.setBounds(40,120,150,20);
        SubCode.setFont(new Font("Arial", 1, 15));
        SubCode.setForeground(new Color(252, 252, 252));
        APanel.add(SubCode);

        Name = new JComboBox();
        try{

            String getDATA = "SELECT * FROM student";
            PreparedStatement pstmt = con.prepareStatement(getDATA);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Name.addItem(rs.getString("STNAME"));
            }
        }catch (SQLException g){
            g.printStackTrace();
        }
            Name.setBounds(185, 65, 200, 30);
            APanel.add(Name);

        Code = new JComboBox();
        try{

            String getDATA = "SELECT * FROM subject";
            PreparedStatement pstmt = con.prepareStatement(getDATA);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Code.addItem(rs.getString("CODE"));
            }
        }catch (SQLException g){
            g.printStackTrace();
        }
        Code.setBounds(185, 115, 200, 30);
        APanel.add(Code);

        AddSubBTN = new JButton("ADD SUBJECT");
        AddSubBTN.setFont(new Font("Inter", 1, 10));
        AddSubBTN.setBounds(395, 115, 105, 25);
        APanel.add(AddSubBTN);
        AddSubBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               selectedItem = (String) Code.getSelectedItem();
               String forSub = "";
                try {
                    stmt = con.createStatement();
                    String getSub = "SELECT DESCRIPTION FROM subject WHERE CODE =" + selectedItem;




                    rs = stmt.executeQuery(getSub);
                    if (rs.next()) {

                        Sub = rs.getString("DESCRIPTION");

                    if (selectedItem != null) {
                        count++;
                        if (count % 2 == 1) {


                            Sub1.setText("SUBJECT 1: " + Sub);
                            forS1 = Sub;

                        } else {

                            Sub2.setText("SUBJECT 2: " + Sub);
                            forS2 = Sub;
                        }
                        if(count >= 2){
                            AddSubBTN.setEnabled(false);
                        }
                            unitHandler();
                            Tuition.setText("TOTAL TUITION: " + Total);
                            TotalAs = "TOTAL ASSESSMENT: " + Total;
                    }
                }

                }catch (SQLException a){
                    a.printStackTrace();
                }}

        });



        BackBTN = new JButton("BACK");
        BackBTN.setFont(new Font("Inter", 1, 10));
        BackBTN.setBounds(330, 460, 90, 25);
        APanel.add(BackBTN);
        BackBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuMain();
                APanel.setVisible(false);
            }
        });


        List = new JLabel("LIST OF SUBJECTS ENROLLED: ");
        List.setBounds(40,170,200,20);
        List.setFont(new Font("Inter", 1, 12));
        List.setForeground(new Color(252, 252, 252));
        APanel.add(List);

        Tuition = new JLabel("TOTAL TUITION: ");
        Tuition.setBounds(185, 420, 200, 30);
        Tuition.setFont(new Font("Inter", 1, 12));
        Tuition.setForeground(new Color(252, 252, 252));
        APanel.add(Tuition);

        PrintBTN = new JButton("PRINT");
        PrintBTN.setFont(new Font("Inter", 1, 10));
        PrintBTN.setBounds(430, 460, 90, 25);
        APanel.add(PrintBTN);
        PrintBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReceiptPanel();
                APanel.setVisible(false);
                selectedName = (String) Name.getSelectedItem();

                try{
                    stmt = con.createStatement();
                    String rID = "SELECT * FROM student WHERE STNAME = '" + selectedName + "'";

                    ResultSet rs = stmt.executeQuery(rID);
                    while(rs.next()){
                        String dataID = rs.getString("ID");
                        setStudName();
                        String dataC = rs.getString("COURSE");
                        String dataY = rs.getString("YEAR");
                        StudIDTxt.setText("Student ID: " + dataID);
                        Course.setText("Course: " + dataC);
                        Year.setText("Year: " + dataY);
                        eSub1.setText(forS1);
                        eSub2.setText(forS2);
                        TAssessment.setText(TotalAs);
                    }
                }catch (SQLException a){
                    a.printStackTrace();
                }
            }
        });

        CancelBTN = new JButton("CANCEL");
        CancelBTN.setFont(new Font("Inter", 1, 10));
        CancelBTN.setBounds(230, 460, 90, 25);
        APanel.add(CancelBTN);
        CancelBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Total = 0;
                count = 0;
                Sub1.setText("");
                Sub2.setText("");
                Tuition.setText("TOTAL TUITION: ");
                AddSubBTN.setEnabled(true);
            }
        });

        Sub1 = new JLabel();
        Sub1.setBounds(105,190,250,20);
        Sub1.setFont(new Font("Inter", 1, 12));
        Sub1.setForeground(new Color(252, 252, 252));
        APanel.add(Sub1);

        Sub2 = new JLabel();
        Sub2.setBounds(105,210,250,20);
        Sub2.setFont(new Font("Inter", 1, 12));
        Sub2.setForeground(new Color(252, 252, 252));
        APanel.add(Sub2);


    }
    public void unitHandler(){

        try{
            stmt = con.createStatement();
            String getUnit = "SELECT UNITS FROM subject WHERE CODE = " + selectedItem;
            rs = stmt.executeQuery(getUnit);
            while (rs.next()){
                units = Double.parseDouble(String.valueOf(Double.parseDouble(rs.getString("UNITS"))));
                Ftotal = units * 200;
                Total += Ftotal;
            }

        }catch (SQLException a){
            a.printStackTrace();
        }
    }
    public void setStudName(){
       String forName = String.valueOf(Name.getSelectedItem());
       String dataN;
       try{
           String getName = "SELECT STNAME FROM student WHERE STNAME = '" + forName + "'";

           PreparedStatement pstmt = con.prepareStatement(getName);
           ResultSet rs = pstmt.executeQuery();
           while(rs.next()){
               dataN = rs.getString("STNAME");
               StudNameTxt.setText("Student Name: " + dataN);
           }

       }catch (SQLException r){
           r.printStackTrace();
       }
    }
}

