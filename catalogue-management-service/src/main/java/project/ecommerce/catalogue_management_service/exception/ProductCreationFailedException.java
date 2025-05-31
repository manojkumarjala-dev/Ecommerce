package project.ecommerce.catalogue_management_service.exception;

public class ProductCreationFailedException extends RuntimeException {
    public ProductCreationFailedException(String message) {
        super(message);
    }
}
