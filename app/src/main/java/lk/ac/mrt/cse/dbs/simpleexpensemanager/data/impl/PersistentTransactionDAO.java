package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.Constants;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

/**
 * Created by vinujan on 03/12/15.
 */
public class PersistentTransactionDAO implements TransactionDAO {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {MySQLiteHelper.DATE, MySQLiteHelper.ACCOUNT_NUMBER,
            MySQLiteHelper.AMOUNT, MySQLiteHelper.EXPENSE_TYPE};

    public PersistentTransactionDAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    @Override
    public void logTransaction(Date date, String accountNo, ExpenseType expenseType, double amount) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATEFORMAT,Locale.ENGLISH);
        String dateString = simpleDateFormat.format(date);

        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLiteHelper.DATE,dateString);
        contentValues.put(MySQLiteHelper.ACCOUNT_NUMBER,accountNo);
        String expenseTypeString = Constants.INCOME;
        if(expenseType == ExpenseType.EXPENSE){
            expenseTypeString = Constants.EXPENSE;
        }
        contentValues.put(MySQLiteHelper.EXPENSE_TYPE,expenseTypeString);
        contentValues.put(MySQLiteHelper.AMOUNT,amount);

        database.insert(MySQLiteHelper.TABLE_TRANSACTIONS, null, contentValues);
    }

    @Override
    public List<Transaction> getAllTransactionLogs() {
        List<Transaction> transactions = new ArrayList<Transaction>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_TRANSACTIONS,allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Transaction transaction = cursorToTransaction(cursor);
            transactions.add(transaction);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return transactions;
    }

    @Override
    public List<Transaction> getPaginatedTransactionLogs(int limit) {
        List<Transaction> transactions = getAllTransactionLogs();
        int size = transactions.size();
        if (size <= limit) {
            return transactions;
        }
        // return the last <code>limit</code> number of transaction logs
        return transactions.subList(size - limit, size);
    }

    private Transaction cursorToTransaction(Cursor cursor){
        String date = cursor.getString(0);
        //date format "2 January 2010"
        DateFormat dateFormat = new SimpleDateFormat(Constants.DATEFORMAT, Locale.ENGLISH);
        Date date1 = new Date();
        try {
             date1 = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String accountNumber = cursor.getString(1);
        String expenseType = cursor.getString(2);
        ExpenseType expenseType1 = ExpenseType.EXPENSE;
        if(expenseType.equals(Constants.INCOME)){
            expenseType1 = ExpenseType.INCOME;
        }
        Double amount = cursor.getDouble(3);
        return new Transaction(date1,accountNumber,expenseType1,amount);
    }
}
