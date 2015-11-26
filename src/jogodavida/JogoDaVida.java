package jogodavida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class JogoDaVida {

    static int ciclos;
    static Tabuleiro geracao1, geracao2;

    public static void main(String[] args) {
        Random r = new Random();

        int x = -1, i = 0;
        int[][] matriz = new int[0][0];

//        for (i = 0; i <= 1000; i++) {
//            for (int j = 0; j <= 1000; j++) {
//                System.out.print(r.nextInt(2));
//            }
//            System.out.println(" ");
//        }
//        System.exit(1);
        try {
            Arquivo arquivo = new Arquivo("src/geracao.txt");
            FileReader arq = new FileReader(args[0]);
            ciclos = Integer.parseInt(args[1]);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();
            while (linha != null) {

                if (x == -1) {
                    x = Integer.parseInt(linha);
                    matriz = new int[x][x];
                    linha = lerArq.readLine();
                    continue;

                }
                for (int j = 1; j <= x; j++) {
                    matriz[i][j - 1] = Integer.parseInt(linha.substring(j - 1, j));

                }
                i++;
                linha = lerArq.readLine();

            }

            arq.close();
            geracao2 = new Tabuleiro(matriz);
            iniciar(ciclos, arquivo);
            arquivo.fechar();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }

    static void iniciar(int x, Arquivo arquivo) {

        int n = 0;
        while (n < x) {
            geracao2.threads = 1;
            Gerador g = new Gerador(geracao2, "Thread 1");
            g.start();
            Gerador g2 = new Gerador(geracao2, "Thread 2");
            g2.start();
            Gerador g3 = new Gerador(geracao2, "Thread 3");
            g3.start();
            Gerador g4 = new Gerador(geracao2, "Thread 4");
            g4.start();
            while (geracao2.threads > 0) {
                try {
                    // System.out.println("Threads em processamento");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            geracao2 = geracao2.novaGeracao();
            n++;
        }
        int tamanho = geracao2.tamanhoTabuleiro();
        int[][] celulas = geracao2.getCelulas();
        for (int i = 0; i <= (tamanho); i++) {
            for (int j = 0; j <= (tamanho); j++) {
                arquivo.gravar("" + celulas[i][j]);
            }
            arquivo.gravar("\n");
        }

    }

}
