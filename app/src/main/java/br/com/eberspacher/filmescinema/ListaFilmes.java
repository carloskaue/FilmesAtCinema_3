package br.com.eberspacher.filmescinema;

import android.content.Context;

import java.util.ArrayList;

// criando a lista de filmes usando o pattern singleton
// ou seja, uma classe que permite a existência de apenas uma instância de sí mesma

// mantendo a lista de filmes em um singleton, ela estará disponível na memoria
// durante o ciclo de vida das atividades e do app
public class ListaFilmes {
    private ArrayList<Filme> alFilmes;        // a lista de filmes privada da classe
    private static ListaFilmes sListaFilmes;  // a instancia static
    private Context appContext;

    // o constutor é privado
    private ListaFilmes(Context appContext) {
        this.appContext = appContext;
        // criar o ArrayList de objetos da classe Filme
        alFilmes = new ArrayList<Filme>();
        // e adicionar 3 filmes para popular a base
        alFilmes.add(new Filme("Homem-Formiga", "UCI", 03, 12, 2015));
        alFilmes.add(new Filme("Capitão America - Guerra Civil", "Cinemark", 18, 03, 2016));
        alFilmes.get(1).setfComentario("Foi todo o grupo do trabalho e o pessoal ficou dividido entre o Capitao e o Homem de Ferro, bem bacana!");
        alFilmes.add(new Filme("X-Men Apocalipse", "Cinepolis", 20, 04, 2016));
    }

    // getter irá verificar se a instância já existe ou não
    // este método é static
    public static ListaFilmes get (Context c){
        if (sListaFilmes == null) {
            sListaFilmes = new ListaFilmes(c.getApplicationContext());
        }
        return sListaFilmes;
    }

    // retorna o conjunto de filme
    public ArrayList<Filme> getAlFilmes() {
        return alFilmes;
    }
}
