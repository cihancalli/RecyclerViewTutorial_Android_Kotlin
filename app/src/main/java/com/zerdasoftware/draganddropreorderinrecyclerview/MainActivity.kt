package com.zerdasoftware.draganddropreorderinrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var newRecyclerView:RecyclerView
    private lateinit var newsArrayList:ArrayList<News>
    lateinit var imageId:Array<Int>
    lateinit var heading:Array<String>
    lateinit var news: Array<String>
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
            getString(R.string.lorem_ipsum_list_a),
            getString(R.string.lorem_ipsum_list_b),
            getString(R.string.lorem_ipsum_list_c),
            getString(R.string.lorem_ipsum_list_d),
            getString(R.string.lorem_ipsum_list_e),
            getString(R.string.lorem_ipsum_list_a),
            getString(R.string.lorem_ipsum_list_b),
            getString(R.string.lorem_ipsum_list_c),
            getString(R.string.lorem_ipsum_list_d),
            getString(R.string.lorem_ipsum_list_e)
        )

        news = arrayOf(
            getString(R.string.lorem_ipsum_paragraph),
            getString(R.string.lorem_ipsum_paragraph),
            getString(R.string.lorem_ipsum_paragraph),
            getString(R.string.lorem_ipsum_paragraph),
            getString(R.string.lorem_ipsum_paragraph),
            getString(R.string.lorem_ipsum_paragraph),
            getString(R.string.lorem_ipsum_paragraph),
            getString(R.string.lorem_ipsum_paragraph),
            getString(R.string.lorem_ipsum_paragraph),
            getString(R.string.lorem_ipsum_paragraph),
        )

        newRecyclerView = findViewById(R.id.recyclerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newsArrayList = arrayListOf()
        getUserData()

    }

    private fun getUserData() {
        for (i in imageId.indices){
            val news = News(imageId[i],heading[i])
            newsArrayList.add(news)
        }

        var adapter = MyAdapter(newsArrayList)
        newRecyclerView.adapter = adapter

        adapter.setOnItemClickListener(object :MyAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                //Toast.makeText(this@MainActivity ,"You Clicked on item no. $position",Toast.LENGTH_SHORT).show()

                val intent = Intent(this@MainActivity,NewsActivity::class.java)
                intent.putExtra("heading",newsArrayList[position].heading)
                intent.putExtra("imageId",newsArrayList[position].titleImage)
                intent.putExtra("news",news[position])
                startActivity(intent)
            }
        })
    }
}