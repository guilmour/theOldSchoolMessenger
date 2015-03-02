package oldschoolmessenger;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
/* Classe que monitora chat do cliente */
public class ChatCliente {
    /* Acesso do Cliente */
    static class acessodoChat extends Observable {
        private Socket socket;
        private OutputStream out;
        
        @Override
        public void notifyObservers(Object arg){
            super.setChanged();
            super.notifyObservers(arg);
        }
        
        /* Cria socket, a thread de recebimento*/
        public acessodoChat(String servidor, int porta) throws IOException {
            socket = new Socket(servidor, porta);
            out = socket.getOutputStream();
            
            Thread threadRecebimento = new Thread() {
                @Override
                public void run() {
                    try{
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String linha;
                        while((linha = reader.readLine()) != null)
                                notifyObservers(linha);
                    } catch (IOException ex){
                        notifyObservers(ex);
                    }
                }
            };
            threadRecebimento.start();
        }
        
        private static final String CRLF = "\r\n"; //newline
        
        /* Envia uma linha de texto */
        public void send (String text){
            try{
                out.write((text +CRLF).getBytes());
                out.flush();
            } catch (IOException ex){
                notifyObservers(ex);
            }
        }
        
        /* Fecha o soquete */
        public void close(){
            try{
                socket.close();
            } catch(IOException ex){
                notifyObservers(ex);
            }
        }
        
        
    /** Chat client UI */
    static class ChatFrame extends JFrame implements Observer {

        private JTextArea textArea;
        private JTextField inputTextField;
        private JButton sendButton;
        private acessodoChat chatAccess;

        public ChatFrame(acessodoChat chatAccess) {
            this.chatAccess = chatAccess;
            chatAccess.addObserver(this);
            buildGUI();
        }

        /** Builds the user interface */
        private void buildGUI() {
            textArea = new JTextArea(20, 50);
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            add(new JScrollPane(textArea), BorderLayout.CENTER);

            Box box = Box.createHorizontalBox();
            add(box, BorderLayout.SOUTH);
            inputTextField = new JTextField();
            sendButton = new JButton("Send");
            box.add(inputTextField);
            box.add(sendButton);

            // Action for the inputTextField and the goButton
            ActionListener sendListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String str = inputTextField.getText();
                    if (str != null && str.trim().length() > 0)
                        chatAccess.send(str);
                    inputTextField.selectAll();
                    inputTextField.requestFocus();
                    inputTextField.setText("");
                }
            };
            inputTextField.addActionListener(sendListener);
            sendButton.addActionListener(sendListener);

            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    chatAccess.close();
                }
            });
        }

        /** Updates the UI depending on the Object argument */
        public void update(Observable o, Object arg) {
            final Object finalArg = arg;
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    textArea.append(finalArg.toString());
                    textArea.append("\n");
                }
            });
        }
    }
        
        
        //string
        
        
    }   
    
    public static void main(String[] args) {
        String server = args[0];
        int port = 2222;
        acessodoChat access = null;
        try {
            access = new acessodoChat("localhost", port);
            
        } catch (IOException ex) {
            System.out.println("Cannot connect to " + server + ":" + port);
            ex.printStackTrace();
            System.exit(0);
        }
    }
}
