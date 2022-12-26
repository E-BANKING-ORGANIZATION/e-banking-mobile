package android.example.ebankingmobile.ui.fragments.home

import android.app.Application
import android.example.ebankingmobile.data.api.TransactionService
import androidx.lifecycle.AndroidViewModel


class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var transactionService: TransactionService
}