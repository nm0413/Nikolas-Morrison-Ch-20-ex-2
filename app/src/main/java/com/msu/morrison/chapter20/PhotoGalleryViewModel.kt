package com.msu.morrison.chapter20

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class PhotoGalleryViewModel : ViewModel() {
    private val photoRepository = PhotoRepository()

    val galleryItems = Pager(
        PagingConfig(pageSize = 20, enablePlaceholders = false)
    ) {
        PhotoPagingSource(photoRepository)
    }.flow.cachedIn(viewModelScope)
}
