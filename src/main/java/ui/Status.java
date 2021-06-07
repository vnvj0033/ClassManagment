package ui;

import javax.swing.*;

public class Status {
    StatusBar statusBar;

    public Status(StatusBar label) {
        this.statusBar = label;
    }

    public void addText(JTextField textField, String info) {
        statusBar.setInfo(textField, info);
    }
}
