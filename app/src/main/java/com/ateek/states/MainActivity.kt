package com.ateek.states

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.constraintlayout.widget.ConstraintLayout

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val constraintLayout: ConstraintLayout by lazy { findViewById<ConstraintLayout>(R.id.constraint_state) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        constraintLayout.loadLayoutDescription(R.xml.constraint_layout_states)

        getData()

        fab.setOnClickListener {
            constraintLayout.setState(R.id.error, 0, 0)
        }
    }

    private fun getData() {
        constraintLayout.setState(R.id.loading, 0, 0)

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            constraintLayout.setState(R.id.result, 0, 0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_reload -> {
                getData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}