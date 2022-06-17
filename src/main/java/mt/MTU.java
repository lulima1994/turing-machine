package mt;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

public class MTU {

    public static void main(String[] args) throws IOException, URISyntaxException {
        URL caminhoArquivo = MTU.class.getResource("/input.txt");
        RandomAccessFile leitor = new RandomAccessFile(new File(caminhoArquivo.toURI()), "r");
        String linha = leitor.readLine();
        String[] estados = linha.split(";");
        Maquina maquina = new Maquina();

        for (String estado : estados) {
            maquina.adicionarEstado(estado);
        }
        linha = leitor.readLine();
        estados = linha.split(";");
        for (String estado : estados) {
            maquina.definirEstadoFinal(estado);
        }

        while (leitor.getFilePointer() < leitor.length()) {
            linha = leitor.readLine();
            String[] dadosConexao = linha.split(";");
            Conexao conexao = new Conexao();
            conexao.setLerEstado(dadosConexao[1]);
            conexao.setEscreverEstado(dadosConexao[2]);
            conexao.setMoverEstado(dadosConexao[3]);
            Estado origem = maquina.buscarEstadoNome(dadosConexao[0]);
            origem.adicionarConexao(conexao);
            Estado destino = maquina.buscarEstadoNome(dadosConexao[4]);
            conexao.setDestino(destino);
        }
        leitor.close();
//        executarTeste(maquina, "1");
    }

    public static void executarTeste(Maquina maquina, String fita) {
        System.out.println("processando fita: " + fita);
//        maquina.processar(new String[]{"0","0","1","0","1","1","1","1"});
        if (maquina.processar(fita.split(";")))
            System.out.println("valido");
        else
            System.out.println("nao valido");
    }
}
