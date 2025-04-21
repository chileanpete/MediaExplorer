package com.example.mediaexplorer

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mediaexplorer.ui.theme.AddCategoriaScreen
import com.example.mediaexplorer.ui.theme.AddContentScreen
import com.example.mediaexplorer.ui.theme.CategoryPageScreen
import com.example.mediaexplorer.ui.theme.ContentPageScreen
import com.example.mediaexplorer.ui.theme.HomeScreen

@Composable
fun MediaExplorerNavHost(navController: NavHostController, viewModel: MediaViewModel) {

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable("addCategory") {
            AddCategoriaScreen (
                onBack = { navController.popBackStack() },
                onSave = { name, description ->
                    viewModel.addCategory(name, description)
                    navController.popBackStack()
                }
            )
        }
        composable(
            "category/{categoryId}",
            arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: 0
            CategoryPageScreen(
                categoryId = categoryId,
                onBack = { navController.popBackStack() },
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(
            "addContent/{categoryId}",
            arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: 0
            AddContentScreen (
                categoryId = categoryId,
                onBack = { navController.popBackStack() },
                onSave = { content ->
                    viewModel.addContent(categoryId, content)
                    navController.popBackStack()
                }
            )
        }

        composable(
            "contentDetail/{contentId}?categoryId={categoryId}",
            arguments = listOf(
                navArgument("contentId") { type = NavType.IntType },
                navArgument("categoryId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val contentId = backStackEntry.arguments?.getInt("contentId") ?: 0
            val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: 0
            ContentPageScreen(
                categoryId = categoryId,
                contentId = contentId,
                onBack = { navController.popBackStack() },
                viewModel = viewModel
            )
        }
    }
}