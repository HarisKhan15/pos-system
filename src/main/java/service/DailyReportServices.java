package service;

import repository.TransactionRepository;

public class DailyReportServices {
    TransactionRepository transactionRepository = new TransactionRepository();
    public String[][] getDataForTable(int column){

        return transactionRepository.getAllValueForJtabel(column);
    }

}