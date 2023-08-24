package com.example.newsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    var adapter = NewsAdapter()
    lateinit var binding: ActivityMainBinding
    lateinit var uri: Uri
    val IMAGE_CODE = 3
    lateinit var userArrayList: ArrayList<NewsModel>
    lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getNews()

        binding.rcvnews.layoutManager = LinearLayoutManager(this)
        binding.rcvnews.adapter = adapter

    }

    private fun getNews() {

        dbRef = FirebaseDatabase.getInstance().getReference("News")

        dbRef.root.child("News").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                var Newslist = ArrayList<NewsModel>()
                Newslist.clear()
                for (snap in snapshot.children) {
                    var model = snap.getValue(NewsModel::class.java)
                    Newslist.add(model!!)
                }
                adapter.update(Newslist)
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}

