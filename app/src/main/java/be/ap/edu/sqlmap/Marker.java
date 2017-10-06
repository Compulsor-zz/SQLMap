package be.ap.edu.sqlmap;

/**
 * Created by schel on 6/10/2017.
 */

public class Marker {

    private int id;
    private double lat;
    private double lon;
    private String description;

    public Marker(int id, double lat, double lon, String description) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.description = description;
    }

    public Marker() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
