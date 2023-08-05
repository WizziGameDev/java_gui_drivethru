package com.models;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.HashMap;

import javax.swing.*;
import com.controller.*;
import com.view.user_view;

import javax.swing.border.EmptyBorder;

public class db_user  {
    public JPanel displayPanel;
    public JScrollPane scrollPane;
    JPanel panelPesanan;
    JPanel panelTotalBayar;
    customRadius panelMenuUser = new customRadius();
    // Menyimpan data yang di klik
    private HashMap<String, Integer> orders = new HashMap<>();
    private HashMap<String, Integer> prices = new HashMap<>();

    // Set panel untuk menaruh data
    public void setTotalPesanan(JPanel panelTotalBayar) {
        this.panelTotalBayar = panelTotalBayar;
    }

    public void setPanelPesanan(JPanel panelPesanan) {
        this.panelPesanan = panelPesanan;
    }

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
                // Integer primaryId = resultSet.getInt("idMenu");
                imageControll image = new imageControll();
                image.setImage(resultSet, "imageMenu", 100, 110);
                Icon newImage = image.getImage();
                String nameMenu = resultSet.getString("nameMenu");
                String descriptionMenu = resultSet.getString("descriptionMenu");
                Integer priceMenu = resultSet.getInt("priceMenu");

                // JPanel
                panelMenuUser = new customRadius();
                panelMenuUser.setSize(250, 300);
                panelMenuUser.setRoundTopRight(50);
                panelMenuUser.setRoundBottomRight(50);
                panelMenuUser.setRoundTopLeft(50);
                panelMenuUser.setRoundBottomLeft(50);
                BoxLayout boxLayout = new BoxLayout(panelMenuUser, BoxLayout.Y_AXIS);
                panelMenuUser.setLayout(boxLayout);
                panelMenuUser.add(Box.createVerticalBox());

                // Judul
                JLabel judulLabel = new JLabel(nameMenu);
                judulLabel.setFont(new Font("Arial", Font.BOLD, 16));
                judulLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                judulLabel.setBorder(new EmptyBorder(15, 10, 0, 10));
                panelMenuUser.add(judulLabel);

                // Image
                JLabel imageLabel = new JLabel();
                imageLabel.setIcon(newImage);
                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                imageLabel.setBorder(new EmptyBorder(8, 10, 0, 10));
                panelMenuUser.add(imageLabel);

