package com.example.lb10

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    companion object {

        val cars = mutableListOf(
            Car("BMW", "X5", 2011, "Комфортний кросовер", 100000, R.drawable.car1),
            Car("Audi", "A6", 2015, "Німецький седан", 70000, R.drawable.car2),
            Car("Toyota", "Camry", 2018, "Надійний автомобіль", 50000, R.drawable.car3),
            Car("Mercedes", "E200", 2020, "Преміум клас", 120000, R.drawable.car4),
            Car("Honda", "Civic", 2008, "Економний автомобіль", 30000, R.drawable.car5)
        )

        var filteredCars = cars
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CarAdapter(filteredCars)

        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
            .setOnMenuItemClickListener {

                if (it.itemId == R.id.menu_search) {

                    startActivity(
                        Intent(this, SearchActivity::class.java)
                    )

                    true
                } else {
                    false
                }
            }
    }

    override fun onResume() {
        super.onResume()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = CarAdapter(filteredCars)
    }
}