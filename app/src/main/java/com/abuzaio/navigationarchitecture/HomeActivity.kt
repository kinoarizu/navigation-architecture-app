package com.abuzaio.navigationarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.MenuRes
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), OnNavigationItemSelectedListener {
    private lateinit var navController: NavController;

    private var currentMenuItem: Int = R.id.menu_home
    private var currentNavigation: Int? = R.id.frag_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar_home)

        navController = Navigation.findNavController(this, R.id.frag_home)
        NavigationUI.setupActionBarWithNavController(this, navController, dl_home)
        NavigationUI.setupWithNavController(nv_home, navController)

        dl_home.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        nv_home.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (dl_home.isDrawerOpen(GravityCompat.START)) {
            dl_home.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        updateMenu(R.id.menu_home, R.id.frag_home)
        return NavigationUI.navigateUp(navController, dl_home)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        menuItem.isChecked = false
        dl_home.closeDrawers()

        return when (menuItem.itemId) {
            currentMenuItem -> false
            R.id.menu_home -> {
                navController.navigate(R.id.frag_home)
                updateMenu(R.id.menu_home, R.id.frag_home)
                true
            }
            R.id.menu_history -> {
                navController.navigate(R.id.frag_history)
                updateMenu(R.id.menu_home, R.id.frag_history)
                true
            }
            R.id.menu_history -> {
                navController.navigate(R.id.frag_settings)
                updateMenu(R.id.menu_home, R.id.frag_settings)
                true
            }
            else -> {
                updateMenu(menuItem.itemId, navController.currentDestination?.id)
                false
            }
        }
    }


}