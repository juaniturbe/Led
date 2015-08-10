package cl.usach.diinf.led;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        setContentView(R.layout.activity_main);


        WebView engine = (WebView) this.findViewById(R.id.webView);


        engine.getSettings().setJavaScriptEnabled(true);
        engine.getSettings().setBuiltInZoomControls(true);
        engine.getSettings().setLoadsImagesAutomatically(true);
        engine.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        engine.getSettings().setDomStorageEnabled(true);
        engine.setInitialScale(100);
        engine.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        engine.loadUrl("http://www.informatica.usach.cl");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);





        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
