package cl.usach.diinf.led;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ClipData.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.random;


public class MainActivity extends ActionBarActivity {


    static String TAG = "MainActivity";



    //JSON Node Names
    private static final String TAG_titulo = "titulo";
    private static final String TAG_descripcion = "contenido";
    private static final String TAG_fuente = "fuente";
    private static final String TAG_fecha = "fecha";



    final String[] urls = {
            "http://www.informatica.usach.cl",
            "http://www.informatica.usach.cl/proyecto/observe-vigilancia-a-bordo-para-una-respuesta-efectiva-en-ambientes-vehiculares/",
            "http://www.informatica.usach.cl/proyecto/escalabilidad-y-uso-eficiente-de-energia-empleando-set-top-boxes/",
            "http://www.informatica.usach.cl/proyecto/herramienta-de-prediccion-de-indicadores-de-disponibilidad-para-gestion-de-activos-mineros/",
            "http://www.informatica.usach.cl/proyecto/despliegue-agil-de-aplicaciones-de-apoyo-a-la-gestion-de-desastres-de-origen-natural-y-caso-de-estudio-en-sismologia/",
            "http://www.informatica.usach.cl/proyecto/factorizando-matrices-de-sistemas-con-informacion-dependiente-de-pacientes-y-geometria-de-deteccion-invariante/",
            "http://www.informatica.usach.cl/investigacion/areas-aplicativas/",
            "http://www.informatica.usach.cl/postgrado/magister/magister-en-ingenieria-informatica/",
            "http://www.informatica.usach.cl/postgrado/magister/magister-en-seguridad-peritaje-y-auditoria-en-procesos-informaticos/",
            "http://www.informatica.usach.cl/postgrado/doctorado/doctorado-en-ciencias-de-la-ingenieria-con-mencion-en-informatica/",
            "http://www.informatica.usach.cl/educacion-continua/diplomados/diplomado-en-gestion-informatica/",
            "http://www.informatica.usach.cl/educacion-continua/diplomados/diplomado-en-control-seguridad-y-auditoria-computacional/",
            "http://www.informatica.usach.cl/educacion-continua/diplomados/diplomado-en-peritaje-informatico/",
            "http://www.informatica.usach.cl/pregrado/ingenieria-civil-en-informatica/vespertino/",
            "http://www.informatica.usach.cl/pregrado/ingenieria-civil-en-informatica/diurno/",
            "http://www.informatica.usach.cl/pregrado/ingenieria-de-ejecucion-en-computacion-e-informatica/diurno/",
            "http://www.informatica.usach.cl/pregrado/ingenieria-de-ejecucion-en-computacion-e-informatica/vespertino/",
            "http://www.informatica.usach.cl/academico/rannou-fuentes-fernando/",
            "http://sitios.diinf.usach.cl/respond/",
            "http://citiaps.cl/",
            "http://sitios.diinf.usach.cl/hcir/",
            "http://www.usach.cl/",
            "http://ceii.cl/",
            "https://www.google.com/calendar/embed?src=usach.cl_fqj5pt1vl1sb4cr83r146uqlpk%40group.calendar.google.com&ctz=America/Santiago&mode=WEEK&showNav=0&showPrint=0&showTabs=0",
            "https://www.google.com/calendar/embed?src=usach.cl_dfi5lmfie6njhfsa72gm6l6m04%40group.calendar.google.com&ctz=America/Santiago&mode=WEEK&showNav=0&showPrint=0&showTabs=0",
            "https://www.google.com/calendar/embed?src=usach.cl_8rgvmc602ncoh0r3bdpmah92ms%40group.calendar.google.com&ctz=America/Santiago&mode=AGENDA&hl=es_419",

    };


    String[] images = {
            "http://www.informatica.usach.cl/multimedia/Organigrama-simple.jpg"
    };

    ListView yourListView = null;
    ListAdapter customAdapter = null;
    List<noticiaDIINF> yourData = null;

