package lucasjar;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client {

    public static void main(String[] args) throws IOException {
        DistanciaEuclidianaService server = new DistanciaEuclidianaServiceImpl();
        DistanciaEuclidianaService stub = (DistanciaEuclidianaService) UnicastRemoteObject.exportObject(server, 0);

        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("DistanciaEuclidianaService", stub);
    }
}