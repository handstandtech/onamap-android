package net.onamap.models.models

class Country : Place() {
    var regions: List<Region>? = null
}