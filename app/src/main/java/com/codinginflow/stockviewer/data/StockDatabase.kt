package com.codinginflow.stockviewer.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Stock::class], version = 1)
abstract class StockDatabase : RoomDatabase() {

}