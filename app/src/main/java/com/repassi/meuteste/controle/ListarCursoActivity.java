package com.repassi.meuteste.controle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.repassi.meuteste.R;
import com.repassi.meuteste.modelo.Curso;
import com.repassi.meuteste.modelo.Usuario;

import java.util.List;

public class ListarCursoActivity extends AppCompatActivity {

    ListarCursoActivity _this;
    TextView textNomeAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_curso);

        _this = this;

        Intent it = getIntent();
        String nomecurso = (String) it.getStringExtra("nomecurso");

        FirebaseDatabase myRefdatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = myRefdatabase.getReference();

        Query query = myRef.child("cursos").orderByChild("nome").equalTo(nomecurso);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"
                        updateUI(issue);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(_this, "Query failed.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateUI(DataSnapshot issue) {

        Curso curso = issue.getValue(Curso.class);
        List<String> alunosMatriculados = curso.getAlunosMatriculados();

        TextView textNomeCurso = (TextView) findViewById(R.id.txtNomeCurso1);
        textNomeCurso.setText(curso.getNome());

        textNomeAluno = (TextView) findViewById(R.id.txtIdAluno);

        FirebaseDatabase myRefdatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = myRefdatabase.getReference();

        Log.i("idAluno", alunosMatriculados.get(0));

        Query query = myRef.child("usuarios").orderByKey().equalTo(alunosMatriculados.get(0));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"
                        Log.i("achou", "Achou");
                        Usuario usuario = issue.getValue(Usuario.class);
                        textNomeAluno.setText(usuario.getNome());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(_this, "Não achou",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
