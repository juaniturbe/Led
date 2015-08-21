package cl.usach.diinf.led;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jiturbe on 18-08-15.
 */
class JsonFeedTask extends AsyncTask<String, Void, String> {

    private Exception exception;
    private Context context= null;
    List<noticiaDIINF> yourData = null;

    static String TAG = "JsonFeedTask";

    public AsyncResponse delegate=null;

    //JSON Node Names
    private static final String TAG_titulo = "titulo";
    private static final String TAG_descripcion = "contenido";
    private static final String TAG_fuente = "fuente";
    private static final String TAG_fecha = "fecha";
    private static final String TAG_time = "time";

    public JsonFeedTask(Context context, List<noticiaDIINF> yourData){
        this.context = context;
        this.yourData = yourData;

    }
    @Override
    protected String doInBackground(String... url) {
        try {
            //JSON
            //URL to get JSON Array


            String url_json = "http://sitios.diinf.usach.cl/informaciones/wp-content/themes/led/noticias.json";

            //Create a JSON parser Instance ----- Used JSON parser from Android
            JSONParser jParser=new JSONParser();

            //Getting JSON string from URL ------ Used JSON Array from Android
            JSONArray json=jParser.getJSONFromUrl(url_json);


            Log.d(TAG, json.toString());



            yourData = null;
            yourData = new ArrayList<noticiaDIINF>();

            try {
                int largojson = json.length();
                Log.d(TAG,"JSON size: " + largojson);

                for(int i=0;i<largojson;i++)
                {
                    JSONObject c=json.getJSONObject(i);// Used JSON Object from Android

                    //Storing each Json in a string variable



                    String TITULO=c.getString(TAG_titulo);
                    String DESCRIPCION=c.getString(TAG_descripcion);
                    String FUENTE=c.getString(TAG_fuente);
                    String FECHA=c.getString(TAG_fecha);
                    String TIME=c.getString(TAG_time);

                    Log.d(TAG,"i: " + i);
                    Log.d(TAG,"TITULO: " + TITULO);
                    Log.d(TAG,"DESCRIPCION: " + DESCRIPCION);
                    Log.d(TAG,"FUENTE: " + FUENTE);
                    Log.d(TAG,"FECHA: " + FECHA);

                    yourData.add(new noticiaDIINF(TITULO, DESCRIPCION, FUENTE, FECHA,TIME));


                }

                //Collections.sort(yourData);

                int auxsize= yourData.size();
                Log.d(TAG,"size: " + auxsize);
                for(int j=0;j<auxsize; j++){

                    Log.d(TAG,j +" TIME: " + yourData.get(j).getTime() +" - " + yourData.get(j).getFecha() + " - " + yourData.get(j).getTitulo() );

                }


                //customAdapter.notifyDataSetChanged();

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }



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

        delegate.processFinish(yourData);

        //this method will be running on UI thread


    }



}
