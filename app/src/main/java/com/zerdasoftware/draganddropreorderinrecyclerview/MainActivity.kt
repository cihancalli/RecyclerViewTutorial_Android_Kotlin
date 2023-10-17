package com.zerdasoftware.draganddropreorderinrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var newRecyclerView:RecyclerView
    private lateinit var newsArrayList:ArrayList<News>
    lateinit var imageId:Array<Int>
    lateinit var heading:Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageId = arrayOf(
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a
        )

        heading = arrayOf(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed blandit volutpat erat, id auctor ex euismod at.",
            "Sed blandit volutpat erat, id auctor ex euismod at. Aenean sapien nisl, dignissim et imperdiet vitae, dictum laoreet odio.",
            "Donec volutpat eleifend condimentum. Duis gravida porta arcu, in tincidunt libero ornare vel. ",
            "Cras nec vulputate urna, at efficitur neque. Aenean vel aliquet tortor. Fusce mattis eget nisi non rhoncus. ",
            "Donec id viverra enim, eu gravida erat. Cras tempor ultricies fringilla. Aliquam iaculis augue nec odio blandit, vitae commodo ligula placerat.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed blandit volutpat erat, id auctor ex euismod at.",
            "Sed blandit volutpat erat, id auctor ex euismod at. Aenean sapien nisl, dignissim et imperdiet vitae, dictum laoreet odio.",
            "Donec volutpat eleifend condimentum. Duis gravida porta arcu, in tincidunt libero ornare vel. ",
            "Cras nec vulputate urna, at efficitur neque. Aenean vel aliquet tortor. Fusce mattis eget nisi non rhoncus. ",
            "Donec id viverra enim, eu gravida erat. Cras tempor ultricies fringilla. Aliquam iaculis augue nec odio blandit, vitae commodo ligula placerat.",
        )

        newRecyclerView = findViewById(R.id.recyclerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newsArrayList = arrayListOf<News>()
        getUserData()

    }

    private fun getUserData() {
        for (i in imageId.indices){
            val news = News(imageId[i],heading[i])
            newsArrayList.add(news)
        }

        newRecyclerView.adapter = MyAdapter(newsArrayList)
    }
}