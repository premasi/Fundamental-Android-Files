package rk.enkidu.githubusers20.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import rk.enkidu.githubusers20.DetailActivity
import rk.enkidu.githubusers20.MainViewModel
import rk.enkidu.githubusers20.data.helper.ViewModelFactory
import rk.enkidu.githubusers20.databinding.FragmentDetailBinding
import rk.enkidu.githubusers20.response.TempDetailResponse

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding

    //ViewModel
    private lateinit var mainViewModel : MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = DetailActivity().getTempDataUsername()

        mainViewModel = obtainViewModel(requireActivity() as AppCompatActivity)

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
        binding.apply {
            this!!.tvUsernameDetail.text = user.login
            Glide.with(this@DetailFragment)
                .load("${user.avatarUrl}")
                .into(ivAvatarDetail)
            tvNameDetail.text = user.name
            tvFollower.text = user.followers.toString()
            tvFollowing.text = user.following.toString()
            tvCompanyDetail.text = user.company
            tvLocationDetail.text = user.location
        }

    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }

    private fun showLoading(isLoading: Boolean) {binding?.pb0?.visibility = if(isLoading) View.VISIBLE else View.GONE}


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}