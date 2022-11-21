package com.example.marmitech.apontamento.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marmitech.databinding.FragmentFuncionarioBinding

class FuncionarioFragment : Fragment() {
    companion object {
        fun newInstance(): FuncionarioFragment {
            val args = Bundle().apply { }
            val fragment = FuncionarioFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentFuncionarioBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFuncionarioBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btYes.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.btYes.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}