package com.repassi.meuteste.controle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.repassi.meuteste.R;
import com.repassi.meuteste.modelo.Curso;
import com.repassi.meuteste.modelo.Usuario;
import com.repassi.meuteste.modelo.Util;

import java.util.ArrayList;

public class EditarCursoActivity extends AppCompatActivity {

    EditarCursoActivity _this;

    EditText edtNomeCurso;

    Usuario usuario;
    Curso curso;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_curso);

        edtNomeCurso = (EditText) findViewById(R.id.edtNomeCurso);

        Intent it = getIntent();
        usuario = (Usuario) it.getSerializableExtra("usuario");

        _this = this;
        mAuth = FirebaseAuth.getInstance();
    }

    public void salvar(View v){

        String nome = edtNomeCurso.getText().toString();
        ArrayList<String> alunosMatriculados = new ArrayList<String>();
        alunosMatriculados.add(Util.idUser);
        Curso curso = new Curso(nome, alunosMatriculados);

        FirebaseDatabase myRefdatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = myRefdatabase.getReference("cursos");

        //myRef.child(myRef.push().getKey()).setValue(curso);
        myRef.push().setValue(curso);

        Intent intent = new Intent(this, PrincipalActivity.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }
}
