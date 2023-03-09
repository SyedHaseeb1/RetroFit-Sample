package com.hsb.syedhaseebtaks_janbark.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.hsb.syedhaseebtaks_janbark.R
import com.hsb.syedhaseebtaks_janbark.data.models.Movies
import com.hsb.syedhaseebtaks_janbark.utils.ads.InterstitialAdNew


fun ImageView.setImage(src: String) {
    Glide.with(this.context)
        .load(src)
        .error(R.drawable.ic_image)
        .into(this)
}

fun Context.vertical(): LinearLayoutManager =
    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.isNetworkConnected(): Boolean {
    val mgr =
        this.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = mgr.activeNetworkInfo
    return netInfo != null && netInfo.isConnected && netInfo.isAvailable
}

fun Activity.showInterstitial(callBack: (() -> Unit)) {
    InterstitialAdNew.getInstance().showInterstitialAdNew(this) {
        callBack.invoke()
    }
}

//Update
@SuppressLint("InflateParams")
fun Activity.updateDialogue(movie: Movies, callBack: (Movies) -> Unit) {
    val dialogueBinding = layoutInflater.inflate(R.layout.update_dialogue, null)
    val myDialogue = Dialog(this)
    myDialogue.setContentView(dialogueBinding)
    myDialogue.setCancelable(false)
    myDialogue.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    myDialogue.show()
    val cancelBtn = myDialogue.findViewById<Button>(R.id.cancelbtn)
    val saveBtn = myDialogue.findViewById<Button>(R.id.savebtn)
    val titletxt = myDialogue.findViewById<EditText>(R.id.titletxt)
    val yeartxt = myDialogue.findViewById<EditText>(R.id.yeartxt)
    val typetxt = myDialogue.findViewById<EditText>(R.id.typetxt)
    val imagetxt = myDialogue.findViewById<EditText>(R.id.imagetxt)

    titletxt.setText(movie.Title)
    yeartxt.setText(movie.Year)
    typetxt.setText(movie.Type)
    imagetxt.setText(movie.Poster)

    cancelBtn.setOnClickListener {
        myDialogue.dismiss()
    }
    saveBtn.setOnClickListener {
        val title = titletxt.text.toString().trim()
        val year = yeartxt.text.toString().trim()
        val type = typetxt.text.toString().trim()
        val image = imagetxt.text.toString()
        val imdbID = movie.imdbID
        val id = movie.id
        val updatedMovie = Movies(id, ViewHolder_Simple, title, year, imdbID, type, image)
        callBack.invoke(updatedMovie)
        myDialogue.dismiss()
    }
}