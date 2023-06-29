package com.izhxx.navcore.utils

internal fun getNullableCoreMessage(): String =
    "IndoorNavigationCore is null because core isn't initialize. " +
            "To avoid this, you need to call the initialize method, and then get the instance" + easterEgg()

internal fun easterEgg(): String = "Huh, it's a core error. Don't worry and fix bugs :)"