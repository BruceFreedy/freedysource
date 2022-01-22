spawn = location("world", 0.5, -60 + 0.5, 0.5)

online = []

@join
{
    message = "&e" + player.name + "님 안녕하세요"
    init(player)
}

@left
{
}

#플레이어 초기화
init(player) {
    player.location=spawn
    player.title("&bANG ANG ANG", "", 0 60, 0)
}