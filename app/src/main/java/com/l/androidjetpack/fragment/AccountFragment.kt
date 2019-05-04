package com.l.androidjetpack.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.l.androidjetpack.R
import com.l.androidjetpack.helper.UserHelper
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment:Fragment() {
    companion object{
        fun getInstance():AccountFragment= AccountFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name_profile.text="Hello  "+UserHelper(view.context).Name

    }
}