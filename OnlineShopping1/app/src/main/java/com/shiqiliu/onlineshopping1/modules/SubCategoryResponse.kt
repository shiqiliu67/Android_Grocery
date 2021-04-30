package com.shiqiliu.onlineshopping1.modules

import java.io.Serializable

data class SubCategoryResponse(
    val count: Int,
    val data: ArrayList<SubCategory>,
    val error: Boolean
): Serializable

data class SubCategory(
    val __v: Int,
    val _id: String,
    val catId: Int,
    val position: Int,
    val status: Boolean,
    val subDescription: String,
    val subId: Int,
    val subImage: String,
    val subName: String
): Serializable