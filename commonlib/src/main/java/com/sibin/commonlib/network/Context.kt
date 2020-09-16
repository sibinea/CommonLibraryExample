package com.sibin.commonlib.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

val Context.connectivityManager: ConnectivityManager?
    get() = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)

@Suppress("DEPRECATION")
fun Context.isConnected(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        cm?.run {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                return when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
                    else -> false

                }
            }
        }

    } else {
        cm?.run {
            cm.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_VPN -> true
                    else -> false
                }
            }
        }
    }
    return false
}
