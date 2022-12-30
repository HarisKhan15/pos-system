package service;

import repository.TransactionRepository;

public class DailyReportServices {
    TransactionRepository transactionRepository = new TransactionRepository();
    public String[][] getDataForTable(){

        return transactionRepository.getAllValueForJtabel();
    }

}
