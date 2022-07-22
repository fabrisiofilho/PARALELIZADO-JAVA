package lucasjar;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class Server {

    public static void main(String[] args) throws IOException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(); //Mais maquinas.
        DistanciaEuclidianaService client = (DistanciaEuclidianaService) registry.lookup("DistanciaEuclidianaService");
        List<DadosSaida> resultados = client.processoCalcular(0, 2998);
        ControladorDeDocumento.gravaResultados(resultados);
    }
}