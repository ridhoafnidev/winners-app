package com.dailyapps.home.changepassword

import com.dailyapps.entity.ChangePassword

sealed class ChangePasswordScreenState {
    class Success(val changePassword: ChangePassword): ChangePasswordScreenState()
    class Error(val message:  String): ChangePasswordScreenState()
    object Loading: ChangePasswordScreenState()
    object Empty: ChangePasswordScreenState()
}