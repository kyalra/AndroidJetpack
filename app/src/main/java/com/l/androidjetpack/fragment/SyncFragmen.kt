package com.l.androidjetpack.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.l.androidjetpack.R

class SyncFragmen:Fragment() {
    companion object{
        fun getInstance():SyncFragmen= SyncFragmen()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home,container,false)
    }
}