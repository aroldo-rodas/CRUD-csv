package aplication;

import entities.Funcionario;
import entities.exceptions.MenuException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        //Pega o user e define o caminho onde arquivo será criado ou lido
        String userHome = System.getProperty("user.home");
        String path = userHome + "/Documentos/CRUD-csv/src/dado/dados.csv";
        
        //Bloco try criando o objeto de leitura e objeto de escrita, garantindo que irá fechar o arquivo
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
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
                        System.out.println("#####################");
                        System.out.println("#Cadastrar          #");
                        System.out.println("#####################\n");
                        System.out.print("Informe o ID: ");
                        int idFuncionario = sc.nextInt();

                        //Aguarda 2 segundos
                        System.out.println("SALVANDO...");
                        Thread.sleep(2000);
                        break;

                    case 5:
                        //Aguarda 2 segundos
                        System.out.println("SAINDO...");
                        Thread.sleep(2000);
                        sair = true;
                        break;

                    default:
                        break;
                }

                //Limpa terminal os linux
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        }
        catch(IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        catch(MenuException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    //Função que impprime menu
    public static void menu() {
        System.out.println("#####################");
        System.out.println("#Sistema de Cadastro#");
        System.out.println("#####################");
        System.out.println("#1 - Cadastrar      #");
        System.out.println("#5 - Sair           #");
        System.out.print("Informe a opção: ");
    }
}
