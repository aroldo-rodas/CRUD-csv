package aplication;

import entities.Funcionario;
import entities.exceptions.MenuException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        List<Funcionario> lista = new ArrayList<>();
        List<String> listaAtualizar = new ArrayList<>();
        List<String> listaNova = new ArrayList<>();

        //Pega o user e define o caminho onde arquivo será criado ou lido
        String userHome = System.getProperty("user.home");
        String path = userHome + "/Documentos/CRUD-csv/src/dado/dados.csv";
        
        //Bloco try criando o objeto de leitura e objeto de escrita, garantindo que irá fechar o arquivo
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, false));
            BufferedReader br = new BufferedReader(new FileReader(path))){
            
            Boolean sair = false;

            //Controle do menu
            while(sair != true) {
                menu();

                int opcao = sc.nextInt();

                //Tratamendo para entrada inválida
                if(opcao > 5 || opcao < 1){ 
                    throw new MenuException("Digite um valor válido!");
                }

                //CRUD
                switch(opcao) {
                    //Cadastra Funcionário
                    case 1:
                        sc.nextLine();
                        //Limpa terminal os linux
                        new ProcessBuilder("clear").inheritIO().start().waitFor();

                        //Salva o funcionário no csv
                        String lineFuncionario = cadastrarFuncionário(lista, listaAtualizar);
                        if(lineFuncionario != "Erro") {
                            bw.write(lineFuncionario);
                            bw.newLine();
                            bw.flush();
                        }
                        else {
                            System.out.println("ID existente, tente novamente!");
                        }

                        //Aguarda 2 segundos
                        System.out.println("SALVANDO...");
                        Thread.sleep(2000);
                        break;

                        //Lista funcionário
                    case 2:
                        System.out.println();

                        String line = br.readLine();

                        //Faz a varredura no csv e adiciona na lista os funcionários
                        while(line != null) {

                            String[] funcionario = line.split(";");
                            lista.add(new Funcionario(Integer.parseInt(funcionario[0]), funcionario[1], 
                                                funcionario[2], Double.parseDouble(funcionario[3])));

                            line = br.readLine();
                        }

                        //Imprime os funcionários se existir
                        for(Funcionario func : lista) {
                            System.out.println(func.getIdFuncionario() +
                                            ", " + func.getNomeFuncionario() +
                                            ", " + func.getCargoFuncionario() + 
                                            ", R$" + func.getSalarioFuncionario());
                        }

                        Thread.sleep(2000);
                        break;
                        
                    case 3:
                        new ProcessBuilder("clear").inheritIO().start().waitFor();

                        System.out.print("Informe o id do funcionário: ");
                        int idEditar = sc.nextInt();

                        for(String linha : listaAtualizar) {
                            String[] funcEditar = linha.split(";");

                            if(Integer.parseInt(funcEditar[0]) == idEditar) {
                                sc.nextLine();
                                System.out.print("Novo cargo: ");
                                String novoCargo = sc.nextLine();
                                System.out.print("Novo salário: ");
                                Double novoSalario = sc.nextDouble();

                                Funcionario funcionario = new Funcionario(Integer.parseInt(funcEditar[0]), funcEditar[1], novoCargo, novoSalario);
                                linha = funcionario.toString();
                            }
                            listaNova.add(linha);
                        }
                        
                        atualizarDados(listaNova, path);
                        listaNova.clear();
                        Thread.sleep(2000);
                       
                        break;
                    case 5:
                        System.out.println("SAINDO...");
                        Thread.sleep(2000);
                        sair = true;
                        break;
                        

                    default:
                        break;
                }

                //Limpa terminal OS linux
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }

            sc.close();
        }
        catch(IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        catch(MenuException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    //Função que imprime menu
    public static void menu() {
        System.out.println("#####################");
        System.out.println("#Sistema de Cadastro#");
        System.out.println("#####################");
        System.out.println("#1 - Cadastrar      #");
        System.out.println("#2 - Listar         #");
        System.out.println("#3 - Editar         #");
        System.out.println("#5 - Sair           #");
        System.out.print("Informe a opção: ");
    }

    //Criar funcinário
    public static String cadastrarFuncionário(List<Funcionario> lista, List<String> listaAtualizar) {
        Scanner sc = new Scanner(System.in);

        System.out.println("#####################");
        System.out.println("#Cadastrar          #");
        System.out.println("#####################\n");
        System.out.print("Informe o ID: ");
        int idFuncionario = sc.nextInt();
        sc.nextLine();
        System.out.print("Informe o nome: ");
        String nomeFuncionario = sc.nextLine();
        System.out.print("Informe o cargo: ");
        String cargoFuncionario = sc.nextLine();
        System.out.print("Informe o salário: ");
        Double salarioFuncionario = sc.nextDouble();

        Funcionario func = lista.stream().filter(x -> x.getIdFuncionario() == idFuncionario)
                                                .findFirst().orElse(null);

        //Se não existir id igual ao id digitado, cria funcionário
        if(func == null) {
            Funcionario funcionario = new Funcionario(idFuncionario, nomeFuncionario, cargoFuncionario, salarioFuncionario);
            
            listaAtualizar.add(funcionario.toString());
            return funcionario.toString();
        }
        else {
            return "Erro";
        }
    }

    //Atualizar dados
    public static void atualizarDados(List<String> lista, String path) {
        try(BufferedWriter bw = new  BufferedWriter(new FileWriter(path))){
            for(String linha : lista) {
                bw.write(linha);
                bw.newLine();
                bw.flush();
            }
        }
        catch(IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        catch(RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
