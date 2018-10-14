package com.example.maharani.login_application

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

/**
 * Created by maharani on 05/11/17.
 */
class ShowBookDetails : AppCompatActivity() {

    //Defining Views
    //private TextView textViewBookId;
    private var textViewBookName : TextView? = null
    private var textViewBookPrice : TextView? = null

    private var textViewBookInStock : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_book_detail)

        //Initialize Views
       // textViewBookId = (TextView) findViewById(R.id.textViewBookId);
        textViewBookName = findViewById<TextView>(R.id.textViewBookName)
        textViewBookPrice = findViewById<TextView>(R.id.textViewBookPrice)
        textViewBookInStock = findViewById<TextView>(R.id.textViewBookInStock)

        //Getting intent
        val intent = intent

        //Displaying values by fetching from intent
       // textViewBookId.setText(String.valueOf(intent.getIntExtra(NextActivity.KEY_BOOK_ID, 0)));
        textViewBookName!!.text = "Title: " + intent.getStringArrayExtra(NextActivity.KEY_BOOK_NAME)
        textViewBookPrice!!.text = "Price: " + intent.getStringExtra(NextActivity.KEY_BOOK_PRICE)
        textViewBookInStock!!.text = "In Stock: " + intent.getIntExtra(NextActivity.KEY_BOOK_STOCK, 0).toString()
    }
}

