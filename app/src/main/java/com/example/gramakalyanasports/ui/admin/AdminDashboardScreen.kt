package com.example.gramakalyanasports.ui.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.SportsCricket
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gramakalyanasports.data.TournamentRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(
    tournamentName: String,
    onManageTournament: (String) -> Unit,
    onBack: () -> Unit
) {

    var tournaments by remember {
        mutableStateOf(
            listOf<String>()
        )
    }

    LaunchedEffect(Unit) {

        TournamentRepository.initialize()

        TournamentRepository.tournaments.collect { list ->

            tournaments = list.map {
                it.name
            }
        }

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .verticalScroll(rememberScrollState())
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Icon(
            Icons.Default.SportsCricket,
            contentDescription = null,
            tint = Color(0xFF1B5E20),
            modifier = Modifier.size(70.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "ADMIN DASHBOARD",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF1B5E20)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = tournamentName,
            fontSize = 18.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { },

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            shape = RoundedCornerShape(16.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50)
            )
        ) {

            Text(
                "VIEW USERS",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Button(
            onClick = { },

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            shape = RoundedCornerShape(16.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            )
        ) {

            Text(
                "VIEW MATCHES",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Button(
            onClick = { },

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            shape = RoundedCornerShape(16.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF9800)
            )
        ) {

            Text(
                "VIEW RESULTS",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Button(
            onClick = { },

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            shape = RoundedCornerShape(16.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF9C27B0)
            )
        ) {

            Text(
                "LIVE MATCH MONITORING",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "ALL TOURNAMENTS",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        tournaments.forEach { tournament ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),

                shape = RoundedCornerShape(18.dp),

                elevation = CardDefaults.cardElevation(6.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = tournament,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B5E20)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Button(
                            onClick = {
                                onManageTournament(tournament)
                            },

                            shape = RoundedCornerShape(12.dp),

                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF1B5E20)
                            )
                        ) {

                            Text(
                                "MANAGE",
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Button(
                            onClick = {

                                TournamentRepository.deleteTournament(
                                    tournament
                                ) { success ->

                                    if(success){

                                        tournaments =
                                            tournaments.filter {
                                                it != tournament
                                            }
                                    }
                                }
                            },

                            shape = RoundedCornerShape(12.dp),

                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red
                            )
                        ) {

                            Icon(
                                Icons.Default.Delete,
                                contentDescription = null
                            )

                            Spacer(
                                modifier = Modifier.width(6.dp)
                            )

                            Text(
                                "DELETE",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(
            onClick = onBack,

            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),

            shape = RoundedCornerShape(16.dp)
        ) {

            Text(
                "BACK",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}