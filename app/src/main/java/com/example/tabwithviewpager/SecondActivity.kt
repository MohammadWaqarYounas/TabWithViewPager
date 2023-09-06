package com.example.tabwithviewpager

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.tabwithviewpager.Adapter.TabFragmentAdapter
import com.example.tabwithviewpager.Fragments.FirstFragment
import com.example.tabwithviewpager.Fragments.SecondFragment
import com.example.tabwithviewpager.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var indicatorWidth = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Set up the view pager and fragments
        val adapter = TabFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(FirstFragment(), "Tab 1")
        adapter.addFragment(SecondFragment(), "Tab 2")
        binding.viewPager.setAdapter(adapter)
        binding.tab.setupWithViewPager(binding.viewPager)

        // Determine indicator width at runtime
        binding.tab.post {
            indicatorWidth = binding.tab.width / binding.tab.tabCount

            // Assign new width
            val indicatorParams = binding.indicator.layoutParams as FrameLayout.LayoutParams
            indicatorParams.width = indicatorWidth
            binding.indicator.layoutParams = indicatorParams
        }

        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            // To move the indicator as the user scrolls, we will need the scroll offset values
            // positionOffset is a value from [0..1] which represents how far the page has been scrolled
            override fun onPageScrolled(i: Int, positionOffset: Float, positionOffsetPx: Int) {
                val params = binding.indicator.layoutParams as FrameLayout.LayoutParams

                // Multiply positionOffset with indicatorWidth to get translation
                val translationOffset = (positionOffset + i) * indicatorWidth
                params.leftMargin = translationOffset.toInt()
                binding.indicator.layoutParams = params
            }

            override fun onPageSelected(i: Int) {}

            override fun onPageScrollStateChanged(i: Int) {}
        })
    }
}