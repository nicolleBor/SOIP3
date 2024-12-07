package controller;

import java.io.*;

import static java.lang.System.getProperty;

public class PopController {

    public void buscaPop(int ano) throws IOException{
        String pop = null;
        String anoTexto= String.valueOf(ano);
        String so = getProperty("os.name").toLowerCase();
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
            boolean encontrouAno = false;

            for(int i=0; i<tamanhoTudo-1; i++){
                String[]vetCampos=vetLinha[i].split(",");
                if(vetLinha[i].contains(anoTexto)){
                    String valor = vetCampos[3].substring(vetCampos[3].indexOf(":")+1).replace("\"","").trim();
                    encontrouAno = true;
                    pop = valor;
                    break;
                }
            }

            if(encontrouAno){
                System.out.println("A população do ano de " + ano + " é de: " + pop);
            } else {
                try {
                    throw new Exception("Ano " + ano + " não encontrado no arquivo.");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            buffer.close();
            isr.close();
            fis.close();
        } else {
            throw new FileNotFoundException("Arquivo 'pop.json' não encontrado.");
        }
    }
}
