package com.mohamed.medhat.twograndtask.model

import com.google.gson.annotations.SerializedName

data class User(

    @field:SerializedName("website")
    val website: String,

    @field:SerializedName("address")
    val address: Address,

    @field:SerializedName("phone")
    val phone: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("company")
    val company: Company,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("username")
    val username: String
) {
    fun getAddress(): String {
        return address.toString()
    }
}

data class Geo(

    @field:SerializedName("lng")
    val lng: String,

    @field:SerializedName("lat")
    val lat: String
)

data class Company(

    @field:SerializedName("bs")
    val bs: String,

    @field:SerializedName("catchPhrase")
    val catchPhrase: String,

    @field:SerializedName("name")
    val name: String
)

data class Address(

    @field:SerializedName("zipcode")
    val zipcode: String,

    @field:SerializedName("geo")
    val geo: Geo,

    @field:SerializedName("suite")
    val suite: String,

    @field:SerializedName("city")
    val city: String,

    @field:SerializedName("street")
    val street: String
) {
    override fun toString(): String {
        return "$street, $suite, $city, $zipcode"
    }
}
