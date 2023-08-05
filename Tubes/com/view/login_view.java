package com.view;

import java.awt.*;
import javax.swing.*;
import com.controller.buttonControl;

public class login_view extends JFrame {
    private Container container = new Container();
    private JPanel iconPanel = new JPanel(new GridLayout());
    private JPanel formLogin = new JPanel();
    private JPanel buttonAction = new JPanel();

    public login_view() {
        super("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 430);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // JPanel Icon
        iconPanel.setPreferredSize(new Dimension(450, 95));
        iconPanel.setBackground(Color.decode("#526D82"));

        // Icon image
        ImageIcon icon = new ImageIcon("F:\\Tubes\\com\\icon\\login.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(110, 110, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);

        JLabel label = new JLabel(icon);
        iconPanel.add(label);

        // JPanel form
        formLogin.setPreferredSize(new Dimension(450, 70));
        formLogin.setBackground(Color.decode("#526D82"));
        formLogin.setLayout(null);

        // Input Username
        JLabel labelUsername = new JLabel("Username");
        labelUsername.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        labelUsername.setBounds(80, 1, 200, 40);
        labelUsername.setForeground(Color.WHITE);

        JTextField inputUsername = new JTextField();
        inputUsername.setBounds(180, 6, 170, 30);

        // Input Password
        JLabel labelPassword = new JLabel("Password");
        labelPassword.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        labelPassword.setBounds(80, 60, 200, 40);
        labelPassword.setForeground(Color.WHITE);

        JTextField inputPassword = new JTextField();
        inputPassword.setBounds(180, 65, 170, 30);

        // Menampilkan component
        formLogin.add(labelUsername);
        formLogin.add(inputUsername);
        formLogin.add(labelPassword);
        formLogin.add(inputPassword);

        // JPanel button
        buttonAction.setPreferredSize(new Dimension(450, 25));
        buttonAction.setBackground(Color.decode("#526D82"));
        buttonAction.setLayout(null);

        // Button action
        buttonControl btnLogin = new buttonControl("LOGIN", 30, "#FFFFFF", 250, 10, 110,
                33, "Arial", 13, 1, "Bold");
        buttonControl btnSignUp = new buttonControl("SIGN UP", 30, "#FFFFFF", 75, 10, 110,
                33, "Arial", 13, 1, "Bold");

        btnSignUp.buttonPanelSignUp(this, btnSignUp);
        btnLogin.actionLogin(btnLogin, this, inputUsername, inputPassword);

        // Menampilkan button
        buttonAction.add(btnLogin.getButton());
        buttonAction.add(btnSignUp.getButton());

        container = getContentPane();
        container.add(iconPanel);
        container.add(formLogin);
        container.add(buttonAction);
        setVisible(true);
    }
}
