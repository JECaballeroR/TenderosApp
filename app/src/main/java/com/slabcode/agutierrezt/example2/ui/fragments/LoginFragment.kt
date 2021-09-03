package com.slabcode.agutierrezt.example2.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.slabcode.agutierrezt.example2.R
import com.slabcode.agutierrezt.example2.databinding.FragmentLoginBinding
import com.slabcode.agutierrezt.example2.ui.activities.MainActivity
import com.slabcode.agutierrezt.example2.ui.viewmodels.LoginViewModel
import com.slabcode.agutierrezt.example2.utils.isValidEmail
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_login, container, false)
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onStart() {
        super.onStart()

        loginViewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireContext(),error, Toast.LENGTH_LONG).show()
        })

        loginViewModel.user.observe(viewLifecycleOwner, Observer { user ->
//            Toast.makeText(requireContext(),"Usuario registrado", Toast.LENGTH_LONG).show()
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        })

        binding.loginSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.loginButton.setOnClickListener {
            var isValid = true

            if(!binding.loginEmail.text.isValidEmail()) {
                isValid = false
                binding.loginEmailLayout.error = "Correo electronico no valido"
            } else {
                binding.loginEmailLayout.error = null
            }

            if(binding.loginPassword.text.toString().length < 4) {
                isValid = false
                binding.loginPasswordLayout.error = "Contraseña invalida"
            } else {
                binding.loginPasswordLayout.error = null
            }


            if(isValid) {
                loginViewModel.login(binding.loginEmail.text.toString(),
                    binding.loginPassword.text.toString())
            }
        }

    }


}