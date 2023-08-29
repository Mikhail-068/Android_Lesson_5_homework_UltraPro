package com.example.android_lesson_5

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup 	// переменная для работы со списком
    private lateinit var submitButton: Button 		// переменная для работы с кнопкой
    private lateinit var resultTextView: TextView 	// переменная для работы с текстовым полем

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById(R.id.radioGroup) 		// находим список предметов
        submitButton = findViewById(R.id.getPrice) 		// находим кнопку
        resultTextView = findViewById(R.id.resultTextView) 	// находим текстовое поле


        // добавляем обработчик нажатия на кнопку
        submitButton.setOnClickListener {


            //----------------------------------------------------------------------
            // проверяем наличие подключения к сети, если его нет, то выводим надпись в текстовое поле
            if (! NetworkManager.isNetworkAvailable(this)) {
                resultTextView.text =
                    "Нет доступа к интернету! \n\n проверьте подключение"
                return@setOnClickListener
            }

            //----------------------------------------------------------------------
            // работаем со спиcком предметов

            // записываем ID выбранного предмета в переменную
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId

            // на всякий случай проверяем, что мы получили корректный ID
            if (selectedRadioButtonId != -1) {

                // раскомментируйте три строки ниже, чтобы создать ошибку при выборе ноутбука
                if (selectedRadioButtonId == R.id.radioButtonLaptop){
                    throw Error("error! cannot get a price for a laptop")
                }
                //https://kotlinlang.org/docs/exceptions.html

                // находим пункт в списке, который мы выбрали
                val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
                // считываем в переменную название выбранного пункта списка
                val selectedText = selectedRadioButton.text

                Log.i("myTag", "$selectedText click")
                // выводим стоимость в текстовое поле
                resultTextView.text = "стоимость товара\n ($selectedText): \n${getPrice()}"
            }
        }
    }
}



// нужно для проверки подключения к интернету
object NetworkManager {
    // функция, которая вернет false, если не будет подключения к интернету
    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}



// функция для получения стоимости товара, возвращает случайное число в заданном интервале
fun getPrice(): Long {
    return (4000..30000).random().toLong()

    // закомментируйте строку выше, а также раскомментируйте строку ниже, чтобы создать ошибку тестирования
//     return (0..3999).random().toLong()
}
