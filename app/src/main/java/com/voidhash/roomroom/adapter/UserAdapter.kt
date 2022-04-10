package com.voidhash.roomroom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.voidhash.roomroom.R
import com.voidhash.roomroom.entity.User
import com.voidhash.roomroom.listener.OnButtonClickListener
import kotlinx.android.synthetic.main.item_view.view.*

class UserAdapter(private var userList: MutableList<User>, private val context: Context):
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var listener: OnButtonClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = userList[position]
        holder?.let {
            it.bindView(currentItem, it.adapterPosition, listener)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setUserList(newList: MutableList<User>) {
        userList = newList
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: OnButtonClickListener) {
        this.listener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        private val txtFirstName: TextView = itemView.txtFirstName
        private val txtLastName: TextView = itemView.txtLastName
        private val btnEdit: ImageButton = itemView.btnEdit
        private val btnDelete: ImageButton = itemView.btnDelete

        fun bindView(item: User, position: Int, listener: OnButtonClickListener) {
            txtFirstName.text = item.firstName
            txtLastName.text = item.lastName
            btnEdit.setOnClickListener {  }
            btnDelete.setOnClickListener {  }
        }
    }
}