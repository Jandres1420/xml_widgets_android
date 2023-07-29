package com.example.trainingandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import com.squareup.picasso.Picasso

class WidgetsD : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widgets_d)

//        var ivEjemplo = findViewById<ImageView>(R.id.ivEjemplo)
//        val imageURL = "http://jotajotavm.com/img/PREMIUM-AndroidDevelopment.gif"
//        Picasso.get().load(imageURL).into(ivEjemplo)

        var webView  = findViewById<WebView>(R.id.webView)
//        si no se usa esto va a cargar instagram pero va a querer cargarlo desde afeura de ela aplicacion por lo que hay que darle permisos de javascript
        var webSettings:WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.setWebViewClient(WebViewClient())
        webView.loadUrl("http://instagram.com")
    }
}