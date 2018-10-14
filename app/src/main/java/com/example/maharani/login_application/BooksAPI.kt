package com.example.maharani.login_application

import retrofit.http.GET
import retrofit.Callback



/**
 * Created by maharani on 05/11/17.
 */
interface BooksAPI {
    /* Retrofit get annotation with our URL And our method that will return us the list ob Book
     */

   @GET("/bins/jul6f")

    //val getBooks: Call<Responce>
    fun getBooks(response:Callback<List<Book>>)


}