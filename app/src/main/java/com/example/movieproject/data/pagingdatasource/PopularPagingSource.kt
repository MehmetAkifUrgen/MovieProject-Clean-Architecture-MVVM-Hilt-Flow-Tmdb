//package com.example.movieproject.data.pagingdatasource
//
//import android.util.Log
//import androidx.paging.PagingSource
//import androidx.paging.PagingState
//import com.example.movieproject.data.api.ServiceInterface
//import com.example.movieproject.data.uimodel.populars.PopularUiModel
//import com.example.movieproject.data.usecase.populars.PopularsUseCase
//import com.example.movieproject.ui.mapper.populars.PopularsMapper
//
//
//class PopularPagingSource(
//    private val popularsUseCase: PopularsUseCase,
//    private val popularsMapper: PopularsMapper
//) : PagingSource<Int, PopularUiModel>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularUiModel> {
//        val page = params.key ?: 1
//        return try {
//
//            val response = mapPo(page)
//            val data = response
//            LoadResult.Page(
//                data = data,
//                prevKey = if (page == 1) null else page - 1,
//                nextKey = if (data.isEmpty()) null else page + 1
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//
//    suspend fun mapPo(page: Int): ArrayList<PopularUiModel> {
//        var data = arrayListOf<PopularUiModel>()
//        popularsUseCase.pages(page)
//        popularsUseCase.execute(onSuccess = {
//            popularsMapper.mapOnPopularsResponse(it)
//            data = popularsMapper.popularsAdapterList
//        },
//            onError = {
//                Log.e("hata", it.cause.toString())
//            })
//        return data
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, PopularUiModel>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(
//                anchorPosition
//            )?.prevKey
//        }
//    }
//}
