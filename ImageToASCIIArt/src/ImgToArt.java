import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Color;

import javax.imageio.ImageIO;

public class ImgToArt {

    private double pixelVal;

    public ImgToArt() {
        this.pixelVal = 0;

    }

    public void convertToAscii(String imgName) {
        // try {
        // BufferedImage img = ImageIO.read(new File(imgName));

        // try (PrintWriter writer = new PrintWriter(new FileWriter("UltimateGohan.txt",
        // true))) {

        // for (int i = 0; i < img.getHeight(); i++) {
        // for (int j = 0; j < img.getWidth(); j++) {
        // Color pixelColor = new Color(img.getRGB(j, i));
        // pixelVal = ((pixelColor.getRGB() * 0.30) + (pixelColor.getBlue() * 0.59)
        // + (pixelColor.getGreen() * 0.11));
        // // writer.write(densityFun(pixelVal));
        // writer.print(densityFun(pixelVal));
        // }
        // writer.write("\n");
        // writer.flush();
        // }

        // } catch (IOException e) {
        // e.printStackTrace();
        // System.out.println("Unable to write to file");
        // }

        // for (int i = 0; i < img.getHeight(); i++) {
        // for (int j = 0; j < img.getWidth(); j++) {
        // Color pixelColor = new Color(img.getRGB(i, j));
        // pixelVal = ((pixelColor.getRGB() * 0.30) + (pixelColor.getBlue() * 0.59)
        // + (pixelColor.getGreen() * 0.11));
        // try (BufferedWriter writer = new BufferedWriter(new FileWriter("Aqua.txt",
        // true))) {
        // writer.write(densityFun(pixelVal));
        // } catch (IOException e) {
        // e.printStackTrace();
        // System.out.println("Unable to write to file");
        // }
        // }
        // try (BufferedWriter writer = new BufferedWriter(new FileWriter("Aqua.txt",
        // true))) {
        // writer.newLine();
        // } catch (IOException e) {
        // e.printStackTrace();
        // System.out.println("Unable to write to file");
        // }
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // System.out.println("Error in opening image file");
        // }

        try {
            BufferedImage img = ImageIO.read(new File(imgName));

            StringBuilder sb = new StringBuilder((img.getWidth() + 1) * img.getHeight());
            for (int y = 0; y < img.getHeight(); y++) {
                if (sb.length() != 0)
                    sb.append("\n");
                for (int x = 0; x < img.getWidth(); x++) {
                    Color pixelColor = new Color(img.getRGB(x, y));
                    double dValue = (double) pixelColor.getRed() * 0.2989 + (double) pixelColor.getBlue() * 0.5870
                            + (double) pixelColor.getGreen() * 0.1140;

                    final char s = densityFun(dValue);
                    sb.append(s);
                }
            }
            try (PrintWriter writer = new PrintWriter(new FileWriter("ImgToASCII.txt", true))) {
                writer.print(sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in opening image file");
        }

        System.out.println("Conversion Done!");
    }

    private char densityFun(double density) {
        final char str;

        if (density >= 230.0) {
            str = ' ';
        } else if (density >= 200.0) {
            str = '.';
        } else if (density >= 180.0) {
            str = '*';
        } else if (density >= 160.0) {
            str = ':';
        } else if (density >= 130.0) {
            str = 'o';
        } else if (density >= 100.0) {
            str = '&';
        } else if (density >= 70.0) {
            str = '8';
        } else if (density >= 50.0) {
            str = '#';
        } else {
            str = '@';
        }
        return str;
    }
}
