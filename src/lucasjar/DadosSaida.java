package lucasjar;

import java.io.Serializable;

public class DadosSaida implements Serializable {

    private Integer posicao;
    private Double valor;

    public DadosSaida(Integer posicao, Double valor) {
        this.posicao = posicao;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return posicao + ";" + valor;
    }
}
