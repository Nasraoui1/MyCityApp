
import androidx.lifecycle.ViewModel
import com.example.mycityapp.data.CityData
import com.example.mycityapp.model.Category
import com.example.mycityapp.model.Recommendation
import com.example.mycityapp.ui.MycityUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MycityViewModel : ViewModel() {

    // Initial state
    private val _uiState = MutableStateFlow(
        MycityUiState(
            categoriesList = CityData.categories,
            currentCategory = CityData.defaultCategory,
            currentRecommendation = CityData.defaultCategory.recommendations[0],
            isShowingCategoryPage = true,
            isShowingRecommendationPage = false,
            isShowingRecommendationDetailPage = false
        )
    )

    val uiState: StateFlow<MycityUiState> = _uiState

    fun onCategoryClick(category: Category) {
        _uiState.value = _uiState.value.copy(
            currentCategory = category,
            isShowingCategoryPage = false,
            isShowingRecommendationPage = true
        )
    }

    fun onRecommendationClick(recommendation: Recommendation) {
        _uiState.value = _uiState.value.copy(
            currentRecommendation = recommendation,
            isShowingRecommendationPage = false,
            isShowingRecommendationDetailPage = true
        )
    }

    fun onBackClick() {
        when {
            _uiState.value.isShowingRecommendationDetailPage -> {
                _uiState.value = _uiState.value.copy(
                    isShowingRecommendationDetailPage = false,
                    isShowingRecommendationPage = true
                )
            }
            _uiState.value.isShowingRecommendationPage -> {
                _uiState.value = _uiState.value.copy(
                    isShowingRecommendationPage = false,
                    isShowingCategoryPage = true
                )
            }
        }
    }
}


