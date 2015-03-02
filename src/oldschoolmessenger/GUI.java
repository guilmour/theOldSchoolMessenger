//guilmour.com
package oldschoolmessenger;
import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame{    
    
    public GUI(){
        /* Inicia o Jframe */
        setTitle("theOldSchoolMessenger v0.1");
        setSize(800,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(400,100);
        //this.setResizable(false);
        
        /* Inicia o JPanel*/
        JPanel painel = new JPanel();
        add(painel);      
        
        
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
        bar.setVisible(true);
        
        /* Atribui a barra ao frame atual*/
        setJMenuBar(bar);        
        
        /*Area de Texto mensagens*/
        JTextArea areaDeTexto = new JTextArea();
        areaDeTexto.setBackground(Color.black);
        areaDeTexto.setLineWrap(true);
        areaDeTexto.setEditable(false);
        areaDeTexto.setFont(new java.awt.Font("Lucida Console", 0, 14));
        areaDeTexto.setForeground(Color.green);
        areaDeTexto.setCaretColor(Color.green);
        areaDeTexto.setText("Testando");  
        areaDeTexto.setWrapStyleWord(true);
        painel.add(areaDeTexto);        
        
        //scrollbar
        add(new JScrollPane(areaDeTexto), BorderLayout.CENTER);
        
        /*Area de TCOmando e enviarr*/
        JTextArea areaDeComando = new JTextArea();
        areaDeComando.setBackground(Color.black);
        areaDeComando.setRows(4);
        areaDeComando.setLineWrap(true);
        areaDeComando.setEditable(true);
        areaDeComando.setFont(new java.awt.Font("Lucida Console", 0, 14));
        areaDeComando.setForeground(Color.green);
        areaDeComando.setCaretColor(Color.green); 
        areaDeComando.setWrapStyleWord(true);
        areaDeComando.setCaretPosition(0);
        add(areaDeComando, BorderLayout.SOUTH);
        add(new JScrollPane(areaDeComando), BorderLayout.SOUTH);
        
        
        
        setVisible(true);    
        
        
       
    }
    

    
    
}

