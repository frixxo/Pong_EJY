package event;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PlayerInput {
    private void keyPressed(KeyEvent event) {
        if (!running) {
            return;
        }
        KeyCode kc = event.getCode();
        switch (kc) {
            case UP:
                // TODO
                break;
            case DOWN:
                // TODO
                break;
            case Q:
                // TODO
                break;
            case A:
                // TODO
                break;
            default:  // Nothing
        }
    }

    private void keyReleased(KeyEvent event) {
        if (!running) {
            return;
        }
        KeyCode kc = event.getCode();
        switch (kc) {
            case UP:
            case DOWN:
                // TODO
                break;
            case A:
            case Q:
                // TODO
                break;
            default: // Nothing
        }
    }
}
