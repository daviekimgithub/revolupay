package com.chat.revolupay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chat.revolupay.constant.CardType
import com.chat.revolupay.ui.theme.REVOLUPAYTheme
import com.chat.revolupay.ui.theme.lightGreen
import com.chat.revolupay.ui.theme.greenColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            REVOLUPAYTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .padding(20.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var selectedOption by remember { mutableStateOf("") }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        Spacer(
                            modifier = Modifier
                                .height(40.dp)
                        )
                        Text(
                            text = "Reception Method",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        ReceptionMethodCard(
                            cardType = CardType.MOBILE,
                            transferDuration = "Instantly",
                            "10.41",
                            selectedOption
                        )

                        Spacer(
                            modifier = Modifier
                                .height(14.dp)
                        )
                        ReceptionMethodCard(
                            cardType = CardType.BANK,
                            transferDuration = "2 to 3 days",
                            "10.41",
                            selectedOption
                        )

                        Spacer(
                            modifier = Modifier
                                .height(14.dp)
                        )
                        ReceptionMethodCard(
                            cardType = CardType.CASH,
                            transferDuration = "2 to 3 days",
                            "10.41",
                            selectedOption
                        )

                        Spacer(
                            modifier = Modifier
                                .height(14.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DashedDivider(
    thickness: Dp,
    color: Color = greenColor,
    phase: Float = 10f,
    intervals: FloatArray = floatArrayOf(10f, 10f),
    modifier: Modifier
) {
    Canvas(
        modifier = modifier
    ) {
        val dividerHeight = thickness.toPx()
        drawRoundRect(
            color = color,
            style = Stroke(
                width = dividerHeight,
                pathEffect = PathEffect.dashPathEffect(
                    intervals = intervals,
                    phase = phase
                )
            )
        )
    }
}

@Composable
fun TransferDuration(transferDuration: String){
    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (transferDuration == "Instantly") {
            VectorImageInstant(
                modifier = Modifier.size(30.dp)
            )
        } else {
            VectorImageTime(
                modifier = Modifier.size(30.dp)
            )
        }
        Text(
            text = transferDuration
        )
    }
}

@Composable
fun ReceptionMethodCard(cardType: CardType, transferDuration: String, rate: String, selectedOption: String) {
    Surface(
        modifier = Modifier
            .padding(start = 2.dp, end = 2.dp, bottom = 2.dp)
            .wrapContentHeight()
            .border(2.dp, lightGreen, shape = RoundedCornerShape(16.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .padding(top = 8.dp)
                ) {
                    when (cardType) {
                        CardType.CASH -> {
                            Row() {
                                VectorImageCash(
                                    modifier = Modifier.size(36.dp)
                                )
                                Column() {
                                    Text(
                                        text = "Cash",
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(
                                        modifier = Modifier.height(12.dp)
                                    )
                                    TransferDuration(transferDuration)
                                }
                            }
                        }

                        CardType.MOBILE -> {
                            Row() {
                                VectorImageMobile(
                                    modifier = Modifier.size(36.dp)
                                )
                                Column() {
                                    Text(
                                        text = "Mobile Wallet",
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(
                                        modifier = Modifier.height(12.dp)
                                    )
                                    TransferDuration(transferDuration)
                                }
                            }
                        }

                        else -> {
                            Row() {
                                VectorImageBank(
                                    modifier = Modifier.size(36.dp)
                                )
                                Column() {
                                    Text(
                                        text = "Bank Transfer",
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(
                                        modifier = Modifier.height(12.dp)
                                    )
                                    TransferDuration(transferDuration)
                                }
                            }
                        }
                    }
                }
                Column() {
                    RadioButton(
                        selected = false,
                        onClick = {

                        }
                    )
                    Text(
                        text = "£ = $rate"
                    )
                }
            }
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            DashedDivider(
                thickness = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                VectorImageTime(
                    modifier = Modifier.size(36.dp)
                )
                VectorImageInstant(
                    modifier = Modifier.size(36.dp)
                )
                VectorImageInstant(
                    modifier = Modifier.size(36.dp)
                )
                VectorImageBank(
                    modifier = Modifier.size(36.dp)
                )
                VectorImageInstant(
                    modifier = Modifier.size(36.dp)
                )
                VectorImageBank(
                    modifier = Modifier.size(36.dp)
                )
                VectorImageBank(
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    }
}

// Vector images
@Composable
fun VectorImageTime(modifier: Modifier = Modifier) {
    val imageVector = ImageVector.vectorResource(id = R.drawable.icon_time)
    Image(
        painter = rememberVectorPainter(imageVector),
        contentDescription = "time",
        modifier = modifier
            .padding(4.dp)
    )
}

@Composable
fun VectorImageBank(modifier: Modifier = Modifier) {
    val imageVector = ImageVector.vectorResource(id = R.drawable.icon_bank)
    Image(
        painter = rememberVectorPainter(imageVector),
        contentDescription = "bank",
        modifier = modifier
            .padding(4.dp)
            .background(color = lightGreen, shape = CircleShape)
            .padding(6.dp)
    )
}

@Composable
fun VectorImageInstant(modifier: Modifier = Modifier) {
    val imageVector = ImageVector.vectorResource(id = R.drawable.icon_instant)
    Image(
        painter = rememberVectorPainter(imageVector),
        contentDescription = "instant",
        modifier = modifier
            .padding(4.dp)
            .background(color = greenColor, shape = CircleShape)
            .padding(6.dp)
    )
}

@Composable
fun VectorImageMobile(modifier: Modifier = Modifier) {
    val imageVector = ImageVector.vectorResource(id = R.drawable.icon_mobile)
    Image(
        painter = rememberVectorPainter(imageVector),
        contentDescription = "mobile",
        modifier = modifier
            .padding(4.dp)
            .background(color = lightGreen, shape = CircleShape)
            .padding(6.dp)
    )
}

@Composable
fun VectorImageCash(modifier: Modifier = Modifier) {
    val imageVector = ImageVector.vectorResource(id = R.drawable.icon_mobile)
    Image(
        painter = rememberVectorPainter(imageVector),
        contentDescription = "cash",
        modifier = modifier
            .padding(4.dp)
            .background(color = lightGreen, shape = CircleShape)
            .padding(6.dp)
    )
}
