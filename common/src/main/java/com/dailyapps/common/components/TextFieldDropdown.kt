package com.dailyapps.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dailyapps.common.Neutral100
import com.dailyapps.common.Neutral300
import com.dailyapps.common.Neutral500
import com.dailyapps.common.fontLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDropdown(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    itemsDropdown: List<String>,
    onValueChange: (String) -> Unit,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(modifier = modifier
        .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { onValueChange(it) },
                placeholder = { BaseText(text = label) },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    focusedIndicatorColor = Neutral300,
                    unfocusedIndicatorColor = Neutral100,
                ),
                shape = RoundedCornerShape(8.dp),
                textStyle = TextStyle.Default.copy(fontFamily = fontLight, fontSize = 14.sp),
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )
            MaterialTheme(shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(16.dp))){
                DropdownMenu(
                    modifier = Modifier.background(Color.White)
                        .exposedDropdownSize().clip(RoundedCornerShape(16.dp)),
                    expanded = expanded,
                    onDismissRequest = { expanded = false }) {
                    itemsDropdown.forEach {
                        DropdownMenuItem(
                            modifier = modifier,
                            onClick = {
                                onValueChange(it)
                                expanded = false
                            },
                            text = {
                                BaseText(text = it, fontColor = Neutral500)
                            }
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
private fun PreviewTextFieldDropdown() {
    var text by remember { mutableStateOf("") }
    val list = listOf("Male", "Female")
    TextFieldDropdown(
        text = text,
        label = "Gender",
        itemsDropdown = list,
        onValueChange = { text = it })
}