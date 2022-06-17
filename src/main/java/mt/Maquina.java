package mt;

import java.util.ArrayList;
import java.util.List;

public class Maquina {
    private final List<Estado> estados = new ArrayList<>();

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
                fita[posicaoAtual] = conexaoEncontrada.getEscreverEstado();
                if (conexaoEncontrada.getMoverEstado().equals("D"))
                    posicaoAtual++;
                else if (conexaoEncontrada.getMoverEstado().equals("E"))
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
