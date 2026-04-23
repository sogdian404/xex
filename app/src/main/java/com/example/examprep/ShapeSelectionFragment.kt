package com.example.examprep

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar


class ShapeSelectionFragment : Fragment() {

    private  var selected = "Треугольник"
    private lateinit var imageView: ImageView
    private lateinit var formulaText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shape_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinner = view.findViewById<Spinner>(R.id.spinnerShapes)
        imageView = view.findViewById(R.id.ImageShape)
        formulaText = view.findViewById(R.id.textFormula)
        val editValue = view.findViewById<EditText>(R.id.editValue)
        val btnCalculate = view.findViewById<Button>(R.id.CalcBtn)

        updateUI(selected)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>, p1: View?, p2: Int, p3: Long) {
                selected = p0.getItemAtPosition(p2).toString()
                updateUI(selected)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        btnCalculate.setOnClickListener{
            val valeStr = editValue.text.toString().trim()
            if (valeStr.isEmpty())
            {
                Snackbar.make(it,"Заполните значение",Snackbar.LENGTH_SHORT).setAction("ok"){}.show()
                return@setOnClickListener
            }

            val value = valeStr.toDoubleOrNull()
            if (value==null || value<=0)
            {
                Snackbar.make(it,"Введите положиельное число",Snackbar.LENGTH_SHORT).setAction("ok"){}.show()
                return@setOnClickListener
            }

            val resultFragment = ResultFragment().apply {
                arguments = Bundle().apply {
                    putString("shape",selected)
                    putDouble("value",value)
                }
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,resultFragment)
                .addToBackStack(null)
                .commit()

        }

    }
    private fun updateUI(shape: String) {
        when (shape) {
            "Треугольник" -> {
                imageView.setImageResource(R.drawable.triangle)
                formulaText.text = "Периметр = 3 × a"
            }
            "Круг" -> {
                imageView.setImageResource(R.drawable.circle)
                formulaText.text = "Площадь = π × r²"
            }
            "Ромб" -> {
                imageView.setImageResource(R.drawable.rhombus)
                formulaText.text = "Площадь = (d₁ × d₂) / 2\n(предполагаем d₁ = d₂)"
            }
        }
    }
}