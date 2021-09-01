package com.brewingkotlin.classvilla.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.brewingkotlin.classvilla.MainActivity
import com.brewingkotlin.classvilla.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        auth= FirebaseAuth.getInstance()
        signin_signin.setOnClickListener {
            login()
        }

        signin.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }

    }

    fun login()  {


            if(TextUtils.isEmpty(email.text.toString())){
                email.error = "Mandatory Field"
                email.requestFocus()
            }
            else if(TextUtils.isEmpty(password.text.toString())){
                password.error = "Mandatory Field"
                password.requestFocus()
            }

            auth.signInWithEmailAndPassword(email.text.toString(),password.text.toString())
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    else{
                        Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show()
                }
        }
    }
}