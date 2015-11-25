package jogodavida;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Raldney
 */
public class Gerador extends Thread {

    Tabuleiro geracao;
    String nome;
    int x;

    public Gerador(Tabuleiro geracao, String nome) {
        this.geracao = geracao;
        this.nome = nome;
        this.x = x;
    }

    @Override
    public void run() {
        int vizinhos = 0;
        for (int i = geracao.getLinha(); i <= geracao.tamanhoTabuleiro(); i = geracao.getLinha()) {
            for (int j = 0; j <= geracao.tamanhoTabuleiro(); j++) {
                vizinhos = geracao.getVizinhos(i, j);
                if (geracao.celulas[i][j] == 1) {
                    if (vizinhos < 2 || vizinhos > 3) {
                        geracao.setNovasCelulas(i, j, 0);
                    } else {
                        geracao.setNovasCelulas(i, j, 1);
                    }
                }
                if (geracao.celulas[i][j] == 0 && vizinhos == 3) {
                    geracao.setNovasCelulas(i, j, 1);
                }
            }
        }
        geracao.reduzirThread(this.nome);
    }
    
}
