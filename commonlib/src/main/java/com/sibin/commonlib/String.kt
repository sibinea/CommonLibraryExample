package com.sibin.commonlib

import java.security.MessageDigest
import java.util.*

const val DEFAULT_PASSWORD_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%]).{6,20})"


fun String.Companion.empty() = ""


fun String.isPhone(): Boolean {
    val p = "^((?=.*[0-9]).{4,15})\$".toRegex()
    return matches(p)
}


fun String.isEmail(): Boolean {
    val p = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)\$".toRegex()
    return matches(p)
}


fun String.isValidPassword(regex: String = DEFAULT_PASSWORD_REGEX): Boolean {
    val p = "^$regex\$".toRegex()
    return matches(p)
}
//((?=.*[a-zA-Z0-9]).{6,20})


fun String.isNumeric(): Boolean {
    val p = "^[0-9]+$".toRegex()
    return matches(p)
}


fun String.equalsIgnoreCase(other: String) =
    this.toLowerCase(Locale.ROOT).contentEquals(other.toLowerCase(Locale.ROOT))

fun String.toSha512(): String {
    return MessageDigest
        .getInstance("SHA-512")
        .digest(this.toByteArray())
        .fold("", { str, it -> str + "%02x".format(it) })
}