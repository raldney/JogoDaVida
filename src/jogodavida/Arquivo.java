/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavida;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author raldney
 */
public class Arquivo {
     FileWriter geracoesTxt;
     PrintWriter gravarArq;
     
     Arquivo(String caminho) throws IOException{
        geracoesTxt = new FileWriter(caminho);
        gravarArq = new PrintWriter(geracoesTxt);
     }
     
     public void gravar(String n){
         gravarArq.print(n);
     }
     
     public void fechar() throws IOException{
        geracoesTxt.close();
     }
}
