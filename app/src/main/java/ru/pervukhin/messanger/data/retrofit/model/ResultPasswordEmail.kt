package ru.pervukhin.messanger.data.retrofit.model

import com.google.gson.annotations.SerializedName
import ru.pervukhin.messanger.domain.Profile

class ResultPasswordEmail(@SerializedName("result")var result: String, @SerializedName("profile")var profile: Profile) {
}