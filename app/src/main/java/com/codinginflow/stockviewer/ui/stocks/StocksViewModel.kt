package com.codinginflow.stockviewer.ui.stocks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.codinginflow.stockviewer.data.StockDao


class StocksViewModel @ViewModelInject constructor(
    private val stockDao: StockDao
) : ViewModel() {

}