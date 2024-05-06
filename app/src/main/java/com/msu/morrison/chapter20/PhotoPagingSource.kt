package com.msu.morrison.chapter20

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.msu.morrison.chapter20.api.GalleryItem

class PhotoPagingSource(
    private val repository: PhotoRepository
) : PagingSource<Int, GalleryItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryItem> {
        val pageNumber = params.key ?: 1
        return try {
            val items = repository.fetchPhotos(pageNumber)
            LoadResult.Page(
                data = items,
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (items.isEmpty()) null else pageNumber + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GalleryItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
