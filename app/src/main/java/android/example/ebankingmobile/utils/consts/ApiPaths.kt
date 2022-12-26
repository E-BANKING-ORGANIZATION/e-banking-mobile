package android.example.ebankingmobile.utils.consts

class ApiPaths {

    companion object {
        const val WS_BENEFICIARE = "http://localhost:8083"
        const val WS_TRANSACTION = "http://localhost:8084"
        const val WS_OTP = "http://localhost:8085"

        const val SLASH = "/"
        const val GET_BENEFICIAIRE = WS_BENEFICIARE + SLASH
        const val GET_TRANSACTION = WS_TRANSACTION + SLASH
        const val POST_TRANSACTION = "/do-transaction"
        const val GET_OTP = "/get-otp"
    }

}