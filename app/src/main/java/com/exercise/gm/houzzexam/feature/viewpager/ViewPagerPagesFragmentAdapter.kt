package com.exercise.gm.houzzexam.feature.viewpager

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.exercise.gm.houzzexam.feature.webview.WebViewFragment
import com.exercise.gm.houzzexam.network.response.LinkItem

class ViewPagerPagesFragmentAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val pages: List<LinkItem>
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int) =
        when (position) {
            in pages.indices -> WebViewFragment.newInstance(pages[position].url)
            else -> throw Exception("page list fragment is not implemented!")
        }

    override fun getItemCount() = pages.size
}