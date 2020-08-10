package com.sony.fetchdataandstoreinsqlite

import android.icu.text.IDNA
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var dbHandler: DbHandlerInfo
    }

    var infoList = ArrayList<InfoData>()
    lateinit var adapter: RecyclerView.Adapter<*>
    lateinit var rv: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHandler = DbHandlerInfo(this, null, null, 1)
        refreshLayout.setOnRefreshListener {
            refresh()
        }
        refresh()

    }

    private fun refresh() {

        refreshLayout.isRefreshing = true
        InfoAPI().getInfo().enqueue(object : Callback<List<InfoData>> {
            override fun onFailure(call: Call<List<InfoData>>, t: Throwable) {
                refreshLayout.isRefreshing = false

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<InfoData>>,
                response: Response<List<InfoData>>
            ) {
                infoList.clear()
                refreshLayout.isRefreshing = false

                val info = response.body()

                Log.e("kdjndwd34wer", info.toString())

                if (info != null) {
                    for (infos in info) {

                        infos.name
                        infos.username
                        infos.email
//
                        Log.e("name", infos.name)
                        Log.e("username", infos.username)
                        Log.e("email", infos.email)

                         dbHandler.addInfos(applicationContext, infos)

                    }

                }



            }


        })
        getInfo()
    }

    fun getInfo() {

        infoList = dbHandler.getInfos(this)
        adapter = InfoAdapter(this, infoList)
        rv = findViewById(R.id.recyclerViewInfo)

        rv.layoutManager =
            LinearLayoutManager(this, LinearLayout.VERTICAL, false) as RecyclerView.LayoutManager

        rv.adapter = adapter
    }

    override fun onResume() {
        getInfo()
        super.onResume()
    }
}
