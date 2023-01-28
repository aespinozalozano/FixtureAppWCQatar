
package com.ademirespinoza.fixturewcqatar2022

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ademirespinoza.fixturewcqatar2022.adapter.FixtureAdapter
import com.ademirespinoza.fixturewcqatar2022.manager.RequestManager
import com.ademirespinoza.fixturewcqatar2022.models.FixtureResponse



class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var dialog: ProgressDialog
    lateinit var manager: RequestManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_fixture)

        dialog = ProgressDialog(this)
        dialog.setMessage("Cargando...")

        manager = RequestManager(this)
        manager.getFixture(listener)
        dialog.show()
    }

    private val listener = object : ResponseListener {
        override fun didFetch(response: FixtureResponse?, message: String?) {
            dialog.dismiss()
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            val adapter = response!!.data?.let { FixtureAdapter(this@MainActivity, it) }
            recyclerView.adapter = adapter
        }

        override fun didError(message: String?) {
            dialog.dismiss()
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
        }
    }




}