package com.example.contextmenu_11

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var textET: EditText
    private lateinit var randomButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textET = findViewById(R.id.textET)
        randomButton = findViewById(R.id.menu_button)

        
        registerForContextMenu(textET)


        randomButton.setOnClickListener {
            val randomNumber = Random.nextInt(1, 51)
            textET.setText(randomNumber.toString())
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.add(0, 1, 0, "Цветовое качество")
        menu?.add(0, 2, 0, "Выход из приложения")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            1 -> {
                val gradeString = textET.text.toString()
                val grade = gradeString.toIntOrNull()
                if (grade != null) {
                    when (grade) {
                        1 -> textET.setBackgroundColor(Color.parseColor("#FFCA86"))
                        2 -> textET.setBackgroundColor(Color.YELLOW)
                        3 -> textET.setBackgroundColor(Color.GREEN)
                        4 -> textET.setBackgroundColor(Color.BLUE)
                        5 -> textET.setBackgroundColor(Color.RED)
                        in 1..10 -> textET.setBackgroundColor(Color.RED)
                        in 11..20 -> textET.setBackgroundColor(Color.parseColor("#FFCA86"))
                        in 21..30 -> textET.setBackgroundColor(Color.YELLOW)
                        in 31..40 -> textET.setBackgroundColor(Color.GREEN)
                        in 41..50 -> textET.setBackgroundColor(Color.BLUE)
                        else -> Toast.makeText(this, "Введите оценку от 1 до 50", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Неверный ввод. Пожалуйста, введите число.", Toast.LENGTH_SHORT).show()
                }
                true
            }
            2 -> {
                finish()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}