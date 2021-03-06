package com.myanmarit.votingapp.ui.queen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myanmarit.votingapp.api.VotingClient
import com.myanmarit.votingapp.model.KingQueenModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QueenViewModel : ViewModel() {
    private var queenModel: MutableLiveData<KingQueenModel> = MutableLiveData()

    fun getQueen() = queenModel

    fun loadData(){
        var apiClient = VotingClient()
        var apiCall = apiClient.getQueen()

        apiCall.enqueue(object : Callback<KingQueenModel> {
            override fun onFailure(call: Call<KingQueenModel>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<KingQueenModel>,
                response: Response<KingQueenModel>
            ) {
                queenModel.value = response.body()
                Log.e("Respond>>>",response.body().toString())
            }

        })
    }
}