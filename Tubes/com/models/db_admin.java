package com.models;

// import bahan yang dibuat
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import com.controller.*;
import javax.swing.border.EmptyBorder;

public class db_admin {
    // Enkapsulasi
    private JPanel displayPanel;
    private JScrollPane scrollPane;
    customRadius panelMenu = new customRadius();

    // set
    public void setDisplayPanel(JPanel displayPanel) {
        this.displayPanel = displayPanel;
        this.displayPanel.setLayout(new GridLayout(0, 4, 15, 15));
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    // get
    public JPanel getDisplayPanel() {
        return displayPanel;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    // Reload data
    public void getDataReload() {
        this.showDataSql();
        this.getDisplayPanel().revalidate();
        this.getDisplayPanel().repaint();
        this.getScrollPane().revalidate();
        this.getScrollPane().repaint();
    }

    public void showDataSql() {
        // Koneksi kosong
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Melakukan connection database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/makanan", "root", "");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tbl_menu");

            // Menghapus semua data yang ada di tampilan displayPanel
            displayPanel.removeAll();
            displayPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Menambahkan margin pada displayPanel

            Dimension panelSize = new Dimension(200, 250);

            int panelCount = 0;
            while (resultSet.next()) {
                // Inisiasi variabel
                Integer primaryId = resultSet.getInt("idMenu");
                imageControll image = new imageControll();
                image.setImage(resultSet, "imageMenu", 100, 110);
                Icon newImage = image.getImage();
                String nameMenu = resultSet.getString("nameMenu");
                String descriptionMenu = resultSet.getString("descriptionMenu");
                Integer priceMenu = resultSet.getInt("priceMenu");

                // JPanel
                panelMenu = new customRadius();
                panelMenu.setSize(250, 300);
                panelMenu.setRoundTopRight(50);
                panelMenu.setRoundBottomRight(50);
                panelMenu.setRoundTopLeft(50);
                panelMenu.setRoundBottomLeft(50);
                BoxLayout boxLayout = new BoxLayout(panelMenu, BoxLayout.Y_AXIS);
                panelMenu.setLayout(boxLayout);
                panelMenu.add(Box.createVerticalBox());

                // Judul
                JLabel judulLabel = new JLabel(nameMenu);
                judulLabel.setFont(new Font("Arial", Font.BOLD, 16));
                judulLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                judulLabel.setBorder(new EmptyBorder(15, 10, 0, 10));
                panelMenu.add(judulLabel);

                // Image
                JLabel imageLabel = new JLabel();
                imageLabel.setIcon(newImage);
                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                imageLabel.setBorder(new EmptyBorder(8, 10, 0, 10));
                panelMenu.add(imageLabel);

                // Description
                JLabel descriptionLabel = new JLabel(descriptionMenu);
                descriptionLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 10));
                descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                descriptionLabel.setBorder(new EmptyBorder(5, 10, 0, 10));
                panelMenu.add(descriptionLabel);

                // Harga
                JLabel priceLabel = new JLabel(String.valueOf(priceMenu));
                priceLabel.setFont(new Font("Arial", Font.BOLD, 15));
                priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                priceLabel.setBorder(new EmptyBorder(10, 10, 0, 10));
                panelMenu.add(priceLabel);

                // Button
                customRadius buttonLayout = new customRadius();
                buttonLayout.setRoundBottomRight(50);
                buttonLayout.setRoundBottomLeft(50);
                buttonLayout.setLayout(new GridBagLayout());

                layoutControl position = new layoutControl(0, 10, 5, 10);
                position.getcustomGrid();
                
                // Button Update
                buttonControl Update = new buttonControl("UPDATE", 30, "#FFFFFF", "Arial",
                        10, 1);
                buttonLayout.add(Update.getButton());
                position.customPosition(0, 4, 3, 1, buttonLayout, Update.getButton());
                Update.setPopUpUpdate(Update, primaryId, this);

                // Button Delete
                buttonControl Delete = new buttonControl("DELETE", 30, "#FFFFFF", "Arial",
                        10, 1);
                buttonLayout.add(Delete.getButton());
                position.customPosition(3, 4, 3, 1, buttonLayout, Delete.getButton());
                Delete.setActionDelete(Delete, primaryId, this);

                panelMenu.add(buttonLayout);
                panelMenu.setPreferredSize(panelSize);
                displayPanel.add(panelMenu);

                panelCount++;
            }

            // Jika panel lebih dari 4 akan dihilangkan panel kosong dan mengatur ke panel
            // bawahnya
            int totalColumns = 1;
            int remainingPanels = totalColumns - (panelCount % totalColumns);
            for (int i = 0; i < remainingPanels; i++) {
                JPanel emptyPanel = new JPanel();
                emptyPanel.setVisible(false);
                displayPanel.add(emptyPanel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Koneksi gagal");

        } finally {
            // Close all
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
