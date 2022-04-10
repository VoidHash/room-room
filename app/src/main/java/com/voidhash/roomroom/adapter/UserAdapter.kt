package com.voidhash.roomroom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.voidhash.roomroom.R
import com.voidhash.roomroom.listener.OnButtonClickListener
import kotlinx.android.synthetic.main.item_view.view.*

class UserAdapter(private val context: Context):
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var listener: OnButtonClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.let {
            it.bindView(it.adapterPosition, listener)
        }
    }

    override fun getItemCount(): Int {
        return 0
    }

    fun setOnClickListener(listener: OnButtonClickListener) {
        this.listener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {

        private val txtFirstName: TextView = itemView.txtFirstName
        private val txtLastName: TextView = itemView.txtLastName
        private val btnEdit: ImageButton = itemView.btnEdit
        private val btnDelete: ImageButton = itemView.btnDelete

        fun bindView(position: Int, listener: OnButtonClickListener) {
            btnEdit.setOnClickListener {  }
            btnDelete.setOnClickListener {  }
        }
    }
}