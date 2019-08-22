package ru.vvdev.awesome

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import ru.vvdev.awesomebutton.AwesomeButton

class MainActivity : AppCompatActivity() {

    internal lateinit var button: AwesomeButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.awesome)
        button.setBackground("#E5E500")
    }
}
