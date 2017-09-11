package cn.leeii.simple.ui.web;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import cn.leeii.simple.R;
import cn.leeii.simple.base.BaseActivity;
import cn.leeii.simple.di.component.BaseComponent;
import cn.leeii.simple.ui.web.di.DaggerWebComponent;
import cn.leeii.simple.ui.web.di.WebModule;

/**
 * WebActivity Created by leeiidesu on 2017/8/9.
 */
public class WebActivity extends BaseActivity<WebPresenter> implements WebContract.IWebView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void trySetupData(Bundle savedInstanceState) {
        WebViewClient webViewClient = new WebViewClient();
        webView.setWebViewClient(webViewClient);
        webView.clearCache(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setInitialScale(1);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.clearHistory();
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.loadUrl("https://github.com/Leeii/LeeFream");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_web;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void setupComponent(BaseComponent baseComponent) {
        DaggerWebComponent
                .builder()
                .baseComponent(baseComponent)
                .webModule(new WebModule(this))
                .build()
                .inject(this);
    }
}
