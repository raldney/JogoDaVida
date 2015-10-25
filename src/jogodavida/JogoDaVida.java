
package jogodavida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class JogoDaVida {

    static int ciclos;
    static Tabuleiro geracao1, geracao2;


    public static void main(String[] args) {

        int x = -1, i = 0;
        int[][] matriz = new int[0][0];
        try {
            Arquivo arquivo = new Arquivo("/home/raldney/NetBeansProjects/JogoDaVida/src/geracao.txt");
            FileReader arq = new FileReader(args[0]);
            ciclos = Integer.parseInt(args[1]);
            BufferedReader lerArq = new BufferedReader(arq);

            arquivo.gravar("\n Arquivo: " + args[0]);
            arquivo.gravar("\n\n Ciclos: " + ciclos);
            String linha = lerArq.readLine();
            arquivo.gravar("\n Matriz do arquivo: \n");

            while (linha != null) {

                if (x == -1) {
                    x = Integer.parseInt(linha);
                    matriz = new int[x][x];
                    linha = lerArq.readLine();
                    continue;

                }

                for (int j = 1; j <= x; j++) {
                    arquivo.gravar(" " + linha.substring(j - 1, j));
                    matriz[i][j - 1] = Integer.parseInt(linha.substring(j - 1, j));

                }

                arquivo.gravar("\n");
                i++;
                linha = lerArq.readLine();

            }

            arq.close();
            geracao2 = new Tabuleiro(matriz);
            geracao1 = new Tabuleiro(matriz.length);
            prox(ciclos, arquivo);
            arquivo.fechar();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
        return;
    }

    static void prox(int x, Arquivo arquivo) {

        int n = 0;

        int count = 0;

        while (n < x) {
            geracao1 = geracao2.novaGeracao();
            geracao2 = geracao1;
            n++;
        }
        arquivo.gravar("\n Matriz Final: \n\n");
        int tamanho = geracao2.tamanhoTabuleiro();
        int[][] celulas = geracao2.getCelulas();
        for (int i = 0; i <= (tamanho); i++) {
            for (int j = 0; j <= (tamanho); j++) {
                arquivo.gravar(" " + celulas[i][j]);
            }
            arquivo.gravar("\n");
        }

    }

}