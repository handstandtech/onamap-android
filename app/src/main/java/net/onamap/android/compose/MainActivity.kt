//package net.onamap.android.compose
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.compose.Composable
//import androidx.ui.core.setContent
//import androidx.ui.foundation.Text
//import androidx.ui.layout.Column
//import androidx.ui.layout.Row
//import androidx.ui.material.MaterialTheme
//import androidx.ui.material.Surface
//import androidx.ui.tooling.preview.Preview
//
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MyApplicationTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    Column {
//                        Row {
//                            Greeting("Androi5")
//                        }
//                        Row {
//                            Greeting("Android1")
//                        }
//                        Row {
//                            Greeting("Android2")
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MyApplicationTheme {
//        Greeting("Android")
//    }
//}