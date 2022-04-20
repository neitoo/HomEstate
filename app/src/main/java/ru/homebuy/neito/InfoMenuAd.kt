package ru.homebuy.neito

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import ru.homebuy.neito.Model.Houses


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

    private var HouseID: String = ""

    var HouseReference: DatabaseReference? = null
    private var recyclerView: RecyclerView? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_menu_ad)


        init()
        getIntentMain(HouseID)
        numberInfo?.setOnClickListener {
            numberInfo?.text = numberHides.toString()
        }
    }



    private fun init() {
        imageInfo= findViewById(R.id.select_home_image)
        costInfo= findViewById(R.id.costMenuHouseAd)
        locationInfo= findViewById(R.id.locationMenuHouseAd)
        roomInfo= findViewById(R.id.bedroomMenuAdValue)
        squareInfo= findViewById(R.id.squareMenuAdValue)
        infoInfo= findViewById(R.id.infoMenuHouseAd)
        numberInfo= findViewById(R.id.numberHide)

        val IntentInfoAd = intent
        HouseID = IntentInfoAd.getStringExtra("pid")!!

    }

    private fun getIntentMain(HouseId:String) {
        HouseReference = FirebaseDatabase.getInstance().reference.child("Houses")

        HouseReference!!.child(HouseId).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val houses: Houses = snapshot.getValue(Houses::class.java)!!
                    costInfo?.text = "₽"+houses.getCostV()
                    locationInfo?.text = (houses.getLocationV())
                    roomInfo?.text = houses.getRoomsV()
                    squareInfo?.text = houses.getSquareV()
                    infoInfo?.text = houses.getInfoV()
                    numberHides = houses.getNumberV()
                    imageUrl = houses.getImage()
                    Picasso.get().load(houses.getImage()).into(imageInfo)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }


}