package com.l.androidjetpack

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.l.androidjetpack.fragment.*
import com.l.androidjetpack.helper.UserHelper
import kotlinx.android.synthetic.main.buttom_nav.*

class ButtonNav:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buttom_nav)
        if (!UserHelper(this).StatusLogin) {
            startActivity(Intent(this, Login::class.java))
            finish()
        }
        val menu: Menu = navigation.menu
        selectedMenu(menu.getItem(0))
        navigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            selectedMenu(item)
            true
        }
    }

    private fun selectedMenu(item: MenuItem) {
        when (item.itemId) {
            R.id.navigation_sync -> selectedFragment(SyncFragmen.getInstance())
            R.id.navigation_search-> selectedFragment(SearchFragment.getInstance())
            R.id.navigation_Add -> selectedFragment(AddFragment.getInstance())
            R.id.navigation_message -> selectedFragment(MessageFragment.getInstance())
            R.id.navigation_Account -> selectedFragment(AccountFragment.getInstance())
        }
    }

    private fun selectedFragment(fragment: Fragment) {
        val transaction: FragmentTransaction? = supportFragmentManager.beginTransaction()
        transaction?.replace(R.id.rootFragment, fragment)
        transaction?.commit()
    }
}
