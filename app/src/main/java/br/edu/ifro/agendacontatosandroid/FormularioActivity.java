package br.edu.ifro.agendacontatosandroid;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.ifro.agendacontatosandroid.model.Contato;

public class FormularioActivity extends AppCompatActivity {

    private TextInputEditText formNome, formEmail, formEndereco, formTelefone;
    private Button formSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        //Mapeamento de componentes
        formNome = findViewById(R.id.formulario_field_nome);
        formEmail = findViewById(R.id.formulario_field_email);
        formEndereco = findViewById(R.id.formulario_field_endereco);
        formTelefone = findViewById(R.id.formulario_field_telefone);
        formSubmitButton = findViewById(R.id.formulario_button_submit);

        formSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contato contato = new Contato();

                contato.setNome(formNome.getText().toString().trim());
                contato.setEmail(formEmail.getText().toString().trim());
                contato.setEndereco(formEndereco.getText().toString().trim());
                contato.setTelefone(formTelefone.getText().toString().trim());

                Toast.makeText(FormularioActivity.this, contato.toString(),Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
