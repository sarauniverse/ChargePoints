package com.cariad.stations.models.data

import com.google.gson.annotations.SerializedName

data class ChargePoint(
    @SerializedName("ID") val id: Long,
    @SerializedName("UsageType") val usageType: UsageType,
    @SerializedName("OperatorInfo") val operatorInfo: OperatorInfo?,
    @SerializedName("AddressInfo") val addressInfo: AddressInfo,
    @SerializedName("Connections") val connections: List<Connection>
)

data class AddressInfo(
    @SerializedName("ID")  val id: Long,
    @SerializedName("Title") val title: String?,
    @SerializedName("AddressLine1") val addressLine1: String,
    @SerializedName("AddressLine2") val addressLine2: String?,
    @SerializedName("Town") val town: String?,
    @SerializedName("StateOrProvince") val stateOrProvince: String,
    @SerializedName("Postcode") val postCode: String,
    @SerializedName("Country") val country: Country,
    @SerializedName("Latitude") val latitude: Double,
    @SerializedName("Longitude") val longitude: Double,
    @SerializedName("ContactTelephone1") val contactTelephoneNo1: String,
    @SerializedName("ContactTelephone2") val contactTelephoneNo2: String,
    @SerializedName("ContactEmail") val contactEmail: String,
    @SerializedName("Distance") val distance: Double,
    @SerializedName("DistanceUnit") val distanceUnit: Int
)

data class OperatorInfo(
    @SerializedName("ID") val id: Long,
    @SerializedName("Title") val title: String,
    @SerializedName("WebsiteURL") val websiteUrl: String,
    @SerializedName("ContactEmail") val contactEmail: String
)

data class Country(
    @SerializedName("ID") val id: Int,
    @SerializedName("ISOCode") val isoCode: String,
    @SerializedName("ContinentCode") val continentCode: String,
    @SerializedName("Title") val title: String
)

data class UsageType(
    @SerializedName("IsPayAtLocation") val isPayAtLocation: Boolean,
    @SerializedName("IsMembershipRequired") val isMembershipRequired : Boolean,
    @SerializedName("Title") val title: String
)

data class Connection(
    @SerializedName("ID") val id: Long,
    @SerializedName("Amps") val amps: Double,
    @SerializedName("Voltage") val voltage: Double,
    @SerializedName("PowerKW") val power: Double
)