package oldschoolmessenger;

import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.DataInputStream;

/**
 *
 * @author guilmour
 */
public class CreateServer {

    ServerSocket socketServidor = null;
    Socket socketCliente = null;
    private static final int maxConexoes = 10;
    private static final threadsCliente[] threads = new threadsCliente[maxConexoes];
    public static int porta = 2222;

    public void main(String[] args) {
        
        /* Número da porta*/
        if (args.length < 1) {            
                GUI.ImprimeTexto("\nServer criado usando a porta: " + porta);

        } else {
            porta = Integer.valueOf(args[0]).intValue();
        }
        
        /* Cria o SOcket na porta declarada*/
        try {
            socketServidor = new ServerSocket(porta);
        } catch (IOException e) {
            GUI.ImprimeTexto(e.toString());
        }
        
        /* Cria um socket diferente para cada cliente chamando a class de threads*/
        while(true){
            try{
                socketCliente = socketServidor.accept();
                int i = 0;
                for(i=0; i < maxConexoes; i++){
                    if(threads[i] ==null){
                        (threads[i] = new threadsCliente(socketCliente, threads)).start();
                        break;
                    }
                }
                
                if(i == maxConexoes){
                    GUI.ImprimeTexto("Servidor cheio!");
                    socketCliente.close();                    
                }
            } catch(IOException e){
                GUI.ImprimeTexto(e.toString());
            }
                
        }
    }
        
        
}


