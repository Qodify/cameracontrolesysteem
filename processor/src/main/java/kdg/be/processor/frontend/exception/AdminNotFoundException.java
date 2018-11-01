package kdg.be.processor.frontend.exception;

import javax.persistence.EntityNotFoundException;

public class AdminNotFoundException extends EntityNotFoundException {

  @Override
  public String getMessage() {
    return "Admin's username is not found in the database." + super.getMessage();
  }
}
