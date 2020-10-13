package com.myanmarit.votingapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.myanmarit.votingapp.R
import com.myanmarit.votingapp.api.VotingClient
import com.myanmarit.votingapp.model.KingQueenItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import retrofit2.Call
import retrofit2.Response

class QueenDetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var item : KingQueenItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_queen_detail, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val args = arguments?.let { DetailFragmentArgs.fromBundle(it) }
        item = args.item

        detailName.text = item.name
        detailClassName.text = item.className
        Picasso.get()
            .load(item.image)
            .placeholder(R.drawable.ic_launcher_background)
            .into(voteImage)

        btnSubmit.setOnClickListener {
            var apiClient = VotingClient()
            var apiCall = apiClient.voteKing(item.id!!,edtCode.text.toString())
            apiCall.enqueue(object : retrofit2.Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {

                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    txtMessage.text = response.body()
                }
            })
        }
    }

}