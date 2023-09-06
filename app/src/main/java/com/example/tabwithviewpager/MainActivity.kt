package com.example.tabwithviewpager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import com.example.tabwithviewpager.Adapter.ViewPagerAdapter
import com.example.tabwithviewpager.Fragments.FirstFragment
import com.example.tabwithviewpager.Fragments.SecondFragment
import com.example.tabwithviewpager.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnNext.setOnClickListener {
            startActivity(Intent(this@MainActivity, SecondActivity::class.java))
        }


        viewPagerAdapter = ViewPagerAdapter(this.supportFragmentManager, this.lifecycle)
        viewPagerAdapter.addFragment(FirstFragment(), "First Fragment")
        viewPagerAdapter.addFragment(SecondFragment(), " Second Fragment")


        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.offscreenPageLimit = 1

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = viewPagerAdapter.titleList[position]
        }.attach()

        for (i in 0 until binding.tabLayout.tabCount) {
            val tv: TextView = LayoutInflater.from(this@MainActivity)
                .inflate(R.layout.custom_tab, null) as TextView
            binding.tabLayout.getTabAt(i)?.customView = tv
        }


//        TabLayoutMediator.TabConfigurationStrategy { tab, position ->
//            tab.text = viewPagerAdapter.titleList[position]
//            for (i in 0 until binding.tabLayout.tabCount) {
//                val tv: TextView =
//                    LayoutInflater.from(this).inflate(R.layout.custom_tab, null) as TextView
//                binding.tabLayout.getTabAt(i)!!.setCustomView(tv)
//            }
//        }


    }
}