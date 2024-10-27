package ActionPerformed;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static MainFrame.LoginFrame.MFrame;

public class EditProdPanel {
    JPanel EPanel, bgPanel;
    JLabel  EditProductText, IdentificationNumText, BrandText, ColorText, PriceText, QuantityText;
    JTextField IdentificationField, BrandField, ColorField, PriceField, QuantityField;
    JButton bckBTN, saveBTN, srchBTN;
    Connection con;
    Statement stmt;
    EditProdPanel(){
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


        EPanel = new JPanel();
        EPanel.setBounds(0, 0, 500, 600);
        EPanel.setLayout(null);
        MFrame.add(EPanel);

        bgPanel = new JPanel();
        bgPanel.setBounds(30, 30, 420, 500);
        bgPanel.setBackground(new Color(255, 255, 255));
        bgPanel.setLayout(null);
        EPanel.add(bgPanel);


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
                EPanel.setVisible(false);
            }
        });

        EditProductText = new JLabel("EDIT PRODUCT");
        EditProductText.setBounds(95,17,250,21);
        EditProductText.setFont(new Font("Inter", Font.BOLD,20));
        EditProductText.setForeground(new Color(128,0,0));
        bgPanel.add(EditProductText);

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
        IdentificationField.setBounds(40,80,250,30);
        bgPanel.add(IdentificationField);

        BrandField = new JTextField();
        BrandField.setBounds(40,145,350,30);
        BrandField.setEnabled(false);
        bgPanel.add(BrandField);

        ColorField = new JTextField();
        ColorField.setBounds(40, 210, 350, 30);
        ColorField.setEnabled(false);
        bgPanel.add(ColorField);

        PriceField = new JTextField();
        PriceField.setBounds(40, 275, 350, 30);
        PriceField.setEnabled(false);
        bgPanel.add(PriceField);

        QuantityField = new JTextField();
        QuantityField.setBounds(40, 340, 350, 30);
        QuantityField.setEnabled(false);
        bgPanel.add(QuantityField);

        saveBTN = new JButton("Save");
        saveBTN.setBounds(315, 405, 75, 25);
        saveBTN.setFont(new Font("Sanserif", Font.BOLD,12));
        saveBTN.setForeground(new Color(255, 255 ,255));
        saveBTN.setBackground(new Color(128,0,0));
        saveBTN.setEnabled(false);
        bgPanel.add(saveBTN);
        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ID = Integer.parseInt(IdentificationField.getText());
                String Brand = BrandField.getText();
                String Color = ColorField.getText();
                Double Price = Double.valueOf(PriceField.getText());
                int Quan = Integer.parseInt(QuantityField.getText());
                try{
                    stmt = con.createStatement();

                    if(e.getSource() == saveBTN){
                        String upt = "UPDATE smartphone SET Brand = '" +
                                Brand + "', Color = '" +
                                Color + "',Price = '" +
                                Price + "', Quantity = '" +
                                Quan + "' WHERE Identification_Number = " +
                                ID;
                        int rowsAffected = stmt.executeUpdate(upt);
                        if(rowsAffected > 0){
                            JOptionPane.showMessageDialog(null, "Data updated successfully.");
                            IdentificationField.setText("");
                            BrandField.setText("");
                            ColorField.setText("");
                            PriceField.setText("");
                            QuantityField.setText("");
                        }else{
                            JOptionPane.showMessageDialog(null, "No data found with specified ID.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                                    IdentificationField.setText("");
                                    BrandField.setText("");
                                    ColorField.setText("");
                                    PriceField.setText("");
                                    QuantityField.setText("");
                        }
                    }
                }catch (SQLException a){
                    a.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to insert the data into the database.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        srchBTN = new JButton("Search");
        srchBTN.setBounds(315, 83, 75, 25);
        srchBTN.setFont(new Font("Sanserif", Font.BOLD,12));
        srchBTN.setForeground(new Color(255, 255 ,255));
        srchBTN.setBackground(new Color(128,0,0));
        bgPanel.add(srchBTN);
        srchBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    int ID = Integer.parseInt(IdentificationField.getText());
                try{
                    stmt = con.createStatement();

                    if(e.getSource() == srchBTN){
                        String getData = "SELECT * FROM smartphone WHERE Identification_Number =" + ID;

                        ResultSet rs = stmt.executeQuery(getData);
                        if(rs.next()){
                            BrandField.setText(rs.getString("Brand"));
                            ColorField.setText(rs.getString("Color"));
                            PriceField.setText(rs.getString("Price"));
                            QuantityField.setText(rs.getString("Quantity"));

                            BrandField.setEnabled(true);
                            ColorField.setEnabled(true);
                            PriceField.setEnabled(true);
                            QuantityField.setEnabled(true);
                            saveBTN.setEnabled(true);
                        }else{
                            JOptionPane.showMessageDialog(null, "No data found with specified ID.", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                                    IdentificationField.setText("");
                        }

                    }
                }catch (SQLException a){
                    a.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Search failed, no data from database.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }
}
