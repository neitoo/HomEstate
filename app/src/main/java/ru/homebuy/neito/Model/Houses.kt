package ru.homebuy.neito.Model

class Houses(_costV: String = "",  _locationV: String = "", _roomsV: String = "", _squareV: String = "",_image: String = "", _infoV: String = "",_pid: String = "",  _date: String = "",_time: String = "", _number:String = "default") {
    private var costV: String = _costV
    private var locationV: String = _locationV
    private var roomsV: String = _roomsV
    private var squareV: String = _squareV
    private var image: String = _image
    private var infoV: String = _infoV
    private var pid: String = _pid
    private var date: String = _date
    private var time: String = _time
    private var numberV: String = _number



    fun getCostV(): String {
        return costV
    }

    fun setCostV(costV: String) {
        this.costV = costV
    }

    fun getLocationV(): String {
        return locationV
    }

    fun setLocationV(locationV: String) {
        this.locationV = locationV
    }

    fun getRoomsV(): String {
        return roomsV
    }

    fun setRoomsV(roomsV: String) {
        this.roomsV = roomsV
    }

    fun getSquareV(): String {
        return squareV
    }

    fun setSquareV(squareV: String) {
        this.squareV = squareV
    }

    fun getImage(): String {
        return image
    }

    fun setImage(image: String?) {
        this.image = image!!
    }

    fun getInfoV(): String {
        return infoV
    }

    fun setInfoV(infoV: String) {
        this.infoV = infoV
    }

    fun getPut(): String {
        return pid
    }

    fun setPut(pid: String) {
        this.pid = pid
    }

    fun getDate(): String {
        return date
    }

    fun setDate(date: String) {
        this.date = date
    }

    fun getTime(): String {
        return time
    }

    fun setTime(date: String) {
        this.time = date
    }

    fun getNumberV(): String {
        return numberV
    }

    fun setNumberV(number: String) {
        this.numberV = number
    }
}
