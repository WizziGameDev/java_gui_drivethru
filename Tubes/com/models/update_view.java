package com.models;

import java.awt.*;
import javax.swing.*;
import com.controller.buttonControl;

public class update_view {
    public static void popUpUpdate(int primaryId, db_admin dbReload) {
        JDialog popUpUpdate = new JDialog();
        popUpUpdate.setTitle("Create Food");
        popUpUpdate.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        popUpUpdate.setSize(400, 350);
        popUpUpdate.setLocationRelativeTo(null);

        JPanel panelUpdate = new JPanel();
        panelUpdate.setLayout(null);
        panelUpdate.setBackground(Color.decode("#DDE6ED"));

        // Judul
        JLabel judulUpdate = new JLabel("UPDATE DATA");
        judulUpdate.setFont(new Font("Arial", Font.BOLD, 20));
        judulUpdate.setBounds(120, 30, 200, 40);

        JLabel lineJudul = new JLabel("----------------------------------------------");
        lineJudul.setFont(new Font("Arial", Font.BOLD, 20));
        lineJudul.setBounds(30, 50, 400, 40);

        // Input deskripsi
        JLabel descriptionLabel = new JLabel("Deskripsi");
        descriptionLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        descriptionLabel.setBounds(30, 100, 200, 40);

        JTextField descriptionInput = new JTextField();
        descriptionInput.setBounds(153, 105, 200, 30);

        // Input price
        JLabel priceLabel = new JLabel("Harga Makanan");
        priceLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
        priceLabel.setBounds(30, 150, 200, 40);

        JTextField priceInput = new JTextField();
        priceInput.setBounds(153, 155, 200, 30);

        // Button Update
        buttonControl buttonUpdateMenu = new buttonControl("DONE", 30, "#526D82", 225, 230, 100, 30, "Arial", 14, 2);
        buttonUpdateMenu.setActionUpdate(buttonUpdateMenu, descriptionInput, priceInput, primaryId, dbReload,
                popUpUpdate);

        // Button Close
        buttonControl buttonClose = new buttonControl("CLOSE", 30, "#526D82", 60, 230, 100, 30, "Arial", 14, 2);
        buttonClose.buttonClose(popUpUpdate, buttonClose);

        panelUpdate.add(judulUpdate);
        panelUpdate.add(lineJudul);
        panelUpdate.add(descriptionLabel);
        panelUpdate.add(descriptionInput);
        panelUpdate.add(priceLabel);
        panelUpdate.add(priceInput);
        panelUpdate.add(buttonUpdateMenu.getButton());
        panelUpdate.add(buttonClose.getButton());

        popUpUpdate.getContentPane().add(panelUpdate);
        popUpUpdate.setVisible(true);

    }
}
