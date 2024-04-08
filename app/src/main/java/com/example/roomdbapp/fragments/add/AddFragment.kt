package com.example.roomdbapp.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdbapp.R
import com.example.roomdbapp.data.User
import com.example.roomdbapp.data.UserViewModel


class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        val button: Button = view.findViewById(R.id.button)



        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        button.setOnClickListener {
            insertDataToDatabase(view)
        }

        return view
    }

    private fun insertDataToDatabase(view: View) {
        val addFirstName: EditText = view.findViewById(R.id.addFirstName)
        val addLastName: EditText = view.findViewById(R.id.addLastName)
        val addAge: EditText = view.findViewById(R.id.addAge)

        val firstName = addFirstName.text.toString()
        val lastName = addLastName.text.toString()
        val age = addAge.text

        if (checkInput(firstName, lastName, age)){
            //create user object
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
            //add data to database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }

    }

    private fun checkInput(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}