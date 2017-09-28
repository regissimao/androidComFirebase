package com.repassi.meuteste.modelo;

import java.util.List;

/**
 * Created by Regis on 24/09/17.
 */

public class Curso {

    private String nome;
    private List<String> alunosMatriculados;

    public Curso() {

    }

    public Curso(String nome, List<String> alunosMatriculados) {
        this.nome = nome;
        this.alunosMatriculados = alunosMatriculados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public void setAlunosMatriculados(List<String> alunosMatriculados) {
        this.alunosMatriculados = alunosMatriculados;
    }
}
