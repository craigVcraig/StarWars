package com.cvitirinyu.codingchallenge.starwars.utilities

import com.cvitirinyu.codingchallenge.starwars.data.database.entities.StarWarsMission
import com.cvitirinyu.codingchallenge.starwars.data.network.model.StarWarsMissionResponse
import java.text.SimpleDateFormat
import java.util.*

val GMT_TIME_ZONE: TimeZone = TimeZone.getTimeZone("GMT")

fun StarWarsMissionResponse.toDbModel() : StarWarsMission {
    return StarWarsMission(
        id = this.id,
        title = title,
        description = description,
        date = date.toRequiredDateFormat(),
        image = image,
        phone = phone,
        locationLineOne = locationLineOne,
        locationLineTwo = locationLineTwo,
    )
}

fun String?.toRequiredDateFormat(): String {
    return this?.let {
        val parseDate = createSimpleDataFormat(
            format = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            timeZone = GMT_TIME_ZONE
        ).parse(it) ?: ""

        val outFormat = createSimpleDataFormat( "MMM d, yyyy 'at' h:mma", TimeZone.getDefault())

        outFormat
            .format(parseDate)
            .replace("AM", "am")
            .replace("PM", "pm")

    } ?: ""
}

private fun createSimpleDataFormat(format: String, timeZone: TimeZone): SimpleDateFormat {
    val sdf = SimpleDateFormat(format, Locale.ENGLISH)
    sdf.timeZone = timeZone
    return sdf
}