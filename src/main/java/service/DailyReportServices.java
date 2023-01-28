package service;

import repository.TransactionRepository;

public class DailyReportServices {
    TransactionRepository transactionRepository = new TransactionRepository();
    public Double getDailyProfit(){
        return transactionRepository.totalDailyProfit();
    }
    public Double getUserProfit(String name){
        return transactionRepository.totalDailyProfitByUser(name);
    }
    public Double getAllTransactionReportUserProfit(String name){
        return transactionRepository.totalTransactionProfitByUser(name);
    }
    public Double getProfitPerTransaction(int transactionId){
        return transactionRepository.getProfitPerTransaction(transactionId);
    }
    public Double getTotalProfit(){
        return transactionRepository.totalTransactionProfit();
    }
    public String[][] getDataForTable(){
        return transactionRepository.getAllValueForJtabel();
    }

}
