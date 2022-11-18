package com.example.caesarstranslator.view.screen.translate

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.example.caesarstranslator.R
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
                if (binding.etOffset.text.isNullOrEmpty()) {
                    binding.layoutOffset.helperText = "Should contain number"
                } else {

                    when (binding.tvTop.text) {
                        getString(R.string.decrypted) -> binding.tiEtDecText.setText(
                            cipher(
                                p0.toString(),
                                binding.etOffset.text.toString().toInt()
                            )
                        )
                        getString(R.string.encrypted) -> binding.tiEtDecText.setText(
                            cipher(
                                p0.toString(),
                                26 - (binding.etOffset.text.toString().toInt() % 26)
                            )
                        )
                    }
                    binding.layoutOffset.helperText = null
                }
            }
        })

        binding.imgChange.setOnClickListener {
            val rotateAnim = AnimationUtils.loadAnimation(requireActivity(), R.anim.rotate_anim)
            binding.imgChange.startAnimation(rotateAnim)

            when (binding.tvTop.text) {
                getString(R.string.decrypted) -> {
                    binding.tvTop.text = getString(R.string.encrypted)
                    binding.tvBot.text = getString(R.string.decrypted)
                    binding.tiEtText.text = binding.tiEtDecText.text
                }

                getString(R.string.encrypted) -> {
                    binding.tvTop.text = getString(R.string.decrypted)
                    binding.tvBot.text = getString(R.string.encrypted)
                    binding.tiEtText.text = binding.tiEtDecText.text
                }
            }
        }

        binding.etOffset.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.etOffset.text.isNullOrEmpty()) {
                    binding.layoutOffset.helperText = "Should contain number"
                } else {
                    if (!binding.tiEtText.text.isNullOrEmpty()) {

                        when (binding.tvTop.text) {
                            getString(R.string.decrypted) -> binding.tiEtDecText.setText(
                                cipher(
                                    p0.toString(),
                                    binding.etOffset.text.toString().toInt()
                                )
                            )
                            getString(R.string.encrypted) -> binding.tiEtDecText.setText(
                                cipher(
                                    p0.toString(),
                                    26 - (binding.etOffset.text.toString().toInt() % 26)
                                )
                            )
                        }

                    }

                    binding.layoutOffset.helperText = null
                }
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