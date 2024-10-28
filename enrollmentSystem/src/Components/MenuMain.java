package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Components.LoginFrame.MainFrame;


public class MenuMain {
    JPanel MPanel = new JPanel();
    JLabel Header;
    JButton Student, Subject, Assessment, Logout;

    public MenuMain(){
        MPanel.setSize(550,550);
        MPanel.setSize(550,550);
        MPanel.setLayout(null);
        MPanel.setBackground(new Color(96, 32, 32));
        MainFrame.add(MPanel);

        Header = new JLabel("ENROLLMENT INFORMATION SYSTEM");
        Header.setBounds(90,110, 450, 20);
        Header.setFont(new Font("Inter", Font.BOLD, 20));
        Header.setForeground(new Color(252, 252, 252));
        MPanel.add(Header);

        Student = new JButton("STUDENT");
        Student.setBounds(100, 185,110, 30);
        Student.setFont(new Font("Inter", 1, 12));
        MPanel.add(Student);
        Student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudMenu();
                MPanel.setVisible(false);
            }
        });

        Subject = new JButton("SUBJECT");
        Subject.setBounds(215, 185,110, 30);
        Subject.setFont(new Font("Inter", 1, 12));
        MPanel.add(Subject);
        Subject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SubMenu();
                MPanel.setVisible(false);
            }
        });

        Assessment = new JButton("ASSESSMENT");
        Assessment.setBounds(330, 185,110, 30);
        Assessment.setFont(new Font("Inter", 1, 12));
        MPanel.add(Assessment);
        Assessment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AssessmentMenu();
                MPanel.setVisible(false);
            }
        });

        Logout = new JButton("LOGOUT");
        Logout.setFont(new Font("Inter", 1, 10));
        Logout.setForeground(new Color(255, 255, 255));
        Logout.setBackground(new Color(255, 255, 255, 0));
        Logout.setBounds(430, 460, 75, 25);
        MPanel.add(Logout);
        Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame();
                MPanel.setVisible(false);
            }
        });
    }

}
