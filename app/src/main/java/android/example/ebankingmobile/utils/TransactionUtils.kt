package android.example.ebankingmobile.utils

import android.example.ebankingmobile.utils.consts.Consts
import android.example.ebankingmobile.utils.consts.NotificationNames

class TransactionUtils {
    companion object {
        fun calculateFinalTransferAmountWithChargeFeesWithoutNotificationTransferFees(
            amount: Long,
            choice: Int
        ): Long {
            return when (choice) {
                0 -> 5000
                1 -> 4000
                2 -> 6000
                else -> { // Note the block
                    amount
                }
            }
        }

        fun returnFinalLineInListTransactionFees(fee: Long, typeFee: Int): String {
            return when (typeFee) {
                0 -> Consts.AMOUNT + fee
                1 -> Consts.NOTIFICATION_TRANSFER_FEES + fee
                2 -> Consts.CHARGE_FEES + fee
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