package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

/**
 * Created by vinujan on 03/12/15.
 */
public class PersistentTransactionDAO implements TransactionDAO {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {MySQLiteHelper.DATE, MySQLiteHelper.ACCOUNT_NUMBER,
            MySQLiteHelper.AMOUNT,MySQLiteHelper.EXPENSE_TYPE};
    @Override
    public void logTransaction(Date date, String accountNo, ExpenseType expenseType, double amount) {

    }

    @Override
    public List<Transaction> getAllTransactionLogs() {
        return null;
    }

    @Override
    public List<Transaction> getPaginatedTransactionLogs(int limit) {
        return null;
    }

    private Transaction cursorToTransaction(Cursor cursor){
        String date = cursor.getString(0);
        String bankName = cursor.getString(1);
        String accountHolderName = cursor.getString(2);
        Double balance = cursor.getDouble(3);
        return new Account(accountName,bankName,accountHolderName,balance);
    }
}
