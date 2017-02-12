package net.onamap.android.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.onamap.android.dao.Place;

public class GsonUtil extends Place {
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
}
