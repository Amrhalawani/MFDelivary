package com.aks.shagra.data.local

import android.content.Context


object SharedPref {

    lateinit var context: Context
    fun AddMainContext(context: Context) {
        this.context = context
    }

    private const val APP_VALUES = "app_val"


    //=========================================== first run =============================================
    private const val FIRST_RUN_KEY = "first_run"

    fun setFirstRunValuetoFalse(context: Context) {

        //true means yes its the first run
        context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE).edit().putBoolean(
            FIRST_RUN_KEY, false
        ).apply()
    }

    fun isItTheFirstRun(context: Context): Boolean {

        return context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE).getBoolean(
            FIRST_RUN_KEY, true
        )

    }

    //======================================= Recently Searched Items ==========================================

    private const val his = "his"
    fun setRecentlySearchedItems(context: Context, set: Set<String>) {
        context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE)
            .edit()
            .putStringSet(his, set)
            .apply()
    }

    fun getRecentlySearchedItems(context: Context): Set<String>? {

        return context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE).getStringSet(
            his, mutableSetOf()
        )

    }

    //======================================= UserData ==========================================

    /**
     * authentacted ?
     * user name, usertype{user, supplier}, user_id
     */

    private const val username = "userName"

    fun setUserName(context: Context, name: String) {
        context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE)
            .edit()
            .putString(username, name)
            .apply()
    }

    fun getUserName(context: Context): String? {

        return context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE).getString(
            username, ""
        )

    }

    //-------------


    private const val demoAccount = "dem"

    fun setUserDemoAccount(context: Context, name: String) {
        context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE)
            .edit()
            .putString(demoAccount, name)
            .apply()
    }

    fun getUserDemoAccount(): String? {

        return context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE).getString(
            demoAccount, ""
        )

    }

    //-------------------------------------------------
    private const val userType = "userType"
    public const val NORMAL_USER = "0"
    public const val SUPPLIER_USER = "1"

    fun setUserType(context: Context, type: String) {
        context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE)
            .edit()
            .putString(userType, type)
            .apply()
    }

    fun getUserType(context: Context): String? {

        return context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE).getString(
            userType, ""
        )

    }

    //-------------------------------------------------
    private const val accessToken = "acct"

    fun setAccessToken(context: Context, type: String) {
        context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE)
            .edit()
            .putString(accessToken, type)
            .apply()
    }

    fun getAccessToken(/*context: Context*/): String? {

        return "${
            context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE).getString(
                accessToken, ""
            )
        }"

    }

    //-------------------------------------------------
    private const val userId = "userId"

    fun setUserId(context: Context, id: Int) {
        context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE)
            .edit()
            .putInt(userId, id)
            .apply()
    }

    fun getUserId(context: Context): Int? {
        return context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE)
            .getInt(userId, 0)

    }

    //-------------------------------------------------
    private const val auth = "auth"

    fun setAuthState(context: Context, state: Boolean) {
        context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(auth, state)
            .apply()
    }

    fun isUserAuthentacted(context: Context): Boolean {
        return context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE)
            .getBoolean(auth, false)
    }


    //-------------------------------------------------
    private const val DESC = "desc"

    fun setAppDesc(context: Context, str: String) {
        context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE)
            .edit()
            .putString(DESC, str)
            .apply()
    }

    fun getAppDesc(context: Context): String? {

        return context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE).getString(
            DESC, ""
        )

    }

    //-------------------------------------------------
    private const val PHONE = "phone"

    fun setPhone(context: Context, str: String) {
        context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE)
            .edit()
            .putString(PHONE, str)
            .apply()
    }

    fun getPhone(context: Context): String? {

        return context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE).getString(
            PHONE, ""
        )

    }

    //-------------------------------------------------
    private const val THUM = "thumbnail"

    fun setTreeThumbnail(context: Context, str: String) {
        context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE)
            .edit()
            .putString(THUM, str)
            .apply()
    }

    fun getTreeThumbnail(context: Context): String? {

        return context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE).getString(
            THUM, ""
        )

    }

    //--------------------------------------------------
    //==--------------------------
    private val TERMS_COND: String? = "terms"

    fun setTermsToAccepted(context: Context) {
        context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(TERMS_COND, true)
            .apply()
    }

    fun isUserAcceptTheTerms(context: Context): Boolean? {
        return context.getSharedPreferences(APP_VALUES, Context.MODE_PRIVATE)
            .getBoolean(TERMS_COND, false)
    }


}