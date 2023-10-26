package br.com.cadastrodeclientes.dao;

import br.com.cadastrodeclientes.dao.IClienteDAO;
import br.com.cadastrodeclientes.domain.Cliente;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GerenciamentoDeClientes implements IClienteDAO {

    private Map<String, Cliente> mapDeClientes;

    public GerenciamentoDeClientes() {
        mapDeClientes = new HashMap<>();
    }

    @Override
    public Boolean CREATE(Cliente cliente) {
        Boolean clienteCadastrado = mapDeClientes.containsKey(cliente.getCpf());

        if (clienteCadastrado) {
            return false;
        } else {
            this.mapDeClientes.put(cliente.getCpf(), cliente);
            return true;
        }
    }

    @Override
    public Cliente READ(String cpf) {
        Cliente clienteCadastrado = this.mapDeClientes.get(cpf);

        if (clienteCadastrado != null) {
            return clienteCadastrado;
        }

        return null;
    }

    @Override
    public Boolean UPDATE(Cliente cliente) {
         Cliente clienteCadastrado = this.mapDeClientes.get(cliente.getCpf());

         if (clienteCadastrado != null) {
             clienteCadastrado.setNome(cliente.getNome());
             clienteCadastrado.setNumero(cliente.getNumero());
             clienteCadastrado.setEndereco(cliente.getEndereco());
             return false;
         } else {
             return true;
         }

    }

    @Override
    public void DELETE(String cpf) {
        this.mapDeClientes.remove(cpf);
    }

    @Override
    public Collection<Cliente> SEARCH_ALL() {
        return this.mapDeClientes.values();
    }
}
