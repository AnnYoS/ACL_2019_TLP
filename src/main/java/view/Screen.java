package view;

import javax.swing.*;

public interface Screen {
    JPanel getContentPanel();
    void start();
    void pause();
    void step();
}
