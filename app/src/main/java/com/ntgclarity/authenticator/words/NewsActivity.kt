package com.ntgclarity.authenticator.words
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.example.Articles
import com.example.example.News
import com.maximeroussy.invitrode.WordGenerator
import com.ntgclarity.authenticator.BuildConfig
import com.ntgclarity.authenticator.Network.retrofit
import com.ntgclarity.authenticator.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsActivity : AppCompatActivity(),Callback<News?> ,NewsAdapter.ItemClickListener{
    var adapter:NewsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_words)
        val layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        val words = randomNews()
        adapter = NewsAdapter(words,this)

        val rvWords: RecyclerView = findViewById(R.id.rv_words)
        rvWords.layoutManager = layoutManager
        rvWords.adapter = adapter
        if(BuildConfig.FLAVOR == "free")
        {
            requestNews("sports")
        }
        else
            requestNews("science")

    }

    private fun randomNews(): Array<Articles> {
        val generator = WordGenerator()

        return Array(100) {
            Articles(
                null,
                generator.newWord(15),
                generator.newWord(15),
                generator.newWord(15),
                "https://picsum.photos/200/100",
                generator.newWord(15),
                generator.newWord(15),
                generator.newWord(15)
            )
        }
    }
    private fun requestNews(input:String){

            retrofit.news(input,"ddfae166ffff495489147ec8b066775d")?.enqueue(this)
    }

    // New data coming
    override fun onResponse(call: Call<News?>, response: Response<News?>) {
        adapter?.articles = response.body()?.articles
        adapter?.notifyDataSetChanged()
    }

    override fun onFailure(call: Call<News?>, t: Throwable) {
        Toast.makeText(this,"Error !!",Toast.LENGTH_LONG).show()
    }

    override fun itemClick(position: Int) {
        val clicked = adapter?.articles?.get(position)
        if (clicked != null) {
            startActivity(Intent(Intent.ACTION_VIEW).setData(clicked?.url?.toUri()))
        }
    }

}