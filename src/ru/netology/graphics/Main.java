package ru.netology.graphics;

import ru.netology.graphics.converter.Converter;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;

import java.io.File;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new Converter(); // Создайте тут объект вашего класса конвертера
    //МК (малый)
        //String url = "https://i.ytimg.com/vi/RRd-V6vjz8g/default.jpg";
    //Сова
        //String url = "https://fsd.kopilkaurokov.ru/up/html/2017/12/17/k_5a36af6718a2f/444874_1.jpeg";
    //Пейзаж (крупный)
        //String url = "https://wallpaperforu.com/wp-content/uploads/2020/08/nature-wallpaper-200816160641212048x1152.jpg";
    //Филипп
        String url = "https://i.ibb.co/6DYM05G/edu0.jpg";

        // converter.convert(url);
/*
        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем
*/
        // Или то же, но с сохранением в файл:
///*
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

//*/
    }
}
