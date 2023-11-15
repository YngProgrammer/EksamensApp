package Main;

import javax.swing.SwingUtilities;


import View.MainView;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView mainView = new MainView();
            mainView.MainScreen();
        });
    }
}

