package com.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

import com.models.create_view;
import com.models.db_admin;
import com.models.update_view;
import com.view.admin_view;
import com.view.login_view;
import com.view.register_view;
import com.view.user_view;

class buttonLayout extends JButton {
    public int cornerRadius;
    public Color backgroundColor;
    public JButton button;

    public buttonLayout(String text, int cornerRadius, String backgroundColor, String nameFont, int sizeFont,
            int ColorNumber) {
        this.cornerRadius = cornerRadius;
        this.backgroundColor = Color.decode(backgroundColor);
        createButton(text, ColorNumber);
        button.setFont(new Font(nameFont, Font.CENTER_BASELINE, sizeFont));

    }

    public buttonLayout(String text, int cornerRadius, String backgroundColor, int x, int y, int width, int height,
            String nameFont, int sizeFont, int ColorNumber) {
        this.cornerRadius = cornerRadius;
        this.backgroundColor = Color.decode(backgroundColor);
        createButton(text, ColorNumber);
        button.setBounds(x, y, width, height);
        button.setFont(new Font(nameFont, Font.ROMAN_BASELINE, sizeFont));
    }

    public buttonLayout(String text, int cornerRadius, String backgroundColor, int x, int y, int width, int height,
            String nameFont, int sizeFont, int ColorNumber, String bold) {
        this.cornerRadius = cornerRadius;
        this.backgroundColor = Color.decode(backgroundColor);
        createButton(text, ColorNumber);
        button.setBounds(x, y, width, height);
        button.setFont(new Font(nameFont, Font.BOLD, sizeFont));
    }

    private void createButton(String text, int a) {
        button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D graphics = (Graphics2D) g.create();
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                graphics.setColor(backgroundColor);
                graphics.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

                graphics.dispose();
                super.paintComponent(g);
            }
        };
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);

        if (a == 1) {
            button.setForeground(Color.BLACK);
        } else if (a == 2) {
            button.setForeground(Color.WHITE);
        }
        button.setFocusPainted(false);
    }

    public JButton getButton() {
        return button;
    }

    public void addActionListener(ActionListener listener) {
        button.addActionListener(listener);
    }
}

// ----------------------------------------------------------------------------------------------------------------------
// Action Button
public class buttonControl extends buttonLayout {

    // No position
    public buttonControl(String text, int cornerRadius, String backgroundColor, String nameFont, int sizeFont,
            int ColorNumber) {
        super(text, cornerRadius, backgroundColor, nameFont, sizeFont, ColorNumber);
    }

    // With position
    public buttonControl(String text, int cornerRadius, String backgroundColor, int x, int y, int width, int height,
            String nameFont, int sizeFont, int ColorNumber) {
        super(text, cornerRadius, backgroundColor, x, y, width, height, nameFont, sizeFont, ColorNumber);
    }

    // Bold
    public buttonControl(String text, int cornerRadius, String backgroundColor, int x, int y, int width, int height,
            String nameFont, int sizeFont, int ColorNumber, String Bold) {
        super(text, cornerRadius, backgroundColor, x, y, width, height, nameFont, sizeFont, ColorNumber, Bold);
    }

    // Create data pop up
    public void setPopUpCreate(buttonControl x, db_admin dbReload) {
        x.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Pop up untuk melakukan/menambah data
                create_view.popUpCreate(dbReload);
            }
        });
    }

    // Update data pop up
    public void setPopUpUpdate(buttonControl x, int primaryId, db_admin dbReload) {
        x.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                update_view.popUpUpdate(primaryId, dbReload);
            }
        });
    }

    // Button Create
    public void setActionCreate(buttonControl x, imageControll file, JTextField foodInput, JTextField descriptionInput,
            JTextField priceInput, db_admin dbReload, JDialog popUpCreate) {
        x.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mengecek data kosong atau tidak, jika kosong jalankan else
                if (file.getFileImage() != null && !foodInput.getText().isEmpty() && !descriptionInput.getText().isEmpty() && !priceInput.getText().isEmpty()) {
                    crudControl.createMenu(file.getFileImage(), foodInput.getText(), descriptionInput.getText(),
                            Integer.parseInt(priceInput.getText()), dbReload, popUpCreate);
                } else {
                    JOptionPane.showMessageDialog(null, "Complete the data", "Warning", JOptionPane.CANCEL_OPTION);
                }
            }
        });
    }

    // Button Update
    public void setActionUpdate(buttonControl x, JTextField descriptionInput, JTextField priceInput, int primaryId,
            db_admin dbReload, JDialog popUpCreate) {
        x.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Periksa data jika kossong eksekusi else
                if (!descriptionInput.getText().isEmpty() && !priceInput.getText().isEmpty()) {
                    crudControl.updateMenu(primaryId, descriptionInput.getText(),
                            Integer.parseInt(priceInput.getText()), dbReload, popUpCreate);
                } else {
                    JOptionPane.showMessageDialog(null, "Complete the data", "Warning", JOptionPane.CANCEL_OPTION);
                }
            }
        });
    }

    // Delete data
    public void setActionDelete(buttonControl x, int primaryId, db_admin dbReload) {
        x.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crudControl.deleteMenu(primaryId, dbReload);
            }
        });
    }

    // Button backLogin JFrame
    public void buttonBackLogin(JFrame frameClose, buttonControl x) {
        x.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new login_view();
                frameClose.dispose();
            }
        });
    }

    // Button dispose JDialog
    public void buttonClose(JDialog jDialogClose, buttonControl x) {
        x.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jDialogClose.dispose();
            }
        });
    }

    // Button Login
    public void actionLogin(buttonControl x, JFrame frameClose, JTextField username, JTextField password) {
        x.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                Statement statement = null;
                ResultSet resultSet = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/makanan", "root", "");
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery("SELECT * FROM user_login");

                    boolean loginSuccess = false;

                    while (resultSet.next()) {
                        int primaryId = resultSet.getInt("idUser");
                        String usernameUser = resultSet.getString("usernameUser");
                        String passwordUser = resultSet.getString("passwordUser");
                        int saldoUser = resultSet.getInt("saldoUser");

                        if (username.getText().equals("admin") && password.getText().equals("1234")) {
                            new admin_view(username);
                            frameClose.dispose();
                            loginSuccess = true;
                            break;
                        } else if (username.getText().equals(usernameUser) && password.getText().equals(passwordUser)) {
                            new user_view(primaryId,usernameUser, saldoUser);
                            frameClose.dispose();
                            loginSuccess = true;
                            break;
                        }
                    }

                    if (!loginSuccess) {
                        System.out.println("Login Gagal");
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
        });
    }

    // Button panelSignup
    public void buttonPanelSignUp(JFrame frameClose, buttonControl x) {
        x.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new register_view();
                frameClose.dispose();
            }
        });
    }

    // Button actionSignUp
    public void buttonActionSignUp(buttonControl x, JFrame frameClose, JTextField usernameUser, JComboBox genderUser,
            JTextField saldoUser, JTextField passwordUser) {
        x.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crudControl.registerData(usernameUser.getText(), genderUser.getSelectedItem().toString(), Integer.parseInt(saldoUser.getText()), passwordUser.getText());
                new login_view();
                frameClose.dispose();
            }
        });
    }

    // + and -
    public void incrementCount(JLabel countLabel) {
        int count = Integer.parseInt(countLabel.getText());
        count++;
        countLabel.setText(String.valueOf(count));
    }

    public void decrementCount(JLabel countLabel) {
        int count = Integer.parseInt(countLabel.getText());
        if (count > 0) {
            count--;
            countLabel.setText(String.valueOf(count));
        }
    }
}