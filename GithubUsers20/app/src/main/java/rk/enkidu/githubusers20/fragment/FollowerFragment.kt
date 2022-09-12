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
import rk.enkidu.githubusers20.databinding.FragmentFollowerBinding
import rk.enkidu.githubusers20.response.FollowersResponseItem


class FollowerFragment : Fragment() {

    private lateinit var binding: FragmentFollowerBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
        binding.rvFollower.setHasFixedSize(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = DetailActivity().getTempDataUsername()

        mainViewModel.getFollowers(data)

        mainViewModel.followers.observe(viewLifecycleOwner){
            showFollowers(it)
        }

        //show loading
        mainViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }

    }

    private fun showFollowers(followers: List<FollowersResponseItem>){
        binding.rvFollower.layoutManager = LinearLayoutManager(context)
        val listFollowersAdapter = ListFollowersAdapter(followers)

        if(listFollowersAdapter.itemCount <= 0){
            binding.tvInfoFollowers.text = "Followers not found"
        } else {
            binding.rvFollower.adapter = listFollowersAdapter
        }

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pb2.visibility = View.VISIBLE
        } else {
            binding.pb2.visibility = View.GONE
        }
    }

}