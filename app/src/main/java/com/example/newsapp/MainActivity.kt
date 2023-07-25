package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var dbRef: DatabaseReference
    var adapter = NewsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbRef = FirebaseDatabase.getInstance().reference

        binding.rcvnews.layoutManager = LinearLayoutManager(this)
        binding.rcvnews.adapter = adapter
//        var key = dbRef.root.push().key
//        var data = NewsModel(key!!, title, desc, image)
        dbRef.root.child("News").child("key").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                var Newslist = ArrayList<NewsModel>()
                Newslist.clear()
                for (snap in snapshot.children) {
                    var model = snap.getValue(NewsModel::class.java)
                    Newslist.add(model!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })
    }
}