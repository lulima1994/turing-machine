package mt;

public class Conexao {
    private String lerElemento;
    private String escreverElemento;
    private String moverElemento;
    private Estado destino;

    public String getLerElemento() {
        return lerElemento;
    }

    public void setLerElemento(String lerElemento) {
        this.lerElemento = lerElemento;
    }

    public String getEscreverElemento() {
        return escreverElemento;
    }

    public void setEscreverElemento(String escreverElemento) {
        this.escreverElemento = escreverElemento;
    }

    public String getMoverElemento() {
        return moverElemento;
    }

    public void setMoverElemento(String moverElemento) {
        this.moverElemento = moverElemento;
    }

    public Estado getDestino() {
        return destino;
    }

    public void setDestino(Estado destino) {
        this.destino = destino;
    }
}
