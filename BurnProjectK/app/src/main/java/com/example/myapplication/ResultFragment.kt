package com.example.burnproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.MyApi
import com.example.myapplication.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ResultFragment : Fragment() {

    //this is final
    lateinit var TBSAresult : TextView
    lateinit var Fluidresult1 : TextView
    lateinit var result1: Button
    lateinit var result2:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_result, container, false)

        TBSAresult=view.findViewById(R.id.TBSAResult)
        Fluidresult1=view.findViewById(R.id.fluidresult1)
        result1=view.findViewById(R.id.result1)
        result2=view.findViewById(R.id.buttonresult2)
//        val tbsa=30.5732
//        TBSAresult.text=tbsa.toString()
        lifecycleScope.launchWhenCreated {
//            findNavController().navigate(R.id.action_resultFragment_to_frontFragment)
        }
        //from front
//        val args =this.arguments
//        val gotfromfront = args?.get("frontresultee")
//        TBSAresult.text = gotfromfront.toString()
//        Log.d("ep1",gotfromfront.toString())
//
//        //from back
//        val gotfromback = args?.get("backresultee")
//        Log.d("ep2",gotfromback.toString())
//        //from form
//        val gotfromformweight = args?.get("frontresulteeweight")
//        val gotfromformtime = args?.get("frontresulteetime")

        result1.setOnClickListener {
//            lifecycleScope.launchWhenCreated {
//                findNavController().navigate(R.id.action_backFragment_to_formFragment)
//            }
            val retrofit = Retrofit.Builder()
                .baseUrl("https://59bb-117-219-22-193.ngrok-free.app/predict/finalResult/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                        .build()
                )
                .build()
            val myApi = retrofit.create(MyApi::class.java)
            myApi.getFloat().enqueue(object : Callback<Float> {

                override fun onResponse(call: Call<Float>, response: Response<Float>) {
                    var result = response.body()
                    //this
                    TBSAresult.text = result.toString()
//                    val bundle = Bundle()
//                    bundle.putString("backresultee",TBSAresult.text.toString())
//                    val fragment = ResultFragment()
//                    fragment.arguments = bundle
                }

                override fun onFailure(call: Call<Float>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                }
            })
//            val fragment = FormFragment()
//            val transaction = fragmentManager?.beginTransaction()
//            transaction?.replace(R.id.nav_container,fragment)?.commit()
        }
        result2.setOnClickListener{
            val retrofit = Retrofit.Builder()
                .baseUrl("https://59bb-117-219-22-193.ngrok-free.app/predict/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                        .build()
                )
                .build()
            val myApi = retrofit.create(MyApi::class.java)
            myApi.getString().enqueue(object : Callback<String> {

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    var results = response.body()
                    //this
                    Fluidresult1.text = results.toString()

//                    val bundle = Bundle()
//                    bundle.putString("backresultee",TBSAresult.text.toString())
//                    val fragment = ResultFragment()
//                    fragment.arguments = bundle
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()              }
            })
        }


//        val fragment = FrontFragment()
//        val transaction = fragmentManager?.beginTransaction()
//        transaction?.replace(R.id.nav_container,fragment)?.commit()
        return view

    }

}