package com.dailyapps.home.visi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dailyapps.common.Primary
import com.dailyapps.common.components.BaseAppBar
import com.dailyapps.common.fontMedium
import com.dailyapps.common.fontRegular
import com.dailyapps.home.dashboard.scrollEnabled

@Composable
fun VisiMisiScreen(
    navController: NavController
) {
    val misi = """
        1.	Melaksanakan proses pembelajaran menggunakan kurikulum yang diintegrasikan dengan pola dengan Pedagogik Reflektif,
        2.	Menyelenggarakan kegiatan pembelajaran yang menginternalisasi empati dalam kebersamaan, semangat kasih, dan disiplin,
        3.	Menyelenggarakan kegiatan pengembangan diri siswa dalam bidang bimbingan dan konseling, sains dan teknologi, seni dan bahasa, olah raga serta Bina Iman,
        4.	Menyelenggarakan pembelajaran berbasis IT dengan disiplin,
        5.	Melakukan pembelajaran berkelanjutan bagi para pendidik agar menghidupi  nilai - nilai kehidupan dan IT dalam proses pembelajaran.
    """.trimIndent()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BaseAppBar(
                title = "Visi & Misi",
                modifier = Modifier.fillMaxWidth(),
                onClickBack = { navController.popBackStack() },
                onMenuClick = {})
        }
    ) { contentPadding ->
        Column(modifier = Modifier
            .scrollEnabled(true)
            .padding(contentPadding)
            .padding(16.dp)) {
            Text(text = "Visi", fontSize = 32.sp, color = Primary, fontFamily = fontMedium, fontWeight = FontWeight.Bold)
            Text(text = "“Terwujudnya sekolah yang unggul dalam disiplin, tangguh dan berempati dalam terang kristiani”", fontSize = 14.sp, color = Color.Gray, fontFamily = fontRegular, modifier = Modifier.padding(top = 12.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Misi", fontSize = 32.sp, color = Primary, fontFamily = fontMedium, fontWeight = FontWeight.Bold)
            Text(text = misi, fontSize = 14.sp, color = Color.Gray, fontFamily = fontRegular, modifier = Modifier.padding(top = 12.dp))
        }
    }
}

