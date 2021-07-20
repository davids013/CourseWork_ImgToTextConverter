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
    private int maxHeight;
    private int maxWidth;

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));
        int height = img.getHeight();
        int width = img.getWidth();
        double ratio = width / height;
        System.out.println(height + ", " + width + ", " + ratio);
        System.out.println(maxHeight + ", " + maxWidth + ", " + maxRatio);
        if (height > maxHeight && maxHeight > 0) {
            System.err.printf("Недопустимая высота изображения: %d вместо %d%n", height, maxHeight);
            return null;
        }
        if (width > maxWidth && maxWidth > 0) {
            System.err.printf("Недопустимая ширина изображения: %d вместо %d%n", width, maxWidth);
            return null;
        }
        if (ratio > maxRatio && maxRatio > 0) {
            throw new BadImageSizeException(ratio, maxRatio);
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
                //bytes[i][j] = (byte) ((red + green + blue) / 3);
                bytes[i][j] = (byte) (((red + minByte) + (green + minByte) + (blue + minByte)) / 3);
                //System.out.print((bytes[i][j] - minByte) + " ");
                //System.out.println(((bytes[i][j] - minByte) / ((maxByte - minByte + 1) / SYMBOLS.length)));
                char c = SYMBOLS[((bytes[i][j] - minByte) / ((maxByte - minByte + 1) / SYMBOLS.length))];
                System.out.print(c + " ");
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
        //TODO:Написать метод
        System.out.println("\tЗаказали setMaxWidth(" + width + ")");
        maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        //TODO:Написать метод
        System.out.println("\tЗаказали setMaxHeight(" + height + ")");
        maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        //TODO:Написать метод
        System.out.println("\tЗаказали setMaxRatio(" + maxRatio + ")");
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
}
