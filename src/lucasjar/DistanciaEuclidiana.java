package lucasjar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DistanciaEuclidiana {

    private static final String ASSETS_PATH = "/home/aluno/NetBeansProjects/lucasjar/src/lucasjar/";
    private static final String ENTRADA = "entrada.txt";
    private static final String SAIDA = "saida.csv";
    
    private List<Double> numeros = new ArrayList<>();
    private List<Integer> posicoes = new ArrayList<>();
    private List<Double> valores = new ArrayList<>();
    
    public void calcular(int qtdCasa) {      
        populaMatriz();
        
        int qtdNumeros = numeros.size()-(qtdCasa-1);
        
        if (qtdNumeros == 0) {
            System.out.println("Não foi possível popular a matriz.");
            return;
        }
        
        int valor = qtdNumeros/4;
        
        int part1 = valor;
        int part2 = valor*2;
        int part3 = valor*3;
        int part4 = valor*4;
        
        new Thread() {
            @Override
            public void run() {
                processoCalcular(0, part1, qtdCasa);
            }
            
        }.start();  
        
        new Thread() {
            @Override
            public void run() {
                processoCalcular(part1+1, part2, qtdCasa);
            }
            
        }.start();   
                
        new Thread() {
            @Override
            public void run() {
                processoCalcular(part2+1, part3, qtdCasa);
            }
            
        }.start();  
                        
        new Thread() {
            @Override
            public void run() {
                processoCalcular(part3+1, part4, qtdCasa);
                try {
                    gravaResultadosNoArquivo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }.start(); 
    }
    
    private void processoCalcular(int valorInicio, int valorFinal, int qtdCasa) {
        for (int i = 0; i < (valorFinal); i++) {
            double menor = Double.MAX_VALUE; 
            
            int posicao = 0;
            for (int j = 0; j < (valorFinal); j++) {
                
                if (i == j) {
                    continue;
                }

                double distancia = calculaDistanciaEntreDoisPontos(i, j, qtdCasa);
                
                if (distancia < menor) {
                    menor = distancia;
                    posicao = j;
                }
            }
            posicoes.add(posicao+1);
            valores.add(menor);                      
        }
    }
    
    private double calculaDistanciaEntreDoisPontos(int i, int j, int qtdCasas) {   
        double distancia = 0;
        for (int k = 0; k < qtdCasas; k++) {
            double aux =+ numeros.get(i+k) - numeros.get(j+k);
            distancia += aux * aux;
        }
        return Math.sqrt(distancia);
    }
    
    private void gravaResultadosNoArquivo() throws FileNotFoundException, IOException {      
        FileOutputStream os = new FileOutputStream(ASSETS_PATH + SAIDA);
        Writer wr = new OutputStreamWriter(os);
        BufferedWriter br = new BufferedWriter(wr);
        
        for (int i=0; i<posicoes.size(); i++) {
            br.write(posicoes.get(i) + ";" + valores.get(i));
            br.newLine();
        }
        br.close();        
    }
    
    private void populaMatriz() {        
        File file = new File(ASSETS_PATH + ENTRADA);
        String line = "";
        try {                        
            BufferedReader br = new BufferedReader(new FileReader(file));
            
            do {
                line = Objects.isNull(br.readLine()) ? "" : String.valueOf(br.readLine()).trim();              
                
                if (line.isEmpty() || line.equals("null")) {
                    break;
                }
                numeros.add(Double.parseDouble(line.replaceAll("[^0-9]", "")));
                
            } while (Objects.nonNull(line));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
