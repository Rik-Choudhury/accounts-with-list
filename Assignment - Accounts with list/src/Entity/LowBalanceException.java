package Entity;

public class LowBalanceException extends RuntimeException {
    String message;
    public LowBalanceException(String message) {
      this.message = message;
    }

  @Override
  public String getMessage() {
    return message;
  }
}
