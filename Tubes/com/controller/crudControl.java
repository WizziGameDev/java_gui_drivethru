package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.models.db_admin;

public class crudControl {

    // Buat menu baru
    public static void createMenu(File imagePath, String productName, String productDescription, int productPrice,
            db_admin dbReload, JDialog closePop) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Mengatur koneksi ke database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/makanan", "root", "");

            // Mengeksekusi query SQL
            String query = "INSERT INTO tbl_menu (imageMenu, nameMenu, descriptionMenu, priceMenu) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(query);

            FileInputStream fileGambar = null;
            // Jika file tidak ada eksekusi berikut
            try {
                fileGambar = new FileInputStream(imagePath);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }

            // Memasukkan data ke dalam query VALUES sesuai urutan
            statement.setBinaryStream(1, fileGambar);
            statement.setString(2, productName);
            statement.setString(3, productDescription);
            statement.setInt(4, productPrice);

            int rowsInserted = statement.executeUpdate();
            // Jika data berhasil ditambahkan
            if (rowsInserted > 0) {
                dbReload.getDataReload();

                JOptionPane.showMessageDialog(null, "Thanks Your data add", "Create Data",
                        JOptionPane.INFORMATION_MESSAGE);
                closePop.dispose();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            // Menutup koneksi dan sumber daya terkait
            try {
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

    // Melakukan update barang berupa price dan description
    public static void updateMenu(int productId, String newDescription, int newProductPrice, db_admin dbReload,
            JDialog closePop) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Mengatur koneksi ke database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/makanan", "root", "");

            // Mengeksekusi query SQL
            String query = "UPDATE tbl_menu SET descriptionMenu = ?, priceMenu = ? WHERE idMenu = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, newDescription);
            statement.setInt(2, newProductPrice);
            statement.setInt(3, productId);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                dbReload.getDataReload();

                JOptionPane.showMessageDialog(null, "Thanks Your data has been change", "Change Data",
                        JOptionPane.INFORMATION_MESSAGE);
                closePop.dispose();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            // Menutup koneksi dan sumber daya terkait
            try {
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

    // Delete Menu
    public static void deleteMenu(int idMenu, db_admin dbReload) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Mengatur koneksi ke database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/makanan", "root", "");

            // Mengeksekusi query SQL
            String query = "DELETE FROM tbl_menu WHERE idMenu = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, idMenu);

            int rowsDeleted = statement.executeUpdate();
            // Ketika data dihapus lakukan pembaruan tampilan data
            if (rowsDeleted > 0) {
                dbReload.getDataReload();

                JOptionPane.showMessageDialog(null, "Data successfully deleted", "Dalete Data",
                        JOptionPane.OK_OPTION);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            // Menutup koneksi dan sumber daya terkait
            try {
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

    // Register
    // Delete Menu
    public static void registerData(String usernameUser, String genderUser, int saldoUser, String passwordUser) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Mengatur koneksi ke database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/makanan", "root", "");

            // Mengeksekusi query SQL
            String query = "INSERT INTO user_login (usernameUser, genderUser, saldoUser, passwordUser) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, usernameUser);
            statement.setString(2, genderUser);
            statement.setInt(3, saldoUser);
            statement.setString(4, passwordUser);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Thanks for the registration", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            // Menutup koneksi dan sumber daya terkait
            try {
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

    // Perbarui data saldo
    public static void updateSaldo(int userId, int saldo) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Mengatur koneksi ke database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/makanan", "root", "");

            // Mengeksekusi query SQL
            String query = "UPDATE user_login SET saldoUser = ? WHERE idUser = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, saldo);
            statement.setInt(2, userId);
            

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update berhasil");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            // Menutup koneksi dan sumber daya terkait
            try {
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
