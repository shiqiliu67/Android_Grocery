package com.shiqiliu.onlineshopping1.database

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build.ID
import android.util.Log
import android.widget.Toast
import com.shiqiliu.onlineshopping1.activity.ShoppingCartActivity
import com.shiqiliu.onlineshopping1.modules.Product
import com.shiqiliu.onlineshopping1.modules.ProductCart

class DBHelper (var mContext: Context) : SQLiteOpenHelper(mContext, DATABASE_NAME,null,
    DATABSE_VERSION){
    var db :SQLiteDatabase = writableDatabase
    companion object{
        const val DATABASE_NAME = "mydb"
        const val DATABSE_VERSION =2
        const val TABLE_NAME = "cart"
        //insert product image, name, price
        const val COLUMN_NAME = "name"
        const val COLUMN_IMAGE = "image"
        const val COLUMN_PRICE = "price"
        const val COLUMN_COUNT = "count"
        const val COLUMN_ID = "ID"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var createTable = "create table $TABLE_NAME($COLUMN_ID integer, $COLUMN_NAME char(200), $COLUMN_PRICE decimal(10,2),$COLUMN_IMAGE varchar(500), $COLUMN_COUNT integer)"
        db?.execSQL(createTable)
        Log.d("abc","create table")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        var dropTable = "drop table $TABLE_NAME"
        db?.execSQL(dropTable)
        onCreate(db)
    }
    //add product to cart
    fun addToCart(product: Product){
        if(!containProduct(product)){
        var contentValues = ContentValues()
      //  product.count =1
         product.quantity=1
        contentValues.put(COLUMN_ID,product._id)
        contentValues.put(COLUMN_NAME,product.productName)
        contentValues.put(COLUMN_PRICE,product.price)
        contentValues.put(COLUMN_IMAGE,product.image)
        //contentValues.put(COLUMN_COUNT,product.count)//default 1
        contentValues.put(COLUMN_COUNT,product.quantity)
        db.insert(TABLE_NAME,null,contentValues)
        Log.d("abc","add to cart")}
        else{
           // product.quantity++
            UpdateCart(product)
        }
    }
    //update product to cart unit = '', where product id = ""
    fun UpdateCart(product: Product):Int{
        //product.count++
        product.quantity++
        var contentValues = ContentValues()
       contentValues.put(COLUMN_COUNT,product.quantity)
        Log.d("abc","update cart count:${product.quantity}")
      //  contentValues.put(COLUMN_PRICE,product.price)
        var whereClause = "$COLUMN_ID = ?"
        var whereArgs = arrayOf(product._id.toString())
        return db.update(TABLE_NAME,contentValues,whereClause,whereArgs)
    }
    //delete product to cart
    fun deleteProductToCart(product: Product):Int{
        var whereClause = "$COLUMN_ID = ?"
        var whereArgs = arrayOf(product._id.toString())
        return db.delete(TABLE_NAME, whereClause, whereArgs)
    }
    fun getAllProduct():List<Product>{
        var list:ArrayList<Product> = ArrayList()
        var columns = arrayOf(COLUMN_ID, COLUMN_NAME, COLUMN_PRICE, COLUMN_IMAGE, COLUMN_COUNT)
        var cursor = db.query(TABLE_NAME, columns, null, null, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            do {
                var id = cursor.getString(cursor.getColumnIndex(COLUMN_ID))
                var name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                var price = cursor.getFloat(cursor.getColumnIndex(COLUMN_PRICE))
                var image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE))
                var count = cursor.getInt(cursor.getColumnIndex(COLUMN_COUNT))

               // var product = ProductCart(id,name,price,image,quantity)
                var product = Product(null,id,null,null,null,image,null,null,price,name,count,null,null,null)
                list.add(product)
            } while (cursor.moveToNext())
        }
        return list
    }

    fun increaseNumber(product: Product):Int{
        product.quantity = product.quantity + 1
        Log.d("abc", "count now ${product.quantity}")
        var contentValues = ContentValues()
        contentValues.put(COLUMN_COUNT,product.quantity)
        var whereClause = "$COLUMN_ID = ?"
        var whereArgs = arrayOf(product._id.toString())
        return db.update(TABLE_NAME,contentValues,whereClause,whereArgs)
    }

    fun decreaseNumber(product: Product):Int {

        product.quantity = product.quantity -1
        Log.d("abc", "count now $product.count")
        //when count<0 delete
        if(product.quantity <= 0){
            deleteProductToCart(product)
            mContext.startActivity(Intent(mContext, ShoppingCartActivity::class.java))
            Toast.makeText(mContext, "Delete Successful", Toast.LENGTH_SHORT).show()
        }

        var contentValues = ContentValues()
        contentValues.put(COLUMN_COUNT,product.quantity)
        var whereClause = "$COLUMN_ID = ?"
        var whereArgs = arrayOf(product._id.toString())
        return db.update(TABLE_NAME,contentValues,whereClause,whereArgs)
    }

    fun totalPrice(product: Product):Float{
        var total = product.quantity * product.price!!
        return total
    }

    fun containProduct(product: Product):Boolean{
       // var product:Product? =null
        var query = "select * from $TABLE_NAME where $COLUMN_ID = ?"
        var cursor = db.rawQuery(query, arrayOf(product._id))
 //       if (cursor != null) {
//            var id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
//            var name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
//            var count = cursor.getInt(cursor.getColumnIndex(COLUMN_COUNT))
//            var image = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE))
//            var price = cursor.getFloat(cursor.getColumnIndex(COLUMN_PRICE))
//            product = Product(null,null,id,null,null,image,null,null,price,name,null,null,null,null,count)
//        return true
//        }
//        else{return false
        var count1 = cursor.count
        return count1 != 0
    }
    fun checkCartEmpty():Boolean{
        var query = "select * from $TABLE_NAME"
        var cursor = db.rawQuery(query, null)
        return cursor.count == 0
    }
    fun EmptyCart():Int{
        return db.delete(TABLE_NAME, null, null)
    }


}