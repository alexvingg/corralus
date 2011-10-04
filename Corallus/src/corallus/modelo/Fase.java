/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corallus.modelo;

import java.util.Random;
import corallus.Config;
import corallus.ui.window.GerenciadorJanela;

/**
 *
 * @author itakenami
 */
public abstract class Fase {
    
    private Tabuleiro tabuleiro;
    private Cobra cobra;
    private Jogo jogo;
    
    public final int CIMA = 1;
    public final int BAIXO = 2;
    public final int DIREITA = 3;
    public final int ESQUERDA = 4;
    
    public abstract void iniciar();
    public abstract void comeu();
    
    final void carregar(Jogo j){
        jogo = j;
        tabuleiro = j.getTabuleiro();
        cobra = j.getCobra();
        iniciar();
    }
    
    final void recarregar(Jogo j){
        jogo = j;
        tabuleiro = j.getTabuleiro();
        cobra = j.getCobra();
    }
    
    public final void fim(){
        jogo.setFim(true);
    }
    
    public final void addRabo(){
        getCobra().addRabo();
    }
    
    public final int getPosDesocupada(){
        int x;
        do {
            x = new Random().nextInt(getTabuleiro().TOTAL_CASAS);
        } while (getTabuleiro().getCasa(x).isOcupada());
        return x;
    }
    
    public final void novaComida(int pos) {
        getTabuleiro().getCasa(pos).setItem(new Comida());
        getTabuleiro().addAlteracao(pos);

    }
    
    public final void novaComida() {
        novaComida(getPosDesocupada());

    }
    
    public final void bordaPedra(){
        this.bordaPedra(false);
    }
     
    public final void bordaPedra(boolean reload){
        
        Tabuleiro tabuleiro = getTabuleiro();
        
        int delta = (tabuleiro.TOTAL_CASAS - Config.LARGURA_TABULEIRO);

        for (int x = 1; x <= Config.LARGURA_TABULEIRO; x++) {
            tabuleiro.getCasa(x).setItem(new Pedra());
            tabuleiro.getCasa(delta + x).setItem(new Pedra());
            if(reload){
                tabuleiro.addAlteracao(x);
                tabuleiro.addAlteracao(delta + x);
            }
        }


        int i = 1;
        for (int x = 1; x < Config.ALTURA_TABULEIRO; x++) {
            tabuleiro.getCasa(i).setItem(new Pedra());           
            tabuleiro.getCasa(i + (Config.LARGURA_TABULEIRO - 1)).setItem(new Pedra());            
            if(reload){
                tabuleiro.addAlteracao(i);
                tabuleiro.addAlteracao(i + (Config.LARGURA_TABULEIRO - 1));
            }
            
            i += Config.LARGURA_TABULEIRO;
        }
    }
    
    public final void addPedra(int pos){
        this.addPedra(false,pos);
    }
    
    public final void addPedra(boolean reload, int pos){
        tabuleiro.getCasa(pos).setItem(new Pedra());
        if(reload){
            tabuleiro.addAlteracao(pos);
        }
    }
    
    public final void caixaPedraAberta(int pos, int largura, int altura){
        linhaHorizontalPedra(pos, largura);
        linhaVerticalPedra(pos, altura);
        linhaHorizontalPedra(pos+(Config.LARGURA_TABULEIRO*altura)-Config.LARGURA_TABULEIRO, largura);
        linhaVerticalPedra(pos+largura-1, altura);
        
    }
    
    public final void caixaPedra(int pos, int qtd){
        this.caixaPedra(false, pos, qtd);
        
    }
    
    public final void caixaPedra(boolean reload, int pos, int qtd){
        for(int y=0;y<qtd;y++){
            for(int x=pos;x<pos+qtd;x++){
                tabuleiro.getCasa(x).setItem(new Pedra());
                if(reload){
                    tabuleiro.addAlteracao(x);
                }
            }
            pos+=Config.LARGURA_TABULEIRO; 
        }
    }
    
    public final void linhaVerticalPedra(int pos, int qtd){
        this.linhaVerticalPedra(false, pos, qtd);
        
    }
    
    public final void linhaVerticalPedra(boolean reload, int pos, int qtd){
        for(int y=0;y<qtd;y++){
            tabuleiro.getCasa(pos).setItem(new Pedra());
            if(reload){
                tabuleiro.addAlteracao(pos);
            }
            pos+=Config.LARGURA_TABULEIRO;
        }
    }
    
    public final void linhaHorizontalPedra(int pos, int qtd){
        this.linhaHorizontalPedra(false, pos, qtd);
    }
    
    public final void linhaHorizontalPedra(boolean reload, int pos, int qtd){
        for(int x=pos;x<pos+qtd;x++){
            tabuleiro.getCasa(x).setItem(new Pedra());
            if(reload){
                tabuleiro.addAlteracao(x);
            }
        }
    }
    
    public final void diminuirVelocidade(int v){
        jogo.diminuirVelocidade(v);
    }
    
    public final void aumentarVelocidade(int v){
        jogo.aumentarVelocidade(v);
    }
    
    public final void setDefaultVelocidade(){
        jogo.setVelocidade(100);
    }
    
    public final void addPontos(int p){
        jogo.addPontos(p);
    }
    
    public final void mudarFase(){
        jogo.mudarFase();
        GerenciadorJanela.getInstance().getPrincipal().setDisplayTexto("");
        jogo.setMontarTabuleiro(true);
    }
    
    public final void moverPara(int pos, int direcao){
        jogo.mover(pos, direcao);
    }
    
    public final void reiniciarFase(){
        jogo.recarregarFase();
        jogo.setMontarTabuleiro(true);
        
    }

    public final Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public final Cobra getCobra() {
        return cobra;
    }
    
    public void setItem(int pos, Item i){
        tabuleiro.getCasa(pos).setItem(i);
    }
    
    public void bateuCobra() {
        fim();
    }
    
    public void bateuPedra(){
        fim();
    }
    
    public void fimFase(){
        
    }
    
    public void moveu(){
        
    }
    
    public String getTituloFase(){
        return this.getClass().getName();
    }
    
    public String getTextoFase(){
        return "Texto para " + this.getClass().getName();
    }
    
    public void setDisplayTexto(String txt){
        GerenciadorJanela.getInstance().getPrincipal().setDisplayTexto(txt);
    }
    
}
