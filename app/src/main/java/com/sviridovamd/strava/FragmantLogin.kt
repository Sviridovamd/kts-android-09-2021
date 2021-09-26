package com.sviridovamd.strava

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class FragmantLogin : Fragment(R.layout.fragment_login) {
    private var pass: TextInputEditText? = null
    private var email: TextInputEditText? = null
    private var emailLayout: TextInputLayout? = null
    private var passLayout: TextInputLayout? = null
    private var loginBtn: Button? = null
    val model: AppViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailLayout = view.findViewById(R.id.email_field_layout)
        passLayout = view.findViewById(R.id.password_field_layout)
        email = view.findViewById(R.id.email_field)
        pass = view.findViewById(R.id.password_field)
        loginBtn = view.findViewById(R.id.login_button)
        bindFields()
    }

    private fun bindFields() {
        pass?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                model.onEditPass(s.toString())
            }
        })
        email?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                model.onEditEmail(s.toString())
            }
        })
        model.loginEmailValid.observe(viewLifecycleOwner, { valid ->
            emailLayout?.isErrorEnabled = !valid
            if (!valid)
                emailLayout?.error = getString(R.string.login_email_warning)
        })
        model.loginPassValid.observe(viewLifecycleOwner, { valid ->
            passLayout?.isErrorEnabled = !valid
            if (!valid)
                passLayout?.error = getString(R.string.login_min_length_warning)
        })
        model.loginState.observe(viewLifecycleOwner, { valid ->
            if (valid)
                loginBtn?.alpha = 1.0F
            else
                loginBtn?.alpha = 0.5F
        })
        loginBtn?.setOnClickListener {
            if (model.loginState.value == true)
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
        //Автоматическое нажатие на кнопки при валидном вводе+нажатии Enter
        pass?.setOnEditorActionListener { _, _, _ ->
            if (model.loginState.value == true) {
                loginBtn?.performClick()
            }
            return@setOnEditorActionListener (model.loginState.value != true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        pass = null
        email = null
        passLayout = null
        emailLayout = null
        loginBtn = null
    }
}