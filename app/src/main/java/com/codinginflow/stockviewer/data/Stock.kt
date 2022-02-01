package com.codinginflow.stockviewer.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat

@Entity(tableName = "stock_table")
@Parcelize
data class Stock(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val checked: Boolean = false,
    val buyPrice: Double,
    val currentPrice: Double,
    val created: Long = System.currentTimeMillis()
) : Parcelable {
    val createDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)
}