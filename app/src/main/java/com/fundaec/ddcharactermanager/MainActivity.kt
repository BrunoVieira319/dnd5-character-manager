package com.fundaec.ddcharactermanager

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.fundaec.ddcharactermanager.activities.NewCharacterActivity
import com.fundaec.ddcharactermanager.adapters.CharactersAdapter
import com.fundaec.ddcharactermanager.models.CharacterMainActivityDto
import com.fundaec.ddcharactermanager.network.GsonRequest
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var queue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        queue = Volley.newRequestQueue(baseContext)

        fab.setOnClickListener {
            startActivity(Intent(baseContext, NewCharacterActivity::class.java))
        }

        fetchCharacters()
    }

    private fun fetchCharacters() {
        val request = GsonRequest(
            "https://character-manager.herokuapp.com/v1/characters",
            Array<CharacterMainActivityDto>::class.java,
            Response.Listener { characters ->
                val adapter = CharactersAdapter(baseContext, characters.toList())

                recyclerCharacters.adapter = adapter
                recyclerCharacters.layoutManager = LinearLayoutManager(baseContext, RecyclerView.VERTICAL, false)
            },
            Response.ErrorListener {
                Toast.makeText(baseContext, it.message, Toast.LENGTH_LONG).show()
            }
        )
        queue?.add(request)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
