package net.onamap.android.dao;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class PhotoDao {

    private List<Photo> photos;

    public PhotoDao(Context context) {
        photos = JsonParser.parse(context, "handstandsam");
    }

    public List<Photo> getPhotosForState(String stateName) {
        List<Photo> photosInState = new ArrayList<>();
        for (Photo photo : photos) {
            if (photo.country.equalsIgnoreCase("United States")) {
                if (photo.region != null && photo.region.equalsIgnoreCase(stateName)) {
                    photosInState.add(photo);
                }
            }
        }
        return photosInState;
    }
}
