package cl.usach.diinf.led;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

/**
 * Created by jiturbe on 18-08-15.
 */
public class ListViewAdapter extends ArrayAdapter<noticiaDIINF> {

    final String TAG = "";
    private List<noticiaDIINF> items;
    Context context;

    public ListViewAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        // TODO Auto-generated constructor stub
        this.context = context;
    }



    public ListViewAdapter(Context context, int resource, List<noticiaDIINF> items) {

        super(context, resource, items);

        this.items = items;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = new ViewHolder();

        if(convertView==null){
            Log.d(TAG, "if convertView == null");

        }

 //       if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.listnoticia, parent, false);


            // initialize the view holder
            viewHolder.titulo = (TextView) convertView.findViewById(R.id.titulo);
            viewHolder.descripcion = (TextView) convertView.findViewById(R.id.descripcion);
            viewHolder.fuente = (TextView) convertView.findViewById(R.id.fuente);
            viewHolder.fecha = (TextView) convertView.findViewById(R.id.fecha);


            noticiaDIINF p = items.get(position);

            String text = p.getTitulo();

//            Log.d(TAG, "text " + text);
//        viewHolder.titulo.setText("hola");
            viewHolder.titulo.setText(""+ Html.fromHtml(p.getTitulo()));
            viewHolder.descripcion.setText(""+Html.fromHtml(p.getDescripcion()));
            viewHolder.fuente.setText(""+Html.fromHtml(p.getFuente()));
            viewHolder.fecha.setText(""+Html.fromHtml(p.getFecha()));

            convertView.setTag(viewHolder);
/*
        }else{

            if(convertView==null){
                Log.d(TAG, "else convertView == null");

            }

            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();

        }
*/


        return convertView;

    }

    private static class ViewHolder {
        TextView titulo;
        TextView descripcion;
        TextView fuente;
        TextView fecha;
    }
}
