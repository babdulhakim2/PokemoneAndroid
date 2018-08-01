package com.example.abdulhakimbashir.pokemonadroid

/**
 * Created by abdulhakim.bashir on 22/03/2018.
 */

class Pokemon{
    var name:String?=null
    var des:String?=null
    var image:Int?=null
    var power:Double?=null
    var lat:Double?=null
    var log:Double?=null
    var IsCatch:Boolean?=false
    constructor(image:Int, name:String, des:String, power:Double, lat:Double, log:Double){
        this.name = name
        this.des = des
        this.image = image
        this.power = power
        this.lat = lat
        this.log = log
        this.IsCatch = false
    }
}