package com.repassi.meuteste.controle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.repassi.meuteste.R;
import com.repassi.meuteste.modelo.Usuario;
import com.repassi.meuteste.modelo.Util;

public class EditarUsuarioActivity extends AppCompatActivity {

    EditarUsuarioActivity _this;

    EditText edtNome;
    EditText edtEmail;
    EditText edtSenha;

    Usuario usuario;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        _this = this;
        mAuth = FirebaseAuth.getInstance();
    }

    public void salvar(View v) {

        String nome = edtNome.getText().toString();
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();

        usuario = new Usuario(nome, email, senha);

        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG.sucesso", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG.falha", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(_this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void updateUI(FirebaseUser user) {

        FirebaseDatabase myRefdatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = myRefdatabase.getReference("usuarios");

        Util.idUser = user.getUid();

        myRef.child(user.getUid()).setValue(usuario);

        Intent intent = new Intent(this, PrincipalActivity.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }
}
