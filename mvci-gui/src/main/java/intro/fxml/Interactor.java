package intro.fxml;

import domain.service.api.DomainObject;
import domain.service.api.Service;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.concurrent.ExecutionException;

@Singleton
final class Interactor {

  private int propertyLength = 0;

  private final ViewModel viewModel;

  private final Service service;

  @Inject
  Interactor(ViewModel viewModel, Service service) {
    this.viewModel = viewModel;
    this.service = service;
  }

  void save() {
    viewModel.setProperty2("");
    // Exactly. Not whenComplete. Block thread here.
    final DomainObject domainObject;
    try {
     domainObject = service
          .saveDataSomewhereAsync(viewModel.getProperty1() + " --> " + propertyLength)
          .get();
      viewModel.setProperty1("");
      viewModel.setProperty2(domainObject.getSomeValue());
      propertyLength = 0;
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }

  void updatePropertyLength(int value) {
    propertyLength = value;
  }
}
