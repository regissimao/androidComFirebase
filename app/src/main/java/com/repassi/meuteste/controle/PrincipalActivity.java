package com.repassi.meuteste.controle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.repassi.meuteste.R;
import com.repassi.meuteste.modelo.Usuario;

public class PrincipalActivity extends AppCompatActivity {

    TextView txtNome;
    TextView txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        txtNome = (TextView) findViewById(R.id.txtPrincipalNome);
        txtEmail = (TextView) findViewById(R.id.txtPrincipalEmail);

        Intent it = getIntent();

        Usuario usuario = (Usuario) it.getSerializableExtra("usuario");

        String nome = usuario.getNome();
        String email = usuario.getEmail();
        txtNome.setText(nome);
        txtEmail.setText(email);
    }
}
