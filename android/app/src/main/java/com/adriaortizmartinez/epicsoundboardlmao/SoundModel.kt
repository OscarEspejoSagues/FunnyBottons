package com.adriaortizmartinez.epicsoundboardlmao

data class SoundModel(
    var id: Int?,
    var title: String?,
    var file: String?
)

data class SoundList(
    var sounds: ArrayList<SoundModel>?
)