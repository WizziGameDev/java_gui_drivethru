package com.view;

import java.awt.*;
import javax.swing.*;

import com.controller.buttonControl;

public class register_view extends JFrame {
    Container container = new Container();
    JPanel panelIcon = new JPanel(new GridLayout());
    JPanel panelForm = new JPanel();
    JPanel panelButton = new JPanel();

    public register_view() {
        super("REGISTER");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Panel Image
        panelIcon.setPreferredSize(new Dimension(450, 50));
        panelIcon.setBackground(Color.decode("#526D82"));

        // Icon image
        ImageIcon icon = new ImageIcon("F:\\Tubes\\com\\icon\\add-user.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newImage);

        JLabel label = new JLabel(icon);
        panelIcon.add(label);

        // Panel Form
        panelForm.setPreferredSize(new Dimension(450, 120));
        panelForm.setBackground(Color.decode("#526D82"));
        panelForm.setLayout(null);

        // Input Username
        JLabel labelUsername = new JLabel("Username");
        labelUsername.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        labelUsername.setBounds(65, 10, 200, 40);
        labelUsername.setForeground(Color.WHITE);

        JTextField inputUsername = new JTextField();
        inputUsername.setBounds(190, 15, 170, 30);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Choose...");
        comboBox.addItem("Man");
        comboBox.addItem("Woman");

        // Label combobox
        JLabel labelChoose = new JLabel("Gender");
        labelChoose.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        labelChoose.setBounds(65, 60, 200, 40);
        labelChoose.setForeground(Color.WHITE);

        // Atur lebar dan tinggi ComboBox
        comboBox.setPreferredSize(new Dimension(170, 30));
        comboBox.setBounds(190, 65, 170, 30);

        // Input Salda
        JLabel labelSaldo = new JLabel("Balance");
        labelSaldo.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        labelSaldo.setBounds(65, 110, 200, 40);
        labelSaldo.setForeground(Color.WHITE);

        JTextField inputSaldo = new JTextField();
        inputSaldo.setBounds(190, 115, 170, 30);

        // Input Password
        JLabel labelPassword = new JLabel("Password");
        labelPassword.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        labelPassword.setBounds(65, 160, 200, 40);
        labelPassword.setForeground(Color.WHITE);

        JTextField inputPassword = new JTextField();
        inputPassword.setBounds(190, 165, 170, 30);

        // Menampilkan data
        panelForm.add(labelUsername);
        panelForm.add(inputUsername);
        panelForm.add(labelChoose);
        panelForm.add(comboBox);
        panelForm.add(labelSaldo);
        panelForm.add(inputSaldo);
        panelForm.add(labelPassword);
        panelForm.add(inputPassword);

        // Panel Button
        panelButton.setPreferredSize(new Dimension(450, 50));
        panelButton.setBackground(Color.decode("#526D82"));
        panelButton.setLayout(null);

        // Button setAll
        buttonControl btnCancel = new buttonControl("CANCEL", 30, "#FFFFFF", 75, 45, 110, 33, "Arial", 13, 1, "Bold");
        buttonControl btnSignUp = new buttonControl("SIGN UP", 30, "#FFFFFF", 250, 45, 110, 33, "Arial", 13, 1, "Bold");

        // Button action
        btnCancel.buttonBackLogin(this, btnCancel);
        btnSignUp.buttonActionSignUp(btnSignUp, this, inputUsername, comboBox, inputSaldo, inputPassword);

        // Menampilakan button
        panelButton.add(btnCancel.getButton());
        panelButton.add(btnSignUp.getButton());

        container = getContentPane();
        container.add(panelIcon);
        container.add(panelForm);
        container.add(panelButton);
        setVisible(true);
    }
}