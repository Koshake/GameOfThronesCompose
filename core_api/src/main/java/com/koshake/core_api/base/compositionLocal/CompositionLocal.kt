package com.koshake.core_api.base.compositionLocal

import androidx.compose.runtime.staticCompositionLocalOf
import com.koshake.core_api.app.FacadeComponentProvider

val LocalFacadeComponent = staticCompositionLocalOf<FacadeComponentProvider> { error("No FacadeComponent provided!") }
