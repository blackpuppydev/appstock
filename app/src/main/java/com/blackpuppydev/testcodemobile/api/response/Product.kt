package com.blackpuppydev.testcodemobile.api.response

data class Product(
    var id:String,
    var title:String,
    var thumbnail:String,
    var price:Float,
    var stock:Int,
    var rating:Float,
    var images:ArrayList<String>,
    var discountPercentage:Float,
    var totalPrice:Float
)