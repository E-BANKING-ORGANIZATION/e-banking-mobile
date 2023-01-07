package android.example.ebankingmobile.utils.consts

class Consts {
    companion object {
        const val BRAND = "A1E4"
        const val API_KEY = "14949d4b"
        const val API_SECRET = "Y3nRDeRwCG5csjY3"
        const val NOTIFICATION_TRANSFER_FEES     = "Transfer fee : "
        const val CHARGE_FEES = "Frais de charge : "
        const val AMOUNT = "Original Amount : "
        const val BENEFICIARE = "BENEFICIARE"
        const val TRANSACTION = "TRANSACTION"
        private const val AUTHORIZATION_HEADER = "AUTHORIZATION"
        const val BEARER = "Bearer "
        const val REQUEST_WITH_HEADER_AUTHORIZATION = "$AUTHORIZATION_HEADER: $BEARER "
        const val JWT = "kjh"
        const val APPLICATION_JSON = "application/json"
        const val NOTHING = "NOTHING"
        const val FIRST_NOTIFICATION_CHOICE ="Expéditeur will pay 3% of the transaction"
        const val SECOND_NOTIFICATION_CHOICE ="Bénéficiare will pay 3% of the transaction"
        const val THIRD_NOTIFICATION_CHOICE ="Expéditeur will pay 1.5% and Bénéficiare will pay 1.5%"
        const val PERCENTAGE_FEES = 0.03
        const val HALF_PERCENTAGE_FEES = 0.015
        const val TRANSFER_NOTIFICATION_FEES = 2
    }
}