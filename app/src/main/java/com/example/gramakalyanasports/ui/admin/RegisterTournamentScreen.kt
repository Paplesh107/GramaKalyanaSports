package com.example.gramakalyanasports.ui.admin

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramakalyanasports.data.Tournament
import com.example.gramakalyanasports.data.TournamentRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterTournamentScreen(
    initialUsername: String = "",
    initialEmail: String = "",
    onRegistrationSuccess: (String) -> Unit,
    onBack: () -> Unit
) {

    var username by remember { mutableStateOf(initialUsername) }
    var email by remember { mutableStateOf(initialEmail) }
    var tournamentName by remember { mutableStateOf("") }

    var gameType by remember {
        mutableStateOf("Cricket")
    }

    val now = Calendar.getInstance()

    var date by remember {
        mutableStateOf(
            "${now.get(Calendar.DAY_OF_MONTH)}/${now.get(Calendar.MONTH)+1}/${now.get(Calendar.YEAR)}"
        )
    }

    var time by remember {
        mutableStateOf("10:00 AM")
    }

    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    var dropdownExpanded by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _, y, m, d ->

            date = "$d/${m+1}/$y"

        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val timePickerDialog = TimePickerDialog(
        context,
        { _, h, min ->

            val ap = if(h < 12) "AM" else "PM"

            val hh =
                if(h % 12 == 0) 12
                else h % 12

            time = String.format(
                Locale.getDefault(),
                "%02d:%02d %s",
                hh,
                min,
                ap
            )

        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        false
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "REGISTER TOURNAMENT",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF1B5E20)
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },

                label = {
                    Text("Username")
                },

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },

                label = {
                    Text("Email")
                },

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = tournamentName,
                onValueChange = { tournamentName = it },

                label = {
                    Text("Tournament Name")
                },

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(
                expanded = dropdownExpanded,

                onExpandedChange = {
                    dropdownExpanded = !dropdownExpanded
                }
            ) {

                OutlinedTextField(
                    value = gameType,
                    onValueChange = {},

                    readOnly = true,

                    label = {
                        Text("Select Sport")
                    },

                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = dropdownExpanded
                        )
                    },

                    modifier = Modifier
                        .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                        .fillMaxWidth(),

                    shape = RoundedCornerShape(16.dp)
                )

                ExposedDropdownMenu(
                    expanded = dropdownExpanded,

                    onDismissRequest = {
                        dropdownExpanded = false
                    }
                ) {

                    listOf(
                        "Cricket",
                        "Football",
                        "Kabaddi",
                        "Kho Kho",
                        "Volleyball",
                        "Badminton",
                        "Throwball"
                    ).forEach { sport ->

                        DropdownMenuItem(
                            text = {
                                Text(sport)
                            },

                            onClick = {

                                gameType = sport
                                dropdownExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                OutlinedTextField(
                    value = date,
                    onValueChange = {},

                    modifier = Modifier.weight(1f),

                    label = {
                        Text("Date")
                    },

                    readOnly = true,

                    trailingIcon = {

                        IconButton(
                            onClick = {
                                datePickerDialog.show()
                            }
                        ) {

                            Icon(
                                Icons.Default.DateRange,
                                null
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.width(10.dp))

                OutlinedTextField(
                    value = time,
                    onValueChange = {},

                    modifier = Modifier.weight(1f),

                    label = {
                        Text("Time")
                    },

                    readOnly = true,

                    trailingIcon = {

                        Text(
                            "🕒",
                            fontSize = 22.sp,
                            modifier = Modifier.clickable {
                                timePickerDialog.show()
                            }
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },

                label = {
                    Text("Password")
                },

                modifier = Modifier.fillMaxWidth(),

                visualTransformation =
                    if(passwordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),

                trailingIcon = {

                    IconButton(
                        onClick = {
                            passwordVisible = !passwordVisible
                        }
                    ) {

                        Icon(
                            if(passwordVisible)
                                Icons.Default.Visibility
                            else
                                Icons.Default.VisibilityOff,

                            null
                        )
                    }
                },

                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },

                label = {
                    Text("Confirm Password")
                },

                modifier = Modifier.fillMaxWidth(),

                visualTransformation =
                    if(confirmPasswordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),

                trailingIcon = {

                    IconButton(
                        onClick = {
                            confirmPasswordVisible =
                                !confirmPasswordVisible
                        }
                    ) {

                        Icon(
                            if(confirmPasswordVisible)
                                Icons.Default.Visibility
                            else
                                Icons.Default.VisibilityOff,

                            null
                        )
                    }
                },

                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            if(isLoading){

                CircularProgressIndicator()

            } else {

                Button(
                    onClick = {

                        if(
                            tournamentName.isBlank()
                            ||
                            password.isBlank()
                        ){

                            Toast.makeText(
                                context,
                                "Fill all fields",
                                Toast.LENGTH_SHORT
                            ).show()

                        }

                        else if(password != confirmPassword){

                            Toast.makeText(
                                context,
                                "Passwords do not match",
                                Toast.LENGTH_SHORT
                            ).show()

                        }

                        else {

                            isLoading = true

                            scope.launch {

                                delay(10000)

                                if(isLoading){

                                    isLoading = false

                                    Toast.makeText(
                                        context,
                                        "Connection Timeout",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            val tournament = Tournament(
                                name = tournamentName,
                                type = gameType,
                                date = date,
                                time = time,
                                leaderUsername = username,
                                leaderEmail = email
                            )

                            TournamentRepository.registerTournament(
                                tournament,
                                password
                            ) { success ->

                                isLoading = false

                                if(success){

                                    Toast.makeText(
                                        context,
                                        "Tournament Registered",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    onRegistrationSuccess(
                                        tournamentName
                                    )

                                } else {

                                    Toast.makeText(
                                        context,
                                        "Tournament Already Exists",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
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
                        "REGISTER",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    "BACK",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}