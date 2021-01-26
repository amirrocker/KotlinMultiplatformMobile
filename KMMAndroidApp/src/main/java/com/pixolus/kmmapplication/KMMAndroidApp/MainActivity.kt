package com.pixolus.kmmapplication.KMMAndroidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import com.pixolus.kmmapplication.shared.Greeting
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pixolus.kmmapplication.KMMAndroidApp.launch.LaunchAdapter
import com.pixolus.kmmapplication.shared.Repository
import com.pixolus.kmmapplication.shared.cache.DatabaseDriverFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // set coroutines default scope
    private val mainScope = MainScope()

    private lateinit var launchRecyclerView:RecyclerView
    private lateinit var progressBarView:FrameLayout
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val launchAdapter: LaunchAdapter by lazy {
        LaunchAdapter(listOf())
    }

    private val repository = Repository(DatabaseDriverFactory(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = "Launches"
        setContentView(R.layout.activity_main)

        launchRecyclerView = findViewById(R.id.launchRv)
        progressBarView = findViewById(R.id.progressBar)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        launchRecyclerView.adapter = launchAdapter
        launchRecyclerView.layoutManager = LinearLayoutManager(this)

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            displayLaunches(true)
        }


//        val tv: TextView = findViewById(R.id.text_view)
//        tv.text = greet()
    }

    fun displayLaunches(forceSync:Boolean) {
        progressBarView.visibility = View.VISIBLE

        mainScope.launch {
            kotlin.runCatching {
                repository.getLaunches(forceSync)
            }.onSuccess {
                launchAdapter.launches = it
                launchAdapter.notifyDataSetChanged()
            }.onFailure {
                Toast
                    .makeText(this@MainActivity, it.localizedMessage, Toast.LENGTH_SHORT)
                    .show()
            }
            progressBarView.visibility = View.INVISIBLE
        }

    }

    private fun greet(): String {
        return Greeting().greeting()
    }

    override fun onResume() {
        super.onResume()
        displayLaunches(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }
}
