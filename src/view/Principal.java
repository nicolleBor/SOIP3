package view;

import javax.swing.JOptionPane;
import controller.PopController;
import java.io.IOException;

public class Principal {
    public static void main(String[] args) throws IOException {
        int ano = Integer.parseInt(JOptionPane.showInputDialog("Insira um ano: "));
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
