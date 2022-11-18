package com.harveyhaha.sample.db.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by wtf on 1/13/20 7:57 PM.
 */
@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    @NonNull
    val login: String,
    val id: String,
    val node_id: String,
    val avatar_url: String,
    val url: String,
    val type: String,
    val name: String,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val bio: String?,
    val public_repos: String?,
    val public_gists: String?,
    val followers: String?,
    val following: String?,
    val created_at: String?,
    val updated_at: String?,
    val two_factor_authentication: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeString(id)
        parcel.writeString(node_id)
        parcel.writeString(avatar_url)
        parcel.writeString(url)
        parcel.writeString(type)
        parcel.writeString(name)
        parcel.writeString(company)
        parcel.writeString(blog)
        parcel.writeString(location)
        parcel.writeString(email)
        parcel.writeString(bio)
        parcel.writeString(public_repos)
        parcel.writeString(public_gists)
        parcel.writeString(followers)
        parcel.writeString(following)
        parcel.writeString(created_at)
        parcel.writeString(updated_at)
        parcel.writeByte(if (two_factor_authentication) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserEntity> {
        override fun createFromParcel(parcel: Parcel): UserEntity {
            return UserEntity(parcel)
        }

        override fun newArray(size: Int): Array<UserEntity?> {
            return arrayOfNulls(size)
        }
    }
}