package com.repassi.meuteste.controle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.repassi.meuteste.R;
import com.repassi.meuteste.modelo.Usuario;

public class PrincipalActivity extends AppCompatActivity {

    TextView txtNome;
    TextView txtEmail;

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        txtNome = (TextView) findViewById(R.id.txtPrincipalNome);
        txtEmail = (TextView) findViewById(R.id.txtPrincipalEmail);

        Intent it = getIntent();
        usuario = (Usuario) it.getSerializableExtra("usuario");

        String nome = usuario.getNome();
        String email = usuario.getEmail();
        txtNome.setText(nome);
        txtEmail.setText(email);
    }

    public void cadastrarCurso(View v) {
        Intent intent = new Intent(this, EditarCursoActivity.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }

    public void listarCurso(View v) {

        EditText edtNomeCurso = (EditText) findViewById(R.id.edtNomeCurso);
        String nomeCurso = edtNomeCurso.getText().toString();

        Intent intent = new Intent(this, ListarCursoActivity.class);
        intent.putExtra("nomecurso", nomeCurso);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }
}
