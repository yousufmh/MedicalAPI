package com.gulfunion.medicalapi

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional


@Repository
@Transactional
class MedicalAPIDAO {
    @Autowired
    private val jdbcTemplate: JdbcTemplate? = null

    fun findUser(id: String): MutableMap<String, Any>? {
        var query = """
            
        """.trimIndent()

        return try {

            jdbcTemplate?.queryForMap(query, id)

        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }

    fun getCCHIStatus(id: String):MutableMap<String,Any>?{

        val query = """
            """.trimIndent()

        return try {
            jdbcTemplate?.queryForMap(query,id)
        }catch (e: EmptyResultDataAccessException){
            null
        }
    }

    fun providerList(id: String): List<MutableMap<String, Any>>? {
        val query = """
            """.trimIndent()

        return try {

            jdbcTemplate?.queryForList(query, id)

        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }


    fun medicalApprovalList(id: String): List<MutableMap<String, Any>>? {
        val query = """
            """.trimIndent()

        return try {

            jdbcTemplate?.queryForList(query, id)

        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }


    fun medicalClaimList(id: String): List<MutableMap<String, Any>>? {
        val query = """
        """.trimIndent()

        return try {

            jdbcTemplate?.queryForList(query, id)

        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }


    fun beniftList(id: String): List<MutableMap<String, Any>>? {
        val query = """
            """.trimIndent()

        return try {
            jdbcTemplate?.queryForList(query, id)

        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }


    fun certificate(id: String): List<MutableMap<String, Any>>? {
        val query = """
            """.trimIndent()

        return try {
            jdbcTemplate?.queryForList(query, id)

        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }

    fun createUser(user: User): Number {
        user.GMCL_Username = user.GMCL_Username.toLowerCase()
        val insertActor = SimpleJdbcInsert(jdbcTemplate!!)
        insertActor.withSchemaName("").withTableName("")
                .usingGeneratedKeyColumns("")
        val param = BeanPropertySqlParameterSource(user)
        return insertActor.executeAndReturnKey(param)

    }

    fun createToken(token: VerificationToken): Number {
        token.GMCV_USERID = token.GMCV_USERID.toLowerCase()
        val insertActor = SimpleJdbcInsert(jdbcTemplate!!)
        insertActor.withSchemaName("").withTableName("")
                .usingGeneratedKeyColumns("")
        val param = BeanPropertySqlParameterSource(token)
        return insertActor.executeAndReturnKey(param)
    }

    fun validateUser(id: String, dob: String = ""): MutableMap<String, Any>? {
        var queryCount = """
            """.trimIndent()
        return try {
            if (dob != "") {
                queryCount = "$queryCount  "
                jdbcTemplate?.queryForMap(queryCount, id, dob)
            } else {
                jdbcTemplate?.queryForMap(queryCount, id)

            }
        } catch (e: EmptyResultDataAccessException) {
            null
        }

    }

    fun verifyUser(user: User): MutableMap<String, Any>? {

//
        return try {
//            if (user.GMCL_NationalID != "") {
               val querycount = """
            
        """.trimIndent()
                jdbcTemplate?.queryForMap(querycount, user.GMCL_NationalID)

//            } else {
//            }
        } catch (e: EmptyResultDataAccessException) {
            val mutableMapOf = mutableMapOf<String, Any>()
            mutableMapOf
        }


    }

    fun getUser(user: User): MutableMap<String, Any>? {

        val querycount = """
           
        """.trimIndent()

        return try {
            jdbcTemplate?.queryForMap(querycount, user.GMCL_NationalID)
        } catch (e: EmptyResultDataAccessException) {
            null
        }


    }

    fun getToken(token: VerificationToken): MutableMap<String, Any>? {

        val querycount = """
           
        """.trimIndent()

        return try {
            jdbcTemplate?.queryForMap(querycount, token.GMCV_TOKEN)
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }

    fun updateUser(user: User):Int?{

        val querycount = """
           
        """.trimIndent()

        return try {
            jdbcTemplate?.update(querycount,
                    user.GMCL_Password,
                    user.GMCL_EMAIL,
                    user.GMCL_VERFIED,
                    user.GMCL_NationalID)
        }catch (e: EmptyResultDataAccessException){
            null
        }


    }

    fun checkDependents(user: User):MutableMap<String,Any>?{
        val query = """
            
        """.trimIndent()

        return try {
            jdbcTemplate?.queryForMap(query,user.GMCL_NationalID)
        }catch (e: EmptyResultDataAccessException){
            null
        }
    }

    fun getDependents(user: User):List<MutableMap<String, Any>>?{

        val query = """
            
        """.trimIndent()


        return try {
            jdbcTemplate?.queryForList(query,user.GMCL_NationalID)
        }catch (e: EmptyResultDataAccessException){
            null
        }

    }

}

