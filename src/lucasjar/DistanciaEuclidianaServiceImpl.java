package lucasjar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DistanciaEuclidianaServiceImpl implements DistanciaEuclidianaService {

    private int qtdCasas;
    private List<Double> numeros;
    private List<Integer> posicoes = new ArrayList<>();
    private List<Double> valores = new ArrayList<>();

    public DistanciaEuclidianaServiceImpl() throws IOException {
        qtdCasas = 4;
        numeros = ControladorDeDocumento.populaMatriz();
    }

    public List<DadosSaida> processoCalcular(int valorInicio, int valorFinal) {
        for (int i = valorInicio; i < valorFinal; i++) {
            double menor = Double.MAX_VALUE;

            int posicao = 0;
            for (int j = 0; j < (valorFinal); j++) {
                if (i == j) {
                    continue;
                }

                double distancia = calculaDistanciaEntreDoisPontos(i, j);

                if (distancia < menor) {
                    menor = distancia;
                    posicao = j;
                }
            }
            posicoes.add(posicao+1);
            valores.add(menor);
        }

        List<DadosSaida> results = new ArrayList<>();

        for (int i = 0; i < posicoes.size(); i++) {
            results.add(new DadosSaida(posicoes.get(i), valores.get(i)));
        }

        return results;
    }

    private double calculaDistanciaEntreDoisPontos(int i, int j) {
        double distancia = 0;
        for (int k = 0; k < qtdCasas; k++) {
            double aux =+ numeros.get(i+k) - numeros.get(j+k);
            distancia += aux * aux;
        }
        return Math.sqrt(distancia);
    }
}

