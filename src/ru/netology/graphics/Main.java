package ru.netology.graphics;

import ru.netology.graphics.converter.Converter;
import ru.netology.graphics.converter.ColorSchema;
import ru.netology.graphics.image.TextGraphicsConverter;

import java.io.File;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new Converter(); // Создайте тут объект вашего класса конвертера
        converter.setTextColorSchema(new ColorSchema(ColorSchema.MY_STRANGE_SCHEMA));
        converter.setMaxRatio(1.546d);

    //Пейзаж (широкий/исключение)
        //String url = "https://wallpaperforu.com/wp-content/uploads/2020/08/nature-wallpaper-200816160641212048x1152.jpg";
    //Филипп
        String url = "https://i.ibb.co/6DYM05G/edu0.jpg";
/*
        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем
*/
        // Или то же, но с сохранением в файл:
        String fileName = "converted-image.txt";
        PrintWriter fileWriter = new PrintWriter(new File(fileName));
        converter.setMaxWidth(200);
        converter.setMaxHeight(300);
        fileWriter.write(converter.convert(url));
        fileWriter.close();
        try {
            Runtime.getRuntime().exec(new String[] { "c:\\windows\\notepad.exe", fileName });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
