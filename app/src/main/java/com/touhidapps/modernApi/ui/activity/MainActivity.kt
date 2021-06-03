package com.touhidapps.modernApi.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.touhidapps.modernApi.R
import com.touhidapps.modernApi.databinding.ActivityMainBinding
import com.touhidapps.modernApi.ui.fragment.ItemFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .addToBackStack("ItemFragment")
            .add(R.id.frameLayout, ItemFragment())
            .commit()

    } // onCreate

}


