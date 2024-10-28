package Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame {
    static JFrame MainFrame = new JFrame();
    JPanel LPanel = new JPanel();
    JLabel Header,Background, Logo, userTXT, passTXT, chkTXT;
    JTextField userField;
    JPasswordField passField;
    Checkbox ShowPass = new Checkbox();
    ImageIcon icon, icon0, logo;
    JButton lgnBTN, cancelBTN;

    public LoginFrame(){
        logo = new ImageIcon(LoginFrame.class.getResource("/USeP.png"));
        icon = new ImageIcon(LoginFrame.class.getResource("/USA.jpg"));
        icon0 = new ImageIcon(LoginFrame.class.getResource("/USE.png"));

        MainFrame.setSize(550, 550);
        MainFrame.setResizable(false);
        MainFrame.setIconImage(logo.getImage());




        LPanel.setLayout(null);
        LPanel.setBounds(0, 0, 550, 550);
        LPanel.setBackground(new Color(255,255,255,0));
        MainFrame.add(LPanel);


        Header = new JLabel("ENROLLMENT INFORMATION SYSTEM");
        Header.setBounds(90,110, 450, 20);
        Header.setFont(new Font("Inter", Font.BOLD, 20));
        Header.setForeground(new Color(238, 235, 235));
        LPanel.add(Header);

        userField = new JTextField();
        userField.setBounds(165,140, 230, 30);
        userField.setFont(new Font("Inter", Font.ITALIC, 12));
        LPanel.add(userField);
        userField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    passField.requestFocus();
                }
            }
        });

        passField = new JPasswordField();
        passField.setBounds(165, 210, 230, 30);
        passField.setFont(new Font("Inter", Font.ITALIC, 12));
        LPanel.add(passField);


        userTXT = new JLabel("USERNAME: ");
        userTXT.setBounds(70, 145, 150, 20);
        userTXT.setFont(new Font("Arial", 1, 15));
        userTXT.setForeground(new Color(250, 247, 247));
        LPanel.add(userTXT);

        passTXT = new JLabel("PASSWORD: ");
        passTXT.setBounds(70, 215, 150, 20);
        passTXT.setFont(new Font("Arial", Font.BOLD, 15));
        passTXT.setForeground(new Color(246, 241, 241));
        LPanel.add(passTXT);


        chkTXT = new JLabel("Show Password");
        chkTXT.setBounds(180, 241, 270, 30);
        chkTXT.setFont(new Font("Inter", Font.BOLD, 13));
        chkTXT.setForeground(new Color(255, 255, 255));
        LPanel.add(chkTXT);
        ShowPass.setBounds(165, 250, 12, 12);
        LPanel.add(ShowPass);
        ShowPass.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(ShowPass.getState()){
                    passField.setEchoChar((char) 0);
                }else{
                    passField.setEchoChar('•');
                }
            }
        });


        lgnBTN = new JButton("LOGIN");
        lgnBTN.setBounds(165, 270,80, 30);
        lgnBTN.setFont(new Font("Inter", Font.BOLD, 12));
        LPanel.add(lgnBTN);
        lgnBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userField.getText().equals("admin") && passField.getText().equals("admin")){
                    new MenuMain();
                    LPanel.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null, "Wrong Credentials.");
                    userField.setText("");
                    passField.setText("");
                }
            }
        });

        cancelBTN = new JButton("CANCEL");
        cancelBTN.setBounds(260, 270, 80, 30);
        cancelBTN.setFont(new Font("Arial", Font.BOLD, 12));
        LPanel.add(cancelBTN);
        cancelBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==cancelBTN){
                    userField.setText("");
                    passField.setText("");
                }
            }
        });






        Logo = new JLabel(icon0);
        Logo.setBounds(220,5, 100, 100);
        LPanel.add(Logo);

        Background = new JLabel(icon);
        Background.setBounds(0, 0, 825, 550);
        LPanel.add(Background);





        MainFrame.setLayout(null);
        MainFrame.setLocationRelativeTo(null);
        MainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MainFrame.setVisible(true);
    }
}
