package br.edu.ifro.agendacontatosandroid;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListarContatosActivity extends AppCompatActivity {

    private ListView lista;
    private String[] itens = new String[]{"Igor", "Eclésio"};
    private FloatingActionButton buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contatos);

        lista =  findViewById(R.id.listar_contatos_listview);
        buttonAdd = findViewById(R.id.listar_contatos_button_new);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,itens);

        lista.setAdapter(adapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarContatosActivity.this, FormularioActivity.class);

                startActivity(intent);
            }
        });
    }
}
