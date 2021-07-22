package ru.netology.graphics.converter;

import ru.netology.graphics.image.TextColorSchema;

public class ColorSchema implements TextColorSchema {
    private Character[] schema;
    public static final Character[] BASIC_SCHEMA = {'#', '$', '@', '%', '*', '+', '-', '\''};
    public static final Character[] MY_SIMPLE_SCHEMA = {'M', 'N', 'O', 'm', 'n', 'o', 'i', '-', ',', '.'};
    public static final Character[] MY_STRANGE_SCHEMA = {(char) 174, (char) 169, (char) 1138,
            (char) 240, (char) 164, (char) 559, (char) 691, (char) 176, (char) 720, (char) 721};

    public ColorSchema() {
        schema = BASIC_SCHEMA;
    }

    public ColorSchema(Character[] chars) {
        schema = chars;
    }

    @Override
    public char convert(int color) {
        return
                schema[(int) ((color - Byte.MIN_VALUE)
                        / ((Byte.MAX_VALUE - Byte.MIN_VALUE + 1d) / schema.length))];
    }
}
