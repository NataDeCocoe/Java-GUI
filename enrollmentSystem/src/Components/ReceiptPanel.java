package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;



import static Components.LoginFrame.MainFrame;

public class ReceiptPanel {
    JPanel RPanel = new JPanel();
    public static JLabel Header, OR, StudIDTxt, StudNameTxt, Course, Year, SubEnrolled, eSub1, eSub2, TAssessment;
    JButton BckBTN;
    Connection con;
    Statement stmt;
    int count;

    ReceiptPanel(){
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

        RPanel.setSize(550,550);
        RPanel.setSize(550,550);
        RPanel.setLayout(null);
        RPanel.setBackground(new Color(96, 32, 32));
        MainFrame.add(RPanel);

        Header = new JLabel("ENROLLMENT INFORMATION SYSTEM");
        Header.setBounds(130,25, 350, 20);
        Header.setFont(new Font("Arial", 1, 15));
        Header.setForeground(new Color(252, 252, 252));
        RPanel.add(Header);

        OR = new JLabel("OFFICIAL RECEIPT");
        OR.setBounds(210,45, 350, 20);
        OR.setFont(new Font("Arial", 1, 15));
        OR.setForeground(new Color(252, 252, 252));
        RPanel.add(OR);

        StudIDTxt = new JLabel("Student ID: ");
        StudIDTxt.setBounds(40,70,250,20);
        StudIDTxt.setFont(new Font("Arial", 1, 15));
        StudIDTxt.setForeground(new Color(252, 252, 252));
        RPanel.add(StudIDTxt);

        StudNameTxt = new JLabel("Student Name: ");
        StudNameTxt.setBounds(40,120,250,20);
        StudNameTxt.setFont(new Font("Arial", 1, 15));
        StudNameTxt.setForeground(new Color(252, 252, 252));
        RPanel.add(StudNameTxt);



        Course = new JLabel("Course: ");
        Course.setBounds(40,170,150,20);
        Course.setFont(new Font("Arial", 1, 15));
        Course.setForeground(new Color(252, 252, 252));
        RPanel.add(Course);

        Year = new JLabel("Year: ");
        Year.setBounds(360,170,150,20);
        Year.setFont(new Font("Arial", 1, 15));
        Year.setForeground(new Color(252, 252, 252));
        RPanel.add(Year);

        SubEnrolled = new JLabel("Subjects Enrolled: ");
        SubEnrolled.setBounds(40,280,260,20);
        SubEnrolled.setFont(new Font("Arial", 1, 15));
        SubEnrolled.setForeground(new Color(252, 252, 252));
        RPanel.add(SubEnrolled);

        eSub1 = new JLabel();
        eSub1.setBounds(40,300,260,20);
        eSub1.setFont(new Font("Arial", 1, 15));
        eSub1.setForeground(new Color(252, 252, 252));
        RPanel.add(eSub1);

       eSub2 = new JLabel();
       eSub2.setBounds(40,320,260,20);
       eSub2.setFont(new Font("Arial", 1, 15));
       eSub2.setForeground(new Color(252, 252, 252));
        RPanel.add(eSub2);

        TAssessment = new JLabel("TOTAL ASSESSMENT: ");
        TAssessment.setBounds(40, 460, 260, 25);
        TAssessment.setFont(new Font("Arial", 1, 15));
        TAssessment.setForeground(new Color(252, 252, 252));
        RPanel.add(TAssessment);

        BckBTN = new JButton("BACK");
        BckBTN.setFont(new Font("Inter", 1, 10));
        BckBTN.setBounds(430, 460, 90, 25);
        RPanel.add(BckBTN);
        BckBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AssessmentMenu();
                RPanel.setVisible(false);
            }
        });
    }
}
