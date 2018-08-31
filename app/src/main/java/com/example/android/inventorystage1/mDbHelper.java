package com.example.android.inventorystage1;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
    public class mDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "BOOK STORE";
        private static final int DATABASE_VERSION = 1;
        public mDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String SQL_CREATE_BOOKS_TABLE = "CREATE TABLE "
                    + BookContract.BookEntry.TABLE_NAME + " ("
                    + BookContract.BookEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + BookContract.BookEntry.COLUMN_BOOK_NAME + " TEXT NOT NULL, "
                    + BookContract.BookEntry.COLUMN_BOOK_PRICE + " INTEGER NOT NULL, "
                    + BookContract.BookEntry.COLUMN_BOOK_QUANTITY + " INTEGER NOT NULL, "
                    + BookContract.BookEntry.COLUMN_BOOK_SUPPLIERNAME + " TEXT NOT NULL, "
                    + BookContract.BookEntry.COLUMN_BOOK_SUPPLIERPHONE + " INTEGER DEFAULT 0);";
            sqLiteDatabase.execSQL(SQL_CREATE_BOOKS_TABLE); }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            // The database is still at version 1.
        }}

