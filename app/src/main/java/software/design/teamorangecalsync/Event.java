package software.design.teamorangecalsync;

public abstract class Event {
    protected int color;
    protected String Name;
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


}
