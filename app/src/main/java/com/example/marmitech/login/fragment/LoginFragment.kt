package com.example.marmitech.login.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.example.domain.marmitech.appPeople.model.DataList
import com.example.domain.marmitech.appPeople.model.Fiscal
import com.example.domain.marmitech.appPeople.model.FiscalSaved
import com.example.domain.marmitech.appPeople.model.Turma
import com.example.domain.marmitech.base.Event
import com.example.marmitech.R
import com.example.marmitech.main.activity.MainActivity
import com.example.marmitech.databinding.FragmentLoginBinding
import com.example.marmitech.extension.showDialog
import com.example.marmitech.extension.showToast
import com.example.marmitech.extension.toDataList
import com.example.marmitech.login.LoginViewModel
import com.example.marmitech.login.adapter.TurmaAdapter
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
    private var turmaSelected: Turma? = null

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)

        loginViewModel.insertTurma.observe(this) { onInsertTurma(it) }
        loginViewModel.insertFiscalSaved.observe(this) { onInsertFiscalSaved(it) }
        loginViewModel.insertFuncionario.observe(this) { onInsertFuncionario(it) }
        loginViewModel.insertFiscal.observe(this) { onInsertFiscal(it) }
        loginViewModel.fiscal.observe(this) { fiscal -> sucessGetFiscal(fiscal) }
        loginViewModel.allTurmas.observe(this) { allTurmas -> sucessGetAllTurma(allTurmas) }
        loginViewModel.deleteAllFiscalSaved.observe(this) {}
        loginViewModel.loading.observe(this) { onLoading(it) }
        loginViewModel.error.observe(this) { onError(it) }
    }

    override fun onCreateView(
        inflator: LayoutInflater, container: ViewGroup?, savedInstance: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflator, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btUpdate.setOnClickListener {
            updateFromJsonFile()
        }

        binding.btSignIn.setOnClickListener {
            if (binding.matriculaEditText.text.toString()
                    .isEmpty() || binding.passwords.text.toString()
                    .isEmpty() || turmaSelected == null
            ) {
                context?.showDialog("Existe campos vazios")
            } else {
                loginViewModel.getFiscal(
                    binding.matriculaEditText.text.toString().toLong(),
                    turmaSelected?.codigo ?: 0,
                    binding.passwords.text.toString()
                )
            }
        }

        updateFromJsonFile(true)
    }

    override fun onResume() {
        super.onResume()
        loginViewModel.deleteAllFiscalSaved()

        binding.matriculaEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.matriculaLayout.error = getString(R.string.tv_required)
                binding.matriculaLayout.isErrorEnabled = s.isEmpty()
            }
        })

        binding.passwords.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                binding.passwordLayout.error = getString(R.string.tv_required)
                binding.passwordLayout.isErrorEnabled = s.isEmpty()
            }
        })
    }

    private fun updateFromJsonFile(first: Boolean = false) {
        onLoading(true)

        val list = context?.toDataList()?.dataList
        list?.let {
            insert(it, first)
        } ?: kotlin.run { onLoading(false) }
    }

    private fun insert(dataList: DataList?, first: Boolean = false) {
        dataList?.let { objects ->
            objects.turma?.let { turmaList ->
                turmaList.iterator().forEach { turma ->
                    loginViewModel.insertTurma(turma)
                }
            }

            objects.fiscal?.let { fiscalList ->
                fiscalList.iterator().forEach { fiscal ->
                    loginViewModel.insertFiscal(fiscal)
                }
            }

            objects.funcionario?.let { funcionarioList ->
                funcionarioList.iterator().forEach { funcionario ->
                    loginViewModel.insertFuncionario(funcionario)
                }
            }
        }
        if (!first) context?.showDialog("Cadastros atualizados com sucesso!")
    }

    private fun sucessGetAllTurma(allTurmas: List<Turma>?) {
        activity?.let { act ->
            allTurmas?.let {
                val adapter = TurmaAdapter(it)
                binding.spinnerTurmas.adapter = adapter
                binding.spinnerTurmas.setSelection(0)

                binding.spinnerTurmas.onItemSelectedListener =
                    (object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            turmaSelected = null
                        }

                        override fun onItemSelected(
                            parent: AdapterView<*>, view: View?, position: Int, id: Long
                        ) {
                            turmaSelected = parent.getItemAtPosition(position) as Turma
                        }
                    })
            }
        }
    }

    private fun sucessGetFiscal(fiscal: List<Fiscal>?) {
        fiscal?.takeIf { it.isNotEmpty() }?.get(0)?.let {
            loginViewModel.insertFiscalSaved(FiscalSaved(it.matricula, it.nome, it.turma))
        } ?: kotlin.run {
            context?.showDialog("Matricula ou senha errada, tente novamente.")
        }
    }

    private fun onInsertTurma(data: Boolean?) {
        data?.let {
            if (!it) {
                context?.showToast("Não foi possível atualizar as turmas, tente novamente.")
            } else {
                loginViewModel.getAllTurma()
            }
        }
    }

    private fun onInsertFiscal(data: Boolean?) {
        data?.let {
            if (!it) {
                context?.showToast("Não foi possível atualizar os fiscais, tente novamente.")
            }
        }
    }

    private fun onInsertFiscalSaved(data: Boolean?) {
        data?.let {
            if (it) {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun onInsertFuncionario(data: Boolean?) {
        data?.let {
            if (!it) {
                context?.showToast("Não foi possível atualizar os funcionarios, tente novamente.")
            }
        }
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
        if (error == null) return
        else context?.showDialog("Desculpe não foi possível completar a ação, tente novamente.")
    }
}