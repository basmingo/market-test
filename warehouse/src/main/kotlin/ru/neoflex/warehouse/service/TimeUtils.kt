package ru.neoflex.warehouse.service

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TimeUtils {

    fun parseH2Time(time: String): LocalDateTime {
        var mutableTime = time
        while (mutableTime.length != 26) {
            mutableTime += 0
        }

        return LocalDateTime.from(
            Constants.h2DateTimeFormatter.parse(mutableTime)
        )
    }
}