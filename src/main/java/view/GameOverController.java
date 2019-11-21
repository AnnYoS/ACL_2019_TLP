package view;

import engine.Cmd;
import engine.GameController;

import java.awt.event.KeyEvent;

public class GameOverController implements GameController {

    private Cmd commandeEnCours;

    @Override
    public Cmd getCommand() {
        return this.commandeEnCours;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char input = Character.toLowerCase(e.getKeyChar());
        switch (input) {
            case 'y':
                this.commandeEnCours = Cmd.YES;
                break;
            case 'n':
                this.commandeEnCours = Cmd.NO;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.commandeEnCours = Cmd.IDLE;
    }
}
