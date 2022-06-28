package mt;

import java.util.ArrayList;
import java.util.List;

public class Estado {
    private final String nome;
    private final List<Conexao> conexoes = new ArrayList<>();

    private boolean estadoFinal = false;

    public Estado(String nome) {
        this.nome = nome;
    }

    public void adicionarConexao(Conexao conexao) {
        conexoes.add(conexao);
    }

    public Conexao buscarConexaoLeitura(String lerEstado) {
        for (Conexao conexao : conexoes) {
            if (conexao.getLerElemento().equals(lerEstado))
                return conexao;
        }
        throw new RuntimeException("conexao nao encontrada");
    }

    public String getNome() {
        return nome;
    }

    public boolean isEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal() {
        this.estadoFinal = true;
    }
}
