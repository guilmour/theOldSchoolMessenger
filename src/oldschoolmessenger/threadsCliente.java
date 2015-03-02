//guilmour
package oldschoolmessenger;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;

public class threadsCliente extends Thread {

    private String nomeCliente = null;
    private DataInputStream in = null;
    private PrintStream out = null;
    private Socket socketCliente = null;
    private final threadsCliente[] threads;
    private int maxConexoes;

    public threadsCliente(Socket socketCliente, threadsCliente[] threads) {
        this.socketCliente = socketCliente;
        this.threads = threads;
        maxConexoes = threads.length;
    }
    
    public void inicia() {
        int maxConexoes = this.maxConexoes;
        threadsCliente[] threads = this.threads;
    
        try{
            in = new DataInputStream(socketCliente.getInputStream());
            out = new PrintStream(socketCliente.getOutputStream());
            String nome;
            while (true) {
                out.println("Defina seu user: ");
                nome = in.readLine().trim();
                if (nome.indexOf('@') == -1) {
                    break;
                } else {
                    out.println("O Nome não pode conter '@'");
                }
            }
            
            out.println("Bem-vindo" + nome + "Para sair use /sair");

            synchronized (this) {
                for (int i = 0; i < maxConexoes; i++) {
                    if (threads[i] != null && threads[i] == this) {
                        nomeCliente = "@" + nome;
                        break;
                    }
                }
                for (int i = 0; i < maxConexoes; i++) {
                    if (threads[i] != null && threads[i] != this) {
                        threads[i].out.println("::: O usuário" + nome + " entrou na conversa :::");
                    }
                }
            }

            /* Start the conversation. */
            while (true) {
                String line = in.readLine();
                if (line.startsWith("/sair")) {
                    break;
                }
                /* If the message is private sent it to the given client. */

                    /* The message is public, broadcast it to all other clients. */
                    synchronized (this) {
                        for (int i = 0; i < maxConexoes; i++) {
                            if (threads[i] != null && threads[i].nomeCliente != null) {
                                threads[i].out.println("<" + nome + "> " + line);
                            }
                        }
                    }
            }

            synchronized (this) {
                for (int i = 0; i < maxConexoes; i++) {
                    if (threads[i] != null && threads[i] != this
                            && threads[i].nomeCliente != null) {
                        threads[i].out.println("::: O usuário " + nome + " deixou a conversa :::");
                    }
                }
            }
            out.println("::: Desconectado " + nome);

            /*
             * Clean up. Set the current thread variable to null so that a new client
             * could be accepted by the server.
             */
            synchronized (this) {
                for (int i = 0; i < maxConexoes; i++) {
                    if (threads[i] == this) {
                        threads[i] = null;
                    }
                }
            }
            /*
             * Close the output stream, close the input stream, close the socket.
             */
            in.close();
            out.close();
            socketCliente.close();
        } catch (IOException e) {

        }
    }
}

