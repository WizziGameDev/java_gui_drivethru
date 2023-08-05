package com.controller; // PR

import javax.swing.JOptionPane;

public interface handlingMessage {
    void messageAdd();
    void messageDelete();
    void messageChange();
}

class allMessage implements handlingMessage{

    @Override
    public void messageAdd() {
        JOptionPane.showMessageDialog(null, "Thanks Your data add", "Create Data",
                JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void messageDelete() {
        JOptionPane.showMessageDialog(null, "Data successfully deleted", "Dalete Data",
                JOptionPane.OK_OPTION);
    }

    @Override
    public void messageChange() {
        JOptionPane.showMessageDialog(null, "Thanks Your data has been change", "Change Data",
                JOptionPane.INFORMATION_MESSAGE);
    }

}

