package com.simplefit.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
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
fun GenderScreen(navController: NavController) {
    var selectedGender by remember { mutableStateOf("Feminino") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                "Seu gênero",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Para estimar a taxa metabólica\ndo seu corpo.",
                color = TextGray,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            GenderOptionItem("Masculino", selectedGender == "Masculino") { selectedGender = "Masculino" }
            GenderOptionItem("Feminino", selectedGender == "Feminino") { selectedGender = "Feminino" }
            GenderOptionItem("Não definir", selectedGender == "Não definir") { selectedGender = "Não definir" }
        }

        Button(
            onClick = { navController.navigate("birthday") },
            colors = ButtonDefaults.buttonColors(containerColor = NeonGreen),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                "Continuar",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Color.Black)
        }
    }
}

@Composable
fun GenderOptionItem(text: String, isSelected: Boolean, onClick: () -> Unit) {
    val borderColor = if (isSelected) NeonGreen else CardDark
    val bg = if (isSelected) CardDark else DarkBackground

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(1.5.dp, borderColor, RoundedCornerShape(30.dp))
            .clip(RoundedCornerShape(30.dp))
            .background(bg)
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Icon(
            imageVector = if (isSelected) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
            contentDescription = null,
            tint = if (isSelected) NeonGreen else TextGray
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Medium)
    }
}
