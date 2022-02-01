package com.codinginflow.stockviewer.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.codinginflow.stockviewer.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Stock::class], version = 1)
abstract class StockDatabase : RoomDatabase() {

    //provided by AppModule by HiltDagger
    abstract fun stockDao(): StockDao

    //there is a circular dependency, so we provide callback so after the Room db builder build() finishes, callback runs.
    class Callback @Inject constructor(
        private val database: Provider<StockDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        //runs first time db created.
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            //runs inside the callback, after the Room DB build().
            val dao = database.get().stockDao()

            //dao functions are suspend functions, so they need coroutine to run, coroutine need a scope
            //using GlobalScope considered bad practice. So creating applicationScope
            applicationScope.launch {
                dao.insert(
                    Stock(
                        name = "EREGL",
                        buyPrice = 28.10,
                        currentPrice = 22.10,
                        checked = true
                    )
                )
                dao.insert(Stock(name = "KARTN", buyPrice = 50.50, currentPrice = 56.00))
            }
        }
    }
}