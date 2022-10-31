package rk.enkidu.githubusers20.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import rk.enkidu.githubusers20.DetailActivity
import rk.enkidu.githubusers20.MainViewModel
import rk.enkidu.githubusers20.R
import rk.enkidu.githubusers20.data.helper.ViewModelFactory
import rk.enkidu.githubusers20.databinding.FragmentFollowingBinding
import rk.enkidu.githubusers20.response.FollowingResponseItem

class FollowingFragment : Fragment() {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding

    //ViewModel
    private lateinit var mainViewModel : MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = DetailActivity().getTempDataUsername()

        mainViewModel = obtainViewModel(requireActivity() as AppCompatActivity)

        mainViewModel.getFollowing(data)

        mainViewModel.following.observe(viewLifecycleOwner){
            showFollowing(it)
        }

        //show loading
        mainViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }

    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }

    private fun showFollowing(following: List<FollowingResponseItem>){
        binding?.rvFollowing?.layoutManager = LinearLayoutManager(context)
        val listFollowingAdapter = ListFollowingAdapter(following)
        binding?.rvFollowing?.adapter = listFollowingAdapter

        if(listFollowingAdapter.itemCount <= 0){
            binding?.tvInfoFollowing?.text = getString(R.string.following_not_found)
            Toast.makeText(context, getString(R.string.following_not_found), Toast.LENGTH_SHORT).show()
        } else {
            binding?.rvFollowing?.adapter = listFollowingAdapter
        }

    }

    private fun showLoading(isLoading: Boolean) {binding?.pb3?.visibility = if(isLoading) View.VISIBLE else View.GONE}

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}