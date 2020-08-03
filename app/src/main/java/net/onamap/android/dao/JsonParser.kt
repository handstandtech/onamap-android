package net.onamap.android.dao

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import net.onamap.android.util.AssetUtils
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber
import java.util.*

object JsonParser {
    var gson = GsonBuilder().create()
    var photosForUsernameCache: MutableMap<String, List<Photo>> =
        HashMap()

    fun parse(
        context: Context?,
        username: String
    ): List<Photo>? {
        if (photosForUsernameCache[username] != null) {
            return photosForUsernameCache[username]
        }
        var photos: List<Photo> =
            ArrayList()
        try {
            val assetFilename = "data/$username.json"
            Timber.d(assetFilename)
            val json = AssetUtils.doit(context!!, assetFilename)
            val jsonObject = JSONObject(json)
            photos = parsePhotoMap(jsonObject.getJSONObject("photosMap"))
            val photoMap: MutableMap<String?, Photo> =
                HashMap()
            for (photo in photos) {
                photoMap[photo.id] = photo
            }
            val countries = parseCountries(
                jsonObject.getJSONObject("world").getJSONObject("places")
            )
            for (country in countries) {
                for (region in country.regions!!) {
                    for (city in region.cities!!) {
                        for (cityPhotoId in city.photos) {
                            val photo = photoMap[cityPhotoId]
                            if (photo != null) {
                                photo.city = city.name
                            } else {
                                Timber.w("Null Photo: $cityPhotoId")
                            }
                        }
                    }
                    for (regionPhotoId in region.photos) {
                        val photo = photoMap[regionPhotoId]
                        if (photo != null) {
                            photo.region = region.name
                        } else {
                            Timber.w("Null Photo: $regionPhotoId")
                        }
                    }
                }
                for (countryPhotoId in country.photos) {
                    val photo = photoMap[countryPhotoId]
                    if (photo != null) {
                        photo.country = country.name
                    } else {
                        Timber.w("Null Photo: $countryPhotoId")
                    }
                }
            }
            photos = ArrayList(photoMap.values)
        } catch (e: JSONException) {
            Timber.e(e)
        }
        photosForUsernameCache[username] = photos
        return photos
    }

    @Throws(JSONException::class)
    private fun parseCountries(jsonObject: JSONObject): List<Country> {
        val countries: MutableList<Country> = ArrayList()
        val keys = jsonObject.keys()
        while (keys.hasNext()) {
            val countryName = keys.next()
            Timber.d("Country: $countryName")
            val countryJsonObject = jsonObject.getJSONObject(countryName)
            val country = Country()
            country.name = countryName
            country.photos = parsePhotos(countryJsonObject)
            country.regions = parseRegions(countryJsonObject)
            countries.add(country)
        }
        return countries
    }

    @Throws(JSONException::class)
    private fun parsePhotos(jsonObject: JSONObject): List<String> {
        return if (!jsonObject.isNull("photos")) {
            gson.fromJson(
                jsonObject.getJSONArray(
                    "photos"
                ).toString(), object : TypeToken<Collection<String?>?>() {}.type
            )
        } else ArrayList()
    }

    @Throws(JSONException::class)
    private fun parseRegions(p: JSONObject): List<Region> {
        val regions: MutableList<Region> =
            ArrayList()
        if (!p.isNull("places")) {
            val jsonObject = p.getJSONObject("places")
            val keys = jsonObject.keys()
            while (keys.hasNext()) {
                val regionName = keys.next()
                Timber.d("Region: $regionName")
                val regionJsonObject = jsonObject.getJSONObject(regionName)
                val region = Region()
                region.name = regionName
                region.photos = parsePhotos(regionJsonObject)
                region.cities = parseCities(regionJsonObject)
                regions.add(region)
            }
        }
        return regions
    }

    @Throws(JSONException::class)
    private fun parseCities(p: JSONObject): List<City> {
        val cities: MutableList<City> = ArrayList()
        if (!p.isNull("places")) {
            val jsonObject = p.getJSONObject("places")
            val keys = jsonObject.keys()
            while (keys.hasNext()) {
                val cityName = keys.next()
                val cityJsonObject = jsonObject.getJSONObject(cityName)
                Timber.d("City: $cityName")
                val city = City()
                city.name = cityName
                city.photos = parsePhotos(cityJsonObject)
                cities.add(city)
            }
        }
        return cities
    }

    @Throws(JSONException::class)
    private fun parsePhotoMap(photoMap: JSONObject): List<Photo> {
        val photos: MutableList<Photo> =
            ArrayList()
        val keys = photoMap.keys()
        while (keys.hasNext()) {
            val id = keys.next()
            val photo =
                gson.fromJson(
                    photoMap.getJSONObject(id).toString(), Photo::class.java
                )
            photo.id = id
            photos.add(photo)
        }
        return photos
    }
}