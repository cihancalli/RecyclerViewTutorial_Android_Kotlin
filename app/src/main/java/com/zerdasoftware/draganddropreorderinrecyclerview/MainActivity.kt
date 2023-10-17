package com.zerdasoftware.draganddropreorderinrecyclerview

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var newsRecyclerView:RecyclerView
    private lateinit var newsArrayList:ArrayList<News>
    private lateinit var tempArrayList:ArrayList<News>
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

        newsRecyclerView = findViewById(R.id.recyclerView)
        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        newsRecyclerView.setHasFixedSize(true)

        newsArrayList = arrayListOf()
        tempArrayList = arrayListOf()
        getUserData()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_item,menu)
        val item = menu?.findItem(R.id.search_action)
        val searchView = item?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean { return false }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                tempArrayList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    newsArrayList.forEach {
                        if (it.heading.toLowerCase(Locale.getDefault()).contains(searchText)){ tempArrayList.add(it) }
                    }
                    newsRecyclerView.adapter!!.notifyDataSetChanged()
                }else{
                    tempArrayList.clear()
                    tempArrayList.addAll(newsArrayList)
                    newsRecyclerView.adapter!!.notifyDataSetChanged()
                }

                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun getUserData() {
        for (i in imageId.indices){
            val news = News(imageId[i],heading[i])
            newsArrayList.add(news)
        }

        tempArrayList.addAll(newsArrayList)

        val adapter = MyAdapter(tempArrayList)

        val swipeGesture = object : SwipeGesture (this){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val from_pos = viewHolder.adapterPosition
                val to_pos = target.adapterPosition

                Collections.swap(newsArrayList,from_pos,to_pos)
                adapter.notifyItemMoved(from_pos,to_pos)
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when(direction){
                    ItemTouchHelper.LEFT -> { adapter.deleteItem(viewHolder.adapterPosition) }

                    ItemTouchHelper.RIGHT -> {
                        val archiveItem = newsArrayList[viewHolder.adapterPosition]
                        adapter.deleteItem(viewHolder.adapterPosition)
                        adapter.addItem(newsArrayList.size,archiveItem)
                    }
                }
            }
        }

        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(newsRecyclerView)
        newsRecyclerView.adapter = adapter

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