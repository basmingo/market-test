package ru.neoflex.warehouse.service.utils

import org.springframework.stereotype.Service
import ru.neoflex.warehouse.service.Constants
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