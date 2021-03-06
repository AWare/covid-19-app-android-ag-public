package uk.nhs.nhsx.covid19.android.app.common

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import uk.nhs.nhsx.covid19.android.app.SupportedLanguage
import java.util.Locale

@Parcelize
data class Translatable(val translations: Map<String, String>) : Parcelable {

    fun translate(): String {
        val languageAndRegion = Locale.getDefault().toLanguageTag()
        val languageCodeOnly = Locale.getDefault().language

        if (!isLanguageSupported(languageCodeOnly)) {
            return translations[fallbackLanguageAndRegion] ?: ""
        }

        val exactMatch = translations[languageAndRegion]
        if (exactMatch != null) {
            return exactMatch
        }

        val firstMatchedLanguageCode = translations.keys
            .firstOrNull { translationsLanguageAndRegion ->
                getLanguageCode(
                    translationsLanguageAndRegion
                ) == languageCodeOnly
            }

        return translations[firstMatchedLanguageCode ?: fallbackLanguageAndRegion] ?: ""
    }

    private fun getLanguageCode(languageAndRegion: String) =
        if (languageAndRegion.contains("-")) languageAndRegion.split("-")[0] else languageAndRegion

    private fun isLanguageSupported(languageCode: String) = SupportedLanguage.values()
        .map { it.code }
        .filterNotNull()
        .any {
            it == languageCode
        }

    companion object {
        private const val fallbackLanguageAndRegion = "en-GB"
    }
}
