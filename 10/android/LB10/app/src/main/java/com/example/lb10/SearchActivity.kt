package com.example.lb10

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val etBrand = findViewById<AutoCompleteTextView>(R.id.etBrand)
        val etModel = findViewById<AutoCompleteTextView>(R.id.etModel)
        val btnMatches = findViewById<Button>(R.id.btnMatches)
        val tvMatchCount = findViewById<TextView>(R.id.tvMatchCount)

        // ===== DATA =====
        val brands = MainActivity.cars.map { it.brand }.distinct()
        val models = MainActivity.cars.map { it.model }.distinct()

        val years = (2000..2025 step 5).map { it.toString() }
        val costs = (0..200000 step 20000).map { it.toString() }

        // ===== AUTOCOMPLETE =====
        etBrand.setAdapter(
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, brands)
        )

        etModel.setAdapter(
            ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, models)
        )

        // ===== SPINNERS =====
        val yearAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, years)
        val costAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, costs)

        val spYearFrom = findViewById<Spinner>(R.id.spYearFrom)
        val spYearTo = findViewById<Spinner>(R.id.spYearTo)
        val spCostFrom = findViewById<Spinner>(R.id.spCostFrom)
        val spCostTo = findViewById<Spinner>(R.id.spCostTo)

        spYearFrom.adapter = yearAdapter
        spYearTo.adapter = yearAdapter
        spCostFrom.adapter = costAdapter
        spCostTo.adapter = costAdapter

        // ===== MATCH COUNT =====
        fun updateMatchCount() {
            tvMatchCount.text = "Matches: ${getFilteredCars().size}"
        }

        // обновляем при выборе spinner
        val spinnerListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                updateMatchCount()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        spYearFrom.onItemSelectedListener = spinnerListener
        spYearTo.onItemSelectedListener = spinnerListener
        spCostFrom.onItemSelectedListener = spinnerListener
        spCostTo.onItemSelectedListener = spinnerListener

        btnMatches.setOnClickListener {
            MainActivity.filteredCars = getFilteredCars().toMutableList()
            finish()
        }

        updateMatchCount()
    }

    private fun getFilteredCars(): List<Car> {

        val brand = findViewById<AutoCompleteTextView>(R.id.etBrand).text.toString()
        val model = findViewById<AutoCompleteTextView>(R.id.etModel).text.toString()

        val yearFrom = findViewById<Spinner>(R.id.spYearFrom).selectedItem.toString().toInt()
        val yearTo = findViewById<Spinner>(R.id.spYearTo).selectedItem.toString().toInt()

        val costFrom = findViewById<Spinner>(R.id.spCostFrom).selectedItem.toString().toInt()
        val costTo = findViewById<Spinner>(R.id.spCostTo).selectedItem.toString().toInt()

        return MainActivity.cars.filter {
            (brand.isEmpty() || it.brand.equals(brand, true)) &&
                    (model.isEmpty() || it.model.equals(model, true)) &&
                    it.year in yearFrom..yearTo &&
                    it.cost in costFrom..costTo
        }
    }
}