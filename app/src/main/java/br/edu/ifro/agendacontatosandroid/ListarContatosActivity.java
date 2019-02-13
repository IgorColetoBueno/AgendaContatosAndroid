package br.edu.ifro.agendacontatosandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.edu.ifro.agendacontatosandroid.DAO.ContatoDAO;
import br.edu.ifro.agendacontatosandroid.model.Contato;

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

        this.listar();


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarContatosActivity.this, FormularioActivity.class);

                startActivity(intent);
            }
        });

        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contato contato = (Contato) lista.getItemAtPosition(position);

                Intent intent = new Intent(ListarContatosActivity.this, FormularioActivity.class);
                intent.putExtra("contato", contato);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        this.listar();
    }

    private void listar() {
        ContatoDAO contatoDAO = new ContatoDAO(this);
        List<Contato> contatos = contatoDAO.Listar();


        ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatos);

        lista.setAdapter(adapter);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDeletar = menu.add("Deletar");
        MenuItem menuLigar = menu.add("Ligar");

        menuDeletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;

                Contato contato = (Contato) lista.getItemAtPosition(adapterContextMenuInfo.position);
                ContatoDAO dao = new ContatoDAO(ListarContatosActivity.this);

                dao.remover(contato.getId());
                listar();

                return false;
            }
        });


        menuLigar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;

                Contato contato = (Contato) lista.getItemAtPosition(adapterContextMenuInfo.position);

                if (contato.getTelefone() != null) {
                    ligar(contato.getTelefone());
                }
                return false;
            }
        });

    }

    private void ligar(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
}
