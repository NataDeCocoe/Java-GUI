package ActionPerformed;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static MainFrame.LoginFrame.MFrame;

public class AddProdPanel {
    JPanel APanel, bgPanel;
    JLabel  AddProductText, IdentificationNumText, BrandText, ColorText, PriceText, QuantityText;
    JTextField IdentificationField, BrandField, ColorField, PriceField, QuantityField;
    JButton bckBTN, sbmtBTN;
    Connection con;
    Statement stmt;
    public AddProdPanel(){
        String url = "jdbc:mysql://localhost:3306/products";
        String user = "root";
        String password = "";

        try{
            con = DriverManager.getConnection(url, user, password);
        }catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect to the database", "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        APanel = new JPanel();
        APanel.setBounds(0, 0, 500, 600);
        APanel.setLayout(null);
        MFrame.add(APanel);

        bgPanel = new JPanel();
        bgPanel.setBounds(30, 30, 420, 500);
        bgPanel.setBackground(new Color(255, 255, 255));
        bgPanel.setLayout(null);
        APanel.add(bgPanel);

        bckBTN = new JButton("BACK");
        bckBTN.setBounds(15, 15, 69, 25);
        bckBTN.setFont(new Font("Sanserif", Font.BOLD,12));
        bckBTN.setForeground(new Color(255, 255 ,255));
        bckBTN.setBackground(new Color(128,0,0));
        bgPanel.add(bckBTN);
        bckBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DashBoard();
                bgPanel.setVisible(false);
                APanel.setVisible(false);
            }
        });

        AddProductText = new JLabel("ADD PRODUCT");
        AddProductText.setBounds(95,17,250,21);
        AddProductText.setFont(new Font("Inter", Font.BOLD,20));
        AddProductText.setForeground(new Color(128,0,0));
        bgPanel.add(AddProductText);

        IdentificationNumText = new JLabel("Identification Number*");
        IdentificationNumText.setBounds(40,60,250,12);
        bgPanel.add(IdentificationNumText);

        BrandText = new JLabel("Brand*");
        BrandText.setBounds(40,125,250,12);
        bgPanel.add(BrandText);

        ColorText = new JLabel("Color*");
        ColorText.setBounds(40, 190, 250, 12);
        bgPanel.add(ColorText);

        PriceText = new JLabel("Price*");
        PriceText.setBounds(40, 255, 250, 12);
        bgPanel.add(PriceText);

        QuantityText = new JLabel("Quantity*");
        QuantityText.setBounds(40 , 320, 250, 12);
        bgPanel.add(QuantityText);

        IdentificationField = new JTextField();
        IdentificationField.setBounds(40,80,350,30);
        bgPanel.add(IdentificationField);

        BrandField = new JTextField();
        BrandField.setBounds(40,145,350,30);
        bgPanel.add(BrandField);

        ColorField = new JTextField();
        ColorField.setBounds(40, 210, 350, 30);
        bgPanel.add(ColorField);

        PriceField = new JTextField();
        PriceField.setBounds(40, 275, 350, 30);
        bgPanel.add(PriceField);

        QuantityField = new JTextField();
        QuantityField.setBounds(40, 340, 350, 30);
        bgPanel.add(QuantityField);

        sbmtBTN = new JButton("Submit");
        sbmtBTN.setBounds(315, 405, 75, 25);
        sbmtBTN.setFont(new Font("Sanserif", Font.BOLD,12));
        sbmtBTN.setForeground(new Color(255, 255 ,255));
        sbmtBTN.setBackground(new Color(128,0,0));
        bgPanel.add(sbmtBTN);
        sbmtBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ID = Integer.parseInt(IdentificationField.getText());
                String Brand = BrandField.getText();
                String Color = ColorField.getText();
                Double Price = Double.valueOf(PriceField.getText());
                int Quan = Integer.parseInt(QuantityField.getText());

                try{
                    stmt = con.createStatement();
                    String add = "INSERT INTO smartphone (Identification_Number, Brand, Color, Price, Quantity) VALUES (" +
                                    ID + ", '" +
                                    Brand + "', '" +
                                    Color + "', '" +
                                    Price + "', '" +
                                    Quan + "')";
                                    stmt.executeUpdate(add);
                                    JOptionPane.showMessageDialog(null, "Added successfully.");

                                    IdentificationField.setText("");
                                    BrandField.setText("");
                                    ColorField.setText("");
                                    PriceField.setText("");
                                    QuantityField.setText("");

                }catch (SQLException ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Duplicate entry into the database.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }


            }
        });
    }
}