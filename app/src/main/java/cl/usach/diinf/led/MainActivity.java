package cl.usach.diinf.led;

import android.content.Context;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;




public class MainActivity extends ActionBarActivity implements AsyncResponse{


    static String TAG = "MainActivity";







    int i_position = 0;

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
    int POSITION=0;
    ListAdapter customAdapter = null;
    List<noticiaDIINF> yourData = null;

    boolean first = true;
    boolean adapterLoader = false;

    Context context=null;

    final JsonFeedTask asyncTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        setContentView(R.layout.activity_main);



        context = getBaseContext();

        if(context == null){
            Log.d(TAG,"CONTEXT == NULL");
        }


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


        yourListView.setSmoothScrollbarEnabled(true);


        final JsonFeedTask asyncTask  = new JsonFeedTask(context, yourData);

        asyncTask .execute();


        asyncTask.delegate = this;



        final int TIEMPO = 30000;
        final int TIEMPO_BARRA = 120000;
        final Handler handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {

                //TEST
                //refreshNoticias();



                if (first == true && yourData!=null) {
                    Log.d(TAG, "Primera vez, seteando el adapter");


                    int auxsize= yourData.size();
                    Log.d(TAG,"size: " + auxsize);
                    for(int j=0;j<auxsize; j++){

                        Log.d(TAG,"FIRST: "+j +" TIME: " + yourData.get(j).getTime() +" - " + yourData.get(j).getFecha() + " - " + yourData.get(j).getTitulo() );

                    }



                    customAdapter = new ListViewAdapter(context, R.layout.listnoticia, yourData);

                    yourListView.setAdapter(customAdapter);

                    first = false;
                    adapterLoader = true;




                }

                if(yourData!=null &&adapterLoader) {
                    int last_position = yourData.size() - 1;

                    Log.d(TAG, "ejecutado First view: " + yourListView.getFirstVisiblePosition());
                    Log.d(TAG, "ejecutado Last  view: " + yourListView.getLastVisiblePosition());

                    if(yourListView.getLastVisiblePosition() == last_position){

                        yourListView.smoothScrollToPositionFromTop(0,0,TIEMPO_BARRA);

                    }

                    if(yourListView.getFirstVisiblePosition() == 0){

                        yourListView.smoothScrollToPositionFromTop(last_position,0,TIEMPO_BARRA);

                    }

                }








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
                    Toast.makeText(context,
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

    @Override
    public void processFinish(List<noticiaDIINF> output) {

        yourData = output;

    }



}



