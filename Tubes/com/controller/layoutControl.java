package com.controller;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class layoutControl {
    GridBagConstraints customGrid = new GridBagConstraints();

    public layoutControl(int top, int left, int buttom, int right) {
        customGrid.insets = new Insets(top, left, buttom, right);
    }

    public GridBagConstraints getcustomGrid() {
        return customGrid;
    }

    public void customPosition(int x, int y, int width, int height, JPanel frameBorder, JButton inputLabel) {
        customGrid.gridx = x;
        customGrid.gridy = y;
        customGrid.gridwidth = width;
        customGrid.gridheight = height;
        frameBorder.add(inputLabel, customGrid);
    }

    public void customPosition(int x, int y, int width, int height, JPanel frameBorder, JLabel countLabel) {
        customGrid.gridx = x;
        customGrid.gridy = y;
        customGrid.gridwidth = width;
        customGrid.gridheight = height;
        frameBorder.add(countLabel, customGrid);
    }
}
