package intro.app;

import java.util.NoSuchElementException;
import micronaut.javafx.launcher.api.JFXLauncher;

public final class Launcher {

  static void main(String[] args) {
    JFXLauncher
        .findLauncher()
        .orElseThrow(() -> new NoSuchElementException("JFXLauncher not found."))
        .launch(args);
  }

}
