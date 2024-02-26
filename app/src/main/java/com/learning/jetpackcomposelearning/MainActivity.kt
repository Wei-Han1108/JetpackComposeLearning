package com.learning.jetpackcomposelearning

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.learning.jetpackcomposelearning.ui.theme.JetpackComposeLearningTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeLearningTheme{
                MessageCard( Message("Hello","Maltese"))
            }


        }
    }
}

data class Message(val author:String, val body: String)
object  MsgData{
    private const val author = "Hello"
    val messages = listOf(
        Message(author, "Maltese"),
        Message(author, "为了给广大的读者一个更好的体验，从今天起，我们公众号决定陆续发一些其他作者的高质量文章"),
        Message(author, "每逢佳节倍思亲，从今天起，参加我们公众号活动的同学可以获得精美礼品一份！！"),
        Message(author, "荣华梦一场，功名纸半张，是非海波千丈，马蹄踏碎禁街霜，听几度头鸡唱"),
        Message(author, "唤归来，西湖山上野猿哀。二十年多少风流怪，花落花开。望云霄拜将台，袖星斗安邦策，破烟月迷魂寨。酸斋笑我，我笑酸斋"),
        Message(author, "伤心尽处露笑颜，醉里孤单写狂欢。两路殊途情何奈，三千弱水忧忘川。花开彼岸朦胧色，月过长空爽朗天。青鸟思飞无侧羽，重山万水亦徒然"),
        Message(author, "又到绿杨曾折处，不语垂鞭，踏遍清秋路。衰草连天无意绪，雁声远向萧关去。恨天涯行役苦，只恨西风，吹梦成今古。明日客程还几许，沾衣况是新寒雨"),
        Message(author, "莫笑农家腊酒浑，丰年留客足鸡豚。山重水复疑无路，柳暗花明又一村。箫鼓追随春社近，衣冠简朴古风存。从今若许闲乘月，拄杖无时夜叩门")

    )
}

// 定义一个compose函数, 特点是里面可以使用jc的Text函数。Composable 函数一般用大写开头，为了和普通的函数作为区分
@Composable
fun MessageCard(msg: Message)
{
    var isExpanded by remember { mutableStateOf(false) }

    Surface(shape = MaterialTheme.shapes.large,
            shadowElevation = 8.dp,
            modifier = Modifier.padding(all = 8.dp)) {
        Row(modifier = Modifier.padding(all = 10.dp)
            .clickable { isExpanded = ! isExpanded }){
            Image(painterResource(id = R.drawable.gjsdg)  , contentDescription = "profile picture",
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.secondary, shape = CircleShape))

            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Column {
                Text(text = "Hello ${msg.author}!" ,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.padding(vertical = 2.dp))
                Text(text = "Hello ${msg.body}!",
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = if(isExpanded) Int.MAX_VALUE else 1,
                    modifier = Modifier.animateContentSize())
            }
        }
    }
}


// 轻松实现把列表类型的消息，按照messagecard格式显示出来。而且实现效果有弹性滑动。
@Composable
fun Conversation(messages: List<Message>)
{
    LazyColumn{
        items(messages){message ->
            MessageCard(msg = message)
        }
    }
}


// 预览白天和夜间模式，compose是可以自适应的
@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "DarkMode"
)


// 预览函数
@Preview
@Composable
fun PreviewMessageCard()
{
    JetpackComposeLearningTheme {
        Conversation(messages = MsgData.messages)
    }
}