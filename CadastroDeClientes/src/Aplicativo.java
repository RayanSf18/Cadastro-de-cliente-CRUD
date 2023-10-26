import br.com.cadastrodeclientes.dao.GerenciamentoDeClientes;
import br.com.cadastrodeclientes.dao.IClienteDAO;
import br.com.cadastrodeclientes.domain.Cliente;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Aplicativo {
    public static void main(String[] args) {
        System.out.println("***** Sistema Inicializado *****");
        IClienteDAO iClienteDAO = new GerenciamentoDeClientes();

        String[] opcoes = {"1", "2", "3", "4", "5", "6"};

        while (true) {
            int escolha = JOptionPane.showOptionDialog(null, "Escolha uma opção que deseja executar:\n1 - Cadastrar\n2 - Verificar\n3 - Buscar Todos\n4 - Atualizar\n5 - Deletar\n6 - Sair", "Sistema de Cadastro de Cliente", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

            if (escolha >= 0) {
                String opcaoSelecionada = opcoes[escolha];
            }

            switch (escolha) {
                case 0: // cadastrar
                    String cpf;
                    String nome;
                    String telefone;
                    String endereco;
                    while (true) {
                        cpf = JOptionPane.showInputDialog(null, "CPF: ", "Cadastrar Dados", JOptionPane.INFORMATION_MESSAGE).trim();

                        if (cpf.isEmpty() && !(cpf.contains(".")) && cpf.length() != 14) {
                            JOptionPane.showMessageDialog(null, "Informe o campo de CPF corretamente!", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                            continue;
                        } else {
                            break;
                        }
                    }
                    while (true) {
                        nome = JOptionPane.showInputDialog(null, "Nome Completo: ", "Cadastrar Dados", JOptionPane.INFORMATION_MESSAGE).trim();
                        if (nome.contains(" ")) {
                            break;
                        }
                        JOptionPane.showMessageDialog(null, "Informe o nome completo separando por espacos!");
                    }
                    while (true) {
                        telefone = JOptionPane.showInputDialog(null, "Telefone: ", "Cadastrar Dados", JOptionPane.INFORMATION_MESSAGE);
                        endereco = JOptionPane.showInputDialog(null, "Endereço Completo: ", "Cadastrar Dados", JOptionPane.INFORMATION_MESSAGE);

                        if (!telefone.isEmpty() && !endereco.isEmpty()) {
                            break;
                        }
                        JOptionPane.showMessageDialog(null, "Preencha os campos de numero e endereco!");
                    }

                    Boolean cadastrado = iClienteDAO.CREATE(new Cliente(cpf, nome, telefone, endereco));

                    if (!cadastrado) {
                        JOptionPane.showMessageDialog(null, "Cliente já cadastrado!", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Cliente Cadastrado", JOptionPane.INFORMATION_MESSAGE);
                    }

                    continue;
                case 1: // verificar
                    String cpfParaEncontrar = JOptionPane.showInputDialog(null, "Digite o numero do CPF do cliente: ", "Buscar Cliente", JOptionPane.INFORMATION_MESSAGE);
                    Cliente clienteEncontrado = iClienteDAO.READ(cpfParaEncontrar);

                    if (clienteEncontrado != null) {
                        JOptionPane.showMessageDialog(null, "Cliente: \n" + clienteEncontrado.toString(), "Dados", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente não existe!", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                    }

                    continue;
                case 2:
                    List<Cliente> listaDeClientes = new ArrayList<>(iClienteDAO.SEARCH_ALL());
                    StringBuilder clientes = new StringBuilder();
                    for (Cliente cliente: listaDeClientes) {
                        clientes.append("\n" + cliente);
                    }

                    JOptionPane.showMessageDialog(null, "Clientes:\n" + clientes, "Lista de Clientes", JOptionPane.INFORMATION_MESSAGE);
                    continue;
                case 3:
                    String cpfAtual;
                    while (true) {
                        cpfAtual = JOptionPane.showInputDialog(null, "Digite o CPF do cliente para atualizar: ", "Atualizar Dados", JOptionPane.INFORMATION_MESSAGE);
                        String nomeAtualizado = JOptionPane.showInputDialog(null, "Atualizar nome: ", "Atualizar Dados", JOptionPane.INFORMATION_MESSAGE);
                        String telefoneAtualizado = JOptionPane.showInputDialog(null, "Atualizar telefone: ", "Atualizar Dados", JOptionPane.INFORMATION_MESSAGE);
                        String enderecoAtualizado = JOptionPane.showInputDialog(null, "Atualizar endereço: ", "Atualizar Dados", JOptionPane.INFORMATION_MESSAGE);
                        String dados = cpfAtual + "," + nomeAtualizado + "," + telefoneAtualizado + "," + enderecoAtualizado;
                        String[] dadoSeparados = dados.split(",");
                        Cliente clienteAtualizado = new Cliente(dadoSeparados[0], dadoSeparados[1], dadoSeparados[2], dadoSeparados[3]);
                        Boolean atualizou = iClienteDAO.UPDATE(clienteAtualizado);

                        if (!atualizou) {
                            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Não foi possivel atualizar os dados. Tente novamente!", "ERRO", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                    continue;
                case 4:
                    String cpfParaExcluir = JOptionPane.showInputDialog(null, "Digite o CPF do cliente para excluir os dados: ", "Excluir Dados", JOptionPane.INFORMATION_MESSAGE);
                    int opcao = JOptionPane.showOptionDialog(
                            null,
                            "Você realmente deseja excluir ?",
                            "Confirmação",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new String[]{"Sim", "Não"},
                            "Sim"
                    );

                    if (opcao == JOptionPane.YES_OPTION) {
                        iClienteDAO.DELETE(cpfParaExcluir);
                        JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if (opcao == JOptionPane.NO_OPTION) {
                            JOptionPane.showMessageDialog(null, "Exclusão cancelada!", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                    continue;
                case 5:
                    JOptionPane.showMessageDialog(null, "Até logo!", "Sistema encerrado", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
            }
        }


    }
}