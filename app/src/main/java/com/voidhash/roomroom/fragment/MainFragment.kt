package com.voidhash.roomroom.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.voidhash.roomroom.R
import com.voidhash.roomroom.adapter.UserAdapter
import com.voidhash.roomroom.dao.UserDao
import com.voidhash.roomroom.db.AppDatabase
import com.voidhash.roomroom.entity.User
import com.voidhash.roomroom.listener.OnButtonClickListener
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), OnButtonClickListener {

    private lateinit var adapter: UserAdapter
    private lateinit var myList: MutableList<User>
    private lateinit var userDao: UserDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = AppDatabase.getDatabase(requireContext())
        userDao = db.userDao()
        myList = mutableListOf()

        val recycleView: RecyclerView = rclUser
        val layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL, false)
        recycleView.layoutManager = layoutManager
        recycleView.itemAnimator = null
        adapter = UserAdapter(myList, requireContext())
        adapter.setOnClickListener(this)
        recycleView.adapter = adapter

        val btnListAll = btnList
        btnListAll.setOnClickListener {
            //Just to test your list function
            //myList = mutableListOf(User(null,"Void", "Hash"))
            //userDao.insertAll(myList[0])
            val allUsers = listAllUser(userDao)
            adapter.setUserList(allUsers)
        }

        val btnInsert = btnInsert
        btnInsert.setOnClickListener {
            insertUser()
        }
    }
    private fun listAllUser(userDao: UserDao): MutableList<User> {
        return userDao.getAll().toMutableList()
    }

    private fun insertUser() {
        val fm: FragmentManager = (requireActivity() as AppCompatActivity).supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.replace(R.id.fragmentContainerView, UserFragment())
        ft.addToBackStack(null)
        ft.commit()
    }

    override fun onEditUser() {
        val fm: FragmentManager = (requireActivity() as AppCompatActivity).supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        val frag = UserFragment()
        ft.replace(R.id.fragmentContainerView, frag)
        ft.addToBackStack(null)
        ft.commit()
    }

    override fun onDeleteUser() {
    }
}