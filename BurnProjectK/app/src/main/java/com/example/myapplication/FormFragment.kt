package com.example.burnproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.ApiResponse
import com.example.myapplication.Intreq
import com.example.myapplication.MyApi
import com.example.myapplication.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FormFragment : Fragment() {
    lateinit var name: EditText
    lateinit var age: EditText
    lateinit var weight: EditText
    lateinit var time: EditText
    lateinit var cause: EditText
    lateinit var medical: EditText
    lateinit var result: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_form, container, false)
        name=view.findViewById(R.id.Name)
        age=view.findViewById(R.id.Age)
        weight=view.findViewById(R.id.Weight)
        time=view.findViewById(R.id.Time)
        cause=view.findViewById(R.id.cause)
        medical=view.findViewById(R.id.medical)
        result=view.findViewById(R.id.result)

        result.setOnClickListener(View.OnClickListener {
//            lifecycleScope.launchWhenCreated {
            findNavController().navigate(R.id.action_formFragment_to_resultFragment2)
//            }
            val name: String = name.text.toString()
            val age: String = age.text.toString()
            //weight and time
            val weight: Int = weight.text.toString().toInt()
            val time: String = time.text.toString()


//            val bundle = Bundle()
//            bundle.putString("frontresulteeweight", weight.toString())
//            bundle.putString("frontresulteetime",time)
//            val fragmentba = ResultFragment()
//            fragmentba.arguments = bundle




            val cause: String = cause.text.toString()
            val medical: String = medical.text.toString()

            if(name.isEmpty() ||age.isEmpty()||weight==null||time.isEmpty()){
                Toast.makeText(requireContext(),"Enter All details compulsary", Toast.LENGTH_SHORT).show()
            }
            if(!(age.toInt()>0&&age.toInt()<70)){
                Toast.makeText(requireContext(),"Enter Age correctly", Toast.LENGTH_SHORT).show()
            }
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
            val senddata=name.toString()+"#"+weight.toString()+"#"+time.toString()
            val myApi = retrofit.create(MyApi::class.java)
            val intreq = senddata.toString()?.let { it1 -> Intreq(it1 ) }
            if (intreq != null) {
                myApi.postInt(intreq).enqueue(object : Callback<ApiResponse>{
                    override fun onResponse(
                        call: Call<ApiResponse>,
                        response: Response<ApiResponse>
                    ) {
                    }

                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                        Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }
        })
//            val fragment =ResultFragment()
//            val transaction = fragmentManager?.beginTransaction()
//            transaction?.replace(R.id.nav_container,fragment)?.commit()
        return view

    }


}