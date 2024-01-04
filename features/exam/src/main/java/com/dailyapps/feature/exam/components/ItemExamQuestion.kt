package com.dailyapps.feature.exam.components

import android.util.Base64
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.dailyapps.common.Neutral900
import com.dailyapps.common.Primary
import com.dailyapps.common.components.BaseText
import com.dailyapps.common.components.FontType
import com.dailyapps.common.fontLight
import com.dailyapps.common.utils.convertHtmlToString
import com.dailyapps.entity.ExamQuestion
import com.dailyapps.entity.ObjectiveExamQuestion
import com.dailyapps.entity.SubmitExamRequest
import com.dailyapps.entity.objectiveOptions
import com.dailyapps.feature.score.R
import com.dailyapps.common.R as CommonR

enum class ItemExamQuestionType {
    Objektif,
    Essay
}

@Composable
fun ItemExamQuestions(
    modifier: Modifier = Modifier,
    examQuestion: ExamQuestion,
    submitExamRequest: SubmitExamRequest,
    questionNumber: Int,
    onAnswerChanged: (String) -> Unit = {},
    onImageQuestionClicked: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .shadow(
                elevation = 2.dp,
                spotColor = Color(0x0F101828),
                ambientColor = Color(0x0F101828)
            )
            .shadow(
                elevation = 3.dp,
                spotColor = Color(0x1A101828),
                ambientColor = Color(0x1A101828)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFE5E5E5),
                shape = RoundedCornerShape(size = 8.dp)
            )
    ) {
        val examHeader = stringResource(R.string.format_question_number, questionNumber)
        ItemExamQuestionHeader(
            pageHeader = examHeader,
            question = examQuestion.textSoal ?: ""
        )
        if (examQuestion.imagesSoal != null && examQuestion.imagesSoal?.isNotEmpty() == true) {
            AsyncImage(
                model = examQuestion.imagesSoal,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
                    .height(172.dp)
                    .clip(RoundedCornerShape(size = 8.dp))
                    .clickable { onImageQuestionClicked(examQuestion.imagesSoal!!) },
                error = painterResource(id = CommonR.drawable.ic_image_error),
                placeholder = painterResource(id = CommonR.drawable.ic_image_placeholder)
            )
        }
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = Color(0xFFE5E5E5)
        )
        ItemExamQuestionContent(
            type = ItemExamQuestionType.valueOf(examQuestion.jenisSoal ?: ""),
            examQuestion = examQuestion,
            submitExamRequest = submitExamRequest,
            onAnswerChanged = onAnswerChanged
        )
    }
}

@Composable
fun ItemExamQuestionHeader(
    modifier: Modifier = Modifier,
    pageHeader: String,
    question: String,
) {
    Column(
        modifier = modifier.padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        BaseText(
            text = pageHeader.uppercase(),
            modifier = Modifier.fillMaxWidth(),
            fontColor = Primary,
            fontFamily = FontType.MEDIUM,
            fontSize = 10.sp,
            letterSpacing = 1.sp,
            lineHeight = 16.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        if (question.contains("<img", true)) {
            RenderHtml(content = question)
        } else {
            BaseText(
                text = question.convertHtmlToString(),
                modifier = Modifier.fillMaxWidth(),
                fontFamily = FontType.MEDIUM,
                fontSize = 12.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.14.sp,
                fontColor = Neutral900
            )
        }
    }
}

@Composable
fun ItemExamQuestionContent(
    modifier: Modifier = Modifier,
    type: ItemExamQuestionType = ItemExamQuestionType.Objektif,
    examQuestion: ExamQuestion,
    submitExamRequest: SubmitExamRequest,
    onAnswerChanged: (String) -> Unit = {}
) {
    when (type) {
        ItemExamQuestionType.Objektif -> ItemExamQuestionContentObjective(
            modifier = modifier,
            examQuestion = examQuestion,
            submitExamRequest = submitExamRequest,
            onAnswerChanged = onAnswerChanged
        )

        ItemExamQuestionType.Essay -> ItemExamQuestionContentEssay(
            essay = submitExamRequest.jawaban,
            onEssayChanged = onAnswerChanged
        )
    }
}


@Composable
fun ItemExamQuestionContentObjective(
    modifier: Modifier = Modifier,
    examQuestion: ExamQuestion,
    submitExamRequest: SubmitExamRequest,
    onAnswerChanged: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 4.dp, bottom = 16.dp)
    ) {
        examQuestion.objectiveOptions.forEach { option ->
            Spacer(modifier = Modifier.height(12.dp))
            ItemExamQuestionContentObjectiveItem(
                selected = option.option == submitExamRequest.jawaban,
                option = option,
                onOptionSelected = { onAnswerChanged(it.option) }
            )
        }
    }
}

