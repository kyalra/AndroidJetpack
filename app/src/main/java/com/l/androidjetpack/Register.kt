package com.l.androidjetpack

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.register.*

class Register:AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        mAuth = FirebaseAuth.getInstance()

        btnRegister.setOnClickListener {
            if (etUsernameReg.text.toString() == "" && etEmailReg.text.toString() == "" &&
                etPasswordReg.text.toString() == "" && etConfirmPasswordReg.text.toString() == ""
            ) {
                Toast.makeText(this, "Please check your credentials again", Toast.LENGTH_SHORT).show()
            } else {
                if (etPasswordReg.text.toString() == etConfirmPasswordReg.text.toString()) {
                    registerWithFirebase(etEmailReg.text.toString(), etPasswordReg.text.toString())
                } else {
                    Toast.makeText(this, "Please check your credentials again", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }

    private fun registerWithFirebase(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("REGISTER", "Register Success")
                    val user = mAuth.currentUser
                    updateUI(user)
                } else {
                    Log.w("REGISTER", "Register Failed", task.exception)
                    Toast.makeText(this, "Register Failed", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }
}
