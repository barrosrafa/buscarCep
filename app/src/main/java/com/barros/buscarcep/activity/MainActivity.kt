package com.barros.buscarcep.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.barros.buscarcep.R
import com.barros.buscarcep.base.ViewModelFactory
import com.barros.buscarcep.viewmodel.CepViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CepViewModel
    private lateinit var btBuscar: Button
    private lateinit var tvResult: TextView
    private lateinit var etPostalCode: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(CepViewModel::class.java)
        initViews()
        initObservables()
        setListeners()
    }

    private fun initViews() {
        btBuscar = findViewById(R.id.btBuscar)
        tvResult = findViewById(R.id.tvResult)
        etPostalCode = findViewById(R.id.etPostalCode)
    }

    private fun initObservables() {
        viewModel.getPostalCode.observe(this, Observer {
            if(it != null) {
                showLoading(false)
                val cep = it
                tvResult.text = cep.toString()
            }
        })

        viewModel.getError.observe(this, Observer {
            if(it != null) {
                showLoading(false)
                tvResult.text = it
            }
        })
    }

    private fun setListeners() {
        btBuscar.setOnClickListener { searchPostalCode() }
    }

    private fun searchPostalCode() {
        if(!etPostalCode.editableText.isEmpty() && etPostalCode.editableText.count() == 8) {
            showLoading(true)
            viewModel.searchPostalCode(etPostalCode.editableText.toString())
        } else {
            etPostalCode.error = getString(R.string.error_incorrect_zip)
        }
    }

    private fun showLoading(loop: Boolean) {
        if(loop) av_from_code.setAnimation("loading.json")
        av_from_code.playAnimation()
        av_from_code.loop(loop)
        if(!loop) av_from_code.visibility = View.INVISIBLE
    }
}
