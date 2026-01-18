package domain.service.api;

public final class DomainObject {

  private final String someValue;

  public DomainObject(String someValue) {
    this.someValue = someValue;
  }

  public String getSomeValue() {
    return someValue;
  }
}
