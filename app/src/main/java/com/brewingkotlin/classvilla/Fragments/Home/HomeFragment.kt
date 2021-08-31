package com.brewingkotlin.classvilla.Fragments.Home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.brewingkotlin.classvilla.R

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_home,container,false)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_fragment_home,menu)
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