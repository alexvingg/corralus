/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corallus.modelo;

/**
 *
 * @author itakenami
 */
public abstract class Customizado implements Item {

    public abstract String getImagemPath();
    
    public abstract void acao(Fase f);

    @Override
    public final char getChar() {
        return '%';
    }
    
}
