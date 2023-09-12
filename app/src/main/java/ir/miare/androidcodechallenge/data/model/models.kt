package ir.miare.androidcodechallenge.data.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable


data class FakeData(
    @JsonProperty("league") var league: League,
    @JsonProperty("players") var players: ArrayList<Player>
) {
    constructor() : this(League(), arrayListOf())
}

data class League(
    @JsonProperty("name") val name: String,
    @JsonProperty("country") val country: String,
    @JsonProperty("rank") val rank: Int,
    @JsonProperty("total_matches") val totalMatches: Int,
) {
    constructor(): this("", "", -1, -1)
}

data class Player(
    @JsonProperty("name") val name: String,
    @JsonProperty("team") val team: Team,
    @JsonProperty("total_goal") val totalGoal: Int
) : Serializable

data class Team(
    @JsonProperty("name") val name: String,
    @JsonProperty("rank") val rank: Int
) : Serializable
