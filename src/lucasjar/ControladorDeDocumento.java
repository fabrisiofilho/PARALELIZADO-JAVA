package lucasjar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControladorDeDocumento {

    private static final String ASSETS_PATH = "src/resources/";
    private static final String ENTRADA = "entrada.txt";
    private static final String SAIDA = "saida.csv";

    public static List<Double> populaMatriz() {
        List<Double> numeros = new ArrayList<>();
        File file = new File(ASSETS_PATH + ENTRADA);
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            do {
                String readline = br.readLine();
                line = Objects.isNull(readline) ? "" : readline.trim();

                if (line.isEmpty() || line.equals("null")) {
                    break;
                }
                numeros.add(Double.parseDouble(line.replaceAll("[^0-9]", "")));

            } while (Objects.nonNull(line));

            return numeros;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void gravaResultados(List<DadosSaida> resultados) throws IOException {
        try (FileWriter fileWriter = new FileWriter(ASSETS_PATH + SAIDA)) {
            PrintWriter arquivoGravacao = new PrintWriter(fileWriter);

            resultados.forEach(resultado -> arquivoGravacao.println(resultado.toString()));
        }
    }
}
