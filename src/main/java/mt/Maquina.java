package mt;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Maquina {
    private final List<Estado> estados = new ArrayList<>();

    public void lerArquivo() throws IOException, URISyntaxException {
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
            conexao.setLerElemento(dadosConexao[1]);
            conexao.setEscreverElemento(dadosConexao[2]);
            conexao.setMoverElemento(dadosConexao[3]);
            Estado origem = maquina.buscarEstadoNome(dadosConexao[0]);
            origem.adicionarConexao(conexao);
            Estado destino = maquina.buscarEstadoNome(dadosConexao[4]);
            conexao.setDestino(destino);
        }
        leitor.close();
        MTU.executarTeste(maquina, "1");
    }

    public void adicionarEstado(String nome) {
        Estado estado = new Estado(nome);
        estados.add(estado);
    }

    public void definirEstadoFinal(String nome) {
        Estado fim = buscarEstadoNome(nome);
        fim.setEstadoFinal();
    }

    public Estado buscarEstadoNome(String nome) {
        for (Estado estado : estados) {
            if (estado.getNome().equals(nome))
                return estado;
        }
        throw new RuntimeException("estado nao encontrado");
    }

    public boolean processar(String[] fita) {
        Estado estadoAtual = estados.get(0);
        int posicaoAtual = 0;
        int limiteOperacoes = 1000;

        try {
            do {
                if (limiteOperacoes-- <= 0)
                    throw new RuntimeException("loop detectado");
                String leituraAtual = fita[posicaoAtual];
                Conexao conexaoEncontrada = estadoAtual.buscarConexaoLeitura(leituraAtual);
                fita[posicaoAtual] = conexaoEncontrada.getEscreverElemento();
                if (conexaoEncontrada.getMoverElemento().equals("D"))
                    posicaoAtual++;
                else if (conexaoEncontrada.getMoverElemento().equals("E"))
                    posicaoAtual--;
                else throw new RuntimeException("direcao invalida");
                estadoAtual = conexaoEncontrada.getDestino();
            } while (posicaoAtual >= 0 && posicaoAtual < fita.length);
        } catch (RuntimeException exception) {
            return false;
        }
        return estadoAtual.isEstadoFinal();
    }
}
