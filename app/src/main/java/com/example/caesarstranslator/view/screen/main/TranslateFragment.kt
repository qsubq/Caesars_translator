package com.example.caesarstranslator.view.screen.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.caesarstranslator.databinding.FragmentTranslateBinding


class TranslateFragment : Fragment() {
    private lateinit var binding: FragmentTranslateBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTranslateBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tiEtText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.tiEtDecText.setText(cipher(p0.toString(), binding.etOffset.text.toString().toInt()))
            }
        })
    }

    private fun cipher(message: String?, offset: Int): String {
        val result = StringBuilder()
        for (character in message!!.toCharArray()) {
            if (character != ' ') {
                val originalAlphabetPosition = character - 'a'
                val newAlphabetPosition = (originalAlphabetPosition + offset) % 26
                val newCharacter = ('a'.code + newAlphabetPosition).toChar()
                result.append(newCharacter)
            } else {
                result.append(character)
            }
        }
        return result.toString()
    }

}