/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corallus.modelo.util;

import java.util.ArrayList;
import corallus.modelo.Fase;
import corallus.modelo.Fase;

/**
 *
 * @author itakenami
 */
public class CarregadorFase {
    
    private static CarregadorFase instance;
    private ArrayList<Fase> fases;
    private ArrayList<Fase> clone;
    private int idxFase;
    
    private CarregadorFase(){
        idxFase = 0;
        fases = new ArrayList<Fase>();
        clone = new ArrayList<Fase>();
    }
    
    public void carregarFases(){
        fases = (ArrayList<Fase>) clone.clone();
        idxFase = 0;
    }
    
    public void addFase(Fase i){
        clone.add(i);
    }
    
    public Fase getProximaFase() throws Exception {
        if(fases.size()>0){
            Fase i = fases.get(0);
            fases.remove(0);
            idxFase++;
            return i;
        }else{
            throw new Exception("Não existe mais fase");
        }
        
    }
    
    public static CarregadorFase getInstance(){
        if(instance==null){
            instance = new CarregadorFase();
        }
        return instance;
    }
    
    public int getIdxFase(){
        return idxFase;
    }
}
