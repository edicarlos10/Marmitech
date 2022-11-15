package com.example.marmitech.marmitechUI.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.domain.marmitech.appPeople.model.Fiscal
import com.example.domain.marmitech.appPeople.model.Turma
import com.example.domain.marmitech.base.Event
import com.example.marmitech.databinding.FragmentLoginBinding
import com.example.marmitech.extension.showDialog
import com.example.marmitech.extension.toDataList
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

        loginViewModel.insertTurma.observe(this) { onInsertTurma(it) }
        loginViewModel.insertFiscalSaved.observe(this) { onInsertFiscalSaved(it) }
        loginViewModel.insertFiscal.observe(this) { onInsertFiscal(it) }
        loginViewModel.fiscal.observe(this) { fiscal -> sucessGetFiscal(fiscal) }
        loginViewModel.allTurmas.observe(this) { allTurmas -> sucessGetAllTurma(allTurmas) }
        loginViewModel.loading.observe(this) { onLoading(it) }
        loginViewModel.error.observe(this) { onError(it) }
    }

    private fun onInsertTurma(data: Boolean?) {
        data?.let {
            if (!it) {
                //Não foi possivel atualizar turma
            }
        }
    }

    private fun onInsertFiscal(data: Boolean?) {
        data?.let {
            if (!it) {
                //Não foi possivel atualizar os fiscais
            }
        }
    }

    private fun onInsertFiscalSaved(data: Boolean?) {
        data?.let {
            if (it) {
                // fiscal logado salvo na tabela
            }
        }
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

        binding.btUpdate.setOnClickListener {
            updateFromJsonFile()
        }

    }

    private fun sucessGetFiscal(fiscal: Fiscal?) {

    }

    private fun sucessGetAllTurma(allTurmas: List<Turma>?) {

    }

    private fun onLoading(loading: Boolean?) {
        loading?.let {
            if (loading == true) {
                binding.clLoginSecond.visibility = View.GONE
                binding.progressBarLogin.visibility = View.VISIBLE
            } else {
                binding.progressBarLogin.visibility = View.GONE
                binding.clLoginSecond.visibility = View.VISIBLE
            }
        }
    }

    private fun onError(error: Event.Error?) {
        if (error == null)
            return
        else
            context?.showDialog("Desculpe não foi possível completar a ação, tente novamente")
    }

    private fun updateFromJsonFile() {
        onLoading(true)

        val list = context?.toDataList()?.dataList
        list?.let {
            onLoading(false)
            context?.showDialog("Cadastros atualizados com sucesso!")
        } ?: kotlin.run { onLoading(false) }
    }
}