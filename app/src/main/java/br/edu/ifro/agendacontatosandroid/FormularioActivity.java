package br.edu.ifro.agendacontatosandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.ifro.agendacontatosandroid.DAO.ContatoDAO;
import br.edu.ifro.agendacontatosandroid.model.Contato;

public class FormularioActivity extends AppCompatActivity {

    private TextInputEditText formNome, formEmail, formEndereco, formTelefone;
    private Button formSubmitButton;
    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        //Declara cada view
        bindViews();

        Intent intent = getIntent();

        if (intent.hasExtra("contato")) {
            contato = (Contato) intent.getSerializableExtra("contato");
            inicializarForm(contato);
        } else {
            contato = new Contato();
        }


        formSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    contato.setNome(formNome.getText().toString().trim());
                    contato.setEmail(formEmail.getText().toString().trim());
                    contato.setEndereco(formEndereco.getText().toString().trim());
                    contato.setTelefone(formTelefone.getText().toString().trim());

                    //inserir no banco de dados
                    ContatoDAO contatoDAO = new ContatoDAO(FormularioActivity.this);

                    if (contato.getId() == 0) {
                        contatoDAO.inserir(contato);
                    } else {
                        contatoDAO.alterar(contato);
                    }

                    Toast.makeText(FormularioActivity.this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } catch (Exception e) {
                    throw e;
                }
            }
        });
    }

    private void bindViews() {
        //Mapeamento de componentes
        formNome = findViewById(R.id.formulario_field_nome);
        formEmail = findViewById(R.id.formulario_field_email);
        formEndereco = findViewById(R.id.formulario_field_endereco);
        formTelefone = findViewById(R.id.formulario_field_telefone);
        formSubmitButton = findViewById(R.id.formulario_button_submit);
    }

    private void inicializarForm(Contato contato) {
        formNome.setText(contato.getNome());
        formEndereco.setText(contato.getEndereco());
        formEmail.setText(contato.getEmail());
        formTelefone.setText(contato.getTelefone());
    }
}