                // Description
                JLabel descriptionLabel = new JLabel(descriptionMenu);
                descriptionLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 10));
                descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                descriptionLabel.setBorder(new EmptyBorder(5, 10, 0, 10));
                panelMenuUser.add(descriptionLabel);

                // Harga
                JLabel priceLabel = new JLabel(String.valueOf(priceMenu));
                priceLabel.setFont(new Font("Arial", Font.BOLD, 15));
                priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                priceLabel.setBorder(new EmptyBorder(5, 10, 0, 10));
                panelMenuUser.add(priceLabel);

                // Button
                customRadius buttonLayout = new customRadius();
                buttonLayout.setRoundBottomRight(50);
                buttonLayout.setRoundBottomLeft(50);
                buttonLayout.setLayout(new GridBagLayout());

                layoutControl position = new layoutControl(0, 10, 5, 10);
                position.getcustomGrid();

                // button -
                buttonControl buttonMinus = new buttonControl("-", 25, "#FFFFFF", "Arial", 13, 1);
                buttonLayout.add(buttonMinus.getButton());
                position.customPosition(0, 1, 1, 1, buttonLayout, buttonMinus.getButton());

                // Label count
                JLabel countLabel = new JLabel("0");
                buttonLayout.add(countLabel);
                position.customPosition(1, 1, 1, 1, buttonLayout, countLabel);

                // Button +
                buttonControl buttonPlus = new buttonControl("+", 25, "#FFFFFF", "Arial", 13, 1);
                buttonLayout.add(buttonPlus.getButton());
                position.customPosition(2, 1, 1, 1, buttonLayout, buttonPlus.getButton());

                // Action button min
                buttonMinus.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonMinus.decrementCount(countLabel);
                        updateOrdersPanel(panelPesanan, panelTotalBayar, nameMenu,
                                Integer.parseInt(countLabel.getText()), priceMenu);
                    }
                });

                // Action button plus
                buttonPlus.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonPlus.incrementCount(countLabel);
                        updateOrdersPanel(panelPesanan, panelTotalBayar, nameMenu,
                                Integer.parseInt(countLabel.getText()), priceMenu);
                    }
                });

                panelMenuUser.add(buttonLayout);
                panelMenuUser.setPreferredSize(panelSize);
                displayPanel.add(panelMenuUser);
                displayPanel.revalidate();
                displayPanel.repaint();
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

    private int totalAmount; // Variabel untuk menyimpan jumlah total

    // Set total baru
    private void setTotal(int total) {
        totalAmount = total;
    }

    // Get total
    public int getTotal() {
        return totalAmount;
    }

    // Update pesanan
    private void updateOrdersPanel(JPanel ordersPanel, JPanel totalBayar, String nameMenu, int count, int priceMenu) {
        orders.put(nameMenu, count);
        prices.put(nameMenu, priceMenu);

        totalAmount = 0;
        // Menghapus ulang jika ada perubahan
        ordersPanel.removeAll();
        totalBayar.removeAll();
        for (String orderName : orders.keySet()) {
            int orderCount = orders.get(orderName);
            int orderPrice = prices.get(orderName);

            if (orderCount > 0) {
                JLabel orderLabel1 = new JLabel("  |  " + orderName);
                JLabel orderLabel2 = new JLabel(orderCount * orderPrice + "    x" + orderCount + " |   ");
                orderLabel1.setFont(new Font("Serif", Font.PLAIN, 15));
                orderLabel2.setFont(new Font("Serif", Font.PLAIN, 15));
                orderLabel1.setForeground(Color.WHITE);
                orderLabel2.setForeground(Color.WHITE);

                JPanel rowPanel = new JPanel();
                rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
                rowPanel.add(orderLabel1);
                rowPanel.add(Box.createHorizontalGlue());
                rowPanel.add(orderLabel2);
                rowPanel.setBackground(Color.decode("#9DB2BF"));

                ordersPanel.add(rowPanel);

                totalAmount += orderCount * orderPrice;
            }
        }
        setTotal(totalAmount);
        setLabelBayar(totalBayar, totalAmount);

        totalBayar.validate();
        totalBayar.repaint();
        ordersPanel.revalidate();
        ordersPanel.repaint();
    }

    // Melakukan pencetakan order ke .txt
    public void printOrders(String nameUser, int primaryId, int saldoUser, JFrame newPanel) {
        try {
            FileWriter fileWriter = new FileWriter("Orders.txt", true); // append is set to true
            PrintWriter writer = new PrintWriter(fileWriter);
            for (String orderName : orders.keySet()) {
                int orderCount = orders.get(orderName);
                int orderPrice = prices.get(orderName);

                if (orderCount > 0) {
                    writer.println(
                            "  |  " + orderName + "    " + orderCount * orderPrice + "    x" + orderCount + " |   ");

                }
            }
            writer.println("  -------------------------------------------   + Nama Pemesan = " + nameUser);
            writer.println("  Total: Rp " + getTotal());
            writer.close();
            JOptionPane.showMessageDialog(null, "Your order has been added", "Warning",
                    JOptionPane.INFORMATION_MESSAGE);

            int saldoBerkurang = saldoUser - getTotal();
            // Perbarui saldo
            crudControl.updateSaldo(primaryId, saldoBerkurang);

            new user_view(primaryId, nameUser, saldoBerkurang);
            newPanel.dispose();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Output bagian bawah total pembayaran
    public void setLabelBayar(JPanel panelTotalBayar, int totalBayar) {
        // Label bayar
        JLabel totalLabel = new JLabel("Total: Rp " + totalAmount);
        totalLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setBounds(10, 10, 150, 30);
        panelTotalBayar.add(totalLabel);
    }

    // Melakukan update saldo
}
