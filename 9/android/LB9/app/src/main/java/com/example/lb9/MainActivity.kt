package com.example.lb9

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etAge: EditText
    private lateinit var seekSalary: SeekBar
    private lateinit var tvSalary: TextView
    private lateinit var btnSubmit: Button
    private lateinit var tvResult: TextView

    private lateinit var rg1: RadioGroup
    private lateinit var rg2: RadioGroup
    private lateinit var rg3: RadioGroup
    private lateinit var rg4: RadioGroup
    private lateinit var rg5: RadioGroup

    private lateinit var cbExperience: CheckBox
    private lateinit var cbTeamwork: CheckBox
    private lateinit var cbTrips: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        etAge = findViewById(R.id.etAge)
        seekSalary = findViewById(R.id.seekSalary)
        tvSalary = findViewById(R.id.tvSalary)
        btnSubmit = findViewById(R.id.btnSubmit)
        tvResult = findViewById(R.id.tvResult)

        rg1 = findViewById(R.id.rg1)
        rg2 = findViewById(R.id.rg2)
        rg3 = findViewById(R.id.rg3)
        rg4 = findViewById(R.id.rg4)
        rg5 = findViewById(R.id.rg5)

        cbExperience = findViewById(R.id.cbExperience)
        cbTeamwork = findViewById(R.id.cbTeamwork)
        cbTrips = findViewById(R.id.cbTrips)

        btnSubmit.isEnabled = false

        checkFields()

        etName.addTextChangedListener(SimpleTextWatcher { checkFields() })
        etAge.addTextChangedListener(SimpleTextWatcher { checkFields() })

        rg1.setOnCheckedChangeListener { _, _ -> checkFields() }
        rg2.setOnCheckedChangeListener { _, _ -> checkFields() }
        rg3.setOnCheckedChangeListener { _, _ -> checkFields() }
        rg4.setOnCheckedChangeListener { _, _ -> checkFields() }
        rg5.setOnCheckedChangeListener { _, _ -> checkFields() }
        cbExperience.setOnCheckedChangeListener { _, _ -> checkFields() }
        cbTeamwork.setOnCheckedChangeListener { _, _ -> checkFields() }
        cbTrips.setOnCheckedChangeListener { _, _ -> checkFields() }

        seekSalary.max = 4000
        seekSalary.progress = 1000

        tvSalary.text = "3000 USD"

        seekSalary.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                val salary = progress + 1000
                tvSalary.text = "$salary USD"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        btnSubmit.setOnClickListener {

            val name = etName.text.toString().trim()
            val ageText = etAge.text.toString()

            if (name.length < 3) {
                tvResult.visibility = TextView.VISIBLE
                tvResult.text = "Кандидат не підійшов: некоректне ПІБ"
                return@setOnClickListener
            }

            val age = ageText.toInt()

            if (age !in 21..40) {
                tvResult.visibility = TextView.VISIBLE
                tvResult.text = "Кандидат не підійшов: вік повинен бути 21-40"
                return@setOnClickListener
            }

            val salary = seekSalary.progress + 1000

            if (salary !in 1000..5000) {
                tvResult.visibility = TextView.VISIBLE
                tvResult.text = "Кандидат не підійшов: зарплата не відповідає вимогам"
                return@setOnClickListener
            }

            var points = 0

            // правильные ответы
            if (rg1.checkedRadioButtonId == R.id.rb1_1) points += 2
            if (rg2.checkedRadioButtonId == R.id.rb2_1) points += 2
            if (rg3.checkedRadioButtonId == R.id.rb3_1) points += 2
            if (rg4.checkedRadioButtonId == R.id.rb4_1) points += 2
            if (rg5.checkedRadioButtonId == R.id.rb5_1) points += 2

            if (cbExperience.isChecked) points += 2
            if (cbTeamwork.isChecked) points += 1
            if (cbTrips.isChecked) points += 1

            tvResult.visibility = TextView.VISIBLE

            if (points >= 10) {
                tvResult.text =
                    "Тест пройдено!\nБалів: $points\nКонтакти HR:\nhr@company.com"
            } else {
                tvResult.text =
                    "Тест НЕ пройдено\nБалів: $points"
            }
        }
    }

    private fun checkFields() {

        val nameValid = etName.text.toString().trim().isNotEmpty()

        val ageValid = try {
            val age = etAge.text.toString().toInt()
            age in 21..40
        } catch (e: Exception) {
            false
        }

        val radioValid = rg1.checkedRadioButtonId != -1 &&
                rg2.checkedRadioButtonId != -1 &&
                rg3.checkedRadioButtonId != -1 &&
                rg4.checkedRadioButtonId != -1 &&
                rg5.checkedRadioButtonId != -1

        btnSubmit.isEnabled = nameValid && ageValid && radioValid
    }
}