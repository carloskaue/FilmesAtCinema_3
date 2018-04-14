package br.com.eberspacher.filmescinema;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DadosFilme extends AppCompatActivity {

    // ArrayList, estrutura de dados contendo os filmes vistos
    ArrayList<Filme> alFilmes;
    Filme filme;
    int filmePos;
    ActionBar ab;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_filme);

        extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        ab = getSupportActionBar(); // acessa a action bar
        ab.setDisplayHomeAsUpEnabled(true);  // seta retorno <- na action bar

        // pega código do gilme
        filmePos = Integer.parseInt(extras.getString("filmeId"));

        this.mostraDadosFilme();

    }

    private void mostraDadosFilme() {
        // pegar os filmes
        alFilmes = ListaFilmes.get(getApplicationContext()).getAlFilmes();
        // pega o filme em questão
        filme = alFilmes.get(filmePos);
        // atualiza nome do filme na avtion bar
        ab.setTitle(filme.getfNome());

        // atualiza dados do filme nas TextViews
        TextView fNome = (TextView) findViewById(R.id.tvNome);
        fNome.setText(filme.getfNome());

        TextView fData = (TextView) findViewById(R.id.tvData);
        fData.setText(filme.getfDataString());

        TextView fLocal = (TextView) findViewById(R.id.tvLocal);
        fLocal.setText(filme.getfLocal());

        ImageView ivComentario = (ImageView) findViewById(R.id.ivComentario);
        TextView fComentario = (TextView) findViewById(R.id.tvComentario);
        String sComentario = filme.getfComentario();
        if (sComentario.isEmpty())
            ivComentario.setVisibility(View.INVISIBLE);
        else fComentario.setText(sComentario);

    }

    public void onResume() {
        super.onResume();
        this.mostraDadosFilme();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // popula a action bar com os icones configurados em menu_dados_filme.xml
        getMenuInflater().inflate(R.menu.menu_dados_filme, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                // fechar activity e voltar
                this.finish();
                return true;

            case R.id.miShare:
                // compartilhar os dados deste evento
                // montar a string usando strigbuilder
                StringBuilder strShare = new StringBuilder(200);
                strShare.append("Assisti o filme ");
                strShare.append(filme.getfNome());
                strShare.append(" em ");
                strShare.append(filme.getfDataString());
                strShare.append(" no ");
                strShare.append(filme.getfLocal());

                Intent i = new Intent (Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, strShare.toString());
                startActivity(Intent.createChooser(i, "Compartilhar filme visto"));
                return true;

            case R.id.miEditar:
                // chamar a activity para editar os dados deste filme
                Intent i2 = new Intent (this, EditaFilme.class);
                i2.putExtra("filmeId", String.valueOf(filmePos));
                i2.putExtra("from", "1");
                startActivity(i2);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
