package rk.enkidu.githubusers20.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import rk.enkidu.githubusers20.DetailActivity
import rk.enkidu.githubusers20.MainViewModel
import rk.enkidu.githubusers20.databinding.FragmentFollowingBinding
import rk.enkidu.githubusers20.response.FollowingResponseItem

class FollowingFragment : Fragment() {

    private lateinit var binding: FragmentFollowingBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
        binding.rvFollowing.setHasFixedSize(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = DetailActivity().getTempDataUsername()

        mainViewModel.getFollowing(data)

        mainViewModel.following.observe(viewLifecycleOwner){
            showFollowing(it)
        }

        //show loading
        mainViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }



    }

    private fun showFollowing(following: List<FollowingResponseItem>){
        binding.rvFollowing.layoutManager = LinearLayoutManager(context)
        val listFollowingAdapter = ListFollowingAdapter(following)
        binding.rvFollowing.adapter = listFollowingAdapter

        if(listFollowingAdapter.itemCount <= 0){
            binding.tvInfoFollowing.text = "Following not found"
        } else {
            binding.rvFollowing.adapter = listFollowingAdapter
        }

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pb3.visibility = View.VISIBLE
        } else {
            binding.pb3.visibility = View.GONE
        }
    }




}