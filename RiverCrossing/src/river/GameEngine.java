package river;

public class GameEngine {

    public enum Item {
        TOP, MID, BOTTOM, PLAYER;
    }

    public enum Location {
        START, FINISH, BOAT;
    }

    private GameObject top;
    private GameObject mid;
    private GameObject bottom;
    private GameObject player;
    private Location currentLocation;

    public GameEngine() {
        top = new Wolf();
        mid = new Goose();
        bottom = new Beans();
        player = new Farmer();
        currentLocation = Location.START;
    }

    public String getName(Item id) {
        switch (id) {
        case TOP:
            return top.getName();
        case MID:
            return mid.getName();
        case BOTTOM:
            return bottom.getName();
        default:
            return player.getName();
        }
    }

    public Location getLocation(Item id) {
        switch (id) {
        case TOP:
            return top.getLocation();
        case MID:
            return mid.getLocation();
        case BOTTOM:
            return bottom.getLocation();
        default:
            return player.getLocation();
        }
    }

    public String getSound(Item id) {
        switch (id) {
        case TOP:
            return top.getSound();
        case MID:
            return mid.getSound();
        case BOTTOM:
            return bottom.getSound();
        default:
            return player.getSound();
        }
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void loadBoat(Item id) {

        switch (id) {
        case TOP:
            if (top.getLocation() == currentLocation
                    && mid.getLocation() != Location.BOAT
                    && bottom.getLocation() != Location.BOAT) {
                top.setLocation(Location.BOAT);
            }
            break;
        case MID:
            if (mid.getLocation() == currentLocation
                    && top.getLocation() != Location.BOAT
                    && bottom.getLocation() != Location.BOAT) {
                mid.setLocation(Location.BOAT);
            }
            break;
        case BOTTOM:
            if (bottom.getLocation() == currentLocation
                    && top.getLocation() != Location.BOAT
                    && mid.getLocation() != Location.BOAT) {
                bottom.setLocation(Location.BOAT);
            }
            break;
        default: // do nothing
        }
    }

    public void unloadBoat() {
        if (top.getLocation() == Location.BOAT) {
            top.setLocation(currentLocation);
        } else if (mid.getLocation() == Location.BOAT) {
            mid.setLocation(currentLocation);
        } else if (bottom.getLocation() == Location.BOAT) {
            bottom.setLocation(currentLocation);
        }
    }

    public void rowBoat() {
        assert (currentLocation != Location.BOAT);
        if (currentLocation == Location.START) {
            currentLocation = Location.FINISH;
            player.setLocation(Location.FINISH);
        } else {
            currentLocation = Location.START;
            player.setLocation(Location.START);
        }
    }

    public boolean gameIsWon() {
        return top.getLocation() == Location.FINISH
                && mid.getLocation() == Location.FINISH
                && bottom.getLocation() == Location.FINISH;
    }

    public boolean gameIsLost() {
        if (mid.getLocation() == Location.BOAT) {
            return false;
        }
        if (mid.getLocation() != player.getLocation()
                && mid.getLocation() == top.getLocation()) {
            return true;
        }
        if (mid.getLocation() != player.getLocation()
                && mid.getLocation() == bottom.getLocation()) {
            return true;
        }
        return false;
    }
}
