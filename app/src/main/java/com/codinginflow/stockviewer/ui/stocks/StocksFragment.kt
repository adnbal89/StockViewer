package com.codinginflow.stockviewer.ui.stocks

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.codinginflow.stockviewer.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StocksFragment : Fragment(R.layout.fragment_stocks) {

    private val viewModel: StocksViewModel by viewModels()
}