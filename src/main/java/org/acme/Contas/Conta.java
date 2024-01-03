package org.acme.Contas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.acme.Exceptions.SaldoInsuficienteException;
import org.acme.Util.ConversorUtil;

import java.io.*;

@AllArgsConstructor@NoArgsConstructor
@Data
public class Conta {
    
    private String titular;
    private String cpf;
    private String senha;
    private double saldo;

    public void Depositar(double valorDeposito){
        saldo += valorDeposito;
        System.out.println("Depósito realizado com sucesso");
    }

    public void Sacar(double valorSaque) throws SaldoInsuficienteException {
        if (valorSaque > saldo){
            throw new SaldoInsuficienteException("Saldo Insuficiente");
        }else{
            saldo -= valorSaque;
            System.out.println("Saque Realizado com sucesso");
        }

    }

    public void Transferir(String valorTransferencia,String cpfContaDestino) throws SaldoInsuficienteException {

        double valorTransferenciaDouble = ConversorUtil.ConverterParaDouble(valorTransferencia);

        if (valorTransferenciaDouble > saldo) {
            throw new SaldoInsuficienteException("Saldo Insuficiente");
        } else {
            try {
                FileReader reader = new FileReader(cpfContaDestino + ".txt");
                BufferedReader bufferedReader = new BufferedReader(reader);
                String linha;
                String dados[];

                linha = bufferedReader.readLine();
                dados = linha.split(";");

                try {
                    double saldoContaDestino = ConversorUtil.ConverterParaDouble(dados[3]);
                    double novoSaldoContaDestino = valorTransferenciaDouble + saldoContaDestino;
                    FileWriter fileWriter = new FileWriter(cpfContaDestino + ".txt");
                    PrintWriter printWriter = new PrintWriter(fileWriter);
                    printWriter.println(dados[0] + ";" + dados[1] + ";" + dados[2] + ";" + String.format("%.2f",novoSaldoContaDestino));
                    fileWriter.close();
                    printWriter.close();
                    saldo -= valorTransferenciaDouble;
                    System.out.println("Transferência realizada com sucesso");
                } catch(IOException e){
                    System.out.println("Erro ao alterar o arquivo.");
                }

            }catch (IOException e){
                System.out.println("Conta inexistente.");
            }
        }
    }
}
