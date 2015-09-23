package ai.cogmission.fxmaps.model;

/**
 * Map object pointer display
 * 
 * @author Rob Terpilowski
 * @author cogmission
 *
 */
public class Marker implements MapObject {
    private com.lynden.gmapsfx.javascript.object.Marker marker;
    
    private String title;
    
    public Marker(MarkerOptions options) {
        this.marker = new com.lynden.gmapsfx.javascript.object.Marker(options.convert());
    }
    
    /**
     * Sets the title of this Marker
     * @param title The Marker's new title
     */
    public void setTitle( String title ) {
        marker.setTitle(title);
        this.title = title;
    }
    
    /**
     * Returns the title of this {@code Marker}
     * @return
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Sets how the marker should be animated.  To clear the animation use Animation.NULL
     * @param animation The animation to use for this marker.
     */
    public void setAnimation( Animation animation ) {
        marker.setAnimation(animation.convert());
    }
    
    /**
     * Internally used to convert from this model to a GMapsFX model.
     * @return
     */
    public com.lynden.gmapsfx.javascript.object.Marker convert() {
        return marker;
    }
    
}