package com.example.marmitech.marmitechUI.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.domain.marmitech.appPeople.Fiscal
import com.example.domain.marmitech.appPeople.Turma
import com.example.domain.marmitech.base.Event
import com.example.marmitech.databinding.FragmentLoginBinding
import com.example.marmitech.marmitechUI.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    companion object {
        fun newInstance(): LoginFragment {
            val args = Bundle().apply { }
            val fragment = LoginFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)

        loginViewModel.fiscal.observe(this) { fiscal -> sucessGetFiscal(fiscal) }
        loginViewModel.allTurmas.observe(this) { allTurmas -> sucessGetAllTurma(allTurmas) }
        loginViewModel.loading.observe(this) { onLoading(it) }
        loginViewModel.error.observe(this) { onError(it) }
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

    private fun sucessGetFiscal(fiscal: Fiscal?) {

    }

    private fun sucessGetAllTurma(allTurmas: List<Turma>?) {

    }

    private fun onLoading(loading: Boolean?) {
        loading?.let {

        }
    }

    private fun onError(error: Event.Error?) {
        if (error == null)
            return
    }
}