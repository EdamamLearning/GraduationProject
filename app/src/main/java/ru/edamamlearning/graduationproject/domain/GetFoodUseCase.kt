package ru.edamamlearning.graduationproject.domain

class GetFoodUseCase(
    private val repository: DomainRepository
) {
    suspend fun execute(text: String) = repository.get(text)
}