package komnacki.gallery.login.domain;

interface UiMapper <T, UiModel>{

    fun mapToUiModel(model: T): UiModel

    fun mapFromUiModel(uiModel: UiModel): T
}