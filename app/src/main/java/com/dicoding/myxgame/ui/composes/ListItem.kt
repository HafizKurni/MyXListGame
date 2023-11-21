package com.dicoding.myxgame.ui.composes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.myxgame.R
import com.dicoding.myxgame.model.Game
import com.dicoding.myxgame.ui.theme.MyXGameTheme
import com.dicoding.myxgame.ui.theme.Shapes

@Composable
fun ListItem(
    game: Game,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = game.photoId),
            contentDescription = game.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(Shapes.small))
        Column(
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = game.name,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            )
            Text(
                text = game.console,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun ListItemPreview() {
    MyXGameTheme {
        ListItem(Game(15, "Cult of the Lamb", "Xbox One", "May 24, 2023", "Culturist","Massive Monster", "Devolver Digital", R.drawable.cult,"Cult of the Lamb casts players in the role of a possessed lamb saved from annihilation by an ominous stranger, and must repay their debt by building a loyal following in his name. Start your own cult in a land of false prophets, venturing out into diverse and mysterious regions to build a loyal community of woodland Followers and spread your Word to become the one true cult." ))
    }
}