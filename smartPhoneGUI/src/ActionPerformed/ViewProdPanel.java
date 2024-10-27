package ActionPerformed;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static MainFrame.LoginFrame.MFrame;

public class ViewProdPanel {
    JPanel VPanel, bgPanel;
    JLabel ViewProductText;
    JButton bckBTN;
    private JTable tb;
    JScrollPane Sp;
    DefaultTableModel dm;
    Connection con;

    ViewProdPanel(){
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

        VPanel = new JPanel();
        VPanel.setBounds(0, 0, 500, 600);
        VPanel.setLayout(null);
        MFrame.add(VPanel);

        bgPanel = new JPanel();
        bgPanel.setBounds(30, 30, 420, 500);
        bgPanel.setBackground(new Color(255, 255, 255));
        bgPanel.setLayout(null);
        VPanel.add(bgPanel);

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
                VPanel.setVisible(false);
            }
        });

        ViewProductText = new JLabel("VIEW PRODUCT");
        ViewProductText.setBounds(95,17,250,21);
        ViewProductText.setFont(new Font("Inter", Font.BOLD,20));
        ViewProductText.setForeground(new Color(128,0,0));
        bgPanel.add(ViewProductText);


        dm = new DefaultTableModel();
        dm = new DefaultTableModel();
        dm.setColumnIdentifiers(new String[] {"ID", "Brand", "Color", "Price", "Quantity"});

        try{
            String view = "SELECT * FROM smartphone";
            PreparedStatement pstmt = con.prepareStatement(view);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                int ID = rs.getInt("Identification_Number");
                String Brand = rs.getString("Brand");
                String Color = rs.getString("Color");
                Double Price = rs.getDouble("Price");
                int Quan = rs.getInt("Quantity");

                dm.addRow(new Object[]{ID, Brand, Color, Price, Quan});


            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        tb = new JTable(dm);
        Sp = new JScrollPane(tb);

        Sp.setBounds(30, 75, 360, 390);
        bgPanel.add(Sp);

    }
}
