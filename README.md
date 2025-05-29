# 🕐 Clock Compose

<div align="center">
  
  ![Clock Compose Demo](demo.gif)
  
  *A beautiful collection of animated clock components built with Jetpack Compose*
  
  [![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-blue.svg)](https://kotlinlang.org)
  [![Compose](https://img.shields.io/badge/Jetpack%20Compose-2024.02.00-green.svg)](https://developer.android.com/jetpack/compose)
  [![API](https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=26)

</div>

## ✨ Features

- **🎨 3D Analog Clock** - Beautifully crafted analog clock with 3D frame effects and smooth hand animations
- **💫 Digital Clock** - Modern digital clock with animated color transitions and elegant styling
- **⚡ Real-time Updates** - Both clocks update every second with precise timing
- **🎭 Custom Animations** - Smooth color transitions and visual effects
- **📱 Responsive Design** - Optimized for different screen sizes

## 🚀 Components

### 📊 AnalogClock3D
A stunning 3D analog clock featuring:
- Golden metallic frame with depth effects
- Precise hour, minute, and second hands
- Real-time hour and minute markers
- Smooth animations and transitions
- Customizable size parameter

### 🔮 OwlDigitalClock
An elegant digital clock with:
- Animated color-changing text effects
- Beautiful gradient backgrounds
- Date and time display
- Glossy glass-like appearance
- Subtle shadow and glow effects

### Basic Implementation

```kotlin
@Composable
fun MyClockScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 3D Analog Clock
        AnalogClock3D(
            modifier = Modifier,
            size = 250.dp
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Digital Clock with animations
        OwlDigitalClock()
    }
}
```


## 🏗️ Architecture

```
📁 presentation/
├── 📄 AnalogClock3D.kt      # 3D analog clock component
├── 📄 OwlDigitalClock.kt    # Digital clock with animations
└── 📄 MainActivity.kt       # Main activity and preview
```

## 🎨 Design Features

### AnalogClock3D
- **Frame Effects**: Multi-layered golden frame with shadow and highlight
- **Hand Design**: Proportional hands with rounded caps
- **Markers**: 12 hour markers and 60 minute markers
- **Real-time**: Updates every second with smooth transitions

### OwlDigitalClock
- **Color Animation**: Smooth cyan-to-magenta color transitions
- **3D Effects**: Layered shadows and highlights for depth
- **Typography**: Monospace font for time, clean font for date
- **Background**: Gradient with glass-like reflections

## 🧰 Technical Details

### Dependencies
```kotlin
// Compose BOM
implementation 'androidx.compose:compose-bom:2024.02.00'

// Core Compose dependencies
implementation 'androidx.compose.ui:ui'
implementation 'androidx.compose.ui:ui-graphics'
implementation 'androidx.compose.foundation:foundation'
implementation 'androidx.activity:activity-compose'

// Animation
implementation 'androidx.compose.animation:animation'
```

### Key Technologies
- **Canvas API**: Custom drawing for clock faces and hands
- **Coroutines**: Non-blocking time updates
- **Animation API**: Smooth color transitions
- **Material Design**: Following modern design principles

```

### Animation Speed
```kotlin
// In OwlDigitalClock - modify animation duration
tween(600, easing = FastOutSlowInEasing) // Change 600 to your preferred duration
```


## 🙏 Acknowledgments

- **Jetpack Compose Team** for the amazing UI toolkit
- **Material Design** for design inspiration
- **Android Developer Community** for continuous support

---

<div align="center">
  <strong>⭐ Star this repository if you found it helpful!</strong>
</div>
