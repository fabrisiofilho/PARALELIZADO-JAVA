package lucasjar;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DistanciaEuclidianaService extends Remote {
    List<DadosSaida> processoCalcular(int posicaoInicial, int posicaoFinal) throws RemoteException;
}