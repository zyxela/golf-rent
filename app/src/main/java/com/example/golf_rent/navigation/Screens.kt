package com.example.golf_rent.navigation

sealed class Screens(var route:String) {
    object MeetingScreen:Screens("meet_screen")
    object AuthorizationScreen:Screens("authorization_screen")
    object RegistrationScreen:Screens("registration_screen")
    object AdminPanelScreen:Screens("admin_panel")
    object CatalogScreen:Screens("catalog_screen")
    object MyRentScreen:Screens("my_rent")
}