package es.jco.demo.data.server

import es.jco.data.source.RemoteDataSource
import javax.inject.Inject


class ServerDataSource @Inject constructor(val apiService: APIService) : RemoteDataSource {

}