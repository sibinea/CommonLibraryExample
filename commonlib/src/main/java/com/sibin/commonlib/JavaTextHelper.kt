package com.sibin.commonlib

class JavaTextHelper {
    companion object {

        @JvmStatic
        fun empty() = String.empty()

        @JvmStatic
        fun isEmail(string: String): Boolean {
            return string.isEmail()
        }

        @JvmStatic
        fun isPhone(string: String): Boolean {
            return string.isPhone()
        }

        @JvmStatic
        fun isValidPassword(string: String): Boolean {
            return string.isValidPassword()
        }

        @JvmStatic
        fun isValidPassword(string: String, regex: String): Boolean {
            return string.isValidPassword(regex)
        }

        @JvmStatic
        fun isNumeric(string: String): Boolean {
            return string.isNumeric()
        }

        @JvmStatic
        fun equalsIgnoreCase(string: String, other: String): Boolean {
            return string.equalsIgnoreCase(other)
        }

        @JvmStatic
        fun toSha512(string: String): String {
            return string.toSha512()
        }

    }
}