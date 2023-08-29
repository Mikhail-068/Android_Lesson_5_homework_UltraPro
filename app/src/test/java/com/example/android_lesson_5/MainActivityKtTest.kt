package com.example.android_lesson_5

import org.junit.Test
import org.junit.jupiter.api.Assertions

class MainActivityKtTest {

    @Test
    fun `return true if price is 4000 or more`() {
        val actualPrice = getPrice()

        Assertions.assertTrue(actualPrice >= 4000)
    }
    @Test
    fun `return true if price is in interval`(){
        val priceInterval = 4000..65000 // интервал, внутри которого должна быть стоимость
        val actualValue = getPrice() // получаем стоимость

        Assertions.assertTrue(priceInterval.contains(actualValue)) // проверяем
    }

    @Test
    fun `return false if price is not in interval`(){
        val priceInterval = -1000..0 // интервал, внутри которого НЕ должна быть стоимость
        val actualValue = getPrice() // получаем стоимость

        Assertions.assertFalse(actualValue in priceInterval) // проверяем
    }
}
