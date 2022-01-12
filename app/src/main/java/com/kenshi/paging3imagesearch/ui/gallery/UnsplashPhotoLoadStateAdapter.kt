package com.kenshi.paging3imagesearch.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kenshi.paging3imagesearch.databinding.UnsplashPhotoLoadStateFooterBinding

class UnsplashPhotoLoadStateAdapter(private val retry:() -> Unit):
    LoadStateAdapter<UnsplashPhotoLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding =
            UnsplashPhotoLoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(private val binding: UnsplashPhotoLoadStateFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

            init {
                binding.btnFooterRetry.setOnClickListener {
                    retry.invoke()
                }
            }

            fun bind(loadState: LoadState) = with(binding) {
                pbFooter.isVisible = loadState is LoadState.Loading
                btnFooterRetry.isVisible = loadState is LoadState.Loading
                tvFooterError.isVisible = loadState !is LoadState.Loading
            }
        }
}