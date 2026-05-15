package com.example.gramakalyanasports.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameSelectionScreen(
    tournamentName: String,
    onGameSelected: (String) -> Unit,
    onBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Select Sport",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = tournamentName,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(40.dp))

        // CRICKET
        Button(
            onClick = { onGameSelected("Cricket") },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50)
            )
        ) {

            Text(
                "🏏 CRICKET",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // FOOTBALL
        Button(
            onClick = { onGameSelected("Football") },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            )
        ) {

            Text(
                "⚽ FOOTBALL",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // KABADDI
        Button(
            onClick = { onGameSelected("Kabaddi") },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF9800)
            )
        ) {

            Text(
                "🤼 KABADDI",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // KHO KHO
        Button(
            onClick = { onGameSelected("Kho Kho") },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF9C27B0)
            )
        ) {

            Text(
                "🏃 KHO KHO",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // VOLLEYBALL
        Button(
            onClick = { onGameSelected("Volleyball") },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE91E63)
            )
        ) {

            Text(
                "🏐 VOLLEYBALL",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // BADMINTON
        Button(
            onClick = { onGameSelected("Badminton") },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF795548)
            )
        ) {

            Text(
                "🏸 BADMINTON",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // THROWBALL
        Button(
            onClick = { onGameSelected("Throwball") },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF607D8B)
            )
        ) {

            Text(
                "🥎 THROWBALL",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                "BACK",
                fontWeight = FontWeight.Bold
            )
        }
    }
}