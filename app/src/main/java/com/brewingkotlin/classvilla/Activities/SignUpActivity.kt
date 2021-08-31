package com.brewingkotlin.classvilla.Activities

import android.R.attr.country
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.brewingkotlin.classvilla.MainActivity
import com.brewingkotlin.classvilla.R
import com.brewingkotlin.classvilla.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity() , AdapterView.OnItemSelectedListener{
    lateinit var auth: FirebaseAuth
    var databaseReferenceStudent: DatabaseReference?=null
    var databaseReferenceTeacher: DatabaseReference?=null
    var database: FirebaseDatabase?=null
    var options= arrayOf("Students", "Teacher")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth= FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()
        databaseReferenceStudent= database?.reference!!.child("student")
        databaseReferenceTeacher= database?.reference!!.child("teacher")

        val aa: ArrayAdapter<*> =
            ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, options)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = aa
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
       if(position==0){
           registerStudent()
       }
        else
            registerTeacher()
    }

    private fun registerTeacher() {

        val name1= name.text.toString()
        val email1=email.text.toString()
        val about1=about.text.toString()
        val number=phone.text.toString()
        signup.setOnClickListener{
            if(TextUtils.isEmpty(name.text.toString())){
                name.setError("Mandatory Field") }
            else if(TextUtils.isEmpty(email.text.toString())){
                email.setError("Mandatory Field")}
            else if(TextUtils.isEmpty(about.text.toString())){
                about.setError("Mandatory Field")}
            else if(TextUtils.isEmpty(password.text.toString())){
                password.setError("Mandatory Field")}
            else if(TextUtils.isEmpty(phone.text.toString())){
                phone.setError("Mandatory Field")}
            auth.createUserWithEmailAndPassword(email.text.toString(),password.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        val currentUser= auth.currentUser
                        val currentUserDb=databaseReferenceTeacher?.child((currentUser?.uid!!))
                    currentUserDb?.child(currentUser?.uid!!)?.setValue(
                        User(name1,email1,"",about1,"Student",number,currentUser?.uid!!))

                        Toast.makeText(this,"Registration successful",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                    else{
                        Toast.makeText(this,"Registration Failed",Toast.LENGTH_SHORT).show()
                    }
                }
        }


    }

    private fun registerStudent() {
        val name1= name.text.toString()
        val email1=email.text.toString()
        val about1=about.text.toString()
        val number=phone.text.toString()
        signup.setOnClickListener{
            if(TextUtils.isEmpty(name.text.toString())){
                name.setError("Mandatory Field") }
            else if(TextUtils.isEmpty(email.text.toString())){
                email.setError("Mandatory Field")}
            else if(TextUtils.isEmpty(about.text.toString())){
                about.setError("Mandatory Field")}
            else if(TextUtils.isEmpty(password.text.toString())){
                password.setError("Mandatory Field")}
            else if(TextUtils.isEmpty(phone.text.toString())){
                phone.setError("Mandatory Field")}
            auth.createUserWithEmailAndPassword(email.text.toString(),password.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        val currentUser= auth.currentUser
                        val currentUserDb=databaseReferenceStudent?.child((currentUser?.uid!!))
                        currentUserDb?.child(currentUser?.uid!!)?.setValue(
                            User(name1,email1,"",about1,"Teacher",number,currentUser?.uid!!))

                        Toast.makeText(this,"Registration successful",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                    else{
                        Toast.makeText(this,"Registration Failed",Toast.LENGTH_SHORT).show()
                    }
                }
        }


    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


}
