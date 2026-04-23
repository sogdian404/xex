package com.example.examprep

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlin.math.PI


class ResultFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textResult = view.findViewById<TextView>(R.id.textResult)
        val btnBack = view.findViewById<Button>(R.id.btnBack)

        val shape = arguments?.getString("shape")?:""
        val value = arguments?.getDouble("value")?:0.0


        val resultText = when(shape)
        {
            "Треугольник"->{
                val perimeter = 3 * value
                "Фигура: Треугольник\nПериметр = 3 × $value = ${"%.2f".format(perimeter)}"
            }
            "Круг" -> {
                val area = PI * value * value
                "Фигура: Круг\nПлощадь = π × $value² ≈ ${"%.2f".format(area)}"
            }
            "Ромб" -> {
                val area = (value * value) / 2.0
                "Фигура: Ромб\nПлощадь = ($value × $value) / 2 = ${"%.2f".format(area)}"
            }
            else->
            {
                "Ошибка: неизвестная фигура"
            }

        }
        textResult.text = resultText

        btnBack.setOnClickListener{
            parentFragmentManager.popBackStack()
        }



    }
}
