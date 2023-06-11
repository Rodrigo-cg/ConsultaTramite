package com.example.consultatramite

import com.google.gson.annotations.SerializedName

data class dataTramite(
    @SerializedName("NRO DE EXPEDIENTE") val expediente: String? = null,
    @SerializedName("RUC") val ruc: String? = null,
    @SerializedName("RAZON SOCIAL") val razon_social: String? = null,
    @SerializedName("Fecha de Nacimiento") val fecha_nac: String? = null,
    @SerializedName("Dirección") val dirrec: String? = null,
    @SerializedName("DISTRITO") val distrito: String? = null,
    @SerializedName("Teléfono") val telf: String? = null,
    @SerializedName("Correo electrónico") val correo: String? = null,
    @SerializedName("Estado de expediente") val estadoexp: String? = null,
    @SerializedName("error") val errortra: String? = null

    )
