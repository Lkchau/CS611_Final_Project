/**
 * Utility class to write info into given file
 */

package utils;

import account.CustomerAccount;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.List;

public class WriteFileUtil{

    // Add new line to csv
    public static void writeLine(String filePath, String content){
        try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(filePath, true))){
            csvWriter.write(content);
            csvWriter.newLine();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeField(String filePath, int keyIndex, String key, int fIndex, String target){
        String fileBuffer = "";
        try (BufferedReader csvReader = new BufferedReader(new FileReader(filePath))){
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if(data[keyIndex].equals(key)){
                    return data(fieldIndex);
                    data[fIndex] = target;
                }
                fileBuffer += String.join(", ", data);
            }
            csvReader.close();
            try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(filePath))){
                csvWriter.write(fileBuffer);
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return row;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void removeLineFromCustomerAccountData(String path, CustomerAccount acct) {
        try {
            CSVReader reader = new CSVReader(new FileReader(path));
            List<String[]> data = reader.readAll();
            int i;
            for (i = 0; i < data.size(); i++) {
                if (Integer.parseInt(data.get(i)[0]) == (acct.getId())) {
                    data.remove(i);
                }
            }
            CSVWriter writer = new CSVWriter(new FileWriter(path));
            writer.writeAll(data);
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

}
