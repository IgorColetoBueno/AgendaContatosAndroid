package br.edu.ifro.agendacontatosandroid.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifro.agendacontatosandroid.model.Contato;

public class ContatoDAO extends SQLiteOpenHelper {

    private final String TABLE_NAME = "contatos";

    public ContatoDAO(Context context) {
        super(context, "Agenda de Contatos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_NAME + " (id integer primary key, nome text not null, email text, endereco text, telefone text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inserir(Contato contato){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("nome", contato.getNome());
        dados.put("email", contato.getEmail());
        dados.put("endereco", contato.getEndereco());
        dados.put("telefone", contato.getTelefone());

        db.insert(TABLE_NAME, null, dados);
    }
    public List<Contato> Listar(){

        String sql = "select * from " + TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Contato> lista = new ArrayList<Contato>();

        while(c.moveToNext()){
            Contato contato = new Contato();

            contato.setId(c.getInt(c.getColumnIndex("id")));
            contato.setNome(c.getString(c.getColumnIndex("nome")));
            contato.setEmail(c.getString(c.getColumnIndex("email")));
            contato.setEndereco(c.getString(c.getColumnIndex("endereco")));
            contato.setTelefone(c.getString(c.getColumnIndex("telefone")));

            lista.add(contato);
        }
        return lista;
    }

    public void remover(int id) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(id)});
    }

    public void alterar(Contato contato) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("id", contato.getId());
        dados.put("nome", contato.getNome());
        dados.put("email", contato.getEmail());
        dados.put("endereco", contato.getEndereco());
        dados.put("telefone", contato.getTelefone());

        db.update(TABLE_NAME, dados, "id = ?", new String[]{String.valueOf(contato.getId())});
    }
}
