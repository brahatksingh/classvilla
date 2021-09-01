package com.brewingkotlin.classvilla.Fragments.Search

import android.os.Bundle
import android.renderscript.Sampler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.brewingkotlin.classvilla.R
import com.brewingkotlin.classvilla.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment

        auth= FirebaseAuth.getInstance()
        val main_list = mutableListOf<User?>()
        val mDatabase :FirebaseDatabase = Firebase.database("https://classvilla-d75bb-default-rtdb.asia-southeast1.firebasedatabase.app")
        val myRefStudents = mDatabase.getReference("students")
        myRefStudents.addValueEventListener(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val gotList = snapshot.children
                gotList.forEach{
                    dataSnapshot ->
                    val tempuserobj = dataSnapshot.getValue(User::class.java)
                    main_list.add(tempuserobj)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("SearchFragment","Cancel")
            }

        })

        val myRefTeachers = mDatabase.getReference("Teachers")

        myRefTeachers.addValueEventListener(object :ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val gotList = snapshot.children
                gotList.forEach{ dataSnapshot ->
                    val tempuserobj = dataSnapshot.getValue(User::class.java)
                    main_list.add(tempuserobj)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("SearchFragment","Cancel")
            }

        })

        val rv = searchFragmentRV
        val adapter = SearchAdapter()
        adapter.setData(main_list)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(context)

        val etv = searchFragment_etv
        val btn = searchFragment_btn
        btn.setOnClickListener {
            val queryString : String = etv.text.toString()
            if(queryString.isEmpty()) {
                Toast.makeText(context,"Empty Text",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            main_list.forEach {
                user ->
                if(user?.name.equals(queryString)) {
                    val temp = user
                    main_list.clear()
                    main_list.add(user)
                    adapter.setData(main_list)
                    return@setOnClickListener
                }
            }
            Toast.makeText(context,"No Such User",Toast.LENGTH_SHORT).show()
        }

        return inflater.inflate(R.layout.fragment_search, container, false)
    }

}