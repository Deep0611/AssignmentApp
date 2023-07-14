package com.assignment.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.data.model.DataModel
import com.assignment.databinding.FragmentMainBinding
import com.assignment.presentation.viewmodel.DataViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: MainAdapter
    private var dataList = ArrayList<DataModel>()
    private val dataViewModel: DataViewModel by viewModel()
    private var timer: Timer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        initObservers()
    }

    private fun initialise() {
        timer = Timer()
        timer!!.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                dataViewModel.getRemoteJoke()
            }
        }, 0, 6  * 1000)
    }

    override fun onStart() {
        super.onStart()
        initialise()
    }

    override fun onPause() {
        super.onPause()
        timer!!.cancel()
    }

    private fun setAdapter() {
        adapter = MainAdapter(dataList)
        binding.rvJokes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvJokes.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            dataViewModel.getJokesFromDB().observe(viewLifecycleOwner) {
                dataList.clear()
                dataList.addAll(it.map { jokesEntity ->
                    jokesEntity.toDataModel()
                }.reversed())
                binding.rvJokes.scrollToPosition(dataList.size - 1)
                adapter.notifyDataSetChanged()
            }
        }
    }
}