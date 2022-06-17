package mt;

public class Conexao {
    private String lerEstado;
    private String escreverEstado;
    private String moverEstado;
    private Estado destino;

    public String getLerEstado() {
        return lerEstado;
    }

    public void setLerEstado(String lerEstado) {
        this.lerEstado = lerEstado;
    }

    public String getEscreverEstado() {
        return escreverEstado;
    }

    public void setEscreverEstado(String escreverEstado) {
        this.escreverEstado = escreverEstado;
    }

    public String getMoverEstado() {
        return moverEstado;
    }

    public void setMoverEstado(String moverEstado) {
        this.moverEstado = moverEstado;
    }

    public Estado getDestino() {
        return destino;
    }

    public void setDestino(Estado destino) {
        this.destino = destino;
    }
}
