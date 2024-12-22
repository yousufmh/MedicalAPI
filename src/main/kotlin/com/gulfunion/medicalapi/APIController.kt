package com.gulfunion.medicalapi

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.util.*


@RestController
@RequestMapping("medical-api")
class APIController {

    @Autowired
    val rep:MedicalAPIDAO? = null


    @RequestMapping("finduser",produces = ["application/JSON"])
    fun findUser(@RequestParam id:String): MutableMap<String, Any>? {

        val result = rep?.findUser(id)
        return result
    }

    @RequestMapping("getcchi",produces = ["application/JSON"])
    fun getCCHIStatus(@RequestParam id:String): MutableMap<String, Any>? {

        val result = rep?.getCCHIStatus(id)
        return result
    }


    @RequestMapping("validateuser",produces = ["application/JSON"])
    fun validateUser(@RequestParam id:String,
                     @RequestParam(required = false,defaultValue = "") dob:String
                    ): MutableMap<String, Any>? {

        val result = rep?.validateUser(id,dob)
        val check = result?.get("COUNT") as Number
        return if (check == 1.toBigDecimal()) {
            result["status"] = 200
            result
        }else {
            result["status"] = 404
            result
        }
    }

    @RequestMapping("checkDependents",produces = ["application/JSON"])
    fun checkDependents(@RequestParam id:String):MutableMap<String, Any>?{
        val user = User(GMCL_NationalID = id)
        val result = rep?.checkDependents(user)
        val check = result?.get("COUNT") as BigDecimal

        return if (check.toInt() > 1) {
            result["status"] = 200
            result
        }else {
            result["status"] = 404
            result
        }
    }



    @RequestMapping("createuser",method = [RequestMethod.POST])
    fun createUser(@RequestBody user:User): MutableMap<String, Any?> {
        val check = rep?.verifyUser(user)?.get("COUNT") as Number
        val response = mutableMapOf<String,Any?>()
        return if(check == 0.toBigDecimal()) {
            val id = rep?.createUser(user)
            response["status"] = 200
            response
        }else{
            response["status"] = 500
            response
        }

    }

    @RequestMapping("emailtoken",method = [RequestMethod.POST])
    fun emailToken(@RequestBody token: VerificationToken): MutableMap<String, Any?> {
        val response = mutableMapOf<String,Any?>()

            val id = rep?.createToken(token)
            return if (id !=null) {
                response["status"] = 200
                response
            }
            else {
                response["status"] = 500
                response
            }

    }

    @RequestMapping("login", method = [RequestMethod.POST],produces = ["application/JSON"])
    fun verify(@RequestBody user:User): MutableMap<String, Any?> {

        val verifiy = rep?.verifyUser(user)
        val response = mutableMapOf<String,Any?>()

        val check = verifiy?.get("COUNT") as Number
        return if (check == 1.toBigDecimal()){
            val userInfo = rep?.getUser(user)
            val id = userInfo?.get("GMCL_NATIONALID") as String
            val emailVerfied = userInfo?.get("GMCL_VERFIED") as String
            val user = rep?.validateUser(id)
            val check = user?.get("count") as Number == 1.toBigDecimal()
            val verifiedEmail =  emailVerfied == "T"
            if(check) {
                if (verifiedEmail) {
                    val getuser = rep?.findUser(userInfo?.get("GMCL_NATIONALID") as String)
                    response["status"] = 200
                    response["loginInfo"] = userInfo
                    response["user"] = getuser
                    response
                }else{
                    response["status"] = 402
                    response["loginInfo"] = userInfo
                    response
                }
            }
            else {
                response["status"] = 404
                response
            }
        }else {
            response["status"] = 401
            response
        }
    }


    @RequestMapping("getUser")
    fun getUserInfo(@RequestBody user:User): MutableMap<String, Any?>{
        val response = mutableMapOf<String,Any?>()
        val userInfo = rep?.getUser(user)
        val result = rep?.findUser(user.GMCL_NationalID)
        return if(userInfo!=null){
            response["status"] = 200
            response["loginInfo"] = userInfo
            response["user"] = result
            response
        }else{
            response["status"] = 404
            response
        }

    }

    @RequestMapping("getDependents",produces = ["application/JSON"])
    fun getDependents(@RequestParam id:String): List<MutableMap<String, Any>>? {

//        val response = mutableMapOf<String,Any?>()
        return rep?.getDependents(User(GMCL_NationalID = id))
//        return if(dependentsInfo!=null){
//            response["status"] = 200
//            response["dependentsInfo"] = dependentsInfo
//            response
//        }else{
//            response["status"] = 404
//            response
//        }

    }

    @RequestMapping("updateUser", method = [RequestMethod.POST],produces = ["application/JSON"])
    fun userUpdate(@RequestBody user: User):MutableMap<String, Any?> {
        val response = mutableMapOf<String,Any?>()
        val updatedCount = rep?.updateUser(user)
        if(updatedCount==0){
            response["status"] =500
        }
        else{
            response["status"] =200
        }
        return response
    }

    @RequestMapping("verifyToken", method = [RequestMethod.POST],produces = ["application/JSON"])
    fun verifyToken(@RequestBody token: VerificationToken): MutableMap<String, Any?> {

        val verifiedToken = rep?.getToken(token)
        val response = mutableMapOf<String,Any?>()
        return if (verifiedToken!=null){

            val createDate = verifiedToken["GMCV_CREATE_DT"] as Date
            val userID = verifiedToken["GMCV_USERID"] as String
            val currentDate = Date()
            val timeDifference = currentDate.time - createDate.time
            val differenceInDays = ((timeDifference/(1000 * 60 * 60 * 24)) % 365)

            if (differenceInDays == 0L) {
                val userInfo = rep?.getUser(User(GMCL_NationalID = userID))
                response["status"] = 200
                response["loginInfo"] = userInfo
                response
            }else{
                response["status"] = 400
                response
            }

        }else{
            response["status"] = 404
            response
        }

    }


    @RequestMapping("provider-list",produces = ["application/JSON"])
    fun providerList(@RequestParam id:String): List<MutableMap<String, Any>>? {

        val result = rep?.providerList(id)
        return result
    }

    @RequestMapping("benfit-list",produces = ["application/JSON"])
    fun benfitList(@RequestParam id:String): List<MutableMap<String, Any>>? {

        val result = rep?.beniftList(id)
        return result
    }


    @RequestMapping("certificate",produces = ["application/JSON"])
    fun certificate(@RequestParam id:String): List<MutableMap<String, Any>>? {

        val result = rep?.certificate(id)
        return result
    }


    @RequestMapping("medical-approval",produces = ["application/JSON"])
    fun medicalApproval(@RequestParam id:String): List<MutableMap<String, Any>>? {

        val result = rep?.medicalApprovalList(id)
        return result
    }


    @RequestMapping("medical-claim",produces = ["application/JSON"])
    fun medicalClaim(@RequestParam id:String): List<MutableMap<String, Any>>? {

        val result = rep?.medicalClaimList(id)
        return result
    }


}