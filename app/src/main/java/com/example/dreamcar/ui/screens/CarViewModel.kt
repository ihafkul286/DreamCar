import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamcar.activity.Car
import com.example.dreamcar.repository.DragonBallRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CarViewModel(private val repository: DragonBallRepository) : ViewModel() {

    private val _carList = MutableStateFlow<List<Car>>(emptyList())
    val carList = _carList.asStateFlow()

    private val _favorites = mutableStateOf<Set<Car>>(emptySet())

    fun isFavorite(car: Car): Boolean {
        return _favorites.value.contains(car)
    }

    fun toggleFavorite(car: Car) {
        _favorites.value = if (_favorites.value.contains(car)) {
            _favorites.value - car
        } else {
            _favorites.value + car
        }
    }

    fun fetchCars() {
        viewModelScope.launch {
            try {
                _carList.value = repository.getCars()
            } catch (e: Exception) {

            }
        }
    }
}
