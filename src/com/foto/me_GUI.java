package com.foto;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;


public class me_GUI extends JFrame{

    public Icon icon;
    private JLabel label;
    private JSlider red_slider;
    private JSlider green_slider;
    private JSlider blue_slider;
    private JSlider light_slider;

    private JSlider size_slider;

    private JColorChooser jColorChooser = new JColorChooser();

    private JCheckBox pen = new JCheckBox("Draft");
    private JRadioButton dots = new JRadioButton("Чорно белий");
    private JRadioButton dots2 = new JRadioButton("Жовтий");
    private JRadioButton dots3 = new JRadioButton("Зелений");
    private JRadioButton dots4 = new JRadioButton("Синій");
    private JRadioButton dots5 = new JRadioButton("Орігінал");
    private changeImage change = new changeImage();
    private BufferedImage result;
    private BufferedImage image;

    me_GUI(){


        this.setBounds(0,0,800,500);
        label = new JLabel();
//        label = new JLabel(new ImageIcon(myPicture));
//        JPanel northPanel = new JPanel();
//        JPanel westPanel= new JPanel();
        JPanel eastPanel = new JPanel();




        JButton button2 = new JButton("File");

        ButtonGroup gryp = new ButtonGroup();
        gryp.add(dots);
        gryp.add(dots2);
        gryp.add(dots3);
        gryp.add(dots4);
        gryp.add(dots5);


        Container westBox = Box.createVerticalBox();
        westBox.add(dots5);
        westBox.add(dots);
        westBox.add(dots2);
        westBox.add(dots3);
        westBox.add(dots4);



        dots5.setSelected(true);



        size_slider = new JSlider(1,2,200,100);


        size_slider.setMinorTickSpacing(10);
        size_slider.setPaintTicks(true);






        red_slider = new JSlider(2,200,100);


        red_slider.setMinorTickSpacing(10);
        red_slider.setPaintTicks(true);


        green_slider = new JSlider(2,200,100);


        green_slider.setMinorTickSpacing(10);
        green_slider.setPaintTicks(true);


        blue_slider = new JSlider(2,200,100);


        blue_slider.setMinorTickSpacing(10);
        blue_slider.setPaintTicks(true);

        light_slider = new JSlider(2,200,99);
        light_slider.setMinorTickSpacing(10);
        light_slider.setPaintTicks(true);

//        westPanel.add(label);

        JLabel red_label = new JLabel("Красний");
        westBox.add(red_label);
        westBox.add(red_slider);
        JLabel green_label = new JLabel("Зелений");
        westBox.add(green_label);
        westBox.add(green_slider);
        JLabel blue_label = new JLabel("Синій");
        westBox.add(blue_label);
        westBox.add(blue_slider);
        JLabel light_label = new JLabel("Свет");
        westBox.add(light_label);
        westBox.add(light_slider);
        westBox.add(jColorChooser);
        westBox.add(pen);
////////////////////////////////////////////////////////////////////////////////////////////////////
//Draft white background

        image = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();
        result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g1 = result.createGraphics();
        g1.setColor(Color.WHITE);
        g1.fillRect(0, 0, result.getWidth(), result.getHeight());
        g1.dispose();
        icon = new ImageIcon(result);
        label.setIcon(icon);
//////////////////////////////////////////////////////////////////////////////////////////////////////

        eastPanel.add(westBox);
 //////////////////////////////////////////////////////////////////////////////////////////////////////
//        Draft in image
        MouseMotionListener mouse = new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(pen.isSelected()){
                Color newColor = jColorChooser.getColor();
                for (int x = - 3; x < 4;x++){
                    for (int y = -3; y < 4;y++){
                      int  X = e.getX() + x;
                      int Y = e.getY() + y;
                      if(X < 0)X = 1;
                      if(X > result.getHeight()) X = result.getHeight() - 4;

                        if(Y < 0)Y = 1;
                        if(Y > result.getWidth()) Y = result.getWidth() - 4;
                result.setRGB(X,Y,newColor.getRGB());
                    }
                }
                icon = new ImageIcon(result);
                label.setIcon(icon);
                revalidate();
                repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        };
 /////////////////////////////////////////////////////////////////////////////////////

        label.addMouseMotionListener(mouse);

        JScrollPane scroll = new JScrollPane(label);


        JButton button = new JButton("press");
        add(button, BorderLayout.SOUTH);
        add(eastPanel, BorderLayout.EAST);
        add(scroll, BorderLayout.CENTER);
        add(button2 , BorderLayout.NORTH);
        add(size_slider, BorderLayout.WEST);

        //  container.add(check);

        button.addActionListener(new ButtonEventListener());
        button2.addActionListener(new Button2EventListener());
        size_slider.addChangeListener(new ChannerLIstenerSize());
        light_slider.addChangeListener(new ChannerLIstener());
        red_slider.addChangeListener(new ChannerLIstener());
        green_slider.addChangeListener(new ChannerLIstener());
        blue_slider.addChangeListener(new ChannerLIstener());

    }




