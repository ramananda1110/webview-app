package com.my.webapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.my.webapp.R;
import com.my.webapp.application.AppBaseActivity;
import com.my.webapp.databinding.ActivityWebViewBinding;
import com.my.webapp.utils.ConnectDetect;

public class MainActivity extends AppBaseActivity {

    String getUrl;
    WebClientCreate webClientCreate;

    ActivityWebViewBinding binding;

    String TAG = "LoaderViewURL";

    ConnectDetect connectDetect;
    boolean connection = false;

    public static void start(Context mContext, String baseUrl) {
        Intent intent = new Intent(mContext,
                MainActivity.class);
        intent.putExtra("base_url", baseUrl);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view);

        connectDetect = new ConnectDetect(getApplicationContext());

        getUrl = getIntent().getStringExtra("base_url");
        binding.webView.getSettings().setSupportZoom(true);
        binding.webView.getSettings().setBuiltInZoomControls(true);
        binding.webView.getSettings().setLoadWithOverviewMode(true);
        binding.webView.getSettings().setUseWideViewPort(true);
        binding.webView.setPadding(0, 0, 0, 0);

        connection = connectDetect.isNetworkAvailable();

        if (connection) {
            webClientCreate = new WebClientCreate();
            webClientCreate.startWebview(getUrl);
            Log.i(TAG, "Create " + getUrl);
        } else {
            Toast.makeText(getApplicationContext(), "no internet connection",
                    Toast.LENGTH_LONG).show();
        }

    }


    class WebClientCreate extends WebViewClient {
        @SuppressLint("SetJavaScriptEnabled")
        private void startWebview(String url) {
            binding.webView.setWebViewClient(new WebViewClient() {

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    binding.progressbar.setVisibility(View.VISIBLE);
                    return true;
                }

                public void onLoadResource(WebView view, String url) {
                    binding.progressbar.setVisibility(View.GONE);
                }

                public void onPageFinished(WebView v, String url) {
                    binding.progressbar.setVisibility(View.GONE);
                }
            });

            binding.webView.getSettings().setJavaScriptEnabled(true);
            binding.webView.loadUrl(url);

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                AddUrlDialog dialogFragment = AddUrlDialog.display(getSupportFragmentManager(), "dialog_product");
                break;
            case R.id.action_refresh:
                startActivity(getIntent());

                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && binding.webView.canGoBack()) {
            binding.webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        binding.webView.saveState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        binding.webView.restoreState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }


}
