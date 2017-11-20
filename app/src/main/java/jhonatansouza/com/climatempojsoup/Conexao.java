package jhonatansouza.com.climatempojsoup;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;



/**
 * Created by jhonatansouza on 11/19/17.
 */

public class Conexao {

    private final String url;
    private Temperatura retorno;

    public Conexao(String url){
        this.url = url;
    }


    public Temperatura getRetorno() throws Exception {
        return this.getTemperatura();
    }

    public void setRetorno(Temperatura retorno) {
        this.retorno = retorno;
    }

    private  Temperatura getTemperatura() throws Exception {

       final Temperatura sender = new Temperatura();
        Document d = Jsoup.connect(url).userAgent("Mozilla/Gecko").get();
        sender.setCidadeNome(d.getElementById("momento-localidade").text());
        sender.setTemperatura(d.getElementsByClass("temp-topo").text().replace("°", ""));
        Log.d("temperatura", d.getElementsByClass("temp-topo").text());

        return sender;
    }

}
