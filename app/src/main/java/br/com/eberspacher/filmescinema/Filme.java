package br.com.eberspacher.filmescinema;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Filme {
    private String fNome;
    private String fLocal;
    private GregorianCalendar fData;
    private String fComentario;

    public Filme(String fNome, String fLocal, GregorianCalendar fData) {
        this.fNome = fNome;
        this.fLocal = fLocal;
        this.fData = fData;
        this.fComentario = "";
    }

    public Filme(String fNome, String fLocal, int dia, int mes, int ano) {
        this.fNome = fNome;
        this.fLocal = fLocal;
        this.fData = new GregorianCalendar(ano, mes-1, dia);
        this.fComentario = "";
    }

    public String getfNome() {
        return fNome;
    }

    public void setfNome(String fNome) {
        this.fNome = fNome;
    }

    public String getfLocal() {
        return fLocal;
    }

    public void setfLocal(String fLocal) {
        this.fLocal = fLocal;
    }

    public GregorianCalendar getfData() {
        return fData;
    }

    public String getfDataString () {
        SimpleDateFormat stf = new SimpleDateFormat("dd.MM.yyyy");
        String sData = stf.format(fData.getTime());
        return sData;
    }

    public void setfData(GregorianCalendar fData) {
        this.fData = fData;
    }

    public void setfData(int dia, int mes, int ano) {
        setfData(new GregorianCalendar(ano, mes-1, dia));
    }

    public String getfComentario() {
        return fComentario;
    }

    public void setfComentario(String fComentario) {
        this.fComentario = fComentario;
    }

    public String toString() {
        return fNome;
    }
}
