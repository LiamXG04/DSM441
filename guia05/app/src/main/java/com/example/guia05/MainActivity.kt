package com.example.guia05

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val names = resources.getStringArray(R.array.dish_names)
        val descriptions =
            resources.getStringArray(R.array.dish_descriptions)
        val prices = resources.getStringArray(R.array.dish_prices)
        val images = resources.getStringArray(R.array.dish_images)
        val size = listOf(names.size, descriptions.size, prices.size,
            images.size).minOrNull() ?: 0
        val dishes = mutableListOf<Dish>()
        for (i in 0 until size) {
            dishes.add(Dish(names[i], descriptions[i], prices[i],
                images[i]))
        }
        val adapter = DishAdapter(dishes) { dish ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("name", dish.name)
                putExtra("description", dish.description)
                putExtra("price", dish.price)
                putExtra("imageName", dish.imageName)
            }
            startActivity(intent)
        }

    }
}
