package com.foto;
import javax.swing.*;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {

        me_GUI frame = new me_GUI();
        frame.setTitle("Form v1.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}

