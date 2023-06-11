package com.example.consultatramite;
import kotlinx.android.parcel.Parcelize;

@Parcelize
class TimeLineModel(
        var message: String,
        var date: String,
        var status: OrderStatus
) : Parcelable
