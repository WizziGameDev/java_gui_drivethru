package com.view;

import java.awt.*;
import javax.swing.*;
import com.models.db_admin;
import com.controller.buttonControl;

public class admin_view extends JFrame {
    private Container container = new Container();
    private JPanel panelPembungkus = new JPanel();
    public JPanel panelMenu = new JPanel();
    public JScrollPane scrollPane = new JScrollPane(panelMenu);

    public admin_view(JTextField username) {
        super("Admin View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setResizable(false);

        panelPembungkus.setSize(1200, 750);
        panelPembungkus.setLayout(null);
        panelPembungkus.setBackground(Color.decode("#526D82"));

        // Icon image
        ImageIcon icon = new ImageIcon("F:\\Tubes\\com\\icon\\account.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);

        JLabel putImage = new JLabel(icon);
        putImage.setBounds(20, 20, 30, 30);

        // Username
        JLabel userName = new JLabel(username.getText());
        userName.setForeground(Color.WHITE);
        userName.setFont(new Font("Arial", Font.BOLD, 14));
        userName.setBounds(60, 10, 270, 50);

        // Judul
        JLabel judulProject = new JLabel("MANAGEMENT DRIVE THRU");
        judulProject.setForeground(Color.WHITE);
        judulProject.setFont(new Font("Arial", Font.BOLD, 20));
        judulProject.setBounds(460, 30, 270, 50);

        // Panel Menu + scroll
        panelMenu.setLayout(new FlowLayout());
        panelMenu.setBackground(Color.decode("#9DB2BF"));

        panelMenu.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(91, 100, 1000, 450);

        // Button
        buttonControl Create = new buttonControl("CREATE", 30, "#DDE6ED", 870, 610,
                100, 40, "Arial", 16, 1);
        buttonControl Exit = new buttonControl("EXIT", 30, "#DDE6ED", 990, 610, 100,
                40, "Arial", 16, 1);

        // Menampilkan data menu dari database
        db_admin menu = new db_admin();
        menu.setDisplayPanel(panelMenu);
        menu.setScrollPane(scrollPane);
        menu.showDataSql();

        // Button controll Create
        Create.setPopUpCreate(Create, menu);

        // Button controll back login
        Exit.buttonBackLogin(this, Exit);

        // Panel pembungkus / penampng
        panelPembungkus.add(putImage);
        panelPembungkus.add(userName);
        panelPembungkus.add(judulProject);
        panelPembungkus.add(scrollPane);
        panelPembungkus.add(Create.getButton());
        panelPembungkus.add(Exit.getButton());

        // Container
        container = getContentPane();
        container.add(panelPembungkus);
        setVisible(true);
    }
}
