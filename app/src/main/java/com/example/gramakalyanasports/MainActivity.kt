package com.example.gramakalyanasports

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.gramakalyanasports.ui.admin.AdminDashboardScreen
import com.example.gramakalyanasports.ui.home.AppHome
import com.example.gramakalyanasports.ui.login.LoginScreen
import com.example.gramakalyanasports.ui.navigation.Screen
import com.example.gramakalyanasports.ui.theme.GramaKalyanaSportsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            GramaKalyanaSportsTheme {

                var currentScreen by remember {
                    mutableStateOf("login")
                }

                var username by remember {
                    mutableStateOf("")
                }

                var email by remember {
                    mutableStateOf("")
                }

                var selectedTournament by remember {
                    mutableStateOf("")
                }

                when(currentScreen){

                    "login" -> {

                        LoginScreen(
                            onLoginSuccess = { user, mail ->

                                username = user
                                email = mail

                                currentScreen = "home"
                            }
                        )
                    }

                    "home" -> {

                        AppHome(
                            username = username,
                            email = email,

                            onNavigate = { screen ->

                                when(screen){

                                    is Screen.Profile -> {

                                    }

                                    is Screen.RegisterTournament -> {

                                        currentScreen = "admin"
                                    }

                                    else -> {

                                    }
                                }
                            }
                        )
                    }

                    "admin" -> {

                        AdminDashboardScreen(
                            tournamentName = selectedTournament,

                            onManageTournament = { tournament ->

                                selectedTournament = tournament
                            },

                            onBack = {

                                currentScreen = "home"
                            }
                        )
                    }
                }
            }
        }
    }
}