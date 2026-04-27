package com.example.equili

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.equili.ui.viewModel.ExpenseViewModel
import kotlinx.coroutines.launch

/**
 * LoginActivity handles user authentication.
 * It verifies credentials against the local Room database and starts a session.
 */
class LoginActivity : AppCompatActivity() {

    private val viewModel: ExpenseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // UI component initialization
        val etUser = findViewById<EditText>(R.id.etUsername)
        val etPass = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // Login button click logic
        btnLogin.setOnClickListener {
            val email = etUser.text.toString().trim()
            val pass = etPass.text.toString()

            // Validate that input fields are not empty
            if (email.isNotEmpty() && pass.isNotEmpty()) {
                // Launch coroutine to perform database lookup off the main thread
                lifecycleScope.launch {
                    val user = viewModel.getUserByEmail(email)

                    // Simple password verification
                    if (user != null && user.password == pass) {
                        // Persist session locally using SharedPreferences
                        getSharedPreferences("EquiliPrefs", MODE_PRIVATE).edit()
                            .putString("CURRENT_USER", email)
                            .apply()

                        // Navigate to the dashboard on successful login
                        val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                        startActivity(intent)
                        finish() // Prevent user from returning to login screen via 'Back'
                    } else {
                        // Feedback for failed authentication
                        Toast.makeText(this@LoginActivity, "Invalid email or password", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please enter login details", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
