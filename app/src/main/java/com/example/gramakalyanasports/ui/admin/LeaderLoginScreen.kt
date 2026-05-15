package com.example.gramakalyanasports.ui.admin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramakalyanasports.data.TournamentRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderLoginScreen(
    onLoginSuccess: (String) -> Unit,
    onBack: () -> Unit
) {

    var tournamentName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "ADMIN LOGIN",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF1B5E20)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Manage tournaments and sports",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Tournament Name
        OutlinedTextField(
            value = tournamentName,
            onValueChange = { tournamentName = it },

            label = {
                Text("Tournament Name")
            },

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(16.dp),

            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },

            label = {
                Text("Admin Email")
            },

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(16.dp),

            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },

            label = {
                Text("Password")
            },

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(16.dp),

            singleLine = true,

            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(40.dp))

        if (isLoading) {

            CircularProgressIndicator(
                color = Color(0xFF1B5E20)
            )

        } else {

            Button(
                onClick = {

                    // SIMPLE ADMIN LOGIN
                    if (
                        email == ""
                        &&
                        password == ""
                    ) {

                        Toast.makeText(
                            context,
                            "Admin Login Successful",
                            Toast.LENGTH_SHORT
                        ).show()

                        onLoginSuccess(
                            tournamentName.ifEmpty {
                                "Admin Tournament"
                            }
                        )

                        return@Button
                    }

                    // Tournament Leader Login
                    isLoading = true

                    TournamentRepository.verifyTournamentLeader(
                        tournamentName,
                        email,
                        password
                    ) { success ->

                        isLoading = false

                        if (success) {

                            onLoginSuccess(tournamentName)

                        } else {

                            Toast.makeText(
                                context,
                                "Invalid Credentials",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),

                shape = RoundedCornerShape(16.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1B5E20)
                )
            ) {

                Text(
                    "LOGIN",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = onBack
        ) {

            Text(
                "BACK",
                color = Color.Gray
            )
        }
    }
}
