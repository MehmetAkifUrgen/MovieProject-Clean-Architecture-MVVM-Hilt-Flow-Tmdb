package com.example.movieproject.ui.fragment.populars

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieproject.R
import com.example.movieproject.base.BaseFragment
import com.example.movieproject.databinding.FragmentAgentsBinding
import com.example.movieproject.ui.adapter.populars.PopularsAdapter
import com.example.movieproject.ui.adapter.upcoming.UpComingsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentsFragment : BaseFragment<FragmentAgentsBinding>() {

    private lateinit var popularAdapter :PopularsAdapter
    private var upComingsAdapter: UpComingsAdapter? = null
    private val viewModel : AgentsViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.fragment_agents

    override fun prepareView(savedInstanceState: Bundle?) {
        viewModel.agentsRequest()
        initAgentAdapterListObserve()
    }

    private fun initAdapter(){
       viewModel.agentAdapterList.value?.let { adapterList->
            popularAdapter = PopularsAdapter(adapterList, itemClick = {
                it.id?.let { id->
                    findNavController().navigate(AgentsFragmentDirections.actionAgentsFragmentToAgentDetailFragment(id))
                }
            })
            binding.apply {
                movieRecyclerView.adapter = popularAdapter
                movieRecyclerView.set3DItem(true)
                movieRecyclerView.setInfinite(true)
                movieRecyclerView.setAlpha(true)
                movieRecyclerView.setFlat(true)
            }

        }
        viewModel.upcomingAdapterList.value?.let { adapterList->
            upComingsAdapter = UpComingsAdapter(adapterList, itemClick = {
                it.id?.let { id->
                    Log.d("kk",id)
                    findNavController().navigate(AgentsFragmentDirections.actionAgentsFragmentToAgentDetailFragment(id))
                }
            })
            binding.apply {
                upcomingRecyclerView.adapter = upComingsAdapter
                upcomingRecyclerView.set3DItem(true)
                upcomingRecyclerView.setInfinite(true)
                upcomingRecyclerView.setAlpha(true)
                upcomingRecyclerView.setFlat(true)
            }
        }
    }

    private fun initAgentAdapterListObserve(){
        viewModel.agentAdapterList.observe(viewLifecycleOwner){
            initAdapter()
        }
        viewModel.upcomingAdapterList.observe(viewLifecycleOwner){
            initAdapter()
        }
    }
}