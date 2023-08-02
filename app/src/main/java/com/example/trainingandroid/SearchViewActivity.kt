package com.example.trainingandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class SearchViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_view)

        val users =  arrayOf("Juan","María","Carlos","Ana","Pedro","Sofía","Luis","Valentina","Andrés","Isabella","Javier","Camila","Diego","Lucía",
            "Fernando","Mariana","Gabriel","Renata","Alejandro","Martina","Emilio","Sara","Mateo","Olivia","Nicolás")
        val adapterUser: ArrayAdapter<String> =  ArrayAdapter(this,android.R.layout.simple_list_item_1,users)
        var svUsers = findViewById<androidx.appcompat.widget.SearchView>(R.id.svUsers)
        var listView = findViewById<ListView>(R.id.lvUsers)
        listView.adapter = adapterUser
        svUsers.setOnQueryTextListener(object:androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                svUsers.clearFocus()
                if(users.contains(query)) adapterUser.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                adapterUser.filter.filter(query)
                return false
            }

        })
    }
}