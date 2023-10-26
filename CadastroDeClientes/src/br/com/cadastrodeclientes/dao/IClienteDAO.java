package br.com.cadastrodeclientes.dao;

import br.com.cadastrodeclientes.domain.Cliente;

import java.util.Collection;

public interface IClienteDAO {

    public Boolean CREATE(Cliente cliente); // Adicionar um novo cliente ao sistema.
    public Cliente READ(String cpf); // Ver informações sobre clientes existentes.
    public Boolean UPDATE(Cliente cliente); // Modificar os detalhes de um cliente, como endereço ou número de telefone.
    public void DELETE(String cpf); // Remover um cliente da base de dados, se necessário.
    public Collection<Cliente> SEARCH_ALL(); // Exibe o map

}
