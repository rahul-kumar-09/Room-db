package com.example.roomdbapp.fragments.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbapp.R
import com.example.roomdbapp.data.User

class ListAdapter(val context: Context) : RecyclerView.Adapter<com.example.roomdbapp.fragments.list.ListAdapter.MyListAdapter>() {

        private var userList = emptyList<User>()

    class MyListAdapter(view: View): RecyclerView.ViewHolder(view){
        val txtId: TextView = view.findViewById(R.id.txt_id)
        val txtFirstName: TextView = view.findViewById(R.id.txt_first_name)
        val txtLastName: TextView = view.findViewById(R.id.txt_last_name)
        val txtAge: TextView = view.findViewById(R.id.txt_age)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListAdapter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        return MyListAdapter(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyListAdapter, position: Int) {
        val currentItem = userList[position]
        holder.txtId.text = currentItem.id.toString()
        holder.txtFirstName.text = currentItem.firstName
        holder.txtLastName.text = currentItem.lastName
        holder.txtAge.text = currentItem.age.toString()
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}