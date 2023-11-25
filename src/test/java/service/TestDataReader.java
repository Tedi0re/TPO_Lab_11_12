package service;

import java.util.ResourceBundle;

public class TestDataReader {
    private static ResourceBundle resourceBundle;

    private static String TESTDATA_AMOUNT_WITHDRAW = "testdata.amount.withdraw";

    public TestDataReader(){
        try{
            resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));
        } catch (NullPointerException exception){
            resourceBundle = ResourceBundle.getBundle("dev");
        }

    }


    public static String GetAmountWithdraw(String key){
        System.out.println(resourceBundle.getString(key));
        return resourceBundle.getString(key);
    }

    public static String GetAmountWithdraw(){
        return GetAmountWithdraw(TESTDATA_AMOUNT_WITHDRAW);
    }

    public static String getTestData(String key) {return resourceBundle.getString(key);}
}
