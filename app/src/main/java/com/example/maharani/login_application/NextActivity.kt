package com.example.maharani.login_application

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import retrofit.Callback
import retrofit.RestAdapter
import retrofit.RetrofitError
import retrofit.client.Response


class NextActivity : AppCompatActivity() {

    //List view to show data
    private var listView:ListView? = null

    //List of type books this list will store type Book which is our data model
    private var books : List<Book>? = null
    private val getBooks: Book? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        //Initializing the list view
        listView = findViewById<ListView>(R.id.listViewBooks)

        //Calling the method that will fetch data
        getBooks()

        //Setting onItemClickListener to listview
        listView!!.setOnItemClickListener { adapterView, view, i, l ->

            //Creating an intent
            val intent = Intent(this,ShowBookDetails::class.java)

            //Getting the requested book form the list
            val book = books!![i]

            //Adding book details to intent
            intent.putExtra(KEY_BOOK_ID,book.bookId)
            intent.putExtra(KEY_BOOK_NAME,book.name)
            intent.putExtra(KEY_BOOK_PRICE,book.price)
            intent.putExtra(KEY_BOOK_STOCK,book.inStock)

            //Starting another activity to show book details
            startActivity(intent)

        }
    }

private fun getBooks() {
    //While the app fetched data we are displaying a progress dialog
    //Progress Dialog is deprecated use progres bar
    val loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false)

    //Creating a rest adapter
    val adapter = RestAdapter.Builder()
            .setEndpoint(ROOT_URL)
            .build()

    //Creating an object of our api interface
    val api = adapter.create<BooksAPI>(BooksAPI::class.java!!)



    api.getBooks(object : Callback<List<Book>> {
        override fun success(list: List<Book>, response: Response) {
            //Dismissing the loading progressbar
            loading.dismiss()

            //Storing the data in our list
            books = list

            //Calling a method to show the list
            showList()
        }

        override fun failure(error: RetrofitError) {
            //you can handle the errors here
        }
    })

}

    //Our method to show list
    private fun showList() {
        //String array to store all the book names
        val items = arrayOfNulls<String>(books!!.size)

        //Traversing through the whole list to get all the names
        for (i in books!!.indices) {
            //Storing names to string array
            items[i] = books!![i].name
        }

        //Creating an array adapter for list view
        val adapter = ArrayAdapter<String>(this, R.layout.simple_list, items)

        //Setting adapter to listview
        listView!!.adapter = adapter
    }


    companion object {
        //Root URL
        val ROOT_URL = "https://api.myjson.com/"

        //Strings to bind with intent
        val KEY_BOOK_ID = "key_book_id"
        val KEY_BOOK_NAME = "key_book_name"
        val KEY_BOOK_PRICE = "key_book_price"
        val KEY_BOOK_STOCK = "key_book_stock"
    }






}
