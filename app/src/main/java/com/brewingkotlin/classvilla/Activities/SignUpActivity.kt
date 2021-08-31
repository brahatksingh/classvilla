package com.brewingkotlin.classvilla.Activities

import android.Manifest

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
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
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.UploadTask
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity() , AdapterView.OnItemSelectedListener{
    lateinit var auth: FirebaseAuth
    var databaseReferenceStudent: DatabaseReference?=null
    var databaseReferenceTeacher: DatabaseReference?=null
    var database: FirebaseDatabase?=null
    var storage: FirebaseStorage?=null
     var options= arrayOf("Students", "Teacher")
    private lateinit var downloadUrl: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth= FirebaseAuth.getInstance()
        database= FirebaseDatabase.getInstance()
        databaseReferenceStudent= database?.reference!!.child("student")
        databaseReferenceTeacher= database?.reference!!.child("teacher")
        storage= FirebaseStorage.getInstance()


        val aa: ArrayAdapter<*> =
            ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, options)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = aa

        userImgView.setOnClickListener {
            checkPermissionForImage()
        }
        signin.setOnClickListener {
            val intent=Intent(applicationContext,SignInActivity::class.java)
            startActivity(intent)
        }
        signup.setOnClickListener{


            if(TextUtils.isEmpty(name.text.toString())){
                name.error = "Mandatory Field"
            }
            else if(TextUtils.isEmpty(email.text.toString())){
                email.error = "Mandatory Field"
            }
            else if(TextUtils.isEmpty(about.text.toString())){
                about.error = "Mandatory Field"
            }
            else if(TextUtils.isEmpty(password.text.toString())){
                password.error = "Mandatory Field"
            }
            else if(TextUtils.isEmpty(phone.text.toString())){
                phone.error = "Mandatory Field"
            }
            else{
                spinner.onItemSelectedListener=this
            }

        }
    }

    private fun checkPermissionForImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                && (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
            ) {
                val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                val permissionWrite = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

                requestPermissions(
                    permission,
                    1001
                ) // GIVE AN INTEGER VALUE FOR PERMISSION_CODE_READ LIKE 1001
                requestPermissions(
                    permissionWrite,
                    1002
                ) // GIVE AN INTEGER VALUE FOR PERMISSION_CODE_WRITE LIKE 1002
            } else {
                pickImageFromGallery()
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(
            intent,
            1000
        ) // GIVE AN INTEGER VALUE FOR IMAGE_PICK_CODE LIKE 1000
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 1000) {
            data?.data?.let {
                userImgView.setImageURI(it)
                startUpload(it)
            }
        }
    }

    private fun startUpload(filePath: Uri) {
        signup.isEnabled = true
        val ref = storage?.reference?.child("uploads/" + auth.uid.toString())
        val uploadTask = ref?.putFile(filePath)
        uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            return@Continuation ref.downloadUrl
        })?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                downloadUrl = task.result.toString()
                signup.isEnabled = true
            } else {
                signup.isEnabled = true
                // Handle failures
            }
        }?.addOnFailureListener {

        }
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
            auth.createUserWithEmailAndPassword(email.text.toString(),password.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        val currentUser= auth.currentUser
                        val currentUserDb=databaseReferenceTeacher?.child((currentUser?.uid!!))
                    currentUserDb?.child(currentUser?.uid!!)?.setValue(
                        User(name1,email1,downloadUrl,about1,"Teacher",number,currentUser?.uid!!))

                        Toast.makeText(this,"Registration successful",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                    else{
                        Toast.makeText(this,"Registration Failed",Toast.LENGTH_SHORT).show()
                    }
                }
        }




    private fun registerStudent() {
        val name1= name.text.toString()
        val email1=email.text.toString()
        val about1=about.text.toString()
        val number=phone.text.toString()
            auth.createUserWithEmailAndPassword(email.text.toString(),password.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        val currentUser= auth.currentUser
                        val currentUserDb=databaseReferenceStudent?.child((currentUser?.uid!!))
                        currentUserDb?.child(currentUser?.uid!!)?.setValue(
                            User(name1,email1,downloadUrl,about1,"Student",number,currentUser?.uid!!))

                        Toast.makeText(this,"Registration successful",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                    else{
                        Toast.makeText(this,"Registration Failed",Toast.LENGTH_SHORT).show()
                    }
                }
        }




    private fun toast(s: String) {
        Toast.makeText(this,"s", Toast.LENGTH_SHORT).show()

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


}
