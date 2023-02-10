package com.appointmate.salon

import com.google.android.gms.maps.model.LatLng

data class Salon(
    val name: String,
    val latLng: LatLng,
)