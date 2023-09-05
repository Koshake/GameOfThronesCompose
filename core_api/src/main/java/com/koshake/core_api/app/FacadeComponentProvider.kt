package com.koshake.core_api.app

import com.koshake.core_api.network.NetworkProvider

interface FacadeComponentProvider : AppProvider, NavGraphProvider, NetworkProvider
