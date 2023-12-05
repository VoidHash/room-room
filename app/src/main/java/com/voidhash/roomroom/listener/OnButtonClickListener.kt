package com.voidhash.roomroom.listener

import com.voidhash.roomroom.entity.User

interface OnButtonClickListener {
    fun onEditUser(item: User)
    fun onDeleteUser(item: User, position: Int)
}