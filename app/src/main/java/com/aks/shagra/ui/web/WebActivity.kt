package com.aks.shagra.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import com.aks.shagra.R
import com.yariksoffice.lingver.Lingver
import kotlinx.android.synthetic.main.activity_web.*
import javax.inject.Inject

class WebActivity : Activity() ,ExitActivityListener {
    lateinit var exitListener: ExitActivityListener
    var url:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        Lingver.getInstance().setLocale(this, "ar")
        exitListener = this

        url = intent?.getStringExtra(WebActivity::class.java.simpleName).toString()
        backBtn.setOnClickListener {
            finish()
        }

        //   webView.loadUrl(url);
        webViewSetup(url)
    }
    class ChromeClient @Inject constructor(private var exitListner: ExitActivityListener) :
        WebChromeClient() {
        override fun onConsoleMessage(message: String?, lineNumber: Int, sourceID: String?) {
            super.onConsoleMessage(message, lineNumber, sourceID)
        }
    }

    override fun onWebViewTriggered() {
        finish()
    }
//    override fun onRefresh() {
//        if (this::mWebView.isInitialized)
//            mWebView.reload()
//    }
//

    private lateinit var mWebView: WebView
    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewSetup(urlPath: String) {
        mWebView = findViewById(R.id.webView)
        mWebView.isClickable = true
        mWebView.settings.javaScriptEnabled = true
        mWebView.settings.domStorageEnabled = true
        mWebView.settings.javaScriptEnabled = true
        mWebView.settings.loadWithOverviewMode = true
        mWebView.settings.useWideViewPort = true
        mWebView.settings.setSupportMultipleWindows(true)
        mWebView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        mWebView.settings.javaScriptCanOpenWindowsAutomatically = true
        mWebView.isHorizontalScrollBarEnabled = false
        mWebView.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        mWebView.webChromeClient = WebActivity.ChromeClient(exitListener)
        // Now Added Java Interface Class
        // mWebView.addJavascriptInterface(WebAppInterface(this.applicationContext), "Android")
        // Load Our Custom JS Inside WebView
        mWebView.loadUrl(urlPath)
        // setContentView(mWebView)
    }

    override fun onDestroy() {
        mWebView.destroy()
        super.onDestroy()
    }
    override fun onBackPressed() {
        if (this::mWebView.isInitialized && mWebView.canGoBack())
            mWebView.goBack()
        super.onBackPressed()
    }
}

interface ExitActivityListener {
    fun onWebViewTriggered()
}
