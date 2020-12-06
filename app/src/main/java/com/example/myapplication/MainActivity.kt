package com.example.myapplication

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.SupportActionModeWrapper
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import java.lang.ClassCastException

class MainActivity : AppCompatActivity(),ViewPager.OnPageChangeListener {

    val dr by lazy{
        val drawerLayout:DrawerLayout=findViewById(R.id.drawar)
        val toolbar:Toolbar = findViewById(R.id.toolbar)
        ActionBarDrawerToggle(this,drawerLayout ,toolbar,R.string.dropen,R.string.reclose)
    }
    lateinit var bot : BottomNavigationView
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        val cMenuItem =bot.menu.getItem(position).itemId
        if (cMenuItem != bot.selectedItemId){
            bot.menu.getItem(position).isChecked =true
            bot.menu.findItem(bot.selectedItemId).isChecked = false
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar:Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
//        val drawerLayout:DrawerLayout=findViewById(R.id.drawar)

        val nav : NavigationView = findViewById(R.id.nav)
        nav.setNavigationItemSelectedListener {
            onclickitem(it)
        }
//        drawerLayout.addDrawerListener(dr)

        bot = findViewById(R.id.bottmnav)
        bot.setOnNavigationItemSelectedListener {
          onclickitem(it)
        }

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        dr.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        dr.onConfigurationChanged(newConfig)
    }

    private fun onclickitem(item: MenuItem) : Boolean{
        val viewpagerhelper = viewpagerclass(supportFragmentManager)
        val viewPager : ViewPager = findViewById(R.id.viewpager)
        viewPager.adapter = viewpagerhelper
//        val drawerLayout :DrawerLayout = findViewById(R.id.drawar)
//        println("-------------------")
//        println("--------- ${item.itemId}----------")
        when (item.itemId){
            R.id.blank -> viewPager.currentItem =0
            R.id.home -> viewPager.currentItem =1
            else -> viewPager.currentItem =1
        }
        val x = viewPager.currentItem
        println(x)
//        println("---------${viewPager.currentItem}----------")
        viewPager.addOnPageChangeListener(this)
        // to put slide (left to right ) of the navigation br
//        if(drawerLayout.isDrawerOpen(GravityCompat.START))
//        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

//     private fun replaceFregment (fragment: Fragment){
//         val ft = supportFragmentManager.beginTransaction()
//         ft.replace(R.id.contaner_layouit, fragment)
//         ft.commit()
//     }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (dr.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(item)
    }



    /*
    To create a 3 dot menu in the right top og the toolbar
    */

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu,menu)
//        return true
//    }


}