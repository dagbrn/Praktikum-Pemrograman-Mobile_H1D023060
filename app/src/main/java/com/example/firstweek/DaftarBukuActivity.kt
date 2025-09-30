package com.example.firstweek

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstweek.data.model.BookDoc
import com.example.firstweek.databinding.ActivityDaftarBukuBinding
import com.example.firstweek.ui.adapter.BookAdapter
import com.example.firstweek.ui.adapter.OnBookClickListener
import com.example.firstweek.ui.fragment.BookDetailFragment
import com.example.firstweek.viewmodel.MainViewModel
class DaftarBukuActivity : AppCompatActivity(), OnBookClickListener {

    private lateinit var binding: ActivityDaftarBukuBinding

    private val viewModel: MainViewModel by viewModels()

    private val adapter = BookAdapter(emptyList(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.books.observe(this){
            adapter.setData(it)
        }

        viewModel.fetchBooks("kotlin programming")
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_daftar_buku)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }

    override fun onBookClick(book: BookDoc) {
        book.let { b ->
            BookDetailFragment(
                b.title?:"-",
                b.authorName?.joinToString(", ") ?: "Unknown Author",
                "${b.firstPublishYear}",
                b.coverId?:0
            ).show(supportFragmentManager, BookDetailFragment::class.java.simpleName)
        }
    }
}