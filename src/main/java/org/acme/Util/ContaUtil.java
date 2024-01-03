package org.acme.Util;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import org.acme.Contas.Conta;
import org.acme.Exceptions.SaldoInsuficienteException;
import org.acme.Exceptions.SenhaIncorretaException;
import org.acme.Main;

import java.io.*;
import java.util.Scanner;

import static org.acme.Util.ConversorUtil.ConverterParaReais;
import static org.acme.Util.ConversorUtil.ConverterParaDouble;

public class ContaUtil {

    Scanner sc = new Scanner(System.in);

    public void RealizarLogin() throws SenhaIncorretaException {
        System.out.print("Cpf: ");
        String cpfInput = sc.nextLine();
        System.out.print("Senha: ");
        String senhaInput = sc.nextLine();

        try {
            FileReader reader = new FileReader(cpfInput + ".txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String linha;
            String dados[];

            linha = bufferedReader.readLine();
            dados = linha.split(";");
            if(!senhaInput.equals(dados[2])){
                throw new SenhaIncorretaException("Senha Incorreta");
            }
            Main.conta = new Conta(dados[0],dados[1],dados[2], ConverterParaDouble(dados[3]));


        }catch (IOException e){
            System.out.println("Conta inexistente.");
        }
    }
    public void RealizarCadastro(){

        CPFValidator validator = new CPFValidator();
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        String cpf;
        while(true) {
            System.out.print("Cpf: ");
            cpf = sc.nextLine();
            try {
                validator.assertValid(cpf );
                break;
            } catch (InvalidStateException ex) {
                System.out.println("Insira um Cpf Válido.");
            }
        }

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        System.out.print("Saldo Inicial: ");
        double saldoInicial = sc.nextFloat();

        try {
            FileWriter fileWriter = new FileWriter(cpf + ".txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(nome + ";" + cpf + ";" + senha + ";" + String.format("%.2f",saldoInicial));
            fileWriter.close();
            printWriter.close();
        } catch(IOException e){
            System.out.println("Erro ao criar o arquivo.");
        }

    }
    public void InserirNovoSaldoNoArquivo(Conta conta){

        try {
            FileWriter fileWriter = new FileWriter(conta.getCpf() + ".txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(conta.getTitular() + ";" + conta.getCpf() + ";" + conta.getSenha() + ";" + String.format("%.2f",conta.getSaldo()));
            fileWriter.close();
            printWriter.close();
        } catch(IOException e){
            System.out.println("Erro ao alterar o arquivo.");
        }
    }
    public void RealizarOperacoesBancarias(int opcao){
        switch (opcao){
            case 1 :
                System.out.println("Seu saldo atual é de " + ConverterParaReais(Main.conta.getSaldo()));
                break;

            case 2 :
                while(true) {
                    System.out.print("Valor do saque: ");
                    double valorSaque = sc.nextFloat();
                    sc.nextLine();
                    try {
                        Main.conta.Sacar(valorSaque);
                        break;
                    } catch (SaldoInsuficienteException e) {
                        System.out.println(e.getMessage() + ", " + "Seu saldo atual é de " + ConverterParaReais(Main.conta.getSaldo()) + ". Tente novamente com uma quantia menor.");
                    }
                }break;

            case 3 :
                System.out.print("Valor do depósito: ");
                double valorDeposito = sc.nextFloat();
                Main.conta.Depositar(valorDeposito);
                break;

            case 4 :
                System.out.print("Cpf da conta destino: ");
                String cpfContaDestino = sc.nextLine();

                System.out.print("Valor da transferência: ");
                String valorTransferencia = sc.nextLine();

                try {
                    Main.conta.Transferir(valorTransferencia,cpfContaDestino);
                    break;
                } catch(SaldoInsuficienteException e) {
                    System.out.println(e.getMessage() + ", " + "Seu saldo atual é de " + ConverterParaReais(Main.conta.getSaldo()) + ". Tente novamente com uma quantia menor.");
                }

        }
    }

}
