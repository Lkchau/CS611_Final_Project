package account;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import utils.ReadFileUtil;
import utils.WriteFileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SavingAccount extends CustomerAccount {

    public SavingAccount(String accountName, int id, double amount, String currencyType){
        super(accountName, id, amount, currencyType);
    }

    public SavingAccount(String accountName){
        super(accountName, 0.0,"USD");
    }

    public SavingAccount(){
        this("");
    }
    
    public void transaction(){

    }

    public void display(){
        // Connect to the GUI
    }

    @Override
    public void updateBalance(double value) throws IOException, CsvException {
        balance = balance + value;
        System.out.println("saving balance" + balance);
        File currAccountFile = ReadFileUtil.getPathToAccountData("savingAccounts");
        List<String[]> data = new CSVReader(new FileReader(currAccountFile)).readAll();
        for(String[] d: data){
            if(Integer.parseInt(d[0]) == this.id){
                d[1] = "" + (balance);
            }
        }
        WriteFileUtil.writeFile(currAccountFile, data);
    }

    @Override
    public void open() {
        super.open(this);
        File checkingAccountFile = ReadFileUtil.getPathToAccountData("savingAccounts");
        if(checkingAccountFile.exists()){
            try {
                CSVReader reader = new CSVReader(new FileReader(getCustomerData().getAbsolutePath()));
                List<String[]> data = reader.readAll();
                boolean customerExists = false;
                for(String[] d: data){
                    if(d[0].equalsIgnoreCase(this.name)){
                        customerExists = true;
                    }
                }
                if(customerExists){
                    WriteFileUtil.writeLine(checkingAccountFile.getPath(), id +"," + balance + "," + currencyType);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CsvException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(){
        super.close(this);
        File savingAccountFile = ReadFileUtil.getPathToAccountData("savingAccounts");
        if(savingAccountFile.exists()){

            WriteFileUtil.removeLineFromCustomerAccountData(savingAccountFile.getAbsolutePath(), this);
        }
    }


}
