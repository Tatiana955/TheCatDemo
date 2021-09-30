package com.example.thecatdemo.data.source

data class DataSource(
        val adaptability: Int,
        val affection_level: Int,
        val alt_names: String,
        val cfa_url: String,
        val child_friendly: Int,
        val country_cod: String,
        val country_codes: String,
        val description: String,
        val dog_friendly: Int,
        val energy_level: Int,
        val id: String,
        val image: Image,
        val indoor: Int,
        val intelligence: Int,
        val lap: Int,
        val life_span: String,
        val name: String,
        val natural: Int,
        val origin: String,
        val rare: Int,
        val reference_image_id: String,
        val rex: Int,
        val shedding_level: Int,
        val short_legs: Int,
        val social_needs: Int,
        val stranger_friendly: Int,
        val suppress_tail: Int,
        val temperament: String,
        val vcahospitals_url: String,
        val vetstreet_url: String,
        val vocalisation: Int,
        val weight: Weight,
        val wikipedia_url: String
)

data class Image(
        val height: Int,
        val id: String,
        val url: String,
        val width: Int
)

data class Weight(
        val imperial: String,
        val metric: String
)

val fakeDataSource = DataSource(
        1, 1, "", "", 1, "", "",
        "Native to the Greek islands known as the Cyclades in the Aegean Sea, these are natural cats, meaning they developed without humans getting involved in their breeding.",
        1, 1, "", image = Image(100, "o1o789", "☕",
        100), 1, 1, 1, "", "Abyssinian", 1, "Egypt", 1, "",
        1, 1, 1, 1, 1, 1,
        "Affectionate, Social, Intelligent, Playful, Active", "",
        "", 1, weight = Weight("", ""), "https://en.wikipedia.org/wiki/Abyssinian_(cat)"
)