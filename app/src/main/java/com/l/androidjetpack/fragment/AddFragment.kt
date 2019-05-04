package com.l.androidjetpack.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.l.androidjetpack.R
import com.l.androidjetpack.upload.FullCost
import com.l.androidjetpack.upload.OtherAsk
import com.l.androidjetpack.upload.PeopleWanted
import kotlinx.android.synthetic.main.fragmen_upload.*

class AddFragment:Fragment() {
    companion object {
        fun getInstance(): AddFragment = AddFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragmen_upload, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnUpload_1.setOnClickListener {
            val intent = Intent(context, FullCost::class.java)
            startActivity(intent)
        }
        btnUpload_2.setOnClickListener {
            val intent = Intent(context, OtherAsk::class.java)
            startActivity(intent)
        }
        btnUpload_3.setOnClickListener {
            val intent = Intent(context, PeopleWanted::class.java)
            startActivity(intent)
        }
    }
}

