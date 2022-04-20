package ru.homebuy.neito

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.annotations.NotNull
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash_screen.*
import ru.homebuy.neito.Model.Houses


class MainActivity : AppCompatActivity() {


    var HouseReference: DatabaseReference? = null
    private var recyclerView: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    var cost: String? = null
    var location: String? = null
    var room: String? = null
    var square: String? = null
    var info: String? = null
    var number: String? = null
    var image: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        HouseReference = FirebaseDatabase.getInstance().reference.child("Houses")

        val PlusAdActivity = findViewById<View>(R.id.addAnAd) as Button
        PlusAdActivity.setOnClickListener { view ->
            val intent = Intent(view.context, PlusAd::class.java)
            view.context.startActivity(intent)
        }

        /* settingButton.setOnClickListener() {
             val intentSet = Intent(this, setting_homes::class.java)
             startActivity(intentSet)
         }*/

        recyclerView = findViewById(R.id.recycler_menu)
        recyclerView?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView?.setLayoutManager(layoutManager)
    }

    override fun onStart() {
        super.onStart()


        val options = FirebaseRecyclerOptions.Builder<Houses>()
            .setQuery(HouseReference!!, Houses::class.java).build()

        val adapter: FirebaseRecyclerAdapter<Houses, HouseViewHolder> =
            object : FirebaseRecyclerAdapter<Houses, HouseViewHolder>(options) {
                @SuppressLint("SetTextI18n")
                override fun onBindViewHolder(
                    @NotNull holder: HouseViewHolder,
                    i: Int,
                    @NotNull model: Houses
                ) {
                    Log.v("adapter", "222")
                    holder.txtCost.text = ("₽" + model.getCostV())

                    holder.txtLocation.text = model.getLocationV()

                    val lengthInfo = holder.txtInfo.text.length
                    if (lengthInfo < 40) holder.txtInfo.text =
                        model.getInfoV().substring(0, 40).replaceRange(37, 40, "...")
                    else holder.txtInfo.text = model.getInfoV()

                    Picasso.get().load(model.getImage()).into(holder.imageView)

                    cost = ("₽" + model.getCostV())
                    location = (model.getLocationV())
                    room = (model.getRoomsV())
                    square = (model.getSquareV())
                    info = (model.getInfoV())
                    number = (model.getNumberV())
                    image = (model.getImage())

                    holder.itemView.setOnClickListener {
                        val IntentInfoAd = Intent(this@MainActivity, InfoMenuAd::class.java)
                        IntentInfoAd.putExtra("pid", model.getPid())
                        startActivity(IntentInfoAd)
                    }


                }

                @NotNull
                override fun onCreateViewHolder(
                    @NotNull parent: ViewGroup,
                    viewType: Int
                ): HouseViewHolder {
                    Log.v("viewholder", "222")
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.house_image_layout, parent, false)
                    return HouseViewHolder(view)
                }
            }
        recyclerView!!.adapter = adapter
        adapter.startListening()
    }


}