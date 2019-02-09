package br.edu.ifro.agendacontatosandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListarContatosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contatos);

        ListView lista = findViewById(R.id.listar_contatos_listview);

        String[] itens = new String[]{"Igor", "Eclésio"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,itens);

        lista.setAdapter(adapter);
    }
}
