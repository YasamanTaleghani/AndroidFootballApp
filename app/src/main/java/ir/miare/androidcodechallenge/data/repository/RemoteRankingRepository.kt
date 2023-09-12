package ir.miare.androidcodechallenge.data.repository

import android.util.Log
import ir.logicbase.mockfit.Mock
import ir.miare.androidcodechallenge.data.model.FakeData
import ir.miare.androidcodechallenge.data.model.Player
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

class RemoteRankingRepository @Inject constructor(private val retrofit: Retrofit) {

    interface Api {
        @Mock("data.json")
        @GET("list")
        fun getData(): Call<List<FakeData>>
    }



    fun getRankingSort(sortingMode: Int, fakeData: ArrayList<FakeData>): ArrayList<FakeData> {
        val tempSort = arrayListOf<FakeData>()
        tempSort.addAll(fakeData)
        Log.d("TAG", "getRankingSort: ${tempSort.size} ${fakeData}")
        return when (sortingMode) {
            -1 -> tempSort
            0 -> ArrayList(tempSort.sortedWith(compareBy { it.league.rank }) )
            1 -> getSortByMostGoals(fakeData)
            2 -> getSortedByMostAverageGoals(fakeData)
            else -> {fakeData}
        }
    }

    private fun getSortedByMostAverageGoals(fakeData: ArrayList<FakeData>): ArrayList<FakeData> {
        val meanGoals: ArrayList<Double> = arrayListOf()
        for (item in fakeData) {
            var totalGoals = 0
            for (player in item.players) {
                totalGoals += player.totalGoal
            }
            meanGoals.add(totalGoals.toDouble()/(item.league.totalMatches))
        }
        val temp: ArrayList<Pair<FakeData, Double>> = arrayListOf()
        for (item in fakeData) {
            temp.add(Pair(item, meanGoals[fakeData.indexOf(item)]))
        }
        temp.sortByDescending { it.second }
        val result: ArrayList<FakeData> = arrayListOf()
        for (item in 0..2) {
            result.add(temp[item].first)
        }
        return result
    }

    private fun getSortByMostGoals(fakeData: ArrayList<FakeData>): ArrayList<FakeData> {
        val result: ArrayList<FakeData> = arrayListOf()
        val temp= arrayListOf<Player>()
        for (item in fakeData) {
            temp.addAll(item.players)
        }
        temp.sortByDescending { it.totalGoal }
        for (item in temp) {
            val fakeData = FakeData()
            fakeData.players.add(item)
            result.add(fakeData)
        }
        return result
    }
}