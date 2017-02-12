package net.onamap.android.dao;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import net.onamap.android.util.AssetUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import timber.log.Timber;

public class JsonParser {

    static Gson gson = new GsonBuilder().create();

    static Map<String, List<Photo>> photosForUsernameCache = new HashMap<>();

    public static List<Photo> parse(Context context, String username) {
        if (photosForUsernameCache.get(username) != null) {
            return photosForUsernameCache.get(username);
        }

        List<Photo> photos = new ArrayList<>();
        try {
            String assetFilename = "data/" + username + ".json";
            Timber.d(assetFilename);
            String json = AssetUtils.doit(context, assetFilename);
            JSONObject jsonObject = new JSONObject(json);
            photos = parsePhotoMap(jsonObject.getJSONObject("photosMap"));
            Map<String, Photo> photoMap = new HashMap<>();
            for (Photo photo : photos) {
                photoMap.put(photo.id, photo);
            }

            List<Country> countries = parseCountries(jsonObject.getJSONObject("world").getJSONObject("places"));

            for (Country country : countries) {
                for (Region region : country.regions) {
                    for (City city : region.cities) {
                        for (String cityPhotoId : city.photos) {
                            Photo photo = photoMap.get(cityPhotoId);
                            if (photo != null) {
                                photo.city = city.name;
                            } else {
                                Timber.w("Null Photo: " + cityPhotoId);
                            }
                        }
                    }
                    for (String regionPhotoId : region.photos) {
                        Photo photo = photoMap.get(regionPhotoId);
                        if (photo != null) {
                            photo.region = region.name;
                        } else {
                            Timber.w("Null Photo: " + regionPhotoId);
                        }
                    }
                }
                for (String countryPhotoId : country.photos) {
                    Photo photo = photoMap.get(countryPhotoId);
                    if (photo != null) {
                        photo.country = country.name;
                    } else {
                        Timber.w("Null Photo: " + countryPhotoId);
                    }
                }
            }

            photos = new ArrayList<>(photoMap.values());
        } catch (JSONException e) {
            Timber.e(e);
        }
        photosForUsernameCache.put(username, photos);
        return photos;
    }

    private static List<Country> parseCountries(JSONObject jsonObject) throws JSONException {
        List<Country> countries = new ArrayList<>();
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String countryName = keys.next();
            Timber.d("Country: " + countryName);
            JSONObject countryJsonObject = jsonObject.getJSONObject(countryName);
            Country country = new Country();
            country.name = countryName;

            country.photos = parsePhotos(countryJsonObject);

            country.regions = parseRegions(countryJsonObject);
            countries.add(country);
        }
        return countries;
    }

    private static List<String> parsePhotos(JSONObject jsonObject) throws JSONException {
        if (!jsonObject.isNull("photos")) {
            return gson.fromJson(jsonObject.getJSONArray("photos").toString(), new TypeToken<Collection<String>>() {
            }.getType());
        }
        return new ArrayList<>();

    }


    private static List<Region> parseRegions(JSONObject p) throws JSONException {
        List<Region> regions = new ArrayList<>();
        if (!p.isNull("places")) {
            JSONObject jsonObject = p.getJSONObject("places");
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String regionName = keys.next();
                Timber.d("Region: " + regionName);
                JSONObject regionJsonObject = jsonObject.getJSONObject(regionName);
                Region region = new Region();
                region.name = regionName;


                region.photos = parsePhotos(regionJsonObject);
                region.cities = parseCities(regionJsonObject);
                regions.add(region);
            }
        }
        return regions;
    }


    private static List<City> parseCities(JSONObject p) throws JSONException {
        List<City> cities = new ArrayList<>();

        if (!p.isNull("places")) {
            JSONObject jsonObject = p.getJSONObject("places");
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String cityName = keys.next();
                JSONObject cityJsonObject = jsonObject.getJSONObject(cityName);
                Timber.d("City: " + cityName);
                City city = new City();
                city.name = cityName;
                city.photos = parsePhotos(cityJsonObject);
                cities.add(city);
            }
        }
        return cities;
    }

    private static List<Photo> parsePhotoMap(JSONObject photoMap) throws JSONException {
        List<Photo> photos = new ArrayList<>();
        Iterator<String> keys = photoMap.keys();
        while (keys.hasNext()) {
            String id = keys.next();
            Photo photo = gson.fromJson(photoMap.getJSONObject(id).toString(), Photo.class);
            photo.id = id;
            photos.add(photo);
        }
        return photos;
    }


}
