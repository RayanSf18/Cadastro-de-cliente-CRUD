package br.com.cadastrodeclientes.domain;

import java.util.Objects;

public class Cliente {

    // Atributos
    private String cpf;
    private String nome;
    private String numero;
    private String endereco;

    // Construtor
    public Cliente(String cpf, String nome, String numero, String endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.numero = numero;
        this.endereco = endereco;
    }

    // Metodos especiais
    @Override
    public String toString() {
        return "CPF: " + this.getCpf() +
                "\nNome: " + this.getNome() +
                "\nNúmero: " + this.getNumero() +
                "\nEndereço: " + this.getEndereco() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    // Metodos getters e setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
