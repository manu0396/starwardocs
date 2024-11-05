package com.example.starwarsdocs.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.starwarsdocs.ui.viewmodel.SharedViewModel



@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailItem(viewModel: SharedViewModel, label: String, text: String, animatedVisibilityScope: AnimatedVisibilityScope, onItemClick: ((String, String) -> Unit)? = null) {

    // State to control animation phases
    var isClicked by remember { mutableStateOf(false) }

// Animated rotation and scale using animateFloatAsState
    val rotationAngle by animateFloatAsState(
        targetValue = if (isClicked) 360f else 0f,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )

    val scale by animateFloatAsState(
        targetValue = if (isClicked) 1.1f else 1f,
        animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)
    )

    Row(
        modifier = Modifier
            .graphicsLayer {
                rotationZ = rotationAngle
                scaleX = scale
                scaleY = scale
            }
            .animateContentSize { initialValue, targetValue ->
                // Animate size changes with an easing curve
                spring<Dp>(stiffness = Spring.StiffnessLow)
            }
            .clickable {
                if (onItemClick != null) {
                    try {
                        onItemClick(label, text)
                    } catch (e: Exception) {
                       viewModel.showDialog(e.message ?: "Se ha producido un error")
                    }
                }
            }
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        // Label text
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .sharedElement(
                    state = rememberSharedContentState(key = "text/$label"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = { _, _ ->
                        tween(durationMillis = 1000)
                    }
                )
                .weight(1f) // Use weight to balance layout
        )
        // Value text
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .sharedElement(
                    state = rememberSharedContentState(key = "text/$text"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = { _, _ ->
                        tween(durationMillis = 1000)
                    }
                )
                .weight(1f)
        )
    }
}