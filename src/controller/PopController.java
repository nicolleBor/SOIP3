package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.getProperty;

public class PopController {

    public String buscaPop(int ano) throws IOException{
        String pop = null;
        String anoTexto= String.valueOf(ano);
        String so = getProperty("os.name").toLowerCase();
        System.out.println("Sistema Operacional detectado: " + so);
        String path = "";

        if(so.contains("win")){
            path = "C:/TEMP";
        } else if(so.contains("nix") || so.contains("nux") || so.contains("aix")){
            path = "/tmp";
        }

        File arq = new File(path, "pop.json");

        if(arq.exists() && arq.isFile()){
            FileInputStream fis = new FileInputStream(arq);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader buffer = new BufferedReader(isr);
            String linha = buffer.readLine();

            String[] vetLinha = linha.split("}");
            int tamanhoTudo = vetLinha.length;

            for(int i=0; i<tamanhoTudo-1; i++){
                String[]vetCampos=vetLinha[i].split(",");
                if(vetLinha[i].contains(anoTexto)){
                    String valor = vetCampos[3].substring(vetCampos[3].indexOf(":")+1).replace("\"","").trim();
                    pop = valor;
                    break;
                }
            }
            buffer.close();
            isr.close();
            fis.close();
        }
        return pop;
    }
}
