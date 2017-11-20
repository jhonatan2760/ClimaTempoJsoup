package jhonatansouza.com.climatempojsoup;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;



/**
 * Created by jhonatansouza on 11/19/17.
 */

public class Conexao {

    //URL da cidade que vamos utilizar
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
       //Criamos um documento que recebe de uma conexao http utilizando um método get
        Document d = Jsoup.connect(url).userAgent("Mozilla/Gecko").get();
        //Capturamos a cidade utilizando o id da mesma
        sender.setCidadeNome(d.getElementById("momento-localidade").text());
        //Capturamos a temperatura utilizando a classe temp-topo que só possui um item
        sender.setTemperatura(d.getElementsByClass("temp-topo").text());
        Log.d("temperatura", d.getElementsByClass("temp-topo").text());

        return sender;
    }

}
