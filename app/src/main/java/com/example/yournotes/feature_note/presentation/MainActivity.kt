package com.example.yournotes.feature_note.presentation

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.yournotes.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.example.yournotes.feature_note.presentation.util.Scren
import com.example.yournotes.ui.theme.YourNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            @Composable
          fun MainScreen(){
          Surface(color = MaterialTheme.colorScheme.background) {
              val navController = rememberNavController()
             NavHost(
                  navController =navController,
                  startDescription =Scren.NoteScreen.route
              ){
                  composable(route= Scren.NoteScreen.route){
                      NoteSreen(navController=navController)
                  }
                  composable(route=Scren.AddEditNoteScreen.route + "?noteId={noteId}&noteColor={noteColor}",
                      arguments= listOf(
                          navArgument(name = "noteId"){
                              type= NavType.IntType
                              defaultValue=-1
                          },
                                  navArgument(name = "noteColor"){
                                      type= NavType.IntType
                                      defaultValue=-1},
                      )
                  ){
                      val color=it.arguments?.getInt("noteColor")?:-1
                      AddEditNoteScreen(navController =navController , noteColor =color )
                  }

          }
          }
        }
    }
}}