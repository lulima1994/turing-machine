package mt;

import java.util.Scanner;

public class MTU {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Maquina maquina = new Maquina();

        while (true) {
            var linha = scanner.nextLine();
            var comandos = linha.split(" ");

            if (comandos[0].equals("adicionar")) {
                if (comandos[1].equals("estado")) {
                    maquina.adicionarEstado(comandos[2]);
                } else if (comandos[1].equals("conexao")) {
                    Conexao conexao = new Conexao();
                    conexao.setLerElemento(comandos[3]);
                    conexao.setEscreverElemento(comandos[4]);
                    conexao.setMoverElemento(comandos[5]);
                    Estado origem = maquina.buscarEstadoNome(comandos[2]);
                    origem.adicionarConexao(conexao);
                    Estado destino = maquina.buscarEstadoNome(comandos[6]);
                    conexao.setDestino(destino);
                }
            } else if (comandos[0].equals("definir")) {
                if (comandos[1].equals("estado")) {
                    if (comandos[2].equals("final")) {
                        maquina.definirEstadoFinal(comandos[3]);
                    }
                }
            } else if (comandos[0].equals("sair")) {
                System.out.println("adicionar fita");
                linha = scanner.nextLine();
                executarTeste(maquina, linha);
                break;
            }
        }
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
