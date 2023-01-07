package android.example.ebankingmobile.utils

import android.example.ebankingmobile.utils.consts.Consts

class TransactionUtils {
    companion object {

        fun returnFinalLineInListTransactionFees(fee: Double, typeFee: Int): String {
            return when (typeFee) {
                0 -> Consts.AMOUNT + fee
                1 -> Consts.NOTIFICATION_TRANSFER_FEES + fee
                2 -> Consts.CHARGE_FEES + fee
                else -> ({ // Note the block
                    null
                }).toString()
            }
        }

        fun returnChargeFees(amountUser: Long, chargeFeesChoice: String): Double? {
            return when (chargeFeesChoice) {
                Consts.FIRST_NOTIFICATION_CHOICE -> {
                    amountUser.toDouble().times(Consts.PERCENTAGE_FEES)
                }
                Consts.SECOND_NOTIFICATION_CHOICE -> {
                    amountUser.toDouble().times(Consts.HALF_PERCENTAGE_FEES)
                }
                else -> {
                    0.0
                }
            }
            return null
        }

    }
}