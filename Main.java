package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class Main
{

    public static void main(String[] args)
    {
        drawImageFromScrypt("test.txt", "output.png");

    }

    private static void fillCircle(BufferedImage im, int centerX, int centerY, int radius, Color color)
    {
        int rgb = color.getRGB();
        int dx = 0;
        int dy = 0;
        double distance = 0.0;
        for(int x = centerX - radius; x < centerX + radius; x++)
        {
            for(int y = centerY - radius; y < centerY + radius; y++)
            {
                dx = x - centerX;
                dy = y - centerY;
                distance = Math.sqrt(dx*dx + dy*dy);
                if(distance <= radius)
                {
                    im.setRGB(x, y, rgb);
                }
            }
        }
    }

    private static void setBackground(BufferedImage im, int rgb)
    {
        //int colorCode = color.getRGB();
        for(int x = 0; x < im.getWidth(); x++)
        {
            for(int y = 0; y < im.getHeight(); y++)
            {
                im.setRGB(x, y, rgb);
            }
        }
    }


    private static ArrayList<String[]> readScript(String file)
    {
        ArrayList<String[]> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while((line = br.readLine()) != null)
            {
                String[] words = line.split(",");
                lines.add(words);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return lines;
    }

    private static void drawImageFromScrypt(String inputFile, String outputFile)
    {
        ArrayList<String[]> lines = readScript(inputFile);
        int width = Integer.parseInt(lines.get(0)[0]);
        int height = Integer.parseInt(lines.get(0)[1]);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int backgroundColor = Integer.parseInt(lines.get(1)[0]);
        setBackground(image, backgroundColor);

        File output = new File(outputFile);
        try
        {
            ImageIO.write(image, "png", output);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
