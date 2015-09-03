package cl.usach.diinf.led;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiturbe on 18-08-15.
 */
class RSSFeedTask extends AsyncTask<String, Void, String> {

    private Exception exception;
    private Context context= null;
    final String finalUrl = "http://sitios.diinf.usach.cl/informaciones/feed/?post_type=url";
    //private HandleXML obj;


    static String TAG = "RSSFeedTask";

    List<String> links = null;

    public AsyncResponse delegate=null;


    public RSSFeedTask(Context context){
        this.context = context;


    }
    @Override
    protected String doInBackground(String... url) {



        links = null;
        links = new ArrayList<String>();

        try {

            RssParser parser = new RssParser(finalUrl);
            int i = 0;
            while(parser.getItem(i)!=null) {


                Log.d(TAG, "Title: " + parser.getItem(i).title);
                links.add(parser.getItem(i).title);

                i++;

            }
            Log.d(TAG,"Title fin");
        } catch (Exception e) {
            this.exception = e;

        }


        return null;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //this method will be running on UI thread


    }
    @Override
    protected void onPostExecute(String result) {


        //this method will be running on UI thread

        delegate.rssFinish(links);


    }



}
