package oldschoolmessenger;

import javax.swing.*;
import java.awt.*;

public class OldSchoolMessenger {

    public static void main(String[] args) {
        String textoComando = "/0";
        int flag=0;
        GUI janela = new GUI();        
        janela.ImprimeTexto("Olá user, você pode /criar ou /entrar em algum servidor de chat.\n");
        
        while(flag == 0){
        textoComando = janela.areaDeComando.getText();
            if((textoComando.equals("\n/criar") || textoComando.equals("/criar\n")) && janela.enterPressed == true){
                janela.ImprimeTexto("Criando novo server...");
                String[] arguments = new String[] {};
                new CreateServer().main(arguments);
                flag = 1;
            }else if((textoComando.equals("\n/entrar") || textoComando.equals("/entrar\n")) && janela.enterPressed == true){
                GUI.ImprimeTexto("Entre o ip para se conectar:\n");
                textoComando = janela.areaDeComando.getText();
                String[] arguments = new String[] {"localhost"};
                new ChatCliente().main(arguments);
                
            }
        }
    }
}
