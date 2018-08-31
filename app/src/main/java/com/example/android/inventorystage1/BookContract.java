package com.example.android.inventorystage1;

import android.provider.BaseColumns;

public class BookContract {


        private BookContract() {
        }

        public static final class BookEntry implements BaseColumns {

            public final static String TABLE_NAME = "bookStore";

            public final static String _ID = BaseColumns._ID;

            public final static String COLUMN_BOOK_NAME = "name";

            public final static String COLUMN_BOOK_PRICE = "price";

            public final static String COLUMN_BOOK_QUANTITY = "quantity";

            public final static String COLUMN_BOOK_SUPPLIERNAME = "supplierName";

            public final static String COLUMN_BOOK_SUPPLIERPHONE = "supplierPhone";
        }
    }




