package com.gulfunion.medicalapi

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class User(

        val  _ID:Int = 0,
        @JsonProperty(" _NATIONALID")
        val  _NationalID:String = "",
        @JsonProperty(" _USERNAME")
        var  _Username:String = "",
        @JsonProperty(" _PASSWORD")
        val  _Password:String = "",
        @JsonProperty(" _EMAIL")
        val  _EMAIL:String = "",
        @JsonProperty(" _VERFIED")
        var  _VERFIED:String = "",
        @JsonProperty(" _HAS_DEPENDENTS")
        var  _HAS_DEPENDENTS:String = "",
        @JsonProperty(" _CREATE_USER")
        val  _Create_User:String = "SpringBoot Medical Client App",
        @JsonProperty(" _CREATE_DATE")
        val  _CREATE_DATE: Date = Date(),
        @JsonProperty(" _UPDATE_USER")
        val  _UPDATE_USER: String? = "",
        @JsonProperty(" _UPDATE_DATE")
        val  _UPDATE_DATE: Date? = Date()
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class VerificationToken(
        @JsonProperty(" ID")
        val  ID:Int = 0,
        @JsonProperty(" TOKEN")
        var  TOKEN:String = "",
        @JsonProperty(" USERID")
        var  USERID:String = "",
        @JsonProperty(" EMAIL_OR_PASSWORD")
        var  EMAIL_OR_PASSWORD:String = "",
        @JsonProperty(" CREATE_USER")
        var  CREATE_USER:String = "SpringBoot Medical Client App",
        @JsonProperty(" CREATE_DT")
        var  CREATE_DT:Date = Date(),
        @JsonProperty(" UPDATE_USER")
        var  UPDATE_USER: String? = null,
        @JsonProperty(" UPDATE_DT")
        var  UPDATE_DT: Date?= null
)