    class ChannerLIstenerSize implements ChangeListener{
        @Override
        public void stateChanged(ChangeEvent e) {

            icon = new ImageIcon(changeImage.resize(result,(int) (image.getHeight() * (size_slider.getValue() / 100.)),
                    (int) (image.getWidth() * (size_slider.getValue() / 100.))));

            label.setIcon(icon);
            revalidate();
            repaint();
        }

    }

    //управленіє опзунками
    class ChannerLIstener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {

                image();
        }


        public void image() {

            change.changeImage(image,result,
                    (double)red_slider.getValue()/100. * light_slider.getValue()/100.,
                    (double)green_slider.getValue()/100.* light_slider.getValue()/100.,
                    (double)blue_slider.getValue()/100. * light_slider.getValue()/100.);

            icon = new ImageIcon(result);
            label.setIcon(icon);
            revalidate();
            repaint();
        }
    }

    //Ізмененія по шаблонам

    class ButtonEventListener implements ActionListener {

        public void actionPerformed(ActionEvent e){

            try {
                image();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Ми не можем открить файл",
                        "Ошибка",
                        JOptionPane.PLAIN_MESSAGE);
            }

        }


        public void image () throws IOException{

            if(dots.isSelected())
                    {
                        change.changeImage_grey( image,result);
                    }
            if(dots2.isSelected()) {
                change.changeImage(image,result,1,0.7,0.1);
                red_slider.setValue(100);
                green_slider.setValue(70);
                blue_slider.setValue(10);
                light_slider.setValue(100);
            }
            if(dots3.isSelected()) {
                change.changeImage(image,result,0.299,0.9,0.114);
                red_slider.setValue(29 );
                green_slider.setValue(90);
                blue_slider.setValue(11);
                light_slider.setValue(100);
            }
            if(dots4.isSelected()) {
                change.changeImage(image,result,0.3,0.3,0.9);
                red_slider.setValue(30);
                green_slider.setValue(30);
                blue_slider.setValue(90);
                light_slider.setValue(100);
            }
            if(dots5.isSelected()) {
                change.changeImage(image,result,1,1,1);
                red_slider.setValue(100);
                green_slider.setValue(100);
                blue_slider.setValue(100);
                light_slider.setValue(100);
            }

            File output = new File("result.jpg");
            ImageIO.write(result, "jpg", output);

            icon = new ImageIcon(result);

            label.setIcon(icon);
            revalidate();
            repaint();
        }
    }




    //Откритіє файла

    class Button2EventListener implements ActionListener {

        public void actionPerformed(ActionEvent e){

            JFileChooser file_chooser = new  JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter (
                    "JPG & GIF Images", "jpg", "gif");

            file_chooser.setFileFilter(filter);
            file_chooser.showOpenDialog(null);
            String ss = file_chooser.getSelectedFile().getAbsolutePath();
            File file = new File(ss);
            try {

                image= ImageIO.read(file);
                result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
                change.changeImage(image,result, 1,1,1);
                icon = new ImageIcon(result);
                label.setIcon(icon);
                revalidate();
                repaint();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Ми не можем открить файл",
                        "Ошибка",
                        JOptionPane.PLAIN_MESSAGE);
            }


        }
    }
}

