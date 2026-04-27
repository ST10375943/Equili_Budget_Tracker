package com.example.equili

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.equili.data.model.UserModel
import com.example.equili.ui.viewModel.ExpenseViewModel
import com.example.equili.utils.ValidationUtils
import kotlinx.coroutines.launch

/**
 * RegisterActivity handles the creation of new user accounts.
 * Includes password validation and duplicate user checks.
 */
class RegisterActivity : AppCompatActivity() {

    private val viewModel: ExpenseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // UI component initialization
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        // Register button logic
        btnRegister.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()

            // Step 1: Basic validation - check if fields are empty
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Step 2: Security check - validate password complexity
            if (!ValidationUtils.isValidPassword(password)) {
                Toast.makeText(this, "Password must start with a capital, be 6+ characters, and contain a number and symbol", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Step 3: Database check and User creation
            lifecycleScope.launch {
                val existingUser = viewModel.getUserByEmail(email)
                if (existingUser != null) {
                    // Prevent duplicate accounts
                    Toast.makeText(this@RegisterActivity, "User already exists", Toast.LENGTH_SHORT).show()
                } else {
                    // Register the new user in the database
                    viewModel.registerUser(UserModel(email, password))
                    Toast.makeText(this@RegisterActivity, "Registration Successful", Toast.LENGTH_SHORT).show()

                    // Proceed to login screen
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }
}
