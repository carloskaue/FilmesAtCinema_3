package br.com.eberspacher.filmescinema;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EditaFilme extends AppCompatActivity {

    // ArrayList, estrutura de dados contendo os filmes vistos
    ArrayList<Filme> alFilmes;
    Filme filme;
    int filmePos;
    EditText fNome;
    EditText fLocal;
    EditText fComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_filme);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        ActionBar ab = getSupportActionBar(); // acessa a action bar
        ab.setDisplayHomeAsUpEnabled(true);  // seta retorno <- na action bar

        // pega código do filme
        filmePos = Integer.parseInt(extras.getString("filmeId"));
        // pegar os filmes
        alFilmes = ListaFilmes.get(getApplicationContext()).getAlFilmes();
        // pega o filme em questão
        filme = alFilmes.get(filmePos);
        // atualiza nome do filme na avtion bar
        ab.setTitle(filme.getfNome());

        // atualiza dados do filme nas TextViews
        fNome = (EditText) findViewById(R.id.etNome);
        fNome.setText(filme.getfNome());

        TextView fData = (TextView) findViewById(R.id.tvData);
        fData.setText(filme.getfDataString());

        fLocal = (EditText) findViewById(R.id.etLocal);
        fLocal.setText(filme.getfLocal());

        fComentario = (EditText) findViewById(R.id.etComentario);
        String sComentario = filme.getfComentario();
        fComentario.setText(sComentario);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // popula a action bar com os icones configurados em menu_dados_filme.xml
        getMenuInflater().inflate(R.menu.menu_edita_filme, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case android.R.id.home:
                // fechar activity e voltar
                this.finish();
                return true;

            case R.id.miOkEdit:
                // salvar os dados alterados deste evento e voltar
                filme.setfNome(fNome.getText().toString());
                filme.setfLocal(fLocal.getText().toString());
                filme.setfComentario(fComentario.getText().toString());
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
