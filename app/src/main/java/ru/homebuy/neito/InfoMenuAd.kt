package ru.homebuy.neito

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.annotations.NotNull
import com.squareup.picasso.Picasso



class InfoMenuAd : AppCompatActivity() {

    private var imageInfo: ImageView?=null
    private var costInfo: TextView?=null
    private var locationInfo: TextView? =null
    private var roomInfo: TextView?=null
    private var squareInfo: TextView?=null
    private var infoInfo: TextView?=null
    private var numberInfo: TextView?=null
    private var numberHides: String?=null
    private var imageUrl: String ?= null

    var HouseReference: DatabaseReference? = null
    private var recyclerView: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_menu_ad)
        HouseReference = FirebaseDatabase.getInstance().reference.child("Houses")

        init()
        getIntentMain()
    }

    /*override fun onStart() {
        super.onStart()


        val options = FirebaseRecyclerOptions.Builder<Houses>()
            .setQuery(HouseReference!!, Houses::class.java).build()

        val adapter: FirebaseRecyclerAdapter<Houses, HouseInfoHolder> =
            object : FirebaseRecyclerAdapter<Houses, HouseInfoHolder>(options) {
                @SuppressLint("SetTextI18n")
                override fun onBindViewHolder(@NotNull holder: HouseInfoHolder, i: Int, @NotNull model: Houses) {
                    Log.v("adapter","222")
                    holder.txtCost.text = ("₽"+ model.getCostV())
                    holder.txtLocation.text = model.getLocationV()
                    holder.txtRoom.text = model.getRoomsV()
                    holder.txtSquare.text = model.getSquareV()
                    holder.txtInfo.text = model.getInfoV()
                    holder.txtNumber.text = model.getNumber()
                    Picasso.get().load(model.getImage()).into(holder.imageView)
                }
                @NotNull
                override fun onCreateViewHolder(@NotNull parent: ViewGroup, viewType: Int): HouseInfoHolder {
                    Log.v("viewholder","222")
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.activity_info_menu_ad, parent, false)
                    return HouseInfoHolder(view)
                }
            }
        adapter.startListening()
    }*/

    private fun init() {
        imageInfo= findViewById(R.id.select_home_image)
        costInfo= findViewById(R.id.costMenuHouseAd)
        locationInfo= findViewById(R.id.locationMenuHouseAd)
        roomInfo= findViewById(R.id.bedroomMenuAdValue)
        squareInfo= findViewById(R.id.squareMenuAdValue)
        infoInfo= findViewById(R.id.infoMenuHouseAd)
        numberInfo= findViewById(R.id.numberHide)

    }

    private fun getIntentMain() {
        val IntentInfoAd = intent
        if (IntentInfoAd != null) {
            costInfo?.text = IntentInfoAd.getStringExtra(Constant.COST)
            locationInfo?.text = IntentInfoAd.getStringExtra(Constant.LOCATION)
            roomInfo?.text = IntentInfoAd.getStringExtra(Constant.ROOM)
            squareInfo?.text = IntentInfoAd.getStringExtra(Constant.SQUARE)
            infoInfo?.text = IntentInfoAd.getStringExtra(Constant.INFO)
            numberHides = IntentInfoAd.getStringExtra(Constant.NUMBER)
            imageUrl = IntentInfoAd.getStringExtra(Constant.IMAGE)
            Picasso.get().load(imageUrl).into(imageInfo)
        }
    }

    fun seekNumber(view: View){
        numberInfo?.text = numberHides.toString()
    }


}