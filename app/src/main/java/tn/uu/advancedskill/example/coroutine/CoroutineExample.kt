package tn.uu.advancedskill.example.coroutine


import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object CoroutineExample {

    fun runExample() {
        CoroutineScope(Dispatchers.IO).launch {
            val price = getPrice()
//            Log.e("xxx11", "price: $price")
            val job1 = async {
                delay(1500)
//                Log.e("xxx11", "this is job1")
                1
            }
            val job2 = async {
                delay(2000)
//                Log.e("xxx11", "this is job2")
                2
            }
            val job3 = async {
                delay(500)
//                Log.e("xxx11", "this is job3")
                3
            }

            val c1 = job1.await()
            val c2 = job2.await()
            val c3 = job3.await()
//            Log.e("xxx11", "Oh year, it come here job1: $c1 job2: $c2 job3: $c3")

        }
    }

    suspend fun getPrice(): Double {
        return withContext(Dispatchers.IO) {
            delay(2000)
            35.0
        }
    }

}