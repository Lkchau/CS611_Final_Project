/**
 * Utility class to check info in given file
 */

package utils;

public class ReadFileUtil{
    
    // Check if a target exist in file
    public static boolean isExist(String filePath, int fieldIndex, String target){
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            if(data[fieldIndex].equals(target)){
                csvReader.close();
                return true;
            }
        }
        csvReader.close();
        return false;
    }
    
    // find if all target match with a query in file
    public static boolean findMatch(String filePath, int[] fieldIndex, String[] target){
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            for (int i=0; i<fieldIndex.size();i++){
                if(data[fieldIndex].equals(target) == false){
                    break;
                }
                csvReader.close();
                return true;
            }
        }
        csvReader.close();
        return false;
    }
    
    // get specific field in given line of csv
    public static String getField(String filePath, int lineIndex, int fieldIndex){
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        row = "";
        for (int i=0; i<lineIndex; i++){
            row = csvReader.readLine();
        }
        
        if(row != null){
            csvReader.close();
            return row.split(",")[fieldIndex];
        }else{
            csvReader.close();
            return row;
        }
    }
}