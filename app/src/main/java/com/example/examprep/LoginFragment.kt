package com.example.examprep

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editLogin = view.findViewById<EditText>(R.id.editLogin)
        val editPassword = view.findViewById<EditText>(R.id.editPassword)
        val btnRegister = view.findViewById<Button>(R.id.btnRegister)

        btnRegister.setOnClickListener{
            val login = editLogin.text.toString().trim()
            val pass = editPassword.text.toString().trim()

            if (login.isEmpty()||pass.isEmpty())
            {
                Snackbar.make(it,"Введите логин и пароль", Snackbar.LENGTH_SHORT).setAction("ok"){}.show()
                return@setOnClickListener
            }
            if (login=="ects"&&pass=="ects2026")
            {
                val prefs = requireContext().getSharedPreferences("auth",Context.MODE_PRIVATE)
                prefs.edit().putString("login",login).putString("pass",pass).apply()

            val fragment = ShapeSelectionFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
            }else
            {
                Snackbar.make(it,"Неверный логин или пароль", Snackbar.LENGTH_SHORT).setAction("ok"){}.show()
            }
        }
    }
}