    boolean first = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);



        setContentView(R.layout.activity_main);


        final WebView engine = (WebView) this.findViewById(R.id.webView);
        final TextView tv_url = (TextView) this.findViewById(R.id.textView3);


        engine.getSettings().setJavaScriptEnabled(true);
        engine.getSettings().setBuiltInZoomControls(true);
        engine.getSettings().setLoadsImagesAutomatically(true);

        engine.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        engine.getSettings().setDomStorageEnabled(true);
        engine.setInitialScale(70);
        engine.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


        yourListView = (ListView) findViewById(R.id.listView);






        new JsonFeedTask(getApplicationContext()).execute();



        final int TIEMPO = 15000;

        final Handler handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {

                //TEST
                refreshNoticias();


                //FIN-TEST


                int M = 0;
                int N = urls.length-1;
                int L = images.length-1;
                int prob = 10; // Entre mayor sea este valor, menos probable que aparesca una imagen

                int j = (int) Math.floor(Math.random()*(N-M+1)+M);  // Valor entre M y N, ambos incluidos.

                int k = (int) Math.floor(Math.random()*(L-M+1)+M);  // Valor entre M y N, ambos incluidos.

                int o = (int) Math.floor(Math.random()*(prob-M+1)+M);


                int LARGO = 90;
                if(o == 0){
                    Display display = getWindowManager().getDefaultDisplay();
                    int width=display.getWidth();

                    String data="<html><head><title>Example</title><meta name=\"viewport\"\"content=\"width="+width+", initial-scale=0.65 \" /></head>";
                    data=data+"<body><center><img width=\""+width+"\" src=\""+images[k]+"\" /></center></body></html>";
                    engine.loadData(data, "text/html", null);


                    Log.d(TAG, "Imagen: " + images[k]);
                    Log.d(TAG, "Imagen lenght: " +  images[k].length());

                    if(images[k].length() < LARGO){

                        tv_url.setText("Link: " + images[k]);

                    }else {

                        tv_url.setText("Link: " + images[k].substring(0, LARGO-1) + "...");

                    }


                }else{

                    engine.loadUrl(urls[j]);
                    Toast.makeText(getApplicationContext(),
                            urls[j], Toast.LENGTH_SHORT);


                    if(urls[j].length() < LARGO){

                        tv_url.setText("Link: " + urls[j]);

                    }else {

                        tv_url.setText("Link: " + urls[j].substring(0, LARGO) + "...");

                    }



                }


                engine.setWebViewClient(new WebViewClient(){

                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url){
                        view.loadUrl(url);
                        return true;
                    }
                });


                Log.d(TAG, "Handler ejecutado: " + j);
                handler.postDelayed(this, TIEMPO);

            }
        };

        handler.postDelayed(r, TIEMPO);







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


    public void refreshNoticias() {

            Log.d(TAG, "Refresh News");
        if(yourData!=null) {

            Log.d(TAG, "Data != null");

            if (first == true) {
                Log.d(TAG, "Primera vez, seteando el adapter");
                customAdapter = new ListAdapter(getBaseContext(), R.layout.listnoticia, yourData);

                yourListView.setAdapter(customAdapter);

                first = false;

            }






            new JsonFeedTask(getApplicationContext()).execute();


        }else{
            Log.d(TAG, "Data == null");
        }


    }


    class JsonFeedTask extends AsyncTask<String, Void, String> {

        private Exception exception;
        private Context context= null;


        public JsonFeedTask(Context context){
            this.context = context;

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

                yourData = new ArrayList<noticiaDIINF>();

                try {
                    for(int i=0;i<json.length();i++)
                    {
                        JSONObject c=json.getJSONObject(i);// Used JSON Object from Android

                        //Storing each Json in a string variable



                        String TITULO=c.getString(TAG_titulo);
                        String DESCRIPCION=c.getString(TAG_descripcion);
                        String FUENTE=c.getString(TAG_fuente);
                        String FECHA=c.getString(TAG_fecha);
/*
                        Log.d(TAG,"TITULO: " + TITULO);
                        Log.d(TAG,"DESCRIPCION: " + DESCRIPCION);
                        Log.d(TAG,"FUENTE: " + FUENTE);
                        Log.d(TAG,"FECHA: " + FECHA);
*/
                        yourData.add(new noticiaDIINF(TITULO, DESCRIPCION, FUENTE, FECHA));


                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }




                //Log.d(TAG,json.toString());


                //Fin JSON



            } catch (Exception e) {
                this.exception = e;

            }
            return null;
        }

        //@TODO: Esto no se ejecuta!!!
    
        protected void onPostExecute() {
            // TODO: check this.exception
            // TODO: do something with the feed

            customAdapter.notifyDataSetChanged();


            yourListView.post(new Runnable() {
                public void run() {

                    int i = 0;

                    int largo = yourListView.getCount();
                    int posicion_actual = i;


                        while(i<largo){

                            try {
                                Log.d(TAG, "Entre en el while");


                                //Thread.sleep(10000);


                                posicion_actual = i % largo;
                                Log.d(TAG, "i              : " + i);
                                Log.d(TAG, "largo          : " + largo);

                                Log.d(TAG, "Posicion actual: " + posicion_actual);

                                yourListView.smoothScrollToPosition(posicion_actual);


                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            i++;

                        }



                    }



            });



        }
    }



    public class ListAdapter extends ArrayAdapter<noticiaDIINF> {

        public ListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
            // TODO Auto-generated constructor stub
        }

        private List<noticiaDIINF> items;

        public ListAdapter(Context context, int resource, List<noticiaDIINF> items) {

            super(context, resource, items);

            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            TextView tt = null;
            TextView tt1 = null;
            TextView tt2 = null;
            TextView tt3 = null;
            TextView tt4 = null;

            if (v == null) {

                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.listnoticia, null);

                tt = (TextView) v.findViewById(R.id.titulo);
                tt1 = (TextView) v.findViewById(R.id.descripcion);
                tt2 = (TextView) v.findViewById(R.id.fuente);
                tt3 = (TextView) v.findViewById(R.id.fecha);

            }

            noticiaDIINF p = items.get(position);

            if (p != null) {

                if (tt != null) {
                    tt.setText(""+ Html.fromHtml(p.getTitulo()));
                }
                if (tt1 != null) {

                    tt1.setText(""+Html.fromHtml(p.getDescripcion()));
                }
                if (tt2 != null) {

                    tt2.setText(""+Html.fromHtml(p.getFuente()));
                }

                if (tt3 != null) {

                    tt3.setText(""+Html.fromHtml(p.getFecha()));
                }


            }



            return v;

        }
    }
}

