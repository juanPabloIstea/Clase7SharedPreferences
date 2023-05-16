package com.example.clase7sharedpreferences

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var buttonToAction: Button
    private lateinit var textViewName: TextView
    private lateinit var editTextUserNameLogin: EditText
    private lateinit var editTextPassworLogin: EditText
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonToAction = findViewById(R.id.buttonLogin)
        textViewName = findViewById(R.id.textViewNombre)
        editTextPassworLogin = findViewById(R.id.editTextPasswordLogin)
        editTextUserNameLogin = findViewById(R.id.editTextNameLogin)
        imageView = findViewById(R.id.imageView)

        val preferences = getSharedPreferences("loginPref", MODE_PRIVATE)
        val userNamePref = preferences.getString("name", "")
        val passwordPref = preferences.getString("pass", "")

        if (userNamePref != null && passwordPref != null) {
            if (userNamePref.isEmpty()) {
                buttonToAction.text = "registrar"
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }

        buttonToAction.setOnClickListener {
            if (editTextUserNameLogin.text.isNullOrEmpty() && editTextPassworLogin.text.isNullOrEmpty()) {
                Toast.makeText(this, "Ingrese user y password", Toast.LENGTH_SHORT).show()
            } else {
                val name = editTextUserNameLogin.text.toString()
                val pass = editTextPassworLogin.text.toString()

                if (name == userNamePref && pass == passwordPref) {
                    val intent = Intent(this, FinalActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "usuario o contraseña no valida", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}