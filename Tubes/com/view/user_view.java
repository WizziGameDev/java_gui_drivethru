package com.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.controller.buttonControl;
import com.models.db_user;

public class user_view extends JFrame {
    Container container;
    JPanel panelMenu = new JPanel();
    JScrollPane scrollPane = new JScrollPane();
    JPanel panelPesanan = new JPanel();
    JPanel panelJudulPesanan = new JPanel();
    JPanel panelOutputPesanan = new JPanel();
    JPanel panelOutputTotal = new JPanel();

    public user_view(int primaryId, String usernameUser, int saldoUser) {
        super("DRIVE THRU");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1350, 765);
        setLocationRelativeTo(null);
        setResizable(false);

        // Icon image
        ImageIcon icon = new ImageIcon("F:\\Tubes\\com\\icon\\account.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newImage);

        // Image user_view
        JLabel putImage = new JLabel(icon);
        putImage.setBounds(20, 20, 30, 30);

        // user_viewname
        JLabel userName = new JLabel(usernameUser);
        userName.setForeground(Color.WHITE);
        userName.setFont(new Font("Arial", Font.BOLD, 14));
        userName.setBounds(60, 10, 270, 50);

        // Judul
        JLabel judulProject = new JLabel("ORDER YOUR FOOD");
        judulProject.setForeground(Color.WHITE);
        judulProject.setFont(new Font("Arial", Font.BOLD, 20));
        judulProject.setBounds(570, 30, 270, 50);

        // Panel Menu + scroll
        panelMenu.setLayout(new FlowLayout());
        panelMenu.setBackground(Color.decode("#9DB2BF"));
        panelMenu.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(25, 100, 950, 450);

        // Call database
        db_user userMenu = new db_user();
        userMenu.setDisplayPanel(panelMenu);
        userMenu.setScrollPane(scrollPane);
        userMenu.setTotalPesanan(panelOutputTotal);
        userMenu.setPanelPesanan(panelOutputPesanan);
        scrollPane.setViewportView(panelMenu);
        userMenu.showDataSql();

        // Label balance
        JLabel labelSaldo = new JLabel("Saldo : Rp " + saldoUser + ",00");
        labelSaldo.setBounds(25, 568, 200, 20);
        labelSaldo.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        labelSaldo.setForeground(Color.WHITE);

        // JPanel Pesanan
        panelPesanan.setBounds(1000, 100, 310, 450);
        panelPesanan.setBackground(Color.decode("#9DB2BF"));
        panelPesanan.setLayout(null);

        // Panel judul pesanan
        panelJudulPesanan.setBounds(0, 0, 310, 75);
        panelJudulPesanan.setBackground(Color.decode("#9DB2BF"));
        panelJudulPesanan.setLayout(null);

        // Label judul
        JLabel pesananUser = new JLabel("PESANAN");
        pesananUser.setBounds(110, 25, 200, 20);
        pesananUser.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
        pesananUser.setForeground(Color.WHITE);

        // Line pemisah
        JLabel linePesanan = new JLabel("-----------------------------------------");
        linePesanan.setBounds(10, 43, 290, 20);
        linePesanan.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
        linePesanan.setForeground(Color.WHITE);

        panelJudulPesanan.add(pesananUser);
        panelJudulPesanan.add(linePesanan);

        // Panel output pesanan
        BoxLayout boxLayout = new BoxLayout(panelOutputPesanan, BoxLayout.Y_AXIS);
        panelOutputPesanan.setLayout(boxLayout);
        panelOutputPesanan.setBounds(0, 76, 310, 300);
        panelOutputPesanan.setBackground(Color.decode("#9DB2BF"));

        // Panel total
        panelOutputTotal.setBounds(0, 390, 310, 50);
        panelOutputTotal.setBackground(Color.decode("#9DB2BF"));
        panelOutputTotal.setLayout(null);

        // Component panel pesanan
        panelPesanan.add(panelJudulPesanan);
        panelPesanan.add(panelOutputPesanan);
        panelPesanan.add(panelOutputTotal);

        // Button action order and exit
        buttonControl Order = new buttonControl("ORDER", 30, "#DDE6ED", 1070, 610, 100,
                40, "Arial", 16, 1);
        buttonControl Exit = new buttonControl("EXIT", 30, "#DDE6ED", 1200, 610, 100,
                40, "Arial", 16, 1);

        // Button untuk mencetak pesanan ke bentuk file
        Order.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userMenu.printOrders(usernameUser, primaryId, saldoUser, user_view.this);
            }
        });

        Exit.buttonBackLogin(this, Exit);

        // Container
        container = getContentPane();
        container.setLayout(null);
        container.setBackground(Color.decode("#526D82"));

        container.add(putImage);
        container.add(userName);
        container.add(judulProject);
        container.add(scrollPane);
        container.add(labelSaldo);
        container.add(panelPesanan);
        container.add(Order.getButton());
        container.add(Exit.getButton());
        setVisible(true);
    }
}
