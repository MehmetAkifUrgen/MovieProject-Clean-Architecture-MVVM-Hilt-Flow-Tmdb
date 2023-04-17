@file:Suppress("DEPRECATION")

package com.example.movieproject.ui.fragment.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieproject.R
import com.example.movieproject.databinding.FragmentLoginBinding
import com.google.android.flexbox.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    private val RC_SIGN_IN = 9001

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auth = Firebase.auth
        Log.d("data", auth.currentUser?.displayName.toString())
        if (auth.currentUser?.uid != null) {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        } else {
        }
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebase()

        onClick()
    }

    private fun onClick() {
        binding.googleSignInButton.setOnClickListener {
            signInWithGoogle()
        }
    }

    private fun firebase() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        // Firebase Authentication nesnesini initialize et
        auth = Firebase.auth
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Google hesabından dönen sonucu işle
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google hesabı doğrulandı, Firebase Authentication'a aktar
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())

            } catch (e: ApiException) {
                // Google hesabı doğrulanamadı
                Log.w("error", "Google sign in failed", e)
                Toast.makeText(requireContext(), "Google sign in failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Giriş başarılı
                    val user = auth.currentUser
                    // TODO: Uygulama içinde kullanıcı verilerini kaydet
                } else {
                    // Giriş hatası
                    Log.w("hata", "signInWithCredential:failure", task.exception)
                    // TODO: Kullanıcıya bir hata mesajı göster
                }
            }
    }

}
