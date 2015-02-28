//guilmour.com
package oldschoolmessenger;
import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame{    
    
    public GUI(){
        /* Inicia o Jframe */
        setTitle("theOldSchooMessenger v0.1");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.setLocation(400,100);
        
        /* Inicia o JPanel*/
        JPanel painel = new JPanel();

        
        /*Inica o próprio o Menu e itens do menu*/
        //Arquivo
        JMenu arquivo = new JMenu("Arquivo");
        JMenuItem amigos = new JMenuItem("Amigos");
        JMenuItem close = new JMenuItem("Sair");
        arquivo.add(amigos);
        arquivo.addSeparator();
        arquivo.add(close);
        
        //Editar
        JMenu editar = new JMenu("Editar");
        JMenuItem copiar = new JMenuItem("Copiar");
        JMenuItem colar = new JMenuItem("Colar");
        JMenuItem selecionar_tudo = new JMenuItem("Selecionar Tudo");
        editar.add(copiar);
        editar.add(colar);
        editar.add(selecionar_tudo);
        
        //Exibir
        JMenu exibir = new JMenu("Exibir");
        JMenuItem exibir1 = new JMenuItem("Campo1");
        JMenuItem exibir2 = new JMenuItem("Campo2");
        JMenuItem exibir3 = new JMenuItem("Campo 3");
        exibir.add(exibir1);
        exibir.add(exibir2);
        exibir.add(exibir3);
        
        //Ajuda
        JMenu help = new JMenu("Ajuda");
        JMenuItem help1 = new JMenuItem("Abrir Ajuda");
        JMenuItem help2 = new JMenuItem("Atualizações");
        JMenuItem help3 = new JMenuItem("Website");
        JMenuItem help4 = new JMenuItem("Sobre");
        help.add(help1);
        help.add(help2);
        help.add(help3);
        help.add(help4);
        
        
        
        /* Inciar a barra de menu*/
        JMenuBar bar = new JMenuBar();
        bar.add(arquivo);
        bar.add(editar);
        bar.add(exibir);
        bar.add(help);
        
        /* Atribui a barra ao frame atual*/
        this.setJMenuBar(bar);
    }
    

    
    
}

