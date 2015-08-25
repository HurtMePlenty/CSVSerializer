import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CSVSerializer {

    public static <T> String serialize(Iterable<T> data, Class<T> clazz, String separator, String replaceForSeparator) throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();

        List<Field> fieldsToSerialize = new ArrayList<Field>();


        StringBuilder builder = new StringBuilder();

        for (Field field : fields) {
            CsvField csvField = field.getAnnotation(CsvField.class);
            if (csvField == null) {
                continue;
            }

            fieldsToSerialize.add(field);
            String fieldName = csvField.name();
            if (fieldName == null || fieldName.equals("")) {
                fieldName = field.getName();
            }
            if (builder.length() != 0) {
                builder.append(separator);
            }
            builder.append(fieldName);
        }

        for (T object : data) {
            builder.append("\n");
            for (Field field : fieldsToSerialize) {
                if (fieldsToSerialize.indexOf(field) != 0) {
                    builder.append(separator);
                }
                Object value = field.get(object);
                if (value == null) {
                    value = "";
                }
                String strValue = value.toString().replace(separator, replaceForSeparator);
                builder.append(strValue);
            }
        }

        return builder.toString();
    }
}
