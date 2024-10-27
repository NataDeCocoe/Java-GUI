package MainFrame;

import ActionPerformed.DashBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame {
    public static JFrame MFrame = new JFrame();
    JPanel LPanel, bgPanel;
    JLabel Header, userTXT, passTXT;
    JTextField userField;
    JPasswordField passField;
    JButton lgnBTN;
    public static String Uname, Upass;
    ImageIcon img;
    public LoginFrame(){

        img = new ImageIcon(LoginFrame.class.getResource("/USeP.png"));


        MFrame.setIconImage(img.getImage());
        MFrame.setSize(500, 600);
        MFrame.setResizable(false);

        LPanel = new JPanel();
        LPanel.setBounds(0, 0, 500, 600);
        LPanel.setLayout(null);
        MFrame.add(LPanel);

        bgPanel = new JPanel();
        bgPanel.setBounds(30, 30, 420, 500);
        bgPanel.setBackground(new Color(255, 255, 255));
        bgPanel.setLayout(null);
        LPanel.add(bgPanel);

        Header = new JLabel("WELCOME!");
        Header.setBounds(130, 180, 300, 39);
        Header.setFont(new Font("Inter", Font.BOLD, 30));
        bgPanel.add(Header);

        userTXT = new JLabel("Username: ");
        userTXT.setBounds(100, 220, 100, 12);
        bgPanel.add(userTXT);

        passTXT = new JLabel("Password: ");
        passTXT.setBounds(100, 270, 100, 12);
        bgPanel.add(passTXT);

        userField = new JTextField();
        userField.setBounds(100, 235, 250, 26);
        bgPanel.add(userField);

        passField = new JPasswordField();
        passField.setBounds(100, 290, 250, 29);
        bgPanel.add(passField);

        lgnBTN = new JButton("Login");
        lgnBTN.setBounds(285, 330, 65, 21);
        lgnBTN.setFont(new Font("Arial", Font.BOLD,12));
        lgnBTN.setForeground(new Color(255, 255, 255));
        lgnBTN.setBackground(new Color(128, 0, 0));
        bgPanel.add(lgnBTN);
        lgnBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Uname = userField.getText();
                Upass = passField.getText();
                if(userField.getText().equals(Uname) && passField.getText().equals(Upass)){
                    new DashBoard();
                    bgPanel.setVisible(false);
                    LPanel.setVisible(false);
                }
            }
        });






        MFrame.setLayout(null);
        MFrame.setLocationRelativeTo(null);
        MFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        MFrame.setVisible(true);
    }
}