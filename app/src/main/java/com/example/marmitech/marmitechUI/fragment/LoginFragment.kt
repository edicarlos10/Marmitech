package com.example.marmitech.marmitechUI.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marmitech.BuildConfig
import com.example.marmitech.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    companion object{
        fun newInstance() : LoginFragment{
            val args = Bundle().apply {  }
            val fragment = LoginFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
    }

    override fun onCreateView(
        inflator: LayoutInflater,
        container: ViewGroup?,
        savedInstance: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflator, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}