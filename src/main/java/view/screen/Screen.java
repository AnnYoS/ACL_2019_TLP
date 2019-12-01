package view.screen;

import javax.swing.*;

public interface Screen {
    JPanel getContentPanel();
    void start();
    void pause();
    void step();

    boolean keepScreen();
    Screen getNextScreen();
}
