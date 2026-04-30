# Testing Guide

## Quick Start

### Run All Tests Locally
```bash
./gradlew test                    # Unit tests only
./gradlew connectedAndroidTest    # UI/integration tests (requires emulator)
./gradlew test connectedAndroidTest  # All tests
```

### Test Structure

**Unit Tests** (`src/test/`) — Fast, isolated logic testing
- ViewModel behavior (state changes, event handling)
- Repository data transformations
- Business logic calculations
- Tools: JUnit 5, Mockk, Hamcrest

**UI Tests** (`src/androidTest/`) — Compose screen testing
- Screen composition and rendering
- User interactions (clicks, input)
- Tools: Jetpack Compose UI Testing API

**Integration Tests** (`src/androidTest/integration/`) — End-to-end journeys
- Full user flows across multiple screens
- Repository and DataSource interactions
- Real or semi-mocked dependencies

## Writing Tests

### Unit Test Template
```kotlin
class MyViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: MyViewModel
    private val fakeRepository = FakeRepository()

    @BeforeEach
    fun setUp() {
        viewModel = MyViewModel(fakeRepository)
    }

    @Test
    fun `specific behavior description`() = runTest {
        // Arrange
        val input = createTestData()
        
        // Act
        viewModel.doSomething(input)
        advanceUntilIdle()
        
        // Assert
        assertThat(viewModel.state.value, instanceOf(Success::class.java))
    }
}
```

### UI Test Template
```kotlin
class MyScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `specific UI behavior`() {
        composeTestRule.setContent {
            MyScreen()
        }

        composeTestRule
            .onNodeWithText("Button Text")
            .assertIsDisplayed()
            .performClick()
    }
}
```

## Test Utilities

### Creating Test Data
```kotlin
// Simple factory function
val user = createUser(name = "Alice")

// Builder pattern for complex objects
val user = userBuilder()
    .withName("Bob")
    .withEmail("bob@example.com")
    .build()
```

### Fake Repository
```kotlin
val fakeRepository = FakeUserRepository()
fakeRepository.shouldThrowError = true  // Simulate error
fakeRepository.delayMs = 500             // Add delay
```

## Debugging Tests

### Run single test
```bash
./gradlew test --tests "HomeViewModelTest.loadUser*"
```

### Run with logging
```bash
./gradlew test --info
```

## CI/CD

Tests run automatically on push via CircleCI:
- Unit tests: ~2-3 minutes
- UI tests: ~5-7 minutes (includes emulator startup)
- View results: https://app.circleci.com/

## Best Practices

1. **One assertion per test when possible** — Easier to debug failures
2. **Use semantic matchers** — `onNodeWithText()` not coordinates
3. **Test behavior, not implementation** — Don't test private methods
4. **Keep tests DRY** — Use builders, factories, and test utilities
5. **Fail fast** — Quick unit tests before slow UI tests
