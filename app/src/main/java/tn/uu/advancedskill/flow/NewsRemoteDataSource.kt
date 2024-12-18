package tn.uu.advancedskill.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class NewsRemoteDataSource(
    private val refreshIntervalMs: Long = 5000
) {
    val lastNews: Flow<List<String>> = flow {
        var index = 0
        val indexList = ArrayList<String>()
        while (true) {
            val latestNes = "index: ${index}"
            index += 1
//            val latestNes = newsApi.fetchLatestNews()
            indexList.add("$index")
            emit(indexList)
            delay(refreshIntervalMs)
        }
    }
}



interface NewsApi {
    suspend fun fetchLatestNews(): List<String>
}