@Composable
fun ItemExamQuestionContentObjectiveItem(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    option: ObjectiveExamQuestion,
    onOptionSelected: (ObjectiveExamQuestion) -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = { onOptionSelected(option) },
            colors = RadioButtonDefaults.colors(
                selectedColor = Primary,
                unselectedColor = Color.Gray
            ),
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        if (option.answer?.contains("<img") == true) {
            RenderHtml(content = option.answer ?: "-")
        } else {
            BaseText(
                text = option.answer?.convertHtmlToString() ?: "-",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                letterSpacing = 1.sp,
                fontFamily = FontType.MEDIUM,
                fontColor = Neutral900
            )
        }
    }
}

@Composable
fun ItemExamQuestionContentEssay(
    modifier: Modifier = Modifier,
    essay: String,
    onEssayChanged: (String) -> Unit = {},
) {
    OutlinedTextField(
        value = essay,
        onValueChange = onEssayChanged,
        modifier = modifier
            .padding(all = 16.dp)
            .fillMaxWidth(),
        textStyle = TextStyle(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            fontFamily = fontLight,
            color = Neutral900,
        ),
        placeholder = {
            BaseText(
                text = "Tulis jawaban disini",
                fontFamily = FontType.LIGHT,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontColor = Color(0xFF979797)
            )
        },
        shape = RoundedCornerShape(size = 8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color(0xFFE5E5E5)
        ),
        minLines = 5,
        keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
    )
}

