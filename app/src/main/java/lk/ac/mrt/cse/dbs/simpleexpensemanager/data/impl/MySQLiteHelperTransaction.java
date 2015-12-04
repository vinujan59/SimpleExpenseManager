package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by vinujan on 04/12/15.
 */
public class MySQLiteHelperTransaction extends SQLiteOpenHelper{
    public static final String TABLE_TRANSACTIONS = "transaction";
    public static final String DATE= "date";
    public static final String ACCOUNT_NUMBER = "account_number";
    public static final String AMOUNT= "amount";
    public static final String EXPENSE_TYPE = "expense_type";


    private static final String DATABASE_NAME = "130618F.db";
    private static final int DATABASE_VERSION = 5;

    // Database creation sql statement
    private static final String CREATE_TRANSACTIONS = "create table IF NOT EXISTS "
            + TABLE_TRANSACTIONS + "(" + DATE
            + " TEXT not null , " + ACCOUNT_NUMBER
            + " TEXT not null, " + AMOUNT
            + " REAL not null, " + EXPENSE_TYPE
            + " TEXT not null);";

    public MySQLiteHelperTransaction(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TRANSACTIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("vvvvvvvvvvvvvvvvv",
                "Upgrading database from version " + oldVersion + " to " + newVersion);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
//        db.execSQL(CREATE_TRANSACTIONS);
    }
}
