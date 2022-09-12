package rk.enkidu.githubusers20.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import rk.enkidu.githubusers20.DetailActivity
import rk.enkidu.githubusers20.MainViewModel
import rk.enkidu.githubusers20.databinding.FragmentDetailBinding
import rk.enkidu.githubusers20.response.TempDetailResponse

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = DetailActivity().getTempDataUsername()

        mainViewModel.getDetailUser(data)

        mainViewModel.users.observe(viewLifecycleOwner){
            setProfileData(it)
        }

        //show loading
        mainViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }
    }

    private fun setProfileData(user: TempDetailResponse) {
        binding.tvUsernameDetail.text = user?.login
        Glide.with(this@DetailFragment)
            .load("${user.avatarUrl}")
            .into(binding.ivAvatarDetail)
        binding.tvNameDetail.text = user?.name
        binding.tvFollower.text = user?.followers.toString()
        binding.tvFollowing.text = user?.following.toString()
        binding.tvCompanyDetail.text = user?.company
        binding.tvLocationDetail.text = user?.location
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pb0.visibility = View.VISIBLE
        } else {
            binding.pb0.visibility = View.GONE
        }
    }

}