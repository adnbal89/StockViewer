package com.codinginflow.stockviewer.di

import android.app.Application
import androidx.room.Room
import com.codinginflow.stockviewer.data.StockDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        app: Application,
        //to implement circular dependency
        callback: StockDatabase.Callback
    ) =
        Room.databaseBuilder(app, StockDatabase::class.java, "stock_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()

    //StockDatabase is abstract, so we cannot constructor injection
    @Provides
    fun provideStockDao(db: StockDatabase) = db.stockDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope