package com.example.core2

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Location (var place: String, var country: String, var lastvisit: String, var score: String, var id: Int): Parcelable {}
