package com.exercise.gm.houzzexam.feature.webview

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.exercise.gm.houzzexam.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_web_view.*

@AndroidEntryPoint
class WebViewFragment : Fragment(R.layout.fragment_web_view) {

    private lateinit var currentUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currentUrl = it.getString(PAGE_URL)!!
        }
    }

    private fun setupViewPager() {
        with(wv_page) {
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    view.loadUrl(url)
                    return true
                }
            }
            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    pb_url.visibility =
                        if (newProgress != PROGRESS_COMPLETE_URL_LOAD) View.VISIBLE else View.GONE
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        loadPageUrl()
    }

    private fun loadPageUrl() {
        wv_page.loadUrl(currentUrl)
    }

    companion object {

        private const val PAGE_URL: String = "page_url_key"
        private const val PROGRESS_COMPLETE_URL_LOAD = 100

        fun newInstance(url: String) =
            WebViewFragment().apply {
                arguments = Bundle().apply {
                    putString(PAGE_URL, url)
                }
            }
    }
}