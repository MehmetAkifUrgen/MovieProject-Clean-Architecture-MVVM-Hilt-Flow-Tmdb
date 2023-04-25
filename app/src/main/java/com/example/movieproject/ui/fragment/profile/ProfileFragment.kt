package com.example.movieproject.ui.fragment.profile

import android.os.Bundle
import com.example.movieproject.R
import com.example.movieproject.base.BaseFragment
import com.example.movieproject.databinding.FragmentProfileBinding
import com.example.movieproject.utils.loadImage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    private lateinit var auth: FirebaseAuth
    override fun getLayoutId(): Int = R.layout.fragment_profile

    override fun prepareView(savedInstanceState: Bundle?) {
        auth=Firebase.auth
       CoroutineScope(Dispatchers.Main).launch{
           binding.profileImage.loadImage(auth.currentUser?.photoUrl.toString())
       }

    }


}