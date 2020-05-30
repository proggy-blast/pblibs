package com.pblibs.model

import com.pblibs.utility.PBConstants

/**
 * Created by Proggy Blast on 11/4/20 7:13 PM
 */


data class LoginRequest(
    var userEmail: String? = PBConstants.DEFAULT,
    var password: String? = PBConstants.DEFAULT,
    var mobileNumber: String? = PBConstants.DEFAULT,
    var username: String? = PBConstants.DEFAULT
)