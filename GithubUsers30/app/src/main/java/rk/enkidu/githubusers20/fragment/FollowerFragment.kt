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
import rk.enkidu.githubusers20.databinding.FragmentFollowerBinding
import rk.enkidu.githubusers20.response.FollowersResponseItem


class FollowerFragment : Fragment() {

    private var _binding : FragmentFollowerBinding? = null
    private val binding get() = _binding

    //ViewModel
    private lateinit var mainViewModel : MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = DetailActivity().getTempDataUsername()

        mainViewModel = obtainViewModel(requireActivity() as AppCompatActivity)

        mainViewModel.getFollowers(data)

        mainViewModel.followers.observe(viewLifecycleOwner){
            showFollowers(it)
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

    private fun showFollowers(followers: List<FollowersResponseItem>){
        binding?.rvFollower?.layoutManager = LinearLayoutManager(context)
        val listFollowersAdapter = ListFollowersAdapter(followers)

        if(listFollowersAdapter.itemCount <= 0){
            binding?.tvInfoFollowers?.text = getString(R.string.followers_not_found)
            Toast.makeText(context, getString(R.string.followers_not_found), Toast.LENGTH_SHORT).show()
        } else {
            binding?.rvFollower?.adapter = listFollowersAdapter
        }

    }

    private fun showLoading(isLoading: Boolean) {binding?.pb2?.visibility = if(isLoading) View.VISIBLE else View.GONE}

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}