package yueyong.checkout;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class ResultActivity extends Activity {

    private TextView mResultText;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle extras = getIntent().getExtras();

        mResultText = (TextView) findViewById(R.id.result_text);
        mWebView = (WebView) findViewById(R.id.web_view);

        initWebView();

        if (null != extras) {
            String result = extras.getString("result");
            mResultText.setText(result);
            mWebView.loadUrl(result);
        }
    }

    private void initWebView() {
        mWebView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });
    }
}
