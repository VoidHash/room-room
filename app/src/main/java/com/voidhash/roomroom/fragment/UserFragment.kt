package com.voidhash.roomroom.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.voidhash.roomroom.R
import com.voidhash.roomroom.db.AppDatabase
import com.voidhash.roomroom.entity.User
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val edtFirstName: EditText = edtFirstName
        val edtLastName: EditText = edtLastName

        val btnSubmit = btnSubmit
        btnSubmit.setOnClickListener {
            if(validateForm()) {
                submitUser()
            }
        }
    }

    private fun submitUser() {
        val db = AppDatabase.getDatabase(requireContext())
        val userDao = db.userDao()

        val userUpdate: User =
            User(null, edtFirstName.text.toString(), edtLastName.text.toString())
        userDao.insertAll(userUpdate)

        val fm: FragmentManager = (requireActivity() as AppCompatActivity).supportFragmentManager
        fm.popBackStack()
    }

    private fun validateForm(): Boolean {
        if(edtFirstName.length() == 0) {
            edtFirstName.error = "This field is required"
            return false
        }
        if(edtLastName.length() == 0) {
            edtLastName.error = "This field is required"
            return false
        }
        return true
    }

}