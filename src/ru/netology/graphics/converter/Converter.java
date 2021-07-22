package ru.netology.graphics.converter;

import ru.netology.graphics.image.BadImageSizeException;
import ru.netology.graphics.image.TextColorSchema;
import ru.netology.graphics.image.TextGraphicsConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Converter  implements TextGraphicsConverter, TextColorSchema {
    private final char[] SYMBOLS = {'#', '$', '@', '%', '*', '+', '-', '\''};
    private double maxRatio;
    private int maxWidth;
    private int maxHeight;

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));
        int width = img.getWidth();
        int height = img.getHeight();
        double ratio = (double) width / height;
        System.out.printf("ImageSizes: width=%d height=%d ratio=%.2f%n", width, height, ratio);
        System.out.printf("MaxSizes: width=%d height=%d ratio=%.2f%n", maxWidth, maxHeight, maxRatio);
        if (ratio > maxRatio && maxRatio > 0) {
            throw new BadImageSizeException(ratio, maxRatio);
        }
        if (height > maxHeight && maxHeight > 0) {
            System.out.printf("\tНедопустимая высота изображения: %d вместо %d%n", height, maxHeight);
            img = resize(img, (int) (width / ((double) height / maxHeight)), maxHeight);
            System.out.println("Произведено сжатие изображения");
            width = img.getWidth();
            height = img.getHeight();
            ratio = (double) width / height;
            System.out.printf("NewImageSizes: width=%d height=%d ratio=%.2f%n", width, height, ratio);
            //return null;
        }
        if (width > maxWidth && maxWidth > 0) {
            System.out.printf("\tНедопустимая ширина изображения: %d вместо %d%n", width, maxWidth);
            img = resize(img, maxWidth, (int) (height / ((double) width / maxWidth)));
            System.out.println("Произведено сжатие изображения");
            width = img.getWidth();
            height = img.getHeight();
            ratio = (double) width / height;
            System.out.printf("NewImageSizes: width=%d height=%d ratio=%.2f%n", width, height, ratio);
            //return null;
        }

        byte[][] bytes = new byte[img.getHeight()][img.getWidth()];
        byte minByte = Byte.MIN_VALUE;
        byte maxByte = Byte.MAX_VALUE;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 0; j < bytes[i].length; j++) {
                int rgb = img.getRGB(j, i);
                //int alpha = (rgb >> 24) & 0xFF;
                int red =   (rgb >> 16) & 0xFF;
                int green = (rgb >>  8) & 0xFF;
                int blue =  (rgb      ) & 0xFF;
                bytes[i][j] = (byte) (((red + minByte) + (green + minByte) + (blue + minByte)) / 3);
                char c = SYMBOLS[((bytes[i][j] - minByte) / ((maxByte - minByte + 1) / SYMBOLS.length))];
                System.out.print(c + "");
                sb.append(c);
                sb.append(" ");
            }
            System.out.println();
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        //TODO:Написать метод
        System.out.println("\tЗаказали setTextColorSchema(" + schema + ")");
    }

    @Override
    public char convert(int color) {
        //TODO:Написать метод
        return 0;
    }

    private BufferedImage resize(BufferedImage oldImage, int newWidth, int newHeight) {
        //BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        //BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        //newImage.getGraphics().drawImage(resized, 0, 0, null);
        newImage.createGraphics().drawImage(oldImage, 0, 0, newWidth, newHeight, null);
        newImage.createGraphics().dispose();
        //System.out.println(newImage.getWidth() + " " + newImage.getHeight());
        return newImage;
    }
}
