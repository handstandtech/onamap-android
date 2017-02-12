package net.onamap.android.model;

import java.io.Serializable;

public class StateData implements Serializable{
    public int icon;
    public int drawableResThumb;
    public String fullName;
    public String abbv;
    public boolean selected;

    public StateData(String abbv, String fullName, int drawableResThumb, int icon) {
        super();
        this.icon = icon;
        this.fullName = fullName;
        this.drawableResThumb = drawableResThumb;
        this.abbv = abbv;
        this.selected = false;
    }

    public void toggle() {
        selected = !selected;
    }
}