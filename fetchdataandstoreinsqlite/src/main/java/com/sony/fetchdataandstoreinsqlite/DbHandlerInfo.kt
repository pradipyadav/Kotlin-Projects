package com.sony.fetchdataandstoreinsqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

class DbHandlerInfo(
    context: Context,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object {

        private val DATABASE_NAME = "MyDataBAse.db"
        private val DATABASE_VERSION = 1

        val CUSTOMERS_TABLE_NAME = "CustomersInfo"
        val COLUMN_CUSTOMER_ID = "customerid"
        val COLUMN_CUSTOMER_NAME = "customername"
        val COLUMN_CUSTOMER_USERNAME = "customerusername"
        val COLUMN_CUSTOMER_EMAILID = "email"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_CUSTOMER_TABLE = ("CREATE TABLE " +
                "$CUSTOMERS_TABLE_NAME(" +
                "$COLUMN_CUSTOMER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_CUSTOMER_NAME TEXT," +
                "$COLUMN_CUSTOMER_USERNAME TEXT," +
                "$COLUMN_CUSTOMER_EMAILID TEXT)")

        db?.execSQL(CREATE_CUSTOMER_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

//        if (p1 < 1) {
//            p0?.execSQL("Alter Table $CUSTOMERS_TABLE_NAME" + "Add $COLUMN_PHONE_NO TEXT NULL")
//        }
    }

    fun getInfos(mCtx: Context): ArrayList<InfoData> {
        val qry = "Select * from $CUSTOMERS_TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(qry, null)

        val information = ArrayList<InfoData>()

        if (cursor.count == 0)
            Toast.makeText(mCtx, "Records Not Found", Toast.LENGTH_LONG).show()
        else {
            cursor.moveToFirst()
            while (!cursor.isAfterLast()) {
                val infor = InfoData()

                infor.id = cursor.getInt(cursor.getColumnIndex(COLUMN_CUSTOMER_ID))

                infor.name = cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_NAME))
                infor.username = cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_USERNAME))
                infor.email = cursor.getString(cursor.getColumnIndex(COLUMN_CUSTOMER_EMAILID))

                information.add(infor)

                cursor.moveToNext()
            }

            Toast.makeText(mCtx, "${cursor.count.toString()} Records Found", Toast.LENGTH_LONG)
                .show()
        }
        cursor.close()
        db.close()
        return information
    }

    fun addInfos(mCtx: Context, inform: InfoData) {
        val values = ContentValues()
        values.put(COLUMN_CUSTOMER_NAME, inform.name)
        values.put(COLUMN_CUSTOMER_USERNAME, inform.username)
        values.put(COLUMN_CUSTOMER_EMAILID, inform.email)

        val db = this.writableDatabase

        try {
            db.insert(CUSTOMERS_TABLE_NAME, null, values)
//            Toast.makeText(mCtx, "Customer Added", Toast.LENGTH_LONG).show()
            Log.e("added", "Customer Added")
        } catch (e: Exception) {
            Toast.makeText(mCtx, e.message, Toast.LENGTH_LONG).show()
        }
        db.close()
    }
    fun deleteCustomer(customerID: Int): Boolean {

        val qry = "Delete from $CUSTOMERS_TABLE_NAME where $COLUMN_CUSTOMER_ID=$customerID"
        val db = this.writableDatabase
        var result: Boolean = false

        try {
            /*var cursor = db.delete(
                CUSTOMERS_TABLE_NAME,
                "$COLUMN_CUSTOMER_ID=?",
                arrayOf(customerID.toString())
            )*/

            val cursor = db.execSQL(qry)
            result = true

        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Error Deleting")
        }
        db.close()
        return result
    }

    fun updateCustomer(id: String, customerName: String, customerUN: String, customerEmailID: String): Boolean {
        val db = writableDatabase
        val contentValues = ContentValues()
        var result: Boolean = false
        contentValues.put(COLUMN_CUSTOMER_NAME, customerName)
        contentValues.put(COLUMN_CUSTOMER_USERNAME, customerUN)
        contentValues.put(COLUMN_CUSTOMER_USERNAME, customerEmailID)
        try {
            db.update(CUSTOMERS_TABLE_NAME, contentValues, "$COLUMN_CUSTOMER_ID=?", arrayOf(id))
            result = true
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Error Updating")
            result = false
        }
        return result
    }

}