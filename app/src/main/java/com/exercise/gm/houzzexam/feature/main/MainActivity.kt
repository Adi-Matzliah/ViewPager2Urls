package com.exercise.gm.houzzexam.feature.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.exercise.gm.houzzexam.R
import com.exercise.gm.houzzexam.feature.viewpager.ViewPagerPagesFragmentAdapter
import com.exercise.gm.houzzexam.network.response.LinkItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var adapter: ViewPagerPagesFragmentAdapter

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.fetchUrlPages()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.pages.observe(this) {
            setupViewPager(it)
        }
    }


    private fun setupViewPager(pages: List<LinkItem>) {
        pb_loading.visibility = View.GONE
        adapter = ViewPagerPagesFragmentAdapter(supportFragmentManager, lifecycle, pages)
        viewPager = vp_links
        with(viewPager) {
            adapter = this@MainActivity.adapter

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    //TODO: handle page selection if necessary
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewPager.adapter = null

    }
}