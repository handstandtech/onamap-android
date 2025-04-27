package net.onamap.android.dao

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import net.onamap.android.util.AssetUtils
import net.onamap.models.models.City
import net.onamap.models.models.Country
import net.onamap.models.models.Photo
import net.onamap.models.models.Region
import org.json.JSONObject
import timber.log.Timber
import java.util.*

object JsonParser {
    private var gson: Gson = GsonBuilder().create()

    var photosForUsernameCache: MutableMap<String, List<Photo>> = mutableMapOf()

    private fun parseFileForJson(context: Context, username: String): JSONObject {
        val assetFilename = "data/$username.json"
        Timber.d(assetFilename)
        val json = AssetUtils.doit(context, assetFilename)
        return JSONObject(json)
    }

    private data class CountryRegionCity(val country: String?, val region: String?, val city: String?)

    private fun getCountryRegionCity(photoMap: Map<String?, Photo>, countriesList: List<Country>): CountryRegionCity {
        var country: String? = null
        var region: String? = null
        var city: String? = null

        countriesList.forEach { country ->
            country.regions?.forEach { region ->
                region.cities?.forEach { city ->
                    city.photos.forEach { cityPhotoId ->
                        photoMap[cityPhotoId]?.apply {
                            this.city = city.name
                        }
                    }
                }
                region.photos.forEach { regionPhotoId ->
                    photoMap[regionPhotoId]?.apply {
                        this.region = region.name
                    }
                }
            }
            country.photos.forEach { countryPhotoId ->
                photoMap[countryPhotoId]?.apply {
                    this.country = country.name
                }
            }
        }


        return CountryRegionCity(
            country = country,
            region = region,
            city = city
        )
    }

    fun parse(
        context: Context?,
        username: String
    ): List<Photo>? {
        if (photosForUsernameCache[username] != null) {
            return photosForUsernameCache[username]
        }
        val jsonObject = parseFileForJson(context!!, username)
        var photos: List<Photo> = parsePhotoMap(jsonObject.getJSONObject("photosMap"))
        val photoMap = photos.map { photo -> photo.id to photo }.toMap()

        val countriesList = parseCountries(
            jsonObject
                .getJSONObject("world")
                .getJSONObject("places")
        )

        val countryRegionCity = getCountryRegionCity(photoMap, countriesList)

        photosForUsernameCache[username] = ArrayList(photoMap.values)
        return photos
    }

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

    private fun parsePhotos(jsonObject: JSONObject): List<String> {
        return if (!jsonObject.isNull("photos")) {
            gson.fromJson(
                jsonObject.getJSONArray(
                    "photos"
                ).toString(), object : TypeToken<Collection<String?>?>() {}.type
            )
        } else ArrayList()
    }

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

    private fun parsePhotoMap(photoMap: JSONObject): List<Photo> {
        val photos: MutableList<Photo> = mutableListOf()
        photoMap.keys().forEach { id ->
            val photo = gson.fromJson(
                photoMap.getJSONObject(id.toString()).toString(),
                Photo::class.java
            )
            photo.id = id.toString()
            photos.add(photo)
        }
        return photos
    }
}