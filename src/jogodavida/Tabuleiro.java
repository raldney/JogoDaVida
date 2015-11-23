/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavida;

/**
 *
 * @author raldney
 */
public class Tabuleiro {

    public int[][] celulas;
    private int[][] novasCelulas;
    public int threads = 0;
    private int linha = 0, coluna = 0;

    Tabuleiro(int[][] celulas) {
        this.celulas = celulas;
        this.novasCelulas = new int[celulas.length][celulas.length];
        for (int i = 0; i <= (celulas.length - 1); i++) {
            for (int j = 0; j <= (celulas.length - 1); j++) {
                this.novasCelulas[i][j] = 0;

            }
        }
    }

    Tabuleiro(int tamanho) {
        this.celulas = new int[tamanho][tamanho];
        this.novasCelulas = new int[tamanho][tamanho];
        for (int i = 0; i <= (tamanho - 1); i++) {
            for (int j = 0; j <= (tamanho - 1); j++) {
                this.celulas[i][j] = 0;
                this.novasCelulas[i][j] = 0;

            }
        }
    }

    public synchronized void setNovasCelulas(int i, int j, int valor) {
        this.novasCelulas[i][j] = valor;
    }

    public synchronized void reduzirThread(String nome) {
        this.threads--;
        System.out.println(nome + " Acabou! o tabuleiro tem " + this.threads + " threads restantes");

    }

    public synchronized int getLinha() {
        return linha++;
    }

    public synchronized int getColuna() {
        return coluna++;
    }

    public int getVizinhos(int i, int j) {
        int count = 0;
        if (i > 0) {
            if (celulas[i - 1][j] == 1) {
                count++;
            }
            if (j < celulas.length - 1) {
                if (celulas[i - 1][j + 1] == 1) {
                    count++;
                }
            }
            if (j > 0) {
                if (celulas[i - 1][j - 1] == 1) {
                    count++;
                }
            }
        }
        if (i < celulas.length - 1) {
            if (celulas[i + 1][j] == 1) {
                count++;
            }
        }
        if (j > 0) {
            if (celulas[i][j - 1] == 1) {
                count++;
            }
            if (i > 0) {
                if (j < celulas.length - 1) {
                    if (celulas[i - 1][j + 1] == 1) {
                        count++;
                    }
                }
                if (celulas[i - 1][j - 1] == 1) {
                    count++;
                }
            }
            if (i < celulas.length - 1) {
                if (celulas[i + 1][j - 1] == 1) {
                    count++;
                }
            }
        } else if (j < celulas.length - 1) {
            if (celulas[i][j + 1] == 1) {
                count++;
            }
        }
        if (i < celulas.length - 1 && j < celulas.length - 1) {
            if (celulas[i + 1][j + 1] == 1) {
                count++;
            }
        }
        return count;
    }

//    public Tabuleiro novaGeracao() {
//        int vizinhos = 0;
//        int[][] novoTabuleiro = new int[celulas.length][celulas.length];
//        for (int i = 0; i <= celulas.length - 1; i++) {
//            for (int j = 0; j <= celulas.length - 1; j++) {
//                vizinhos = this.getVizinhos(i, j);
//                if (celulas[i][j] == 1) {
//                    if (vizinhos < 2 || vizinhos > 3) {
//                        novoTabuleiro[i][j] = 0;
//                    } else {
//                        novoTabuleiro[i][j] = 1;
//                    }
//                }
//                if (celulas[i][j] == 0 && vizinhos == 3) {
//                    novoTabuleiro[i][j] = 1;
//                }
//            }
//        }
//        return new Tabuleiro(novoTabuleiro);
//    }
    public int[][] getCelulas() {
        return celulas;
    }

    public void printarNovasCelulas() {
        for (int i = 0; i <= novasCelulas.length - 1; i++) {
            for (int j = 0; j <= novasCelulas.length - 1; j++) {
                System.out.print(novasCelulas[i][j] + "  ");
            }
            System.out.println(" ");
        }
    }

    public Tabuleiro novaGeracao() {
        return new Tabuleiro(novasCelulas);
    }

    public int tamanhoTabuleiro() {
        return celulas.length - 1;
    }

}
