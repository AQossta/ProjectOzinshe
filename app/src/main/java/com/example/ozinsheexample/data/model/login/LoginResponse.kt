package com.example.ozinsheexample.data.model.login


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id")
    val id: Int, // 25897
    @SerializedName("username")
    val username: String, // aqwertghjhgv5@gmail.com
    @SerializedName("email")
    val email: String, // aqwertghjhgv5@gmail.com
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("accessToken")
    val accessToken: String, // eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhcXdlcnRnaGpoZ3Y1QGdtYWlsLmNvbSIsImlhdCI6MTczOTEwODI2NCwiZXhwIjoxNzcwNjQ0MjY0fQ.QZJm20VjJ1LN46Ybp9x-M-aPSXppN4Xr01f2np-EplX-c1P6EgYmTnmhwFh7T5Jl3FtqvobkaSznbQBCzcKPDw
    @SerializedName("tokenType")
    val tokenType: String // Bearer
) {
    companion object
}
