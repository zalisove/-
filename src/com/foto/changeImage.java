package com.foto;

import java.awt.*;
import java.awt.image.BufferedImage;

public class changeImage {

    public  void changeImage(BufferedImage image, BufferedImage result, double red_cof,double green_cof,double blue_cof){

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                Color color = new Color(image.getRGB(x, y));
                int blue = color.getBlue();
                int red = color.getRed();
                int green = color.getGreen();
                Color newColor =  Color_fon(red,green,blue,red_cof, green_cof, blue_cof);
                result.setRGB(x, y, newColor.getRGB());
            }
        }
    }
    public  void changeImage_grey(BufferedImage image, BufferedImage result){

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                Color color = new Color(image.getRGB(x, y));
                int blue = color.getBlue();
                int red = color.getRed();
                int green = color.getGreen();
                int grey = (int) (red * 0.299 + green * 0.587 + blue * 0.114);
                Color newColor =  new Color(grey , grey , grey );
                result.setRGB(x, y, newColor.getRGB());
            }
        }
    }
    public static Color Color_fon( int Red ,int Green ,int  Blue,double cofRed,double cofGreen,double cofBlue){
        int newRed = (int) (Red * cofRed);
        if (newRed < 0) newRed = 0;
        if (newRed > 255) newRed = 255;
        int newGreen = (int) (Green * cofGreen);
        if (newGreen < 0) newGreen = 0;
        if (newGreen > 255) newGreen = 255;
        int newBlue= (int) (Blue * cofBlue);
        if (newBlue < 0) newBlue = 0;
        if (newBlue > 255) newBlue = 255;
        Color newColor = new Color(newRed , newGreen , newBlue );
        return newColor;
    }
    public static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

}
