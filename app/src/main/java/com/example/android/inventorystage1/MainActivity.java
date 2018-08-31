package com.example.android.inventorystage1;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextQuantity;
    private EditText editTextPrice;
    private EditText editTextSupplierName;
    private EditText editTextSupplierPhone;
    Button addBtn;
    TextView displayTextView;
    mDbHelper bookDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.bookName);
        editTextPrice = findViewById(R.id.price);
        editTextQuantity = findViewById(R.id.quantity);
        editTextSupplierName = findViewById(R.id.supplierName);
        editTextSupplierPhone = findViewById(R.id.supplierPhone);
        displayTextView = findViewById(R.id.display);
            addBtn = findViewById(R.id.btnAdd);
             addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertBook();
                displayDatabase();
            }
        });
    }
    private void insertBook() {
        String nameString = editTextName.getText().toString().trim();
        String quantityString = editTextQuantity.getText().toString().trim();
        int quantity = Integer.parseInt(quantityString);
        String priceString = editTextPrice.getText().toString().trim();
        int price = Integer.parseInt(priceString);
        String supplierNameString = editTextSupplierName.getText().toString().trim();
        String supplierPhoneString = editTextSupplierPhone.getText().toString().trim();
        int supplierPhone = Integer.parseInt(supplierPhoneString);
        bookDbHelper = new mDbHelper (this);
        SQLiteDatabase sqLiteDatabase = bookDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BookContract.BookEntry.COLUMN_BOOK_NAME, nameString);
        values.put(BookContract.BookEntry.COLUMN_BOOK_QUANTITY, quantity);
        values.put(BookContract.BookEntry.COLUMN_BOOK_PRICE, price);
        values.put(BookContract.BookEntry.COLUMN_BOOK_SUPPLIERNAME, supplierNameString);
        values.put(BookContract.BookEntry.COLUMN_BOOK_SUPPLIERPHONE, supplierPhone);
        long newRowID = sqLiteDatabase.insert(BookContract.BookEntry.TABLE_NAME, null, values);
        Log.v("MainActivity", "newRowId" + newRowID);
        if (newRowID == -1) {
            Toast.makeText(this, "Error when inserting", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Book Saved With Row Id " + newRowID, Toast.LENGTH_LONG).show(); }}
    private void displayDatabase() {
        SQLiteDatabase sqLiteDatabase = bookDbHelper.getReadableDatabase();
        Log.e("MainActivity", bookDbHelper.getDatabaseName());
        String[] Projection = {
                BookContract.BookEntry._ID,
                BookContract.BookEntry.COLUMN_BOOK_NAME,
                BookContract.BookEntry.COLUMN_BOOK_QUANTITY,
                BookContract.BookEntry.COLUMN_BOOK_PRICE,
                BookContract.BookEntry.COLUMN_BOOK_SUPPLIERNAME,
                BookContract.BookEntry.COLUMN_BOOK_SUPPLIERPHONE};
        Cursor cursor = sqLiteDatabase.query(
                BookContract.BookEntry.TABLE_NAME,
                Projection,
                null,
                null,
                null,
                null,
                null);
        try {
            displayTextView.setText("In Store " + cursor.getCount() + " Book.\n\n");
            displayTextView.append(BookContract.BookEntry._ID + " _ " +
                    BookContract.BookEntry.COLUMN_BOOK_NAME + " _ " +
                    BookContract.BookEntry.COLUMN_BOOK_QUANTITY + " _ " +
                    BookContract.BookEntry.COLUMN_BOOK_PRICE + " _ " +
                    BookContract.BookEntry.COLUMN_BOOK_SUPPLIERNAME + " _ " +
                    BookContract.BookEntry.COLUMN_BOOK_SUPPLIERPHONE + "\n");
            int idColumnIndex = cursor.getColumnIndex(BookContract.BookEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(BookContract.BookEntry.COLUMN_BOOK_NAME);
            int quantityColumnIndex = cursor.getColumnIndex(BookContract.BookEntry.COLUMN_BOOK_QUANTITY);
            int priceColumnIndex = cursor.getColumnIndex(BookContract.BookEntry.COLUMN_BOOK_PRICE);
            int supplierNameColumnIndex = cursor.getColumnIndex(BookContract.BookEntry.COLUMN_BOOK_SUPPLIERNAME);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(BookContract.BookEntry.COLUMN_BOOK_SUPPLIERPHONE);
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                int currentSupplierPhone = cursor.getInt(supplierPhoneColumnIndex);
                displayTextView.append(("\n" +
                        currentID + " _ " +
                        currentName + " _ " +
                        currentPrice + " - " +
                        currentQuantity + " _ " +
                        currentSupplierName + " _ " +
                        currentSupplierPhone + " _ ")); }
        } finally {
            cursor.close ();
        }
    }}
