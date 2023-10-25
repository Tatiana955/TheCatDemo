package com.example.thecatdemo.data.source

import androidx.room.*

@Entity(tableName = "data")
data class DataSource(
    @Ignore val adaptability: Int?,
    @Ignore val affection_level: Int?,
    @Ignore val alt_names: String?,
    @Ignore val cfa_url: String?,
    @Ignore val child_friendly: Int?,
    @Ignore val country_cod: String?,
    @Ignore val country_codes: String?,
    @Ignore val description: String?,
    @Ignore val dog_friendly: Int?,
    @Ignore val energy_level: Int?,
    @ColumnInfo(name = "dataId")
    var id: String?,
    @Embedded
    var image: Image?, // Warning: Api no longer contains images
    @Ignore val indoor: Int?,
    @Ignore val intelligence: Int?,
    @Ignore val lap: Int?,
    @Ignore val life_span: String?,
    @ColumnInfo(name = "name")
    var name: String?,
    @Ignore val natural: Int?,
    @ColumnInfo(name = "origin")
    var origin: String?,
    @Ignore val rare: Int?,
    @Ignore val reference_image_id: String?,
    @Ignore val rex: Int?,
    @Ignore val shedding_level: Int?,
    @Ignore val short_legs: Int?,
    @Ignore val social_needs: Int?,
    @Ignore val stranger_friendly: Int?,
    @Ignore val suppress_tail: Int?,
    @Ignore val temperament: String?,
    @Ignore val vcahospitals_url: String?,
    @Ignore val vetstreet_url: String?,
    @Ignore val vocalisation: Int?,
    @Ignore val weight: Weight?,
    @Ignore val wikipedia_url: String?,
    @PrimaryKey(autoGenerate = true)
    var primaryKey: Int?
) {
    constructor() : this(
        null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null,
        null, null, null
    )
}

data class Image(
    var height: Int?,
    var id: String?,
    var url: String?,
    var width: Int?
)

data class Weight(
    val imperial: String?,
    val metric: String?
)

val fakeDataSource = DataSource(
    1, 1, "", "", 1, "", "",
    "Native to the Greek islands known as the Cyclades in the Aegean Sea, these are natural cats, meaning they developed without humans getting involved in their breeding.",
    1, 1, "", image = Image(100, "o1o789", "☕", 100),
    1, 1, 1, "", "Abyssinian", 1, "Egypt", 1, "",
    1, 1, 1, 1, 1, 1,
    "Affectionate, Social, Intelligent, Playful, Active", "",
    "", 1, weight = Weight("", ""), "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
    1
)