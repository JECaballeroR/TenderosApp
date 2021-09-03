package com.slabcode.agutierrezt.example2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.slabcode.agutierrezt.example2.R
import com.slabcode.agutierrezt.example2.databinding.FragmentSignUpBinding
import com.slabcode.agutierrezt.example2.ui.viewmodels.LoginViewModel
import com.slabcode.agutierrezt.example2.utils.isValidEmail
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_sign_up, container, false)
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onStart() {
        super.onStart()

        loginViewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireContext(),error, Toast.LENGTH_LONG).show()
        })

        loginViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            Toast.makeText(requireContext(),"Usuario registrado", Toast.LENGTH_LONG).show()
            requireActivity().onBackPressed()
        })


        binding.signUpButton.setOnClickListener {
            var isValid = true

            if(!binding.signUpEmail.text.isValidEmail()) {
                isValid = false
                binding.signUpEmailLayout.error = "Correo electronico no valido"
            } else {
                binding.signUpEmailLayout.error = null
            }

            if(binding.signUpPassword.text.toString().length < 4) {
                isValid = false
                binding.signUpPasswordLayout.error = "Contraseña invalida"
            } else {
                binding.signUpPasswordLayout.error = null
            }

            if(binding.signUpName.text.toString().length < 4) {
                isValid = false
                binding.signUpNameLayout.error = "Nombre invalido"
            } else {
                binding.signUpNameLayout.error = null
            }

            if(isValid) {
                loginViewModel.signUp(binding.signUpEmail.text.toString(),
                    binding.signUpName.text.toString(), binding.signUpPassword.text.toString())
            }

        }
    }

}