package com.jetpack.googlesignin.navigation

sealed class Screen(val route:String){
    object Login:Screen(route ="login_screen")
    object Profile:Screen(route = "proile_screen")
}
