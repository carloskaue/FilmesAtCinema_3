package br.com.eberspacher.filmescinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;

// icone da aplicação alterado em mipmap com arquivos correspondentes a cada resolução
// sugestão de utilitário para icones dos apps: http://romannurik.github.io/AndroidAssetStudio/
// cria os diversos formatos a partir de imagem e outras funções

public class MainActivity extends AppCompatActivity {
    // ArrayList, estrutura de dados contendo os filmes vistos
    ArrayList<Filme> alFilmes;
    // ArrayAdapter é um adapter para vincular arrays e views, pode ser usado com list views e spinners
    ArrayAdapter<Filme> aaListaFilmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // pegar os filmes
        alFilmes = ListaFilmes.get(getApplicationContext()).getAlFilmes();

        // associar o elemento da UI com a variável
        ListView lvListaFilmes = (ListView) findViewById(R.id.lvListaFilmes);
        // vamos usar o ArrayAdaptar para mostrar os dados do ArrayList de filmes alFilmes
        aaListaFilmes = new ArrayAdapter<Filme>(this, android.R.layout.simple_list_item_1, alFilmes);
        // e mostrá-los na na listview lvListaFilmes, setada com o método setAdapter
        lvListaFilmes.setAdapter(aaListaFilmes);
        // usará o retorno do toString para determinar o texto que será exibido em cada item da lista

        // criar um listener para os eventos de onClick nos items da listview
        lvListaFilmes.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                        Intent i = new Intent(view.getContext(), DadosFilme.class);
                        i.putExtra("filmeId", String.valueOf(posicao));
                        startActivity(i);
                    }
                });
    }

    // mais info sobre ActionBar e Toolbars em
    // https://developer.android.com/training/appbar/index.html
    public boolean onCreateOptionsMenu(Menu menu) {
        // popula a action bar com os icones configurados em menu_main.xml
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onResume() {
        super.onResume();
        aaListaFilmes.notifyDataSetChanged();
    }

    // icones selecionados em xhdpi (compatível com o AVD em uso nos testes), ver demais resoluções
    // sugestão para bilbioteca de ícones: https://materialdesignicons.com/
    // e também http://www.veryicon.com/
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.miAdd:
                String nome = "teste " + alFilmes.size();
                alFilmes.add(new Filme(nome, "Cinepolis", new GregorianCalendar()));
                // avisa o adaptador que os dados mudaram, logo a view que mostra os dados precisa ser atualizada
                aaListaFilmes.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Adicionei "+nome, Toast.LENGTH_SHORT).show();
                return true;

            case R.id.miAlpha:
                // ordenar filmes em ordem alfabética crescente
                Collections.sort(alFilmes, new Comparator<Filme>() {
                    @Override
                    public int compare(Filme lhs, Filme rhs) {
                        return lhs.getfNome().compareToIgnoreCase(rhs.getfNome());
                    }
                });
                aaListaFilmes.notifyDataSetChanged();
                return true;

            case R.id.miCrono:
                // ordenar colocando os filmes mais recentes em data primeiro
                Collections.sort(alFilmes, new Comparator<Filme>() {
                    @Override
                    public int compare(Filme lhs, Filme rhs) {
                        return rhs.getfData().compareTo(lhs.getfData());
                    }
                });
                aaListaFilmes.notifyDataSetChanged();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
