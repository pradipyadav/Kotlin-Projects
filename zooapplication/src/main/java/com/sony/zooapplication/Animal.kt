package com.sony.zooapplication

class Animal {

    var name:String?=null
    var desc:String?=null
    var img:Int?=null
    var killer:Boolean?=null

    constructor(name:String,desc:String,img:Int,killer:Boolean){
        this.name=name
        this.desc=desc
        this.img=img
        this.killer=killer
    }
}