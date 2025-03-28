## Project Requirements

### 1. Core Features

Create an Android app that fetches and displays a list of planets from the [Star Wars API](https://swapi.dev/api/planets/), using the first page of results.

The list should display the following details for each planet:

- **Name**
- **Climate**
- **Population**

### 2. Detail View

Add a detail view that is displayed when the user selects a planet from the list.

The detail view should include the following information about the selected planet:

- **Name**
- **Climate**
- **Population**
- **Diameter**
- **Gravity**
- **Terrain**

### 3. Technical Considerations

- Use **Jetpack Compose** for all user interfaces.
- Use an architecture you’re comfortable with (e.g., **MVVM, MVI**, etc.).
- Simple UI; we do not need a beautiful interface (we love wireframes ✨).

---

## Key Features of the App

- **Planet List**: Fetches the first page of planets from the [Star Wars API](https://swapi.dev/api/planets/) and displays each planet’s **Name**, **Climate**, and **Population** in a scrollable list.

- **Planet Detail**: When a planet is selected, navigates to a detail screen showing **Name**, **Climate**, **Population**, **Diameter**, **Gravity**, and **Terrain**.

- **Architecture**: Follows a modular **MVVM+UseCase** structure with clear separation of concerns:

   - **Domain Layer**: Business logic, use cases, and repository interface (no Android framework dependencies).
   - **Data Layer**: Retrofit API service and repository implementation (handles network calls and data mapping).
   - **Presentation Layer**: Jetpack Compose UI screens and ViewModels (with Hilt injection).

- **TDD Workflow**: For each layer, we write unit tests and UI tests first (using fakes for dependencies), then implement just enough code to make tests pass, and finally refactor to the full solution.

- **Dependency Injection**: **Hilt** is used to provide singleton instances of the Retrofit API and repository, and to inject use cases into ViewModels.

---

## Project Structure

The project is organized into packages by feature and layer for clarity and maintainability:

- **domain/** – Core domain logic (entities, use cases, result wrappers).
   - **model/** – Domain models (e.g., `Planet.kt`).
   - **usecase/** – Use case classes (e.g., `GetPlanetsUseCase.kt`, `GetPlanetDetailUseCase.kt`).

- **data/** – Data layer implementations (network DTOs, Retrofit service, repository implementation).
   - **remote/** – Retrofit API interface (`PlanetApiService.kt`) and DTO data classes (`PlanetDto.kt`, `PlanetResponseDto.kt`).
   - **repository/** – Implementation of domain repository (`PlanetRepositoryImpl.kt`) using the API service.

- **di/** – Hilt modules and Application class for dependency injection setup (e.g., `AppModule.kt`, `StarWarsApp.kt`).

- **presentation/** – UI layer (Compose screens, ViewModels, navigation).
   - **planetlist/** – List screen UI (`PlanetListScreen.kt`) and ViewModel (`PlanetListViewModel.kt`).
   - **planetdetail/** – Detail screen UI (`PlanetDetailScreen.kt`) and ViewModel (`PlanetDetailViewModel.kt`).

