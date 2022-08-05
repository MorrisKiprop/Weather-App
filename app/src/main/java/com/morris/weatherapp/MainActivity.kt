package com.morris.weatherapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val queue=Volley.newRequestQueue(this)

        val weatherUrl = "https://api.weatherapi.com/v1/current.json?key=f269d6ac5ca5477896375924220208&q=Mombasa"
        val weatherRequest = JsonObjectRequest(Request.Method.GET, weatherUrl, null,
            {
                    mainJsonObject ->
                val locationObject = mainJsonObject.getJSONObject("location")
                val city = locationObject.get("name")
                val country = locationObject.get("country")
                val address=("$city,$country")
                //parse json
                val currentObject = mainJsonObject.getJSONObject("current")
                val windSpeed = currentObject.get("wind_kph")
                val humid_ty = currentObject.get("humidity")
                val pressure = currentObject.get("pressure_in")
                val updatedAt:Long = mainJsonObject.getLong("last_updated")
                val updatedAtText = "Updated at:$updatedAt "
                val temperature = mainJsonObject.getJSONObject("current").getJSONObject("condition").get("tem_c")
                val condition = mainJsonObject.getJSONObject("current").getJSONObject("condition").get("text")
               // Log.d("WEATHER", "onCreate: Condition is $condition")


                findViewById<TextView>(R.id.txtaddress).text=address
                findViewById<TextView>(R.id.txtaddress).text=address
                findViewById<TextView>(R.id.txtupdated_at).text=updatedAtText
                findViewById<TextView>(R.id.txtwind).text=windSpeed.toString()
                findViewById<TextView>(R.id.txtcondition).text= condition.toString().capitalize()
                findViewById<TextView>(R.id.txtTemp).text= temperature.toString()
                findViewById<TextView>(R.id.txthumidity).text= humid_ty.toString()
                findViewById<TextView>(R.id.txtpressure).text= pressure.toString()


            },
            {
                    error ->
                //Log.e("WEATHER", "onCreate: Error while fetching weather data", error)
            })
        queue.add(weatherRequest)

    }
}