package domain.service.impl;

import domain.service.api.DomainObject;
import domain.service.api.Service;
import jakarta.inject.Singleton;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Singleton
final class ServiceImpl implements Service {

  @Override
  public DomainObject saveDataSomewhere(String value) {
    if (Objects.requireNonNull(value, "Error. DomainObject.value is null.").isBlank()) {
      throw new IllegalArgumentException("Error. DomainObject.value is blank.");
    }
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return new DomainObject(value);
  }

  @Override
  public CompletableFuture<DomainObject> saveDataSomewhereAsync(String value) {
    if (Objects.requireNonNull(value, "Error. DomainObject.value is null.").isBlank()) {
      throw new IllegalArgumentException("Error. DomainObject.value is blank.");
    }

    return CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      return new DomainObject(value);
    });
  }
}
