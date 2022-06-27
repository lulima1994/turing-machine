package mt;

import java.util.Scanner;

public class MTU {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Maquina maquina = new Maquina();
        Conexao conexao = new Conexao();

        System.out.println("adicionar estado");
        var linha = scanner.nextLine();
        var estados = linha.split(" ");
        for (String estado : estados) {
            maquina.adicionarEstado(estado);
        }

        System.out.println("adicionar estado final");
        linha = scanner.nextLine();
        estados = linha.split(" ");
        for (String estado : estados) {
            maquina.definirEstadoFinal(estado);
        }

        System.out.println("adicionar conexao");
        while (!scanner.nextLine().equals("")) {
            linha = scanner.nextLine();
            var dadosConexao = linha.split(" ");
            conexao.setLerEstado(dadosConexao[1]);
            conexao.setEscreverEstado(dadosConexao[2]);
            conexao.setMoverEstado(dadosConexao[3]);
            Estado origem = maquina.buscarEstadoNome(dadosConexao[0]);
            origem.adicionarConexao(conexao);
            Estado destino = maquina.buscarEstadoNome(dadosConexao[4]);
            conexao.setDestino(destino);
        }

        System.out.println("adicionar fita");
        linha = scanner.nextLine();
        executarTeste(maquina, linha);

        scanner.close();
    }

    public static void executarTeste(Maquina maquina, String fita) {
        System.out.println("processando fita: " + fita);
        if (maquina.processar(fita.split(" ")))
            System.out.println("valido");
        else
            System.out.println("invalido");
    }
}
