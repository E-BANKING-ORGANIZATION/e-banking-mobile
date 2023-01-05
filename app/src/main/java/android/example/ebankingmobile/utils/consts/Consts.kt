package android.example.ebankingmobile.utils.consts

class Consts {
    companion object {

        const val BENEFICIARE = "BENEFICIARE"
        const val TRANSACTION = "TRANSACTION"
        private const val AUTHORIZATION_HEADER = "AUTHORIZATION"
        const val BEARER = "Bearer "
        const val REQUEST_WITH_HEADER_AUTHORIZATION = "$AUTHORIZATION_HEADER: $BEARER "
        const val JWT = "kjh"
        const val APPLICATION_JSON = "application/json"
    }
}