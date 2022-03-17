package org.dictionaryRMQ.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class PublicService {
    public static String readBufferedReader(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        reader.close();

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    static double parseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            return Double.parseDouble(strNumber);
        } else return 0;
    }

    static int parseInt(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            return Integer.parseInt(strNumber);
        } else return 0;
    }

    static UUID uuidFromString(String s) {
        if (s.equals(""))
            return UUID.fromString("00000000-0000-0000-0000-000000000000");
        else return UUID.fromString(s);
    }

    static LocalDateTime parseDatetime(String s) {
        if (s.equals(""))
            return LocalDateTime.parse("1970-01-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        else return LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
