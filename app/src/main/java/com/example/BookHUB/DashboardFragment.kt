package com.example.BookHUB

import android.app.AlertDialog
import android.app.DownloadManager.Request
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.Volley
import model.Book
import org.json.JSONObject
import util.ConnectionManager


class DashboardFragment : Fragment() {
     lateinit var recyclerDashboard: RecyclerView
    lateinit var layoutManager:RecyclerView.LayoutManager
    val booklist= arrayListOf("P.S I LOVE YOU","MADAME BOVARY")
    lateinit var  recycylerAdadpter:DashboardrecyclerAdapter
    lateinit var btncheckinternet:Button
    val bookInfoList = arrayListOf<Book>(
        Book("P.S. I love You", "Cecelia Ahern", "Rs. 299", "4.5", R.drawable.ps_ily),
        Book("The Great Gatsby", "F. Scott Fitzgerald", "Rs. 399", "4.1", R.drawable.great_gatsby),
        Book("Anna Karenina", "Leo Tolstoy", "Rs. 199", "4.3", R.drawable.anna_kare),
        Book("Madame Bovary", "Gustave Flaubert", "Rs. 500", "4.0", R.drawable.madame),
        Book("War and Peace", "Leo Tolstoy", "Rs. 249", "4.8", R.drawable.war_and_peace),
        Book("Lolita", "Vladimir Nabokov", "Rs. 349", "3.9", R.drawable.lolita),
        Book("Middlemarch", "George Eliot", "Rs. 599", "4.2", R.drawable.middlemarch),
        Book("The Adventures of Huckleberry Finn", "Mark Twain", "Rs. 699", "4.5", R.drawable.adventures_finn),
        Book("Moby-Dick", "Herman Melville", "Rs. 499", "4.5", R.drawable.moby_dick),
        Book("The Lord of the Rings", "J.R.R Tolkien", "Rs. 749", "5.0", R.drawable.lord_of_rings)
    )



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_dashboard, container, false)
        recyclerDashboard=view.findViewById(R.id.recycylerview)
        btncheckinternet=view.findViewById(R.id.checking_only)
        btncheckinternet.setOnClickListener {
            if(ConnectionManager().checkconnectivity(activity as Context)){
                val dialog=AlertDialog.Builder(activity as Context)
                dialog.setTitle("Succes")
                dialog.setMessage("Internet Connection found")
                dialog.setNegativeButton("Ok"){
                    text,listener->
                }
                dialog.setNegativeButton("cancel"){
                    text,listener->
                }
                dialog.create()
                dialog.show()

            }
            else{
                val dialog=AlertDialog.Builder(activity as Context)
                dialog.setTitle("Succes")
                dialog.setMessage("Internet Connection not found")
                dialog.setNegativeButton("Ok"){
                        text,listener->
                }
                dialog.setNegativeButton("cancel"){
                        text,listener->
                }
                dialog.create()
                dialog.show()


            }
        }
        layoutManager=LinearLayoutManager(activity)
        recycylerAdadpter= DashboardrecyclerAdapter(activity as Context,bookInfoList)
        recyclerDashboard.adapter=recycylerAdadpter
        recyclerDashboard.layoutManager=layoutManager
        recyclerDashboard.addItemDecoration(
            DividerItemDecoration(
                recyclerDashboard.context,
                (layoutManager as LinearLayoutManager).orientation
            )
        )

   val queue=Volley.newRequestQueue(activity as Context)
   val url="http://13.235.250.119/v1/book/fetch_books/"
        val jsonobjectRequest=object :JSONObject(Request.Method.Get,url,null,Response.Listener{

        })

        return view
    }
}