package ru.edamamlearning.graduationproject.domain

class GetUseCase(
    private val repository: DomainRepository
) {
    fun execute(text: String) = repository.get(text)
}