package org.acme.Util;
import org.acme.Main;

import java.util.Scanner;
public class MenuUtil {
    Scanner sc = new Scanner(System.in);
    public void ApresentarMenuInicial(){
        System.out.println("\n1- Login");
        System.out.println("2- Cadastrar nova conta");
        System.out.print("-> ");
    }
    public void ApresentarMenuDeOperacoesBancarias(){
        System.out.println("\nOlá " + Main.conta.getTitular());
        System.out.println("Qual operação deseja Realizar ?");
        System.out.println("1- Exibir Saldo Disponível");
        System.out.println("2- Saque");
        System.out.println("3- Depósito");
        System.out.println("4- Transferência");
    }
    public int RealizarOuNaoOutraOperacaoBancaria() {

        System.out.println("\nDeseja realizar outra operação ?");
        System.out.println("1- Sim");
        System.out.println("2- Não");
        System.out.print("->");

        int opcao = sc.nextInt();
        sc.nextLine();

        return opcao;
        }

}
