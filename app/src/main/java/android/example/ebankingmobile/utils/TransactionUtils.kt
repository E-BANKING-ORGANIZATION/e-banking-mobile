package android.example.ebankingmobile.utils

import android.example.ebankingmobile.utils.consts.NotificationNames

class TransactionUtils {
    companion object {
        fun calculateFinalTransferAmountWithFees(amount: Long, choice: Int): Long {
            return when (choice) {
                1 -> 5000
                2 -> 4000
                3 -> 6000
                else -> { // Note the block
                    amount
                }
            }
        }

        fun returnFinalLineInListTransactionFees(fee: Long, typeFee: Int): String {
            return when (typeFee) {
                1 -> "Original Amount : $fee"
                2 -> "Transfer fee : $fee"
                3 -> "Frais de charge : $fee"
                else -> ({ // Note the block
                    null
                }).toString()
            }
        }

        fun calculateNotificationFee(notificationChoice: String, amount: Long): Long {
            return when (notificationChoice) {
                NotificationNames.NOTHING -> 0
                NotificationNames.FIRST_NOTIFICATION_CHOICE -> 15
                NotificationNames.SECOND_NOTIFICATION_CHOICE -> 20
                NotificationNames.THIRD_NOTIFICATION_CHOICE -> 25
                else -> 0
            }
        }
    }
}