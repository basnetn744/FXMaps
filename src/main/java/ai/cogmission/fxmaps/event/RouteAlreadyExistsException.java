package ai.cogmission.fxmaps.event;

/**
 * Indicates that a {@link Route} with the attempted name
 * already exists.
 * 
 * @author cogmission
 */
public class RouteAlreadyExistsException extends Exception {
    private static final long serialVersionUID = 1L;

    public RouteAlreadyExistsException(String message) {
        super(message);
    }
}
