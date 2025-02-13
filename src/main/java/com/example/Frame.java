package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    String apiKey = Config.getApiKey();


    String usernamesearch;
    public Frame(){
        setTitle("Instagram Scraper");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel panelMain = new JPanel();
        panelMain.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        panelMain.setBackground(Color.BLACK);
        panelMain.setVisible(true);

        JTextField textFieldSearch = new JTextField(15);
        textFieldSearch.setVisible(true);

        JButton buttonSearch = new JButton("Search and Download");
        buttonSearch.setBackground(Color.WHITE);
        buttonSearch.setFocusable(false);
        buttonSearch.setVisible(true);



        panelMain.add(textFieldSearch, gbc);

        gbc.gridy++;

        panelMain.add(buttonSearch, gbc);


        add(panelMain);

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernamesearch = textFieldSearch.getText().trim();
                if (!usernamesearch.isEmpty()){
                    App.searchbyusernameanddownload(usernamesearch, apiKey);
                    textFieldSearch.setText("");
                }else {
                    JOptionPane.showMessageDialog(null, "Lütfen bir kullanıcı adı girin!");
                }
            }
        });

        setVisible(true);


    }
}
