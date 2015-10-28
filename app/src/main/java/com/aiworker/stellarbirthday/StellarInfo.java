package com.aiworker.stellarbirthday;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import com.aiworker.stellarbirthday.R;

public class StellarInfo extends Activity{
    private WebView webView;
    String BirthdayStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//		setContentView(R.layout.stellar_info);
        setContentView(R.layout.stellar_info_webview);

        // -- get BirthdayStar name based on Birthday Date from Facebook
        // add logic here
//			BirthdayStar = "sirius";
        // -- end


        // -- WebView implementation
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDisplayZoomControls(true);

        // -- bind WebAppInterface class to the JavaScript that runs in WebView with addJavascriptInterface()
        WebAppInterface.setValue(MainActivity.BirthdayStarName);
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
        // -- load JavaScript located in "asset" folder
        webView.loadUrl("file:///android_asset/stars_javascript.html");

        // -- end of WebView implementation



    }

}


