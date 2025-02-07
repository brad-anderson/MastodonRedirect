package dev.zwander.lemmyredirect

import dev.zwander.lemmyredirect.util.Jerboa
import dev.zwander.lemmyredirect.util.LaunchStrategyUtils
import dev.zwander.shared.App

class LemmyRedirectApp : App(
    launchStrategyUtils = LaunchStrategyUtils,
    defaultLaunchStrategy = Jerboa.JerboaStable,
)
