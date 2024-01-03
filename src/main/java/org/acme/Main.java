package org.acme;

import java.util.Scanner;

import org.acme.Contas.Conta;
import org.acme.Exceptions.SenhaIncorretaException;
import org.acme.Util.ContaUtil;
import org.acme.Util.MenuUtil;


public class Main {
    public static Conta conta;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MenuUtil menuUtil = new MenuUtil();
        ContaUtil contaUtil = new ContaUtil();

        menuUtil.ApresentarMenuInicial();
        int escolhaLoginOuCadastro = sc.nextInt();
        sc.nextLine();

        switch (escolhaLoginOuCadastro){
            case 1:
                try {
                    contaUtil.RealizarLogin();
                    while(true) {
                        menuUtil.ApresentarMenuDeOperacoesBancarias();
                        int escolhaOperacaoBancaria = sc.nextInt();
                        sc.nextLine();
                        contaUtil.RealizarOperacoesBancarias(escolhaOperacaoBancaria);
                        int escolhaRepetirOuNao = menuUtil.RealizarOuNaoOutraOperacaoBancaria();
                        if(escolhaRepetirOuNao == 2){
                            break;
                        }
                    }
                    contaUtil.InserirNovoSaldoNoArquivo(conta);
                    break;

                }catch(SenhaIncorretaException e){
                    System.out.println(e.getMessage());
                    break;
                }
            case 2:
                contaUtil.RealizarCadastro();
                System.out.println("\nCadastro Concluido");
                break;
        }

    }
}