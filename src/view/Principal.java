package view;

import javax.swing.JOptionPane;
import controller.PopController;
import java.io.IOException;

public class Principal {
    public static void main(String[] args) throws IOException {
        int ano = Integer.parseInt(JOptionPane.showInputDialog("Insira um ano: "));
        if(ano < 1960 || ano > 2023){
            try {
                throw new Exception("Ano " + ano + " não encontrado no arquivo.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        PopController pop = new PopController();
        {
            try {
                pop.buscaPop(ano);
            } catch (IOException e) {
                System.err.println("Erro:" + e.getMessage());
            }
        }
    }
}
