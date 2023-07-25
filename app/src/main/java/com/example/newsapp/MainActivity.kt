package com.example.newsapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.newsapp.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var dbRef: DatabaseReference
    var adapter = NewsAdapter()
    lateinit var binding: ActivityMainBinding
    lateinit var uri: Uri
    val IMAGE_CODE = 3
    lateinit var storageRef: StorageReference
    lateinit var dbRef: DatabaseReference

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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        dbRef = FirebaseDatabase.getInstance().reference
        storageRef = FirebaseStorage.getInstance().reference

        binding.addimg.setOnClickListener {

            var intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent,IMAGE_CODE)
        }

        binding.uploadimg.setOnClickListener {

            val ref = storageRef.child("images/${uri.lastPathSegment}.jpg")
            var uploadTask = ref.putFile(uri)

            val urlTask = uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                ref.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    var key = dbRef.root.push().key
                    dbRef.root.child("Images").child(key!!).child("images").setValue(downloadUri.toString())
                } else {
                    // Handle failures
                    // ...
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {

            if (requestCode == IMAGE_CODE) {
                var uri = data?.data!!
                binding.imgposter.setImageURI(uri)
            }

        }
    }
}

