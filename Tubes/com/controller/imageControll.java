package com.controller;

import java.sql.*;
import java.awt.*;
import java.io.File;
import javax.swing.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

public class imageControll {

    // Menampung image
    File fileInput;

    // Chooese image
    public void imageChosee(JButton buttonImage, JLabel imageOutput) {
        buttonImage.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(
                    new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg"));

            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                fileInput = fileChooser.getSelectedFile();

                // Buat ImageIcon dari file
                ImageIcon icon = new ImageIcon(fileInput.getPath());

                // Skalakan ImageIcon agar sesuai dengan ukuran JLabel
                Image scaledImage = icon.getImage().getScaledInstance(imageOutput.getWidth(), imageOutput.getHeight(),
                        Image.SCALE_SMOOTH);

                // Set ikon JLabel ke gambar yang diskalakan
                imageOutput.setIcon(new ImageIcon(scaledImage));
            }
        });
    }

    // Mengambil data image dari komputer
    public File getFileImage() {
        return fileInput;
    }

    // Menampung image
    ImageIcon imageIcon = null;

    // Show image
    public void setImage(ResultSet resultSet, String query, int width, int height) throws SQLException {
        byte[] imageData = resultSet.getBytes(query);
        if (imageData != null) {
            // Konversi data gambar menjadi ImageIcon
            try {
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageData));

                // Mengatur ukuran image
                Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(scaledImage);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Mengambil data image dari database
    public ImageIcon getImage() {
        return imageIcon;
    }
}
