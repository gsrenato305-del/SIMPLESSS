package com.simplefit.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.simplefit.app.ui.theme.*

@Composable
fun TargetMuscleScreen(navController: NavController) {
    var isFrontSide by remember { mutableStateOf(true) }
    val selectedMuscles = remember { mutableStateListOf("Ombros", "Biceps", "Peito", "Trapézio") }

    val frontMuscles = listOf("Ombros", "Tríceps", "Biceps", "Peito", "Pescoço", "Pernas", "Abdômen")
    val backMuscles = listOf("Panturrilhas", "Trapézio", "Deltoides", "Tríceps", "Biceps", "Quadril")

    val currentMuscles = if (isFrontSide) frontMuscles else backMuscles

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "Músculo Alvo",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            "Selecione o grupo muscular alvo",
            color = TextGray,
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .background(CardDark, RoundedCornerShape(20.dp))
                .padding(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(if (isFrontSide) Color.White else Color.Transparent)
                    .clickable { isFrontSide = true }
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Text(
                    "Frente",
                    color = if (isFrontSide) Color.Black else TextGray,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(if (!isFrontSide) Color.White else Color.Transparent)
                    .clickable { isFrontSide = false }
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Text(
                    "Costas",
                    color = if (!isFrontSide) Color.Black else TextGray,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            LazyColumn(
                modifier = Modifier.weight(1.2f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(currentMuscles) { muscle ->
                    val isSelected = selectedMuscles.contains(muscle)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, if (isSelected) NeonGreen else CardDark, RoundedCornerShape(20.dp))
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (isSelected) CardDark else DarkBackground)
                            .clickable {
                                if (isSelected) selectedMuscles.remove(muscle) else selectedMuscles.add(muscle)
                            }
                            .padding(horizontal = 14.dp, vertical = 12.dp)
                    ) {
                        Icon(
                            imageVector = if (isSelected) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                            contentDescription = null,
                            tint = if (isSelected) NeonGreen else TextGray,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            muscle,
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = CardDark),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxHeight()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.FitnessCenter,
                            contentDescription = null,
                            tint = NeonGreen,
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            if (isFrontSide) "Visão\nFrontal" else "Visão\nTraseira",
                            color = TextGray,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("home") },
            colors = ButtonDefaults.buttonColors(containerColor = NeonGreen),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                "Concluir",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
