package ai.cogmission.fxmaps.model;

import java.util.ArrayList;
import java.util.List;

import ai.cogmission.fxmaps.ui.MapPane;
import javafx.application.Platform;


public class PersistentMap {
    protected List<Route> routes = new ArrayList<>();
    protected MapOptions mapOptions;
    protected String name;
    protected double width;
    protected double height;
    
    public PersistentMap(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Adds the specified {@link Route} to this store.
     * @param r     the route to add
     */
    public void addRoute(Route r) {
        if(!routes.contains(r)) {
            routes.add(r);
        }
    }
    
    /**
     * Removes the specified {@link Route} from this store.
     * 
     * @param r     the route to remove
     */
    public void removeRoute(Route r) {
        routes.remove(r);
    }
    
    public List<Route> getRoutes() {
        return routes;
    }
    
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    
    public double getWidth() {
        return width;
    }

    
    public void setWidth(double width) {
        this.width = width;
    }

    
    public double getHeight() {
        return height;
    }

    
    public void setHeight(double height) {
        this.height = height;
    }

    
    public MapOptions getMapOptions() {
        return mapOptions;
    }

    
    public void setMapOptions(MapOptions mapOptions) {
        this.mapOptions = mapOptions;
    }
    
    /**
     * Reifies all underlying JavaScript peers. This method is
     * called from the GPXPersistentMap load process.
     */
    public void createUnderlying() {
        if(mapOptions == null) {
            mapOptions = MapPane.getDefaultMapOptions();
        }
        
        // If deserializing in non-headless mode, build javascript peers
        if(Platform.isFxApplicationThread()) {
            for(Route r : routes) {
                if(r.origin == null || r.origin.getMarker() == null) {
                    continue;
                }
                
                r.origin.getMarker().createUnderlying();
                r.destination.getMarker().createUnderlying();
                for(Waypoint wp : r.observableDelegate) {
                    wp.getMarker().createUnderlying();
                }
                
                for(Polyline line : r.lines) {
                    line.createUnderlying();
                }
            }
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((routes == null) ? 0 : routes.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        PersistentMap other = (PersistentMap)obj;
        if(name == null) {
            if(other.name != null)
                return false;
        } else if(!name.equals(other.name))
            return false;
        if(routes == null) {
            if(other.routes != null)
                return false;
        } else if(!routes.equals(other.routes))
            return false;
        return true;
    }
     
}
