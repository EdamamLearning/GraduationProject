package ru.edamamlearning.graduationproject.domain

class GetFoodUseCase(
    private val repository: DomainRepository
) {
    fun execute(text: String) = repository.get(text)
}