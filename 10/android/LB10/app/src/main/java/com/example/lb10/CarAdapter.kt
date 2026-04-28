package com.example.lb10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CarAdapter(
    private val cars: List<Car>
) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    class CarViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val image: ImageView = view.findViewById(R.id.ivCar)
        val title: TextView = view.findViewById(R.id.tvTitle)
        val desc: TextView = view.findViewById(R.id.tvDescription)
        val cost: TextView = view.findViewById(R.id.tvCost)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_car, parent, false)

        return CarViewHolder(view)
    }

    override fun getItemCount() = cars.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {

        val car = cars[position]

        holder.image.setImageResource(car.imageRes)

        holder.title.text =
            "${car.brand}, ${car.model} (${car.year})"

        holder.desc.text = car.description

        holder.cost.text = "${car.cost}"
    }
}