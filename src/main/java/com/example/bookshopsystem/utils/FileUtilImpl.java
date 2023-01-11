package com.example.bookshopsystem.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class FileUtilImpl implements FileUtil {
    @Override
    public String[] readFile(String path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        Set<String> result = new LinkedHashSet<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (!"".equals(line)) {
                result.add(line);
            }
        }
        return result.toArray(new String[0]);
    }
}
