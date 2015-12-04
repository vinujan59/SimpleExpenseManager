package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by vinu on 10/13/2015.
 */
public class MySQLiteHelperAccount extends SQLiteOpenHelper {
    public static final String TABLE_ACCOUNTS = "accounts";
    public static final String ACCOUNT_NUMBER = "account_number";
    public static final String BANK_NAME = "bank_name";
    public static final String ACCOUNT_HOLDER_NAME = "account_holder_name";
    public static final String BALANCE = "balance";


    private static final String DATABASE_NAME = "130618F.db";
    private static final int DATABASE_VERSION = 5;

    // Database creation sql statement
    private static final String CREATE_ACCOUNTS = "create table IF NOT EXISTS "
            + TABLE_ACCOUNTS + "(" + ACCOUNT_NUMBER
            + " TEXT primary key , " + BANK_NAME
            + " TEXT not null, " + ACCOUNT_HOLDER_NAME
            + " TEXT not null, " + BALANCE
            + " REAL not null);";

    public MySQLiteHelperAccount(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_ACCOUNTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("vvvvvvvvvvvvvvvvv",
                "Upgrading database from version " + oldVersion + " to " + newVersion);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
//        db.execSQL(CREATE_TRANSACTIONS);
    }

}
