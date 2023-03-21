package com.example.movieproject.data.pagingdatasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieproject.data.api.ServiceInterface
import com.example.movieproject.data.response.popular.Movie


class DiscoverPaging(val genre:String,private val movieInterface: ServiceInterface) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1

        return try {

            val data = movieInterface.getDiscover(genre,page)
            Log.d("TAG", "load: ${data.results}")
            LoadResult.Page(
                data = data.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.results.isEmpty()) null else page + 1
            )


        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }


    }
}