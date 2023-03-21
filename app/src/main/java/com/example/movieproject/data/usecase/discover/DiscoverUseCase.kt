//package com.example.movieproject.data.usecase.discover
//
//import com.example.movieproject.base.BaseUseCase
//import com.example.movieproject.data.repository.cast.CastRepository
//import com.example.movieproject.data.repository.popular.PopularRepository
//import com.example.movieproject.data.repository.search.SearchRepository
//import com.example.movieproject.data.response.casting.CastResponse
//import com.example.movieproject.data.response.popular.PopularDetailsResponse
//import com.example.movieproject.data.response.popular.PopularResponse
//import io.reactivex.rxjava3.core.Single
//import kotlinx.coroutines.flow.Flow
//import javax.inject.Inject
//
//class DiscoverUseCase @Inject constructor(
//    private val searchRepository: SearchRepository
//) : BaseUseCase<PopularResponse>() {
//
//    private lateinit var discover : String
//    private  var page:Int=1
//
//    fun discoverId(uii: String) {
//        discover = uii
//    }
//    fun getPage(p:Int){
//        page=p
//    }
//
//
//    override suspend fun buildUseCaseFlow(): Flow<PopularResponse> {
//        return searchRepository.getDiscover(discover)
//    }
//}