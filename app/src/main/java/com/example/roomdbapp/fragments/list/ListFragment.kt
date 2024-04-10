package com.example.roomdbapp.fragments.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbapp.R
import com.example.roomdbapp.data.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment() : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var listAdapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list, container, false)
        val ft: FloatingActionButton = view.findViewById(R.id.floatingActionButton)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        listAdapter = ListAdapter(requireContext())
        recyclerView.adapter
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {user ->
            listAdapter.setData(user)
        })

        ft.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        return view
    }

}