package com.example.Masterji;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.net.URLEncoder;

public class View_Pdf extends AppCompatActivity {

    WebView webView;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__pdf);

        webView = (WebView)findViewById(R.id.viewpdf);
        webView.getSettings().setJavaScriptEnabled(true);

        String pdfTitle = getIntent().getStringExtra("pdfTitle");
        String pdfUrl = getIntent().getStringExtra("pdfUrl");

        pd = new ProgressDialog(this);

        pd.setTitle(pdfTitle);
        pd.setMessage("wait...");

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                pd.show();

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pd.dismiss();
            }
        });

        String url="";
//        try{
//            url = URLEncoder.encode(pdfUrl,"UTF-8");
//
//        }catch (Exception e){
//
//        }
        url = "http://docs.google.com/gview?embedded=true&url=" + pdfUrl;
        webView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + url);



    }
}