package com.dailyapps.common.components.BottomSheet

sealed class Footer {
    object Pain: Footer()
    sealed class ButtonSingle: Footer() {
        data class NegativeButton(val negativeBtnLabel: String, val onClickNegative: (() -> Unit)?): Footer()
        data class PositiveButton(val positiveBtnLabel: String, val onClickPositive: (() -> Unit)?): Footer()
    }
    data class ButtonMultiple(
        val negativeBtnLabel: String,
        val onClickNegative: (() -> Unit)?,
        val positiveBtnLabel: String,
        val onClickPositive: (()->Unit)?
    ): Footer()
}
