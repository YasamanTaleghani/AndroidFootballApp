package ir.miare.androidcodechallenge.ui

import androidx.lifecycle.ViewModel
import ir.miare.androidcodechallenge.data.model.FakeData
import ir.miare.androidcodechallenge.data.repository.RemoteRankingRepository
import javax.inject.Inject

class RankingViewModel @Inject constructor(
    private val remoteRankingRepository: RemoteRankingRepository): ViewModel() {
    var fakeData: ArrayList<FakeData> = arrayListOf()

    fun getRankingSortedList(sortingMode: Int): ArrayList<FakeData> {
        return remoteRankingRepository.getRankingSort(sortingMode, fakeData)
    }
}