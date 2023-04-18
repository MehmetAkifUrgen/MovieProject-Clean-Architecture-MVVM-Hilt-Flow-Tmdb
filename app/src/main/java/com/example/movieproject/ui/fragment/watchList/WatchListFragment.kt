package com.example.movieproject.ui.fragment.watchList

import android.os.Bundle
import android.util.Log
import com.example.movieproject.R
import com.example.movieproject.base.BaseFragment
import com.example.movieproject.databinding.FragmentProfileBinding
import com.example.movieproject.databinding.FragmentWatchlistBinding
import com.example.movieproject.utils.loadImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WatchListFragment : BaseFragment<FragmentWatchlistBinding>() {
    private lateinit var auth: FirebaseAuth
    override fun getLayoutId(): Int = R.layout.fragment_watchlist
    val db = Firebase.firestore


    override fun prepareView(savedInstanceState: Bundle?) {

        getWatchList()
    }

    private fun getWatchList(){
        auth=Firebase.auth

        CoroutineScope(Dispatchers.IO).launch {
            auth.currentUser?.let {
                db.collection(it.uid)
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            Log.d("TAG", "${document.id} => ${document.data}")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.w("TAG", "Error getting documents.", exception)
                    }
            }
        }
    }


}