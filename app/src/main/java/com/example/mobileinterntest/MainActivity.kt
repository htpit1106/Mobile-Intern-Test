package com.example.mobileinterntest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileinterntest.Adapter.ResultAdapter
import com.example.mobileinterntest.ViewModel.MainViewModel
import com.example.mobileinterntest.ViewModel.SearchViewModel
import com.example.mobileinterntest.data.model.Place
import com.example.mobileinterntest.data.repository.SearchRepository
import com.example.mobileinterntest.ui.theme.MobileInternTestTheme

// implement onclickitem
class MainActivity : ComponentActivity() , ResultAdapter.setOnClickListener {

    private lateinit var edtSearch: EditText
    private lateinit var rvResults: RecyclerView
    private lateinit var adapter: ResultAdapter
    private val viewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // set mainlayout
        setContentView(R.layout.mainlayout)

        edtSearch = findViewById(R.id.editTextText)
        rvResults = findViewById(R.id.rvResults)
        adapter = ResultAdapter()
        rvResults.adapter = adapter
        adapter.setOnClickListener(this)
        // set layout
        val layoutManager = LinearLayoutManager(this)
        rvResults.layoutManager = layoutManager

        edtSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setQuery(s?.toString() ?: "")
                if (s.isNullOrBlank()) {
                    adapter.updateData(emptyList(), "")
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        viewModel.results.observe(this) { results ->
            adapter.updateData(results, edtSearch.text.toString())
        }




    }

    override fun onItemClick(place: Place) {
    }

    override fun onRedirectClick(place: Place) {

        try{
            val gmmIntentUri = Uri.parse("google.navigation:q=${place.lat},${place.lon}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps") // mở bằng Google Maps
            startActivity(mapIntent)
        } catch (e: Exception){
            e.printStackTrace()

        }
    }
}

