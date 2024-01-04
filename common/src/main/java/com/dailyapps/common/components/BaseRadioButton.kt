package com.dailyapps.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dailyapps.common.Primary

@Composable
fun BaseRadioButton(
    items: List<String>, isColumn: Boolean = false, modifier: Modifier = Modifier,
    setSelected: (selected: String) -> Unit,
) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(items[0]) }
    if (!isColumn) Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        items.forEach { mItem ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(end = 16.dp)
            ) {
                RadioButton(
                    selected = (selectedOption == mItem),
                    onClick = {
                        onOptionSelected(mItem)
                        setSelected(mItem)
                    },
                    enabled = true,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Primary,
                        unselectedColor = Color.Gray
                    )
                )
                BaseText(text = mItem, fontSize = 12.sp, modifier = modifier)
            }
        }
    }
    else Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        items.forEach { mItem ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = (selectedOption == mItem),
                    onClick = {
                        onOptionSelected(mItem)
                        setSelected(mItem)
                    },
                    enabled = true,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Primary,
                        unselectedColor = Color.Gray
                    )
                )
                BaseText(text = mItem, fontSize = 12.sp, modifier = modifier)
            }
        }
    }
}

@Preview
@Composable
fun BaseRadioButtonPreview() {
    Surface {
        BaseRadioButton(listOf("Hadir", "Sakit", "Alfa")) {
        }
    }
}