package java.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

import javax.imageio.ImageIO;

public class CreateImage {


  public static void main(String[] args) throws MalformedURLException, IOException {

    // Getting ClassLoader obj
    File inputImage = new File("/home/thaint/workspace/JavaImage/image/input.png");

    BufferedImage image = ImageIO.read(inputImage);

    Random rand = new Random();
    int max = 20;
    int min = 1;
    Integer countLike = 1;
    Integer counLove = 5;
    for (int i = 1; i < 100; i++) {
      int randomNum = rand.nextInt((max - min) + 1) + min;
      countLike = countLike + randomNum;
      int randomNum2 = rand.nextInt((max - min) + 1) + min;
      counLove = counLove + randomNum2;

      Graphics2D g2d = image.createGraphics();
      g2d.drawImage(image, 0, 0, null);
      g2d.setPaint(Color.red);
      g2d.setFont(new Font("Serif", Font.BOLD, 20));
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      String s = countLike.toString();

      g2d.drawString(s, 20, 80);
      g2d.drawString(counLove.toString(), 300, 80);
      g2d.dispose();

      String newFile = "/home/thaint/workspace/JavaImage/video/test" + i + ".png";

      ImageIO.write(image, "png", new File(newFile));
      image = ImageIO.read(inputImage);
    }

    System.out.println("done");
  }
}