@Preview
@Composable
fun PreviewItemExamQuestions() {
    androidx.compose.material3.MaterialTheme {
        Surface(
            modifier = Modifier.padding(all = 16.dp)
        ) {
            ItemExamQuestions(
                examQuestion = ExamQuestion(
                    jenisSoal = "Objektif",
                    textSoal = "<p>kerjakan soal ini&nbsp;<img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMoAAAA7CAIAAAC1yYJDAAAAAXNSR0IArs4c6QAAAAlwSFlzAAARsAAAEbAByCf1VAAAB3lJREFUeF7tXTt64jAQNnsWkiLfnoCcIEmTijYdlEmTbsvtaHAJXdqtaIJPEE6QL0Xsu7Cjh21ZlqyRLMCQcUWCXv7n18xoNBKj/X6f0EMIHAaBX4dpllolBBgCRK9uHhTp7Wg0uk2LJJuzT/wjPVgEiF5dSBXp+/Vbvpzs/j3Nv18/9uzjV47FlsolI/K9XCzI5qP7ZLtf3SWgwUZ/b/KP57GrDn0vECDt5WJCtlkns0fgFrBrs55MH4hbLsjq74leDqyK78+SXezj7+txkaYZHuCfXZLo5XDt3//tJjdXotD172R9f7u4fuC6jB43AuR7uTGiEsEIkPYKho4quhEgerkxohLBCBC9gqGjim4EiF4lRhDT6vP4RfMLdQ+g+mN+cStSoldJr7vX5UR+nixziDbjnnw7c09irUSRLtgeANTcfb2n80UiNwTWfy9uxwkH4g8pVVHFh2CMJkmCqZFvl7PZtqQu7DCp9Vgrs60Eejub1AXPGPzkjMd+iKHXuqgStbsbVslJL2DTpFGId1X1wsimfs2Le4zBPcpTlCB66agLpcIevHCBKQ56NXQT77LJJ96r1qHOuFPwo2efRK82gDXBnCoJiX6bXJxdCp8MJVoURHY2pGJEL6M0wpwwi2CNWqhpUK3m1Uy7ITGocyxELws88QhmZIhFeeXL2bJ078XAelhI8QqxNHA3pS19Eb2ssIU4YRZL2/LidFdL9mVaLpq8Mqz2cvuE2Jbc5Yx9afRiS2dY3zQnkLvpiCXYiglmMD7wFLHvGF6+1kYfcsimwptgNfHrk35AmvtS6SX08AAka16U13EiiAodS+33tZGomIVDsMFtVApFvgWOamE4m/uq6NXDxvejvbl2azj5EojP/wvKDXTb0WZmL4I1Y1uBQHF5B7hQbJZyqGBnAW0SAnG29FXSK9oKJZrYTXxXRnlEtyLcCQslhsbDMJKqw/b0NYw41xFndY9LaERbX4Je4ea9NSGj0cuwZjoNuzT0fFRQGC+MmPqrLzkBAxSHP862vviWdgEZv2VCeWP3tUj54b4qG4Dv8/vlBnTs95aZAtDkPM3SW63h8cMUToC918cKT5f2Pn7+kHN3fT+ctIZu6bAjKHAyILl7nCXrTZYUWZo1j2ja8PfH2d4XmyoW35EFYfJas8GfCN8Qq72E3ZD7tkJ2rdabE0+1liJP4bjbvpVxQKAgFFAk2yhNT2vLqFM6ihz4Skh4rPVjx98fZ3tf3Dh2L03Et9slboMVRy8NeItx7rNk6sySCfCTBU6NHAenmYxFL5GTYWa1n3TKIePwd76gswCjl8vzKjWFpTGzy1eL1yBLHS7LsjWYXs7XDikg3Fe06oqovbroJdOBfIYl9In6JgcLGzDfawwHrJLPb+vdCVc3EA6YvtpOX92tdKXbfNf2oWZm3FVfj/t+zE8Y8FOkTy87mCnssDb24cBGuDWA41Udh9N6d0jHNNbj4Y/IVs0WIPyGj42F11Yu/9qpaFlXFqx+EOtcic3eq5NsfsXI9Ta84/8h0vHBv5+sOb3YBLBNsmy+uXkDZRphEppHytWCcW6y9Yhtzna/dVOhti2h3y0RcEvOPVwEsPWrVQLbTz68NqODeZ5FkI4d/wgj5/RiIQC+eNUfAHbzuHoeM/6Jxe08xrpc0hmsMTS4SKYyWx0W2mrrTIUP4EqHEKvYwLHD7fAQoGGeBUsHh7/H4OxF5dRuBd+aawsZlUUsHnErx3LXUKyWxeJACzMczN30cuvFssbjaEez9a5VU7kkcnrlhkZCpVOODoG/F0y2wmez5xjlbX0b6R91x8R8HDs20cIbvm8fofwZZUxEeFuvJvwDEabmu/iFU9DDCs94QbinfK+uaF64Vaxb7WAHajvwnNml08uPm5dbur9VrLGx6iiFONVhXMfG2LkhTsnQbYn5W0XHvodZSzVoJ1vQ3TCc+Rwu6YheumxCPGmXATOyRCEdfG9clKOs53DJBT+ZMOTBnWBsQYEIDCNbTCkpyVNJTZGPc9dcTHpEL4XD/laRVZYxQUz4iukoaf9kLR6QNLCL6TNElPEEE9CrS6JXBZcq8ICItZNenIrbrTyFVdlTo2Fl5TwTmL3EfqzCRC+JtCutyEk4FL0qsdakwljWY7Ehej+IjAknsJdQgOeo9Hn89t55dyIZhG/4QsJAwTZ06X6v6Az/iQ02LaLMgr2IG72a0qSLx/uoLKrrQICMowKQyEL0zjUkklkRIHop0NytwGh5ppjJ02D8MB39FJ/OM6KXioj81SC8OmK5hskfFkLIp58vT0QwDTqilwoIZB2LFaBM1ufJs+bEffHV4mUHv5ZWLgCjHkjAc3zAJYleinBAeXHTWICV43d+8NNR5sT98sRQFZBgx4IOdiBhwAzqHBrRq4aHnVjavVyNRlfspvmP1TNXS/T0QIDoVYPHTCPsLYN7v94ov2jcYRyhbqWw+MmT8qf5ekjksqr+xKCm+Z2rnAb5AW5qdO77Nfd2YiS3XpY8aM+xlKdyxMl454dF7vLMzbHvUzkTFlLU/rKM0cDehnyvgQnksobzH6tPjCBdrwHvAAAAAElFTkSuQmCC\" style=\"height:49px; width:169px\" /></p>",
                    opsiA = "<p>kerjakan soal ini&nbsp;<img src=\"data:image/png;base64,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\" style=\"height:49px; width:169px\" /></p>",
                    opsiB = "3 atau -1",
                    opsiC = "1",
                    opsiD = "<p>kerjakan soal ini&nbsp;<img src=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMoAAAA7CAIAAAC1yYJDAAAAAXNSR0IArs4c6QAAAAlwSFlzAAARsAAAEbAByCf1VAAAB3lJREFUeF7tXTt64jAQNnsWkiLfnoCcIEmTijYdlEmTbsvtaHAJXdqtaIJPEE6QL0Xsu7Cjh21ZlqyRLMCQcUWCXv7n18xoNBKj/X6f0EMIHAaBX4dpllolBBgCRK9uHhTp7Wg0uk2LJJuzT/wjPVgEiF5dSBXp+/Vbvpzs/j3Nv18/9uzjV47FlsolI/K9XCzI5qP7ZLtf3SWgwUZ/b/KP57GrDn0vECDt5WJCtlkns0fgFrBrs55MH4hbLsjq74leDqyK78+SXezj7+txkaYZHuCfXZLo5XDt3//tJjdXotD172R9f7u4fuC6jB43AuR7uTGiEsEIkPYKho4quhEgerkxohLBCBC9gqGjim4EiF4lRhDT6vP4RfMLdQ+g+mN+cStSoldJr7vX5UR+nixziDbjnnw7c09irUSRLtgeANTcfb2n80UiNwTWfy9uxwkH4g8pVVHFh2CMJkmCqZFvl7PZtqQu7DCp9Vgrs60Eejub1AXPGPzkjMd+iKHXuqgStbsbVslJL2DTpFGId1X1wsimfs2Le4zBPcpTlCB66agLpcIevHCBKQ56NXQT77LJJ96r1qHOuFPwo2efRK82gDXBnCoJiX6bXJxdCp8MJVoURHY2pGJEL6M0wpwwi2CNWqhpUK3m1Uy7ITGocyxELws88QhmZIhFeeXL2bJ078XAelhI8QqxNHA3pS19Eb2ssIU4YRZL2/LidFdL9mVaLpq8Mqz2cvuE2Jbc5Yx9afRiS2dY3zQnkLvpiCXYiglmMD7wFLHvGF6+1kYfcsimwptgNfHrk35AmvtS6SX08AAka16U13EiiAodS+33tZGomIVDsMFtVApFvgWOamE4m/uq6NXDxvejvbl2azj5EojP/wvKDXTb0WZmL4I1Y1uBQHF5B7hQbJZyqGBnAW0SAnG29FXSK9oKJZrYTXxXRnlEtyLcCQslhsbDMJKqw/b0NYw41xFndY9LaERbX4Je4ea9NSGj0cuwZjoNuzT0fFRQGC+MmPqrLzkBAxSHP862vviWdgEZv2VCeWP3tUj54b4qG4Dv8/vlBnTs95aZAtDkPM3SW63h8cMUToC918cKT5f2Pn7+kHN3fT+ctIZu6bAjKHAyILl7nCXrTZYUWZo1j2ja8PfH2d4XmyoW35EFYfJas8GfCN8Qq72E3ZD7tkJ2rdabE0+1liJP4bjbvpVxQKAgFFAk2yhNT2vLqFM6ihz4Skh4rPVjx98fZ3tf3Dh2L03Et9slboMVRy8NeItx7rNk6sySCfCTBU6NHAenmYxFL5GTYWa1n3TKIePwd76gswCjl8vzKjWFpTGzy1eL1yBLHS7LsjWYXs7XDikg3Fe06oqovbroJdOBfIYl9In6JgcLGzDfawwHrJLPb+vdCVc3EA6YvtpOX92tdKXbfNf2oWZm3FVfj/t+zE8Y8FOkTy87mCnssDb24cBGuDWA41Udh9N6d0jHNNbj4Y/IVs0WIPyGj42F11Yu/9qpaFlXFqx+EOtcic3eq5NsfsXI9Ta84/8h0vHBv5+sOb3YBLBNsmy+uXkDZRphEppHytWCcW6y9Yhtzna/dVOhti2h3y0RcEvOPVwEsPWrVQLbTz68NqODeZ5FkI4d/wgj5/RiIQC+eNUfAHbzuHoeM/6Jxe08xrpc0hmsMTS4SKYyWx0W2mrrTIUP4EqHEKvYwLHD7fAQoGGeBUsHh7/H4OxF5dRuBd+aawsZlUUsHnErx3LXUKyWxeJACzMczN30cuvFssbjaEez9a5VU7kkcnrlhkZCpVOODoG/F0y2wmez5xjlbX0b6R91x8R8HDs20cIbvm8fofwZZUxEeFuvJvwDEabmu/iFU9DDCs94QbinfK+uaF64Vaxb7WAHajvwnNml08uPm5dbur9VrLGx6iiFONVhXMfG2LkhTsnQbYn5W0XHvodZSzVoJ1vQ3TCc+Rwu6YheumxCPGmXATOyRCEdfG9clKOs53DJBT+ZMOTBnWBsQYEIDCNbTCkpyVNJTZGPc9dcTHpEL4XD/laRVZYxQUz4iukoaf9kLR6QNLCL6TNElPEEE9CrS6JXBZcq8ICItZNenIrbrTyFVdlTo2Fl5TwTmL3EfqzCRC+JtCutyEk4FL0qsdakwljWY7Ehej+IjAknsJdQgOeo9Hn89t55dyIZhG/4QsJAwTZ06X6v6Az/iQ02LaLMgr2IG72a0qSLx/uoLKrrQICMowKQyEL0zjUkklkRIHop0NytwGh5ppjJ02D8MB39FJ/OM6KXioj81SC8OmK5hskfFkLIp58vT0QwDTqilwoIZB2LFaBM1ufJs+bEffHV4mUHv5ZWLgCjHkjAc3zAJYleinBAeXHTWICV43d+8NNR5sT98sRQFZBgx4IOdiBhwAzqHBrRq4aHnVjavVyNRlfspvmP1TNXS/T0QIDoVYPHTCPsLYN7v94ov2jcYRyhbqWw+MmT8qf5ekjksqr+xKCm+Z2rnAb5AW5qdO77Nfd2YiS3XpY8aM+xlKdyxMl454dF7vLMzbHvUzkTFlLU/rKM0cDehnyvgQnksobzH6tPjCBdrwHvAAAAAElFTkSuQmCC\" style=\"height:49px; width:169px\" /></p>",
//                    imagesSoal = "https://smpsantoyosefduri.sch.id/images/soal/1691641707.jpg"
                ),
                submitExamRequest = SubmitExamRequest(),
                questionNumber = 1,
            )
        }
    }
}

@Preview
@Composable
fun PreviewItemExamQuestionHeader() {
    MaterialTheme {
        ItemExamQuestionHeader(
            pageHeader = "NOMOR 1",
            question = "Tekanan dalam zat cair yang disebabkan oleh zat cair itu sendiri disebut...",
            modifier = Modifier
                .padding(16.dp)
                .shadow(
                    elevation = 2.dp,
                    spotColor = Color(0x0F101828),
                    ambientColor = Color(0x0F101828)
                )
                .shadow(
                    elevation = 3.dp,
                    spotColor = Color(0x1A101828),
                    ambientColor = Color(0x1A101828)
                )
                .border(
                    width = 1.dp,
                    color = Color(0xFFE5E5E5),
                    shape = RoundedCornerShape(size = 8.dp)
                ))
    }
}

@Composable
fun RenderHtml(
    modifier: Modifier = Modifier,
    content: String = ""
) {
    val encodedString = Base64.encodeToString(content.toByteArray(), Base64.NO_PADDING)
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            webViewClient = WebViewClient()

            loadData(encodedString, "text/html", "base64")
        }
    }, update = {
        it.loadData(encodedString, "text/html", "base64")
    },
        modifier = modifier
    )
}
