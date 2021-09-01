package com.brewingkotlin.classvilla.Fragments.Home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.brewingkotlin.classvilla.R
import com.brewingkotlin.classvilla.Room
import com.brewingkotlin.classvilla.UserDemo
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_user_search.view.*

class HomeFragment : Fragment() {
    private lateinit var adapterR : RoomAdapter
    private lateinit var adapterU : UserAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        adapterR = RoomAdapter(makeListRoom())
        adapterU = UserAdapter(makeListUser())
        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onStart() {
        homefragment_rv_rooms.adapter = adapterR
        rvStudents.adapter = adapterU
        homefragment_rv_rooms.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        rvStudents.layoutManager = LinearLayoutManager(context)
        super.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_fragment_home,menu)
    }

    fun makeListRoom() : List<Room> {
        val listRoom = listOf<String>("Maths","Science","SST","English","WEXP")
        val listName = listOf<String>("Vishal Saini","Bryan Cranston","Creed Bratton","John Karsinski","Aaron Paul")
        val listlist = mutableListOf<Room>()
        for (x in 0..4) {
            val newRoom = Room(listRoom[x],listName[x])
            listlist.add(newRoom)
        }
        return listlist
    }

    fun makeListUser() : List<UserDemo>{
        val list = listOf<String>("Deepali Verma","Brahat K Singh","Diksha Gupta","Mohd. Syed","Barry Krpike")
        val listlist = mutableListOf<UserDemo>()
        for (x in 0..4) {
            val newRoom = UserDemo(list[x])
            listlist.add(newRoom)
        }
        return listlist
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.mainactivity_search -> {
                // Navigate to Search Fragment
            }
            R.id.mainactivity_calendar -> {
                val builder = AlertDialog.Builder(requireContext())
                builder.setPositiveButton("Okay") { one,two ->
                    // Does Nothing
                }
                builder.setTitle("Calendar")
                builder.setMessage("Calendar is here")
                builder.create().show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}