package com.models;

import java.awt.*;
import javax.swing.*;
import com.controller.*;

public class create_view {
    public static void popUpCreate(db_admin dbReload) {
        JDialog popUpCreate = new JDialog();
        popUpCreate.setTitle("Create Food");
        popUpCreate.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        popUpCreate.setSize(400, 500);
        popUpCreate.setLocationRelativeTo(null);

        JPanel panelCreate = new JPanel();
        panelCreate.setLayout(null);
        panelCreate.setBackground(Color.decode("#DDE6ED"));

        // Judul
        JLabel judulCreate = new JLabel("CREATE DATA");
        judulCreate.setFont(new Font("Arial", Font.BOLD, 20));
        judulCreate.setBounds(120, 30, 200, 40);

        JLabel lineJudul = new JLabel("----------------------------------------------");
        lineJudul.setFont(new Font("Arial", Font.BOLD, 20));
        lineJudul.setBounds(30, 50, 400, 40);

        // Button Choose
        JButton buttonChoose = new JButton("Chooses");
        buttonChoose.setBounds(30, 120, 105, 30);

        // Image output
        JLabel imageOutput = new JLabel();
        imageOutput.setBounds(195, 95, 115, 100);

        imageControll setImage = new imageControll();
        setImage.imageChosee(buttonChoose, imageOutput);

        // Input makanan
        JLabel nameFood = new JLabel("Nama Makanan");
        nameFood.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        nameFood.setBounds(30, 210, 200, 40);

        JTextField foodInput = new JTextField();
        foodInput.setBounds(153, 215, 200, 30);

        // Input deskripsi
        JLabel descriptionLabel = new JLabel("Deskripsi");
        descriptionLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        descriptionLabel.setBounds(30, 260, 200, 40);

        JTextField descriptionInput = new JTextField();
        descriptionInput.setBounds(153, 265, 200, 30);

        // Input price
        JLabel priceLabel = new JLabel("Harga Makanan");
        priceLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        priceLabel.setBounds(30, 310, 200, 40);

        JTextField priceInput = new JTextField();
        priceInput.setBounds(153, 315, 200, 30);

        // Button Create
        buttonControl buttonCreateMenu = new buttonControl("DONE", 30, "#526D82", 225, 380, 100, 30, "Arial", 14, 2);
        buttonCreateMenu.setActionCreate(buttonCreateMenu, setImage, foodInput, descriptionInput, priceInput, dbReload, popUpCreate);

        // Button Close
        buttonControl buttonClose = new buttonControl("CLOSE", 30, "#526D82", 60, 380, 100, 30, "Arial", 14, 2);
        buttonClose.buttonClose(popUpCreate, buttonClose);

        panelCreate.add(judulCreate);
        panelCreate.add(lineJudul);
        panelCreate.add(nameFood);
        panelCreate.add(foodInput);
        panelCreate.add(buttonChoose);
        panelCreate.add(imageOutput);
        panelCreate.add(descriptionLabel);
        panelCreate.add(descriptionInput);
        panelCreate.add(priceLabel);
        panelCreate.add(priceInput);
        panelCreate.add(buttonCreateMenu.getButton());
        panelCreate.add(buttonClose.getButton());

        popUpCreate.getContentPane().add(panelCreate);
        popUpCreate.setVisible(true);
    }
}
