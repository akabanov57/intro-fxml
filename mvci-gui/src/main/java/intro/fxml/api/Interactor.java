package intro.fxml.api;

/**
 * The Interactor is the “business logic” component of MVCI. This is the place where Domain Objects
 * can exist, and where calls to external API’s, persistence layers and other services are made.
 * It’s able to see the Model, so it also contains the logic which updates the Model from data
 * retrieved from services via Domain Objects.
 */
public interface Interactor {

  void save();

  /**
   * The call must be made from FXAT only.
   */
  void beforeStartSave();

  /**
   * The call must be made from FXAT only.
   */
  void afterRunSave();
}
