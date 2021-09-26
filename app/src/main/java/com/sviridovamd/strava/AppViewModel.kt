package com.sviridovamd.strava

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel() : ViewModel() {
    private val loginEmailValidMutable = MutableLiveData(false)
    val loginEmailValid: LiveData<Boolean>
        get() = loginEmailValidMutable

    private val loginPassValidMutable = MutableLiveData(false)
    val loginPassValid: LiveData<Boolean>
        get() = loginPassValidMutable

    private val loginValidMutable = MutableLiveData(false)
    val loginState: LiveData<Boolean>
        get() = loginValidMutable

    init {
        loginPassValid.observeForever{ passValid ->
            if (passValid && loginEmailValid.value == true)
                loginValidMutable.value = true
        }
        loginEmailValid.observeForever{ emailValid ->
            if (emailValid && loginPassValid.value == true)
                loginValidMutable.value = true
        }
    }

    fun onEditPass(newPass: String?) {
        loginPassValidMutable.value = newPass?.length ?: 0 >= PASSWORD_MIN_LENGTH
    }

    fun onEditEmail(newEmail: String?) {
        loginEmailValidMutable.value = Patterns.EMAIL_ADDRESS.matcher(newEmail ?: "").matches()
    }

    companion object {
        private const val PASSWORD_MIN_LENGTH: Int = 8
    }

}