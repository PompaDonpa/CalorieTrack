package com.calorytracker.calorytracker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.calorytracker.calorytracker.navigation.navigate
import com.calorytracker.calorytracker.ui.theme.CaloryTrackerTheme
import com.calorytracker.core.domain.preferences.Preferences
import com.calorytracker.core.navigation.Route
import com.calorytracker.onboarding_presentation.activity.ActivityScreen
import com.calorytracker.onboarding_presentation.age.AgeScreen
import com.calorytracker.onboarding_presentation.welcome.WelcomeScreen
import com.calorytracker.onboarding_presentation.gender.GenderScreen
import com.calorytracker.onboarding_presentation.goal.GoalScreen
import com.calorytracker.onboarding_presentation.height.HeightScreen
import com.calorytracker.onboarding_presentation.nutrient_goal.NutrientGoalScreen
import com.calorytracker.onboarding_presentation.weight.WeightScreen
import com.calorytracker.tracker_presentation.search.SearchScreen
import com.calorytracker.tracker_presentation.tracker_overview.TrackerOverviewScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shouldShowOnboarding = preferences.loadShouldShowOnboarding()
        setContent {
            CaloryTrackerTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = if(shouldShowOnboarding) {
                            Route.WELCOME
                        } else Route.TRACKER_OVERVIEW
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.AGE) {
                            AgeScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.GENDER) {
                            GenderScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.HEIGHT) {
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.WEIGHT) {
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.NUTRIENT_GOAL) {
                            NutrientGoalScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.ACTIVITY) {
                            ActivityScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.GOAL) {
                            GoalScreen(onNavigate = navController::navigate)
                        }

                        composable(Route.TRACKER_OVERVIEW) {
                            TrackerOverviewScreen(
                                onNavigate = navController::navigate
                            )
                        }
                        composable(
                                route = Route.SEARCH + "/{mealName}/{month}/{dayOfMonth}/{year}",
                                arguments = listOf(
                                    navArgument("mealName") {
                                        type = NavType.StringType
                                    },
                                    navArgument("month") {
                                        type = NavType.IntType
                                    },
                                    navArgument("dayOfMonth") {
                                        type = NavType.IntType
                                    },
                                    navArgument("year") {
                                        type = NavType.IntType
                                    },
                                )
                            ) {
                                val mealName = it.arguments?.getString("mealName")!!
                                val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
                                val month = it.arguments?.getInt("month")!!
                                val year = it.arguments?.getInt("year")!!
                                SearchScreen(
                                    scaffoldState = scaffoldState,
                                    mealName = mealName,
                                    month = month,
                                    dayOfMonth = dayOfMonth,
                                    year = year,
                                    onNavigateUp = {
                                        navController.navigateUp()
                                    }
                                )
                            }
                        }
                }
            }
        }
    }
}
