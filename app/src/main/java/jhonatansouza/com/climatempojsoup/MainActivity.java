package jhonatansouza.com.climatempojsoup;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView txt_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btn = (Button) findViewById(R.id.btn_load);
        this.txt_result = (TextView) findViewById(R.id.result);
        Typeface f = Typeface.createFromAsset(getAssets(), "minisystem.ttf");
        this.txt_result.setTypeface(f);
        this.setListeners();

    }

    private void setListeners(){
        this.btn.setOnClickListener(new View.OnClickListener(){

           @Override
            public void onClick(View view){

               try{
                   TarefaLoader f = new TarefaLoader();
                   f.execute("https://www.climatempo.com.br/previsao-do-tempo/cidade/558/saopaulo-sp");
               }catch (Exception ex){
                   ex.printStackTrace();
               }



           }
        });
    }

    class TarefaLoader extends AsyncTask<String, Integer, Temperatura> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Temperatura doInBackground(String... strings) {
             Conexao c = new Conexao(strings[0]);
            try{
                return c.getRetorno();
            }catch (Exception ex){
                return new Temperatura();
            }
        }

        @Override
        protected void onPostExecute(Temperatura temperatura) {
            super.onPostExecute(temperatura);

            txt_result.setText(temperatura.getTemperatura());
            Toast.makeText(getBaseContext(), "Cidade : "+temperatura.getCidadeNome(), Toast.LENGTH_LONG).show();
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();

        }
    }
}

