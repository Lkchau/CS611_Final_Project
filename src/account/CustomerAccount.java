package account;

import assets.Stock;
import com.opencsv.CSVReader;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public class CustomerAccount extends Account {
    // Each Customer has their own folder or csv that has all their transaction history
    // Each customer account has an id which we can use to search for their data. 
    public CustomerAccount(String accountName, String password, int id, File data){
        super (accountName, id, password, data);
    }

    public CustomerAccount(){
        this("","Password", -1, new File(""));
    }

    public void open() {
        // New account, add account info and create path to files and display

        File tempFile = getCustomerData();

        if(tempFile.exists()){
            validatePassword(password);
        } else{
            try {
                if (tempFile.createNewFile()) {
                    System.out.println("File successfully made");
                } else {
                    System.out.println("File was not made");
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private boolean validatePassword(String password){
        File tempFile = getCustomerData();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(tempFile.getAbsolutePath()))) {

            List<String[]> info = new ArrayList<>();
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                info.add(data);
            }
            if(info.size() == 0){
                System.out.println("There is no information for this account, but it exists!");
                return false;
            }
            if(password.equals(info.get(0)[1])){
                return true;
            }
            else{
                return false;
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void close(){
        // Delete the account and all relevant info
        File tempFile = getCustomerData();

        if(tempFile.exists()){

            if(tempFile.delete()){
                System.out.println("File successfully deleted");
            }
            else{
                System.out.println("File does not exist and thus was not deleted");
            }

        }
    }

    public void transaction(Account acct){
        if(acct instanceof CustomerAccount){
        }
    }

    public void transfer(Account acct){
        
    }

    public void checkBalance(){
        
    }

    public void display(){

    }

    public void getDailyReport(Date date){

    }

    public File getCustomerData(){
        Path pathAbsolute = Paths.get("../../data/" + this.name+".csv");
        Path pathBase = Paths.get("../../");
        Path pathRelative = pathBase.relativize(pathAbsolute);
        return new File(pathRelative.toUri());
    }
}
