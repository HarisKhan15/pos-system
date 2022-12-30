package service;

import repository.TransactionRepository;

public class TransactionService {
    TransactionRepository transactionRepository = new TransactionRepository();

    public int transactionEnterIntoDatabaseAndGetId(String userId, Double totalAmount){
        if(transactionRepository.enterTransactionIntoDatabase(userId,totalAmount)){
            return transactionRepository.getTransactionId();
        }
        return -1;
    }
    public String[][] getDataForJTable(){
        return transactionRepository.getAllTransactionForJTable();
    }

    public String[][] getSpecificDataForJTable(int transactionId){
        return transactionRepository.getSpecificTransactionForJTable(transactionId);
    }
}
