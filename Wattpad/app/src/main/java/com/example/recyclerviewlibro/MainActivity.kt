package com.example.recyclerviewlibro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.recyclerviewlibro.databinding.ActivityMainBinding
import com.example.recyclerviewlibro.fragments.ReadingLists

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(ReadingLists())

    }
    private fun replaceFragment(lecturaFragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, lecturaFragment)
        fragmentTransaction.commit()
    }

}