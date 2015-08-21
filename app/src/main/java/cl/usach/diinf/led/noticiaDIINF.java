package cl.usach.diinf.led;

/**
 * Created by jiturbe on 12-08-15.
 */
public class noticiaDIINF implements Comparable <noticiaDIINF>{


        private String titulo;
        private String descripcion;
        private String fuente;
        private String fecha;
        private int time;

        public noticiaDIINF(String titulo, String descripcion, String fuente, String fecha, String time){

            this.titulo=titulo;
            this.descripcion=descripcion;
            this.fuente = fuente;
            this.fecha = fecha;
            this.time = Integer.parseInt(time);


        }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    public int getTime() {
        return time;
    }

    public void settime(int time) {
        this.time = time;
    }

    @Override
    public int compareTo(noticiaDIINF another) {

        int compareQuantity = ((noticiaDIINF) another).getTime();

        //ascending order
        return  compareQuantity - this.time;


    }
}
