package com.example.caesarstranslator.view.screen.main

import android.os.Bundle
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

}