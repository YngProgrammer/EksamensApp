package com.examapp;

import javax.swing.SwingUtilities;


import com.examapp.views.MainView;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView mainView = new MainView();
            mainView.MainScreen();
        });
    }
}

