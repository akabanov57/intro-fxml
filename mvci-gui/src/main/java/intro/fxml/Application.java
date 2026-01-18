package intro.fxml;

import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Inject;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import micronaut.javafx.launcher.api.ApplicationDelegate;
import micronaut.javafx.launcher.api.FxmlLoaderFactory;

@Context
@Requires(beans = {FxmlLoaderFactory.class})
final class Application implements ApplicationDelegate {

  private final FxmlLoaderFactory fxmlLoaderFactory;

  @Inject
  Application(FxmlLoaderFactory fxmlLoaderFactory) {
    this.fxmlLoaderFactory = fxmlLoaderFactory;
  }

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = fxmlLoaderFactory.get(Application.class.getResource("MainView.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 480, 240);
    stage.setTitle("MVCI");
    stage.setScene(scene);
    stage.show();
  }

}
