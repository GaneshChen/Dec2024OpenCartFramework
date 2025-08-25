package com.qa.opencart.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVUtil {

    public static final String CSV_PATH = "./src/test/resources/testdata/";
    public static List<String[]> rows;
    public static Object[][] csvData(String csvName){
        String csvFile = CSV_PATH+csvName+".csv";
        CSVReader reader;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            rows= reader.readAll();
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
        Object[][] data = new Object[rows.size()][];
        for(int i=0; i< rows.size();i++){
            data[i] = rows.get(i);
        }
    return data;
    }

}
