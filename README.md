# ComposeCharter

A library that contains several customizable Charts that can be used in Jecpack Compose's `@Composables`.

## Basic Usage

### 1. Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
    repositories {
    ...
    maven { url 'https://jitpack.io' }
    }
}
```

### 2. Add the dependency to your module:
```gradle
dependencies {
    implementation 'com.github.nikachapo:ComposeCharter:1.0.2'
}
```

### 3. Use Chart Composables:
There is one data class that can be used for drawing Charts:
```kotlin
data class ChartModel(val value: Float, val color: Color, val text: String? = null)
```

> Note: for Animations, library does not have inner implementations, you can use common Animation approach as you would do for composables, for example:
```kotlin
    var cyanPercentage by remember { mutableStateOf<Float?>(0f) }
    var greenPercentage by remember { mutableStateOf<Float?>(0f) }
    var yellowPercentage by remember { mutableStateOf<Float?>(0f) }

    val cyanAnimatedPercentageState = animateFloatAsState(
        targetValue = cyanPercentage ?: 0f,
        animationSpec = tween(1000)
    )

    val greenAnimatedPercentageState = animateFloatAsState(
        targetValue = greenPercentage ?: 0f,
        animationSpec = tween(1000)
    )

    val yellowAnimatedPercentageState = animateFloatAsState(
        targetValue = yellowPercentage ?: 0f,
        animationSpec = tween(1000)
    )

```


#### PieChart
```kotlin
PieChart(
    chartModels = listOf(
                ChartModel(cyanAnimatedPercentageState.value, Color.Cyan),
                ChartModel(greenAnimatedPercentageState.value, Color.Green),
                ChartModel(yellowAnimatedPercentageState.value, Color.Yellow)),
    chartSize = 150.dp,
    elevation = 30.dp,
    showCenterDot = true
)
```
<img src="https://user-images.githubusercontent.com/44478420/177399420-23acf661-ce85-48e1-9f28-33b7ca456d9a.gif" 
data-canonical-src="https://user-images.githubusercontent.com/44478420/177399420-23acf661-ce85-48e1-9f28-33b7ca456d9a.gif" width="280" height="280" />


#### DonutChart
```kotlin
DonutChart(
    chartModels = chartModels,
    chartSize = 150.dp,
    strokeWidth = 100f,
    elevation = 30.dp,
    cap = StrokeCap.Round
)
```
<img src="https://user-images.githubusercontent.com/44478420/177400285-0b0877b3-d0b1-442d-9359-26bd1a5f4761.gif" 
data-canonical-src="https://user-images.githubusercontent.com/44478420/177400285-0b0877b3-d0b1-442d-9359-26bd1a5f4761.gif" width="280" height="280" />

#### ColumnChart
```kotlin
ColumnChart(
    chartModels = chartModels,
    modifier = Modifier.fillMaxWidth(),
    height = 150.dp,
    columnWidth = 150f,
    spaceWidth = 40f,
    cornerRadius = CornerRadius(12f, 12f),
    showValues = true
)
```

<img src="https://user-images.githubusercontent.com/44478420/177401026-cad0f8be-6a95-4844-948f-6cc198de27d4.gif" 
data-canonical-src="https://user-images.githubusercontent.com/44478420/177401026-cad0f8be-6a95-4844-948f-6cc198de27d4.gif" width="280" height="280" />

#### BarChart
```kotlin
AreaChart(
    yAxisValues = list.map { it.value },
    modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .padding(24.dp),
    lineWidth = 2f
)
```

<img src="https://user-images.githubusercontent.com/44478420/177401658-6db29a9b-baf8-4d0e-ae6b-d4088262150b.gif" 
data-canonical-src="https://user-images.githubusercontent.com/44478420/177401658-6db29a9b-baf8-4d0e-ae6b-d4088262150b.gif" width="280" height="280" />

#### LineChart
```kotlin
LineChart(
    yAxisValues = list.map { it.value },
    modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .padding(24.dp),
    lineWidth = 2f,
    withDots = true,
    lineColors = listOf(
        MaterialTheme.colors.primary,
        MaterialTheme.colors.error
    )
)
```

<img src="https://user-images.githubusercontent.com/44478420/177402168-c1a4d8a5-39ad-4178-8bc7-2ec809e43651.gif" 
data-canonical-src="https://user-images.githubusercontent.com/44478420/177402168-c1a4d8a5-39ad-4178-8bc7-2ec809e43651.gif" width="280" height="280" />

#### AreaChart
```kotlin
AreaChart(
    yAxisValues = listOf(10f, 20f, 15f),
    modifier = Modifier
               .fillMaxWidth()
                .height(200.dp),
    spacing = 50f,
    lineWidth = 2f,
    fillColor = Color.Green,
    graphColor = MaterialTheme.colors.primary
)
```

<img src="https://user-images.githubusercontent.com/44478420/177402518-14c24e2d-b8a2-499b-8826-02a705c9e0bd.gif" 
data-canonical-src="https://user-images.githubusercontent.com/44478420/177402518-14c24e2d-b8a2-499b-8826-02a705c9e0bd.gif" width="280" height="280" />
