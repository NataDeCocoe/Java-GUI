package ActionPerformed;

import MainFrame.LoginFrame;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static MainFrame.LoginFrame.MFrame;
import static MainFrame.LoginFrame.Uname;

public class DashBoard {
    JPanel DPanel, bgPanel;
    JLabel Header;
    JButton AddProd, EditProd, DeleteProd, ViewProd, Logout;

    public DashBoard(){

        DPanel = new JPanel();
        DPanel.setBounds(0, 0, 500, 600);
        DPanel.setLayout(null);
        MFrame.add(DPanel);

        bgPanel = new JPanel();
        bgPanel.setBounds(30, 30, 420, 500);
        bgPanel.setBackground(new Color(255, 255, 255));
        bgPanel.setLayout(null);
        DPanel.add(bgPanel);

        Header = new JLabel("Hello, "+ Uname + "!");
        Header.setBounds(25,35,300,39);
        Header.setFont(new Font("Inter", Font.BOLD, 30));
        bgPanel.add(Header);

        AddProd = new JButton("Add Product");
        AddProd.setBounds(155,171,120,25);
        bgPanel.add(AddProd);
        AddProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddProdPanel();
                bgPanel.setVisible(false);
                DPanel.setVisible(false);
            }
        });

        EditProd = new JButton("Edit Product");
        EditProd.setBounds(155,214,120,25);
        bgPanel.add(EditProd);
        EditProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditProdPanel();
                bgPanel.setVisible(false);
                DPanel.setVisible(false);
            }
        });

        DeleteProd = new JButton("Delete Product");
        DeleteProd.setBounds(155,257,120,25);
        bgPanel.add(DeleteProd);
        DeleteProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteProdPanel();
                bgPanel.setVisible(false);
                DPanel.setVisible(false);
            }
        });

        ViewProd = new JButton("View Product");
        ViewProd.setBounds(155,300,120,25);
        bgPanel.add(ViewProd);
        ViewProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewProdPanel();
                bgPanel.setVisible(false);
                DPanel.setVisible(false);
            }
        });

        Logout = new JButton("LOGOUT");
        Logout.setBounds(320,40,85,21);
        Logout.setFont(new Font("Inter", Font.BOLD,12));
        bgPanel.add(Logout);
        Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFrame();
                bgPanel.setVisible(false);
                DPanel.setVisible(false);
            }
        });
    }
}